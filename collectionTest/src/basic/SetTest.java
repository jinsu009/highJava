package basic;

//20.02.06

import java.util.*;

public class SetTest {
	public static void main(String[] args) {
		/*
		 * - list와 set의 차이점 1. list - 데이터의 순서(index)가 있다. - 중복된 데이터를 저장할 수 있다.
		 * 
		 * 2. set - 데이터의 순서가 없다. - 중복된 데이터를 저정할 수 없다.
		 */

		HashSet hs1 = new HashSet();

		// add(); : 데이터 추가
		hs1.add("DD");
		hs1.add("BB");
		hs1.add(22);
		hs1.add("AA");
		hs1.add("ZZ");
		hs1.add(1);
		hs1.add(4);
		// 제네릭을 사용하지 않으면 숫자든 문자든 상관 없이 다 저장 가능
		// 제네릭안에 타입을 적으면 그 타입만 저장 가능

		System.out.println("hs1 >> " + hs1);
		// 내가 입력한 순서에 상관없이 출력된다.
		System.out.println();

		// set은 데이터의 순서가 없고 중복을 허용하지 않는다....
		// 만약 현재 배열안에 저장된 데이터를 또 추가하게 될경우
		// add메소드는 false를 반환하고 데이터는 추가되지 않는다.

		boolean isAdd = hs1.add("강아지");
		// 반환값을 확인해보기 위해 작성

		System.out.println("중복되지 않을경우  >> " + isAdd);

		System.out.println("hs1 >> " + hs1);
		System.out.println();

		isAdd = hs1.add("ZZ");
		System.out.println("중복될 경우  >> " + isAdd);

		System.out.println("hs1 >> " + hs1);
		System.out.println();
		//set은 set();을 사용할 수 없다. 
		//원래의 데이터를 삭제한 후 추가 하여야 한다. 
		//clear() : 전체삭제 
		//remove(삭제데이터) : 삭제 성공시 true 실패시 false반환

		//예를 들엉 강아지를 AA를 변경해야 한다. 
		hs1.remove("AA");
		hs1.add("EE");
		System.out.println("hs1 >> " + hs1);
		System.out.println();
		
		/*hs1.clear();
		System.out.println("clear hs1 >> " + hs1);
		System.out.println();*/
		
		
		/*
		 * set에 저장된 데이터를 차례로 꺼내와서 처리하는 방법
		 * 1.iterator를 이용하는 방법
		 * 2.향상된 for문을 이용하는 방법
		 */
		
		//향상된 for문을 이용
		System.out.println("향상된 for문을 이용한 출력방법");
		for(Object obj : hs1)
		{
			System.out.print(obj+",");
		}
		System.out.println();
		
		
		
		System.out.println("Iterator로 출력하기 ");
		
		//iterator을 이용
		//iterator는 collection에서 저장되어 있는  요소들을 읽어오는 방법
		//iterator객체 구하기 > 각 collection객체에는 iterator()라는 메소드가 있는데 이메소드가 해당 컬렉션의 iterator객체를 생성해주는 메소드이다. 
		
		//set에서 iterator객체 구하기
		Iterator it = hs1.iterator();
		//set이 가지고 잇는 데이터를 가지고 오기 쉽게 한다. 
			
		//데이터 갯수만큼 반복처리 
		//hasnext()메소드 > 다음자료가 잇는지 검사한다. 있으면 true, 없으면 false 반환
		//next()메소드 > 포인터를 다음 자료 위치로 이동시킨후, 그위치의 데이터를 읽어와 반환한다. 
	
		while(it.hasNext())
		{
			System.out.print(it.next() +",");
		}
		System.out.println();
		
		
		System.out.println("set의 자료 갯수 : " + hs1.size());
		
		
		//set을 이용한 로또번호 만들기
		HashSet<Integer> lottoSet = new HashSet<>();
		
		while(lottoSet.size()<6)
		{
			//(최대값-최소값+1)+최소값
			int num =(int)(Math.random()*45)+1;
			lottoSet.add(num);
		}
		System.out.println("lotto > "+lottoSet);
		
		//set유형의 데이터를 list 유형으로 변환해서 사용할 수 있다. 
		ArrayList<Integer> lottoList = new ArrayList<>(lottoSet);
		System.out.println("List 로또 > "+lottoList);
		
	}

}
