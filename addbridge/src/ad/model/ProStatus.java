package ad.model;

public class ProStatus {
	String c_name;
	String cg_name;
	String p_name;
	int c_count;
	String p_qualification;
	int c_no;
	int p_no;
		
	public ProStatus(String c_name, String cg_name, String p_name, int c_count, String p_qualification, int c_no, int p_no) {
		this.c_name = c_name;
		this.cg_name = cg_name;
		this.p_name = p_name;
		this.c_count = c_count;
		this.p_qualification = p_qualification;
		this.c_no = c_no;
		this.p_no = p_no;
	}
	
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getCg_name() {
		return cg_name;
	}
	public void setCg_name(String cg_name) {
		this.cg_name = cg_name;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getC_count() {
		return c_count;
	}
	public void setC_count(int c_count) {
		this.c_count = c_count;
	}
	public String getP_qualification() {
		return p_qualification;
	}
	public void setP_qualification(String p_qualification) {
		this.p_qualification = p_qualification;
	}
	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	
}
