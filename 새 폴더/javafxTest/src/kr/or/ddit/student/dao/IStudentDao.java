package kr.or.ddit.student.dao;

import java.util.List;

import kr.or.ddit.student.vo.StudentVO;

public interface IStudentDao {

	/**
	 * StudentVO에 담겨진 DB에 insert하는 메소드 
	 * @param stuVO DB에 insert할 자료가 저장된 StudentVO객체
	 * @return DB작업 성공 : 1, 실패 : 0
	 */
	public int insertStudent(StudentVO stuVO);
	
	/**
	 * 전체 학생들의 정보를 DB에서 가져와 각각의 정보는 StudentVO에 담고,
	 * 이 StudentVO 객체를 list에 넣어서 반환하는 메소드 
	 * @return 전체회원정보(StudentVO)가 저장된 list객체
	 */
	public List<StudentVO> getAllStudent();
	
	/**
	 * 학생들의 이름을 매개변수로 받아 해당 회원의 인원수를 반환하는 메소드 
	 * @param stunm 검색할 학생 이름 
	 * @return 검색된 학생의 수
	 */
	public int getStuCount(String stunm);
}
