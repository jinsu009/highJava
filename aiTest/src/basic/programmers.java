package basic;

import java.util.*;

public class programmers {

	public static void main(String[] args) {
//		solution("862195","64723");

		boolean a = false;
		
		String z = "862195";
		String y = "64723";

		int[] pw = new int[5];
		int[] an = new int[5];
		int p = Integer.parseInt(z);
//			int s = Integer.parseInt(y);
		do{
			
			System.out.println(p%10);
//			System.out.println(s%10);
			
			p = p/10;
			
			for(int i=0; i<5; i++) {
				pw[i] = p%10;
//				an[i] = s%10;
			}
			a=true;
		}while(a);
		for(int i=0; i<pw.length; i++) {
//			System.out.println("an="+an[i]);
		System.out.println("pw="+pw[i]);
		}
		
		
//		int zz = Integer.parseInt(z);
//		int xx = zz%10;
//	
//		int yy = Integer.parseInt(y);
//		int xxx = yy%10;
//		
//		int[] pw = new int[5];
//		int[] an = new int[5];
//		
//		for(int i=pw.length; i<=0; i--) {
//			pw[i]=xx;
//			an[i]=xxx;
//		}
//		
//		for(int i =0; i<pw.length;i++) {
//			System.out.println(pw[i]);
//		}
//		
//		int cnt = 0;
//		
////		int[] pw = {8,2,1,9,5};
////		int[] an = {6,4,7,2,3};
//		int answer[]= new int[5];
//		
//		for(int i=0; i<an.length;i++) {
//	
//		int a = pw[i];
//		int b = an[i];
//		int c = Math.abs(a-b);
//		int d = 10-(Math.abs(b-a));
//			if(c<d) {
//				cnt=c;
//				answer[i]=cnt;
////				cnt++;
//			}else {
//				cnt=d;
//				answer[i]=cnt;
////				cnt++;
//			}
//		}
//		int e=0;
//		for(int i=0;i<answer.length;i++) {
//			
//			System.out.println(answer[i]);
//			e += answer[i];
//		}
//		System.out.println("전체 경우의 핪 :"+e);
////		System.out.println(cnt);
//	}
//	
//	public static int solution(String p, String s) {
//		int answer = 13;
//		
//		return answer;
//	}
}
}