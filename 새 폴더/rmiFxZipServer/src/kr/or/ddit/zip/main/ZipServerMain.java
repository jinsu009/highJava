package kr.or.ddit.zip.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import kr.or.ddit.zip.service.IZipService;
import kr.or.ddit.zip.service.ZipServiceImpl;

public class ZipServerMain {
	
	public static void main(String[] args) {
		
		try {
			IZipService service = ZipServiceImpl.getInstance();
			Registry reg = LocateRegistry.createRegistry(9999);
			
			reg.rebind("zipService", service);
			
			System.out.println("서버가 준비 되었습니다.");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
