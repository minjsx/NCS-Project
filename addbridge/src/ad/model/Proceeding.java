package ad.model;

public class Proceeding {
	private String p_name;
	private String pp_startDate;
	private String m_tel;
	private String m_email;
	private String ci_region;
	private String c_name;
	private String p_info;
	private String p_content;
	private int p_price;
	private String p_period;
	private int Proceeding_no;
	private int c_no;
	

	public Proceeding(String p_name, String pp_startDate, String m_tel, String m_email, String ci_region, String c_name,
			String p_info, String p_content, int p_price, String p_period, int Proceeding_no, int c_no) {

		this.p_name = p_name;
		this.pp_startDate = pp_startDate;
		this.m_tel = m_tel;
		this.m_email = m_email;
		this.ci_region = ci_region;
		this.c_name = c_name;
		this.p_info = p_info;
		this.p_content = p_content;
		this.p_price = p_price;
		this.p_period = p_period;
		this.Proceeding_no = Proceeding_no;
		this.c_no = c_no;

	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getPp_startDate() {
		return pp_startDate;
	}

	public void setPp_startDate(String pp_startDate) {
		this.pp_startDate = pp_startDate;
	}

	public String getM_tel() {
		return m_tel;
	}

	public void setM_tel(String m_tel) {
		this.m_tel = m_tel;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getCi_region() {
		return ci_region;
	}

	public void setCi_region(String ci_region) {
		this.ci_region = ci_region;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
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

	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}

	public String getP_period() {
		return p_period;
	}

	public void setP_period(String p_period) {
		this.p_period = p_period;
	}

	public int getProceeding_no() {
		return Proceeding_no;
	}

	public void setProceeding_no(int proceeding_no) {
		Proceeding_no = proceeding_no;
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	
	
}
