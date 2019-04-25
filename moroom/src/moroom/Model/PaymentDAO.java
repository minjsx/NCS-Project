package moroom.Model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class PaymentDAO implements IPaymentDAO{
	private Connection 	conn = null;
	private Statement 	stmt = null;
	private PreparedStatement pstmt = null;

	
	// 생성자
	public PaymentDAO(Connection _conn)
	{
		conn =_conn;
	}
	
	
	// 메소드
	@Override
	public void Select_Pi_No(int pi_No) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Select_M_No(int m_No) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Select_Sc_No(int sc_No) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Select_Date(int pi_No, String startPoint, String endPoint) {
		// TODO Auto-generated method stub
		
	}
	
	//캐시충전내역 리스트 가져오기
	@Override
	public ArrayList Select_payCashInfo(int u_no) throws Exception {
		String sql = "select CI_MONEY, CI_DATE from cash_info where u_no = ?"; 
		
		ArrayList list = new ArrayList();
		
		pstmt = conn.prepareStatement(sql);   	

		pstmt.setInt(1, u_no);	
		ResultSet rs = pstmt.executeQuery();
						
						
		while(rs.next()) {
			ArrayList temp = new ArrayList();
					
			temp.add(rs.getString("CI_DATE"));
			temp.add(rs.getInt("CI_MONEY"));
			
			list.add(temp);
		}
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
				 
		rs.close();
		pstmt.close();

		return list;
	}

	//캐시 사용내역 리스트 가져오기
	@Override
	public ArrayList Select_useCashInfo(int u_no) throws Exception {
		
		String sql = "select p.p_date P_DATE,  mi.mi_name MI_NAME, sc.sc_name SC_NAME, sr.sr_name SR_NAME, p.p_cash P_CASH" + 
					 " from participation p, meeting m, meeting_info mi, study_room sr, study_center sc" + 
					 " where p.u_no = ? and p.m_no = m.m_no and m.mi_no = mi.mi_no and sr.sr_no = m.sr_no and sc.sc_no = sr.sc_no" + 
					 " order by p_date asc"; 
		
		
		ArrayList list = new ArrayList();
		
		pstmt = conn.prepareStatement(sql);   	
		pstmt.setInt(1, u_no);	
		ResultSet rs = pstmt.executeQuery();
						
		while(rs.next()) {
			ArrayList temp = new ArrayList();
					
			temp.add(rs.getString("P_DATE"));
			temp.add(rs.getString("MI_NAME"));
			temp.add(rs.getString("SC_NAME"));
			temp.add(rs.getString("SR_NAME"));
			temp.add(rs.getInt("P_CASH"));
			
			list.add(temp);
		}
		rs.close();
		pstmt.close();

		return list;
	}


	@Override
	public void insert_paymentAndResinfo(int sr_no, int money, int checkpayment, int mno,
							String resDate, String startTime, String endTime) throws SQLException
	{
		CallableStatement cs = conn.prepareCall("{call insertPayment(?,?,?,?,?,?,?)}");
		
		cs.setInt(1,sr_no);
		cs.setInt(2, money);
		cs.setInt(3, checkpayment);
		cs.setInt(4, mno);
		cs.setString(5, resDate);
		cs.setString(6, startTime);
		cs.setString(7, endTime);
		System.out.println("프로시저 호출 완료");
		cs.execute();
	}


	@Override
	public int checkmeetingstate(int mno) throws SQLException{
		String sql= "select ms_no from meeting where m_no = " + mno;
		
		int check =0;
		pstmt =conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next())
		{
			check = rs.getInt("ms_no");
			System.out.println(check);
		}
		return check;
	}
}
