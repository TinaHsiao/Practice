package Dao;

import java.sql.*;
import java.util.*;

import Util.LoadProperties;
import Util.ProDriverManager;

public class MovieOnlineDao {

	private Properties properties= LoadProperties.load();
	
	
	public static void main(String[] args) {
		MovieOnlineDao prd= new MovieOnlineDao();
		prd.movieOnline("2016-12-25 13:00",1,"A廳");
	}

	public void movieOnline(String ptime,int movie,String roomid){
		addMovieSchedule(ptime,movie,roomid);
		createSaleSeat(ptime,movie,roomid);	
	}
	
	public void addMovieSchedule(String ptime,int movie,String roomid){
		Connection conn = null;
		try{
			conn = ProDriverManager.getConnection(properties);
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO playlist ");
			sb.append("(ptime,movie,roomid) ");
			sb.append("VALUES (?,?,?) ");
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
						
			pstmt.setString(1,ptime);
			pstmt.setInt(2, movie);
			pstmt.setString(3,roomid);
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public HashMap<String,Integer> searchTheaterSeat(String theater){
		HashMap<String,Integer> hs= new HashMap<String,Integer>();	//HashMap<key,value>
		Connection conn = null;
		ResultSet rs = null;
		try{
			conn = ProDriverManager.getConnection(properties);
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT SEAT_ROW,SEAT_COL ");
			sb.append("FROM M_ROOM ");
			sb.append("WHERE ROOMID = ? ");
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
						
			pstmt.setString(1,theater);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){		//因為指定ROOMID(唯一值)，所以只會1.if有此廳 2.else無此廳的情況
				hs.put("row",rs.getInt("SEAT_ROW"));	//hs.put(key,value)
				hs.put("col",rs.getInt("SEAT_COL"));
			}else{
				hs.put("row",0);
				hs.put("col",0);
			}
			
		} catch (Exception e) {
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
		
		return hs;
	}
	
	public void createSaleSeat(String ptime,int movie,String roomid){
		HashMap<String,Integer> hs = searchTheaterSeat(roomid);
		Connection conn = null;
		try{
			conn = ProDriverManager.getConnection(properties);
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO SEATS ");
			sb.append("(ptime,movie,seat_num,sold) ");
			sb.append("VALUES (?,?,?,0) ");
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
						
			for(int i=1;i<=hs.get("row");i++){
				for(int j=1;j<=hs.get("col");j++){
					pstmt.setString(1,ptime);
					pstmt.setInt(2, movie);
					pstmt.setString(3,(i<10?"0"+i:i)+"-"+(j<10?"0"+j:j));
					
					pstmt.addBatch();	//(ex:A廳 25-20)20筆加入清單
				}
				pstmt.executeBatch(); //(ex:A廳 25-20)每20筆送出一次，送25次
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public List<HashMap<String,String>> searchMovieIfo(){
		List<HashMap<String,String>> list =new ArrayList<HashMap<String,String>>();
		Connection conn = null;
		ResultSet rs = null;
		try{
			conn = ProDriverManager.getConnection(properties);
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT MOVIE_ID,MOVIE_NAME ");
			sb.append("FROM MOVIE_INFO ");
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){		//因為指定ROOMID(唯一值)，所以只會1.if有此廳 2.else無此廳的情況
				HashMap<String,String> hs= new HashMap<String,String>();	//HashMap<key,value>
				hs.put("movie_id",""+rs.getInt("MOVIE_ID"));	//hs.put(key,value)
				hs.put("movie_name",rs.getString("MOVIE_NAME"));
				list.add(hs);
			}
			
		} catch (Exception e) {
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
	
	
	public List<HashMap<String,String>> searchRoom(){
		List<HashMap<String,String>> list =new ArrayList<HashMap<String,String>>();
		Connection conn = null;
		ResultSet rs = null;
		try{
			conn = ProDriverManager.getConnection(properties);
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT roomid ");
			sb.append("FROM m_room ");
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){		//因為指定ROOMID(唯一值)，所以只會1.if有此廳 2.else無此廳的情況
				HashMap<String,String> hs= new HashMap<String,String>();	//HashMap<key,value>
				hs.put("roomid",rs.getString("roomid"));
				list.add(hs);
			}
			
		} catch (Exception e) {
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
