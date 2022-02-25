package packages.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packages.bean.Customer;

public class ControlDBAccess {
	/*//DBとの接続を確立する
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
	}*/
	
	protected Connection createConnection()throws Exception{
		Connection con = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:65534/ KIDDA_LA",
					"user1",
					"pass1");
		} catch(ClassNotFoundException e) {
			throw new Exception("DB接続処理に失敗しました！");
		} catch (SQLException e) {
			throw new Exception("DB接続処理に失敗しました！");
		}
		return con;
	}
	// DBとの接続を閉じる
	protected void closeConnection(Connection con) throws Exception{
		try{
			if(con != null) {
				con.close();
			}
		} catch(SQLException e) {
			throw new Exception("DB接続処理に失敗しました！");
		}
	}
	public ArrayList<Customer>searchCustomerByTel(String tel)throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try{
				String sql = 	"SELECT CUSTID, CUSTNAME, KANA, ADDRESS " +
								"FROM CUSTOMER WHERE TEL = ?;";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,tel);
				rs = pstmt.executeQuery();
				while(rs.next() == true) {
					int id = rs.getInt("CUSTID");
					String name = rs.getString("CUSTNAME");
					String kana = rs.getString("KANA");
					String address = rs.getString("ADDRESS");
					Customer bean = new Customer(id, name, kana, tel, address);
					list.add(bean);
				}
		} catch(SQLException e) {
			throw new Exception("顧客情報検索処理に失敗しました！");
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				throw new Exception("顧客情報検索処理に失敗しました！");
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("顧客情報検索処理に失敗しました！");
			}
		}
		closeConnection(con);
		return list;
	}
}