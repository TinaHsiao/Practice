package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Bean.UserBean;
import Util.DaoUtil;

import java.sql.Blob;

public class UserDAO {
	
	DataSource ds = null;
	public UserDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/UserDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	

	public boolean checkMemberId(String memberId){
		boolean idExist = false;
		
		Connection conn= null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String select = "SELECT 1 FROM TheaterMember WHERE memberId = ?";
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(select);
			
			stmt.setString(1, memberId);
			
			rs = stmt.executeQuery();
			
			if(rs.next()){		//where=memberId 只要有一筆就可判斷了
				idExist = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoUtil.finallyDo(conn, stmt);
		}
		
		return idExist;
	}
	
	public boolean checkPassword(String memberId, String password){
		boolean pwdCorrect = false;
		Connection conn= null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String select = "SELECT 1 FROM TheaterMember WHERE memberId = ? AND password = ?";
		
		try{
			conn = ds.getConnection();
			stmt = conn.prepareStatement(select);
			stmt.setString(1, memberId);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				pwdCorrect =true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoUtil.finallyDo(conn, stmt);
		}
		
		return pwdCorrect;
	}

	public void insertUserData(UserBean bean){
		Connection conn= null;
		PreparedStatement stmt = null;
		String insert = "INSERT INTO TheaterMember (memberId, password, name, email, address, phone, birthday) values (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(insert);
			
			stmt.setString(1, bean.getMemberId());
			stmt.setString(2, bean.getPassword());
			stmt.setString(3, bean.getName());
			stmt.setString(4, bean.getEmail());
			stmt.setString(5, bean.getAddress());
			stmt.setString(6, bean.getPhone());
			stmt.setString(7, bean.getBirthday());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoUtil.finallyDo(conn, stmt);
		}
	}
}