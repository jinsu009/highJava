package yaksa.chemist.chart.service;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import yaksa.chemist.chart.dao.ChartDaoImpl;
import yaksa.chemist.chart.vo.PharmPresVO;
import yaksa.chemist.chart.vo.PharmVO;


public class ChartServiceImpl implements InterChartService{
	
	private SqlMapClient smc;
	private static ChartDaoImpl dao;
	private static ChartServiceImpl service;
	
	private ChartServiceImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	public static ChartServiceImpl getInstance() {
		if(service==null) service = new ChartServiceImpl();
		dao = ChartDaoImpl.getInstance();
		return service;
	}


	@Override
	public List<PharmVO> getAllYK() {
		// TODO Auto-generated method stub
		return dao.getAllYK();
	}
	@Override
	public int womancnt() {
		// TODO Auto-generated method stub
		return dao.womancnt();
	}
	
	@Override
	public int mancnt() {
		// TODO Auto-generated method stub
		return dao.mancnt();
	}
	@Override
	public int onecnt() {
		// TODO Auto-generated method stub
		return dao.onecnt();
	}
	@Override
	public int twocnt() {
		// TODO Auto-generated method stub
		return dao.twocnt();
	}
	@Override
	public int thrcnt() {
		// TODO Auto-generated method stub
		return dao.thrcnt();
	}
	@Override
	public int fourcnt() {
		// TODO Auto-generated method stub
		return dao.fourcnt();
	}
	@Override
	public int fivecnt() {
		// TODO Auto-generated method stub
		return dao.fivecnt();
	}
	@Override
	public int monthcnt(int a) {
		// TODO Auto-generated method stub
		return dao.monthcnt(a);
	}
	
	//준우통계
	@Override
	public List<PharmPresVO> ykvisit(String a) {
		// TODO Auto-generated method stub
		return dao.ykvisit(a);
	}
	
}
