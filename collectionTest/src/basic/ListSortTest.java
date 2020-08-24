package basic;

//20.02.05

import java.util.*;

public class ListSortTest {
	
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		
		list.add("불독");
		list.add("아메리칸 숏 헤어");
		list.add("강아지");
		list.add("악어거북");
		list.add("고양이");
		
		System.out.println("정렬전 list >> "  +list);
		
		//정렬 Collection.sort();
		//정렬은 기본적으로 오름차순 정렬을 수행한다.
		Collections.sort(list);
		System.out.println("(오름차순) 정렬된 list >> " + list);
		
		Collections.shuffle(list);
		System.out.println("자료를 섞은 list >> " + list);
		
		Collections.sort(list, new Desc() );
		//(정렬할 리스트, 정렬방식이정의되어있는 객체)
		System.out.println("(내림차순) 정렬된 list >> " + list);

	}

}


// 정렬하는 방식을 지정하는 클래스 
// comparator 인터페이스를 구현해서 정렬방식을 지정 
// comparator인터페이스의 compare()메서드를 재정의해서 정렬방식을 구현

class Desc implements Comparator<String>
{
	
	/*
	 * compare() 메소드의 반환값이 양수이면 두 값의 순서를 바꾼다.
	 * 오름차순일경우 > 앞의 값이 크면 양수, 같으면 0, 앞의 값이 작으면 음수를 반환한다. 
	 * 
	 * string객체에는 정렬을 위해서 compareTo() 메소드가 구현되어 있는데 이 메서드의 반환값은 오름차순에 맞게 반환되도록 구현되어 있따. 
	 * (wrapper class date, file class 에도 구현되어 잇따.)
	 */

	
	//내림차순 정렬이 되도록  재정의
	@Override
	public int compare(String s1, String s2) {
		
		return s1.compareTo(s2) * -1;
		
		/*if(s1.compareTo(s2)>0)
		{
			return -1;
		}
		else if(s1.compareTo(s2) ==0)
		{
			return 0;
		}
		else{
			return 1;
		}*/
		
	}
}