
package packages.bean;
import java.io.Serializable;
public class T_Order implements Serializable{
	private long orderNo;
	private Customer customer;
	private String orderDate;
	private String reserveDate;
	private Tax tax;
	private int status;
	public T_Order(){}
	public T_Order(long orderNo, Customer customer, String orderDate,
			String reserveDate, Tax tax, int status) {
		this.orderNo = orderNo;
		this.customer = customer;
		this.orderDate = orderDate;
		this.reserveDate = reserveDate;
		this.tax = tax;
		this.status = status;
	}
	public long getNo() {
		return orderNo;
	}
	public Customer getCustomer() {
		return customer;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public String getReserveDate() {
		return reserveDate;
	}
	public Tax getTax() {
		return tax;
	}
	public int getStatus() {
		return status;
	}

	public void setNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
	public void setTax(Tax tax) {
		this.tax = tax;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
