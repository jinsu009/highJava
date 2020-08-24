package basic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//20.02.19 -- 03

public class BufferedIOTest02 {
	/*
	 * public static void main(String[] args) { // 문자기반의 Buffered stream 객체 연습
	 * 
	 * // 우리가 만든 ByteArrayIOTest02.java 파일을 읽어와서 출력해보기 try { FileReader fr = new
	 * FileReader(".\\src\\basic\\ByteArrayIOTest02.java"); //역슬래시 사용하려면 두개씩 사용
	 * // 이클립스에서 자바 프로그램이 실행되는 현재 위치는 '프로젝트 폴더'가 기본위치가 된다. BufferedReader br =
	 * new BufferedReader(fr);
	 * 
	 * String temp =""; // 문자 기반의 버퍼에서는 1줄씩 읽어올 수 있는 readLine()메서드가 잇따. for(int
	 * i = 1; (temp=br.readLine())!= null; i++) { //줄번호 같이 출력
	 * System.out.printf("줄번호 >> %3d : %s\n" , i, temp); } br.close(); // 스트림 닫기
	 * } catch (IOException e) { System.out.println("입출력 오류"); } }
	 */

	public static void main(String[] args) throws IOException {
		// 문자기반의 Buffered stream 객체 연습

		// 우리가 만든 ByteArrayIOTest02.java 파일을 읽어와서 출력해보기

		FileReader fr = new FileReader(".\\src\\basic\\ByteArrayIOTest02.java");
		// 역슬래시 사용하려면 두개씩 사용
		// 이클립스에서 자바 프로그램이 실행되는 현재 위치는 '프로젝트 폴더'가 기본위치가 된다.
		BufferedReader br = new BufferedReader(fr);

		String temp = "";
		// 문자 기반의 버퍼에서는 1줄씩 읽어올 수 있는 readLine()메서드가 잇따.
		for (int i = 1; (temp = br.readLine()) != null; i++) {
			// 줄번호 같이 출력
			System.out.printf("줄번호 >> %3d : %s\n", i, temp);
		}
		br.close();
		// 스트림 닫기

	}
}
