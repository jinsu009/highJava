package basic;

import java.sql.*;
//20.02.20 -- 04
import java.util.Scanner;

/*
 * 문제 : 사용자로부터 lprod_id 값을 입력바다 입력한 값보다 lprod_id가 큰 자료를 출력하시오
 */
public class JdbcTest02 {
	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			System.out.print("lprod_id 값을 입력하세요 ");
			int a = (s.nextInt());
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "LSJ", "java");
			String sql = "select * from lprod where lprod_id >= " + a;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);


			while (rs.next()) {
				
					System.out.println("lprod_Id : " + rs.getInt("lprod_id"));
					System.out.println("lprod_GU : " + rs.getString(2));
					System.out
							.println("lprod_Nm : " + rs.getString("lprod_nm"));
					System.out
							.println("-----------------------------------------");
				
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
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}
