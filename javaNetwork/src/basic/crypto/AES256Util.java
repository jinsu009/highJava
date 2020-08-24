package basic.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256Util {
	
	private String iv; 
	private Key keySpec;
	
	//16자리의 키값을 설정한다.  키값을 한글로 설정하면 오류가 발생할 수 있따. utf-8로 변환
	private String key="a12b435nzz0+_8hn"; 
	// 이 key값과 helloworld 를 섞어서 암호화를 진행 
	
	// 생성자
	public AES256Util() throws UnsupportedEncodingException {
		this.iv = key.substring(0,16);
		
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8");
		int len = b.length;
		if(len>keyBytes.length) {
			len = keyBytes.length;
		}
		
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		this.keySpec = keySpec;
	}
	
	// 암호화 하는 메소드 
	public String encrypt(String str) throws NoSuchAlgorithmException,
	NoSuchPaddingException, 
	InvalidKeyException, 
	InvalidAlgorithmParameterException, 
	IllegalBlockSizeException, 
	BadPaddingException,
	UnsupportedEncodingException {
		
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		// ENCRYPT_MODE : 암호화
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes())); // 암호화 모드 초기화
		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		
		String enStr = Base64.getEncoder().encodeToString(encrypted);
		
		return enStr;
	}
	
	// 복호화 하는 메소드 
	
	public String decrypt(String str) throws NoSuchAlgorithmException, 
	NoSuchPaddingException,
	InvalidKeyException, 
	InvalidAlgorithmParameterException,
	UnsupportedEncodingException,
	IllegalBlockSizeException, 
	BadPaddingException {
		
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		// DECRYPT_MODE : 복호화
		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes())); // 암호화 모드 초기화

		byte[] byteStr = Base64.getDecoder().decode(str);
		return new String(c.doFinal(byteStr),"UTF-8");
		
	}
}
