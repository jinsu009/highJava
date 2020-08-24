package singleton;
//20.02.27--01

/*
 * java design pattern : mvc, singleton ... 
 * 
 * java design 패턴들이 굉장히 많이 있고 상황에따라 필요한 것을 가져와 사용 할 수 있다. 
 * 따로 공부해보는 것을 추천! 자바에 대하여 더 깊게 알 수 있다.
 * 수업시간에 배우는건 확실히 알아두는 것이 좋다 .. 많은 곳에서 사용하기 때문에  
 * 
 * singleton 패턴 :  항상 한 개의 객체만 만들어지게 하는 방법 
 * 		==> 외부에서 new 명령을 사용하지 못하게 하고, 내부에서 항상 동일한 객체를 반환하도록 만들어진 클래스
 * 
 * singleton패턴을 생성할때는 밑의 세가지 경우는 반드시 거쳐줘야 한다. 
 */

// singleton 클래스를 구성하는 예제 
public class MySingleton {

	//1. 자기 class의 참조값을 갖는 멤버변수를 private static 형식으로 선언한다 .
	private static MySingleton single;
	
	//2. 생성자는 private으로 만든다.  
	// 		==> private로 생성하면 외부에서 new 명령을 사용할 수 없다. 
	private MySingleton(){
		System.out.println("생성자 입니다.");
	}
	
	//3. 자기자신 객체(instance)를 생성하여 반환하는 메소드 작성
	//		==> 이 메소드는 'public static 자기자신클래스명 메서드명()' 형식으로 만든다. 
	// 			이 메소드의 이름은 보통 getInstance()로 한다. 
	// 			그리고 이 메서드에서 자기 자신객체를 생성 할 때 객체가 존재하는지 여부를 검사해서 
	//			해당 객체가 존재하지 않으면 새롭게 만들고 
	
	public static MySingleton getInstance(){
		if(single==null) single = new MySingleton();
		return single;
	}
	
	public void displayTest(){
		System.out.println("이것은 싱글톤 객체의 메소드 입니다. ");
	}
	
}

















