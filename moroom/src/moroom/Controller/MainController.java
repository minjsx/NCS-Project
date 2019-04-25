package moroom.Controller;

import java.sql.SQLException;

import moroom.VO.People;

public class MainController {

	ModelController mc = null;
	ViewController vc = null;
	
	// 생성자
	public MainController()
	{
		mc = new ModelController();
	}
	
	// 메소드 =========================================
	// 로그인
	public void login(String email, String pw, int flag) throws Exception
	{
		mc.login(email,pw,flag);
		vc = new ViewController(mc,flag,email);
	}
	
	// 일반사용자 회원가입
	public void signUpUsers(People peo, String category)throws SQLException
	{
		mc.signUpUsers(peo,category);
	}
	
	// 사업자 회원가입
	public void signUpBusiness(People peo, String crn, String ceo) throws SQLException
	{
		mc.signUpBusiness(peo,crn,ceo);
	}
	
	//아이디 중복 확인
	public boolean emailOverlap(String email,int flag) throws Exception
	{
		boolean bool =  mc.emailOverlap(email,flag);
		return bool;
	}
	
}
