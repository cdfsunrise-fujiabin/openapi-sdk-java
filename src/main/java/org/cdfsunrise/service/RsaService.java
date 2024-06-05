package org.cdfsunrise.service;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.StringReader;
import java.math.BigInteger;
import java.security.*;
import javax.crypto.Cipher;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

public class RsaService {

    public static PublicKey pemToPublicKey(String pem) throws Exception {
        PemReader reader = new PemReader(new StringReader(pem));
        PemObject pemObject = reader.readPemObject();
        byte[] content = pemObject.getContent();
        reader.close();

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(
                new BigInteger(1, content),
                new BigInteger("65537") // Public exponent
        );
        return keyFactory.generatePublic(keySpec);
    }

    public static PrivateKey pemToPrivateKey(String pem) throws Exception {
        PemReader reader = new PemReader(new StringReader(pem));
        PemObject pemObject = reader.readPemObject();
        byte[] content = pemObject.getContent();
        reader.close();

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(
                new BigInteger(1, content),
                new BigInteger("65537") // This is not used for private keys but required by the constructor
        );
        return keyFactory.generatePrivate(keySpec);
    }

    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, PrivateKey privateKey) throws Exception {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    public static String encrypt(String plainText, String publicKeyStr) throws Exception {
        PublicKey publicKey = pemToPublicKey(publicKeyStr);
        return encrypt(plainText, publicKey);
    }

    public static String decrypt(String encryptedText, String privateKeyStr) throws Exception {
        PrivateKey privateKey = pemToPrivateKey(privateKeyStr);
        return decrypt(encryptedText, privateKey);
    }
}
