package basic.tcp;

import java.io.DataInputStream;
import java.net.Socket;

// 이 클래스는 소켓에서 메시지를 받아서 그 메시지를 화면애 출력하는 일을 담당하는 스레드이다. 
public class Receiver extends Thread{
	
	private Socket socket; 
	DataInputStream dis; // 데이터를 받을 때는 inputStream
	
	// 생성자 >> 연결된 socket객체를 매개값으로 받아서 처리한다. 
	public Receiver(Socket socket) {
		this.socket = socket;
		try {
			// socket을 이요해서 스트림객체 생성 
			dis = new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		while(dis!=null) {
			try {
				// 받아온 메시지 출력 
				System.out.println(dis.readUTF());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
