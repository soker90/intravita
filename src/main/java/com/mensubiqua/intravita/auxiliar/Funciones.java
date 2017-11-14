package com.mensubiqua.intravita.auxiliar;


import java.security.MessageDigest;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;

/**
 * Funciones - Clase encargada de realizar funciones de encriptaci�n y desencriptaci�n,
 * as� como generar cadenas o validar patrones. Funciones adicionales �tiles para otros m�todos.
 * 
 *
 * @author Ulises Ceca, Ignacio Dones, Jos� Mar�a Sim�n, Miguel Ampuero, Eduardo Parra
 * @since 1.8
 * @version 2.0
 */
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

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            System.out.println(ex.toString());
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
            System.out.println(ex.toString());
        }

        return null;
    }
    
    public static String encrypt_md5(String password) {
    	String pass_md5;
    	byte[] thedigest = null;
    	
		try {
			byte[] bytesOfMessage = password.getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("SHA-384");
			thedigest = md.digest(bytesOfMessage);
			pass_md5 = DatatypeConverter.printHexBinary(thedigest).toLowerCase();
		}catch(Exception e)
		{
			return "error";
		}
		return pass_md5;
    }
    
    public static String generarStringAleatorio() {
    	String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        int longitud = salt.length();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    
    public static boolean validarPatron(String patron, String cadena) {
    	Pattern p = Pattern.compile(patron);
        Matcher m = p.matcher(cadena);
        return m.matches();
 
    }
    
    public static boolean validarEmail(String email) {
    	String patron = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    	
        boolean valido = Funciones.validarPatron(patron, email);
        return valido;
 
    }
    
    public static boolean validarCompartir(String id) {
    	String patron = "^cp#[a-z0-9]{24,24}$";
        boolean valido = Funciones.validarPatron(patron, id);
        return valido;
 
    }
    
}
