package moroom.VO;

public class People {

	String email;	//�̸���
	String pw;		//��й�ȣ
	String name;	//�̸�
	String tel;		//��ȭ��ȣ
	
	public People() {}
	
	/* @Param email; @Param pw; @Param name; @Param tel;
	 * */
	public People(String email, String pw, String name, String tel) {
		this.email = email;
		this.pw = pw;
		this.name = name;
		this.tel = tel;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	
}
