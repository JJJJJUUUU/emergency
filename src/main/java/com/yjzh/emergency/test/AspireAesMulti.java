package com.yjzh.emergency.test;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;



public class AspireAesMulti {
	private Cipher cipher;
	SecretKeySpec key = null;	
	AlgorithmParameterSpec iv = null;

	public static void main(String[] args) throws Exception {
		AspireAesMulti aes = AspireAesMulti.getInstance("0123456789abcdef0123456789abcdef","0123456789abcdef0123456789abcdef");

		System.out.println(aes.decrypt("KlXo1KHhyg2wfHxNFy77+A=="));

        System.out.println(aes.encrypt("18380188628"));

	}



	public static AspireAesMulti getInstance(String key, String iv) {
		return  new AspireAesMulti(key, iv);
	}
	
	private AspireAesMulti(String keyStr, String ivStr) {
		try {
			iv = new IvParameterSpec(packHex(ivStr));
			key = new SecretKeySpec(packHex(keyStr), "AES");
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param hex
	 * @return byte[]
	 */
	public static byte[] packHex(String hex) {
		int len = hex.length() >> 1;
		byte[] b = new byte[len];
		short k = 0;
		for (int i = 0, j = 0; i < len; i++, j = i << 1) {
			k = Short.parseShort(hex.substring(j, j + 2), 16);
			b[i] = (byte) (k & 0xff);
		}
		return b;
	}
	
	public String decrypt(String txt) {
			try {
				cipher.init(Cipher.DECRYPT_MODE, key, iv);
				byte[] b = txt.getBytes();
				b = Base64.decode(b, 0, b.length, Base64.NO_WRAP);
				b = cipher.doFinal(b);
				return new String(b, "utf-8");
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (InvalidAlgorithmParameterException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;
	}

    private String encrypt(String text) {
		byte[] b = this.encrypt(text.getBytes(StandardCharsets.UTF_8));
		return java.util.Base64.getEncoder().encodeToString(b);
    }
	public byte[] encrypt(byte[] txt) {
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			return cipher.doFinal(txt);
		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return new byte[0];
	}
}
