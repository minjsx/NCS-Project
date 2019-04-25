package moroom.Controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import moroom.Model.BusinessDAO;
import moroom.Model.ConnectionPool;
import moroom.Model.MeetingDAO;
import moroom.Model.PaymentDAO;
import moroom.Model.UsersDAO;
import moroom.VO.*;
import moroom.View.UserClient;

public class ModelController {

	private UsersDAO 		users = null;
	private BusinessDAO 	business = null;
	private PaymentDAO 		payment = null;
	private MeetingDAO		meeting = null;
	private Connection 		conn = null;
	
	// ������
	public ModelController()
	{
		try {
			connectDB();
			System.out.println("DB���� ����");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DB���� ����:"+e.getMessage());
		}
		
		users = new UsersDAO(conn);
		business = new BusinessDAO(conn);
		payment = new PaymentDAO(conn);
		meeting = new MeetingDAO(conn);
		
	}
	
	//DB����
	private void connectDB() throws Exception
	{
		conn = ConnectionPool.getInstance(); //db���� ����
		//conn.setAutoCommit(false); ����Ŀ�� ���úκ�
	}
	
	// �޼ҵ� ============================================
	// �α���
	public void login(String email, String pw, int flag) throws Exception
	{
		if(flag==1) //����ȸ��
		{
			//����ȸ�� ���̵�� ��й�ȣ �´��� Ȯ���ϱ�
			boolean bool = users.emailPwCompare(email,pw);
			if(!bool)
				throw new Exception("���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		else // ����� ȸ��
		{
			//����� ȸ�� ���̵�� ��й�ȣ �´��� Ȯ���ϱ�
			boolean bool = business.emailPwCompare(email,pw);
			if(!bool)
				throw new Exception("���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
	}
	
	// �Ϲݻ���� ȸ������
	public void signUpUsers(People peo, String category) throws SQLException
	{
		int c_no = users.categoryNameToNum(category);
		Users user = new Users(peo,c_no);
		users.users_Insert(user);
	}
	
	// ����� ȸ������
	public void signUpBusiness(People peo, String crn, String ceo)
	{
		
		Business bin = new Business(peo,crn,ceo);
		business.business_Insert(bin);
	}
	
	//���̵� �ߺ� Ȯ��
	public boolean emailOverlap(String email,int flag) throws Exception
	{
		boolean bool;
		if(flag==1) //����ȸ��
		{
			bool =  users.email_Overlap(email);
		}
		else //����� ȸ��
		{
			bool =  business.email_Overlap(email);
		}	
		return bool;
	}
	
	// email�� ���� Users ��ü������
	public People emailSearchToUsers(String email) throws SQLException
	{
		People peo = users.emailSearchToUsers(email);
		return peo;
	}

	// email�� ���� Business ��ü������
	public People emailSearchToBusiness(String email) throws SQLException
	{
		People peo = business.emailSearchToBusiness(email);
		return peo;
	}

	// users�� ĳ�� �����ϱ�
	public void cashAdd(Integer cash,String email) throws SQLException
	{
		users.cashAdd(cash,email);
	}
	
	// ĳ�� �����������̺� �߰�
	public void cashInfo_Insert(CashInfo cashinfo) throws SQLException
	{
		users.cashInfo_Insert(cashinfo);
	}
	
	// users update
	public void updateUserInfo(People peo, String category) throws SQLException
	{
		int c_no = users.categoryNameToNum(category);
		Users user = new Users(peo,c_no);
		users.users_Update(user);
	}

	// Meeting Info Table ����
	public boolean meetingInfo_Insert(UserClient u, String roomName, String date,String keyword, String local,int total, String deadline, String category, int roomNum, int total_money) throws Exception
	{
		return meeting.meetingInfo_Insert(u, roomName, date, keyword, local, 
				total, deadline,category,roomNum,total_money);
	}
	
	// ���͵���� �����ϱ�
	public void meetingInfo_update(MeetingInfo mi) throws SQLException
	{
		meeting.meetingInfo_update(mi);
	}
	
	// ���ӹ�ȣ�� ����������ȣ ������
	public int mnoTomino(int mno) throws SQLException
	{
		return meeting.mnoTomino(mno);
	}
	
	// ���ӹ�ȣ�� ���͸����ȣ ������
	public int mnoTosrno(int mno) throws SQLException
	{
		return meeting.mnoTosrno(mno);
	}
	
	// Meeting �˻�
	public ArrayList searchMeeting(int key, String text) throws Exception
	{
		ArrayList list = meeting.meetinginfo_Select(key, text);
		return list;
	}
	
	// ������ü
	public void Meeting_Delete(int mno)throws SQLException {
		meeting.meeting_Delete(mno);
	}

	//���� ī�װ� ���͸� meeting �˻�
	public ArrayList search_MeetingInfo_category(String email) throws SQLException
	{
		return meeting.search_meetingInfo_category(email);
	}
	
	//���ӻ����� ������
	public ArrayList search_studyListDetail(int mno) throws SQLException
	{
		ArrayList list = meeting.search_studyListDetail(mno);
		return list;
	}
	
	
	// region ��˻� ���
	public ArrayList search_CenterByName(int sel, String str) throws SQLException
	{
		ArrayList list = meeting.search_CenterByName(sel, str);
		return list;
	}
	
	public ArrayList search_roomByCenter(String str) throws SQLException
	{
		ArrayList list = meeting.search_roomByCenter(str);
		return list;
	}
	// endregion
	
	
	// ���� , ���͵�� ������ ���� �˻�
	public ArrayList centerSearch(String str) throws Exception
	{
		ArrayList list = users.search_Center(str);
		return list;
	}
	

	
	// region ��¿� ���̺� 
	public StudyCenter selectByName(String sname) throws SQLException{
		StudyCenter sc = users.selecByName(sname);
		return sc;
	}
	public StudyRoom selectByroom(String cname,String rname) throws SQLException{
		StudyRoom sr = users.selecByroom(cname ,rname);
		return sr;
	}
	// endregion
	
		public ArrayList myinfoview(UserClient u) throws Exception {
			ArrayList list = meeting.myMeeting(u);
			return list;
		}
	
		public ArrayList detailRoomInfo(String centerN, String roomN) throws Exception {
			ArrayList list = meeting.detailRoomInfo(centerN, roomN);
			return list;
		}
	
		// �����ϱ⸦ ���Ͽ� �ѽð� �̿��� ����
		public void participation_CashSub(int mno, int fph) throws SQLException {
			users.participation_CashSub(mno, fph);
		}
	
		// �����������̺� �߰��ϱ�
		public void participation_Insert(Participation part) throws SQLException {
			users.participation_Insert(part);
		}
	
		public void participation_Delete(Participation part) throws SQLException
		{
			users.participation_Delete(part);
		}
		
		// �̹� �������� �������� �Ǻ�
		public boolean partcipation_Overlap(Participation part) throws SQLException {
			return users.partcipation_Overlap(part);
		}
	
		// �����ϱ� �Ϸ�ÿ� �ش� ���� ȸ�� update
		public void meeting_UpdatePay(int m_no, int fph, int flag) throws SQLException {
			meeting.meeting_UpdatePay(m_no, fph, flag);
		}
	
		///ĳ�� �������� ����Ʈ ��������
		public ArrayList Select_payCashInfo(int u_no) throws Exception {
			ArrayList list = payment.Select_payCashInfo(u_no);

			return list;
		}
		
		///ĳ�� ��볻�� ����Ʈ ��������
		public ArrayList Select_useCashInfo(int u_no) throws Exception {
			ArrayList list = payment.Select_useCashInfo(u_no);
			return list;
		}
		
		// ���系��, �������� �߰�
		public void insert_paymentAndResinfo(int sr_no, int money, int checkpayment, int mno, String resDate,
				String startTime, String endTime) throws SQLException {
			payment.insert_paymentAndResinfo(sr_no, money, checkpayment, mno, resDate, startTime, endTime);
		}
	
		public int checkmeetingstate(int mno) throws SQLException {
			int check = payment.checkmeetingstate(mno);
			return check;
		}
	
	//Business�κ�==========================================================================================
	
	//���� ����
		public void studyCenter_Insert(StudyCenter sc, int b_no) throws Exception{
			
			business.studyCenter_Insert(sc, b_no);
		}
		
		//���� ����
		public void studyCenter_Update(StudyCenter sc, String sc_name) throws Exception{
			
			business.studyCenter_Update(sc, sc_name);
		}
		
		//���� ����
		public void studyCenter_Delete(int b_no, String sc_name) throws Exception{
			
			business.studyCenter_Delete(b_no, sc_name);
		}
		
		
		//���Ͱ� �ִ��� ������ ���ϱ�
		public boolean insertOrUpdate(int b_no) throws Exception{
			
			boolean bool = business.insertOrUpdate(b_no);
			return bool;
		}
		
		//���������� ������ ��������
		public ArrayList<StudyCenter> getMyCenter(int b_no) throws Exception{
			
			ArrayList<StudyCenter> center = business.getMyCenter(b_no);
					
			return center;
		
		}

		//ȸ������ ������Ʈ
		public void business_Update(Business bin) {
			business.business_Update(bin);
		}
		
		//���͵�� ����
		public void studyRoom_Insert(StudyRoom sr, int sc_no) throws Exception {
			business.studyRoom_Insert(sr, sc_no);
			
		}
		
		//���͵�� ������Ʈ
		public void studyRoom_Update(StudyRoom sr, String sr_name) throws Exception {
			business.studyRoom_Update(sr, sr_name);
			
		}
		
		//���͵�� ����
		public void studyRoom_Delete(String sr_name) throws Exception{
			
			business.studyRoom_Delete(sr_name);
		}
		
		//������� ���͹�ȣ ��������
		public int getSCNO(int b_no) throws Exception {
			
			int sc_no = business.getSCNO(b_no);
					
			return sc_no;
		}
		
		//���͵�� ���� ��������
		public ArrayList<StudyRoom> getMyRoom(int sc_no) throws Exception{
			
			ArrayList<StudyRoom> studyRoom = business.getMyRoom(sc_no);	
			return studyRoom;
		}
		
		//���͵�� ���� ��������
		public ArrayList<StudyRoom> getMyRoom(String sr_name) throws Exception{
			
			ArrayList<StudyRoom> studyRoom = business.getMyRoom(sr_name);		
			return studyRoom;	
		}

		//���೻�� ��������
		public ArrayList callgetReslist(String sc_name, String start_date, String end_date, String res_state)
		throws Exception {
			return business.callgetReslist(sc_name, start_date, end_date, res_state);
		}
	
		//�̸����� ������� ���͹�ȣ ��������
		public int getSCNO(String sc_name) throws Exception{
			return business.getSCNO(sc_name);
		}
		
		//����� ��ȣ�� ���Ͱ�ü ��������
		public StudyCenter SelectMyCenter(int sc_no) throws Exception{
			return business.SelectMyCenter(sc_no);
		}

		

		public Business business_GetMyBusiness(int b_no) throws SQLException{
			// TODO Auto-generated method stub
			return  business.business_GetMyBusiness(b_no);
		}
	
}
