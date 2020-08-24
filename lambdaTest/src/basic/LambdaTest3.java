package basic;

public class LambdaTest3 {
	
	public void method(int temp) {
		int localVar = 40; //지역변수 선언 
		
		//temp = 400; //오류 발생
		//localVar = 200; // 오류발생
		
		// 람다식에서 지역변수를 사용하는 연습
		
		//익명구현체와 같다고 생각 >> 람다식 내부에서 지역변수를 사용하려면 해당 지역변수는 final이어야 한다. 
		// 자바버전 1.8이상에서는 람다식 내부에서 사용되는 지역변수가 변경되지 않으면 final을 생략해도된다. 
		LambdaTestInterface  la = ()-> {
			System.out.println("temp = " + temp);
			System.out.println("localVar = " + localVar);
		}; 
		la.test();
		
	}
	
	public static void main(String[] args) {
		new LambdaTest3().method(50);
	}

}
