package basic;


// 인터페이스가 함수적 인터페이스라는 것을 명시적으로 표시해주는 어노테이션은
// @FunctionalInterface이다. 

// 1. 인터페이스를 생성하고 추상메소드를 선언
// 인터페이스의 메소드가 하나밖에 없는 상황에서 다른 메소드를 생성하였을때 후에 람다식으로 구현 했을때문제가 발생할 수 있다. 때문에 메소드는 하나만 만들어야하며 
// @FuntionalInterface : 을 붙여주면 함수적 인터페이스라는 것을 알려주는 것이고 하나이사의 메소드가 생성되는 것을 막아준다. 

@FunctionalInterface
public interface LambdaTestInterface {
	// 매개변수가 없고 , 반환값도 없는 메소드 
	public void test();

}

//java 파일하나에 보통 interface를 하나만 만들지만 여러개 만들수도 있다. 
//대신 인터페이스 생성시 public은 자바 파일과 같은 인터페이스에만 붙일 수 있다. 
@FunctionalInterface
interface LambdaTestInterface2{
	//반환값이 없고, 매개변수가 1개인 메소드
	public void test2(int a);
}

@FunctionalInterface
interface LambdaTestInterface3{
	//반환값이 있고 매개변수도 있는 메소드 
	public int test3(int a, int b);
}
















