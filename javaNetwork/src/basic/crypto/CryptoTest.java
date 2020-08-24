package basic.crypto;

import java.security.NoSuchAlgorithmException;

public class CryptoTest {
	
	public static void main(String[] args) throws Exception {
		String str = "Hello, world"; // 암호화 할 문자열 
		
		System.out.println("원본 : " + str);
		System.out.println("MD5 : " + CryptoUtil.md5(str));
		System.out.println("SHA-256 : " + CryptoUtil.sha256(str));
		System.out.println("SHA-512 : " + CryptoUtil.sha512(str));
		
		System.out.println("---------------");
		AES256Util aes256 = new AES256Util();
		String str2 = aes256.encrypt("Hello, world");
		String temp = "Hello, world";
		System.out.println("원 본 : " + temp);
		System.out.println("암호화 된 값 : " + str2);
		System.out.println("복호화 된 값 : " + aes256.decrypt(str2));
	}

}
