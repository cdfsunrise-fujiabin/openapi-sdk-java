package org.cdfsunrise.service;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.alibaba.fastjson.JSON;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SM国密工具类
 * 
 * @author fujiabin
 */
public class SmService {
    /**
     * 拼接待加签的参数
     * 
     * @param appId             应用id
     * @param tntId             租户id
     * @param timestamp         请求时间戳
     * @param certificateSerial 密钥序列号
     * @param encryptKey        加密后的sm4
     * @param data              业务参数
     * @return
     */
    public static String assembleRequestParams(String appId, String tntId, String timestamp, String certificateSerial,
            String encryptKey, String data) {
        // 拼接待加签的参数
        // 存放待拼接的验签参数，去除sign签名值
        HashMap<String, String> requestParamsMap = new HashMap<>();
        requestParamsMap.put("app_id", appId);
        requestParamsMap.put("tnt_id", tntId);
        requestParamsMap.put("timestamp", timestamp);
        requestParamsMap.put("certificate_serial", certificateSerial);
        requestParamsMap.put("encrypt_key", encryptKey);
        // 获取解密后的业务参数
        requestParamsMap.put("data", data);
        return getSortedParams(requestParamsMap);
    }

    /**
     * SM2公钥加密，私钥解密
     * 
     * @param content   要加密的业务数据
     * @param publicKey SM2公钥
     * @return
     */
    public static String sm2Encrypt(String content, String publicKey) {
        SM2 sm2 = SmUtil.sm2(null, Base64.decode(publicKey));
        byte[] encrypt = sm2.encrypt(content, KeyType.PublicKey);
        return Base64.encode(encrypt);
    }

    /**
     * SM2私钥解密
     * 
     * @param encryptContent
     * @param privateKey
     * @return
     */
    public static String sm2Decrypt(String encryptContent, String privateKey) {
        SM2 sm2 = SmUtil.sm2(Base64.decode(privateKey), null);
        String decryptStr = StrUtil.utf8Str(sm2.decrypt(encryptContent, KeyType.PrivateKey));
        return decryptStr;
    }

    /**
     * 私钥签名
     * 
     * @param content    要加密的业务数据
     * @param privateKey SM2私钥
     * @return
     */
    public static String getSign(String content, String privateKey) {
        SM2 sm2Sign = SmUtil.sm2(Base64.decode(privateKey), null);
        sm2Sign.usePlainEncoding();
        byte[] sign = sm2Sign.sign(content.getBytes(), null);

        return Base64.encode(sign);
    }

    /**
     * 公钥验签
     * 
     * @param sign      签名
     * @param content   内容
     * @param publicKey 公钥
     * @return
     */
    public static Boolean verify(String sign, String content, String publicKey) {
        SM2 sm2 = SmUtil.sm2(null, Base64.decode(publicKey));
        sm2.usePlainEncoding();
        return sm2.verify(content.getBytes(), Base64.decode(sign));
    }

    /**
     * SM4对称加密
     * 
     * @param content 要加密的明文数据
     * @param key     SM4对称密钥
     */
    public static String sm4Encrypt(String content, String key) {
        SymmetricCrypto sm4 = SmUtil.sm4(Base64.decode(key));
        return sm4.encryptHex(content);
    }

    /**
     * SM4对称解密
     * 
     * @param encryptContent 要解密的密文数据
     * @param key            SM4对称密钥
     */
    public static String sm4Decrypt(String encryptContent, String key) {
        SymmetricCrypto sm4 = SmUtil.sm4(Base64.decode(key));
        return sm4.decryptStr(encryptContent, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 将Map并按照key值的 ASCII 码递增排序（字母升序排序），
     * 如果遇到相同字符则按照第二个字符的键值 ASCII 码递增排序，以此类推。
     * 将排序后的参数与其对应值，组合成 参数=参数值 的格式，并且把这些参数用 & 字符连接起来
     * 
     * @param params
     * @return
     */
    public static String getSortedParams(Map<String, String> params) {
        return params.entrySet().stream()
                .filter(entry -> entry.getValue() != null && !entry.getValue().isEmpty())
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }
}