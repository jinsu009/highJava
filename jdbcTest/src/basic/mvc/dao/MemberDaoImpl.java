package basic.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import util.DBUtil;
import util.DBUtil3;
import basic.mvc.vo.MemberVO;

//20.02.25--08
public class MemberDaoImpl implements IMemberDao{

	//싱글톤 패턴 생성 
	private static MemberDaoImpl dao;
	//기본생성자 생성 
	private MemberDaoImpl(){}
	
	//자기 자신을 참조값으로 한 메소드 생성? 
	public static MemberDaoImpl getInstance(){
		if(dao==null) dao = new MemberDaoImpl();
		return dao;
	}
	
	
	
	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt=0;
				try{	
			
				conn = DBUtil.getConnection();
				String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr)"
						+ "values(?,?,?,?)";
				// insert 조건문을 작성해주고 맞는 value값을 넣어준다.
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memVo.getMem_id());
				pstmt.setString(2, memVo.getMem_name());
				pstmt.setString(3, memVo.getMem_tel());
				pstmt.setString(4, memVo.getMem_addr());
				// (value, 값이 들어간 변수)
				cnt = pstmt.executeUpdate(); // sql문을 수행하는 부분
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
	public int deleteMember(String memId) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt=0;
		try {
			conn = DBUtil.getConnection();
			
			String sql2 = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt =0;
			e.printStackTrace();

		} finally {if (rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();	}}

			if (stmt != null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}

			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}

			if (conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}

		}

		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update mymember set mem_name=?, mem_tel=?, mem_addr=? where mem_id=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memVo.getMem_name());
			pstmt.setString(2, memVo.getMem_tel());
			pstmt.setString(3, memVo.getMem_addr());
			pstmt.setString(4, memVo.getMem_id());
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
	public int getMemberCount(String memId) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {

			conn = DBUtil.getConnection();
			String sql2 = "select count(*) cnt from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			if (rs.next()) {count = rs.getInt("cnt");}

		} catch (Exception e) {count = 0;e.printStackTrace();} 
		finally {if (rs != null) {try {rs.close();	} catch (SQLException e) {e.printStackTrace();	}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {	e.printStackTrace();}	}
			if (conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}}
		return count;

	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		 List<MemberVO> memList = new ArrayList<>();
		 
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from mymember";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MemberVO memVo = new MemberVO();
				
				memVo.setMem_id(rs.getString("mem_id"));
				memVo.setMem_name(rs.getString("mem_name"));
				memVo.setMem_tel(rs.getString("mem_tel"));
				memVo.setMem_addr(rs.getString("mem_addr"));

				// 데이터가 담겨진 vo객체를 list에 추가한다. 
				memList.add(memVo);
			}

		} catch (SQLException e) { memList= null; e.printStackTrace();}
		finally {if (rs != null) {	try {rs.close();} catch (SQLException e) {e.printStackTrace();}	}

			if (stmt != null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}

			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}

			if (conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}

		return memList;
	}

	@Override
	public int updateMember(Map<String, String> paramMap) {
		
		//매개변수 paramMap의 구조 
		//key 	value
		//field 수정할 필드명
		//data  수정할 값 
		//memId 수정할 회원 ID
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt =0;
		
		try{
			conn = DBUtil.getConnection();
					String sql1 = "update mymember set " + paramMap.get("field")+"=? where mem_id=?";
					pstmt = conn.prepareStatement(sql1);
					pstmt.setString(1, paramMap.get("data"));
					pstmt.setString(2, paramMap.get("memId"));
					

					cnt = pstmt.executeUpdate();		
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
		if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}

		if (conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
	}
		return cnt;
	}

	@Override
	public List<MemberVO> searchMember(Map<String, String> searchMap) {
		// map의 구조 
		// key 		value
		// field 	검색할 컬럼명
		// search   검색할 내용
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> memList = new ArrayList<>();
		
		try{
			conn = DBUtil3.getConnection();
			String sql = "select * from mymember where " + searchMap.get("field")
					+ " like '%' || ? || '%'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchMap.get("search"));
			
			rs =pstmt.executeQuery();
			
			while(rs.next())
			{
				MemberVO mvo = new MemberVO();
				mvo.setMem_id(rs.getString("mem_id"));
				mvo.setMem_name(rs.getString("mem_name"));
				mvo.setMem_tel(rs.getString("mem_tel"));
				mvo.setMem_addr(rs.getString("mem_addr"));
				
				memList.add(mvo);
				
			}
			
		} catch (SQLException e) {memList = null; e.printStackTrace();} 
		finally {
		if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
		if (conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		if (rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
	}
		
		return memList;
	}

}
