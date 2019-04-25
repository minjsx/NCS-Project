package moroom.Controller;

import java.sql.SQLException;

import moroom.VO.People;

public class MainController {

	ModelController mc = null;
	ViewController vc = null;
	
	// ������
	public MainController()
	{
		mc = new ModelController();
	}
	
	// �޼ҵ� =========================================
	// �α���
	public void login(String email, String pw, int flag) throws Exception
	{
		mc.login(email,pw,flag);
		vc = new ViewController(mc,flag,email);
	}
	
	// �Ϲݻ���� ȸ������
	public void signUpUsers(People peo, String category)throws SQLException
	{
		mc.signUpUsers(peo,category);
	}
	
	// ����� ȸ������
	public void signUpBusiness(People peo, String crn, String ceo) throws SQLException
	{
		mc.signUpBusiness(peo,crn,ceo);
	}
	
	//���̵� �ߺ� Ȯ��
	public boolean emailOverlap(String email,int flag) throws Exception
	{
		boolean bool =  mc.emailOverlap(email,flag);
		return bool;
	}
	
}
