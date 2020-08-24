package ykChart.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import ykChart.vo.PharmVO;


public class ChartDaoImpl implements InterChartDao{
	
	private SqlMapClient smc;
	private static ChartDaoImpl dao;
	private ChartDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	public static ChartDaoImpl getInstance() {
		if(dao==null) dao = new ChartDaoImpl();
		return dao;
	}
	
	@Override
	public List<PharmVO> getAllYK() {
		List<PharmVO> pList = null;
		try {
			pList = smc.queryForList("yaksachart.getAllYK");
		} catch (SQLException e) {
			pList=null; e.printStackTrace();
		}
		return pList;
	}
	@Override
	public int womancnt() {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("yaksachart.womancnt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public int mancnt() {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("yaksachart.mancnt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public int onecnt() {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("yaksachart.onecnt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public int twocnt() {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("yaksachart.twocnt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public int thrcnt() {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("yaksachart.thrcnt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public int fourcnt() {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("yaksachart.fourcnt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public int fivecnt() {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("yaksachart.fivecnt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	

}
