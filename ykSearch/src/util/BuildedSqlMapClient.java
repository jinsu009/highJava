package util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BuildedSqlMapClient {
	
	//log를 추가
	private static Logger logger = Logger.getLogger(BuildedSqlMapClient.class);
	
	
	private static SqlMapClient smc;
	
	static{
		//이 부분은 클래스로 따로 생성해서 불러올수 있도록 
				try {
					// 1. iBatis의 환경설정 파일을 읽어와서 실행시킨다.
					// 1-1. 문자 인코딩 캐릭터셋 설정
					
					logger.info("환경설정 파일의 인코딩 케릭터셋 설정 시작");
					Charset charset = Charset.forName("UTF-8");
					Resources.setCharset(charset);
					logger.info("환경설정 파일의 인코딩 케릭터셋 설정 완료");
					
					
					// 1-2. 환경설정용 xml문서 읽어오기
					logger.info("환경설정 파일(sqlMapConfig.xml) 읽어오기 ==> Reader객체 생성");
					Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
					
					// 1-3. 위에서 읽어온 Reader객체를 이용하여 실제 작업에 사용할 객체를 생성한다.
					// 		=> 환경설정용 xml문서의 내용을 처리한 후 작업에 사용할 객체를 생성해준다. 
					logger.info("환경 설정 완료 후 sqlMapClient 객체 생성");
					
					smc = SqlMapClientBuilder.buildSqlMapClient(rd);
					
					logger.debug("Reader객체 닫기 ");
					
					rd.close();
				} catch (IOException e) {
					//System.out.println("SqlMapClient 객체 생성 실패");
					//e.printStackTrace();
					logger.error("SqlMapClient객체 생성 실패 ",e);
				}
	}
	
	public static SqlMapClient getSqlMapClient(){
		logger.debug("SqlMapClient객체 반환하기 ");
		return smc;
	}
	
}
