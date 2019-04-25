package ad.model;

public class Creator extends Member {

	private int c_no;
	private int cg_no;
	private int mem_no;

	private String c_chanel;
	private String c_intro;
	private String c_content;
	private String c_url;
	private String c_rurl;
	private String c_name;
	private int c_count;
	private int ci_no; // ũ�������ͼ������� ��ȣ
	private int ci_price; // ũ�������ͼ������� �ܰ�
	private String ci_addr; // ũ�������ͼ������� �ּ�
	private String ci_term; // ũ�������ͼ������� �ҿ�Ⱓ
	private String ci_ad; // ũ�������ͼ������� ��ȣ����
	private String ci_region; // ũ�������ͼ������� ���ð�������
	private String ci_keyword;	//크리에이터세부정보 키워드
	
	private String img_name;
	private String img_src;
	
	public Creator(int c_count ,String c_chanel, String c_intro, String c_content)
	{
		this.c_count = c_count;
		this.c_chanel = c_chanel;
		this.c_intro = c_intro;
		this.c_content = c_content;
	}
	
	
	public Creator(String cg_name, String ci_ad, int ci_price, String c_name)
	{
		this.cg_name = cg_name;
		this.ci_ad = ci_ad;
		this.ci_price = ci_price;
		this.c_name = c_name;
	}
	
	public String getCi_keyword() {
		return ci_keyword;
	}

	public void setCi_keyword(String ci_keyword) {
		this.ci_keyword = ci_keyword;
	}

	public String getCg_name() {
		return cg_name;
	}

	public void setCg_name(String cg_name) {
		this.cg_name = cg_name;
	}

	private String cg_name;     	//카테고리 명

	public String getCi_ad() {
		return ci_ad;
	}

	public void setCi_ad(String ci_ad) {
		this.ci_ad = ci_ad;
	}

	

	public int getCi_no() {
		return ci_no;
	}

	public void setCi_no(int ci_no) {
		this.ci_no = ci_no;
	}

	public int getCi_price() {
		return ci_price;
	}

	public void setCi_price(int ci_price) {
		this.ci_price = ci_price;
	}

	public String getCi_addr() {
		return ci_addr;
	}

	public void setCi_addr(String ci_addr) {
		this.ci_addr = ci_addr;
	}

	public String getCi_term() {
		return ci_term;
	}

	public void setCi_term(String ci_term) {
		this.ci_term = ci_term;
	}

	public String getCi_region() {
		return ci_region;
	}

	public void setCi_region(String ci_region) {
		this.ci_region = ci_region;
	}

	public int getC_count() {
		return c_count;
	}

	public void setC_count(int c_count) {
		this.c_count = c_count;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public Creator() {
		super();
	}

	public Creator(String c_chanel, String c_intro, String c_content) {
		this.c_chanel = c_chanel;
		this.c_intro = c_intro;
		this.c_content = c_content;
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public int getCg_no() {
		return cg_no;
	}

	public void setCg_no(int cg_no) {
		this.cg_no = cg_no;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public String getC_chanel() {
		return c_chanel;
	}

	public void setC_chanel(String c_chanel) {
		this.c_chanel = c_chanel;
	}

	public String getC_intro() {
		return c_intro;
	}

	public void setC_intro(String c_intro) {
		this.c_intro = c_intro;
	}

	public String getC_content() {
		return c_content;
	}

	public void setC_content(String c_content) {
		this.c_content = c_content;
	}

	public String getC_url() {
		return c_url;
	}

	public void setC_url(String c_url) {
		this.c_url = c_url;
	}

	public String getC_rurl() {
		return c_rurl;
	}

	public void setC_rurl(String c_rurl) {
		this.c_rurl = c_rurl;
	}


	public String getImg_src() {
		return img_src;
	}


	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}


	public String getImg_name() {
		return img_name;
	}


	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
}
