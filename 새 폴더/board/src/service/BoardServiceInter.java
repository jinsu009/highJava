package service;

import java.util.List;
import java.util.Map;

import vo.BoardVo;

public interface BoardServiceInter {

	/**
	 * 전체 게시판 정보를 DB에서 가져와 각각의 정보는 BoardVo에 담고 
	 * 이 BoardVo객체를 list에 넣어서 반환하는 메소드
	 * @return 전체게시글 목록(BoardVo)이 저장된 list객체
	 */
	public List<BoardVo> getAllList(); //전체리스트 출력
	
	/**
	 * BoardVo에 담겨진 자료를 DB에 insert하는 메소드
	 * @param boardVo DB에 insert할 자료가 저장된 BoardVo 객체
	 * @return DB작업 성공 : 1 , 실패 : 0
	 */
	public int insertBoard(BoardVo boardVo); //새 글 작성
	
	/**
	 * BoardVo에 담겨진 자료를 select하여 조건에 맞는 게시글의 모든 정보를 찾아보게 하는 메소드 
	 * @param board_no 검색한 게시글 번호가 저장될 변수 
	 * @return 검색결과를 list에 담아서 반환
	 */
	public BoardVo LookBoard(int board_no); //게시글 보기 
	
	/**
	 * 검색할 제목을 매개변수로 받아 해당데이터를 검색하여 list로 반환하는 메소드 
	 * @param board_title 검색한 게시글의 제목이 저장될 변수
	 * @return 검색결과를 list에 담아서 반환
	 */
	public List<BoardVo> searchBoard(BoardVo boardVo); //검색
	
	/**
	 * Map의 정보를 이용하여 회원정보를 수정하는 메소드 
	 * @param paramMap 수정할 정보가 저장된 map객체
	 * @return 작업성공 : 1, 실패 : 0;
	 */
	public int updateBoard(BoardVo boardVo); //수정
	
	/**
	 * 게시판 번호를 매개변수로 넘겨받아서 해당 번호의 게시글을 삭제하는 메소드
	 * @param board_no 삭제할 게시글 번호
	 * @return 작업성공:1. 작업실패 :0
	 */
	public int deleteBoard(int board_no);
	
	public int BoardCount(int board_no);
	

}
