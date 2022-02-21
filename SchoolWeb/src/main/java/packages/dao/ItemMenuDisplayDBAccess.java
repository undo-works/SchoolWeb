package packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packages.bean.Item;

public class ItemMenuDisplayDBAccess extends ControlDBAccess{
	public ArrayList<Item> searchAllItem() throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Item> itemList = new ArrayList<Item>();
		try {
			String sql = 	"SELECT ITEMID, ITEMNAME, SIZE, PRICE " +
					        "FROM ITEM;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next() == true) {
				String itemId = rs.getString("ITEMID");
				String itemName = rs.getString("ITEMNAME");
				String size = rs.getString("SIZE");
				int price = rs.getInt("PRICE");
				Item item = new Item(itemId, itemName, size, price);
	     		itemList.add(item);
		}
		} catch(SQLException e) {
			throw new Exception("商品情報検索処理に失敗しました！");
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				throw new Exception("商品情報検索処理に失敗しました！");
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("商品情報検索処理に失敗しました！");
			}
		}
		closeConnection(con);
		return itemList;
	}
}
