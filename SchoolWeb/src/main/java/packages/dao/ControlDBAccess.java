package packages.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ControlDBAccess {
	//DBとの接続を確立する
	public Connection createConnection()throws Exception{
		Connection con = null;
		try{
			if(con == null || con.isClosed()) {
				InitialContext ic = new InitialContext();
				DataSource ds=(DataSource)ic.lookup("java:comp/env/jdbc/localDB");
				
				//データベース接続を取得
				con = ds.getConnection();
			}
		} catch(NamingException | SQLException e) {
			e.printStackTrace();
			con = null;
			throw e;
		}
		return con;
	}
	// DBとの接続を閉じる
	public void closeConnection(Connection con) throws Exception{
		try{
			if(con != null) {
				con.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			con = null;
		}
	}
}