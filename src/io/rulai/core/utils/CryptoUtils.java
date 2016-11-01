package io.rulai.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class CryptoUtils {

	/**
	 * DO NOT CHANGE
	 * <p>
	 * byte[] salt = Base64.decodeBase64("8@34DM0w/5%2d"
	 * .getBytes(Constants.UTF8));
	 */
	public static final byte[] SHA_SALT_BYTES = new byte[] { -13, 126, 3, 51, 76, 63, -25, 103 };

	// DO NOT CHANGE
	private static final int SHA_ITERATION_COUNT = 198;

	public static final byte[] AES_SECRET = new byte[] { 26, 101, 23, 98, 24, 81, 99, 10, 88, 60, 102, 123, 8, 102, 43,
			63 };

	public static final String ALGO_SHA256 = "SHA-256";

	public static final String ALGO_AES = "AES";

	public static final String ALGO_AES_INSTANCE = "AES";

	/**
	 * Takes 0.01ms per call on a string of length 40.
	 */
	public static String aesDecrypt(String msg) {
		try {
			SecretKeySpec key = new SecretKeySpec(AES_SECRET, ALGO_AES);
			Cipher cipher = Cipher.getInstance(ALGO_AES_INSTANCE);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] b64decoded = Base64.decodeBase64(msg.getBytes(Constants.UTF8));
			byte[] decrypted = cipher.doFinal(b64decoded);
			return new String(decrypted);
		} catch (NoSuchAlgorithmException e) {
			throw new SimpleRuntimeException(e);
		} catch (NoSuchPaddingException e) {
			throw new SimpleRuntimeException(e);
		} catch (InvalidKeyException e) {
			throw new SimpleRuntimeException(e);
		} catch (BadPaddingException e) {
			throw new SimpleRuntimeException(e);
		} catch (IllegalBlockSizeException e) {
			throw new SimpleRuntimeException(e);
		}
	}

	/**
	 * Takes 0.01ms per call on a string of length 40. Produced string is of
	 * variable length; A good approximation of produced String length is
	 * (n/16+1)*16*1.333 where n is size of input txt. This method returns url
	 * safe string.
	 */
	public static String aesEncrypt(String txt) {
		try {
			SecretKeySpec key = new SecretKeySpec(AES_SECRET, ALGO_AES);
			Cipher cipher = Cipher.getInstance(ALGO_AES_INSTANCE);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] bytes = txt.getBytes(Constants.UTF8);
			// encrypted cipherText results in length (txt.length() / 16 + 1) *
			// 16
			byte[] cipherText = cipher.doFinal(bytes);
			String b64encoded = Base64.encodeBase64URLSafeString(cipherText);
			return b64encoded;
		} catch (InvalidKeyException e) {
			throw new SimpleRuntimeException(e);
		} catch (BadPaddingException e) {
			throw new SimpleRuntimeException(e);
		} catch (IllegalBlockSizeException e) {
			throw new SimpleRuntimeException(e);
		} catch (NoSuchPaddingException e) {
			throw new SimpleRuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new SimpleRuntimeException(e);
		}
	}

	/**
	 * See javadoc for hashSHA(). This method uses a single iteration and no
	 * salt. Returns a hex base 16 string.
	 */
	public static String hashHexSHA256Basic(String txt) {
		String algo = ALGO_SHA256;
		byte[] hashed = hashSHA(txt, algo, 1, new byte[] {});
		return CoreUtils.toHexString(hashed);
	}

	/**
	 * Time: with numIterations=198, takes 0.2 millisecond per invocation on
	 * input of length 100. In comparison, with numIterations=1, takes only 0.01
	 * millisecond per invocation. Returns byte[] array which is 65 chars in HEX
	 * or 43 chars in Base64.
	 */
	private static byte[] hashSHA(String txt, String algo, int numIterations, byte[] shaSaltBytes) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algo);
			// BASE64Decoder decoder = new BASE64Decoder();
			// byte[] shaSaltBytes = decoder.decodeBuffer(shaSalt);
			byte[] salt = shaSaltBytes;
			digest.reset();
			digest.update(salt);
			byte[] btPass = digest.digest(txt.getBytes(Constants.UTF8));
			for (int i = 0; i < numIterations - 1; i++) {
				digest.reset();
				btPass = digest.digest(btPass);
			}
			return btPass;
		} catch (NoSuchAlgorithmException e) {
			throw new SimpleRuntimeException(e);
		}
	}

	/**
	 * See javadoc for hashSHA(). This method uses SHA_ITERATION_COUNT
	 * iterations and SHA_SALT. Returns a base 64 string.
	 */
	public static String hashSHA256Strong(String txt) {
		String algo = ALGO_SHA256;
		byte[] hashed = hashSHA(txt, algo, SHA_ITERATION_COUNT, SHA_SALT_BYTES);
		return Base64.encodeBase64URLSafeString(hashed);
	}

	public static void main(String[] args) {
		String secret = CryptoUtils.aesEncrypt("8gAfmtuD");
		String password = CryptoUtils.aesDecrypt(secret);

		System.out.println("secret = " + secret);
		System.out.println("password = " + password);
	}

	public static String md5Base64(String string) {
		byte[] hash = md5Hash(string);

		return Base64.encodeBase64String(hash);
	}

	public static String md5Base64URL(String string) {
		byte[] hash = md5Hash(string);

		return Base64.encodeBase64URLSafeString(hash);
	}

	public static byte[] md5Hash(String string) {
		byte[] hash;

		try {
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}
		return hash;
	}

	public static String md5(String string) {
		byte[] hash = md5Hash(string);

		StringBuilder hex = new StringBuilder(hash.length * 2);

		for (byte b : hash) {
			int i = (b & 0xFF);
			if (i < 0x10)
				hex.append('0');
			hex.append(Integer.toHexString(i));
		}

		return hex.toString();
	}

	public static String md5HexString(byte[] input) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(input);
		String hh = Hex.encodeHexString(thedigest);
		return hh;
	}

	private static String TOKEN_PREFIX = CryptoUtils.aesEncrypt("EG,lw1@-#%@DW!x.asf1sfd0h3mc8%^*(1FD");

	public static String generateEngageToken(String text) {
		String md5 = "" + System.currentTimeMillis()
				+ CryptoUtils.md5Base64URL(TOKEN_PREFIX + text + System.currentTimeMillis());
		return md5;
	}

	public static String generateEngageKey(String text) {
		String md5 = CryptoUtils.md5Base64URL(TOKEN_PREFIX + text + System.currentTimeMillis());
		return md5;
	}
}
