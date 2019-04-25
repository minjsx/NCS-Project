package ad.model;

public class Image {
	private int img_no;        //이미지 번호
	private String img_name;   //이미지 명
	private String img_path;   //이미지 경로
	
	public Image() {}
	
	public int getImg_no() {
		return img_no;
	}
	public void setImg_no(int img_no) {
		this.img_no = img_no;
	}
	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

}
