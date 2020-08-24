package basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 *  UDP 방식 특징 
 *  	> 비연결 지향, 데이터의 비신뢰성, 데이터가 순서대로 도착한다는 보장을 못함 
 *  	> TCP보다 속도가 빠르다
 *  
 *  사용되는 클래스 
 *  	- DatagramSocket : 데이터의 송수신과 관련된 클래스 (우체부 역할) 
 *  	- DatagramPacket : 주고 받을 데이터와 관련된 클래스 (소포) => 객체를 생성할 때 수신용 생성자와 송신용 생성자를 구분해서 사용한다.
 *  
 *  - TCP의 경우에는 스트림을 이용해서 데이터를 주고 받지만 
 *    UDP의 경우에는 Datagram을 이용해서 데이터를 송수신한다.
 *    
 *    사용자가 받은 메시지를 받아서 되돌려주는 예시를 해보자(에코기능)
 */

public class UdpServer {

	public static void main(String[] args) {
		try {
			// 통신할 포트번호를 지정하여 소켓을 생성한다. 
			// 괄호 안에는 UDP를 적어준다. 
			DatagramSocket socket = new DatagramSocket(8888);
			
			// 수신용 Packet과 송신용 packet을 변수선언 
			DatagramPacket inpacket, outpacket; 
			
			System.out.println("서버 실행 중 !");
			
			while(true) {
				// 무한 반복문 생성
				// 데이터가 저장될 byte형 배열 선언
				byte[] bMsg = new byte[512];
				
				// 수신용 패킷 객체 생성 >> 데이터가 저장될 byte형 배열과 길이를 매개값으로 준다
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 데이터를 수신한다. (데이터가 도달할 때까지 기다린다.)
				// 수신된 데이터의 패킷정보가 inpacket변수에 저장된다. 
				socket.receive(inpacket);
				
				// 수신 받은 packet에서 상대방의 주소, 포트번호등을 알 수 있다. 
				InetAddress address = inpacket.getAddress();
				int port = inpacket.getPort();
				System.out.println("상대방 정보");
				System.out.println("IP : " + address + " , PORT : " + port);
				
				// 상대방이 보낸 메시지 출력하기 
				// 상대방이 보낸 문자열도 byte배열 형태로 오기 때문에 이 byte형 배열을 문자열로 변환해준다.
				// 메시지는 inpacket.getData()메소드를 이용해서 구할수도 있고 
				// 입력용 패킷에 설정한 byte형 배열을 이용해서 구할수도 있다. 
				// inpacket.getLength() >> 실제 데이터 갯수 
				String msg = new String(inpacket.getData(),0,inpacket.getLength()); //형변환
//				String msg = new String(inpacket.getData()).trim(); 
				// trim을 사용하는것과 getlength를 사용하는 방법은 같으나 주로 getlength를 사용한다. 
				
				System.out.println("상대방이 보낸 메세지 : " + msg);
				
				// 수신완료
				//----------------------
				// 상대방에게 메시지 보내기 
				
				// 송신용 packet객체 생성 >> 송신할 데이터가 저장된 byte형 배열, 전송할 자료의 길이(배열의길이), 
				// 상대방의 주소(InetAddress instance), 상대방의 포트번호를 매개값으로 전달하여 생성한다.
				
//				byte[] sendMst = msg.getBytes("UTF-8");
				byte[] sendMsg = msg.getBytes();
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				// 송신하기 
				socket.send(outpacket);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
