package basic;

//20.02.08

import java.util.*;

public class Lotto {

	public static int a;

	// static 으로 사용하면 static으로 선언된 class, method뿐만 아니라 다른 곳에서도 호출이 가능하다.
	// Lotto.a;

	public static void main(String[] args) {
		new Lotto().start();
		// static void start()가 아니면 객체를 생성해줘야한다.
		// new Lotto() 객체생성 부분
		// .start(); 객체생성과 동시에 start메소드를 불러들인 것이다.
	}

	void start() {
		Scanner s = new Scanner(System.in);

		int z = 0;

		do {
			System.out.println();
			System.out.println("==========================");
			System.out.println("\tLotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");
			System.out.print("메뉴선택 >> ");
			do {

				try {
					z = Integer.parseInt(s.nextLine());
				} catch (Exception e) {

				}
				if (!(z == 1 || z == 2)) {
					System.out.println("[ 잘못된 값을 입력하였습니다 ]");
					s.nextLine();
				}

			} while (!(z == 1 || z == 2));
			switch (z) {
			case 1:
				buylotto();
				break;
			case 2:
				System.out.println();
				System.out.println("프로그램이 종료되었습니다.");
				break;
			}
		} while (z != 2);
	}

	void buylotto() {
		Scanner s = new Scanner(System.in);

		do {
			System.out.println();
			System.out.println("=== 로또 구매 ===");
			System.out.println("금액을 입력하세요 (1000원에 한 번 입니다.) >> (0.뒤로가기) ");
			a = Integer.parseInt(s.nextLine());

			if (a < 1000) {
				System.out.println();
				System.out.println("금액이 적습니다.");
				System.out.println("로또를 구매하실수 없습니다.");

				return;
			}
			if (a > 10000) {
				System.out.println();
				System.out.println("금액이 많습니다.");
				System.out.println("로또를 구매하실수 없습니다.");

				return;
			} else {
				lotto();
			}

		} while (a != 0);

	}

	void lotto() {
		HashSet<Integer> lottoSet = new HashSet<>();

		// 받은금액을 1000으로 나눈 몫만큼 로또의 갯수를 출력
		// 로또출력

		int b = a / 1000;
		// 5400 을 입력하였을때 for문이 5번 돌수 있도록
		int c = a % 1000;

		System.out.println("행운의 로또번호는 아래와 같습니다.");
		for (int i = 0; i < b; i++) {
			while (lottoSet.size() < 6) {
				int num = (int) (Math.random() * 45) + 1;
				lottoSet.add(num);
			}
			System.out.println("lotto > " + lottoSet);
			lottoSet.clear();
		}

		System.out.println();
		System.out.println("받은금액 >> " + a + " 원" + "\t거스름돈 >> " + c + " 원");

	}

}
