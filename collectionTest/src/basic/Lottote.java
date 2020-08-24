package basic;

//20.02.10

import java.util.*;

public class Lottote {
	
	private Scanner s;
	
	//생성자
	public Lottote(){
		s = new Scanner(System.in);
		
	}
	
	public static void main(String[] args) {
		
		new Lottote().storeStart();
		
	}
	
	public int displayMenu(){
		
		System.out.println();
		System.out.println("==========================");
		System.out.println("\tLotto 프로그램");
		System.out.println("--------------------------");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("==========================");
		System.out.print("메뉴선택 >> ");
		int num = s.nextInt();
		
		return num;
	}
	
	public void storeStart(){
		
		while(true){
			
			int choice = displayMenu();
			
			switch(choice){
			case 1:
				buylotto();
				break;
			case 2:
				System.out.println("감사합니다.");
				return;
			default :
				System.out.println("번호를 잘못 선택했습니다. 다시 선택하세요 ");
			}
		}
	}
	
	public void buylotto(){
		System.out.println();
		System.out.println("로또 구매 시작");
		
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액입력 >> ");
		int money = s.nextInt();
		
		if(money<1000){
			System.out.println("금액이 부족합니다. 로또를 구매하실 수 없습니다. ");
			return;
		}
		if(money>=101000){
			System.out.println("입력금액이 너무 많습니다. 로또를 구매하실 수 없습니다.");
			return;
		}
		
		//금액에 맞는 로또를 생성해주는 메소드 호출
		getlottonum(money);
		
		//거스름돈 계산 및 출력
		System.out.println("받은 금액 > " + money + " 원이고 , 거스름돈은  " + (money%1000) + " 원 입니다.");
		
		
		
		
	}

	
	//금액에 맞는 로또번호를 생성하는 메소드 
	public void getlottonum(int money){
		//돈을 매개변수로 받는다.
		
		//구입할 로또번호 매수구하기 
		int cnt = money/1000;
		
		HashSet<Integer> lottoSet = new HashSet<>();
		
		System.out.println("행운의 번호는 아래와 같습니다.");
		
		for(int i = 1; i<=cnt; i++)
		{
			//구입 매수 만큼 반복
			while(lottoSet.size()<6){
				//1개의 로또번호 만들기 
				lottoSet.add((int)(Math.random()*45+1));
				
			}
			
			//로또번호가 들어있는 set객체를 이용해서 list객체생성
			ArrayList<Integer> lottolist = new ArrayList<>(lottoSet);
			
			Collections.sort(lottolist);
			
			System.out.println("로또번호 >> "+ i + " : " + lottolist);
			
			lottoSet.clear();
			//새로운 작업을 위해 set데이터를 모두 삭제한다.
			
		}
		
	}
	
}
