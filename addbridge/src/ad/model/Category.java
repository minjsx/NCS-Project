package ad.model;

public class Category {
	private int cg_no;		//카테고리 번호
	private String cg_name; //카테고리 명
	
	public Category(int cg_no, String cg_name) {
		this.cg_name = cg_name;
		this.cg_no = cg_no;
	}
	
	public int getCg_no() {
		return cg_no;
	}
	public void setCg_no(int cg_no) {
		this.cg_no = cg_no;
	}
	public String getCg_name() {
		return cg_name;
	}
	public void setCg_name(String cg_name) {
		this.cg_name = cg_name;
	}
	
	
	
}
