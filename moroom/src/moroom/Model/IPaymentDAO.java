package moroom.Model;

import java.sql.SQLException;
import java.util.ArrayList;

import moroom.View.UserClient;

public interface IPaymentDAO {
	abstract void Select_Pi_No(int pi_No);
	abstract void Select_M_No(int m_No);
	abstract void Select_Sc_No(int sc_No);
	// ��¥�� ��¥���̿� �ִ� ������ȸ
	// startPoint : ã�� ��¥ ������, endPoint : ã�� ��¥  ����
	abstract void Select_Date(int pi_No, String startPoint, String endPoint);
	
	//ĳ���������� ����Ʈ ��������
	abstract ArrayList Select_payCashInfo(int u_no) throws Exception;
	
	//ĳ�û�볻�� ����Ʈ ��������
	abstract ArrayList Select_useCashInfo(int u_no) throws Exception;
	
	abstract void insert_paymentAndResinfo(int sr_no, int money, int checkpayment, int mno, String resDate, String startTime, String endTime) throws SQLException;
	
	abstract int checkmeetingstate(int mno) throws SQLException;

}