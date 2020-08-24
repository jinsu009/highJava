package rmi.chat.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import rmi.chat.inf.IChat;
import rmi.chat.inf.IChatClient;

// 채팅 서버의 RMI용 객체  
public class RmiChatServer extends UnicastRemoteObject implements IChat{
	
	// 채팅하는 유저들 저장??
	private List<IChatClient> clientList;
	
	// 생성자
	public RmiChatServer() throws RemoteException {
		clientList = new ArrayList<IChatClient>();
	}

	public static void main(String[] args) {
		// 채팅 서버 객체를 생성해서 Registry에 등록한다. 
		try {
			IChat server = new RmiChatServer();
			Registry reg = LocateRegistry.createRegistry(1099);
			
			reg.rebind("chatServer", server);
			System.out.println("서버가 준비되었습니다! ");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	// 접속한 클라이언트 객체를 list에 추가하는 메소드 
	@Override
	public void setClient(IChatClient client) throws RemoteException {
		clientList.add(client);
		
	}

	
	// 매개값으로 넘어오는 문자열이 클라이언트가 보내는 메시지이다 
	// List에 등록된 클라이언트에게 메시지를 전달하는 메소드 
	@Override
	public void setMessage(String msg) throws RemoteException {
		for(IChatClient client : clientList) {
			System.out.println(msg); // 데이터 출력확인
			client.printMessage(msg); // 각 클라이언트로 전달 			
		}
		
	}

}


















