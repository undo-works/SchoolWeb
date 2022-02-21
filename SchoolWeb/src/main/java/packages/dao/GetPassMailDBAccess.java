package packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetPassMailDBAccess extends ControlDBAccess{
	public String searchPass()throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String pass;
		try {
			String sql = "SELECT PASSWORD FROM PASSMAIL WHERE ID = 1;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			pass =rs.getString("PASSWORD");
	    }catch(SQLException e) {
			throw new Exception("パスワード照会処理に失敗しました！");
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				throw new Exception("パスワード照会処理に失敗しました！");
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("パスワード照会処理に失敗しました！");
			}
		}
		closeConnection(con);
		return pass;
	}

	public String searchMail()throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String mail;
		try {
			String sql = "SELECT MAIL FROM PASSMAIL WHERE ID = 1;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			mail =rs.getString("MAIL");
	    }catch(SQLException e) {
			throw new Exception("メールアドレス照会処理に失敗しました！");
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				throw new Exception("メールアドレス照会処理に失敗しました！");
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("メールアドレス照会処理に失敗しました！");
			}
		}
		closeConnection(con);
		return mail;
	}
}
