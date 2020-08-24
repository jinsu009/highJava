<%@ page language="java" contentType="text/plain; charset=UTF-8"
	    pageEncoding="UTF-8" import="java.sql.*"%>
	    
	    <%
	    	String DB_URL = "jdbc:oracle:thin:@112.220.114.130:1521:xe";
	    	String DB_USER = "team4_201912m";
	    	String DB_PASSWORD = "java";
	    	
	    	Connection conn;
	    	PreparedStatement pstmt = null; 
	    	ResultSet rs; 
	    	
	    	String temp = request.getParameter("temp");
	    	try{
	    		Class.forName("oracle.jdbc.driver.OracleDriver");
	    		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	    		String result = "";
	    		
	    		
	    		String field = request.getParameter("field");
	    		String str = new String(request.getParameter("str").getBytes("8859_1") ,"UTF-8");
	    		System.out.println(field);
	    		System.out.println(str);
	    		//1 . html에서 조건에 넣을 값을 가져오고    		
	    		String sql = "select pharm_name, pharm_add1, pharm_add2, pharm_tel from pharmacy where " + field + " like '%' || ? || '%'";
// 	    		String sql = "select pharm_name, pharm_add1, pharm_add2, pharm_tel from pharmacy where pharm_id ='a002'";
	    		pstmt = conn.prepareStatement(sql);
	    		
	    		// 2. 값을 차례로 넣어준다. 
	    		pstmt.setString(1,str);
	    		
	    		rs = pstmt.executeQuery();          
	    		
	    		result += "[ ";
	    		while(rs.next()){ 
	    			String pharm_name = rs.getString("pharm_name");
	    			String pharm_add1 = rs.getString("pharm_add1");
	    			String pharm_add2 = rs.getString("pharm_add2");
	    			result += "{\"pharm_name\":\"" + pharm_name+"\",\"pharm_add1\":\"" +pharm_add1+"\",\"pharm_add2\":\"" +pharm_add2+"\"},";
	    		}
	    		result = result.substring(0, result.length()-1);
	    		result +="]";
	    		
	    		
	    		out.println(result); // 값이 잘 넘어오는지 검사
	    	}catch(SQLException e){
	    		out.println(e.getMessage());
	    	}
	    
	    %>