package basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TcpMultiChatServer {
	
	// 대화명과 클라이언트의 socket객체를 저장하기 위한 map객체 선언 >> 대화방 정보 저장 
	public Map<String, Socket> clientMap;
	// string : 대화명 
	// socket : 대화명으로 접속한 client
	
	//스레드를 사용하기 위해 map을 동기화 처리를 해줘야한다. >> 생성자에서 
	
	//생성자
	public TcpMultiChatServer() {
		// clientmap을 동기화 처리 한다. 
		clientMap = Collections.synchronizedMap(new HashMap<String, Socket>());
	}

	//서버프로그램의 시작 메소드 
	public void serverStart() {
		ServerSocket server = null; 
		Socket socket = null; 
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");
			//사용자 접속을 기다려야 하므로 반복문을 사용하다. 
			while(true) {
				socket = server.accept(); // 클라이언트(사용자) 대기
				
				//접속이 완료되면 
				System.out.println("["+ socket.getInetAddress() + " : " 
									+ socket.getPort()
									+ "] 에서 접속 했습니다.");
				
				// 서버에서 클라이언트로 메시지를 전송할 스레드 객체 생성 
				ServerReceiver sr = new ServerReceiver(socket);
				sr.start();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(server!=null) try {server.close();} catch(IOException e){}
		}
	}
	
	// 대화방에 저장된 모든 클라이언트에게 메시지를 전송하는 메소드 
	public void sendToAll(String msg) {
		// 대화방에 접속한 사용자의 대화명(key)들을 추출한다. 
		Iterator<String> it = clientMap.keySet().iterator();
		while(it.hasNext()) {
			try {
			String name = it.next(); // 대화명(key) 구하기 
			
			//dataoutput 에는 socket을 담아줘야한다. 
			
			Socket soc = clientMap.get(name); // 대화명 socket객체 구하기
			DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
			 
			dos.writeUTF(msg);//메세지 전송
			
			}catch(IOException e) {}
		}
	}
	
	public static void main(String[] args) {
		//많은 클라이언트들을 map이나 list에 담아둬야한다.
		new TcpMultiChatServer().serverStart();
	}
	
	// 스레드 생성
	// 서버에서 클라이언트로 메시지를 전송하는 thread
	class ServerReceiver extends Thread{
		Socket socket; 
		DataInputStream in; 
		DataOutputStream out;
		//데이터를 받으면서 보내줘야 하기 때문에 output도 필요하다.
		
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				// client의 소켓에서 데이터를 수신받을 inputStream객체 생성
				in = new DataInputStream(socket.getInputStream());
				
				// client의 소켓에서 데이터를 송신할 OutputStream(객체 생성 
				out = new DataOutputStream(socket.getOutputStream());
				
				
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
		
		/* 
		 * 서버 				클라이언트 
		 * 
		 * 1. 클라이언트가 서버로 대화명을 전송
		 * 2. 서버에서는 클라이언트가 보낸 대화명이 중복되는지 검사 
		 * ( 중복될 경우 : "이름중복", 중복이 아닐경우 : "ok" 값을 클라이언트에게 전송)
		 * 대화명이 중복되지 않을 때까지 1-2 반복
		 * 
		 * 
		 * 
		 */
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String name = ""; // 이름이 저장될 변수 선언 
			try {
				while(true) {
					//무한반복 
					// 서버에서는 클라이언트가 최초로 보낸 대화명을 받아서 대화명의 중복 여부를 검사하여 
					// feedback으로 클라이언트에게 보내준다. 
					name = in.readUTF();
					//map의 key값안에 중복되는 값이 있는지 검사
					if(clientMap.containsKey(name)) {
						//중복여부검사 (중복될 경우)
						out.writeUTF("이름중복");
					}else {
						//중복되지 않을 경우 
						out.writeUTF("OK"); break;//반복문빠져나가기 
					}
				}//while문 
				
				// 대화명을 받아서 전체 클라이언트에게 대화방참여 메세지를 보낸다. 
				// 채팅방에 참여한 모두에게 메세지를 전송하는 메소드를 호출
				sendToAll("[" + name + "] 님이 대화방에 입장하셨습니다.");
				
				// 대화명과 socket객체를 대화방 map에 추가한다. 
				clientMap.put(name, socket);
				
				System.out.println("현재 서버 접속자 수 : " + clientMap.size() + " 명");
				
				// 한 클라이언트가 보낸 메시지를 받아서 전체 대화방 참여자에게 보내준다. 
				while(in!=null) {
					sendToAll(in.readUTF());
				}
			} catch (IOException e) {
				// TODO: handle exception
			} finally {
				// 이 부분이 실행된다는 것은 해당 클라이언트가 접속이 종료되었다는 의미.. 
				sendToAll("[" +name + "] 님이 대화방을 나갔습니다. ");
				
				// 대화방 목록에서 접속을 종료한 회원의 정보를 삭제 
				clientMap.remove(name);
				System.out.println("현재 서버 접속자 수 : " + clientMap.size() + " 명");
				
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
