package basic;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {
	public static void main(String[] args) throws MalformedURLException {
		// URL 클래스 >> 인터넷에 존재하는 서버들의 자원에 접근 할 수 있는 주소를 다루는 클래스 
		// URLConnerction 클래스 >> 어플리케이션과 url간의 통신연결을 위한 추상 클래스 
		
		// http://www.ddit.or.kr:80/index.html?ttt=123
		// ttt=123 .. 선생님이 임의적으로 붙인거 
//		URL url = new URL("http://www.ddit.or.kr:80/index.htm?ttt=123");
		
		URL url = new URL("http","www.ddit.or.kr",80,"/index.htm?ttt=123");
		
		System.out.println("프로토콜 : " + url.getProtocol());
		System.out.println("Host : " + url.getHost());
		System.out.println("file : " + url.getFile());
		System.out.println("Query : " + url.getQuery());
		System.out.println("Path : " + url.getPath());
		System.out.println("Port : " + url.getPort());
		System.out.println();
		System.out.println(url.toExternalForm());
	}

}
