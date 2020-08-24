package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtil;

//20.02.24 -- 01

/*
DB의 LPROD 테이블에 새로운 데이터를 추가하기

lprod_id는 현재 lprod_id 중 제일 큰 값보다 1 증가된 값으로 한다.
lprod_gu와 lprod_nm은 직접 입력 받아서 사용한다.
그런데, lprod_gu는 기본키이기 때문에 데이터가 중복되면 다시 입력받는다.
*/

public class JdbcTest07_T {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			/*
			 * Class.forName("oracle.jdbc.driver.OracleDriver");
			 * conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","LSJ","java");
			*/
			
			conn = DBUtil.getConnection();
			
			//lprod_id중 제일 큰값 구하기
			String sql = "select max(lprod_id) maxId from lprod";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			//레코드가 하나일때는 if문으로 검사 
			int maxId = 0;
			if(rs.next()){
				maxId = rs.getInt("maxId");
				//제일 큰 값 가져오기 
			}
			
			maxId++; // 제일 큰 값을 1증가 시킨다. 
			
			String gu;
			int count =0 ;
			do{
				System.out.println("상품 분류코드(lprod_gu) 입력");
				gu = s.next();
				
				String sql2 = "select count(*) cnt from lprod where lprod_gu = ?";
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, gu);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					count = rs.getInt("cnt");
				}
				if(count > 0)
				{
					//중복되는게 있을경우 
					System.out.println("입력한 상품 분류코드 " + gu + " 는 이미 등록된 코드 입니다. ");
					System.out.println("다시 입력하세요 ");
				}
			}while(count > 0);
			System.out.print("상품분류명(lprod_nm) 입력 >> " );
			String nm = s.next();
			
			System.out.println("확인");
			String sql3 = "insert into lprod (lprod_id, lprod_gu, lprod_nm)" + "values(?,?,?)";
			
			System.out.println("확인2");
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, maxId);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			
			System.out.println("확인3");
			if(cnt>0){
				System.out.println("새로운 자료 등록 성공");
			}else{
				System.out.println("새로운 자료 등록 실패 ");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
//		}catch (ClassNotFoundException e) {
//			e.printStackTrace();
		}
		 finally {
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
				}if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
				
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
