package ad.model;

public class YoutuberMain {
	
	
	private int p_no;
	private String state; //상태
	private String category; //카테고리
	private String proCategory; //프로모션카테고리
	private String advertiser; //광고주
	private String proName; //프로모션이름
	private String proCont; //프로모션내용
	private String proPrice; //단가
	private String startDate; //등록일자
	private String endDate; //마감일자
	private String proImage; //프로모션이미지
	private String adImage; //광고주이미지
	
	public String getProImage() {
		return proImage;
	}
	public void setProImage(String proImage) {
		this.proImage = proImage;
	}
	public String getAdImage() {
		return adImage;
	}
	public void setAdImage(String adImage) {
		this.adImage = adImage;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProCategory() {
		return proCategory;
	}
	public void setProCategory(String proCategory) {
		this.proCategory = proCategory;
	}
	public String getAdvertiser() {
		return advertiser;
	}
	public void setAdvertiser(String advertiser) {
		this.advertiser = advertiser;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProCont() {
		return proCont;
	}
	public void setProCont(String proCont) {
		this.proCont = proCont;
	}
	public String getProPrice() {
		return proPrice;
	}
	public void setProPrice(String proPrice) {
		this.proPrice = proPrice;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	
	public YoutuberMain(int p_no, String state, String category, String proCategory, String advertiser,
						String proName, String proCont, String proPrice, String startDate, String endDate,
						String proImage, String adImage) {
		
		this.p_no = p_no;
		this.state = state;
		this.category = category;
		this.proCategory = proCategory;
		this.advertiser = advertiser;
		this.proName = proName;
		this.proCont = proCont;
		this.proPrice = proPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adImage = adImage;
		this.proImage = proImage;
	}
	
	public YoutuberMain(int p_no, String state, String category, String proCategory, String advertiser,
			String proName, String proCont, String proPrice, String startDate, String endDate, String adImage) {

		this.p_no = p_no;
		this.state = state;
		this.category = category;
		this.proCategory = proCategory;
		this.advertiser = advertiser;
		this.proName = proName;
		this.proCont = proCont;
		this.proPrice = proPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adImage = adImage;
}

}
