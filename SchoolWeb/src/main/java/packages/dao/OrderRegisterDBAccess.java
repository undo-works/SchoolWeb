package packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import packages.bean.Customer;
import packages.bean.T_Order;
import packages.bean.Tax;
import packages.model.OrderControlUtility2;

public class OrderRegisterDBAccess extends ControlDBAccess{
	public void registerOrder(T_Order order) throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		try{
			String orderDate = OrderControlUtility2.getDate();
			Customer customer= order.getCustomer();
			int custId = customer.getCustId();
			String reserveDate = order.getReserveDate();
			Tax tax = order.getTax();
			int taxId = tax.getTaxId();

			String sql = "INSERT INTO T_ORDER(CUSTID, ORDERDATE, RESERVEDATE, TAXID, STATUS) "
					+ "VALUES(?, ?, ?, ?, 1);";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, custId);
			pstmt.setString(2, orderDate);
			pstmt.setString(3, reserveDate);
			pstmt.setInt(4, taxId);
			pstmt.executeUpdate();


		} catch(SQLException e) {
			throw new Exception("注文情報登録処理に失敗しました！");
		} finally {
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("注文情報登録処理に失敗しました！");
			}
		}
		closeConnection(con);
	}

}
