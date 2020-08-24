package kr.or.ddit.jdbcboard.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.jdbcboard.vo.JdbcVO;

public interface IJdbcboardDao {
	
	//기능 새글쓰기(저장), 검색하기, 수정, 삭제 
	//리스트보기 : ??? 리스트보기 새창을 띄우는것일까 아니면  창을 전환하는 것인지 .. 
	
	//insert : vo 받고 int로 넘겨주기 
	//string : string 넘겨주기
	//view : 게시글 내용 불러오기 vo
	//update : vo
	//delete : int 
	
	/**
	 * 검색할 컬럼명과 검색할 단어가 저장된 map을 매개변수로 받아서 해당데이터를 검색하여 list로 반환하는 메소드 
	 * key : 검색할 컬럼명(이름,제목,내용) , value : 검색할 단어 
	 * @param searchMap 검색할 컬럼명과 검색할 단어가 저장된 map객체
	 * @return 검색결과를 list에 담아서 반환
	 */
	public List<JdbcVO> searchBoard(Map<String, String> searchMap);
	
	/**
	 * JdbcVO에 담겨진 자료를 select하여 조건에 맞는 게시글의 모든 정보를 찾아보게 하는 메소드 
	 * @param boardno 검색한 게시글 번호가 저장될 변수 
	 * @return 검색결과를 list에 담아서 반환
	 */
	public JdbcVO LookBoard(int boardno); //게시글 보기 
	
	/**
	 * jdbcvo에 담겨진 자료를 데이터베이스에 insert하는 메소드 
	 * @param jvo DB에 insert할 자료가 저장된 jdbcvo객체 
	 * @return DB작업 성공:1, 실패:0
	 */
	public int insertBoard(JdbcVO jvo);
	
	/**
	 * 게시판의 글번호를 매개변수로 받아서 해당 게시글의 정보를 삭제하는 메소드 
	 * @param boardNum 삭제할 게시글 번호
	 * @return 작업성공:1, 실패:0
	 */
	public int deleteBoard(int boardNum);
	
	/**
	 * 하나의 jvo를 이용하여 회원정보를 update하는 메소드 
	 * @param jvo 수정할 정보가 저장된 jvo객체
	 * @return 작업성공: 1, 실패:0
	 */
	public int updateBoard(JdbcVO jvo);
	
	/**
	 * 전체 게시글 정보를 DB에서 가져와 각각의 정보는 jvo에 담고 이 jvo객체를 list에 넣어서 반환하는 메소드 
	 * @return 전체 게시글 정보가 저장된 list 객체
	 */
	public List<JdbcVO> getAllBoard();
	
}
