package basic;

//20.02.05

import java.util.*;
/*
 * 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 student 클래스를 만든다. 
 * 이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 처리한다. 
 * 이 student 객체들은 list에 저장하여 관리한다. 
 * List에 저장된 데이터들을 학번의 오름차순으로 정렬(내부장랼)하여 출력하는 부분과 
 * 총점의 역순으로 정렬(외부정렬)하는 부분 프로그램하시오 
 * (그리고, 총점이 같으면 이름의 내림차순으로 정렬되도록 한다. )
 */


class Student implements Comparable<Student>{
	private int num;//학번
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int hap;	
	private int rank = 1;
	
	public Student(int num, String name, int kor, int eng, int mat)
	{
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.hap = kor+eng+mat;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	public int getHap() {
		return hap;
	}
	public void setHap(int hap) {
		this.hap = hap;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor
				+ ", eng=" + eng + ", mat=" + mat + ", hap=" + hap + ", rank="
				+ rank + "]";
	}
	public int compareTo(Student std){
		if(this.getNum() > std.getNum())
		{
			return 1;
		}
		if(this.getNum() == std.getNum())
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}
	
	
}


public class StudentTest {
	
	public static void main(String[] args)
	{
		
		ArrayList<Student> stlist = new ArrayList<>();
		
		
		stlist.add(new Student(3,"다학생",90,50,35));
		stlist.add(new Student(1,"가학생",50,50,60));
		stlist.add(new Student(5,"마학생",15,25,90));
		stlist.add(new Student(2,"나학생",80,50,70));
		stlist.add(new Student(4,"하학생",25,15,90));
		
		
		System.out.println(" ---- 정렬 전 ----");
		for(int i = 0; i<stlist.size();i++)
		{
			System.out.println(stlist.get(i));
		}
		
		//학번의 오름차순으로 정렬
		System.out.println(" ---- 학번 정렬 ---- ");
		Collections.sort(stlist);
		for(Student std : stlist)
		{
			System.out.println(std);
		}
		
		//총점의 역순으로 정렬 (동점일경우 이름의 내림차순으로 정렬)
		System.out.println(" ---- 총점 역순 ---- ");
		Collections.sort(stlist, new Desc0());
		for(int i = 0; i<stlist.size();i++)
		{
			for(int j = 1 ; j<stlist.size();j++)
			{
				if(stlist.get(i).getHap()<stlist.get(j).getHap())
				{
					
					stlist.get(i).setRank(stlist.get(i).getRank()+1);
				}
			}
		}
		
		for(int i = 0; i<stlist.size();i++)
		{
			System.out.println(stlist.get(i));
		}
		
		
	}

}

class Desc0 implements Comparator<Student>
{

	@Override
	public int compare(Student s1, Student s2) {
		if(s1.getHap() > s2.getHap())
		{
			return 1;
		}
		if(s1.getHap() < s2.getHap())
		{
			return -1;
		}
		else
		{
			return s1.compareTo(s2);
		}
	}
	
}