package basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException {
		//InetAdderss 클래스 >> ip주소를 다루기 위한 클래스 
		//사이트의 아이피 주소를 가지고 올 수있다. 
		//예) 네이버사이트의 IP주소 가져오기 
		//네트워크 입출력은 예외처리를 해줘야 한다. 
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		System.out.println("HostName : " + naverIp.getHostName());
		System.out.println("HostAddr : " + naverIp.getHostAddress());
		System.out.println();
		
		// 자신의 컴퓨터 ip주소 가져오기 
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("HostName : " + localIp.getHostName()); //컴퓨터 이름 
		System.out.println("HostName : " + localIp.getHostAddress()); //아이피 주소 
		System.out.println();
		
		//ip주소가 여러개인 호스트의 정보 가져오기  , 아이피 정보를 쉽게 구할 수 있다. 
		InetAddress[] naverArrays = InetAddress.getAllByName("www.naver.com");
//		InetAddress[] naverArrays = InetAddress.getAllByName("www.daum.net");
		for(InetAddress nIp : naverArrays) {
			System.out.println(nIp.toString());
		}
	}
}
