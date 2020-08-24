package ykManage.dao;

import java.util.List;
import java.util.Map;

import ykManage.vo.PharmVO;



public interface InterManageDao {
	
	/**
	 * 약국의 전체 정보를 DB에서 가져와 각각의 정보는 pharmvo에 담고 
	 * 이 vo객체를 list에 넣어 반환하는 메소드
	 * @return 회원의 전체 정보가 저장된 list객체 
	 */
	public List<PharmVO> getAllYK();
	
	/**
	 * 하나의 pvo를 이용하여 약국 정보를 update하는 메소드 
	 * @param pvo 수정할 정보가 저장된 pvo객체 
	 * @return 작업 성공:1, 실패:0
	 */
	public int updateYK(PharmVO pvo);
	
	/**
	 * 하나의 pvo를 이용하여 약국정보중 우편번호와 add1을 update하는 메소드 
	 * @param pvo 수정할 정보가 저장된 pvo객체 
	 * @return 작업성공:1, 실패 : 0
	 */
	public int updateZipYK(PharmVO pvo);
	
	
	/**
	 * update할 컬럼명과 update할 시간이 저장된 map을 매개변수로 받아서 해당데이터를 검색하여 
	 * int로 반환하는 메소드 
	 * @param updatemap update할 컬럼명과 시간이 저장될 map객체
	 * @return update 성공 : 1, 실패 : 0
	 */
	public int UpdateTimeYK(Map<String, String> updatemap);	

	/**
	 * 약국회원의 아이디를 받아서 약국의 정보를 삭제하는 메소드 
	 * @param pharmid 삭제할 약국 아이디
	 * @return 작업 성공 : 1, 실패 : 0
	 */
	public int deleteYK(String pharmid);

}
