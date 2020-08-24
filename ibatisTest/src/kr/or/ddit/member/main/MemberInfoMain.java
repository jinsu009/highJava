package kr.or.ddit.member.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;


/*
 * 이 클래스는 Controller역할과 view역할을 같이 한다. 
 */
public class MemberInfoMain {
	
	private Logger log = Logger.getLogger(MemberInfoMain.class);
	
	private IMemberService service; // service객체가 저장될 변수선언

	public MemberInfoMain() {
		//service = new MemberServiceImpl(); // service객체 생성
		service = MemberServiceImpl.getInstance(); //service객체 생성 -- 싱글톤패턴
	}

	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		new MemberInfoMain().start();
	}

	public void start() {
		int a = 0;
		do {
			
			log.info("start메소드 시작 ");
			
			System.out.println();
			System.out.println("-------------------------");
			System.out.println("\t1. 자료 입력");
			System.out.println("\t2. 자료 삭제");
			System.out.println("\t3. 전체 자료 수정");
			System.out.println("\t4. 선택 자료 수정");
			System.out.println("\t5. 자료 검색");
			System.out.println("\t6. 전체 출력");
			System.out.println("\t0. 종료");
			System.out.println("-------------------------");
			System.out.print("작업 선택 >> ");
			do {
				try {
					a = s.nextInt();
				} catch (Exception e) {
				}
				if (!(a == 1 || a == 2 || a == 3 || a == 4 || a == 5 || a == 6 || a == 0)) {
					System.out.println("잘못된 값을 입력하셨습니다.");
					s.nextInt();
				}
			} while  (!(a == 1 || a == 2 || a == 3 || a == 4 || a == 5 || a == 6 || a == 0)) ;
			switch (a) {
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				update2();
				break;
			case 5:
				memSearch();
				break;
			case 6:
				select();
				break;
			case 0:
				System.out.println("시스템 종료");
				break;
			}
		} while (a != 0);

	}
	
	// 회원 정보를 검색하는 메소드 
	public void memSearch(){
		//검색할 필드를 보여주고 사용자가 선택하게 한다음 검색할 내용을
		//입력 받아서 사용자가 선택한 필드에 입력받은 내용이 포함되는 자료를 출력한다. 
		log.info("search메소드 시작 ");
		
		int a=0;
		String fieldName ="";
		String searchData ="";
		System.out.println("---검색---");
		do{
			System.out.println("무엇을 검색하시겠습니까?");
			System.out.println("1. 아이디 검색");
			System.out.println("2. 이름 검색");
			System.out.println("3. 전화번호 검색");
			System.out.println("4. 주소 검색");
			System.out.println("0. 뒤로가기 ");
			a = s.nextInt();
			switch(a)
			{
			case 1:  //아이디로 검색
				System.out.print("아이디(mem_id)를 입력하세요 >> ");
				fieldName = "mem_id";
				searchData = s.next();
				break;
			case 2:  //이름으로 검색
				System.out.print("이름(mem_name)을 입력하세요 >> ");
				fieldName = "mem_name";
				searchData = s.next();
				break;
			case 3: //전화번호 검색
				System.out.print("전화번호(mem_tel)를 입력하세요 >> ");
				fieldName = "mem_tel";
				searchData = s.next();
				break;
			case 4: //주소 검색
				System.out.print("주소(mem_addr)를 입력하세요 >> ");
				fieldName = "mem_addr";
				searchData = s.next();
				break;
			case 0:
				return;
			}
			
		}while(a<=0||a>3);		
		
		Map<String, String> searchMap = new HashMap<>();
		// 매개변수로 사용할 Map객체 생성
		searchMap.put("field", fieldName);
		searchMap.put("search", searchData);
		
		List<MemberVO> memList = service.searchMember(searchMap);
		printMember(memList);
		log.info("search메소드 종료 ");
	}
	
	public void insert() {
		
		log.info("insert메소드 시작 ");
		
		 MemberVO mvo = new MemberVO();
		 //입력한 추가할 데이터를 저장할 MemberVO객체 생성
	
			System.out.print("등록할 멤버 아이디(mem_id) 입력 >> ");
			String  mem_id= s.next();
			int count = service.getMemberCount(mem_id);
			if (count > 0) {
				System.out.println(mem_id + " 는 이미 존재하는 아이디입니다.");
				return;
			}
			// 중복이 없을 경우 반복문을 빠져나와서 나머지 자료들을 입력해준다.
			System.out.print("회원 이름(mem_name) 입력 >> ");
			String nm = s.next();
			mvo.setMem_name(nm); //setter를 이용해서 입력한 값들을 VO객체에 저장한다. 
			System.out.print("회원 전화번호(mem_tel) 입력 >> ");
			String tel = s.next();
			mvo.setMem_tel(tel);
			s.nextLine();
			System.out.print("회원 주소(mem_addr) 입력 >> ");
			String addr = s.nextLine();
			mvo.setMem_addr(addr);
			
			mvo.setMem_id(mem_id);
			
			int cnt = service.insertMember(mvo);	//VO객체를 이용해서 추가해주는 service객체쪽의 메소드를 호출한다. 
			
			if (cnt > 0) {
				System.out.println("새로운 자료 등록 성공");
			} else {
				System.out.println("새로운 자료 등록 실패 ");
			}
			
			log.info("insert메소드 종료 ");
		} 

	public void delete(){
		
		log.info("delete메소드 시작 ");

		System.out.print("삭제 할 멤버 아이디(mem_id) 입력 >> ");
		String mem_id = s.next();
		
			int count = service.getMemberCount(mem_id);
			if (count == 0) {
				System.out.println(mem_id + " 는 존재하지 않는 회원입니다.");
				return;
			}
			
			int cnt = service.deleteMember(mem_id);
			
			if (cnt > 0) {
				System.out.println("자료가 삭제 되었습니다.");
			} else {
				System.out.println("자료 삭제가 실패했습니다. ");
			}
			
			log.info("delete메소드 종료 ");
	}
	
	public void update() {
		
		log.info("update메소드 시작 ");

			System.out.print("수정할 회원의 아이디를 입력하세요 >> ");
			String memId = s.next();
			int count = service.getMemberCount(memId);
			if (count == 0) {
				System.out.println(memId + " 은 없는 회원입니다. 수정작업을 종료합니다.");
				return;
			}
			System.out.println("---- 수정 ----");
			System.out.print("회원 이름(mem_name) 입력 >> ");
			String nm = s.next();
			System.out.print("회원 전화번호(mem_tel) 입력 >> ");
			String tel = s.next();
			s.nextLine(); // 버퍼비우기
			System.out.print("회원 주소(mem_addr) 입력 >> ");

			String addr = s.nextLine();

			MemberVO mvo = new MemberVO();
			mvo.setMem_id(memId);
			mvo.setMem_name(nm);
			mvo.setMem_tel(tel);
			mvo.setMem_addr(addr);
			
			int cnt = service.updateMember(mvo);

			if (cnt > 0) {
				System.out.println(memId + " 회원의 정보가 수정 되었습니다.");
			} else {
				System.out.println("수정작업 실패");
			}
			
			log.info("update메소드 종료 ");
	}

	public void update2() 
	{
		log.info("update2메소드 시작 ");
		System.out.print("수정할 회원의 아이디를 입력하세요 >> ");
		String memId = s.next();

		int count = service.getMemberCount(memId);

		if (count == 0) {

			System.out.println(memId + " 은 없는 회원입니다. 수정작업을 종료합니다");

			return;
		}

		int a = 0;
		String selfield="";
		String data="";

		do {
			System.out.println("어떤 정보를 수정 하시겠습니까.?");
			System.out.println("1. 이름 수정");
			System.out.println("2. 전화번호 수정");
			System.out.println("3. 주소 수정");
			System.out.println("0. 종료");
			a = s.nextInt();
		
			switch (a) {
			case 1:
				System.out.print("회원 이름(mem_name) 입력 >> ");
				selfield = "mem_name";
				data = s.next();
				break;
			case 2:
				System.out.print("회원 전화번호(mem_tel) 입력 >> ");
				selfield = "mem_tel";
				data=s.next();
				break;
			case 3:
				s.nextLine(); // 버퍼비우기
				System.out.print("회원 주소(mem_addr) 입력 >> ");
				selfield = "mem_addr";
				data =s.nextLine();
				break;
			case 0:
				return;
			}
			
		} while (a <= 0 || a>3);
		
		// 매개변수로 사용할 Map객체 생성
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("field", selfield);
		paramMap.put("data", data);
		paramMap.put("memId", memId);
		
		int cnt = service.updateMember(paramMap);
		
		if (cnt > 0) {
			System.out.println(memId + " 회원의 정보가 수정 되었습니다.");
		} else {
			System.out.println("수정작업 실패");
		}
		
		log.info("update2메소드 종료 ");
	}
	
	public void select() {

		// 서비스에 있는 getAllMember메소드를 호출해서 전체 회원정보를 가져온다.
		List<MemberVO> memList = service.getAllMember();

		printMember(memList);

	}
	
	public void printMember(List<MemberVO> memList)
	{
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("ID 	NAME		TEL		 ADDR");
		System.out.println("--------------------------------------");

		for (MemberVO memVo : memList) {

			String memId = memVo.getMem_id();
			String memName = memVo.getMem_name();
			String memTel = memVo.getMem_tel();
			String memAddr = memVo.getMem_addr();

			System.out.println(" " + memId + " " + memName + " " + memTel + " "
					+ memAddr);

		}
	}
}
