package basic;

//20.02.06

import java.util.*;

public class SetTest2 {
	public static void main(String[] args)
	{
		//treeset > 데이터들을 자동으로 정렬해서 저장한다. 
		TreeSet<String> ts = new TreeSet<>();
		
		for(char ch = 'Z'; ch>='A'; ch--)
		{
			//문자열로 변환
			String temp = String.valueOf(ch);
			ts.add(temp);
		}
		
		System.out.println("Treeset > " + ts);
		
		//treeset에 저장된 자료 중 특정한 범위의 자료를 찾아서 반환해 주는 메소드들이 있다.
		// 반환되는 자료는 sortedset형으로 반환된다..
		//headset(기준값) > '기준값'보다 작은 자료들을 반환한다.(기준값은 포함되지않는다.)
		//headset(기준값,포함여부) > 포함여부가 true라면 기준값까지 포함하여 반환해준다. 
		SortedSet<String> ss1 = ts.headSet("K");
		System.out.println("K 이전의 자료  > " +ss1);
		
		System.out.println("f를 포함한 이전의 자료 > " +ts.headSet("F", true));
		System.out.println();
		
		
		// tailset(기준값) > 기준값보다 큰 자료들을 반환한다.('기준값'이 포함된다.)
		// tailset(기준값, 포함여부) > 포함여부가 false이면 '기준값'이 포함되지않는다.
		SortedSet<String> ss2 = ts.tailSet("K");
		System.out.println("K이후의 자료 " + ss2);
		System.out.println("k이후의 자료 " + ts.tailSet("K", false));
		System.out.println();
		
		//subset(시작값, 종료값) > 시작값부터 종료값 이전까지의 자료를 반환한다.(시작값 포함, 종료값 미포함)
		System.out.println("K 부터 N 이전 까지의 자료  > " + ts.subSet("K", "N"));
		
		System.out.println("K 부터 N 이전 까지의 자료  > " + ts.subSet("K",true, "N",true));
		System.out.println("K 부터 N 이전 까지의 자료  > " + ts.subSet("K",false, "N",false));
		System.out.println("K 부터 N 이전 까지의 자료  > " + ts.subSet("K",false, "N",true));
		
		
	}

}
