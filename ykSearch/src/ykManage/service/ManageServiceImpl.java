package ykManage.service;

import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import ykManage.dao.ManageDaoImpl;
import ykManage.vo.PharmVO;



public class ManageServiceImpl implements InterManageService{
	
	private SqlMapClient smc;
	private static ManageDaoImpl dao;
	private static ManageServiceImpl service;
	
	private ManageServiceImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	public static ManageServiceImpl getInstance() {
		if(service==null) service = new ManageServiceImpl();
		dao = ManageDaoImpl.getInstance();
		return service;
	}
	
	@Override
	public List<PharmVO> getAllYK() {
		return dao.getAllYK();
	}
	@Override
	public int updateYK(PharmVO pvo) {
		return dao.updateYK(pvo);
	}
	@Override
	public int UpdateTimeYK(Map<String, String> updatemap) {
		return dao.UpdateTimeYK(updatemap);
	}
	@Override
	public int deleteYK(String pharmid) {
		return dao.deleteYK(pharmid);
	}
	@Override
	public int updateZipYK(PharmVO pvo) {
		return dao.updateZipYK(pvo);
	}

}
