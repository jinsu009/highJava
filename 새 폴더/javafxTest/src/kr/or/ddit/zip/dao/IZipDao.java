package kr.or.ddit.zip.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.zip.vo.ZipVO;

public interface IZipDao {
	
	/**
	 *  우편번호로 검색한 결과 
	 *  검색할 컬럼명과 검색할 단어가 저장된 Map을 매개변수로 받아서 해당 데이터를 검샣가여 list로 반환하는 메소드
	 * @param searchMap 검색할 컬럼명과 검색할 단어가 저장된 map객체
	 * @return 검색결과를 list에 담아서 반환
	 */
	public List<ZipVO> searchZip(String searnum);
	
	/**
	 * 동이름으로 검색한 결과 
	 * 검색할 컬럼명과 검색할 단어가 저장된 Map을 매개변수로 받아서 해당 데이터를 검샣가여 list로 반환하는 메소드
	 * @param searchMap 검색할 컬럼명과 검색할 단어가 저장된 map객체
	 * @return
	 */
	public List<ZipVO> searchDong(String Dong);


}
