package basic;

//20.02.11-02

/*
 * 제네릭 클래스 만들기  (ver1.5부터 지원)
 * 자료를 임의적으로 나중에 만들어주고 싶을때 사용오오오옹????
 * 
 * 형식) class 클래스명<제네릭타입글자>{
 * 					
 * 				제네릭타입글자 변수명; //변수선언에 사용할 경우 
 * 					...
 * 
 * 				제네릭타입글자 메서드명(매개변수들....){ //메서드의 반환 값으로 사용할 경우
 * 							....
 * 							return 값;
 * 						}
 * 
 * 				반환값자료형 메서드명(제네릭타입글자 변수명,...){ 	//메서드의 매개변수로 사용할 경우
 * 						... 					
 * 						}
 * 				}
 * 
 */

class MyGenericClass<T> {
	private T val; // 변수선언에 사용

	public void setVal(T val) // 메소드의 매개변수에 사용
	{
		this.val = val;
	}

	public T getVal() // 메소드의 반환값으로 사용
	{
		return val;
	}
}

class GenericClass {

	private Object val;

	// object로 선언되었으면 어떤 타입의 객체든 저장이 가느으으으응

	public void setVal(Object val) {
		this.val = val;
	}

	public Object getVal() {
		return val;
	}

}

class NonGenericClass {

	private Object val;

	// object로 선언되었으면 어떤 타입의 객체든 저장이 가느으으으응

	public void setVal(Object val) {
		this.val = val;
	}

	public Object getVal() {
		return val;
	}

}

public class GenericTest {

	public static void main(String[] args) {

		NonGenericClass nc1 = new NonGenericClass();
		NonGenericClass nc2 = new NonGenericClass();

		nc1.setVal("가나다라라라라라");
		nc2.setVal(999);

		// 데이터 입력은 문제가 없지만 꺼낼때 문제다...
		// String temp = nc1.getVal();
		// 데이터는 문제없이 잘 입력이 되었지만 어째서 error가 발생하는 걸까 ... ㅇㅅㅇ
		// 우리가 아무 종류의 데이터나 저장할수 있도록 object로 선언했지만
		// java는 자식데이터를 부모데이터에 저장할때 자동으로 형변환이 된것이다.
		// 반대로 출력할때는 object 가 조상이고 string가 자손인데 내가 직접 형변환을 해줘야한다.
		String temp = (String) nc1.getVal();
		System.out.println("nc1 temp >> " + temp);

		int intTemp = (int) nc2.getVal();
		System.out.println("nc2 int >> " + intTemp);

		// int test = (int)nc1.getVal();
		// System.out.println("nc1  test >> " + test);
		/*
		 * 문법상으로는 오류가 발생하지 않지만 string을 int형으로 형변환 하지 못했다 string을 int 로 형변환 하려니까
		 * 오류
		 */
		// ----------------------

		System.out.println("=============");

		MyGenericClass<String> mg1 = new MyGenericClass<>();
		MyGenericClass<Integer> mg2 = new MyGenericClass<>();

		mg1.setVal("흑동고래");
		mg2.setVal(999999999);

		String gTemp = mg1.getVal();
		System.out.println("gTemp >> " + gTemp);

		int intgTemp = mg2.getVal();
		System.out.println("intgTemp >> " + intgTemp);

	}
}
