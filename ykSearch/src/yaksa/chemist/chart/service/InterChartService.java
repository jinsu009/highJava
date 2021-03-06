package yaksa.chemist.chart.service;

import java.util.List;

import yaksa.chemist.chart.vo.PharmPresVO;
import yaksa.chemist.chart.vo.PharmVO;




public interface InterChartService {
	
	/**
	 * 약국의 전체 정보를 DB에서 가져와 각각의 정보는 pharmvo에 담고 
	 * 이 vo객체를 list에 넣어 반환하는 메소드
	 * @return 회원의 전체 정보가 저장된 list객체 
	 */
	public List<PharmVO> getAllYK();
	
	/**
	 * 
	 * @return
	 */
	public int womancnt();
	
	/**
	 * 
	 * @return
	 */
	public int mancnt();
	
	
	/**
	 * 
	 * @return
	 */
	public int onecnt();
	
	/**
	 * 
	 * @return
	 */
	public int twocnt();
	
	/**
	 * 
	 * @return
	 */
	public int thrcnt();
	
	/**
	 * 
	 * @return
	 */
	public int fourcnt();
	
	/**
	 * 
	 * @return
	 */
	public int fivecnt();
	
	/**
	 * 
	 * @return
	 */
	public int monthcnt(int a);
	
	
	//준우통께
	public List<PharmPresVO> ykvisit(String a);

}
