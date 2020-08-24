package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLTest02 {
	
	public static void main(String[] args) throws IOException {
		// url에 접근해서 해당 자원내용읽어오기 
		// 예) naver.com의 index.html불러오기 
		
		URL url = new URL("https://naver.com/index.html"); 
		
		// url객체를 이용해서 urlconnection객체를 구한다.
		URLConnection urlCon = url.openConnection();
		
		// Header 정보보기
		System.out.println("Content-Type : " + urlCon.getContentType());
		System.out.println("Encoding : " + urlCon.getContentEncoding());
		System.out.println("Content : " + urlCon.getContent());
		System.out.println("-------------------");
		
		// 내용 출력하기 
		// 방법1. >> UrlConnection의 getInputStream()메소드 이용하기 
		// io 에서 배웠던 input아웃풋 사용하기 
		// Stream객체 생성 
//		InputStream is = urlCon.getInputStream();
		
		// 방법2. >> URL객체의 openStream메소드 이용하기
		InputStream is = url.openStream();
		
		InputStreamReader isr = new InputStreamReader(is);		//문자기반을 byte기반으로 변경
		BufferedReader br = new BufferedReader(isr);

		// 내용출력하기 
		while(true) {
			String str = br.readLine(); //한줄씩 읽기 
			if(str == null ) break;
			System.out.println(str);
		}
	
		//스트림 닫기 
		br.close();
		System.out.println("==============================");
		//naver에서 마우스 오른쪽 버튼 : 페이지 소스 보기 
		
	}

}































