package basic;

//20.02.20 -- 05

/*
 * 문제 )) 사용자로부터 주소의 일부분을 입력받아 member테이블에서 사용자가 입력한 주소가 
 * 			포함된 회원의 ID, 이름, 우편번호, 주소를 출력하시오 
 * 예 )) 만약 대흥동이라고 입력했다면 주소에 '대흥동'이 포함된 모든 자료를 찾아낸다.
 */

import java.sql.*;
import java.util.*;

public class JdbcTest03 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			System.out.println("주소를 입력하세요");
			String a = s.nextLine();

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "LSJ", "java");
			String sql = "select * from member where MEM_ADD1 LIKE '" + a +"%'";
			System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			
			while (rs.next()) {

				System.out.println("아이디 : " + rs.getString("mem_id"));
				System.out.println("이름  : " + rs.getString("mem_name"));
				System.out.println("우편번호 : " + rs.getString("mem_zip"));
				System.out.println("주소 : " + rs.getString("mem_add1")
						+ rs.getString("mem_add2"));
				System.out.println("---------------------");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
