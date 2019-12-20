package vendaingressos.helper;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {

	private final String ALGORITHM = "AES";
	private byte[] key;

	public AES(byte[] key) {
		this.key = key;
	}

	public byte[] encrypt(byte[] text) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		return (cipher.doFinal(text));
	}

	public byte[] decrypt(byte[] text) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		return (cipher.doFinal(text));
	}

}