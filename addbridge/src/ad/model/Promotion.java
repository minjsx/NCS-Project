package ad.model;

public class Promotion {
	private int p_no;
	private String p_name;
	private int p_price;
	private String p_content;
	private String p_qualification;
	private String p_register;
	private String p_deadline;
	private String p_info;
	private String p_period;
	private String sname;
	private String pc_name; // 프로모션카테고리 명
	private String pimg_path;
	private String aimg_path; 
	
	private int s_no;
	private int cg_no;
	private int pc_no;
	private int a_no;
	
	

	public void setPc_name(String pc_name) {
		this.pc_name = pc_name;
	}

	public String getPc_name() {
		return pc_name;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Promotion() {
	}

	public Promotion(String p_register, String pc_name, String p_name, int p_price, String p_qualification, String p_period, String sname,int p_no) {
		this.p_register = p_register;
		this.pc_name = pc_name;
		this.p_name = p_name;
		this.p_price = p_price;
		this.p_qualification = p_qualification;
		this.p_period = p_period;
		this.sname = sname;
		this.p_no = p_no;
	}
	
	public Promotion(String p_period, int p_price, String p_qualification, String p_deadline, String p_content,
			String p_name, String p_info) {
		this.p_period = p_period;
		this.p_price = p_price;
		this.p_qualification = p_qualification;
		this.p_deadline = p_deadline;
		this.p_content = p_content;
		this.p_name = p_name;
		this.p_info = p_info;
	}

	public Promotion(String p_register, String p_deadline, String p_content, String p_name, int p_price,
			String p_period, String sname) {
		this.p_register = p_register;
		this.p_deadline = p_deadline;
		this.p_content = p_content;
		this.p_name = p_name;
		this.p_price = p_price;
		this.p_period = p_period;
		this.sname = sname;
	}

	public Promotion(String p_period, int p_price, String p_register, String p_deadline, String p_content,
			String p_name) {
		this.p_period = p_period;
		this.p_price = p_price;
		this.p_register = p_register;
		this.p_deadline = p_deadline;
		this.p_content = p_content;
		this.p_name = p_name;

	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}

	public String getP_content() {
		return p_content;
	}

	public void setP_content(String p_content) {
		this.p_content = p_content;
	}

	public String getP_qualification() {
		return p_qualification;
	}

	public void setP_qualification(String p_qualification) {
		this.p_qualification = p_qualification;
	}

	public String getP_register() {
		return p_register;
	}

	public void setP_register(String p_register) {
		this.p_register = p_register;
	}

	public String getP_deadline() {
		return p_deadline;
	}

	public void setP_deadline(String p_deadline) {
		this.p_deadline = p_deadline;
	}

	public String getP_info() {
		return p_info;
	}

	public void setP_info(String p_info) {
		this.p_info = p_info;
	}

	public String getP_period() {
		return p_period;
	}

	public void setP_period(String p_period) {
		this.p_period = p_period;
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public int getCg_no() {
		return cg_no;
	}

	public void setCg_no(int cg_no) {
		this.cg_no = cg_no;
	}

	public int getPc_no() {
		return pc_no;
	}

	public void setPc_no(int pc_no) {
		this.pc_no = pc_no;
	}

	public int getA_no() {
		return a_no;
	}

	public void setA_no(int a_no) {
		this.a_no = a_no;
	}

	public String getPimg_path() {
		return pimg_path;
	}

	public void setPimg_path(String pimg_path) {
		this.pimg_path = pimg_path;
	}

	public String getAimg_path() {
		return aimg_path;
	}

	public void setAimg_path(String aimg_path) {
		this.aimg_path = aimg_path;
	}

}
