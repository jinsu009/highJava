package kr.or.ddit.jdbcboard.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.jdbcboard.vo.JdbcVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.BuildedSqlMapClient;

public class JdbcboardDaoImpl implements IJdbcboardDao{
	
	private SqlMapClient smc;
	
	private static JdbcboardDaoImpl dao; //싱글톤 패턴 생성
	
	private JdbcboardDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static JdbcboardDaoImpl getInstance() {
		if(dao==null) dao = new JdbcboardDaoImpl();
		return dao;
	}

	@Override
	public List<JdbcVO> searchBoard(Map<String, String> searchMap) {
		List<JdbcVO> boardList = new ArrayList<JdbcVO>();
		try {
			boardList = smc.queryForList("jdbcboard.searchBoard",searchMap);
		} catch (SQLException e) {boardList = null; e.printStackTrace();}  
		return boardList;
	}

	@Override
	public int insertBoard(JdbcVO jvo) {
		int cnt = 0;
		try{	
			Object obj = smc.insert("jdbcboard.insertBoard",jvo);
			if(obj == null){//출력문이 아닌 처리한 결과만 반환시켜준다. else는 굳이 적어주지 않아도 좋다. 
				cnt = 1;
			}
			else{cnt = 0;}
		}
			catch (SQLException e) { e.printStackTrace();}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNum) {
		int cnt=0;
		try {
			cnt = smc.delete("jdbcboard.deleteBoard", boardNum);
			
		} catch (SQLException e) {
		}
		return cnt;
	}

	@Override
	public int updateBoard(JdbcVO jvo) {
		int cnt = 0;
		try {
			//업데이트가 성공한 레코드의 수를 반환 >> 굳이 if문과 else문을 작성해줄 필요가 없나
			cnt = smc.update("jdbcboard.updateBoard",jvo);
			
		} catch (SQLException e) {e.printStackTrace();}
		return cnt;
	}

	@Override
	public List<JdbcVO> getAllBoard() {
		 List<JdbcVO> boardList = null;
			try {
				boardList = smc.queryForList("jdbcboard.getallBoardTable");
			} catch (SQLException e) { boardList= null; e.printStackTrace();}
			return boardList;
	}

	@Override
	public JdbcVO LookBoard(int boardno) {
		JdbcVO jvo = null;
		try {
			jvo  = (JdbcVO) smc.queryForList("jdbcboard.lookBoard", boardno);
		} catch (SQLException e) {e.printStackTrace();}
		
		return jvo;
	}

}
