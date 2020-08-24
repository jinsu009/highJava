package basic;

public class LambdaTest2 {
	
	public static void main(String[] args) {
		//----------람다식을 사용하지 않은 경우 
		LambdaTestInterface t1 = new LambdaTestInterface() {
			//익명구현체로 생성 
			@Override
			public void test() {
				// TODO Auto-generated method stub
				System.out.println("람다식을 사용하지 않은 경우 입니다. ");
			}
		};
		
		t1.test();
		
		//----------람다식을 사용할경우 
		
		LambdaTestInterface t2 =
				//매개변수가 없는 경우, 굳이 한줄에 다 적어주지 않아도 된다. 
				() -> { System.out.println("람다식을 사용한 경우 입니다. 1"); };
				
		t2.test();
		
		
		LambdaTestInterface t3 =
				//매개변수가 없는 경우, 굳이 한줄에 다 적어주지 않아도 된다. 
				//실행문이 하나일경우 중괄호를 생략해도 된다. 
				() -> System.out.println("람다식을 사용한 경우 입니다. 2"); 
				
		t3.test();
		
		System.out.println("---------------------------------------");
		
		//반환값이 없고 매개변수로 int를 받는 경우 
		//매개변수로 받는 값의 type과 파라미터로 받는 값의 타입은 같아야 하지만 변수명은 달라도 상관이 없다. 
		LambdaTestInterface2 t4 = (int a) -> { 
			int result = a+40;
			System.out.println(a + " + 40 >> " + result);
		};
		
		t4.test2(30);
		
		//자료형 이름과 괄호를 생략했을 경우 
		LambdaTestInterface2 t5 = k -> {
			int result = k * 20;
			System.out.println(k + " * 20 = " + result);
		};
		
		t5.test2(30);
		
		
		//실행문이 하나만 존재해서 중괄호를 생략했을 경우
		LambdaTestInterface2 t6 = k -> 	System.out.println(k + " - 10 = " + (k-10));
		t6.test2(30);
		
		
		LambdaTestInterface3 t7 = (int z, int y) -> {
			int result = z+y;
			return result;
		};
		int k = t7.test3(20, 50);
		System.out.println("k = " + k);
		
		//타입명을 생략한 경우 
		LambdaTestInterface3 t8 = (z, y) -> {
			int result = z-y;
			return result;
		};
		int j = t8.test3(40, 50);
		System.out.println("j = " + j);
		
		//return에 계산식을 바로 기술해줬을 경우  
		// return만 있을 때는 중괄호를 생략하면 안된다. 
		LambdaTestInterface3 t9 = (z, y) -> { return z*y; };
		int i = t9.test3(40, 50);
		System.out.println("i = " + i);
		
		//실행문이 하나만 있을경우
		LambdaTestInterface3 t10 = (z, y) -> z/y;
		int q = t10.test3(20, 4);
		System.out.println("q = " + q);
		
		
	}

}













