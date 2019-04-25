package moroom.VO;

public class Meeting {

	int m_no; //스터디모임번호
	int c_no; //카테고리번호
	int ms_no; //모임상태번호
	int mi_no; // 모임번호
	int sr_no; //스터디룸번호
	int m_pay; //모임회비
	
	public Meeting() {
		// TODO Auto-generated constructor stub
	}
	
	public Meeting(int m_no, int c_no, int ms_no, int mi_no, int sr_no, int m_pay) {
		// TODO Auto-generated constructor stub
		this.m_no = m_no;
		this.c_no = c_no;
		this.ms_no = ms_no;
		this.mi_no = mi_no;
		this.sr_no = sr_no;
		this.m_pay = m_pay;	
	}
	
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public int getMs_no() {
		return ms_no;
	}
	public void setMs_no(int ms_no) {
		this.ms_no = ms_no;
	}
	public int getMi_no() {
		return mi_no;
	}
	public void setMi_no(int mi_no) {
		this.mi_no = mi_no;
	}
	public int getSr_no() {
		return sr_no;
	}
	public void setSr_no(int sr_no) {
		this.sr_no = sr_no;
	}
	public int getM_pay() {
		return m_pay;
	}
	public void setM_pay(int m_pay) {
		this.m_pay = m_pay;
	}
}
