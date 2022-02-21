package packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packages.bean.Item;
import packages.bean.OrderDetail;

public class OrderDetailRegisterDBAccess extends ControlDBAccess{
	public void registerOrderDetail(ArrayList<OrderDetail> orderDetailList) throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		try{
			long orderNo = 0;
			String sql = "SELECT ORDERNO FROM T_ORDER ORDER BY ORDERNO DESC LIMIT 1;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next() == true) {
				orderNo = rs.getLong("ORDERNO");
			}
			for(OrderDetail orderDetail : orderDetailList) {
				Item item = orderDetail.getItem();
				String itemId = item.getItemId();
				int quantity = orderDetail.getQuantity();

				String sql2 = "INSERT INTO ORDERDETAIL(ORDERNO, ITEMID, QUANTITY) "
						+ "VALUES(?, ?, ?);";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setLong(1, orderNo);
				pstmt2.setString(2, itemId);
				pstmt2.setInt(3, quantity);
				pstmt2.executeUpdate();
			}

		} catch(SQLException e) {
			throw new Exception("注文明細情報登録処理に失敗しました！");
		} finally {
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("注文明細情報登録処理に失敗しました！");
			}
		}
		closeConnection(con);
	}

}
