package basic;

import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

//200310--02
public class LprodIbatisTest {

	public static void main(String[] args) {
		// iBatis를 이용하여 DB자료를 처리하는 작업 순서
		
		try {
			// 1. iBatis의 환경설정 파일을 읽어와서 실행시킨다.
			// 1-1. 문자 인코딩 캐릭터셋 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			// 1-2. 환경설정용 xml문서 읽어오기 
			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			// 1-3. 위에서 읽어온 Reader객체를 이용하여 실제 작업에 사용할 객체를 생성한다.
			// 		=> 환경설정용 xml문서의 내용을 처리한 후 작업에 사용할 객체를 생성해준다. 
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();
			//-------------------------환경설정 부분 종료
			
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다. 
			
			/*// 2-1. insert 연습 .. lprodTest클래스에 insert문 기재
			// 1) VO객체에 insert할 데이터를 저장한다. 
			System.out.println("insert 작업을 시작합니다. ");
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(200);
			lvo.setLprod_gu("T101");
			lvo.setLprod_nm("휴대폰");
			
			// 2) SqlMapClient 객체변수를 이용하여 해당 쿼리문을 호출해서 실행한다. 
			// 형식) smc.insert("namespace속성값.호출할id속성값",파라미터클래스);
			//		반환값 : insert 성공 > null, 실패 > 오류객체
			Object obj = smc.insert("lprodTest.insertLprod",lvo);
			if(obj == null)
			{
				System.out.println("insert 작업이 성공했습니다. >ㅁ< ");
			}else{
				System.out.println("insert 작업이 실패했습니다. 9-9");
			}
			System.out.println("------------------------------------");*/
			
			
			/*// 2-2. update 연습 .. lprodTest클래스에 update문기재 
			System.out.println("update 시작...");
			
			// 변경될 데이터를 VO객체에 저장한다. 
			LprodVO lpvo2 = new LprodVO();
			lpvo2.setLprod_id(250);
			lpvo2.setLprod_gu("T101");
			lpvo2.setLprod_nm("스마트폰");
			
			// update()메소드를 이용해서 처리한다. 
			// 형식) smc.update("namespace속성값.호출할id속성값",파라미터를래스)
			//		반환값 : 작업에 성공한 레코드
			int cnt = smc.update("lprodTest.updateLprod", lpvo2);
			if(cnt>0){
				System.out.println("update 작업 성공!");
			}else{
				System.out.println("update 작업 실패!!!");
			}*/
			
			/*// 2-3. delete 연습 .. lprod_gu 값으로 삭제 
			System.out.println("delete 시작!");
			
			// delete() 메소드 사용
			// delete() 메소드의 반환값은 작업에 성공한 레코드 수이다. 
			//int cnt2 = smc.delete("lprodTest.deleteLprod", "T101");
			int cnt2 = smc.delete("lprodTest.deleteLprod", "K101");
			if(cnt2>0){
				System.out.println("delete 작업 성공!!");
			}else{
				System.out.println("실패!");
			}*/
			
			/*// 2-4. select 연습
			// 1) select한 결과가 여러개일 경우 
			System.out.println("select 연습 (결과가 여러개일 경우)");
			// 응답결과가 여러개일경우에는 queryForList()메소드를 사용한다. 
			// 이 메서드는 여러개의 레코드 각각은 VO에 담은 후 이 VO객체를 LIST에 추가해주는 작업을 자동으로 수행한다. 
			List<LprodVO> lprodList = smc.queryForList("lprodTest.getAllLprod");
			System.out.println("=========================================");
			for(LprodVO lpvo3 : lprodList){
				System.out.println("Id >> " + lpvo3.getLprod_id());
				System.out.println("gu >> " + lpvo3.getLprod_gu());
				System.out.println("name >> " + lpvo3.getLprod_nm());
				System.out.println("----------------------------------------");
			}
			System.out.println("출력완료");*/

			
			// 2). select한 결겨과가 1개일경우
			System.out.println("select 연습 (결과가 하나일 경우)");
			
			// 응답결과가 1개가 확실할 경우에는 queryForObject()메소드를 사용한다. 
			// 반환값은 해당 쿼리문의 resultClass에 설정할 객체가 반환되는데 
			// Object형으로 반환되어 형변환을 해야 한다. 
			LprodVO lpvo4 = (LprodVO) smc.queryForObject("lprodTest.getLprod","P101");
			System.out.println("----------------------------------------");
			System.out.println("Id >> " + lpvo4.getLprod_id());
			System.out.println("gu >> " + lpvo4.getLprod_gu());
			System.out.println("name >> " + lpvo4.getLprod_nm());
			System.out.println("----------------------------------------");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}




























