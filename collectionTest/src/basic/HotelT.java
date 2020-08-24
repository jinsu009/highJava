package basic;

import java.util.*;

public class HotelT {

	private Map<Integer, Room> myHotel = new HashMap<Integer, Room>();

	Scanner s = new Scanner(System.in);

	// 생성자 ==> 방번호와 종류 초기화 작업수행
	public HotelT() {

		// 객실초기화
		for (int i = 2; i <= 4; i++) {
			String roomT = null;

			// 방종류
			switch (i) {
			case 2:
				roomT = "싱글룸";
				break;
			case 3:
				roomT = "더블룸";
				break;
			case 4:
				roomT = "스위트룸";
				break;
			}

			// 방번호
			for (int j = 1; j <= 9; j++) {
				int roomNum = i * 100 + j;

				// room객체 생성
				Room room = new Room(roomNum, roomT);

				myHotel.put(roomNum, room);
				// map에 room객체 추가
			}
		}

	}

	public static void main(String[] args) {
		new HotelT().hotelStart();
	}

	// 메뉴 출력 메소드
	public int displayMenu() {
		System.out.println();
		System.out.println("*********************************************");
		System.out.println("\t\t\t어서오세요");
		System.out.println("*********************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인");
		System.out.println("2. 체크아웃");
		System.out.println("3. 객실상태");
		System.out.println("0. 업무종료");
		System.out.println("============================");
		System.out.print("번호입력");

		int num = s.nextInt();

		return num;
	}

	// 프로그램이 시작되는 메소드
	public void hotelStart() {
		while (true) {
			int choice = displayMenu();
			switch (choice) {
			case 1:
				checkin();
				break;
			case 2:
				checkout();
				break;
			case 3:
				showRoom();
				break;
			case 0:
				System.out.println("종료되었습니다.");
				return;
			default:
				System.out.println("번호를 잘못입력했습니다.");

			}
		}
	}
	
	public void showRoom()
	{
		//전체 객실 상태를 출력
		System.out.println();
		System.out.println("==== 현재 객실 상태 ====");
		System.out.println("방번호 \t 방종류 \t 투숙객이름 ");
		
		
		//호텔의 객실번호를 저장하는 list생성 및 초기화
		ArrayList<Integer> roomNumList = new ArrayList<>(myHotel.keySet());
		
		//keyset???
		
		//객실번호 정렬
		Collections.sort(roomNumList);
		
		//객실 갯수만큼 반복
		for(int roomNum : roomNumList)
		{
			//room객체
			Room room = myHotel.get(roomNum);
			
			System.out.print(room.getRoomNum() + "\t" + room.getRoomT() + "\t");
			
			
			String name = room.getGuestName();
			if(name == null){
				name="-";
			}
			System.out.println(room.getGuestName());
			
		}
		System.out.println("====================================");
		
		System.out.println();
		
		
		
		
		

	}
	
	public void checkout()
	{
		System.out.println("==== 체크아웃 ====");
		System.out.print("체크아웃할 방번호를 입력하세요");
		int roomNum = s.nextInt();
		
		if(!(myHotel.containsKey(roomNum))){
			System.out.println(roomNum + " 호 객실은 존재하지 않습니다.");
			return;
		}
		
		if(myHotel.get(roomNum).getGuestName()==null)
		{
			System.out.println("현재객실은 투숙객이 없습니다.");
			return;
		}
		
		//체크아웃작업
		
		String name =myHotel.get(roomNum).getGuestName();
		
		myHotel.get(roomNum).setGuestName(null);
		
		System.out.println(roomNum+" 호 객실의  " + name +" 님이 체크아웃되었습니다.");
	}

	// 체크인 하는 메서드
	public void checkin() {
		System.out.println("==== 체크인 ====");
		System.out.println("---------------");
		System.out.println("201~209호 : 싱글룸");
		System.out.println("301~309호 : 더블룸");
		System.out.println("401~409호 : 스위트룸");
		System.out.println("---------------");
		System.out.print("방번호 입력");

		//정수로 방번호를 입력
		int roomNum = s.nextInt();
		
		//객실번호가 있는지 여부검사
		if(!(myHotel.containsKey(roomNum))){
			System.out.println(roomNum + " 호 객실은 존재하지 않습니다.");
			return;
		}
		
		//해당 객실에손님이있는여부검사
		if(myHotel.get(roomNum).getGuestName()!=null)

//		if(!myHotel.get(roomNum).getGuestName().equals(null))
		
			//string는 equals로 비교하는거 아닌가?
			//null값은 equal로 비교도 가능한데 어째서 오류?
			// null은 스트링이 아니고 공백의 상태이므로 equals로 비교할 수 없다. 
			//"".equals()
			//null.equals() -- error
			
			//itorator 는 sort되지 않는다.
			
			
		{
			System.out.println("현재객실은 사용중입니다.");
			return;
		}
		
		System.out.print("성함을 입력해주세요");
		String name = s.next();
		
		//입력한 손님이름을 해당 객실에 입력
		myHotel.get(roomNum).setGuestName(name);
		
		System.out.println(roomNum + " 호 객실에 " + name + " 씨가 체크인 되셨습니다");
		
	}

}

class Room {

	private int roomNum; // 방번호
	private String roomT;// 방타입
	private String guestName;// 손님이름

	// 생성자
	public Room(int roomNum, String roomT) {
		super();
		this.roomNum = roomNum;
		this.roomT = roomT;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomT() {
		return roomT;
	}

	public void setRoomT(String roomT) {
		this.roomT = roomT;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
}
