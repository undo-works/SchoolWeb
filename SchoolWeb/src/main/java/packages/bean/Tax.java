package packages.bean;
import java.io.Serializable;
public class Tax implements Serializable{
	private int taxId;    //消費税ID
	private double rate;     //消費税率
	private String endDate;  //終了日
	public Tax(){}
	public Tax(int taxId, double rate, String endDate) {
		this.taxId = taxId;
		this.rate = rate;
		this.endDate = endDate;
	}
	public int getTaxId() {		         //消費税IDの値を返却する
		return taxId;
	}
	public double getRate() {		     //消費税率の値を返却する
		return rate;
	}
	public String getEndDate() {		 //終了日の値を返却する
		return endDate;
	}
	public void setTaxId(int taxId) {	     //消費税IDを設定する
		this.taxId = taxId;
	}
	public void setRate(double rate) {	     //消費税率を設定する
		this.rate = rate;
	}
	public void setEndDate(String endDate) { //終了日を設定する
		this.endDate = endDate;
	}
}
