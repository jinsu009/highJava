package ykMap.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import ykMap.vo.PharmVO;



public class SearchDaoImpl implements InterSearchDao{

	private SqlMapClient smc; 
	private static SearchDaoImpl dao;
	private SearchDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	public static SearchDaoImpl getInstance() {
		if(dao==null) dao = new SearchDaoImpl();
		return dao;
	}
	
	@Override
	public List<PharmVO> searchYK(Map<String, String> mapjuso) {
		List<PharmVO> pList = new ArrayList<PharmVO>();
		try {
			pList = smc.queryForList("yaksasearch.searchYK",mapjuso);
		} catch (SQLException e) { pList = null; e.printStackTrace(); }
		
		return pList;
	}
	
	@Override
	public List<PharmVO> pharmName(String phname) {
		List<PharmVO> pList = new ArrayList<PharmVO>();
		try {
			pList = smc.queryForList("yaksasearch.pharmNM", phname);
		} catch (SQLException e) { pList = null; e.printStackTrace(); }
		return pList;
	}
	
}
