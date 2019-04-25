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
	
	// 생성자
	public ViewController(ModelController _mc, int flag, String email)  throws SQLException 
	{
		mc = _mc;
		
		// 해당 플래그에 맞는 뷰 띄워주기
		if(flag == 1)
		{
			peo = mc.emailSearchToUsers(email);
			/* @Param email; @Param pw; @Param name; @Param tel;
			 * @Param user_NO; @Param user_Cash @Param category; 
			 * */
			//People peo = new Users("email","pw","김길동","010-1234-6789",1,0,10);
//			Temp tp = new Temp();
//			tp.setVisible(true);
			uc = new UserClient(this, peo);
			uc.setVisible(true);
		}
		if(flag ==2)
		{
			peo = mc.emailSearchToBusiness(email);
			//People peo = new Business("email","pw","TOZZ 강남 1호점","tel",1,"11555","김말똥");
			bc = new BusinessClient(this, peo);
			bc.setVisible(true);
		}
	}
	
	
	// 메소드 =======================================
	
	// User메소드 =======================================
			
	public void u_cashAdd(Integer cash,String email) throws SQLException
	{
		mc.cashAdd(cash,email);
	}
	
	// 캐쉬 충전내역테이블 추가
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
	

	// meeting 검색
	public ArrayList search_MeetingInfo(int sel, String text) throws Exception
	{
		return mc.searchMeeting(sel, text);
	}
	
	// 모임해체
	public void Meeting_Delete(int mno)throws SQLException {
		// TODO Auto-generated method stub
		mc.Meeting_Delete(mno);
	}
	//관심 카테고리 필터링 meeting 검색
	public ArrayList search_MeetingInfo_category(String email) throws SQLException
	{
		return mc.search_MeetingInfo_category(email);
	}
	
	// 스터디 상세정보에 필요한 정보들 얻어오기
	public ArrayList search_studyListDetail(int mno) throws SQLException
	{
		return mc.search_studyListDetail(mno);
	}
	
	// 스터디모임 수정하기
	public void meetingInfo_update(MeetingInfo mi) throws SQLException
	{
		mc.meetingInfo_update(mi);
	}
	
	// 모임번호로 모임정보번호 얻어오기
	public int mnoTomino(int mno) throws SQLException
	{
		return mc.mnoTomino(mno);
	}
	
	// 결제정보, 예약정보 삽입
	public void insert_paymentAndResinfo(int sr_no, int money, int checkpayment, int mno,
									String resDate, String startTime, String endTime) throws SQLException
	{
		mc.insert_paymentAndResinfo(sr_no, money, checkpayment,mno, resDate, startTime, endTime);
	}
	
	
	// 모임번호로 스터리룸번호 얻어오기
	public int mnoTosrno(int mno) throws SQLException
	{
		return mc.mnoTosrno(mno);
	}
	
	// 센터, 스터디룸 선택을 위한 검색
	public ArrayList center_Search(String str) throws Exception
	{
		return mc.centerSearch(str);
	}
	public ArrayList myinfo_view(UserClient u) throws Exception
	{
		return mc.myinfoview(u);
	}
	
	// region 룸 검색 기능
	public ArrayList searchCenterByName(int sel, String str) throws SQLException
	{
		return mc.search_CenterByName(sel,str);
	}
	
	public ArrayList searchroomByCenter(String str) throws SQLException
	{	
		return mc.search_roomByCenter(str);
	}
	
	// endregion
	
	
	// region 테이블 데이터 검색
	public StudyCenter selectByName(String sname) throws SQLException {
		return mc.selectByName(sname);
	}
	public StudyRoom selectByroom(String cname,String rname) throws SQLException {
		return mc.selectByroom(cname ,rname);
	}
	// endregion
		
	// 모임정보생성
	public boolean meetingInfo_Insert(UserClient u, String roomName, String date, String keyword, String local,int total, String deadline, String category, int roomNum, int total_money) throws Exception
	{
		return mc.meetingInfo_Insert(u, roomName, date, keyword, local, 
				total, deadline,category,roomNum,total_money);
	}
	
	
	public ArrayList detailRoomInfo(String centerN, String roomN)throws Exception {
		return mc.detailRoomInfo(centerN, roomN);
	}
	
	// 참여하기를 통하여 한시간 이용요금 차감
	public void participation_CashSub(int mno,int fph) throws SQLException
	{
		mc.participation_CashSub(mno,fph);
	}
	
	// 참여정보테이블에 추가하기
	public void participation_Insert(Participation part) throws SQLException
	{
		mc.participation_Insert(part);
	}
	
	// 이미 참여중인 모임인지 판별
	public boolean partcipation_Overlap(Participation part) throws SQLException
	{
		return mc.partcipation_Overlap(part);
	}
	
	// 모임나가기를 통한 참여정보테이블 삭제
	public void participation_Delete(Participation part) throws SQLException
	{
		mc.participation_Delete(part);
	}
	
	
	// 참여하기 완료시에 해당 모임 회비 update
	public void meeting_UpdatePay(int m_no, int fph, int flag) throws SQLException{
		mc.meeting_UpdatePay(m_no, fph, flag);
	}
	
	//캐시충전내역 리스트 가져오기
		public ArrayList Select_payCashInfo(int u_no) throws Exception {
			
			ArrayList list = mc.Select_payCashInfo(u_no);
			
			return list;
		}
		
	//캐시 사용내역 리스트 가져오기
		public ArrayList Select_useCashInfo(int u_no) throws Exception {
			
			ArrayList list = mc.Select_useCashInfo(u_no);
			
			

			return list;
		}
	
	public int check_meeting_state(int mno) throws SQLException {
		// TODO Auto-generated method stub
		return mc.checkmeetingstate(mno);
	}
		
		
		
	
	// Business메소드 =======================================
	
	//비즈니스번호로 센터가 있는지 없는지 비교(있으면 수정,없으면 생성)
		public boolean insertOrUpdate(int b_no) throws Exception {
				
				boolean bool = mc.insertOrUpdate(b_no);
				return bool;
		}
			
			//센터 있으면 데이터 가져오기
		public ArrayList<StudyCenter> getMyCenter(int b_no) throws Exception{
				
				ArrayList<StudyCenter> center = mc.getMyCenter(b_no);
//				for(StudyCenter sc : center) {
//					System.out.println(sc.getSc_name());
//					System.out.println(sc.getSc_loc());
//				}
				return center;	
		}

		//센터 생성
		public void b_studyCenter_Insert(StudyCenter sc, int b_no) throws Exception {
			
			mc.studyCenter_Insert(sc, b_no);
		}
		//센터 수정
		public void b_studyCenter_Update(StudyCenter sc, String sc_name) throws Exception {
			
			mc.studyCenter_Update(sc, sc_name);
		}
		
		//센터 삭제
		public void b_studyCenter_Delete(int b_no, String sc_name) throws Exception {
			
			mc.studyCenter_Delete(b_no, sc_name);
		}
		
		//회원정보 업데이트
		public void business_Update(Business bus) {
			mc.business_Update(bus);
		}

		//스터디룸 생성
		public void studyRoom_Insert(StudyRoom sr, int sc_no) throws Exception{
			mc.studyRoom_Insert(sr, sc_no);
		}

		//스터디룸 수정
		public void studyRoom_Update(StudyRoom sr, String sr_name) throws Exception{
			mc.studyRoom_Update(sr, sr_name);
		}
		
		//스터디룸 삭제
		public void studyRoom_Delete(String sr_name) throws Exception{
			
			mc.studyRoom_Delete(sr_name);
		}
		
		//센터 Sc_no 가져오기
		public int getSCNO(int b_no) throws Exception {
			int sc_no = mc.getSCNO(b_no);
			
			return sc_no;
		}

		//스터디룸 정보 가져오기
		public ArrayList getMyRoom(int sc_no) throws Exception{
			
			ArrayList studyRoom = mc.getMyRoom(sc_no);
			
			return studyRoom;
		}
		
		//스터디룸 정보 가져오기
		public ArrayList<StudyRoom> getMyRoom(String sr_name) throws Exception{
			
			ArrayList<StudyRoom> studyRoom = mc.getMyRoom(sr_name);		
			return studyRoom;	
		}

	
		//SELECT (reslist)
		public ArrayList callgetReslist(String sc_name, String start_date, String end_date, String res_state)
		throws Exception {
			return mc.callgetReslist(sc_name, start_date, end_date, res_state);
		}
	
		//이름으로 사업자의 센터번호 가져오기
		public int getSCNO(String sc_name) throws Exception{
			return mc.getSCNO(sc_name);
		}
		
		//사업자 번호로 센터객체 가져오기
		public StudyCenter SelectMyCenter(int sc_no) throws Exception{
			return mc.SelectMyCenter(sc_no);
		}

		public Business business_GetMyBusiness(int b_no) throws SQLException{
			// TODO Auto-generated method stub
			return mc.business_GetMyBusiness(b_no);
		}
}


