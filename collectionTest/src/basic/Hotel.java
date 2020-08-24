package basic;

//20.02.10 과제

import java.util.*;

public class Hotel {

	HashMap<Integer, HotelVO> hotel = new HashMap<>();

	Hotel() {
		// roomtype선언

		hotel.put(201, new HotelVO(201, "싱글룸"));
		hotel.put(202, new HotelVO(202, "싱글룸"));
		hotel.put(203, new HotelVO(203, "싱글룸"));
		hotel.put(204, new HotelVO(204, "싱글룸"));
		hotel.put(205, new HotelVO(205, "싱글룸"));
		hotel.put(206, new HotelVO(206, "싱글룸"));
		hotel.put(207, new HotelVO(207, "싱글룸"));
		hotel.put(208, new HotelVO(208, "싱글룸"));
		hotel.put(209, new HotelVO(209, "싱글룸"));

		hotel.put(301, new HotelVO(301, "더블룸"));
		hotel.put(302, new HotelVO(302, "더블룸"));
		hotel.put(303, new HotelVO(303, "더블룸"));
		hotel.put(304, new HotelVO(304, "더블룸"));
		hotel.put(305, new HotelVO(305, "더블룸"));
		hotel.put(306, new HotelVO(306, "더블룸"));
		hotel.put(307, new HotelVO(307, "더블룸"));
		hotel.put(308, new HotelVO(308, "더블룸"));
		hotel.put(309, new HotelVO(309, "더블룸"));

		hotel.put(401, new HotelVO(401, "스위트룸"));
		hotel.put(402, new HotelVO(402, "스위트룸"));
		hotel.put(403, new HotelVO(403, "스위트룸"));
		hotel.put(404, new HotelVO(404, "스위트룸"));
		hotel.put(405, new HotelVO(405, "스위트룸"));
		hotel.put(406, new HotelVO(406, "스위트룸"));
		hotel.put(407, new HotelVO(407, "스위트룸"));
		hotel.put(408, new HotelVO(408, "스위트룸"));
		hotel.put(409, new HotelVO(409, "스위트룸"));
	}

	class HotelVO {

		// 방번호 int
		// 방종류 투숙객이름 string

		int roomnum;
		String roomtype;
		String username = "-";

		public HotelVO(int roomnum, String roomtype) {
			super();
			this.roomnum = roomnum;
			this.roomtype = roomtype;
		}

		public int getRoomnum() {
			return roomnum;
		}

		public void setRoomnum(int roomnum) {
			this.roomnum = roomnum;
		}

		public String getRoomtype() {
			return roomtype;
		}

		public void setRoomtype(String roomtype) {
			this.roomtype = roomtype;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

	}

	private Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		new Hotel().start();
	}

	void start() {
		int a = 0;
		do {
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
			a = Integer.parseInt(s.nextLine());

			switch (a) {
			case 1:
				checkin();
				break;
			case 2:
				checkout();
				break;
			case 3:
				hotelroom();
				break;
			case 4:
				System.out.println("프로그램이 종료되었습니다");
				break;
			}

		} while (a != 0);
	}

	void checkin() {
		int a = 0;
		String name = "";

		System.out.println("==== 체크인 ====");
		System.out.println("---------------");
		System.out.println("201~209호 : 싱글룸");
		System.out.println("301~309호 : 더블룸");
		System.out.println("401~409호 : 스위트룸");
		System.out.println("---------------");
		System.out.print("방번호 입력");

		a = Integer.parseInt(s.nextLine());

		if (!(hotel.containsKey(a))) {
			System.out.println(a + " 호실은  존재하지 않습니다.");

			start();
		}
		if (!(hotel.get(a).getUsername().equals("-"))) {
			System.out.println("선택하신 방은 사용중입니다.");
		} else {
			System.out.print("성함을 입력해주세요>>");
			name = s.nextLine();

			// 입력한 이름값을 a의 키값을 찾아 username에 입력한다.

			hotel.get(a).setUsername(name);

			System.out.println("입실이 완료되었습니다.");
		}

	}

	void checkout() {
		int a = 0;
		System.out.println("==== 체크아웃 ====");
		System.out.print("체크아웃할 방번호를 입력하세요");
		a = Integer.parseInt(s.nextLine());

		if (!(hotel.containsKey(a))) {
			System.out.println(a + " 호실은  존재하지 않습니다.");

			return;
		}

		if ((hotel.get(a).getUsername().equals("-"))) {
			System.out.println("현재객실은 투숙객이 없습니다.");
		} else {
			hotel.get(a).setUsername("-");
		}

		System.out.println("체크아웃 되셨습니다.");

	}

	void hotelroom() {

		System.out.println();
		System.out.println("==== 현재 객실 상태 ====");
		System.out.println("방번호 \t 방종류 \t 투숙객이름 ");

		Set<Integer> keynum = hotel.keySet();

		ArrayList<Integer> holist = new ArrayList<>(keynum);

		Collections.sort(holist);

		for (int i = 0; i < holist.size(); i++) {
			System.out.println(holist.get(i) + "\t"
					+ hotel.get(holist.get(i)).getRoomtype() + "\t"
					+ hotel.get(holist.get(i)).getUsername());
		}

		/*
		 * Iterator<Integer> keyIt = holist.iterator();
		 * 
		 * while(keyIt.hasNext()) { int key = keyIt.next();
		 * System.out.println(key + "\t" + hotel.get(key).getRoomtype() + "\t" +
		 * hotel.get(key).getUsername()); }
		 */
		System.out.println();
	}
}
