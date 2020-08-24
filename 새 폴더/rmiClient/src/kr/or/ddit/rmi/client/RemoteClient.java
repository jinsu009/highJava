package kr.or.ddit.rmi.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.rmi.inf.RemoteInterface;
import kr.or.ddit.rmi.vo.FileInfoVO;
import kr.or.ddit.rmi.vo.TestVO;

public class RemoteClient {

	// 클라이언트쪽의 VO나 인터페이스는 서버의 VO나
	// 인터페이스가 있는 패키지의 구조와 파일명 그리고 내용 까지 
	// 동일하게 구성되어 있어야 한다. 
	
	public static void main(String[] args) {
		
		try {
			// 등록된 서버를 찾기위해 Registry객체를 생성한다. (서버접속)
			Registry reg = LocateRegistry.getRegistry("localhost",8888);
			
			// 사용할 객체는 서버에 등록된 Alias로 찾아서 객체를 불러온다.
			// 형식 ) Registry 객체 변수. lookup("객체Alias");
			
				
				// 서버에서 만들어진 객체 
				RemoteInterface inf = (RemoteInterface) reg.lookup("server");
				
				// 이 이후 부터는 불러온 객체의 메소들 호출해서 사용할 수 잇따. 
				int a = inf.doRemotePrint("안녕하세요 클라이언트 입니다. !");
				System.out.println("반환값 > " + a);
				System.out.println("------------------");

				
				System.out.println("doPrintList()메소드 호출 ");
				List<String> nameList = new ArrayList<String>();
				nameList.add("북극곰");
				nameList.add("보아뱀");
				nameList.add("구렁이");
				nameList.add("이구아나");
				nameList.add("악어");
				inf.doPrintList(nameList);
				System.out.println("------------------");
				
				System.out.println("doPrintVo()메소드 호출");
				TestVO test = new TestVO();
				test.setName("퀏카");
				test.setNum(2020);
				inf.doPrintVo(test);
				System.out.println("------------------");
				
				// 파일 전송하기 
				System.out.println("파일전송 시작 !! ");
				// 전송할 파일의 File객체 생성 >> 전송할 파일의 존재여부검사를 위해 만들어준다.  
				File file = new File("d:/d_Other/tiger.jpg");
				if(!file.exists()) {
					System.out.println("전송할 파일이 없습니다. 다시 확인하세요!");
					return;
				}
				FileInfoVO fVo = new FileInfoVO();
				fVo.setFileName(file.getName()); // 파일이름 생성 
				
				// 파일의 크기 구하기 
				long fsize = file.length();
				// 파일의 내용을 읽어와 저자할 byte형 배열 선언 
				// 배열의 크기는 파일의 크기와 같도록 한다. > 한번에 가져오기 위해 
				byte[] data = new byte[(int) fsize]; 
				// 크기를 정할땐 long를 못쓴다. casting 필요
				
				try {
					FileInputStream fin = new FileInputStream(file);
					fin.read(data); //파일내용을 읽어와 byte형 배열에 저장한다. 
					
					// 읽어온 데이터를 FileInfoVO객체에 셋팅한다. 
					fVo.setFileData(data);
					
					// RMI용 파일 전송용 메소드 호출 
					inf.setFile(fVo);
					System.out.println("파일전송 끝@@@");
					
				}catch(IOException ee) {
					ee.printStackTrace();
				}
		} catch (NotBoundException e) {
			e.printStackTrace();}
			catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

}
