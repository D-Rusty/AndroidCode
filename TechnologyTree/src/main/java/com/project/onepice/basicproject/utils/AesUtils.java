package com.project.onepice.basicproject.utils;


import org.spongycastle.jce.provider.BouncyCastleProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by onepice2015 on 2017/2/7.
 * <p>
 * Ase 加密工具类
 */

public class AesUtils {
    public static final String AES = "AES";
    public static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    /**
     * 获取秘钥
     */
    public static byte[] getSecretKey() {
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(AES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //秘钥长度
        keyGenerator.init(256);
        SecretKey secretkey = keyGenerator.generateKey();
        return secretkey.getEncoded();
    }




    /**
     * 加密文件
     */

    public void decryPtionFile(Key key, String srcFile) {
        int len = 0;
        byte[] buffer = new byte[5 * 1024];
        byte[] cipherbuffer = null;
        Cipher cipher = null;
        IvParameterSpec ivParameterSpec = new IvParameterSpec("0011004400670087000".getBytes());
        FileInputStream foi = null;
        FileOutputStream fos = null;
        try {
            cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM, new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            foi = new FileInputStream(new File(srcFile));

            //
            // fos = new FileOutputStream(new File(detFile));

            while ((len = foi.read(buffer)) != -1) {
                cipherbuffer = cipher.update(buffer, 0, len);
                fos.write(cipherbuffer);
                fos.flush();
            }

            cipherbuffer = cipher.doFinal();
            fos.write(cipherbuffer);
            fos.flush();

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } finally {
            try {
                if (foi != null) {
                    foi.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}








