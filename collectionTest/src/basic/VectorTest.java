package basic;
//20.02.04
import java.util.*;

public class VectorTest {
	
	public static void main(String[] args) {
		
		Vector v1 = new Vector();
		// collectrion 객채를 먼저 생성
		
		//add : 자료 추가  
		
		System.out.println("처음 크기 : " + v1.size());
		//데이터가 없는 상태의 크기 = 0
		
		// 타입은 object로 저장된다. 
		v1.add("aaa");
		//string type
		v1.add(111);
		//int type
		v1.add(new Integer(123));
		//autoboxing 기능이 없을땐 형변환 상태로 저장해야한다. 
		v1.add('a');
		v1.add(3.14);
		v1.add(true);
		
		//기본 자료형을 객체화 시켜준다. wrapperclass 
		//autoboxing ..???
		
		System.out.println("v1의 크기 : " + v1.size());
		
		//addElement() method : 데이터 추가시 사용할수 있지만 기본기능은 add와 같다.
		// 이전프로그램과의 호환서을위해서 존재한다.
		
		v1.addElement("kkk");
		
//		System.out.println("v1 >> " + v1.toString());
		System.out.println("v1 >> " + v1);
		
		/*
		 * add(index, data) >> index를 기술하지 않으면 맨마지막 index에 추가 되지만 
		 * index를 기술할경우 원하는 위치에 자료를 추가할수 있다.
		 * index는 0부터 시작한다. !!
		 */
		
		v1.add(1,900);
		System.out.println("v1 >> " + v1);
		
		//set : 자료 수정
		//set(index, data) > 벡터의 지정 index 의 자료를 data로 덮어쓴다. (반환값(return) : 원래의 데이터가 반환된다.)
		
		v1.set(1, "zzzz");		
		System.out.println("v1 >> " + v1);
		
//		String temp = v1.set(1, "aaaa");
		/*
		 * vector의 데이터 타입... 때문에 오류.
		 * 형변환을 해준다. 
		 */
		String temp = (String)v1.set(1, "ppppp");
		System.out.println("v1 >> " + v1);
		//자료를 꺼내올때는 형변환을 염두해둔다. 
		
		//remove : 자료삭제 
		/*
		 * remove(index) > vector의 index번째 데이터를 삭제 한다. (반환값(return) : 삭제된 데이터 )
		 * remove(data) > 삭제할 데이터의 값을 직접 입력 , 삭제할 데이터가 여러개일 경우에는 맨 앞쪽의 데이터를 삭제한다.
		 * 
		 * 삭제할 데이터가 정수형이거나 char형일 경우 해당데이터를 객체형으로 변환해서 사용해야한다. 
		 */
		temp = (String)v1.remove(1);
		System.out.println("삭제된 데이터 >> " + temp);
		System.out.println("삭제 후 v1 >> " + v1);
		
		v1.remove("kkk");
		System.out.println("삭제 후 v1 >> " + v1);
		
		v1.remove(new Integer(123));
		//wrapper...
		System.out.println("삭제 후 v1 >> " + v1);
		
		v1.add("aaa");
		System.out.println("추가후 v1 >> " + v1);
		v1.remove("aaa");
		System.out.println("삭제 후 v1 >> " + v1);
		//앞쪽의 aaa만 삭제되었다. 
		
		v1.remove(new Character('a'));
		System.out.println("삭제 후 v1 >> " + v1);
		
		//get : 데이터 반환
		/*
		 * get(index) > 벡터의 해당  index번째에 있는 데이터를 반환 
		 */
		
		int data = (int)v1.get(0);
		System.out.println("0번째 데이터 >> " + data);
		
		v1.clear();
		System.out.println(v1);
			
		
		//-----------------------------
		/*
		 * 제네릭 타입 (Generic Type) : 객체를 선언할때 < >안에 그 collection이 사용할 데이터 타입을 지정해주는 것이다. 
		 * 이런식으로 선언 되었을경우 그 데이터 타입 이외의 다른 데이터를 저장할 수 없게 된다. 
		 * 단, 제네릭으로 선언될 수 있는 데이터 타입은 클래스형 이어야 한다. 
		 * int -> Integer, boolean -> Boolean, char -> Character등으로 대체하여 사용 
		 */
		
		Vector<Integer> v2 = new Vector<Integer>();
		//int만 저장할 수 있는 벡터 
		Vector<String> v3 = new Vector<>();
		//string만 저장할 수 있는 벡터 
		
		v3.add("안녕하세요 ");
//		v3.add(123); //error 
		
		String temp2 = v3.get(0);
		System.out.println(temp2);
		
		Vector<Vector> vv = new Vector<>();
		Vector<Vector<Vector>> vvv = new Vector<>();
		
		//--------------------
		
		//clear() > 벡터의 전체 데이터를 모두 삭제 (크기를 0으로 만들어버린다.)
		
		v3.clear();
		System.out.println("v3의 size : " + v3.size());
		
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		
		Vector<String> v4 = new Vector<>();
		v4.add("BBB");
		v4.add("EEE");
		
		System.out.println("v3 >> " + v3);
		System.out.println("v4 >> " + v4);
		
		//removeAll(collection 객체) > 'collection객체'가 가지고 있는 데이터를 모두 삭제 
		
		v3.removeAll(v4);
		// v3안의 데이터중 v4와 똑같은 데이터는 모두 지워라 
		System.out.println("v3 >> " + v3);
		
		System.out.println("---------------------------------");		
		v3.clear();
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		
		//벡터의 데이터를 순서대로 모두 가져와 사용하고 싶으면 반복문을 사용하면된다. (주로 for문 사용)

		for(int i = 0; i<v3.size();i++)
		{
			System.out.println(i+ " 번 째의 자료 : "+v3.get(i));
		}
		
		System.out.println("---------------------------------");
		//향상된 for문
		//str을 v3만큼 반복한다....???
		for(String str : v3)
		{
			System.out.println(str);
			
		}
	}

}
