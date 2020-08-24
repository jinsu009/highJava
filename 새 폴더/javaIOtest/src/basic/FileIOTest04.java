package basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


//20.02.18--08

public class FileIOTest04 {
	public static void main(String[] args) {
		//사용자가 입력한 내용을 그대로 파일로 저장하기 
		
		Scanner s = new Scanner(System.in);
		
		// 콘솔(표준입출력장치)과 연결된 입력문자 스트림 생성
		InputStreamReader isr = new InputStreamReader(System.in);
		//InputStreamReader ==> 바이트 기반의 스트림을 문자 기반의 스트림으로 변환해주는 스트림
		
		try {
			//파일 출력용 문자 기반 스트림 객체 생성
			FileWriter fw = new FileWriter("d:/d_other/testChar.txt");
			
			int c;
			System.out.println("아무 내용이나 입력하세요 ");
			
			
			// 콘솔에서 입력할 때 입력의 끝 표시는 'ctrl+z' 키를 누른다.
			while((c=isr.read())!=-1){
				fw.write(c); //콘솔에서 입력받은 값들을 파일에 출력 
				
			}
			
			isr.close();fw.close();
		} catch (IOException e) {
			System.out.println("입출력 오류");
			e.printStackTrace();
		}
	}
}