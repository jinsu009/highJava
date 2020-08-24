package yaksa.member.say.dao;

import java.util.List;
import java.util.Map;

import yaksa.member.say.vo.SayVO;

public interface InterSayDao {

	/**
	 * 명언의 모든 정보를 반환
	 * @return
	 */
	public List<SayVO> getAllSay();
	
}
