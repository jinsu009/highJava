package basic;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

//20.02.18-01

public class FileTest02 {

	public static void main(String[] args) {

		// 디렉토리(폴더) 만들기
		// 1. mkdir() ==> File객체의 경로 중 제일 마지막 위치의 디렉토리를 만든다.
		// ==> 반환값 : (만들기 성공 : true, 실패 : false)
		// ==> 중간의 경로가 모두 미리 만들어져 있어야 마지막위치의 디렉토리를 만들수 있따.
		// 2. mkdirs() ==> 중간의 경로가 없으면 중간의 경로도 새롭게 만든 후 마지막 위치의 디렉토리를 만들어준다.
		File file = new File("d:/d_other/연습용");
		/*
		 * if(file.mkdir()){ System.out.println(file.getName() +" 폴더만들기 성공"); }
		 * else{ System.out.println(file.getName() +" 폴더만들기 실패"); }
		 * System.out.println();
		 */

		File file2 = new File("d:/d_other/test/java/src");
		// if(file2.mkdir()){
		if (file2.mkdirs()) {
			System.out.println(file2.getName() + " 폴더만들기 성공");
		} else {
			System.out.println(file2.getName() + " 폴더만들기 실패");
		}
		System.out.println();
		// 실패 이유 : src생성경로를 다 생성해 놔야 한다.

		if (file.exists()) {
			// File객체에 지정한 '파일'이나 '폴더'가 존재하면 true, 그렇지 않으면 false
			System.out.println(file.getName() + "은 이미 존재 합니다.");
		} else {
			System.out.println(file.getName() + "은 존재 하지 않습니다.");
		}

		File file3 = new File("d:/d_other/test.txt");
		File file4 = new File("./src");
		// 이클립스에서 실행되는 자바프로그램의 '현재위치'는 '해당 프로젝트폴더위치'를 말한다.

		System.out.println("file3의 getPath >> " + file3.getPath());
		System.out.println("file3의 getAbsolutePath >> "
				+ file3.getAbsolutePath());

		System.out.println();
		System.out.println("file4의 getPath >> " + file4.getPath());
		System.out.println("file4의 getAbsolutePath >> "
				+ file4.getAbsolutePath());

		if (file3.isFile()) {
			System.out.println(file3.getPath() + " 은(는) 파일 입니다.");
		} else {
			System.out.println(file3.getPath() + " 은(는) 파일이 아닙니다.");
		}

		if (file4.isDirectory()) {
			System.out.println(file4.getPath() + "은 디렉토리(폴더) 입니다.");
		} else {
			System.out.println(file4.getPath() + "은 디렉토리(폴더)가 아닙니다.");
		}

		System.out.println(file3.getName() + "의 크기 >> " + file3.length()
				+ "bytes");

		System.out.println();
		File file5 = new File("d:/d_other");
		displayFileList(file5);
	}

	// 매개변수로 지정된 폴더안에 있는 파일과 폴더 목록을 출력하는 메소드

	public static void displayFileList(File dir) {

		if (!dir.isDirectory()) {
			System.out.println(dir.getPath() + "은 디렉토리(폴더)가 아닙니다. 작업을 종료합니다.");
			return;
		}
		System.out.println("[ " + dir.getAbsolutePath() + " ] 디렉토리의 내용");

		File[] files = dir.listFiles();// 디렉토리안의 파일목록을 읽어온다.
		// String[] strFiles = dir.list(); //파일목록을 읽어오는 또다른 방법
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd a HH:mm");

		// 배열출력
		for (int i = 0; i < files.length; i++) {
			String fileName = files[i].getName();
			String attr = "";
			// 파일의 속성(읽기, 쓰기 , 히든, 디렉토리로 구분)
			String size = "";

			if (files[i].isDirectory()) {
				// 디렉토리일 경우
				attr = "<DIR>";
			} else {
				// 파일일경우
				size = String.valueOf(files[i].length());
				attr = files[i].canRead() ? "R" : " ";
				attr += files[i].canWrite() ? "W" : " ";
				attr += files[i].isHidden() ? "H" : " ";
			}
			System.out.println();
			System.out.printf("날짜 :%s , 속성 : %5s , 용량 :%12s , 파일이름:%s \n ",
					df.format(new Date(files[i].lastModified())), attr, size,
					fileName);
		}
	}

}
