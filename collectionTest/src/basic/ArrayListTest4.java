package basic;
//20.02.04
import java.util.*;

public class ArrayListTest4 {
	/*
	 * q2. 5명의 별명을 scanner로 입력하여 arraylist에 저장하고 이들중 별명의 길이가 제일 긴 별명을 출려하시오
	 * 단, 각 별명의 길이는 모두 달라야한다.
	 * 
	 * q3. q2에서 별명을 입력할때 별명의 길이가 같은것이 허용될 경우를 처리하시오
	 */
/*public static void main(String[] args) {
	

	Scanner s = new Scanner(System.in);

	ArrayList<String> nickname = new ArrayList<>();

	String name = "";

	for (int z = 0; z < 5; z++) {
		
			System.out.println("별명을 입력하세요 ");
			name = s.nextLine();
			
		nickname.add(name); 
			
	}

	System.out.println(nickname);
	System.out.println("------------");

	String temp = "";
	for (int i = 0; i < nickname.size(); i++) {
		for (int j = i+1; j < nickname.size(); j++) {
			if(nickname.get(i).length() < nickname.get(j).length()){
				temp = nickname.get(i);
				nickname.set(i, nickname.get(j));
				nickname.set(j, temp);
			}

		}
	}
	System.out.println("가장 긴 별명 : " + nickname.get(0));

	for(int i = 1 ; i<nickname.size(); i++){
		if(nickname.get(0).length() == nickname.get(i).length())
			System.out.println("중복된 별명 : "+nickname.get(i));
	}
}*/
	
	public static void main(String[] args) {
		
//		q3. 5명의 별명을 입력하여 arraylist에 저장하고 가장 긴 별명을 출력하시오 
//		단, 길이가 같은사람이 2명이상일경우 전부 출력하시오
		

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
		
		int maxle = allist.get(0).length();
		//제일 긴 별명의 길이가 저장될 변수 (첫번째 별명의 길이로 초기화)

		for(int i = 1; i<allist.size();i++)
		{
			if(maxle < allist.get(i).length())
			{
				maxle = allist.get(i).length();
			}
		}
		
		System.out.println("제일 긴 별명들 >> ");
		
		for(int i = 0; i<allist.size();i++)
		{
			if(maxle == allist.get(i).length())
			{
				System.out.println(allist.get(i));
			}
		}
		
		
		
		
	}


}
