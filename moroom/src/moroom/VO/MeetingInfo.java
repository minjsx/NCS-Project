package moroom.VO;

public class MeetingInfo {
	int mi_no; //모임번호
	int u_no; //회원번호
	String mi_name; // 모임명
	String mi_date; //모임일시
	String keyword; //키워드
	String mi_loc; // 모임지역
	int mi_cap; //모임인원
	String mi_deadline; //모집기간
	
	public MeetingInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public MeetingInfo(int mino)
	{
		this.mi_no=mino;
	}
	public MeetingInfo(int mi_no, int u_no, String mi_name, String mi_date, String keyword, String mi_loc, int mi_cap, String mi_deadline) {
		// TODO Auto-generated constructor stub
		this.mi_no = mi_no;
		this.u_no = u_no;
		this.mi_name = mi_name;
		this.mi_date = mi_date;
		this.keyword = keyword;
		this.mi_loc = mi_loc;
		this.mi_cap = mi_cap;
		this.mi_deadline = mi_deadline;
	}
	
	
	public int getMi_no() 			{return mi_no;}
	public void setMi_no(int mi_no) {this.mi_no = mi_no;}
	
	public int getU_no() 			{return u_no;}
	public void setU_no(int u_no)	{this.u_no = u_no;}
	
	public String getMi_name() 				{return mi_name;}
	public void setMi_name(String mi_name) 	{this.mi_name = mi_name;}
	
	public String getMi_date() 				{return mi_date;}
	public void setMi_date(String mi_date) 	{this.mi_date = mi_date;}
	
	public String getKeyword() 				{return keyword;}
	public void setKeyword(String keyword) 	{this.keyword = keyword;}
	
	public String getMi_loc() 				{return mi_loc;}	
	public void setMi_loc(String mi_loc) 	{this.mi_loc = mi_loc;}
	
	public int getMi_cap() 					{return mi_cap;}
	public void setMi_cap(int mi_cap) 		{this.mi_cap = mi_cap;}
	
	public String getMi_deadline() 					{return mi_deadline;}
	public void setMi_deadline(String mi_deadline) 	{this.mi_deadline = mi_deadline;}
}
