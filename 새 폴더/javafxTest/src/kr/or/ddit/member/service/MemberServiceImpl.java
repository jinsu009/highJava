package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

//20.02.25--09
public class MemberServiceImpl implements IMemberService {
	private IMemberDao dao; //dao객체가 저장될 변수 
	
	private static MemberServiceImpl service;
	//생성자 
	private MemberServiceImpl(){
	
	//public MemberServiceImpl() {
		//dao = new MemberDaoImpl();//dao 객체 생성
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance(){
		if(service==null) service = new MemberServiceImpl();
		return service;
	}
	
	@Override
	public int insertMember(MemberVO memVo) {
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		return dao.updateMember(memVo);
	}

	@Override
	public int getMemberCount(String memId) {
		return dao.getMemberCount(memId);
	}

	@Override
	public List<MemberVO> getAllMember() {
		return dao.getAllMember();
	}

	@Override
	public int updateMember(Map<String, String> paramMap) {
		return dao.updateMember(paramMap);
	}

	@Override
	public List<MemberVO> searchMember(Map<String, String> searchMap) {
		// TODO Auto-generated method stub
		return dao.searchMember(searchMap);
	}

	@Override
	public int getAllMemberCount() {
		// TODO Auto-generated method stub
		return dao.getAllMemberCount();
	}

	@Override
	public List<MemberVO> getPagingMemberList(Map<String, Integer> pageMap) {
		// TODO Auto-generated method stub
		return dao.getPagingMemberList(pageMap);
	}

}
