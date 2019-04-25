package moroom.Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import moroom.VO.Business;
import moroom.VO.CashInfo;
import moroom.VO.MeetingInfo;
import moroom.VO.Participation;
import moroom.VO.People;
import moroom.VO.StudyCenter;
import moroom.VO.StudyRoom;
import moroom.View.BusinessClient;
import moroom.View.UserClient;

public class ViewController {

	protected ModelController mc = null;
	UserClient uc = null;
	BusinessClient bc = null;
	People peo = null;
	
	// ������
	public ViewController(ModelController _mc, int flag, String email)  throws SQLException 
	{
		mc = _mc;
		
		// �ش� �÷��׿� �´� �� ����ֱ�
		if(flag == 1)
		{
			peo = mc.emailSearchToUsers(email);
			/* @Param email; @Param pw; @Param name; @Param tel;
			 * @Param user_NO; @Param user_Cash @Param category; 
			 * */
			//People peo = new Users("email","pw","��浿","010-1234-6789",1,0,10);
//			Temp tp = new Temp();
//			tp.setVisible(true);
			uc = new UserClient(this, peo);
			uc.setVisible(true);
		}
		if(flag ==2)
		{
			peo = mc.emailSearchToBusiness(email);
			//People peo = new Business("email","pw","TOZZ ���� 1ȣ��","tel",1,"11555","�踻��");
			bc = new BusinessClient(this, peo);
			bc.setVisible(true);
		}
	}
	
	
	// �޼ҵ� =======================================
	
	// User�޼ҵ� =======================================
			
	public void u_cashAdd(Integer cash,String email) throws SQLException
	{
		mc.cashAdd(cash,email);
	}
	
	// ĳ�� �����������̺� �߰�
	public void cashInfo_Insert(CashInfo cashinfo) throws SQLException
	{
		mc.cashInfo_Insert(cashinfo);
	}
	public People emailSearchToUsers(String email) throws SQLException
	{
		return mc.emailSearchToUsers(email);
	}
	
	public void user_Update(People peo, String category) throws SQLException
	{
		mc.updateUserInfo(peo, category);
	}
	

	// meeting �˻�
	public ArrayList search_MeetingInfo(int sel, String text) throws Exception
	{
		return mc.searchMeeting(sel, text);
	}
	
	// ������ü
	public void Meeting_Delete(int mno)throws SQLException {
		// TODO Auto-generated method stub
		mc.Meeting_Delete(mno);
	}
	//���� ī�װ� ���͸� meeting �˻�
	public ArrayList search_MeetingInfo_category(String email) throws SQLException
	{
		return mc.search_MeetingInfo_category(email);
	}
	
	// ���͵� �������� �ʿ��� ������ ������
	public ArrayList search_studyListDetail(int mno) throws SQLException
	{
		return mc.search_studyListDetail(mno);
	}
	
	// ���͵���� �����ϱ�
	public void meetingInfo_update(MeetingInfo mi) throws SQLException
	{
		mc.meetingInfo_update(mi);
	}
	
	// ���ӹ�ȣ�� ����������ȣ ������
	public int mnoTomino(int mno) throws SQLException
	{
		return mc.mnoTomino(mno);
	}
	
	// ��������, �������� ����
	public void insert_paymentAndResinfo(int sr_no, int money, int checkpayment, int mno,
									String resDate, String startTime, String endTime) throws SQLException
	{
		mc.insert_paymentAndResinfo(sr_no, money, checkpayment,mno, resDate, startTime, endTime);
	}
	
	
	// ���ӹ�ȣ�� ���͸����ȣ ������
	public int mnoTosrno(int mno) throws SQLException
	{
		return mc.mnoTosrno(mno);
	}
	
	// ����, ���͵�� ������ ���� �˻�
	public ArrayList center_Search(String str) throws Exception
	{
		return mc.centerSearch(str);
	}
	public ArrayList myinfo_view(UserClient u) throws Exception
	{
		return mc.myinfoview(u);
	}
	
	// region �� �˻� ���
	public ArrayList searchCenterByName(int sel, String str) throws SQLException
	{
		return mc.search_CenterByName(sel,str);
	}
	
	public ArrayList searchroomByCenter(String str) throws SQLException
	{	
		return mc.search_roomByCenter(str);
	}
	
	// endregion
	
	
	// region ���̺� ������ �˻�
	public StudyCenter selectByName(String sname) throws SQLException {
		return mc.selectByName(sname);
	}
	public StudyRoom selectByroom(String cname,String rname) throws SQLException {
		return mc.selectByroom(cname ,rname);
	}
	// endregion
		
	// ������������
	public boolean meetingInfo_Insert(UserClient u, String roomName, String date, String keyword, String local,int total, String deadline, String category, int roomNum, int total_money) throws Exception
	{
		return mc.meetingInfo_Insert(u, roomName, date, keyword, local, 
				total, deadline,category,roomNum,total_money);
	}
	
	
	public ArrayList detailRoomInfo(String centerN, String roomN)throws Exception {
		return mc.detailRoomInfo(centerN, roomN);
	}
	
	// �����ϱ⸦ ���Ͽ� �ѽð� �̿��� ����
	public void participation_CashSub(int mno,int fph) throws SQLException
	{
		mc.participation_CashSub(mno,fph);
	}
	
	// �����������̺� �߰��ϱ�
	public void participation_Insert(Participation part) throws SQLException
	{
		mc.participation_Insert(part);
	}
	
	// �̹� �������� �������� �Ǻ�
	public boolean partcipation_Overlap(Participation part) throws SQLException
	{
		return mc.partcipation_Overlap(part);
	}
	
	// ���ӳ����⸦ ���� �����������̺� ����
	public void participation_Delete(Participation part) throws SQLException
	{
		mc.participation_Delete(part);
	}
	
	
	// �����ϱ� �Ϸ�ÿ� �ش� ���� ȸ�� update
	public void meeting_UpdatePay(int m_no, int fph, int flag) throws SQLException{
		mc.meeting_UpdatePay(m_no, fph, flag);
	}
	
	//ĳ���������� ����Ʈ ��������
		public ArrayList Select_payCashInfo(int u_no) throws Exception {
			
			ArrayList list = mc.Select_payCashInfo(u_no);
			
			return list;
		}
		
	//ĳ�� ��볻�� ����Ʈ ��������
		public ArrayList Select_useCashInfo(int u_no) throws Exception {
			
			ArrayList list = mc.Select_useCashInfo(u_no);
			
			

			return list;
		}
	
	public int check_meeting_state(int mno) throws SQLException {
		// TODO Auto-generated method stub
		return mc.checkmeetingstate(mno);
	}
		
		
		
	
	// Business�޼ҵ� =======================================
	
	//����Ͻ���ȣ�� ���Ͱ� �ִ��� ������ ��(������ ����,������ ����)
		public boolean insertOrUpdate(int b_no) throws Exception {
				
				boolean bool = mc.insertOrUpdate(b_no);
				return bool;
		}
			
			//���� ������ ������ ��������
		public ArrayList<StudyCenter> getMyCenter(int b_no) throws Exception{
				
				ArrayList<StudyCenter> center = mc.getMyCenter(b_no);
//				for(StudyCenter sc : center) {
//					System.out.println(sc.getSc_name());
//					System.out.println(sc.getSc_loc());
//				}
				return center;	
		}

		//���� ����
		public void b_studyCenter_Insert(StudyCenter sc, int b_no) throws Exception {
			
			mc.studyCenter_Insert(sc, b_no);
		}
		//���� ����
		public void b_studyCenter_Update(StudyCenter sc, String sc_name) throws Exception {
			
			mc.studyCenter_Update(sc, sc_name);
		}
		
		//���� ����
		public void b_studyCenter_Delete(int b_no, String sc_name) throws Exception {
			
			mc.studyCenter_Delete(b_no, sc_name);
		}
		
		//ȸ������ ������Ʈ
		public void business_Update(Business bus) {
			mc.business_Update(bus);
		}

		//���͵�� ����
		public void studyRoom_Insert(StudyRoom sr, int sc_no) throws Exception{
			mc.studyRoom_Insert(sr, sc_no);
		}

		//���͵�� ����
		public void studyRoom_Update(StudyRoom sr, String sr_name) throws Exception{
			mc.studyRoom_Update(sr, sr_name);
		}
		
		//���͵�� ����
		public void studyRoom_Delete(String sr_name) throws Exception{
			
			mc.studyRoom_Delete(sr_name);
		}
		
		//���� Sc_no ��������
		public int getSCNO(int b_no) throws Exception {
			int sc_no = mc.getSCNO(b_no);
			
			return sc_no;
		}

		//���͵�� ���� ��������
		public ArrayList getMyRoom(int sc_no) throws Exception{
			
			ArrayList studyRoom = mc.getMyRoom(sc_no);
			
			return studyRoom;
		}
		
		//���͵�� ���� ��������
		public ArrayList<StudyRoom> getMyRoom(String sr_name) throws Exception{
			
			ArrayList<StudyRoom> studyRoom = mc.getMyRoom(sr_name);		
			return studyRoom;	
		}

	
		//SELECT (reslist)
		public ArrayList callgetReslist(String sc_name, String start_date, String end_date, String res_state)
		throws Exception {
			return mc.callgetReslist(sc_name, start_date, end_date, res_state);
		}
	
		//�̸����� ������� ���͹�ȣ ��������
		public int getSCNO(String sc_name) throws Exception{
			return mc.getSCNO(sc_name);
		}
		
		//����� ��ȣ�� ���Ͱ�ü ��������
		public StudyCenter SelectMyCenter(int sc_no) throws Exception{
			return mc.SelectMyCenter(sc_no);
		}

		public Business business_GetMyBusiness(int b_no) throws SQLException{
			// TODO Auto-generated method stub
			return mc.business_GetMyBusiness(b_no);
		}
}


