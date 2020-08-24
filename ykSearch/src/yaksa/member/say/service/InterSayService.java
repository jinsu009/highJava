package yaksa.member.say.service;

import java.util.List;

import yaksa.member.say.vo.SayVO;

public interface InterSayService {
	/**
	 * 명언의 모든 정보를 반환
	 * @return
	 */
	public List<SayVO> getAllSay();
	
	
}
