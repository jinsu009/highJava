package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.DBUtil3;
import vo.BoardVo;

public class BoardDaoImpl implements BoardDaoInter{
	
	private static BoardDaoImpl dao;
	
	private BoardDaoImpl(){}
	
	public static BoardDaoImpl getInstance(){
		if(dao==null) dao = new BoardDaoImpl();
		return dao;
	}
	

	@Override
	public List<BoardVo> getAllList() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		 List<BoardVo> boardList = new ArrayList<>();
		 
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				
				BoardVo bvo = new BoardVo();
				
				bvo.setBoard_no(rs.getInt("board_no"));
				bvo.setBoard_title(rs.getString("board_title"));
				bvo.setBoard_content(rs.getString("board_content"));
				bvo.setBoard_writer(rs.getString("board_writer"));
				bvo.setBoard_date(rs.getString("board_date"));
				bvo.setBoard_cnt(rs.getInt("board_cnt"));
				

				// 데이터가 담겨진 vo객체를 list에 추가한다. 
				boardList.add(bvo);
			}

		} catch (SQLException e) { boardList= null; e.printStackTrace();}
		finally {if (rs != null) {	try {rs.close();} catch (SQLException e) {e.printStackTrace();}	}
			if (stmt != null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if (conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}

		return boardList;
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cnt = 0;
		
		try {
			
			conn = DBUtil3.getConnection();
			
			String sql =" insert into jdbc_board (board_no, board_title, board_writer, board_content, board_date , board_cnt) "
					+ "values(seq_board.nextval,?,?,?,sysdate,0)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());
		
			cnt = pstmt.executeUpdate();
		}
		catch (SQLException e) { e.printStackTrace();}
		finally {if (rs != null) {	try {rs.close();} catch (SQLException e) {e.printStackTrace();}	}
		if (stmt != null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
		if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
		if (conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
	}
	
		return cnt;
	}

	@Override
	public BoardVo LookBoard(int board_no) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardVo bvo = new BoardVo();
		
		int count = 0;
		try {
			//부적합한 열 이름
			conn = DBUtil3.getConnection();
			String sql2 = "update jdbc_board set board_cnt =? where board_no =?" ;
			pstmt = conn.prepareStatement(sql2);		
			pstmt.setInt(1, bvo.getBoard_cnt()+1);
			pstmt.setInt(2, board_no);
			pstmt.executeQuery();			
			pstmt.close();
			
			String sql = "select * from jdbc_board where board_no =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs= pstmt.executeQuery();	
			if(rs.next()){
				bvo.setBoard_no(rs.getInt("board_no"));
				bvo.setBoard_title(rs.getString("board_title"));
				bvo.setBoard_content(rs.getString("board_content"));
				bvo.setBoard_writer(rs.getString("board_writer"));
				bvo.setBoard_date(rs.getString("board_date"));
				bvo.setBoard_cnt(rs.getInt("board_cnt"));
			}
			
						
		} catch (SQLException e) {e.printStackTrace();}
		finally {if (rs != null) {	try {rs.close();} catch (SQLException e) {e.printStackTrace();}	}
		if (stmt != null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
		if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
		if (conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
	}
		return bvo;
	}

	@Override
	public List<BoardVo> searchBoard(BoardVo boardVo) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVo> bvoList = new ArrayList<>();
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board where board_title like '%' || ? || '%' ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				BoardVo bvo = new BoardVo();
				bvo.setBoard_no(rs.getInt("board_no"));
				bvo.setBoard_title(rs.getString("board_title"));
				bvo.setBoard_content(rs.getString("board_content"));
				bvo.setBoard_writer(rs.getString("board_writer"));
				bvo.setBoard_date(rs.getString("board_date"));
				bvo.setBoard_cnt(rs.getInt("board_cnt"));
				
				bvoList.add(bvo);
			}
			
		} catch (SQLException e) {e.printStackTrace();}
		finally {if (rs != null) {	try {rs.close();} catch (SQLException e) {e.printStackTrace();}	}
		if (stmt != null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
		if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
		if (conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
	}
		
		return bvoList;
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cnt =0;
		
		try {
			conn=DBUtil3.getConnection();
			
			String sql = "update jdbc_board set board_title=?, board_content=?, board_date = sysdate"
					+ " where board_no =?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,boardVo.getBoard_title());
			pstmt.setString(2,boardVo.getBoard_content());
			pstmt.setInt(3,boardVo.getBoard_no());
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {if (rs != null) {try {	rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if (stmt != null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();	}}
			if (conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}}

		return cnt;
	}

	@Override
	public int deleteBoard(int board_no) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt =0;
		try {
			conn=DBUtil3.getConnection();
			
			String sql = "delete from jdbc_board where board_no =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		finally {if (rs != null) {	try {rs.close();} catch (SQLException e) {e.printStackTrace();}	}
		if (stmt != null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
		if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
		if (conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
	}
		return cnt;
	}

	@Override
	public int BoardCount(int board_no) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count =0;
		
		try{
			conn = DBUtil3.getConnection();
			String sql = "select count(*) cnt from jdbc_board where board_no = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs= pstmt.executeQuery();
			if(rs.next()){count=rs.getInt("cnt");}
		}catch(Exception e){ count =0; e.printStackTrace();}
		finally {if (rs != null) {try {rs.close();	} catch (SQLException e) {e.printStackTrace();	}}
		if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {	e.printStackTrace();}	}
		if (conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}}
		return count;
	}

	
	public List<BoardVo> getAllBoardList(){
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVo> boardlist = new ArrayList<>();
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board order by board_no desc"; 
			
			rs=pstmt.executeQuery(sql);
			
			while(rs.next()){
				BoardVo bvo = new BoardVo();
				bvo.setBoard_no(rs.getInt("board_no"));
				bvo.setBoard_title(rs.getString("board_title"));
				bvo.setBoard_content(rs.getString("board_content"));
				bvo.setBoard_writer(rs.getString("board_writer"));
				bvo.setBoard_date(rs.getString("board_date"));
				bvo.setBoard_cnt(rs.getInt("board_cnt"));
			}
			
		} catch (SQLException e) {e.printStackTrace();}
		finally {if (rs != null) {	try {rs.close();} catch (SQLException e) {e.printStackTrace();}	}
		if (stmt != null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
		if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
		if (conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
	}
		return boardlist;
	}
}
