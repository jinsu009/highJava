package basic;

//20.02.10

import java.util.*;

public class phte {

	// 전체적으로 관리하는 프로그램
	private Scanner s;
	private HashMap<String, Phone> phoneBookMap;

	// 생성자
	public phte() {
		s = new Scanner(System.in);
		phoneBookMap = new HashMap<>();
	}

	public static void main(String[] args) {
		new phte().phoneBookStart();
	}

	// 메뉴를 출력하고 실행할 메뉴 번호를 입력받아 반환하는 메소드
	public int displayMenu() {
		System.out.println("===================");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴선택 >> ");

		int num = s.nextInt();
		return num;
	}

	// 프로그램을 시작하는 메소드
	public void phoneBookStart() {

		System.out.println("===================");
		System.out.println("전화번호 관리 프로그램");
		System.out.println("===================");

		while (true) {

			int choice = displayMenu();
			
			switch(choice){
			case 1: //등록
				insert();
				break;
			case 2: //수정
				update();
				break;
			case 3: //삭제
				delete();
				break;
			case 4: //검색
				search();
				break;
			case 5: //전체출력
				displayAll();
				break;
			case 0:
				System.out.println("프로그램 종료");
				return;
				//잘못입력햇을때
			default :
				//string 을 못잡는다. 
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요.");
			}
		}
	}
	
	//전화번호 정보를 검색하는 메소드
	public void search(){
		System.out.println();
		System.out.println("검색할 전화번호를 입력하세요");
		
		System.out.print("이름 >> ");
		String name  =s.next();
		
		if(!phoneBookMap.containsKey(name)){
			System.out.println(name + " 씨는 등록되지 않은 사람입니다.");
			return;
		}
		
		//검색할 이름(key)을 이용해서 해당 phone객체를 구한다.
		Phone p = phoneBookMap.get(name);
		System.out.println(name + " 씨의 전화번호 정보");
		System.out.println(" 이름 > "+p.getName()+" 전화번호 > "+p.getTel()+" 주소 > "+p.getAdd());
	}
	
	//정보를 삭제하는 메소드
	public void delete(){
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요");
		
		System.out.print("이름 >> ");
		String name = s.next();
		
		if(!(phoneBookMap.containsKey(name)))
		{
			System.out.println(name + " 씨는 등록되지 않은 사람입니다.");
			return;
		}
		phoneBookMap.remove(name);
		System.out.println(name + " 씨의 정보를 삭제했습니다");
	}
	
	//전화번호를 수정하는 메소드 
	public void update(){
		System.out.println("수정할 전화번호 정보를 입력하세요 ");
		
		System.out.print("이름 >> ");
		String name = s.next();
		
		// 수정할 사람이이미등록되어있는지 여부 검사
		if(!(phoneBookMap.containsKey(name)))
		{
			System.out.println(name + " 씨는 등록되지않은 사람입니다.");
			return;
		}
		
		System.out.print("수정할 전화번호 >> " );
		String newtel = s.next();
		
		System.out.print("수정할 주소 >>");
		String newadd = s.next();
		
		//insert에서는 객체를 만들고 저장하는 작업을 나눠서 진행하였지만
		//update에서는 한번에 실행해보도록 하자
		phoneBookMap.put(name, new Phone(name, newtel, newadd));
		
		System.out.println(name+" 씨 전화번호가 수정되었습니다.");
		
		
	}
	
	//새로운전화번호 정보를 등록하는 메소드 
	public void insert(){
		//put을 사용 
		//이미 등록된 사람은 등록되지 않도록 구현
		
		System.out.println();
		System.out.println("등록할 새로운 전화번호 정보를 입력하세요.");
		
		System.out.print("이름 > ");
		String name = s.next();
		
		//이미 등록된 사람인지 여부 검사 
		if(phoneBookMap.containsKey(name))
		{
			System.out.println(name + " 씨는 이미 등록된 사람입니다.");
			return;
		}
		//동명이인일경우 어쩌지? 전화번호로 비교를 해야하는거 아닐까 ******
		//a. 지금은 동명이인일경우를 배제하고 생각하자 
		//정그러면 key값을 번호를 준다. 
		
		System.out.print("전화번호 >> ");
		String tel = s.next();
		
		System.out.print("주소 >> ");
		String add = s.next();
		
		//입력한 값을 이용해서 phone객체 생성
		Phone p = new Phone(name, tel, add);
		
		//생성된 phone객체를 map에 추가 
		
		phoneBookMap.put(name, p);
		System.out.println(name+" 씨의 전화번호 정보가 추가되었습니다.");
		
		
		
	}
	
	//전체자료를 출력하는 메소드
	public void displayAll(){
		
		System.out.println("===================");
		System.out.println("번호\t 이름\t 전화번호\t 주소");
		System.out.println("===================");
		//map을  사용해서 순서가 뭐가 먼지 나올지 모른다면 굳이 번호를 같이 나오게 해야할까 ?? *****
		//a. 구색맞추기
		
		//향상된 for문사용 혹은 key값을 호출 
		
		Set<String> nameSet = phoneBookMap.keySet();
		if(nameSet.size()==0){
			System.out.println("등록된 전화번호 정보가 하나도 없습니다.");
		}
		else{
			Iterator<String> nameIt = nameSet.iterator();
			int cnt = 0; //번호 출력용변수
			//번호는 출력할대만 보기위해 
			
			while(nameIt.hasNext()){
				cnt++;
				//카값(이름)가져오기
				String name = nameIt.next();
				//키값을 ㅣ용해서 phone객체(value 값) 구하기
				Phone p = phoneBookMap.get(name);
				System.out.println(cnt +"\t"+p.getName()+"\t"+p.getTel()+"\t"+p.getAdd());
					
			}
		}
		System.out.println("===================");
		System.out.println("출력 완료");
		

		
	}
	

}

// phone 클래스 만들기

class Phone {

	private String name;
	private String tel;
	private String add;

	// 기본생성자
	public Phone() {
	}

	// 생성자
	public Phone(String name, String tel, String add) {
		super();
		this.name = name;
		this.tel = tel;
		this.add = add;
	}

	// getter,setter
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

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	// tostring 는 있어도 그만 없어도 그만.

}