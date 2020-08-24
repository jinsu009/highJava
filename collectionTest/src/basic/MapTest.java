package basic;

//20.02.07 - 01

import java.util.*;

public class MapTest {
	
	/*
	 * Map => key값과 value값을 한 쌍으로 관리하는 객체
	 * 	   => key값은 중복을 허용하지 않고, 순서가 없다. (Set의 특징을 갖는다.)
	 * 	   => value값은 중복을 허용한다.
	 * 
	 */
	
	public static void main(String[] args) {
			
		HashMap<String, String> map = new HashMap<>();
		
		//자료추가 => put(key값, value값);
		map.put("name", "장수하늘소");
		map.put("tel", "010-3625-2525");
		map.put("add", "대전");
		
		System.out.println("map >> " + map);
		
		System.out.println("map의 데이터의 갯수 >> " + map.size());

		
		//자료수정 => 데이터를 저장할 때 key값이 중복되면 나중에 입력한 값이 저장된다. 
		//			즉, key값은 동일하게 하고 value값을 다르게 지정하면 된다. 
		map.put("add","서울시");
		
		System.out.println("map >> " + map);

		//자료삭제 => remove(key값); > 해당 key값을 갖는 데이터를 삭제한다. 
		//							반환된 삭제된 데이터의 value값이 반환된다
		
			String temp = map.remove("tel");
			
			System.out.println(temp);
			System.out.println("map >> " + map);
			
		//자료읽기 => get(key값); > 해당 key값을 갖는 데이터의 value값을 반환한다.
			System.out.println("name >> " + map.get("name"));
			System.out.println();
			
		//-------------------------
			
			System.out.println("-------------------");
			
			//key값을 읽어와서 출력하는 방법
			
			//방법1) keyset()메소드 이용하기
			//		--> map의 key값만 읽어와서 set형으로 반환한다.
			
			Set<String> keySet = map.keySet();
			//keyset에 저장된 값을 하나씩 불러서 출력한다. 
			
			System.out.println("iterator이용");
			Iterator<String> keyIt = keySet.iterator();
			
			while(keyIt.hasNext())
				//keyIt 안에서 다음 데이터가 있을 때까지 실행
			{
				String key = keyIt.next();
				//키값 가져오기
				System.out.println(key + "==>" +map.get(key));
			}
			
			System.out.println("-------------------");
			
			
			System.out.println("향상된 for문이용");

			for(String key : keySet)
			{
				System.out.println(key + " --> " + map.get(key));
			}
			System.out.println("-------------------");
			
			
			// 방법2) value값만 읽어와 처리하기
			//		 value()메소드 이용하기
			
			for(String val : map.values())
			{
				System.out.println(val);
			}
			System.out.println("-------------------");

			
			// 방법3) map에는 entry라는 내부 class가 만들어져 있다.
			//		 이 entry클래스는 key와 value라는 멤버변수로 구성되어 있다. 
			//		 map에서는 이 entry클래스들을 set형식으로 저장하여 관리한다.
			
			//entry객체 전체를 가져오기
			// > entryset()메서드를 이용한다. 
			// > 가져온 entry들은 set형식으로 되어있다.
			
//			Set<Map.>
			
			Set<Map.Entry<String, String>> mapSet = map.entrySet();
			
			//iterator나 향상된 for문을 이요해서 entry객체를 처리한다.
			Iterator<Map.Entry<String, String>> entryIt = mapSet.iterator();
			
			while(entryIt.hasNext())
			{
				Map.Entry<String, String> entry = entryIt.next();
				
				System.out.println("key값 >> " + entry.getKey());
				System.out.println("value값 >>  " + entry.getValue());
				System.out.println();
			}
			System.out.println("-------------------");

			//key값의 존재여부를 나타내는 메소드
			// > containsKey(key값) 
			// > 해당 key값이 있으면 true 없으면 false반환
			
			System.out.println("name 키 값 존재여부 >> "+map.containsKey("name"));
			System.out.println("age 키 값 존재여부 >> "+map.containsKey("age"));

	}

}