package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import kr.or.ddit.board.vo.BoardVo;
import kr.or.ddit.util.BuildedSqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClient;

public class BoardDaoImpl implements BoardDaoInter{
	private SqlMapClient smc;
	private static BoardDaoImpl dao;
	
	private BoardDaoImpl(){
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static BoardDaoImpl getInstance(){
		if(dao==null) dao = new BoardDaoImpl();
		return dao;
	}
	
	@Override
	public List<BoardVo> getAllList() {
		// 메인을 처음 실행 했을때 상단에 모든 게시글을 보여주는 메소드 
		 List<BoardVo> boardList = null;
		 try {
			 //parameterclass가 없다면 (sql문 호출,파라미터값)에서 파라미터 값이 필요가 없다. 
			 boardList = smc.queryForList("jdbcboard.selectAllboard");
			} catch (SQLException e) {e.printStackTrace();}
		return boardList;
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		// 새로운 게시글을 등록하는 메소드
		int cnt = 0;
		try {
			Object obj = smc.insert("jdbcboard.insertboard",boardVo);
			if(obj==null){cnt = 1;}
			else{cnt=0;}
		}
		catch (SQLException e) { e.printStackTrace();}
		return cnt;
	}

	@Override
	public BoardVo LookBoard(int board_no) {
		//게시글의 번호를 입력해서 선택한 게시글의내용을 볼 수 있는 메소드
		BoardVo bvo = null;
		try {
			//dao에서는 하나의 명령문만 오라클과 연동해서 실행하기 때문에 대체적으로 명령문을 나눠서 실행해준다. 
			int cnt  = smc.update("jdbcboard.updatecount",board_no);
			if(cnt>0){
				bvo = (BoardVo) smc.queryForObject("jdbcboard.lookboard",board_no);
			}
		
		} catch (SQLException e) {e.printStackTrace();}
		
		return bvo;
	}
	
	@Override
	public int BoardCount(int board_no) {
		//번호를 입력한 게시글이 존재하는지 여부를 count를 세서 return해주는 메소드 
		
		int count =0;
		
		try{
			count  = (int) smc.queryForObject("jdbcboard.countboard",board_no);
			if(count==1){count=1;}
			else{count=0;}
		}catch(Exception e){ count =0; e.printStackTrace();}
		return count;
	}

	@Override
	public List<BoardVo> searchBoard(String boardtitle) {
		// 제목을 검색해서 게시글을 찾는 메소드 
		List<BoardVo> bvoList = null;
		
		try {
			bvoList = smc.queryForList("jdbcboard.searchboard",boardtitle);
		} catch (SQLException e) {e.printStackTrace();}
		
		return bvoList;
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		// 게시글을 수정하는 메소드 
		int cnt =0;
		
		try {
			cnt = smc.update("jdbcboard.editboard", boardVo);
		} catch (SQLException e) { e.printStackTrace(); } 

		return cnt;
	}

	@Override
	public int deleteBoard(int board_no) {
		//게시글을 삭제하는 메소드 
		int cnt =0;
		try {
			cnt = smc.delete("jdbcboard.deleteboard",board_no);
		} catch (SQLException e) {e.printStackTrace();}
		return cnt;
	}

	/*public List<BoardVo> getAllBoardList(){
		List<BoardVo> boardlist = null;
		try {
			boardlist = smc.queryForList("jdbcboard,selectAllboard");
		} catch (SQLException e) {e.printStackTrace();}
		return boardlist;
	}*/
}
