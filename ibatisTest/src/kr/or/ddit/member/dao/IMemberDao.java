package kr.or.ddit.member.dao;

import java.util.*;

import kr.or.ddit.member.vo.MemberVO;


//20.02.25--06
//인터페이스는 형태를 잡아주는 것 -- 수정할 필요가 없다. 

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서 Service에 전달하는 DAO의 interface
 * @author PC-19
 *
 */
public interface IMemberDao {
	
	/**
	 * 검색할 컬럼명과 검색할 단어가 저장된 Map을 매개변수로 받아서 해당 데이터를 검색하여 
	 * list로 반환하는 메소드 
	 * @param searchMap 검색할 컬럼명과 검색할 단어가 저장된 Map객체 
	 * @return 검색결과를 List에 담아서 반환
	 */
	public List<MemberVO> searchMember(Map<String, String> searchMap);
	
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메소드 
	 * @param memVo  DB에 insert할 자료가 저장된 MemberVO 객체
	 * @return DB작업 성공 : 1, 실패 : 0
	 */
	public int insertMember(MemberVO memVo);
	
	/**
	 * 회원 아이디를 매개변수로 받아서 해당 회원 정보를 삭제하는 메소드
	 * @param memId 삭제할 회원 ID
	 * @return 작업성공 :1 , 실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * 하나의 MemberVO자료를 이용하여 회원 정보를 update하는 메소드
	 * @param memVo 수정할 정보가 저장된 MemberVO객체 
	 * @return 작업성공 : 1, 실패 : 0
	 */
	public int updateMember(MemberVO memVo);
	
	/**
	 * Map의 정보를 이용하여 회원 정보를 수정하는 메소드
	 * @param paramMap  수정할 정보가 저장된 map객체
	 * @return  작업성공 : 1, 실패 : 0
	 */
	public int updateMember(Map<String, String> paramMap);
	
	/**
	 * 회원 아이디를 매개변수로 받아서 해당 회원의 인원수를 반환하는 메소드 
	 * @param memId 검색할 회원id
	 * @return 검색된 회원 수 
	 */
	public int getMemberCount(String memId);
	
	/**
	 * 전체 회원정보를 DB에서 가져와 각각의 정보는 MemberVO에 담고, 
	 * 이 MemberVO객체를 list에 넣어서 반환하는 메소드 
	 * @return 전체회원정보(MemberVO객체)가 저장된 list객체
	 */
	public List<MemberVO> getAllMember();
	
}