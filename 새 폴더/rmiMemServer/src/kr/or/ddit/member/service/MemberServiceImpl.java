package kr.or.ddit.member.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

//20.02.25--09
public class MemberServiceImpl extends UnicastRemoteObject  implements IMemberService {
	private IMemberDao dao; //dao객체가 저장될 변수 
	
	private static MemberServiceImpl service;
	//생성자 
	private MemberServiceImpl() throws RemoteException {
	
	//public MemberServiceImpl() {
		//dao = new MemberDaoImpl();//dao 객체 생성
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance(){
			try {
				if(service==null) service = new MemberServiceImpl();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return service;
	}
	
	@Override
	public int insertMember(MemberVO memVo) throws RemoteException {
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) throws RemoteException {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) throws RemoteException {
		return dao.updateMember(memVo);
	}

	@Override
	public int getMemberCount(String memId) throws RemoteException {
		return dao.getMemberCount(memId);
	}

	@Override
	public List<MemberVO> getAllMember() throws RemoteException {
		return dao.getAllMember();
	}

	@Override
	public int updateMember(Map<String, String> paramMap) throws RemoteException {
		return dao.updateMember(paramMap);
	}

	@Override
	public List<MemberVO> searchMember(Map<String, String> searchMap) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.searchMember(searchMap);
	}

	@Override
	public int getAllMemberCount() throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getAllMemberCount();
	}

	@Override
	public List<MemberVO> getPagingMemberList(Map<String, Integer> pageMap) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getPagingMemberList(pageMap);
	}

}
