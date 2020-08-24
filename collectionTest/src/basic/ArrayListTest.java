package basic;
//20.02.04

import java.util.*;

public class ArrayListTest {

	public static void main(String[] args) {

		// ArrayList는 기본적인 사용법이 vector과 같다.

		ArrayList list1 = new ArrayList();

		System.out.println("처음 크기 : " + list1.size());

		// add() method를 이용하여 추가 한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111);
		list1.add('k');
		list1.add(false);
		list1.add(12.345);

		System.out.println("list1 의 크기 : " + list1.size());
		System.out.println("list1 >> " + list1);

		// get() method로 데이터를 꺼내욘다.
		System.out.println("1번째 자료 > " + list1.get(1));

		// 데이터 끼워넣기도 똑가다
		list1.add(0, "zzzz");
		System.out.println("list1 >> " + list1);

		// set() 데이터 수정
		String temp = (String) list1.set(0, "yyyy");
		// 제네릭을 사용하지 않으면 형변환을 해줘야 함.
		System.out.println("temp > " + temp);
		System.out.println("list1 > " + list1);

		// remove
		list1.remove(0);
		System.out.println("삭제 후 list1 > " + list1);

		list1.remove("bbb");
		System.out.println("삭제 후 list1 > " + list1);

		System.out.println("-------------------------------------");

		// 제네릭을 지정하여 선언할 수 있다.
		ArrayList<String> list2 = new ArrayList<>();

		list2.add("AAAAA");
		list2.add("BBBBB");
		list2.add("CCCCC");
		list2.add("DDDDD");
		list2.add("EEEEE");

		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + " : " + list2.get(i));
		}

		System.out.println("-------------------------------------");

		// 향상된 for문
		for (String s : list2) {
			System.out.println(s);
		}

		System.out.println("-------------------------------------");

		// contains(비교객체); > 리스트에 '비교객체'가 있으면 true, 없으면 false 반환
		System.out.println("DDDDD 데이터의 존재여부 : " + list2.contains("DDDDD"));
		System.out.println("ZZZZZ 데이터의 존재여부 : " + list2.contains("ZZZZZ"));
		System.out.println("-------------------------------------");

		// indexOf(비교객체); > list에 '비교객체' 가 있으면 '비교객체'가 있는 index값을 반환
		// list에 '비교객체'가 있으면 '비교객체'가 있는 index값을 반환, 없으면 -1반환

		System.out.println("DDDDD 데이터의 존재여부 : " + list2.indexOf("DDDDD"));

		//toArray() > 리스트안의 데이터들을 배열로 변환하여 반환한다. 
		//기본적으로 object형 배열로 변환된다. 
		
		Object[] strArr = list2.toArray();
		System.out.println("배열의 갯수 : " + strArr.length);
		for(int i = 0 ; i<strArr.length;i++)
		{
			System.out.println(i+" 번째 : "+strArr[i]);
			//String tempdata = (String)strArr[i];
			//System.out.println(i+" 번째 : "+tempdata);
		}
		
		System.out.println("-------------------------------------");
		
		String[] strArr2 = list2.toArray(new String[0]);
		for(String str : strArr2)
		{
			System.out.println(str);
		}
		
		System.out.println("-------------------------------------");
		
		
		//왜 정렬이 안되냐ㅠㅠㅠ 
		//comparable 이 통일되지않아서 안됨...
		//타입이 하나로 규정되어야 한다. 
		//제네릭을 해줘야 한다. 
		Collections.sort(list1);
		for (int i = 0; i < list1.size(); i++) {
			System.out.println(list1.get(i));

		}
		
		
		
	}
}
