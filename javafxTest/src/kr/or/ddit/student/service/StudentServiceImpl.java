package kr.or.ddit.student.service;

import java.util.List;

import kr.or.ddit.student.dao.IStudentDao;
import kr.or.ddit.student.dao.StudentDaoImpl;
import kr.or.ddit.student.vo.StudentVO;

public class StudentServiceImpl implements IStudentService{
	
	private IStudentDao dao;
	private static StudentServiceImpl service;
	
	private StudentServiceImpl() {
		dao = StudentDaoImpl.getInstance();
	}
	public static StudentServiceImpl getInstance() {
		if(service==null) {
			service = new StudentServiceImpl();
		}
		return service;
	}

	@Override
	public int insertStudent(StudentVO stuVO) {
		// TODO Auto-generated method stub
		return dao.insertStudent(stuVO);
	}

	@Override
	public List<StudentVO> getAllStudent() {
		// TODO Auto-generated method stub
		return dao.getAllStudent();
	}
	@Override
	public int getStuCount(String stunm) {
		// TODO Auto-generated method stub
		return dao.getStuCount(stunm);
	}

}
