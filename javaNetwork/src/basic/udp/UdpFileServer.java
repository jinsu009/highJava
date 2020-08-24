package basic.udp;

import java.io.File;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpFileServer {

	public static void main(String[] args) {
		// 클라이언트가 보내온 파일을 D:/D_Other/test폴더에 저장한다. 
		
		long fileSize;
		long totalLen = 0;
		String fileName;
		
		byte[] buffer = new byte[1024];
		System.out.println(" 서버 준비 중 @----");
		
		try {
			DatagramSocket socket = new DatagramSocket(8888);
			
			// 처음 메시지 받기 - start 
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			socket.receive(dp);
			String str = new String(dp.getData(), 0, dp.getLength());
			
			if(!str.equals("start")) {
				System.out.println("비 정상적이 접속입니다");
				return;
			}
			
			// 두번째 메시지 받기 - 파일 이름 
			dp = new DatagramPacket(buffer, buffer.length);
			socket.receive(dp);
			fileName = new String(dp.getData(), 0, dp.getLength());
			
			File file = new File("D:/D_Other/test/" + fileName); //파일 객체 생성 
			
			FileOutputStream fos = new FileOutputStream(file);
			// 파일 내용을 불러올 객체 생성?
			// 수신된 파일을 저장할 객체 
			
			// 세번째 메시지 받기 - 파일 용량 
			dp = new DatagramPacket(buffer, buffer.length);
			socket.receive(dp);
			str = new String(dp.getData(), 0, dp.getLength());
			//fileSize = Long.valueOf(str);
			fileSize = Long.parseLong(str); 
			
			System.out.println("파일 전송 시작 ");
			
			// 두번째 메시지 받기 - 파일 데이터
			dp = new DatagramPacket(buffer, buffer.length);
			while(true) {
				socket.receive(dp);
				int len = dp.getLength(); // 전송된 데이터 양 
				
				// 수신된 데이터가 'end'문자열인지 확인 작업
				str = new String(dp.getData(), 0, dp.getLength());
				if("end".equals(str)) {
					// end와 같으면 반복문을 빠져나간다.
					break;
				}
				// 수신 데이터를 파일에 저장한다. 
				fos.write(dp.getData(), 0, len);
				totalLen += len; 
				System.out.println("수신 진행 상태 : " +  totalLen + " / " + fileSize);
			}
			fos.flush();
			//현재 버퍼에 저장되어 있는 내용을 클라이언트로 전송하고 버퍼를 비운다. 
			fos.close();
			socket.close();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
