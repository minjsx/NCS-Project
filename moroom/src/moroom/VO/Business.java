package moroom.VO;


public class Business extends People {

	private int b_no; 			// 업체번호
	private String b_crn; 			// 사업자등록번호
	private String b_ceoname; 	// 대표자명
	
	public Business() {}
	
	public Business(String email, String pw, String name, String tel, int b_no, String b_crn, String b_ceoname) {
		super(email, pw, name, tel);
		this.b_no = b_no;
		this.b_crn = b_crn;
		this.b_ceoname = b_ceoname;
	}
	
	public Business(People p, String crn,String ceo)
	{
		super(p.getEmail(),p.getPw(),p.getName(),p.getTel());
		this.b_crn = crn;
		this.b_ceoname = ceo;
		
	}
	
	public Business(int b_no,String crn,String ceo,People p)
	{
		super(p.getEmail(),p.getPw(),p.getName(),p.getTel());
		this.b_crn = crn;
		this.b_ceoname = ceo;
		this.b_no=b_no;
	}
	
	// get & set 
	
	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public String getB_crn() {
		return b_crn;
	}

	public void setB_crn(String b_crn) {
		this.b_crn = b_crn;
	}

	public String getB_ceoname() {
		return b_ceoname;
	}

	public void setB_ceoname(String b_ceoname) {
		this.b_ceoname = b_ceoname;
	}

}
