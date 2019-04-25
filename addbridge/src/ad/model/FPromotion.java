package ad.model;

public class FPromotion {
	
	private int cf_no;
	private int p_no;
	private int c_no;
	
	private String p_period;
	private int p_price;
	private String p_qualification;
	private String p_deadline;
	private String p_info;
	private String p_content;
	private String p_name;
	private String pi_pimg_path;
	
	
	public FPromotion(int p_no, String p_name, int p_price, String p_deadline)
	{
		this.p_no = p_no;
		this.p_name = p_name;
		this.p_price = p_price;
		this.p_deadline = p_deadline;
	}
	
	
	
	public int getCf_no() {
		return cf_no;
	}
	public void setCf_no(int cf_no) {
		this.cf_no = cf_no;
	}
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public String getP_period() {
		return p_period;
	}
	public void setP_period(String p_period) {
		this.p_period = p_period;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public String getP_qualification() {
		return p_qualification;
	}
	public void setP_qualification(String p_qualification) {
		this.p_qualification = p_qualification;
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
	public String getP_content() {
		return p_content;
	}
	public void setP_content(String p_content) {
		this.p_content = p_content;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}



	public String getPi_pimg_path() {
		return pi_pimg_path;
	}



	public void setPi_pimg_path(String pi_pimg_path) {
		this.pi_pimg_path = pi_pimg_path;
	}

	

	
}
