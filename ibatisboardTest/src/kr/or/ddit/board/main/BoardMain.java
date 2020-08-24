package kr.or.ddit.board.main;
//20.02.27 --과제...?
import java.util.*;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.BoardServiceInter;
import kr.or.ddit.board.vo.BoardVo;


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
		String title = null;
		int choice = -1;
		int a = 0;
		//게시판 목록이 첫화면부터 보여져야한다. 
		do{
			if(choice !=3){ title = null;} 
			choice = getAllList(title);
		switch(choice){
		case 1://새글작성
			insertboard();
			break;
		case 2://게시글보기
			lookboard();
			break;
		case 3://검색
			title = searchboard();
			break;
		case 0:
			System.out.println("작업이 종료되었습니다.");
			return;
		}
		}while(choice!=0);
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
			start();
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
			System.out.println();
			return;
		}
		BoardVo cnt = service.LookBoard(z);
		selectLook(cnt);
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
		//내가 선택한 게시글의 내용을 가져올 수 있도록
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
			start();
			
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
	
	public String searchboard(){
		
		s.nextLine(); // 버퍼비우기
		
		System.out.println("--- 검색 ---");
		System.out.print("검색할 제목을 입력하세요 >> ");
		String title = s.nextLine();
		return title;
		
	}
	
	public void printBoard(List<BoardVo> bvoList)
	{
		System.out.println("-----------------------------------");
		System.out.println(" 번호    제목               작성자    내용 	       날짜    	 조회수");
		System.out.println("-----------------------------------");
		
		for(BoardVo bvo : bvoList){
			System.out.println(" " +bvo.getBoard_no()+" "+bvo.getBoard_title()+" "+ bvo.getBoard_writer()
					+" "+bvo.getBoard_content()+" "+bvo.getBoard_date()+" "+bvo.getBoard_cnt());
		}
	}
	
	public int getAllList(String board_title){
		
		List<BoardVo> bvoList;
		if(board_title==null){
			bvoList = service.getAllList();
		}else{
			bvoList = service.searchBoard(board_title);
		}
		
		if(bvoList==null || bvoList.size()==0){
			System.out.println("[ 출력할 게시글이 없습니다.]");
		}else{
				System.out.println("-----------------------------------");
				System.out.println(" 번호    제목               작성자      날짜    	 조회수");
				System.out.println("-----------------------------------");
				for(BoardVo bvo : bvoList){
					System.out.println(" " +bvo.getBoard_no()+" "+bvo.getBoard_title()+" "+ bvo.getBoard_writer()
					+" "+bvo.getBoard_date()+" "+bvo.getBoard_cnt());
				}
		}
		System.out.println("-----------------------------------");
		System.out.println("메뉴 :  1.새글작성    2.게시글보기    3.검색   0.작업종료");
		System.out.print("작업 선택 >> ");
		int num = s.nextInt();
		
		return num;
		
	}

}
