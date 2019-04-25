package moroom.VO;

public class Users extends People {

	private int u_no; 	//  user 번호
	private int u_cash; //	cash
	private int c_no;	//	카테고리 번호

	public Users() {}
	
	/* @Param email; @Param pw; @Param name; @Param tel;
	 * @Param user_NO; @Param user_Cash @Param category; 
	 * */
	public Users(String email, String pw, String name, String tel, int u_no, int u_cash, int c_no){
		super(email, pw, name, tel);
		this.u_no = u_no;
		this.u_cash = u_cash;
		this.c_no = c_no;
	}
	
	public Users(People p, int _c_no)
	{
		super(p.getEmail(),p.getPw(),p.getName(),p.getTel());
		this.c_no = _c_no;
	}
	
	
	// get & set 
	public int getU_no() {
		return u_no;
	}

	public void setU_no(int u_no) {
		this.u_no = u_no;
	}

	public int getU_cash() {
		return u_cash;
	}

	public void setU_cash(int u_cash) {
		this.u_cash = u_cash;
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

}
