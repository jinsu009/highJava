package basic;

//20.02.18--02

import java.io.*;

public class FileTest01 {
	public static void main(String[] args) {
		// 파일 객체 만들기 연습
		// 윈도우는 따옴표안에 대소문자를 가리지 않는다.
		// 리눅스같은거 사용할때 쭈의

		// 1. new File(String 파일 또는 경로)
		// => 디렉토리와 디렉토리 또는 디렉토리와 파일명사이의 구분 문자는 '/'를 사용하거나 '\'를 사용한다.
		// File file1 = new File("D:\\D_Other\\test.txt");
		File file1 = new File("D:/D_Other/test.txt");

		System.out.println("파일명 >> " + file1.getName());
		System.out.println("파일 인가 ? >> " + file1.isFile());
		System.out.println("디렉토리인가 ? >> " + file1.isDirectory());
		System.out.println();

		File file2 = new File("D:/D_Other");
		// file2는 디렉토리 정보를 가진 변수가 된다.

		System.out.println("파일명 >> " + file2.getName());
		System.out.println("파일 인가 ? >> " + file2.isFile());
		System.out.println("디렉토리인가 ? >> " + file2.isDirectory());
		System.out.println();

		// 2. new File(File parent, String child)
		// ==> 'parent' 디렉토리 안에 있는 'child' 파일을 말한다.
		// 경로명을 파일 객체로 준 것
		File file3 = new File(file2, "test.txt");// 변수 file1와 같은 파일을 나타낸다.

		System.out.println("파일명 >> " + file3.getName());
		System.out.println("파일 인가 ? >> " + file3.isFile());
		System.out.println("디렉토리인가 ? >> " + file3.isDirectory());
		System.out.println();

		// 3. new File(String parent, String child)
		File file4 = new File("D:/D_Other", "test.txt"); // 변수 file1, file3와 같은
															// 파일을 나타낸다.

		System.out.println("파일명 >> " + file4.getName());
		System.out.println("파일 인가 ? >> " + file4.isFile());
		System.out.println("디렉토리인가 ? >> " + file4.isDirectory());
	}
}
