package yaksa.member.map.service;

import java.util.List;
import java.util.Map;

import yaksa.member.map.vo.PharmVO;



public interface InterSearchService {

	/**
	 * 검색할 컬럼명과 단어가 저장될 map을 매개변수로 받아서 해당 데이터를 검색하여 
	 * list로 반환하는 메소드 
	 * @param searchMap	검색할 컬럼명과 검색할 단어가 저장된 map객체
	 * @return 검색결과를 list에 담아서 반환 
	 */
	public List<PharmVO> searchYK(Map<String, String> mapjuso);
	
	/**
	 * 데이터베이스에 저장된 약국들의 이름만을 불러와서 반환
	 * @param phname 저장된 약국이름을 반환
	 * @return 결과를 list에 담아서 반환
	 */
	public List<PharmVO> pharmName(String phname);
}
