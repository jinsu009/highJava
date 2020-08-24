package kr.or.ddit.jdbcboard.service;

import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.jdbcboard.dao.JdbcboardDaoImpl;
import kr.or.ddit.jdbcboard.vo.JdbcVO;
import kr.or.ddit.util.BuildedSqlMapClient;

public class JdbcboardServiceImpl implements IJdbcboardService{

	private SqlMapClient smc;
	
	private static JdbcboardDaoImpl dao;
	
	private static JdbcboardServiceImpl service;
	

	
	private JdbcboardServiceImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static JdbcboardServiceImpl getInstance() {
		if(service==null) service = new JdbcboardServiceImpl();
		dao = JdbcboardDaoImpl.getInstance();
		return service;
	}

	@Override
	public List<JdbcVO> searchBoard(Map<String, String> searchMap) {
		// TODO Auto-generated method stub
		return dao.searchBoard(searchMap);
	}

	@Override
	public int insertBoard(JdbcVO jvo) {
		// TODO Auto-generated method stub
		return dao.insertBoard(jvo);
	}

	@Override
	public int deleteBoard(int boardNum) {
		// TODO Auto-generated method stub
		return dao.deleteBoard(boardNum);
	}

	@Override
	public int updateBoard(JdbcVO jvo) {
		// TODO Auto-generated method stub
		return dao.updateBoard(jvo);
	}

	@Override
	public List<JdbcVO> getAllBoard() {
		// TODO Auto-generated method stub
		return dao.getAllBoard();
	}

	@Override
	public JdbcVO LookBoard(int boardno) {
		// TODO Auto-generated method stub
		return dao.LookBoard(boardno);
	}
	
	
}
