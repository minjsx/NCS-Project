package moroom.VO;

public class Participation {
	
	private int u_no;		// 유저번호
	private int m_no;		// 스터디 모임 번호
	private int p_cash;		// 지불 캐쉬 금액
	
	public Participation(){}
	
	public Participation(int u_no, int m_no, int p_cash) {
		this.u_no = u_no;
		this.m_no = m_no;
		this.p_cash = p_cash;
	}
	
	public int getU_no() {
		return u_no;
	}

	public void setU_no(int u_no) {
		this.u_no = u_no;
	}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public int getP_cash() {
		return p_cash;
	}

	public void setP_cash(int p_cash) {
		this.p_cash = p_cash;
	}
	
	
}
