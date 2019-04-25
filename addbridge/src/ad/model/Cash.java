package ad.model;

public class Cash {
	private int ca_no;    		//캐시번호
	private int ca_recharge;	//충전금액
	private int balance;		//잔액
	private int a_no;			//광고주 번호
	
	public Cash() {}
	
	
	public int getCa_no() {
		return ca_no;
	}
	public void setCa_no(int ca_no) {
		this.ca_no = ca_no;
	}
	public int getCa_recharge() {
		return ca_recharge;
	}
	public void setCa_recharge(int ca_recharge) {
		this.ca_recharge = ca_recharge;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getA_no() {
		return a_no;
	}
	public void setA_no(int a_no) {
		this.a_no = a_no;
	}
	
	
}
