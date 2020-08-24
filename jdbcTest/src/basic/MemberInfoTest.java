package basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtil;
import util.DBUtil2;
//20.02.24 -- 03
/*
 * 회원정보를 관리하는 프로그램 만들기 (DB테이블명 : mymmeber)
 * 아래의 메뉴를 모두 구현하시오 (crud구현)
 * 
 * 메뉴예시 ))
 * -------------------------
 * == 작업 선택 ==
 * 1. 자료 입력 --> insert(Create) 
 * 2. 자료 삭제 --> delete(Delete)
 * 3. 자료 수정 --> update(Update)
 * 4. 전체 출력 --> select(Read)
 * 0. 작업 끝
 * --------------------------
 * 자료의 삭제는 회원id를 입력받아 삭제 한다. 
 * 자료 추가 할때 회원id의 중복 검사를 한다. 
 */
import util.DBUtil3;

public class MemberInfoTest {

	static Scanner s = new Scanner(System.in);
	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	static String memId = "";

	public static void main(String[] args) {

		new MemberInfoTest().start();
	}

	public void start() {
		int a = 0;
		do {
			System.out.println();
			System.out.println("-------------------------");
			System.out.println("\t1. 자료 입력");
			System.out.println("\t2. 자료 삭제");
			System.out.println("\t3. 전체 자료 수정");
			System.out.println("\t4. 자료 선택 수정");
			System.out.println("\t5. 전체 출력");
			System.out.println("\t0. 종료");
			System.out.println("-------------------------");
			System.out.print("작업 선택 >> ");
			do {
				try {
					a = s.nextInt();
				} catch (Exception e) {
				}
				if (!(a == 1 || a == 2 || a == 3 || a == 4 || a == 5 || a == 0)) {
					System.out.println("잘못된 값을 입력하셨습니다.");
					s.nextInt();
				}
			} while (!(a == 1 || a == 2 || a == 3 || a == 4 || a == 5 || a == 0));
			switch (a) {
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				update2();
				break;
			case 5:
				select();
				break;
			case 0:
				System.out.println("시스템 종료");
				break;
			}
		} while (a != 0);
	}

	public void insert() {
		try {
			System.out.print("등록할 멤버 아이디(mem_id) 입력 >> ");
			memId = s.next();
			int count = getMemberCount(memId);
			if (count > 0) {
				System.out.println(memId + " 는 이미 존재하는 아이디입니다.");
				return;
			}
			// 중복이 없을 경우 반복문을 빠져나와서 나머지 자료들을 입력해준다.
			System.out.print("회원 이름(mem_name) 입력 >> ");
			String nm = s.next();
			System.out.print("회원 전화번호(mem_tel) 입력 >> ");
			String tel = s.next();
			s.nextLine();
			System.out.print("회원 주소(mem_addr) 입력 >> ");
			String addr = s.nextLine();
			// 변수를 하나로만사용할경우 마지막 하나만 저장된다.
			conn = DBUtil.getConnection();
			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr)"
					+ "values(?,?,?,?)";
			// insert 조건문을 작성해주고 맞는 value값을 넣어준다.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, nm);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			// (value, 값이 들어간 변수)
			int cnt = pstmt.executeUpdate(); // sql문을 수행하는 부분
			if (cnt > 0) {
				System.out.println("새로운 자료 등록 성공");
			} else {
				System.out.println("새로운 자료 등록 실패 ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {if (rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}

			if (stmt != null) {

				try {

					stmt.close();

				} catch (SQLException e) {

					e.printStackTrace();

				}

			}

			if (pstmt != null) {

				try {

					pstmt.close();

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

	public void delete() {

		System.out.print("삭제 할 멤버 아이디(mem_id) 입력 >> ");

		memId = s.next();

		try {

			int count = getMemberCount(memId);

			if (count == 0) {

				System.out.println(memId + " 는 존재하지 않는 회원입니다.");

				return;

			}

			conn = DBUtil.getConnection();

			String sql2 = "delete from mymember where mem_id = ?";

			pstmt = conn.prepareStatement(sql2);

			pstmt.setString(1, memId);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {

				System.out.println("자료가 삭제 되었습니다.");

			} else {

				System.out.println("자료 삭제가 실패했습니다. ");

			}

		} catch (SQLException e) {

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

			if (pstmt != null) {

				try {

					pstmt.close();

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

	public void update() {

		try {

			System.out.println();

			System.out.print("수정할 회원의 아이디를 입력하세요 >> ");

			memId = s.next();

			int count = getMemberCount(memId);

			if (count == 0) {

				System.out.println(memId + " 은 없는 회원입니다. 수정작업을 종료합니다.");

				return;

			}

			System.out.println("---- 수정 ----");

			System.out.print("회원 이름(mem_name) 입력 >> ");

			String nm = s.next();

			System.out.print("회원 전화번호(mem_tel) 입력 >> ");

			String tel = s.next();

			s.nextLine(); // 버퍼비우기

			System.out.print("회원 주소(mem_addr) 입력 >> ");

			String addr = s.nextLine();

			conn = DBUtil.getConnection();

			String sql = "update mymember set mem_name=?, mem_tel=?, mem_addr=? where mem_id=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, nm);

			pstmt.setString(2, tel);

			pstmt.setString(3, addr);

			pstmt.setString(4, memId);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {

				System.out.println(memId + " 회원의 정보가 수정 되었습니다.");

			} else {

				System.out.println("수정작업 실패");

			}

		} catch (SQLException e) {

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

			if (pstmt != null) {

				try {

					pstmt.close();

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

	// 회원정보를 수정하는 메소드

	public void update2() {

		try {

			System.out.println();

			System.out.print("수정할 회원의 아이디를 입력하세요 >> ");

			memId = s.next();

			int count = getMemberCount(memId);

			if (count == 0) {

				System.out.println(memId + " 은 없는 회원입니다. 수정작업을 종료합니다");

				return;
			}

			int a = 0;

			do {
				System.out.println("어떤 정보를 수정 하시겠습니까.?");
				System.out.println("1. 이름 수정");
				System.out.println("2. 전화번호 수정");
				System.out.println("3. 주소 수정");
				System.out.println("0. 종료");
				a = s.nextInt();
				switch (a) {
				case 1:
					System.out.print("회원 이름(mem_name) 입력 >> ");
					String nm = s.next();
					conn = DBUtil.getConnection();
					String sql1 = "update mymember set mem_name=? where mem_id=?";
					pstmt = conn.prepareStatement(sql1);
					pstmt.setString(1, nm);
					pstmt.setString(2, memId);
					int cnt = pstmt.executeUpdate();
					if (cnt > 0) {
						System.out.println(memId + " 회원의 정보가 수정 되었습니다.");
					} else {
						System.out.println("수정작업 실패");
					}
					break;
				case 2:
					System.out.print("회원 전화번호(mem_tel) 입력 >> ");
					String tel = s.next();
					conn = DBUtil.getConnection();
					String sql2 = "update mymember set mem_tel=? where mem_id=?";
					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, tel);
					pstmt.setString(2, memId);
					int cnt2 = pstmt.executeUpdate();
					if (cnt2 > 0) {
						System.out.println(memId + " 회원의 정보가 수정 되었습니다.");
					} else {
						System.out.println("수정작업 실패");
					}
					break;
				case 3:
					s.nextLine(); // 버퍼비우기
					System.out.print("회원 주소(mem_addr) 입력 >> ");
					String addr = s.nextLine();
					conn = DBUtil.getConnection();
					String sql3 = "update mymember set mem_addr=? where mem_id=?";
					pstmt = conn.prepareStatement(sql3);
					pstmt.setString(1, addr);
					pstmt.setString(2, memId);
					int cnt3 = pstmt.executeUpdate();
					if (cnt3 > 0) {
						System.out.println(memId + " 회원의 정보가 수정 되었습니다.");
					} else {
						System.out.println("수정작업 실패");
					}
					break;
				case 0:
					break;
				}

			} while (a != 0);

		} catch (SQLException e) {

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

			if (pstmt != null) {

				try {

					pstmt.close();

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

	public void select() {

		try {

			//conn = DBUtil.getConnection();
			//conn = DBUtil2.getConnection();
			conn = DBUtil3.getConnection();
			
			
			String sql = "select * from mymember";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			System.out.println("--------------------------------------");

			System.out.println("ID 		NAME	TEL		 ADDR");

			System.out.println("--------------------------------------");

			while (rs.next()) {

				System.out.print(rs.getString(1) + "\t" + rs.getString(2)

				+ "\t" + rs.getString(3) + "\t" + rs.getString(4)

				+ "\n");

			}

		} catch (SQLException e) {

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

			if (pstmt != null) {

				try {

					pstmt.close();

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

	// 회원 아이디를 매개값으로 받아서 해당 회원의 인원수를 반환하는 메소드

	public int getMemberCount(String memId) {

		// conn, pstmt, rs -- 전역변수

		int count = 0;

		try {

			conn = DBUtil.getConnection();

			String sql2 = "select count(*) cnt from mymember where mem_id = ?";

			pstmt = conn.prepareStatement(sql2);

			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				count = rs.getInt("cnt");

			}

		} catch (Exception e) {

			count = 0;

			e.printStackTrace();

		} finally {

			if (rs != null) {

				try {

					rs.close();

				} catch (SQLException e) {

					e.printStackTrace();

				}

			}

			if (pstmt != null) {

				try {

					pstmt.close();

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
		return count;

	}

}