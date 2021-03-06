package basic;

/*
 * 람다식  ==> 익명함수를 생성하기 위한 식 
 * 	    ==> 자바에서는 '매개변수를 가진 코드 블록'으로 실행될때 '익명구현객체'로 생성된다.
 * 		==> 람다식으로 변환할 수 있는 인터페이스는 구현해야할 메소드가 1개인 인터페이스만 가능하다.
 * 			(이런 인터페이스를 '함수적 인터페이스' 라고 한다.)
 * 
 *   형식) 인터페이스명 변수명 = 람다식; 
 *   
 *   람다식 형식 ) (자료형이름 변수명, ...) -> { 실행문1; 실행문2; ... }
 *   
 *   람다식 작성 규칙 )
 *   1. '자료형 이름' 은 생략가능하다. 
 *   	예) (int a) -> {System.out.println( a );} // 매개변수가 하나이고 처리할 실행문도 하나인 것 
 *   		>> (a) -> {System.out.println( a );} // 자료형 이름은 생략하여 기술할수 있다 
 *  
 *   2. 매개변수가 1개일 경우에는 소괄호'( )'를 생략할 수 있다.
 *   	예) a -> {System.out.println( a );} 
 *   
 *   3. 실행문이 1개이면 중괄호'{ }' 를 생략할 수 있다. 
 *   	예) a -> System.out.println( a ); 
 *   
 *   4. 매개변수가 1개가 아닐 경우에는 괄호'( )'를 생략할 수 없다. 
 *   	예) 매개변수가 없을 경우 빈괄호라도 기재해줘야 한다. : ( ) -> System.out.println( "안냥" );
 *   
 *   5. 반환값이 있을 경우에는 return명령을 사용한다. 
 *   	예) (a, b) -> { return a + b; } 
 *   
 *   6. 실행문에 return문만 있는 경우 return과 중괄호 '{ }'를 생략할 수 있다. 
 * 		예) 중괄호만 생략한 경우  : (a, b) ->  return a + b;
 * 		예) 중괄호와 return 을 생략한 경우 : (a, b) ->  a + b;   
 */

/*
 * [ 0312 복습 ]
 * java 람다식  => JDK8이전의 자바에는 메소드라는 함수 형태가 존재하지만 객체를 통해서만 접근이 가능하고, 메소드 그 자체를 변수로 사용하지는 못했는데
 * 			 	JDK8에서는 함수를 변수처럼 사용할 수 있기 때문에, 파라미터로 다른 메소드의 인자로 전달할수 있고, 리턴값으로 함수를 받을수도 있다. 
 * 
 *  < 장점 > 
 *  	1. 코드의 라인수가 줄어든다. 
 *  	2. 병렬 프로그래밍이 가능하다.
 *  	3. 메소드로 행동방식을 전달할 수 있다. 
 *  	4. 의도의 명확성 : 가독성이 좋아진다. 
 *  
 *  < 단점 > 
 *  	1. 람다식의 호출을 위해 직접 메소드를 불러야 한다. 
 *  		( 람다식을 실행 할때는 인터페이스에 선언된 메소드를 호출해야한다. )
 *  	2. 재귀 람다식의 호출이 까다롭다. 
 *  		( 람다식 안에서 자신을 다시 호출하기가 용이하지 않다. 람다식안에서는 람다식을 가리키는 변수를 참조할수없다. *배열의 트릭을 사용하면 가능하기는 한다.)
 *  	3. 클로저가 지원되지 않는다. 
 *  		( 일반적인 함수형 프로그램에서는 람다식안에서 가리키는 외부 변수에 대해 클로져형태로서 외부변수의 라이프사이클을 연장할수있지만, 자바에서는 외부변수에 대해 사실상 
 *  			final 형태로서만 참조할 수 있다. )
 *  	4. 함수외부의 값을 변경한다. 
 *  		
 */

public class LambdaTest1 {

	public static void main(String[] args) {
		// 스레드의 Runnable인터페이스는 '함수적 인터페이스'로 람다식으로 변환해서 사용할 수 있다.

		// 람다식을 사용하지 않을 경우
		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i =0; i<=10; i++) {
					System.out.println("일반적인 방법 >> " +i);
				 }
			}
			
		}
			
		);
		
		th1.start();
		
		//----------------------
		
		// 람다식을 사용하는 경우 
		Thread th2 = new Thread(
			() -> {
				for(int i =1; i<=10;i++)
				{
					System.out.println("람다식을 사용한 방법 >> " + i);
				}
			}
		);
		th2.start();

	}

}
