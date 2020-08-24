package basic;

//20.02.06 과제 

import java.util.*;

/*
 * 숫자야구 게임 프로그램 작성하기 . 
 * 
 *  - set을 이용하여 숫자 야구 게임 프로그램을 작성한다.
 *   
 *  	컴퓨터의 숫자는 난수를 이용해서 구한다. 
 *  (스트라이크는 s, 볼은 b, 로출력한다. )
 * 
 * 세자리의 수는 중복되지 말아야한다. 
 * 
 * 
 * 예) 컴퓨터가 만든난수 > 9 5 7
 * 
 * 실행 예시 >> 
 * 숫자 입력 : 3 5 6
 * 
 * -----
 * 356과 957를 비교 >> 1s 0b
 * 
 * 숫자입력 : 789
 * ---
 * 789 와 957 비교 >> 0s 2b
 * 
 * 숫자입력 : 975
 * 975와 957비교 1s 2b 
 * ---
 * 숫자입력 957
 * 3s 
 * 4번만에 맞췄습니다. 
 * 
 * 
 */

public class BaseBallTest {

	public static void main(String[] args) {

		HashSet<Integer> baseball = new HashSet<>();

		while (baseball.size() < 3) {
			int num = (int) (Math.random() * 9) + 1;
			baseball.add(num);
		}

		// baseball hashset을 list에 담아서 비교
		ArrayList<Integer> baselist = new ArrayList<>(baseball);

		Collections.shuffle(baselist);
		System.out.println("컴퓨터가 생성한 수 : " + baselist);

		System.out.println("=================");
		Scanner s = new Scanner(System.in);

		int strike=0;
		int ball = 0;
		int count = 0;
		
		do{
			strike=0;
			ball=0;
		
		System.out.println("중복되지 않는 숫자 세개를 입력하세요 ");
		
		
		ArrayList<Integer> self = new ArrayList<>();
		
		
		self.add(s.nextInt());
		self.add(s.nextInt());
		self.add(s.nextInt());
		
		System.out.println("=================");
		
		if(baselist.get(0) == self.get(0)) strike++;
		if(baselist.get(1) == self.get(1)) strike++;
		if(baselist.get(2) == self.get(2)) strike++;

		for (int i = 0; i < baselist.size(); i++)
		{
			if(baselist.contains(self.get(i)))
			{
				if(baselist.get(i) == self.get(i)){
					continue;
				}
				ball++;
			}
			
		}	
		count++;
		System.out.println("값을 다시 입력하세요 " + " strike " + strike + " ball " + ball);
		
	}while(strike!=3);
		
		System.out.println("게임이 종료되었습니다. " + " strike " + strike + " ball " + ball);
		System.out.println("도전횟수 : " + count);
	
}
}
