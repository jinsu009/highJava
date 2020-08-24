package basic;
//20.02.04
import java.util.*;

public class ArrayListTest3 {
	/*public static void main(String[] args) {

		
		 * q2. 5명의 별명을 scanner로 입력하여 arraylist에 저장하고 이들중 별명의 길이가 제일 긴 별명을 출려하시오
		 * 단, 각 별명의 길이는 모두 달라야한다.
		 * 
		 * q3. q2에서 별명을 입력할때 별명의 길이가 같은것이 허용될 경우를 처리하시오
		 

		Scanner s = new Scanner(System.in);

		ArrayList<String> nickname = new ArrayList<>();

		String name = "";

		for (int i = 0; i < 5; i++) {
			while (true) {
				System.out.println("별명을 입력하세요 ");
				name = s.nextLine();
				boolean x = true;
				
				for (int j = 0; j < nickname.size(); j++) {
					
//					System.out.println("확인용");
					
					if (name.length() == nickname.get(j).length()) 
					{
						System.out.println("별명의 길이가 모두 달라야 합니다.");
						x = false;
						break;
					}

				}
				if (x) {
					nickname.add(name);
					break;
				}
			}
		}

		System.out.println(nickname);
		System.out.println("------------");

		String temp = "";
		String max = "";
		String min = "";
		for (int i = 0; i < nickname.size(); i++) {
			for (int j = 1; j < nickname.size() - 1; j++) {

				max = nickname.get(i);
				min = nickname.get(j);

				if (max.length() < min.length()) {
					temp = min;
					min = max;
					max = temp;
				}

			}
		}
		System.out.println("가장 긴 별명 : " + max);

	}*/
	
	public static void main(String[] args) {
		 /* q2. 5명의 별명을 scanner로 입력하여 arraylist에 저장하고 이들중 별명의 길이가 제일 긴 별명을 출려하시오
		 * 단, 각 별명의 길이는 모두 달라야한다.
		 * 
		 * q3. q2에서 별명을 입력할때 별명의 길이가 같은것이 허용될 경우를 처리하시오
		 */
		
		ArrayList<String> allist = new ArrayList<>();
		
		Scanner s = new Scanner(System.in);

		String nm="";
		
		System.out.println("서로다른 길이의 별명 5개를 입력하시오");
		for(int i = 0; i<5;i++)
		{
			System.out.println(i +" 번째 별명 > " );
			nm = s.nextLine();
			allist.add(nm);			
		}
		System.out.println("입력완료");
		
		String maxal = allist.get(0);
		
		for(int i = 1; i<allist.size();i++)
		{
			if(maxal.length() < allist.get(i).length())
			{
				maxal = allist.get(i);
			}
		}
		
		System.out.println("제일 긴 별명 >> " + maxal);
		
	}

}






















