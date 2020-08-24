package basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//20.02.18 과제 

/*
 'd:/d_Other/tiger.jpg'파일을
 'd:/d_Other/연습용'폴더에 복사하는 프로그램을 작성하시오.

 */

public class Quest0218 {

	public static void main(String[] args) throws IOException {
		// 연습용 폴더 생성
		File file = new File("d:/d_other/연습용");
		if (file.mkdirs()) {
			System.out.println(file.getName() + " 폴더만들기 성공");
		} else {
			System.out.println(file.getName() + " 폴더만들기 실패");
		}
		System.out.println();

		// 이미지 파일 불러오기
		// 파일 저장하기
		
		try {
			FileInputStream fr = new FileInputStream("d:/d_other/tiger.jpg");
			FileOutputStream isr = new FileOutputStream(new File("d:/d_other/연습용/tiger.jpg"));
			int c;
			while((c = fr.read()) != -1){
				isr.write(c);
			}
			fr.close();
			isr.close();
			System.out.println("성공");
		} catch (IOException e) {
			System.out.println("실패");
			// TODO: handle exception
		}
	}
}
