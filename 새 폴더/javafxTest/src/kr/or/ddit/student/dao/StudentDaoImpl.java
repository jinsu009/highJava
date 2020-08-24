package kr.or.ddit.student.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.student.vo.StudentVO;
import kr.or.ddit.util.BuildedSqlMapClient;

public class StudentDaoImpl implements IStudentDao{
	
	private SqlMapClient smc;
	
	private static StudentDaoImpl dao;
	
	private StudentDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static StudentDaoImpl getInstance() {
		if(dao==null) dao = new StudentDaoImpl();
		return dao;
	}

	@Override
	public int insertStudent(StudentVO stuVO) {
		int cnt =0;
		try {
			Object obj = smc.insert("student.insertstudent",stuVO);
			if(obj == null ) {
				cnt =1;
			}else {
				cnt=0;
			}
		} catch (SQLException e) { e.printStackTrace();}

		return cnt;
	}

	@Override
	public List<StudentVO> getAllStudent() {
		List<StudentVO> stuList = null;
		try {
			stuList = smc.queryForList("student.getAllStudent");
		}  catch (SQLException e) { stuList= null; e.printStackTrace();}
		return stuList;
	}

	@Override
	public int getStuCount(String stunm) {
		int cnt = 0;
		
		try {
			cnt = (int)smc.queryForObject("student.countstudent");
		} catch (SQLException e) {
			cnt =0;
			e.printStackTrace();
		}
		return cnt;
		
	}
	
	

}
