package basic;
//20.02.18 과제 
//20.02.20 -- 01 (과제 풀이)

/*
'd:/d_Other/tiger.jpg'파일을
'd:/d_Other/연습용'폴더에 복사하는 프로그램을 작성하시오.

*/

import java.io.*;

public class QuestFileCopy0220 {

	
	public static void main(String[] args) {
		File sourceFile = new File("D:\\D_Other/tiger.jpg");
		
		if(!sourceFile.exists())
		{
			System.out.println(sourceFile.getPath() + " 는 없는 파일 입니다 . 작업 종료 ");
			return;
		}
		File targetFile = new File("D:\\D_Other/연습용/" + sourceFile.getName());
		
		try {
			// 복사할 입력용 파일 스트림 객체 생성
			FileInputStream fin = new FileInputStream(sourceFile);
			BufferedInputStream bin = new BufferedInputStream(fin); //속도를 빠르게? ,, while문 주석
			
			// 복사될 출력용 파일 스트림 객체 생성 
			FileOutputStream fout = new FileOutputStream(targetFile);
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			//버퍼 크기 : 8kbyte
			
			int c;
			
			System.out.println("파일 복사 시작...");
			
//			while((c=fin.read())!=-1){
//				fout.write(c);
//			}
			
			//buffer용
			while((c=fin.read())!=-1){
				bout.write(c);
			}
			
			System.out.println("파일 복사 완료!!");
//			fin.close();
//			fout.close();
			
			//버퍼용 
			bin.close();
			bout.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
