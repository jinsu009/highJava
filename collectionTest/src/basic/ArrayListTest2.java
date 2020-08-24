package basic;
//20.02.04
import java.util.*;

public class ArrayListTest2 {

	public static void main(String[] args) {
		/*
		 * q1. 5명의 사람이름을 scanner로 입력받아 arraylist에 저장하고 이 사람들 중에 '김'씨성의 이름을 출력하는
		 * 프로그램 작성
		 */

		Scanner s = new Scanner(System.in);

		ArrayList<String> name = new ArrayList();
		// 문자열 사용시 제네릭 타입에 string을 해줘야한다.

		String nm;

		for (int i = 0; i < 5; i++) {
			System.out.println("이름을 입력하세요 ");
			nm = s.nextLine();
			name.add(nm);
		}

		System.out.println("name >> " + name);

		int count = 0;

		for (int i = 0; i < name.size(); i++) {
			// if (name.get(i).substring(0, 1).equals("김"))
//			{
//				System.out.println("'김'씨성을 가진 사람 > " + name.get(i));
//				count++;
//			}
//
//			if (name.get(i).charAt(0) == '김') {
//				System.out.println("'김'씨성을 가진 사람 > " + name.get(i));
//				count++;
//			}
//			 if(name.get(i).indexOf("김")==0)
//			 {
//			 System.out.println("'김'씨성을 가진 사람 > " + name.get(i));
//			 count++;
//			 }

			if (name.get(i).startsWith("김") /*== true*//*true는 생략해도 된다.*/) {
				System.out.println("'김'씨성을 가진 사람 > " + name.get(i));
				count++;
			}

		}
		System.out.println("총 인원 : " + count);

	}

}
