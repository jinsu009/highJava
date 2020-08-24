package basic;

//20.02.11-03

/*
 * 열거형 ==>> - 서로 관련있는 상수들의 집합
 * 			 - 클래스처럼 보이게 하는 상수 
 * 			 - 비교할때 '==' 연산자를 사용해서 비교할 수 있다.
 * 			 - switch
 */

import java.util.*;

public class EnumTest {

	// 열거형 만들기

	// 1)) 기본적인 열거형
	// 형식)) enum 열거형이름 { 상수명1, 상수명2, 상수명3, .. , 상수명 n,}
	// ==>> 상수명을 string처럼 따옴표로 묶지 않는다.
	// ==>> 이 선언문 끝에 ';' 을 붙이지 않는다.
	// class안이든 밖이든 선언할 수 있다.
	public enum City {
		서울, 부산, 대구, 광주, 대전
	}

	// 서울부터 0,1,2,3,4 --> ordinal값

	// 2)) 열거형 상수에 사용자가 임의의 값을 설정할수 있다.
	// 사용자가 설정할 임의의 값은 상수명 옆에 괄호속에 지정한다.
	public enum Season {
		봄("3월부터 5월까지"), 여름("6월부터 8월까지"), 가을("9월부터 11월까지"), 겨울("12월부터 2월까지");
		// 임의의 값이 설정된 상수를 선언할때는 마지막에 세미콜론을 	 붙여준다.

		// 임의의 값이 저장될 변수를 선언
		private String span;

		// 열거형의 생성자를 만든다. ==> 지정한 임의의 값을 변수에 셋팅하는 역할을 수행한다.
		// 열거형의 생성자는 private 이어야 한다. (생략하면 기본적으로 private가 된다.)
		Season(String str) {
			// private Season(String str){
			span = str;
		}

		// 외부에서 임의의 값을 가져갈 수 있는 메소드 만들기 (일종의 getter메소드 만들기 )
		public String getSpan() {
			return span;
		}

	}

	// 자바에선 상수를 선언시 final을 붙인다.

	public static void main(String[] args) {

		/*
		 * 열거형에서 사용하는 메소드들 
		 * 
		 * - name() 메소드 ==> 열거형 상수의 이름을 문자열로 반환 
		 * - ordinal() 메소드 ==> 열거형 상수가 정의된 순서값을 반환(0부터시작) 
		 * - valueOf("상수명") ==> 지정된 열거형에서 '상수명'과 일치하는 열거형 상수를 반환
		 * - values() ==> 열거형 상수들을 배열에 담아서 가져온다.
		 */

		// 클래스에 대한 인스턴스를 만들어야한다.
		// city형 열겨형 상수들 중에서 '서울'값을 가져온다.
		City city1 = City.valueOf("광주");

		System.out.println("city1 >> " + city1.name());
		System.out.println("city1's ordinal >> " + city1.ordinal());
		System.out.println();

		// city형 열거형 상수들 중에서 대구값을 가져온다.
		City city2 = City.대구;
		// City.valueOf("대구");
		System.out.println("city2 >> " + city2.name());
		System.out.println("city2's ordinal >> " + city2.ordinal());

		// Season형 열거형 상수들 중에서 '봄'값 가져오기
		Season ss = Season.valueOf("봄");

		System.out.println("ss >> " + ss.name());
		System.out.println("ss의 ordinal >> " + ss.ordinal());
		System.out.println("ss의 span >> " + ss.getSpan());

		System.out.println("===============================");
		// 상수명과 임의로 설정한 값 가져오기
		for (Season time : Season.values()) {
			// System.out.println(time + " : " + time.getSpan());
			System.out.println(time.name() + " : " + time.getSpan());
		}
		System.out.println();

		System.out.println("===============================");
		for (City city : City.values()) {
			System.out.println(city.name() + " -> " + city.ordinal());
		}

		/*
		 * if(MyConst.one == MyConst2.Red) { System.out.println("합격...?????"); }
		 * else { System.out.println("불합뎓"); }
		 */

	}
}
