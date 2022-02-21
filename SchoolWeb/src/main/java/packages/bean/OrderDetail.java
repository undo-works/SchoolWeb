
package packages.bean;
import java.io.Serializable;
public class OrderDetail implements Serializable{
	private long orderNo;
	private int detailNo;
	private Item item;
	private int quantity;
	public OrderDetail(){}
	public OrderDetail(long orderNo,int detailNo, Item item, int quantity) {
		this.orderNo = orderNo;
		this.detailNo = detailNo;
		this.item = item;
		this.quantity = quantity;
	}
	public long getOrderNo() {
		return orderNo;
	}
	public long getDetailNo() {
		return detailNo;
	}
	public Item getItem() {
		return item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public void setDetailNo(int detailNo) {
		this.detailNo = detailNo;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
