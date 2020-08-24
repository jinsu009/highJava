package kr.or.ddit.prod.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.prod.vo.LprodVO;
import kr.or.ddit.prod.vo.ProdVO;
import kr.or.ddit.util.BuildedSqlMapClient;

public class ProdDaoImpl implements IProdDao{
	
	private SqlMapClient smc; // ibatis용 객체 변수 선언 
	
	private static ProdDaoImpl dao;
	
	private ProdDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static ProdDaoImpl getInstance() {
		if(dao==null) {
			dao = new ProdDaoImpl();
		}
		return dao;
	}

	@Override
	public List<LprodVO> searchLprod() {

		List<LprodVO> lprodlist = null;
		try {
			lprodlist = smc.queryForList("prodser.serlprod");
		} catch (SQLException e) {
			lprodlist = null; e.printStackTrace();
		}
		
		return lprodlist;
	}

	@Override
	public List<ProdVO> searchProdLgu(String searchMap_Prodlgu) {
		
		List<ProdVO> prodlistlgu = new ArrayList<ProdVO>();
		try {
			prodlistlgu = smc.queryForList("prodser.serprodlgu",searchMap_Prodlgu);
		} catch (SQLException e) {
			prodlistlgu=null; e.printStackTrace();
		}
		return prodlistlgu;
	}

	@Override
	public List<ProdVO> searchPordId(String searchMap_ProdId) {
		List<ProdVO> prodlistid = new ArrayList<ProdVO>();
		try {
			prodlistid = smc.queryForList("prodser.serprodid",searchMap_ProdId);
		} catch (SQLException e) {
			prodlistid = null; e.printStackTrace();
		}
		return prodlistid;
	}
	

	


}
