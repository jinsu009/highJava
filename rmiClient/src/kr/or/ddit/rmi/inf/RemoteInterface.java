package kr.or.ddit.rmi.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rmi.vo.FileInfoVO;
import kr.or.ddit.rmi.vo.TestVO;

public interface RemoteInterface extends Remote{

	// RMI용 인터페이스는 Remote를 상속해서 작성한다. 
	// 이 인터페이스에서 선언되는 모든 메서드들은 RemoteException을 throws해야한다. 
	
	public int doRemotePrint(String str) throws RemoteException; // 반환값
	public void doPrintList(List<String> list) throws RemoteException;
	public void doPrintVo(TestVO vo) throws RemoteException;
	
	
	// 파일전송용 메소드 추가 
	public void setFile(FileInfoVO fileVo)throws RemoteException;
	// 클라이언트에서 서버로 데이터 전송	
}
