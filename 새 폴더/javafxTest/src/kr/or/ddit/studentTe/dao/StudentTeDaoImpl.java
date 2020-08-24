package kr.or.ddit.studentTe.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getStudentCount(String stdName) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int insertStudent(StudentTeVO stdVo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
