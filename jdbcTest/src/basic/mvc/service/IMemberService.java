package basic.mvc.service;

import java.util.List;
import java.util.Map;

import basic.mvc.vo.MemberVO;

//20.02.25--07

/**
 * Service객체는 DAO에 작성된 메소드를 원하는 직업에 맞게 호출하여 결과를 받아 오고, 
 * 받아온 자료를 Controller에게 보내주는 역할을 수행한다. 
 * 보통DAO의 메소드를와 같은 구조로 되어있다. 
 * @author PC-19
 *
 */
public interface IMemberService {
	/**
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<MemberVO> searchMember(Map<String, String> paramMap);
	
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
