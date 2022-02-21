package packages.bean;

import java.io.Serializable;
//Customerクラス（DTO）の定義
public class Customer implements Serializable{
	private int custId;		    //顧客ID
	private String custName;	//顧客名
	private String kana;		//カナ
	private String tel;        //電話番号
	private String address;    //住所
	public Customer(){}
	public Customer(int custId, String custName,String kana, String tel, String address) {
		this.custId = custId;
		this.custName =custName;
		this.kana = kana;
		this.tel = tel;
		this.address = address;
	}
	public void setCustId(int custId){		     //顧客IDを設定する
		this.custId = custId;
	}
	public int getCustId(){				     //顧客IDを取得する
		return custId;
	}
	public void setCustName(String custName){  //顧客名を設定する
		this.custName = custName;
	}
	public String getCustName(){				//顧客名を取得する
		return custName;
	}
	public void setKana(String kana){		    //カナを設定する
		this.kana = kana;
	}
	public String getKana(){				    //カナを取得する
		return kana;
	}
	public void setTel(String tel){		    //電話番号を設定する
		this.tel = tel;
	}
	public String getTel(){				    //電話番号を取得する
		return tel;
	}
	public void setAddress(String address){		//住所を設定する
		this.address = address;
	}
	public String getAddress(){				//住所を取得する
		return address;
	}
}

