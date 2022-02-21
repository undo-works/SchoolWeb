package packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import packages.bean.Customer;

public class OrderInputDisplayDBAccess extends ControlDBAccess {

	public Customer searchCustomerById(int custId) throws Exception {

		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Customer customer = null;

		try {

			pstmt = con.prepareStatement("SELECT CUSTNAME, KANA, TEL, ADDRESS FROM CUSTOMER WHERE CUSTID = ?");
			pstmt.setInt(1, custId);
			rs = pstmt.executeQuery();

			if(rs.next()) {

				String custName = rs.getString("CUSTNAME");
				String kana = rs.getString("KANA");
				String tel = rs.getString("TEL");
				String address = rs.getString("ADDRESS");
				customer = new Customer(custId, custName, kana, tel, address);
			}
		} catch (SQLException e) {
			throw new Exception("顧客情報検索処理に失敗しました！");
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		closeConnection(con);
		return customer;
	}
}
