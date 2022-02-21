package packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import packages.bean.Customer;


public class CustomerModifyDBAccess extends ControlDBAccess{

	public int modifyCustomer(Customer customer) throws Exception {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int count;
		try{
			int custId = customer.getCustId();
			String custName = customer.getCustName();
			String kana = customer.getKana();
			String tel = customer.getTel();
			String address = customer.getAddress();

			String sql = "UPDATE CUSTOMER SET CUSTNAME = ?, KANA = ?, "
					+"TEL = ?, ADDRESS = ? WHERE CUSTID = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, custName);
			pstmt.setString(2, kana);
			pstmt.setString(3, tel);
			pstmt.setString(4, address);
			pstmt.setInt(5, custId);
			count = pstmt.executeUpdate();
		} catch(SQLException e) {
			throw new Exception("顧客情報更新処理に失敗しました！");
		} finally {
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("顧客情報更新処理に失敗しました！");
			}
		}
		closeConnection(con);
		return count;
	}
}


