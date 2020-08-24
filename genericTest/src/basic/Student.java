package basic;

import java.util.*;

//20.02.11-과제

/*
 * 학생이름과 여러과목의 점수를 매개변수로 받아서 그학생의 평균과 평점을 구한후
 * 학생이름, 각과목 점수 , 평균, 평점(a학점 b학점...)을 출력하는 메소드를 만드시오 
 * 단, 각 학생별로 시험과목수가 일정하지 않다. 
 */


public class Student {
	
	public void stu(String stuname, int...subjum)
	{
		int hap = 0;
		int avg = 0;
		for(int i = 0 ; i<subjum.length;i++)
		{
			hap += subjum[i];
		}
		
		avg /= subjum.length;
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		
	}	
	
	
}
