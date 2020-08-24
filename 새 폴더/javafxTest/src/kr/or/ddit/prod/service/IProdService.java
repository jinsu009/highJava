package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.prod.vo.LprodVO;
import kr.or.ddit.prod.vo.ProdVO;

public interface IProdService {
	
	/**
	 * 왼쪽의 콤보박스 내용을 불러오는 메소드 lprod의 정보를 불러온다. 
	 * @param searchMap_Lprod 검색할 컬럼명과 검색할 단어가 저장된 Map 객체 
	 * @return 검색결과를 List에 담아서 반환
	 */
	public List<LprodVO> searchLprod();
		
	/**
	 * 오른쪽의 콤보박스 내용을 불러오는 메소드 prod의 정보를 불러온다. 
	 * @param searchMap_Prodid 검색할 컬럼명과 검색할 단어가 저장된 Map객체 
	 * @return 검색결과를 List에 담아서 반환
	 */
	public List<ProdVO> searchProdLgu(String searchMap_Prodlgu);
	
	/**
	 * 테이블에 내용을 불러오는 메소드 prod에서 선택된 lgu의 해당값들을 불러온다. 
	 * @param searchMap_ProdId 검색할 컬럼명과 검색할 단어가 저장된 Map 객체 
	 * @return 검색결과를 List에 담아서 반환
	 */
	public List<ProdVO> searchPordId(String searchMap_ProdId);

}
