package basic;

//20.02.08

import java.util.*;

/*
 * 
 * 문제 >> 이름,주소, 전화번호, 속성을 갖는 phone클래스를 만들고 
 * 		  이 phone클래스를 이용하여 전화번호 정보를 관리하는 프로그램을 완성하시오 
 * 
 * 		이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체출력하는 기능이 있따. 
 * 
 * 		전체 전화번호 정보는 Map을 이용하여 관리한다. 
 * 		(key 값은 '이름'으로 사용하고 , value값으로는 phone 클래스의 인스턴스로 한다.)
 * 
 * 	(실행예시)
 *		===
 *			전화번호 관리 프로그램
 *		===
 *		1. 전화번호 등록 
 *		2. 전화번호 수정
 *		3. 전화번호 삭제
 *		4. 전화번호 검색 
 *		5. 전화번호 전체출력 
 *		0. 프로그램 종료
 ===
 메뉴선택 >

 새롭게 등록할 전화번호정보를 입력하세요 
 이름 > 홍길동
 전화번호 > 010-5151-2625
 주소 > 대전

 홍길동 전화번호 정보가 추가되었습니다.

 */

class phVO {

	private String name;
	private String num;
	private String add;

	public phVO(String name, String num, String add) {
		super();
		this.name = name;
		this.num = num;
		this.add = add;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

}

public class PhoneBookTest {

	HashMap<String, phVO> phnum = new HashMap<>();
	// hashmap도 입력값들이 shuffle된다.

	Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		new PhoneBookTest().start();

	}

	void start() {
		int a = 0;

		do {
			System.out.println("===================");
			System.out.println("전화번호 관리 프로그램");
			System.out.println("===================");
			System.out.println("1. 전화번호 등록");
			System.out.println("2. 전화번호 수정");
			System.out.println("3. 전화번호 삭제");
			System.out.println("4. 전화번호 검색");
			System.out.println("5. 전화번호 전체 출력");
			System.out.println("0. 프로그램 종료");
			System.out.print("메뉴선택 >> ");

			do {
				try {
					a = (Integer.parseInt(s.nextLine()));
				} catch (Exception e) {
				}
				if (!(a == 1 || a == 2 || a == 3 || a == 4 || a == 5 || a == 0)) {
					System.out.println("잘못된 값을 입력하셨습니다.");
					s.nextLine();
				}
			} while (!(a == 1 || a == 2 || a == 3 || a == 4 || a == 5 || a == 0));
			switch (a) {
			case 1:
				phadd();
				break;
			case 2:
				phedit();
				break;
			case 3:
				phdel();
				break;
			case 4:
				phser();
				break;
			case 5:
				list();
				break;
			case 0:
				System.out.println("프로그램이 종료되었습니다.");
				break;
			}

		} while (a != 0);

	}

	String namein = "";
	String numin = "";
	String addin = "";

	void phadd() {
		System.out.println();

		System.out.println("=== 전화번호 등록 ===");
		System.out.println("이름을 입력하세요");
		String namein = s.nextLine();

		System.out.println("전화번호를 입력하세요");
		String numin = s.nextLine();

		System.out.println("주소를 입력하세요");
		String addin = s.nextLine();

		phVO list = new phVO(namein, numin, addin);
		// list는 인스턴스

		phnum.put(namein, list);

		System.out.println("입력이 완료되었습니다.");
		System.out.println();

		list();
		System.out.println();
	}

	void phedit() {
		// 이름을입력해서 찾아오고 수정을해야함
		int a = 0;
		System.out.println();

		list();
		System.out.println();

		System.out.println(" === 정보 수정 ===");
		System.out.println(" 수정할 회원의 이름을 입력하세요");
		namein = s.nextLine();
		phVO out = phnum.get(namein);
		System.out.println();

		System.out.println("=== 수정전 정보 ===");
		System.out.print("이름 >> " + out.getName() + "\t");
		System.out.print("번호 >> " + out.getNum() + "\t");
		System.out.println("주소 >> " + out.getAdd() + "\t");
		System.out.println();
		do {
			System.out.println();

			System.out.println("=== 수정 ===");
			System.out.println("무엇을 수정하시겠습니까?");
			System.out.println("1. 전화번호");
			System.out.println("2. 주소");
			System.out.println("0. 뒤로가기");
			do {
				try {
					a = Integer.parseInt(s.nextLine());
				} catch (Exception e) {
				}
				if (!(a == 1 || a == 2 || a == 0)) {
					System.out.println("잘못된 값을 입력하였습니다.");
					s.nextLine();
				}
			} while (!(a == 1 || a == 2 || a == 0));

			switch (a) {
			case 1:
				System.out.println("수정할 전화번호 입력하세요");
				numin = s.nextLine();
				out.setNum(numin);
				phnum.put(namein, out);

				break;

			case 2:
				System.out.println("수정할 주소 입력하세요");
				addin = s.nextLine();
				out.setAdd(addin);
				phnum.put(namein, out);

				break;

			case 0:
				break;

			// 전화번호를 수정
			}
		} while (a != 0);

		System.out.println("=== 수정후 정보 ===");
		System.out.println("이름" + "\t" + "번호" + "\t" + "주소");
		System.out.println("===================");
		System.out.print("이름 >> " + out.getName() + "\t");
		System.out.print("번호 >> " + out.getNum() + "\t");
		System.out.println("주소 >> " + out.getAdd());
		System.out.println("===================");

	}

	void phdel() {
		System.out.println();
		list();
		// 이름을 입력해서 찾아오고 수정을 해야함
		System.out.println("=== 전화번호 삭제 ===");
		System.out.println("삭제할 이름을 입력하세요");
		namein = s.nextLine();

		System.out.println("===================");

		phnum.remove(namein);

		System.out.println("정보가 삭제되었습니다.");
		System.out.println();

		list();
		System.out.println();

	}

	void phser() {
		System.out.println();

		System.out.println("=== 전화번호 검색 ===");
		System.out.println("검색할 이름을 입력하세요");
		namein = s.nextLine();

		System.out.println("===================");
		System.out.println();
		phVO out = phnum.get(namein);

		if (phnum.containsKey(namein)) {
			System.out.println("이름" + "\t" + "번호" + "\t" + "주소");
			System.out.println("===================");
			System.out.print("이름 >> " + out.getName() + "\t");
			System.out.print("번호 >> " + out.getNum() + "\t");
			System.out.println("주소 >> " + out.getAdd() + "\t");
			System.out.println("===================");
			System.out.println();
		} else {
			System.out.println("검색하신 결과가 없습니다.");
			return;
		}

	}

	void list() {

		System.out.println();

		System.out.println("이름" + "\t" + "번호" + "\t" + "주소");

		for (phVO namein : phnum.values()) {
			System.out.println(namein.getName() + "\t" + namein.getNum() + "\t"
					+ namein.getAdd() + "\t");
		}
		System.out.println("===================");

		System.out.println();

	}

}
