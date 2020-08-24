package basic;
//20.02.11 - 과제 선생님

/*
 * 학생이름과 여러과목의 점수를 매개변수로 받아서 그학생의 평균과 평점을 구한후
 * 학생이름, 각과목 점수 , 평균, 평점(a학점 b학점...)을 출력하는 메소드를 만드시오 
 * 단, 각 학생별로 시험과목수가 일정하지 않다. 
 */

public class StudentTest {
	
	public void calcScore(String name, int...scores)
	{
		int hap = 0;
		for(int i = 0; i<scores.length;i++)
		{
			hap += scores[i];
		}
		
		double avg = (double)hap / scores.length;
		
		String grade = "";
		
		if(avg>=90)
		{
			grade = "A";
		}
		else if(avg >= 80)
		{
			grade = "B";
		}
		else if(avg >= 70)
		{
			grade = "c";
		}
		else if(avg >= 60)
		{
			grade = "D";
		}
		else
		{
			grade = "F";
		}
		
		System.out.print(name + "\t");
		
		for(int score:scores)
		{
			System.out.print(score + "\t");
		}
		System.out.println(avg + "\t" + grade);
		
	}
	
	public static void main(String[] args)
	{
		StudentTest test = new StudentTest();
		
		test.calcScore("강아지", 100,90,80,40,10);
		test.calcScore("고양이", 50,100,90,80,40,10);
	}

}
