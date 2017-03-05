package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MovieScheduleDAO {

	DataSource ds = null;
	public MovieScheduleDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/UserDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<HashMap<String,String>> searchMovieSchedule(String room,String date){
		List<HashMap<String,String>> list =new ArrayList<HashMap<String,String>>();
		Connection conn = null;
		ResultSet rs = null;
		try{
			conn = ds.getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT m.MOVIE_NAME,CONVERT(VARCHAR(5),p.PTIME,114) STIME, ");
			sb.append("		  CONVERT(VARCHAR(5),DATEADD(MINUTE, m.MOVIE_LENGTH,p.PTIME),114) ETIME ");
			sb.append("FROM playlist p ");
			sb.append("JOIN MOVIE_INFO m ON p.movie=m.MOVIE_ID ");
			sb.append("WHERE CONVERT(VARCHAR(10), p.PTIME,120) = ? ");
			sb.append("AND	 p.ROOMID = ? ");
			
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, date);
			pstmt.setString(2, room);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				HashMap<String,String> hs = new HashMap<String,String>();
				hs.put("movie_name", rs.getString("movie_name"));
				hs.put("stime", rs.getString("stime"));
				hs.put("etime", rs.getString("etime"));
				list.add(hs);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					rs.close();
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
		
		return list;		
	}
	
}
