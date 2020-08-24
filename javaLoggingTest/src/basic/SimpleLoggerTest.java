package basic;

import org.apache.log4j.Logger;

public class SimpleLoggerTest {

	
	//로거를 사용하기 위해선 logger메소드를 생성해야한다.
	// logger클래스의 인스턴스를 구한다. 
	// 괄호안에는 현재위치의 클래스 이름을 적어준다.
	static Logger log = Logger.getLogger(SimpleLoggerTest.class);
	public static void main(String[] args){
		log.fatal("[FATAL] 로그 메시지 연습 ");
		log.error("[ERROR] 로그 메시지 연습 ");
		log.warn("[WARN] 로그 메시지 연습 ");
		log.info("[INFO] 로그 메시지 연습 ");
		log.debug("[DEBUG] 로그 메시지 연습 ");
		log.trace("[TRACE] 로그 메시지 연습 ");
		
	}
}
