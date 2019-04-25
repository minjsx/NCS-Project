package moroom.VO;

public class StudyRoom {
	
	private int sr_no; 			// 스터디룸 번호
	private int sc_no;			// 스터디센터 번호
	private String sr_name; 	// 스터디룸 명
	private int sr_cap;			// 스터디룸 최대 수용가능인원
	private String sr_type;      //스터디룸 유형
	private int sr_addf;		// 추가요금
	private String sr_exp;		// 부가시설
	private String sr_picture;  //스터디룸 사진
	private String sr_ex; 		//스터디룸 설명
	
	
	public StudyRoom() {
		
		
	}
	//생성자
	public StudyRoom(String name, int cap, String type, int addf, String exp, String picture)
	{		
		this.sr_name = name;
		this.sr_cap = cap;
		this.sr_type = type;
		this.sr_addf = addf;
		this.sr_exp = exp;		
		this.sr_picture = picture;
	}

	public StudyRoom(String name, int cap, int addf, String exp)
	{
		this.sr_name = name;
		this.sr_cap = cap;
		this.sr_addf = addf;
		this.sr_exp = exp;
	}
	//생성자
	public StudyRoom(int _sc_no, String name, int cap, int addf, String exp)
	{
		this.sc_no = _sc_no;
		this.sr_name = name;
		this.sr_cap = cap;
		this.sr_addf = addf;
		this.sr_exp = exp;
		
	}
	
	//region Get & Set 메소드
	
	public int getSr_no() {
		return sr_no;
	}


	public void setSr_no(int sr_no) {
		this.sr_no = sr_no;
	}


	public int getSc_no() {
		return sc_no;
	}


	public void setSc_no(int sc_no) {
		this.sc_no = sc_no;
	}


	public String getSr_name() {
		return sr_name;
	}


	public void setSr_name(String sr_name) {
		this.sr_name = sr_name;
	}


	public int getSr_cap() {
		return sr_cap;
	}


	public void setSr_cap(int sr_cap) {
		this.sr_cap = sr_cap;
	}


	public String getSr_type() {
		return sr_type;
	}


	public void setSr_type(String sr_type) {
		this.sr_type = sr_type;
	}


	public int getSr_addf() {
		return sr_addf;
	}


	public void setSr_addf(int sr_addf) {
		this.sr_addf = sr_addf;
	}


	public String getSr_exp() {
		return sr_exp;
	}


	public void setSr_exp(String sr_exp) {
		this.sr_exp = sr_exp;
	}

	public String getSr_picture() {
		return sr_picture;
	}

	public void setSr_picture(String sr_picture) {
		this.sr_picture = sr_picture;
	}
			
	
	//endregion

	
}
