package basic;
//20.02.21 -- 04 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
	DB의 LPROD 테이블에 새로운 데이터를 추가하기
	
	lprod_id는 현재 lprod_id 중 제일 큰 값보다 1 증가된 값으로 한다.
	lprod_gu와 lprod_nm은 직접 입력 받아서 사용한다.
	그런데, lprod_gu는 기본키이기 때문에 데이터가 중복되면 다시 입력받는다.
 */
public class JdbcTest07 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "LSJ", "java");
			
			//lprod_id
			String sql = "select max(lprod_id) from lprod";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int lprod_id = 0;
			if(rs.next()){
				lprod_id = rs.getInt("max(lprod_id)") + 1;
			}
			
			//lprod_gu
			String lprod_gu = "";
			do{
				System.out.println("분류코드 > ");
				String tempGU = sc.nextLine();
				
				String sql2 = "select count(lprod_gu) from lprod where lprod_gu = '" + tempGU + "'";
				rs2 = stmt.executeQuery(sql2);
				if(rs2.next()){
					if(rs2.getInt("count(lprod_gu)") != 0){		
						System.out.println("중복된 lprod_gu입니다. 다시 입력해주세요.");
					}else{
						lprod_gu = tempGU;
					}
				}
			}while(rs2.getInt("count(lprod_gu)") != 0);
			
			
			
			//lprod_nm
			System.out.println("상 품 명 > ");
			String lprod_nm = sc.nextLine();
			
			String sql3 = "insert into lprod (lprod_id, lprod_gu, lprod_nm)" 
					+ "values('" + lprod_id 
					+ ("', '") + lprod_gu 
					+ ("', '") + lprod_nm
					+ ("')");
			System.out.println(sql3);
			stmt.executeUpdate(sql3);
			
			while(rs.next()){
				System.out.println("LPROD_ID : " + rs.getString("lprod_id"));
				System.out.println("LPROD_GU : " + rs.getString("lprod_gu"));
				System.out.println("LPROD_NM : " + rs.getString("lprod_nm"));				
				System.out.println("---------------------------------------------------------------------------------");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} finally {
			if(rs2 != null) try{rs.close();} catch(SQLException e){}
			if(rs != null) try{rs.close();} catch(SQLException e){}
			if(stmt != null) try{stmt.close();} catch(SQLException e){}
			if(conn != null) try{conn.close();} catch(SQLException e){}	
		}
		

	}

}
