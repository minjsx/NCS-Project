package moroom.Model;

import java.sql.SQLException;
import java.util.ArrayList;

import moroom.View.UserClient;

public interface IPaymentDAO {
	abstract void Select_Pi_No(int pi_No);
	abstract void Select_M_No(int m_No);
	abstract void Select_Sc_No(int sc_No);
	// 날짜와 날짜사이에 있는 내약조회
	// startPoint : 찾을 날짜 시작점, endPoint : 찾을 날짜  끝점
	abstract void Select_Date(int pi_No, String startPoint, String endPoint);
	
	//캐시충전내역 리스트 가져오기
	abstract ArrayList Select_payCashInfo(int u_no) throws Exception;
	
	//캐시사용내역 리스트 가져오기
	abstract ArrayList Select_useCashInfo(int u_no) throws Exception;
	
	abstract void insert_paymentAndResinfo(int sr_no, int money, int checkpayment, int mno, String resDate, String startTime, String endTime) throws SQLException;
	
	abstract int checkmeetingstate(int mno) throws SQLException;

}