package basic.udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpFileClient {

	public static void main(String[] args) throws IOException {
		// D:/D_Other/tiger.jpg 파일을 서버로 전송한다.
		
		File file = new File("D:/D_Other/tiger.jpg"); //파일객체 생성 
		if(!file.exists()) {
			// 파일 존재여부 묻기
			System.out.println("전송할 파일이 없습니다. 프로그램을 종료합니다.");
			return;
		}
		
		System.out.println("파일 전송 시작!!");
		DatagramSocket socket = null; 
		
		// 데이터 전송 순서 : "start",파일이름 -> 파일 용량 -> 파일 데이터,"end"
		try {
			InetAddress address = InetAddress.getByName("localhost");
			socket = new DatagramSocket();
			
			//----------------- start 전송 
			String str = "start";
			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length
					, address, 8888);
			socket.send(dp);
			//----------------- filename 전송 
			String fileName = file.getName();
			dp = new DatagramPacket(fileName.getBytes(), fileName.getBytes().length
					, address, 8888);
			socket.send(dp);
			//----------------- file 용량 전송
			long fileSize = file.length();
			String strSize = String.valueOf(fileSize); // 문자열로 변환 
			dp = new DatagramPacket(strSize.getBytes(), strSize.getBytes().length
					, address, 8888);
			socket.send(dp);
			//-----------------file 데이터 전송 
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			long totalLen = 0;
			while((len = fis.read(buffer))!=-1){
				dp = new DatagramPacket(buffer, len, address, 8888);
				socket.send(dp);
				totalLen += len;
				
				System.out.println("현재 진행 상태 : " + totalLen +" / " + fileSize + " byte(s)");
				
			}
			//----------------- end 전송
			
			str = "end";
			dp = new DatagramPacket(str.getBytes(), str.getBytes().length
					, address, 8888);
			socket.send(dp);
			
			fis.close(); // 파일 닫기 
			socket.close(); 
			System.out.println("\n\n전송 완료");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
