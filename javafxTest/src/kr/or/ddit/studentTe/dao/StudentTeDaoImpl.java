package kr.or.ddit.studentTe.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.student.vo.StudentVO;
import kr.or.ddit.studentTe.vo.StudentTeVO;
import kr.or.ddit.util.BuildedSqlMapClient;

public class StudentTeDaoImpl implements IStudentTeDao{
	private static StudentTeDaoImpl dao;
	private SqlMapClient smc;
	
	private StudentTeDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	public static StudentTeDaoImpl getInstance() {
		if(dao==null) dao = new StudentTeDaoImpl();
		return dao;
	}
	@Override
	public List<StudentTeVO> getAllStudentList() {
		
		List<StudentTeVO> stuList = null;
		try {
			stuList = smc.queryForList("student.getAllStudent");
		}  catch (SQLException e) { stuList= null; e.printStackTrace();}
		return stuList;
	}
	@Override
	public int getStudentCount(String stdName) {
		int cnt = 0;
		
		try {
			cnt = (int)smc.queryForObject("student.countstudent");
		} catch (SQLException e) {
			cnt =0;
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public int insertStudent(StudentTeVO stdVo) {
		int cnt =0;
		try {
			Object obj = smc.insert("student.insertstudent",stdVo);
			if(obj == null ) {
				cnt =1;
			}else {
				cnt=0;
			}
		} catch (SQLException e) { e.printStackTrace();}

		return cnt;
	}

}
