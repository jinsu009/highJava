package basic;

//20.02.10

import java.util.*;

public class PropertiesTest {
	
	public static void main(String[] args) {
		// 차이점 공통점 만 알아두기
		
		
		// map과 비슷한점은 key와 value값을 분리해서 관리 
		
		// Properties는 Map보다 축소된 기능의 객체
		// map는 모든 형태의 데이터를 key와 value값으로 넣을 수 있지만 
		// properties는 key와 value값으로 string만 사용할 수 있다. 
		
		//map은 put(), get()메서드를 이용해서 데이터를 입출력하지만 
		//properties는 setproperty(), getproperty()메소드를 사용해서 입출력한다. 
		
		
		//객체생성
		Properties prop = new Properties();
		//제네릭을 사용하지 않는다.
		
		//데이터추가 (입력)
		prop.setProperty("name", "동동");
		prop.setProperty("tel", "010-1234-0987");
		prop.setProperty("add", "대전");
		
		//데이터 가져오기 
		String name = prop.getProperty("name");
		//둘다 string 형변환 필요없음
		
		String tel = prop.getProperty("tel");
		
		String add = prop.getProperty("add");
		
		System.out.println("이름>> "+name+"\t"+"전화번호>> "+tel+"\t"+"주소>> "+add+"\t");
		
		
		
	}

}
