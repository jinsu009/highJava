package yaksa.member.say.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import yaksa.member.say.vo.SayVO;



public class SayDaoImpl implements InterSayDao{

	private SqlMapClient smc; 
	private static SayDaoImpl dao;
	private SayDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	public static SayDaoImpl getInstance() {
		if(dao==null) dao = new SayDaoImpl();
		return dao;
	}
	@Override
	public List<SayVO> getAllSay() {
		List<SayVO> sList = null;
		try {
			sList = smc.queryForList("yaksasay.getAllSay");
		} catch (SQLException e) {
			sList=null; e.printStackTrace();
		}
		return sList;
	}
	
	
	
	
	
}
