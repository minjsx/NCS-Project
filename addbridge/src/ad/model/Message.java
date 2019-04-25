package ad.model;

public class Message {
	
	private int msg_no;   		//�޼��� ��ȣ
	private int msg_sno;  		//������ ȸ����ȣ
	private int msg_rno;  		//�޴� ȸ����ȣ
	private int proceeding_no;
	private String msg_content; //����
	private String msg_date;    //��¥
	
	private String cName; //크리에이터명
	private String aName; //광고주명
	private String sendName;
	
	public Message(int msg_no, int msg_sno, int msg_rno, String msg_content, String msg_date, int proceeding_no, String cName, String aName,
			String sendName) {
		
		this.msg_no = msg_no;
		this.msg_sno = msg_sno;
		this.msg_rno = msg_rno;
		this.proceeding_no = proceeding_no;
		this.msg_content = msg_content;
		this.msg_date = msg_date;
		this.cName = cName;
		this.aName = aName;
		this.sendName = sendName;
	}
	
	public int getMsg_no() {
		return msg_no;
	}
	public void setMsg_no(int msg_no) {
		this.msg_no = msg_no;
	}
	public int getMsg_sno() {
		return msg_sno;
	}
	public void setMsg_sno(int msg_sno) {
		this.msg_sno = msg_sno;
	}
	public int getMsg_rno() {
		return msg_rno;
	}
	public void setMsg_rno(int msg_rno) {
		this.msg_rno = msg_rno;
	}

	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public String getMsg_date() {
		return msg_date;
	}
	public void setMsg_date(String msg_date) {
		this.msg_date = msg_date;
	}
	public int getProceeding_no() {
		return proceeding_no;
	}

	public void setProceeding_no(int proceeding_no) {
		this.proceeding_no = proceeding_no;
	}
	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

		
}
