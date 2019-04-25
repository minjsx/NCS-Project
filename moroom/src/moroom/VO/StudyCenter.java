package moroom.VO;

public class StudyCenter {

	private int sc_no;			// ���͵��� ��ȣ
	private int b_no; 			// ��ü ��ȣ
	private String sc_name; 	// ���͵��� ��
	private String sc_loc; 		// ������
	private String sc_start;	// ������ .. DB insert�ÿ��� date������!
	private String sc_open;		// ����ð�
	private String sc_close; 	// ����ð�
	private String sc_cancel;	// ��ұ���
	private int sc_fph; 		// �ð��� �̿���
	private String sc_picture; //���͵��� ����
	
	public StudyCenter() {
		// TODO Auto-generated constructor stub
	}
	
	public StudyCenter(String name) 
	{
		this.sc_name = name;
	}
	
	//������ 
	public StudyCenter(String name, String sc_loc, String start, String open,String close, String cancel, int fph, String sc_picture)
	{
		this.sc_name = name;
		this.sc_loc = sc_loc;
		this.sc_start = start;
		this.sc_open = open;
		this.sc_close = close;
		this.sc_cancel = cancel;
		this.sc_fph = fph;
		this.sc_picture = sc_picture;
	}
	
	//region Get & Set �޼ҵ�
	public int getSc_no() {
		return sc_no;
	}
	public void setSc_no(int sc_no) {
		this.sc_no = sc_no;
	}
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public String getSc_name() {
		return sc_name;
	}
	public void setSc_name(String sc_name) {
		this.sc_name = sc_name;
	}
	public String getSc_loc() {
		return sc_loc;
	}
	public void setSc_loc(String sc_loc) {
		this.sc_loc = sc_loc;
	}

	public String getSc_open() {
		return sc_open;
	}
	public void setSc_open(String sc_open) {
		this.sc_open = sc_open;
	}
	public String getSc_close() {
		return sc_close;
	}
	
	public void setSc_close(String sc_close) {
		this.sc_close = sc_close;
	}
	
	public String getSc_cancel() {
		return sc_cancel;
	}
	
	public void setSc_cancel(String sc_cancel) {
		this.sc_cancel = sc_cancel;
	}
	
	public int getSc_fph() {
		return sc_fph;
	}
	
	public void setSc_fph(int sc_fph) {
		this.sc_fph = sc_fph;
	}

	
	public String getSc_start() {
		return sc_start;
	}

	public void setSc_start(String sc_start) {
		this.sc_start = sc_start;
	}

	public String getSc_picture() {
		return sc_picture;
	}

	public void setSc_picture(String sc_picture) {
		this.sc_picture = sc_picture;
	}
	
	
	//endregion
	
}
