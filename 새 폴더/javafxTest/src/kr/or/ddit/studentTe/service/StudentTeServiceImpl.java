package kr.or.ddit.studentTe.service;

import java.util.List;

import kr.or.ddit.student.service.StudentServiceImpl;
import kr.or.ddit.studentTe.dao.IStudentTeDao;
import kr.or.ddit.studentTe.dao.StudentTeDaoImpl;
import kr.or.ddit.studentTe.vo.StudentTeVO;

public class StudentTeServiceImpl implements IStudentTeService{
	private static StudentTeServiceImpl service;
	private IStudentTeDao dao;
	
	private StudentTeServiceImpl() {
		dao = StudentTeDaoImpl.getInstance();
	}
	public static StudentTeServiceImpl getInstance() {
		if(service==null) service = new StudentTeServiceImpl();
		return service;
	}
	@Override
	public List<StudentTeVO> getAllStudentList() {
		// TODO Auto-generated method stub
		return dao.getAllStudentList();
	}
	@Override
	public int getStudentCount(String stdName) {
		// TODO Auto-generated method stub
		return dao.getStudentCount(stdName);
	}
	@Override
	public int insertStudent(StudentTeVO stdVo) {
		// TODO Auto-generated method stub
		return dao.insertStudent(stdVo);
	}
	
}
