
package packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packages.bean.Customer;
import packages.bean.T_Order;
import packages.bean.Tax;

public class DeliveryConfirmDBAccess extends ControlDBAccess{
	public ArrayList<T_Order> searchDeliveryByCustId(int custId)throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ArrayList<T_Order> orderList = new ArrayList<T_Order>();
		try {
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
				Customer customer = new Customer(custId, name, kana, tel, address);

				String sql2 = "SELECT T_ORDER.TAXID, RATE, ENDDATE, ORDERNO, ORDERDATE, RESERVEDATE "
						+ "FROM T_ORDER INNER JOIN TAX ON T_ORDER.TAXID = TAX.TAXID "
						+ "WHERE CUSTID = ? AND STATUS = 1;";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1,custId);
				rs2 = pstmt2.executeQuery();
				while(rs2.next() == true) {

					int taxId = rs2.getInt("T_ORDER.TAXID");
					double rate = rs2.getDouble("RATE");
					String endDate = rs2.getString("ENDDATE");
					Tax tax = new Tax(taxId, rate, endDate);

					long orderNo = rs2.getLong("ORDERNO");
					String orderDate = rs2.getString("ORDERDATE");
					String reserveDate = rs2.getString("RESERVEDATE");
					T_Order order = new T_Order(orderNo, customer, orderDate, reserveDate, tax, 1);
					orderList.add(order);
			    }
			}
	    }catch(SQLException e) {
			throw new Exception("配達情報検索処理に失敗しました！");
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
				if(rs2 != null) {
					rs2.close();
				}
			} catch(SQLException e) {
				throw new Exception("配達情報検索処理に失敗しました！");
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
				if(pstmt2 != null) {
					pstmt2.close();
				}
			} catch(SQLException e) {
				throw new Exception("配達情報検索処理に失敗しました！");
			}
		}
		closeConnection(con);
		return orderList;
	}
}
