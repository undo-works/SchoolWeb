package packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderCancelDBAccess extends ControlDBAccess{

	public int cancelDeliveryByOrderNo(int orderNo) throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int count = 0;
		try{
			String sql = "UPDATE T_ORDER SET STATUS = 2 WHERE ORDERNO = ? AND STATUS = 1;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderNo);
			count = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			throw new Exception("配達キャンセル処理に失敗しました！");
		}finally{
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("配達キャンセル処理に失敗しました！");
			}
		}
		closeConnection(con);
		return count;
	}
}