/**

 * クラス名：	OrderControlUtility2
 * 概要　　：	注文管理用ユーティリティ
 * 作成者名：
 * 作成日　：
 * 修正者名：
 * 修正日　：
 */

package packages.model;

import java.util.ArrayList;
import java.util.Calendar;

import packages.bean.Customer;
import packages.bean.Item;
import packages.bean.OrderDetail;
import packages.bean.T_Order;
import packages.bean.Tax;
import packages.dao.TaxSearchDBAccess;

public class OrderControlUtility2 {

	public static String[][] itemToArray(ArrayList<Item> list) {

		int listSize = list.size();
		String[][] tableData = new String[listSize][6];

		for(int i = 0; i < listSize; i++) {

			Item item = list.get(i);
			tableData[i][0] = item.getItemId();
			tableData[i][1] = item.getItemName();
			tableData[i][2] = item.getSize();
			tableData[i][3] = "";
			tableData[i][4] = Integer.toString(item.getPrice());
			tableData[i][5] = Integer.toString(0);
		}

		return tableData;
	}

	public static String[][] itemListToArray(ArrayList<Item> list) throws Exception{
		TaxSearchDBAccess dao = new TaxSearchDBAccess();
		Tax tax = dao.searchCurrentTax();
		double rate = tax.getRate();

		int listSize = list.size();
		String[][] tableData = new String[listSize][5];

		for(int i = 0; i < listSize; i++) {
			Item item = list.get(i);
			tableData[i][0] = item.getItemId();
			tableData[i][1] = item.getItemName();
			tableData[i][2] = item.getSize();
			tableData[i][3] = Integer.toString(item.getPrice());
			tableData[i][4] = Integer.toString((int)Math.floor(item.getPrice() * (1 + rate)));
		}
		return tableData;
	}


	public static String[][] orderToArray(ArrayList<T_Order> orderList) {

		int listSize = orderList.size();
		String[][] tableData = new String[listSize][5];

		for(int i = 0; i < listSize; i++) {

			T_Order order = orderList.get(i);

			tableData[i][0] = Long.toString(order.getNo());
			Customer customer = order.getCustomer();
			tableData[i][1] = Integer.toString(customer.getCustId());
			tableData[i][2] = order.getOrderDate();
			tableData[i][3] = order.getReserveDate();
		}
		return tableData;
	}

	public static String getDate() {

		Calendar cal = Calendar.getInstance();

	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH) + 1;
	    int day = cal.get(Calendar.DATE);

	    return year + "-" + month + "-" + day;
	}
	public static String[][] orderDetailToArray(ArrayList<OrderDetail> orderDetailList){
		int listSize = orderDetailList.size();
		String[][] tableData = new String[listSize][7];
		TaxSearchDBAccess dao = new TaxSearchDBAccess();

		for(int i = 0; i < listSize; i++) {

			OrderDetail orderDetail = orderDetailList.get(i);

			tableData[i][0] = Integer.toString(i+1);

			Item item = orderDetail.getItem();
			tableData[i][1] = item.getItemId();
			tableData[i][2] = item.getItemName();
			tableData[i][3] = item.getSize();

			Tax tax = new Tax();
			try {
				tax = dao.searchCurrentTax();
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			double rate = tax.getRate();

			int quantity = orderDetail.getQuantity();
			int price = item.getPrice();
			price = (int)(price * (1 + rate));

			tableData[i][4] = Integer.toString(quantity);
			tableData[i][5] = Integer.toString(price);

			int subTotal = quantity * price;
			tableData[i][6] = Integer.toString(subTotal);
		}

		return tableData;
	}
}