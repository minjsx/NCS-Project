package moroom.VO;

public class CashInfo {

	private int ci_no;		//캐시충전번호
	private int u_no;		//유저번호
	private int ci_money;	//충전 금액
	private String ci_date;	//충전 일자
	
	public CashInfo() {}
	
	public CashInfo(int ci_no, int u_no, int ci_money, String ci_date) {
		this.ci_no = ci_no;
		this.u_no = u_no;
		this.ci_money = ci_money;
		this.ci_date = ci_date;
	}
	
	public int getCi_no() {
		return ci_no;
	}

	public void setCi_no(int ci_no) {
		this.ci_no = ci_no;
	}

	public int getU_no() {
		return u_no;
	}

	public void setU_no(int u_no) {
		this.u_no = u_no;
	}

	public int getCi_money() {
		return ci_money;
	}

	public void setCi_money(int ci_money) {
		this.ci_money = ci_money;
	}

	public String getCi_date() {
		return ci_date;
	}

	public void setCi_date(String ci_date) {
		this.ci_date = ci_date;
	}
	
	
}
