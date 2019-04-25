package ad.model;

public class Advertiser extends Member {	
	private int a_no; 
	private String a_ceo;
	private String a_name;
	private String a_eno;
	private String a_tel;
	private String img_name;
	private String img_src;
	private int cash;
	
	public Advertiser() {
		super();
	}
	
	private String logo_img;
	
	public String getLogo_img() {
		return logo_img;
	}


	public void setLogo_img(String logo_img) {
		this.logo_img = logo_img;
	}

	public int getA_no() {
		return a_no;
	}
	public void setA_no(int a_no) {
		this.a_no = a_no;
	}
	public String getA_ceo() {
		return a_ceo;
	}
	public void setA_ceo(String a_ceo) {
		this.a_ceo = a_ceo;
	}
	public String getA_name() {
		return a_name;
	}
	public void setA_name(String a_name) {
		this.a_name = a_name;
	}
	public String getA_eno() {
		return a_eno;
	}
	public void setA_eno(String a_eno) {
		this.a_eno = a_eno;
	}
	public String getA_tel() {
		return a_tel;
	}
	public void setA_tel(String a_tel) {
		this.a_tel = a_tel;
	}


	public String getImg_name() {
		return img_name;
	}


	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}


	public String getImg_src() {
		return img_src;
	}


	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}
}
