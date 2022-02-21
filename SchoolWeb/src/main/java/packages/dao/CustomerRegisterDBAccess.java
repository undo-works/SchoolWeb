
package packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import packages.bean.Customer;

public class CustomerRegisterDBAccess extends ControlDBAccess {
	public int customerRegister(Customer customer)throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			String custName = customer.getCustName();
			String kana = customer.getKana();
			String tel = customer.getTel();
			String address = customer.getAddress();
			String sql = "INSERT INTO CUSTOMER(CUSTNAME, KANA, TEL, ADDRESS) "
					+ "VALUES(?, ?, ?, ?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, custName);
			pstmt.setString(2, kana);
			pstmt.setString(3, tel);
			pstmt.setString(4, address);
			count = pstmt.executeUpdate();
		}catch(SQLException e) {
			throw new Exception("顧客情報登録処理に失敗しました！");
		} finally {
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("顧客情報登録処理に失敗しました！");
			}
		}
		closeConnection(con);
		return count;
	}
}
