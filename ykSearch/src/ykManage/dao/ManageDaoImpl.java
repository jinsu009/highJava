package ykManage.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import ykManage.vo.PharmVO;

public class ManageDaoImpl implements InterManageDao{
	// ibatis 환경 설정
	private SqlMapClient smc; 
	// 싱글톤 패턴 생성
	private static ManageDaoImpl dao; 
	
	private ManageDaoImpl() {
		smc =  BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static ManageDaoImpl getInstance() {
		if(dao==null) dao = new ManageDaoImpl();
		return dao;
	}

	@Override
	public List<PharmVO> getAllYK() {
		List<PharmVO> pList = null;
		try {
			pList = smc.queryForList("yaksamanage.lookYaksa");
		} catch (SQLException e) {
			pList=null; e.printStackTrace();
		}
		return pList;
	}

	@Override
	public int updateYK(PharmVO pvo) {
		int cnt = 0;
		try {
			cnt = smc.update("yaksamanage.updateYK",pvo);
		} catch (SQLException e) {e.printStackTrace();}
		return cnt;
	}
	
	
	@Override
	public int updateZipYK(PharmVO pvo) {
		int cnt = 0;
		try {
			cnt = smc.update("yaksamanage.updateZipYK",pvo);
		} catch (SQLException e) {e.printStackTrace();}
		return cnt;
	}

	@Override
	public int UpdateTimeYK(Map<String, String> updatemap) {
		int cnt = 0; 
		try {
			cnt = smc.update("yaksamanage.updateYKTime",updatemap);
		} catch (SQLException e) { e.printStackTrace(); }
		return cnt;
	}

	@Override
	public int deleteYK(String pharmid) {
		int cnt = 0;
		try {
			cnt = smc.delete("yaksamanage.deleteYK",pharmid);
		} catch (SQLException e) { }
		return cnt;
	}

	

}
