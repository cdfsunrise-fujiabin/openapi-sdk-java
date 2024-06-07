package org.cdfsunrise.service;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RsaService {
    private static Map<Integer,String> keyMap = new HashMap<>();

    /**
     * 生成随机公钥和密钥对
     */
    public static void getKeyPair() throws Exception {
        //KeyPairGenerator类用于生成公钥和密钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        //初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        //生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();//得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();//得到公钥
        //得到公钥字符串
        String publicKeyString=new String(Base64.encodeBase64(publicKey.getEncoded()));
        //得到私钥字符串
        String privateKeyString=new String(Base64.encodeBase64(privateKey.getEncoded()));
        //将公钥和私钥保存到Map
        keyMap.put(0,publicKeyString);//0表示公钥
        keyMap.put(1,privateKeyString);//1表示私钥
    }

    /**
     * RSA公钥加密
     *
     * @param str 加密字符串
     * @param publicKey 公钥key
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey, String charset) throws Exception {
        //base64编码的公钥
        // 去除前后缀
        publicKey = publicKey.replace("-----BEGIN RSA PUBLIC KEY-----", "");
        publicKey = publicKey.replace("-----END RSA PUBLIC KEY-----", "");
        // 共钥加密
        try{
            //通过X509编码的Key指令获得公钥对象
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(x509KeySpec);
            // RSA加密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            String encryptData = Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, str.getBytes(charset), pubKey.getModulus().bitLength()));
            return encryptData;
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + str + "]时遇到异常", e);
        }
    }

    public static String encrypt(String str, String publicKey) throws Exception {
        return encrypt(str, publicKey, "UTF-8");
    }

    /**
     * RSA私钥解密
     *
     * @param str 加密字符串
     * @param privateKey 私钥key
     * @return  解密字符串result
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception {

        // 去除前后缀
        privateKey = privateKey.replace("-----BEGIN RSA PRIVATE KEY-----", "");
        privateKey = privateKey.replace("-----END RSA PRIVATE KEY-----", "");
        java.security.Security.addProvider(
                new org.bouncycastle.jce.provider.BouncyCastleProvider()
        );
        //Base64解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str);
        // 通过PKCS#8编码的Key指令获得私钥对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(pkcs8KeySpec);
        // RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        byte[] decryptBytes = rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, inputByte, priKey.getModulus().bitLength());
        return new String(decryptBytes, "UTF-8");

    }

    /**
     * rsasplit加密字符串
     * @param cipher  加密cipher值
     * @param opmode  DECRYPT_MODE类型
     * @param datas   要生成验签byte
     * @param keySize  保留长度
     * @return  rsasplit之后byte数据
     */
    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize){
        int maxBlock = 0;
        if(opmode == Cipher.DECRYPT_MODE){
            maxBlock = keySize / 8;
        }else{
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try{
            while(datas.length > offSet){
                if(datas.length-offSet > maxBlock){
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                }else{
                    buff = cipher.doFinal(datas, offSet, datas.length-offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        }catch(Exception e){
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }

//    static String PKCS1v15 = "RSA/ECB/PKCS1Padding";
//    public static PublicKey pemToPublicKey(String pem) throws Exception {
//        PemReader reader = new PemReader(new StringReader(pem));
//        PemObject pemObject = reader.readPemObject();
//        byte[] content = pemObject.getContent();
//        reader.close();
//
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(
//                new BigInteger(1, content),
//                new BigInteger("65537") // Public exponent
//        );
//        return keyFactory.generatePublic(keySpec);
//    }
//
//    public static PrivateKey pemToPrivateKey(String pem) throws Exception {
//        PemReader reader = new PemReader(new StringReader(pem));
//        PemObject pemObject = reader.readPemObject();
//        byte[] content = pemObject.getContent();
//        reader.close();
//
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(
//                new BigInteger(1, content),
//                new BigInteger("65537") // This is not used for private keys but required by the constructor
//        );
//        return keyFactory.generatePrivate(keySpec);
//    }
//
//    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
//        Cipher cipher = Cipher.getInstance(PKCS1v15);
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
//        return Base64.getEncoder().encodeToString(encryptedBytes);
//    }
//
//    public static String decrypt(String encryptedText, PrivateKey privateKey) throws Exception {
//        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
//        Cipher cipher = Cipher.getInstance(PKCS1v15);
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
//        return new String(decryptedBytes);
//    }
//
//    public static String encrypt(String plainText, String publicKeyStr) throws Exception {
//        PublicKey publicKey = pemToPublicKey(publicKeyStr);
//        return encrypt(plainText, publicKey);
//    }
//
//    public static String decrypt(String encryptedText, String privateKeyStr) throws Exception {
//        PrivateKey privateKey = pemToPrivateKey(privateKeyStr);
//        return decrypt(encryptedText, privateKey);
//    }
}
