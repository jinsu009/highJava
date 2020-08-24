package main;
//20.02.27 --과제...?
import java.util.*;

import service.BoardServiceImpl;
import service.BoardServiceInter;
import vo.BoardVo;

public class BoardMain {
	public static int z =0;
	
	private BoardServiceInter service;
	
	public BoardMain(){
		service = BoardServiceImpl.getInstance();
	}
	
	Scanner s = new Scanner(System.in);

	public static void main(String[] args){
		new BoardMain().start();
	}
	
	public void start(){
		int a = 0;
		//게시판 목록이 첫화면부터 보여져야한다. 
		do{
		System.out.println("메뉴 :  1.새글작성    2.게시글보기    3.검색  4.전체게시글보기  0.작업종료");
		System.out.print("작업 선택 >> ");
		do {
			try {
				a = s.nextInt();
			} catch (Exception e) {
			}
			if (!(a == 1 || a == 2 || a == 3 || a == 4  || a == 0)) {
				System.out.println("잘못된 값을 입력하셨습니다.");
				s.nextInt();
			}
		} while (!(a == 1 || a == 2 || a == 3 || a == 4  || a == 0))  ;
		switch(a){
		case 1://새글작성
			insertboard();
			break;
		case 2://게시글보기
			lookboard();
			break;
		case 3://검색
			searchboard();
			break;
		case 4: //전체 게시판 출력
			getAllList();
			break;
		case 0:
			System.out.println("작업이 종료되었습니다.");
			break;
		}
		}while(a!=0);
	}
	
	public void insertboard(){
		BoardVo bvo = new BoardVo();//BoardVo 객체 생성
		
		System.out.println("---- 새로운 게시글 추가 ----");
		System.out.print(" - 제목 >> ");
		s.nextLine();
		String title = s.nextLine();
		s.nextLine();
		System.out.print(" - 작성자 >> ");
		String writer = s.nextLine();
		s.nextLine();
		System.out.print(" - 내용 >> ");
		String content = s.nextLine();
		
		bvo.setBoard_title(title);
		bvo.setBoard_writer(writer);
		bvo.setBoard_content(content);
		
		int cnt = service.insertBoard(bvo);
		
		if(cnt>0){
			System.out.println("새로운 게시글이 추가 되었습니다.");
		}else{
			System.out.println("새로운 게시글 작성 실패");
		}
	}
	public void lookboard(){
		System.out.print("확인하고 싶은 게시글 번호를 입력해주세요 >> ");
		z = s.nextInt();
		
		int count = service.BoardCount(z);
		
		if(count == 0){
			System.out.println("선택하신 게시글은 없습니다. 처음으로 돌아갑니다.");
		}
		BoardVo cnt = service.LookBoard(z);
		selectLook(cnt);
	}
	
	public void searchboard(){
		
		System.out.print("검색할 제목을 입력하세요 >> ");
		s.nextLine();
		String title = s.nextLine();
		
		BoardVo bvo = new BoardVo();//BoardVo 객체 생성
		bvo.setBoard_title(title);
		
		List<BoardVo> bvolist = service.searchBoard(bvo);
		
		printBoard(bvolist);
		System.out.println();
	}
	
	public void selectLook(BoardVo bvo){
		
		//데이터베이스에서 가져온 목록을 담는 메소드가 필요 
		//board_no 를 담는 것이 필요 
		//파리미터로 z값을 넘겨준다. 
		
		bvo = service.LookBoard(z);
		System.out.println(z+" 번 째 게시글 내용");
		System.out.println("-----------------------------------");
		System.out.println(" - 제목 : " + bvo.getBoard_title());
		System.out.println(" - 작성자 : " + bvo.getBoard_writer());
		System.out.println(" - 내용 : " + bvo.getBoard_content());
		System.out.println(" - 작성일 : " + bvo.getBoard_date());
		System.out.println(" - 조회수 : " + bvo.getBoard_cnt());
		System.out.println("-----------------------------------");
		
		//조회수 증가 .. 안됨
		int b = bvo.getBoard_cnt()+1;
		bvo.setBoard_cnt(b);
		int a =0;
		System.out.println("메뉴 : 1.수정    2.삭제    0.돌아가기");
		do {
			try {
				 a = s.nextInt();
			} catch (Exception e) {
			}
			if (!(a == 1 || a == 2 || a == 3 || a == 0)) {
				System.out.println("잘못된 값을 입력하셨습니다.");
				s.nextInt();
			}
		} while  (!(a == 1 || a == 2 || a == 3 || a == 0)) ;
		
		switch(a){
		case 1: edit(bvo); break;
		case 2: delete(); break;
		case 0: start(); break;
		}
		
		//20.02.28 edit이랑 delete 수행 하기 
		
	}
	public void edit(BoardVo bvo){
		//BoardVo bvo = new BoardVo();
		
		//내가 선택한 게시글의 내용을 가져올 수 있도록
		
		//bvo.getBoard_no();
		
		System.out.println("---- 수정 ----");
		System.out.println("-------------");
		s.nextLine();
		System.out.print(" - 제목 : ");
		String title = s.nextLine();
		s.nextLine();
		System.out.print(" - 내용 : ");
		String content = s.next();
		
		bvo.setBoard_title(title);
		bvo.setBoard_content(content);
		
		int cnt = service.updateBoard(bvo);
		if(cnt>0){
			System.out.println("수정이 완료되었습니다.");
			
		}else{
			System.out.println("수정이 실패되었습니다.");
		}
	}
	
	public void delete(){
		int cnt = service.deleteBoard(z);
		if(cnt>0){
			System.out.println("자료가 삭제 되었습니다.");
		}
		else{
			System.out.println("자료 삭제가 실패했습니다.");
		}
	}
	
	public void printBoard(List<BoardVo> bvoList)
	{
		System.out.println("-----------------------------------");
		System.out.println(" 번호    제목               작성자    내용 	       날짜    	 조회수");
		System.out.println("-----------------------------------");
		for(BoardVo bvo : bvoList){
			int num = bvo.getBoard_no();
			String title = bvo.getBoard_title();
			String writer = bvo.getBoard_writer();
			String content = bvo.getBoard_content();
			String date = bvo.getBoard_date();
			int count = bvo.getBoard_cnt();
			System.out.println(" " + num + " " + title + " "
							+ writer + " " + content+ " " + date + " " + count);
		}
	}
	
	public void getAllList(){
		
		List<BoardVo> bvoList = service.getAllList();
		
		for(BoardVo bvo : bvoList){
			int num = bvo.getBoard_no();
			String title = bvo.getBoard_title();
			String writer = bvo.getBoard_writer();
			String content = bvo.getBoard_content();
			String date = bvo.getBoard_date();
			int count = bvo.getBoard_cnt();
			System.out.println(" " + num + " " + title + " "
							+ writer + " " + content+ " " + date + " " + count);
		}
	}

}
