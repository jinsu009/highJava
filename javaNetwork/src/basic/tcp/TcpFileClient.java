package basic.tcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpFileClient {
	private Socket socket;
	
	public void clientStart() {
		File file = new File("D:/D_Other/tiger.jpg");
		//클라이언트가 보내기전에 파일을 읽으면 오류발생 
		if(!file.exists()) {
			System.out.println("전송할 파일이 없습니다.");
			return;
		}
		try {
			socket = new Socket("localhost",9999);
			
			System.out.println("접속완료, 파일전송 시작");
			
			FileInputStream fin = new FileInputStream(file);
			OutputStream os = socket.getOutputStream();
			
			byte[] temp = new byte[1024];
			int len = 0;
			while((len=fin.read(temp))!=-1) {
				os.write(temp, 0, len);
				//temp의 0부터 len까지 
			}
			System.out.println("파일 전송 끝!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}

}
