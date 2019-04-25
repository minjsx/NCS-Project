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
	
	// 생성자
	public ModelController()
	{
		try {
			connectDB();
			System.out.println("DB연결 성공");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DB연결 실패:"+e.getMessage());
		}
		
		users = new UsersDAO(conn);
		business = new BusinessDAO(conn);
		payment = new PaymentDAO(conn);
		meeting = new MeetingDAO(conn);
		
	}
	
	//DB연결
	private void connectDB() throws Exception
	{
		conn = ConnectionPool.getInstance(); //db연결 성공
		//conn.setAutoCommit(false); 오토커밋 관련부분
	}
	
	// 메소드 ============================================
	// 로그인
	public void login(String email, String pw, int flag) throws Exception
	{
		if(flag==1) //개인회원
		{
			//개인회원 아이디와 비밀번호 맞는지 확인하기
			boolean bool = users.emailPwCompare(email,pw);
			if(!bool)
				throw new Exception("아이디와 비밀번호가 일치하지 않습니다.");
		}
		else // 사업자 회원
		{
			//사업자 회원 아이디와 비밀번호 맞는지 확인하기
			boolean bool = business.emailPwCompare(email,pw);
			if(!bool)
				throw new Exception("아이디와 비밀번호가 일치하지 않습니다.");
		}
	}
	
	// 일반사용자 회원가입
	public void signUpUsers(People peo, String category) throws SQLException
	{
		int c_no = users.categoryNameToNum(category);
		Users user = new Users(peo,c_no);
		users.users_Insert(user);
	}
	
	// 사업자 회원가입
	public void signUpBusiness(People peo, String crn, String ceo)
	{
		
		Business bin = new Business(peo,crn,ceo);
		business.business_Insert(bin);
	}
	
	//아이디 중복 확인
	public boolean emailOverlap(String email,int flag) throws Exception
	{
		boolean bool;
		if(flag==1) //개인회원
		{
			bool =  users.email_Overlap(email);
		}
		else //사업자 회원
		{
			bool =  business.email_Overlap(email);
		}	
		return bool;
	}
	
	// email을 통해 Users 객체얻어오기
	public People emailSearchToUsers(String email) throws SQLException
	{
		People peo = users.emailSearchToUsers(email);
		return peo;
	}

	// email을 통해 Business 객체얻어오기
	public People emailSearchToBusiness(String email) throws SQLException
	{
		People peo = business.emailSearchToBusiness(email);
		return peo;
	}

	// users의 캐쉬 충전하기
	public void cashAdd(Integer cash,String email) throws SQLException
	{
		users.cashAdd(cash,email);
	}
	
	// 캐쉬 충전내역테이블 추가
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

	// Meeting Info Table 삽입
	public boolean meetingInfo_Insert(UserClient u, String roomName, String date,String keyword, String local,int total, String deadline, String category, int roomNum, int total_money) throws Exception
	{
		return meeting.meetingInfo_Insert(u, roomName, date, keyword, local, 
				total, deadline,category,roomNum,total_money);
	}
	
	// 스터디모임 수정하기
	public void meetingInfo_update(MeetingInfo mi) throws SQLException
	{
		meeting.meetingInfo_update(mi);
	}
	
	// 모임번호로 모임정보번호 얻어오기
	public int mnoTomino(int mno) throws SQLException
	{
		return meeting.mnoTomino(mno);
	}
	
	// 모임번호로 스터리룸번호 얻어오기
	public int mnoTosrno(int mno) throws SQLException
	{
		return meeting.mnoTosrno(mno);
	}
	
	// Meeting 검색
	public ArrayList searchMeeting(int key, String text) throws Exception
	{
		ArrayList list = meeting.meetinginfo_Select(key, text);
		return list;
	}
	
	// 모임해체
	public void Meeting_Delete(int mno)throws SQLException {
		meeting.meeting_Delete(mno);
	}

	//관심 카테고리 필터링 meeting 검색
	public ArrayList search_MeetingInfo_category(String email) throws SQLException
	{
		return meeting.search_meetingInfo_category(email);
	}
	
	//모임상세정보 얻어오기
	public ArrayList search_studyListDetail(int mno) throws SQLException
	{
		ArrayList list = meeting.search_studyListDetail(mno);
		return list;
	}
	
	
	// region 룸검색 기능
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
	
	
	// 센터 , 스터디룸 선택을 위한 검색
	public ArrayList centerSearch(String str) throws Exception
	{
		ArrayList list = users.search_Center(str);
		return list;
	}
	

	
	// region 출력용 테이블 
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
	
		// 참여하기를 통하여 한시간 이용요금 차감
		public void participation_CashSub(int mno, int fph) throws SQLException {
			users.participation_CashSub(mno, fph);
		}
	
		// 참여정보테이블에 추가하기
		public void participation_Insert(Participation part) throws SQLException {
			users.participation_Insert(part);
		}
	
		public void participation_Delete(Participation part) throws SQLException
		{
			users.participation_Delete(part);
		}
		
		// 이미 참여중인 모임인지 판별
		public boolean partcipation_Overlap(Participation part) throws SQLException {
			return users.partcipation_Overlap(part);
		}
	
		// 참여하기 완료시에 해당 모임 회비 update
		public void meeting_UpdatePay(int m_no, int fph, int flag) throws SQLException {
			meeting.meeting_UpdatePay(m_no, fph, flag);
		}
	
		///캐시 충전내역 리스트 가져오기
		public ArrayList Select_payCashInfo(int u_no) throws Exception {
			ArrayList list = payment.Select_payCashInfo(u_no);

			return list;
		}
		
		///캐시 사용내역 리스트 가져오기
		public ArrayList Select_useCashInfo(int u_no) throws Exception {
			ArrayList list = payment.Select_useCashInfo(u_no);
			return list;
		}
		
		// 결재내역, 예약정보 추가
		public void insert_paymentAndResinfo(int sr_no, int money, int checkpayment, int mno, String resDate,
				String startTime, String endTime) throws SQLException {
			payment.insert_paymentAndResinfo(sr_no, money, checkpayment, mno, resDate, startTime, endTime);
		}
	
		public int checkmeetingstate(int mno) throws SQLException {
			int check = payment.checkmeetingstate(mno);
			return check;
		}
	
	//Business부분==========================================================================================
	
	//센터 생성
		public void studyCenter_Insert(StudyCenter sc, int b_no) throws Exception{
			
			business.studyCenter_Insert(sc, b_no);
		}
		
		//센터 수정
		public void studyCenter_Update(StudyCenter sc, String sc_name) throws Exception{
			
			business.studyCenter_Update(sc, sc_name);
		}
		
		//센터 삭제
		public void studyCenter_Delete(int b_no, String sc_name) throws Exception{
			
			business.studyCenter_Delete(b_no, sc_name);
		}
		
		
		//센터가 있는지 없는지 비교하기
		public boolean insertOrUpdate(int b_no) throws Exception{
			
			boolean bool = business.insertOrUpdate(b_no);
			return bool;
		}
		
		//센터있으면 데이터 가져오기
		public ArrayList<StudyCenter> getMyCenter(int b_no) throws Exception{
			
			ArrayList<StudyCenter> center = business.getMyCenter(b_no);
					
			return center;
		
		}

		//회원정보 업데이트
		public void business_Update(Business bin) {
			business.business_Update(bin);
		}
		
		//스터디룸 생성
		public void studyRoom_Insert(StudyRoom sr, int sc_no) throws Exception {
			business.studyRoom_Insert(sr, sc_no);
			
		}
		
		//스터디룸 업데이트
		public void studyRoom_Update(StudyRoom sr, String sr_name) throws Exception {
			business.studyRoom_Update(sr, sr_name);
			
		}
		
		//스터디룸 삭제
		public void studyRoom_Delete(String sr_name) throws Exception{
			
			business.studyRoom_Delete(sr_name);
		}
		
		//사업자의 센터번호 가져오기
		public int getSCNO(int b_no) throws Exception {
			
			int sc_no = business.getSCNO(b_no);
					
			return sc_no;
		}
		
		//스터디룸 정보 가져오기
		public ArrayList<StudyRoom> getMyRoom(int sc_no) throws Exception{
			
			ArrayList<StudyRoom> studyRoom = business.getMyRoom(sc_no);	
			return studyRoom;
		}
		
		//스터디룸 정보 가져오기
		public ArrayList<StudyRoom> getMyRoom(String sr_name) throws Exception{
			
			ArrayList<StudyRoom> studyRoom = business.getMyRoom(sr_name);		
			return studyRoom;	
		}

		//예약내역 가져오기
		public ArrayList callgetReslist(String sc_name, String start_date, String end_date, String res_state)
		throws Exception {
			return business.callgetReslist(sc_name, start_date, end_date, res_state);
		}
	
		//이름으로 사업자의 센터번호 가져오기
		public int getSCNO(String sc_name) throws Exception{
			return business.getSCNO(sc_name);
		}
		
		//사업자 번호로 센터객체 가져오기
		public StudyCenter SelectMyCenter(int sc_no) throws Exception{
			return business.SelectMyCenter(sc_no);
		}

		

		public Business business_GetMyBusiness(int b_no) throws SQLException{
			// TODO Auto-generated method stub
			return  business.business_GetMyBusiness(b_no);
		}
	
}
