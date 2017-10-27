package com.mensubiqua.intravita.auxiliar;


import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;

public abstract class Funciones {
	private static String key = "IntravitaQQ12345";
	private static String initVector = "RandomInitVector";
    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            System.out.println("encrypted string: "
                    + Base64.encodeBase64String(encrypted));

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            
        }

        return null;
    }
    
    public static String encrypt_md5(String password) {
    	String pass_md5;
    	byte[] thedigest = null;
    	
		try {
			byte[] bytesOfMessage = password.getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("MD5");
			thedigest = md.digest(bytesOfMessage);
			pass_md5 = DatatypeConverter.printHexBinary(thedigest).toLowerCase();
		}catch(Exception e)
		{
			return "error";
		}
		return pass_md5;
    }
}
