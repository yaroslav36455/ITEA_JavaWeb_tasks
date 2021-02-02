package ua.itea.db;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptographer {
	private static final int SHA_BITS = 512;
	private static final String ALGORITHM = "SHA-" + SHA_BITS;
	private static final int BYTES = SHA_BITS / 8;
	private static final String SALT = "arsЫ%$^rs";
	private MessageDigest messageDigest;
	
	public Cryptographer() {
		try {
			messageDigest = MessageDigest.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public static int getBytes() {
		return BYTES;
	}

	public byte[] encrypt(String str) {
		return encrypt(StandardCharsets.UTF_8.encode(SALT + str).array());
	}
	
	public byte[] encrypt(byte[] array) {		
		messageDigest.reset();
		return messageDigest.digest(array);
	}
}
