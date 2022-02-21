package packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import packages.bean.Item;

public class ItemDoubleDBAccess extends ControlDBAccess{
	public int itemRegister(Item item)throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			String itemId = item.getItemId();
			String itemName = item.getItemName();
			String size = item.getSize();
			int price = item.getPrice();
			String sql = "INSERT INTO ITEM(ITEMID, ITEMNAME, SIZE, PRICE) "
					+ "VALUES(?, ?, ?, ?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, itemId);
			pstmt.setString(2, itemName);
			pstmt.setString(3, size);
			pstmt.setInt(4, price);
			count = pstmt.executeUpdate();
		}catch(SQLException e) {
			throw new Exception("商品情報登録処理に失敗しました！");
		} finally {
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("商品情報登録処理に失敗しました！");
			}
		}
		closeConnection(con);
		return count;
	}
	public int itemModify(Item item) throws Exception {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int count;
		try{
			String itemId = item.getItemId();
			String itemName = item.getItemName();
			String size = item.getSize();
			int price = item.getPrice();


			String sql = "UPDATE ITEM SET ITEMNAME = ?, SIZE = ?, "
					+"PRICE = ? WHERE ITEMID = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, itemName);
			pstmt.setString(2, size);
			pstmt.setInt(3, price);
			pstmt.setString(4, itemId);
			count = pstmt.executeUpdate();
		} catch(SQLException e) {
			throw new Exception("商品情報更新処理に失敗しました！");
		} finally {
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("商品情報更新処理に失敗しました！");
			}
		}
		closeConnection(con);
		return count;
	}
}
