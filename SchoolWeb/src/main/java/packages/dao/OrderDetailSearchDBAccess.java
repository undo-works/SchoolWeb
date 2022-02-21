package packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packages.bean.Item;
import packages.bean.OrderDetail;

public class OrderDetailSearchDBAccess extends ControlDBAccess{
	public ArrayList<OrderDetail> searchDeliveryByOrderNo(long orderNo)throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		try {
			String sql = "SELECT ORDERDETAIL.ITEMID, ITEMNAME, SIZE, PRICE,"
					+ " DETAILNO, QUANTITY FROM ORDERDETAIL "
					+ "INNER JOIN ITEM ON ORDERDETAIL.ITEMID = ITEM.ITEMID "
					+ "WHERE orderNo = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1,orderNo);
			rs = pstmt.executeQuery();
			while(rs.next() == true) {
			   	String itemId = rs.getString("ORDERDETAIL.ITEMID");
				String itemName = rs.getString("ITEMNAME");
				String size = rs.getString("SIZE");
				int price = rs.getInt("PRICE");
				Item item =new Item(itemId, itemName, size, price);

				int detailNo = rs.getInt("DETAILNO");
				int quantity = rs.getInt("QUANTITY");
				OrderDetail orderDetail = new OrderDetail(orderNo, detailNo, item, quantity);
				orderDetailList.add(orderDetail);
			}
	    }catch(SQLException e) {
			throw new Exception("注文明細情報検索処理に失敗しました！");
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				throw new Exception("配達情報検索処理に失敗しました！");
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("配達情報検索処理に失敗しました！");
			}
		}
		closeConnection(con);
		return orderDetailList;
	}

	public ArrayList<OrderDetail> searchAllDelivery()throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		try {
			String sql = "SELECT ORDERDETAIL.ITEMID, ORDERNO, ITEMNAME, SIZE, PRICE,"
					+ " DETAILNO, QUANTITY FROM ORDERDETAIL "
					+ "INNER JOIN ITEM ON ORDERDETAIL.ITEMID = ITEM.ITEMID;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next() == true) {
			   	String itemId = rs.getString("ORDERDETAIL.ITEMID");
				String itemName = rs.getString("ITEMNAME");
				String size = rs.getString("SIZE");
				int price = rs.getInt("PRICE");
				Item item =new Item(itemId, itemName, size, price);

				int orderNo =rs.getInt("ORDERNO");
				int detailNo = rs.getInt("DETAILNO");
				int quantity = rs.getInt("QUANTITY");
				OrderDetail orderDetail = new OrderDetail(orderNo, detailNo, item, quantity);
				orderDetailList.add(orderDetail);
			}
	    }catch(SQLException e) {
			throw new Exception("注文明細情報検索処理に失敗しました！");
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				throw new Exception("配達情報検索処理に失敗しました！");
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("配達情報検索処理に失敗しました！");
			}
		}
		closeConnection(con);
		return orderDetailList;
	}
}
