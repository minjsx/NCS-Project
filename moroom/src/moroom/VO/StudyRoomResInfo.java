package moroom.VO;

public class StudyRoomResInfo {

	private int sr_resno; //룸예약번호
	private int sr_no;		//스터디룸번호
	private String sr_resdate; //예약날짜
	private String sr_start_time; //예약시작시간
	private String sr_end_time; // 예약 종료시간
	
	//생성자
	public StudyRoomResInfo(int sr_resno, int sr_no, String _date, String _start , String _end)
	{
		this.sr_resno = sr_resno;
		this.sr_no = sr_no;
		this.sr_resdate = _date;
		this.sr_start_time = _start;
		this.sr_end_time = _start;
	}
	
	//get & set
	public int getSr_resno() {
		return sr_resno;
	}
	public void setSr_resno(int sr_resno) {
		this.sr_resno = sr_resno;
	}
	public int getSr_no() {
		return sr_no;
	}
	public void setSr_no(int sr_no) {
		this.sr_no = sr_no;
	}
	public String getSr_resdate() {
		return sr_resdate;
	}
	public void setSr_resdate(String sr_resdate) {
		this.sr_resdate = sr_resdate;
	}
	public String getSr_start_time() {
		return sr_start_time;
	}
	public void setSr_start_time(String sr_start_time) {
		this.sr_start_time = sr_start_time;
	}
	public String getSr_end_time() {
		return sr_end_time;
	}
	public void setSr_end_time(String sr_end_time) {
		this.sr_end_time = sr_end_time;
	}
	
}
