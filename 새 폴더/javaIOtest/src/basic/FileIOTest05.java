package basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//20.02.19 -- 01

/*
 인코딩을 지정해서 파일내용 읽어오기
 => InputStreamReader 객체를 이용한다. 
 */
public class FileIOTest05 {

	public static void main(String[] args) {
		File fileAnsi = new File("d:/d_other/test_ansi.txt");
		File fileUth8 = new File("d:/d_other/test_utf8.txt");

		try {
			// 기반이 되는 FileInputStream 객체를 생성 한 후 이 객체를
			// InputStreamReader 객체를 생성할 때 매개값으로 넣어서 생성한ㄷ.

			FileInputStream fin = new FileInputStream(fileAnsi);
			// FileInputStream fin = new FileInputStream(fileUth8);
			// 기반이 되는 스트림 객체 생성
			// ansi는 한글이 깨져서 출력된다.

			// 파일의 인코딩은 운영체제 와 해당 java 파일의 인코딩에 영향을 받는다.

			// InputStreamReader 객체는 파일의 인코딩 방식을 지정할 수 있따.
			// 지정하는 인코딩 문자열
			// - MS949 ==> 윈도우의 기본 한글 인코딩 방식(ansi방식)
			// - UTF-8 ==> 유니코드 utf-8 인코딩 방식
			// - US-ASCII ==> 영문 전용 인코딩 방식
			// InputStreamReader isr = new InputStreamReader(fin, "MS949");
			// //ANSI 는 깨지지않는다.
			InputStreamReader isr = new InputStreamReader(fin, "UTF-8"); // ANSI
																			// 깨짐
			// InputStreamReader isr = new InputStreamReader(fin, "US-ASCII");
			// //ANSI 깨짐
			// 보조 스트림객체 생성
			int c;

			while ((c = isr.read()) != -1) {
				System.out.print((char) c);
			}
			isr.close();
			// 보조 스트림 객체를 닫으면 기반이 되는 스트림은 자동으로 닫힌다.

		} catch (IOException e) {
			e.printStackTrace();
			;
		}

	}
}
