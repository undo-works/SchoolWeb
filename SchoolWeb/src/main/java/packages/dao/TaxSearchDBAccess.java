package packages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packages.bean.Tax;
public class TaxSearchDBAccess extends ControlDBAccess{

	public Tax searchCurrentTax()throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		Tax tax = null;
		ResultSet rs = null;
		try{
				String sql = 	"SELECT TAXID, RATE, ENDDATE " +
								"FROM TAX WHERE ENDDATE IS NULL;";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next() == true) {
					int taxId = rs.getInt("TAXID");
					double rate = rs.getDouble("RATE");
					String enddate = rs.getString("ENDDATE");
					tax = new Tax(taxId,rate,enddate);
				}
		} catch(SQLException e) {
			throw new Exception("消費税情報検索処理に失敗しました！");
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				throw new Exception("消費税情報検索処理に失敗しました！");
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("消費税情報検索処理に失敗しました！");
			}
		}
		closeConnection(con);
		return tax;
	}

	//追加機能
	public Tax searchTaxId(int taxId)throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		Tax tax = null;
		ResultSet rs = null;
		try{
				String sql = 	"SELECT  RATE, ENDDATE " +
								"FROM TAX WHERE TAXID = ?;";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,taxId);
				rs = pstmt.executeQuery();
				if(rs.next() == true) {
					double rate = rs.getDouble("RATE");
					String enddate = rs.getString("ENDDATE");
					tax = new Tax(taxId,rate,enddate);
				}
		} catch(SQLException e) {
			throw new Exception("消費税情報検索処理に失敗しました！");
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				throw new Exception("消費税情報検索処理に失敗しました！");
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("消費税情報検索処理に失敗しました！");
			}
		}
		closeConnection(con);
		return tax;
	}

	public ArrayList<Tax> searchAllTax()throws Exception{
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		Tax tax = null;
		ResultSet rs = null;
		ArrayList<Tax> list = new ArrayList<Tax>();
		try{
				String sql = 	"SELECT TAXID, RATE, ENDDATE FROM TAX;";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next() == true) {
					int taxId = rs.getInt("TAXID");
					double rate = rs.getDouble("RATE");
					String enddate = rs.getString("ENDDATE");
					tax = new Tax(taxId,rate,enddate);
					list.add(tax);
				}
		} catch(SQLException e) {
			throw new Exception("消費税情報検索処理に失敗しました！");
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				throw new Exception("消費税情報検索処理に失敗しました！");
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("消費税情報検索処理に失敗しました！");
			}
		}
		closeConnection(con);
		return list;
	}
}