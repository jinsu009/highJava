package basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient2 {

	public static void main(String[] args) {
	
		
		Socket socket = null;
		Scanner scan = new Scanner(System.in);
		System.out.print("대화명 입력 : ");
		String name = scan.nextLine();
		
		
		try {
			socket = new Socket("localhost",7777);
			//localhost에 해당 아이피를 입력하면 그 아이피 주소와 채팅이 가능하다 . 
			Sender sender = new Sender(socket, name);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
						
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
