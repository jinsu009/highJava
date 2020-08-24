package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
//20.02.25--02

/*
 * DB의 정보가 저장된 파일의 내용으로 DB접속 정보를 설정하기 
 * 방법1) Properties객체를 이용하는 방법
 * Properties >> map이랑 형식이 비슷하다 키와 value 값을 가지고 있고 이것은 string타입이다. ??
 * 
 * 옛날 방식 
 * 
 */
public class DBUtil2 {
	private static Properties prop; // Properties객체 변수 선언 
	static{
		//static 초기화 블럭
		prop = new Properties(); //Properties객체 생성
		File f = new File("res/dbinfo.properties"); // 읽어올 파일명이 설정된 file객체 생성
		
		try {
			// 파일을 읽어오기 위해서 FileInputStream객체를 생성한다. 
			FileInputStream fin = new FileInputStream(f);
			
			prop.load(fin);// 파일 내용을 읽어와 properties객체에 데이터를 세팅한다.
			
			// properties객체에 설정된 내용을 getProperty(key값)메소드를 사용해서 읽어와 사용한다. 
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Class.forName(prop.getProperty("driver"));
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버로딩 실패 ");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("DB설정파일이 없습니다.");
			e.printStackTrace();
		}catch (IOException e) {
			System.out.println("입출력오류 입니다. ");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(
					prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pass"));
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
			return null;
		}
	}
}
