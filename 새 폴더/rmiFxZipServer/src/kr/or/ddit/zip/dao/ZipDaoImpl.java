package kr.or.ddit.zip.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.BuildedSqlMapClient;
import kr.or.ddit.zip.vo.ZipVO;

public class ZipDaoImpl implements IZipDao{
	
	private SqlMapClient smc; //ibaits를 사용하기 위한 객체 변수 선언
	
	private static ZipDaoImpl dao; 
	
	public ZipDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static ZipDaoImpl getInstance() {
		if(dao==null) dao= new ZipDaoImpl();
		return dao;
	}

	//우편번호로 찾는 메소드 
	@Override
	public List<ZipVO> searchZip(String searnum) {
		List<ZipVO> zipList = new ArrayList<ZipVO>();
		try {
			zipList = smc.queryForList("zip.numsearch",searnum);
		} catch (SQLException e) {
			zipList = null; e.printStackTrace();
		}
		return zipList;
	}

	//동으로 찾는 메소드
	@Override
	public List<ZipVO> searchDong(String Dong) {
		List<ZipVO> zipList = new ArrayList<ZipVO>();
		try {
			zipList = smc.queryForList("zip.dongsearch",Dong);
		} catch (SQLException e) {
			zipList = null; e.printStackTrace();
		}
		return zipList;
	}

	
}
