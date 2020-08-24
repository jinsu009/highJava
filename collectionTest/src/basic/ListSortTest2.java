package basic;

//20.02.05

import java.util.*;

/*
 * 	- 정렬과 관련된 interface는 comparable과 comparator 이렇게 두가지가 있다.
 * 
 * 	- 이중에 사용자가 작성하는 객체 자체에 정렬기준을 넣기 위해서는 comparable 인터페이스를 구현하고,
 * 		정렬기준을 외부에서 별도로 구현할 경우에는 comparator 인터페이스를 구현하여 사용하면 된다.
 *  
 * 	- comparable 인터페이스는 compareTo()메소드를 재정의해서 구현해야 하고, 
 * 		comparator 인터페이슨 compare()메소드르 재정의해서 구현해야 한다. 	
 */

//회원정보를 저장할 수 있는 클래스 작성 > 회원의 이름을 기준으로 오름차순 정렬이 될 수 있도록 구현한다. 

class Member implements Comparable<Member> {
	private int num; // 회원번호
	private String name;// 회원이름
	private String tel;// 전화번호

	// 생성자
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	// getter, setter
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	// 이 메소드에서 회원의 이름을 기준으로 오름차순 정렬이 되도록 재정의 한다.
	@Override
	public int compareTo(Member mem) {
		// TODO Auto-generated method stub
		// return this.getName().compareTo(mem.getName());

		// 회원번호의 오름차순일 경우
		
		  if(this.getNum() > mem.getNum()) { return 1; //순서를 바꿀대는 양수를 반환 
		  
		  }
		  if(this.getNum()== mem.getNum()) { return 0; } else { return -1; }
		 

		// 회원번호의 내림차순일 경우
		/*if (this.getNum() < mem.getNum()) {
			return 1;
			// 순서를 바꿀대는 양수를 반환
		}
		if (this.getNum() == mem.getNum()) {
			return 0;
		} else {
			return -1;
		}*/

	}

}

public class ListSortTest2 {

	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<>();

		memList.add(new Member(5, "셰퍼드", "010-1111-1111"));
		memList.add(new Member(3, "말티즈", "010-1234-5678"));
		memList.add(new Member(2, "치와와", "010-9876-5432"));
		memList.add(new Member(7, "보더콜리", "010-1212-3434"));
		memList.add(new Member(4, "진돗개", "010-0909-6868"));
		memList.add(new Member(9, "삽살개", "010-7878-3232"));
		memList.add(new Member(12, "풍산개", "010-0202-7874"));

		System.out.println("정렬 전");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("---------------------");
		// Collections.sort(memList);
		// 정렬시키는 기준이 없어서 오류발생

		Collections.sort(memList);
		System.out.println("정렬후");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("---------------------");
		
		//외부정렬 내림차순
		Collections.sort(memList , new Desc2());
		System.out.println("외부정렬후");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("---------------------");

	}

}
// 회원번호의 내림차순으로 정렬이 될 수 있는 외부정렬 기준을 작성하여
// 리스트의 데이터를 정렬한 후 출력하시오
// 외부정렬 기준을 하라는 것은 class 를 따로 작성하라는 것이다. 
class Desc2 implements Comparator<Member>
{

	@Override
	public int compare(Member o1, Member o2) {
		//변수명은 바꿔도 되고 안바꿔도 된다. 
				
		/*if(o1.getNum()<o2.getNum())
		{
			return 1;
			//양수를 반환하면 두 값의 순서가 바뀐다. 
		}
		else if(o1.getNum()>o2.getNum())
		{
			return -1;
		}
		else{
			return 0;
		}*/
		
		//수식을 이용한 방법
//		return o2.getNum() - o1.getNum();
		
		//wrapperclass 를 이용한 방법1 > 전역메소드 인 compare() 이용하기
//		return Integer.compare(o1.getNum(), o2.getNum() )*-1;
		//내림차순 식 *-1을 해서 오름차순 식으로 변형??
		
		//wrapperclass를 이용한 방법2 > 멤머 메소드인 compareTo() 이용하기
		return new Integer(o1.getNum()).compareTo(o2.getNum()) * -1;
		
	}
	
	
}

