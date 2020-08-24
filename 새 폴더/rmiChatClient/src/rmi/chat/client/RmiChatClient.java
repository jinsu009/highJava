package rmi.chat.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import rmi.chat.inf.IChat;
import rmi.chat.inf.IChatClient;

// 클라이언트용 인터페이스를 구현하여 작성 
public class RmiChatClient extends UnicastRemoteObject implements IChatClient{

	
	// 생성자 
	protected RmiChatClient() throws RemoteException { }

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("대화명 입력 > " );
		String name = s.next();
		s.nextLine(); //버퍼비우기 
		
		// Registry에 등록된 채팅 서버에 접속하여 객체 구하기 
		try {
			// 클라이언트에서 구현한 객체를 서버에서 사용하기 위해 객체를 보내야 한다. ?
			
			// 서버에 저장할 클라이언트 객체 생성 
			IChatClient client  = new RmiChatClient();
			Registry reg = LocateRegistry.getRegistry("localhost", 1099);
		
				IChat server = (IChat) reg.lookup("chatServer");
			
				
				server.setClient(client); // 서버에 클라이언트 객체 추가하기 
				
				while(true) {
					String message = s.nextLine();
					server.setMessage("["+name + "] : " + message);
				}
				//종료를 하고 싶을땐 종료 키워드를 등록해서 조건문을 발생시킨다. 
				
		}catch (NotBoundException e) 
		{
			e.printStackTrace();
		}
		catch (RemoteException e) 
		{
			e.printStackTrace();
		}
	}
	
	// 서버가 보내온 메시지를 화면에 출력하는 메소드 
	@Override
	public void printMessage(String msg) throws RemoteException {
		System.out.println(msg);
	}
	
	
}
