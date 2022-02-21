package packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packages.bean.Customer;
import packages.bean.T_Order;
import packages.bean.Tax;

public class OrderSearchDBAccess extends ControlDBAccess {
	public ArrayList<T_Order> serchOrder()throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<T_Order> list = new ArrayList<T_Order>();
		try {
			String sql = 	"SELECT ORDERNO, CUSTID, ORDERDATE, RESERVEDATE, TAXID, STATUS " +
					"FROM T_ORDER WHERE STATUS = 1 AND RESERVEDATE IS NULL ;";
	        pstmt = con.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        while(rs.next() == true) {
	        	long orderNo = rs.getLong("ORDERNO");
	        	int custId = rs.getInt("CUSTID");

	        	CustomerSearchDBAccess csDao = new CustomerSearchDBAccess();
	        	Customer customer = csDao.searchCustomerByCustId(custId);

	        	String orderData = rs.getString("ORDERDATE");
	        	String reserveData = rs.getString("RESERVEDATE");

	        	int taxId = rs.getInt("TAXID");
	        	TaxSearchDBAccess tsDao = new TaxSearchDBAccess();
	        	Tax tax = tsDao.searchTaxId(taxId);

	        	int status = rs.getInt("STATUS");
	        	T_Order t_order = new T_Order(orderNo,customer,orderData,reserveData,tax,status);
	        	list.add(t_order);
	        }
		 } catch(SQLException e) {
				throw new Exception("注文情報検索処理に失敗しました！");
			} finally {
				try{
					if(rs != null) {
						rs.close();
					}
				} catch(SQLException e) {
					throw new Exception("注文情報検索処理に失敗しました！");
				}
				try{
					if(pstmt != null) {
						pstmt.close();
					}
				} catch(SQLException e) {
					throw new Exception("注文情報検索処理に失敗しました！");
				}
			}
			closeConnection(con);
			return list;
	}

	public ArrayList<T_Order> searchAllOrder()throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<T_Order> list = new ArrayList<T_Order>();
		try {
			String sql = 	"SELECT ORDERNO, CUSTID, ORDERDATE, RESERVEDATE, TAXID, STATUS " +
					"FROM T_ORDER;";
	        pstmt = con.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        while(rs.next() == true) {
	        	long orderNo = rs.getLong("ORDERNO");
	        	int custId = rs.getInt("CUSTID");

	        	CustomerSearchDBAccess csDao = new CustomerSearchDBAccess();
	        	Customer customer = csDao.searchCustomerByCustId(custId);

	        	String orderData = rs.getString("ORDERDATE");
	        	String reserveData = rs.getString("RESERVEDATE");

	        	int taxId = rs.getInt("TAXID");
	        	TaxSearchDBAccess tsDao = new TaxSearchDBAccess();
	        	Tax tax = tsDao.searchTaxId(taxId);

	        	int status = rs.getInt("STATUS");
	        	T_Order t_order = new T_Order(orderNo,customer,orderData,reserveData,tax,status);
	        	list.add(t_order);
	        }
		 } catch(SQLException e) {
				throw new Exception("注文情報検索処理に失敗しました！");
			} finally {
				try{
					if(rs != null) {
						rs.close();
					}
				} catch(SQLException e) {
					throw new Exception("注文情報検索処理に失敗しました！");
				}
				try{
					if(pstmt != null) {
						pstmt.close();
					}
				} catch(SQLException e) {
					throw new Exception("注文情報検索処理に失敗しました！");
				}
			}
			closeConnection(con);
			return list;
	}
}
