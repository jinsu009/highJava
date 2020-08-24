package basic.tcp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
	private ServerSocket server;
	private Socket socket;
	//socket으로 받아서 file로 저장

	public void serverStart() {
		//저장할 파일 객체 생성 
		File file = new File("D:/D_Other/test/tiger.jpg");
		try {
			// 서버 연결 준비 
			server = new ServerSocket(9999);
			System.out.println("서버가 준비되었습니다.");
			socket = server.accept();
			
			// 
			System.out.println("파일 다운로드 시작.");
			InputStream in = socket.getInputStream();
			// 파일을 읽어오기 위해 fos생성
			FileOutputStream fos = new FileOutputStream(file);
			
			// 1byte씩 읽어오면 시간이 많이 걸리니까 배열로 생성해서 파일을 한번에 불러옴 
			byte[] temp = new byte[1024];
			int length = 0;
			// 실제 읽어온갯수를 비교후 읽어온 갯수만큼 출력
			while((length=in.read(temp))!=-1) {
				fos.write(temp, 0, length);
			}
			System.out.println("파일 다운로드 완료");
			fos.close();
			in.close();
			socket.close();
			server.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		new TcpFileServer().serverStart();
	}

}
