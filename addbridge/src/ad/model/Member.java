package ad.model;

public class Member {

	private int memNo;
	private int type;
	private String email;
	private String password;
	private String name;
	private String phone;

	public Member() {

	}
	
	public Member(String email, String phone, String name) {
		this.email = email;
		this.phone = phone;
		this.name = name;
	}
	
	public Member(String email) {
		this.email = email;
	}

	public Member(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
