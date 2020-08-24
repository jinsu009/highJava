package singleton;

//20.02.27--02
public class SingletonTest {

	public static void main(String[] args)
	{
//		MySingleton single1 = new MySingleton();	//생성자가 private이기 때문에 외부에서는 생성할 수 없다.
		
		// 싱글톤 객체를 생성하려면 해당 객체를 생성해서 반환하는 메소드를 호출해야 한다. 
		// 이 예제 에서는 getInstance()메소드를 호출한다.
		MySingleton single2 = MySingleton.getInstance(); 
		MySingleton single3 = MySingleton.getInstance(); 
		
		System.out.println("single2의 참조값(주소값) >> " + single2);
		System.out.println("single3의 참조값(주소값) >> " + single3);
		
		System.out.println("equals >> " + single2.equals(single3));
		System.out.println(" == >> " + (single2==single3));
		//생성자를 두번 호출한것 같지만 실제로는 한번만 생성이 된것이다. 
		
		single2.displayTest(); // 객체에서 만들어진 메소드 호출
		
	}
}
