package packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PassMailModifyDBAccess extends ControlDBAccess{
	public int modifyPass(String bytePass) throws Exception {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int count;
		try{
			String sql = "UPDATE PASSMAIL SET PASSWORD = ? WHERE ID = 1;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bytePass);
			count = pstmt.executeUpdate();
		} catch(SQLException e) {
			throw new Exception("パスワード更新処理に失敗しました！");
		} finally {
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("パスワード更新処理に失敗しました！");
			}
		}
		closeConnection(con);
		return count;
	}

	public int modifyMail(String mailAddress) throws Exception {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int count;
		try{
			String sql = "UPDATE PASSMAIL SET MAIL = ? WHERE ID = 1;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mailAddress);
			count = pstmt.executeUpdate();
		} catch(SQLException e) {
			throw new Exception("メールアドレス更新処理に失敗しました！");
		} finally {
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("メールアドレス更新処理に失敗しました！");
			}
		}
		closeConnection(con);
		return count;
	}
}
