package basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		// 송수신용 패킷 변수 선언 
		DatagramPacket inpacket, outpacket;
		
		// 데이터가 저장될 byte배열 
		byte[] bMsg = new byte[512];
		
		try {
			// 통신할 상대방의 주소와 포트번호를 지정해서 DatagramSocket객체 생성
			DatagramSocket socket = new DatagramSocket();
			
			// 받을 곳의 주소 (InetAddress) 객체 생성 
			InetAddress address = InetAddress.getByName("localhost");
			
			while(true) {
				// 전송할 메시지 입력 
				System.out.print("보낼 메시지 입력 : " );
				String msg = s.nextLine();
				
				if("/end".equals(msg)) {
					break; // 반복문 종료 , 채팅 중지 여부 검사 
				}
				
				// 전송용 packet 객체 생성 
				outpacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, 8888);
				
				// 서버로 전송하기 
				socket.send(outpacket);
				//-------
				// 서버에서 온 메시지를 받아서 출력하기 
				
				// 수신용 패킷 객체 생성 
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 데이터 수신 
				socket.receive(inpacket);
				
//				System.out.println("서버응답 : " + new String(inpacket.getData()).trim());
				// trim을 사용하면 전에 보낸 전송 글자길이가 나중에보내는 글자의 길이보다 길 경우 데이터가 겹쳐서 나온다. 
				
				System.out.println("서버응답 : " + new String(inpacket.getData(),0,inpacket.getLength()));
				
				System.out.println();
				
			} // while문 종료 
			
			System.out.println("통신 종료");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
