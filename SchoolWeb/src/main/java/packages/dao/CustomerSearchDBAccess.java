
package packages.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packages.bean.Customer;
public class CustomerSearchDBAccess{
	private Connection createConnection()throws Exception{
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
	private void closeConnection(Connection con) throws Exception{
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
	public ArrayList<Customer>searchCustomerByKana(String kana)throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//kana = "%" + kana + "%";
		ArrayList<Customer> list = new ArrayList<Customer>();
		try{
				String sql = 	"SELECT CUSTID, CUSTNAME, KANA, TEL, ADDRESS " +
								"FROM CUSTOMER WHERE KANA LIKE ?;";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,"%" + kana + "%");
				rs = pstmt.executeQuery();
				while(rs.next() == true) {
					int id = rs.getInt("CUSTID");
					String name = rs.getString("CUSTNAME");
					String kana2 = rs.getString("KANA");
					String tel = rs.getString("TEL");
					String address = rs.getString("ADDRESS");
					Customer bean = new Customer(id, name, kana2, tel, address);
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
	public ArrayList<Customer>searchCustomer(String tel,String kana)throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try{
				String sql = 	"SELECT CUSTID, CUSTNAME, KANA, TEL, ADDRESS " +
								"FROM CUSTOMER WHERE TEL = ? AND KANA LIKE ?;";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,tel);
				pstmt.setString(2,"%" + kana + "%");
				rs = pstmt.executeQuery();
				while(rs.next() == true) {
					int id = rs.getInt("CUSTID");
					String name = rs.getString("CUSTNAME");
					String kana2 = rs.getString("KANA");
					String address = rs.getString("ADDRESS");
					Customer bean = new Customer(id, name, kana2, tel, address);
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

	public Customer searchCustomerByCustId(int custId)throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Customer customer = null;
		try{
				String sql = 	"SELECT CUSTNAME, KANA, TEL, ADDRESS " +
								"FROM CUSTOMER WHERE CUSTID = ?;";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,custId);
				rs = pstmt.executeQuery();
				if(rs.next() == true) {
					String name = rs.getString("CUSTNAME");
					String kana = rs.getString("KANA");
					String tel = rs.getString("TEL");
					String address = rs.getString("ADDRESS");
					customer = new Customer(custId, name, kana, tel, address);
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
		return customer;
	}

	public ArrayList<Customer>searchAllCustomer()throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try{
				String sql = 	"SELECT CUSTID, CUSTNAME, KANA, TEL, ADDRESS " +
								"FROM CUSTOMER;";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next() == true) {
					int id = rs.getInt("CUSTID");
					String name = rs.getString("CUSTNAME");
					String kana = rs.getString("KANA");
					String tel = rs.getString("TEL");
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