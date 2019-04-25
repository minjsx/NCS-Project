package ad.model;

public class PromotionCategory {
	private int pc_no;			//프로모션카테고리 번호
	private String pc_name;		//프로모션카테고리 명
	
	public int getPc_no() {
		return pc_no;
	}
	public void setPc_no(int pc_no) {
		this.pc_no = pc_no;
	}
	public String getPc_name() {
		return pc_name;
	}
	public void setPc_name(String pc_name) {
		this.pc_name = pc_name;
	}
	
	public PromotionCategory(int pc_no, String pc_name) {
		this.pc_no = pc_no;
		this.pc_name = pc_name;
	}
	
}
