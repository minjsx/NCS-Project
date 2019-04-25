package moroom.Model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import moroom.VO.MeetingInfo;
import moroom.View.UserClient;

public class MeetingDAO implements IMeetingDAO
{
	private Connection 	conn = null;
	private Statement 	stmt = null;
	private PreparedStatement pstmt = null;
	public MeetingDAO(Connection _conn)
	{
		conn =_conn;
	}
	 
	
	// 모임생성 프로시저 호출
	@Override
	public boolean meetingInfo_Insert(UserClient u, String roomName, String date,String keyword, String local,
								int total, String deadline, String category, int roomNum, int total_money) throws Exception
	{
			CallableStatement cs = conn.prepareCall("{call createmeeting(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

			cs.setInt(1, u.peo.getU_no());
			cs.setString(2, roomName);
			cs.setString(3, date);
			cs.setString(4, keyword);
			cs.setString(5, local);
			cs.setInt(6, total);
			cs.setString(7, deadline);
			cs.setString(8, category);
			cs.setInt(9, roomNum);
			cs.setInt(10, total_money);
			System.out.println("프로시저 호출 완료");
			cs.execute();
			
			return true;
		}
		
	
	@Override
	public int mnoTomino(int mno) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "select mi.mi_no mi_no from meeting_info mi, meeting m where mi.mi_no=m.mi_no and m.m_no="+mno;
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		int mi_no=0;
		while(rs.next())
		{
			mi_no = rs.getInt("mi_no");
		}
		
		return mi_no;
	}
	
	@Override
	public int mnoTosrno(int mno) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select sr.sr_no from study_room sr, meeting m where sr.sr_no=m.sr_no and m.m_no="+mno;
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		int sr_no=0;
		while(rs.next())
		{
			sr_no = rs.getInt("mi_no");
		}
		
		return sr_no;
	}

	@Override
	public void meetingInfo_update(MeetingInfo meeting) throws SQLException {
		// TODO Auto-generated method stub
		String sql="update meeting_info set mi_name=?,mi_date=?,mi_deadline=?,mi_cap=?,mi_keyword=? where mi_no=?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, meeting.getMi_name());
		pstmt.setString(2, meeting.getMi_date());
		pstmt.setString(3, meeting.getMi_deadline());
		pstmt.setInt(4, meeting.getMi_cap());
		pstmt.setString(5, meeting.getKeyword());
		pstmt.setInt(6, meeting.getMi_no());
		
		pstmt.executeUpdate();
		pstmt.close();
	}

	@Override
	public void meeting_Delete(int m_no) throws SQLException{
		String sql = "delete meeting where m_no = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, m_no);
		
		ResultSet rs = pstmt.executeQuery();
		rs.close();
		pstmt.close();
	}

	@Override
	public void meetingInfo_Delete(int mi_no) {
		// TODO Auto-generated method stub
		
	}
	// 센터명or지역으로 검색하여 Table에 출력
	@Override
	public ArrayList search_CenterByName(int sel, String str) throws SQLException
	{
			String name=null, loc = null,
					phone = null, open = null, close = null;
			String[] selcol = {"sc_loc" , "sc_name"};
			
			String sql = "select sc.sc_name name, sc.sc_loc loc,"
					+ " sc.sc_open open, sc.sc_close close,"
					+ " (select b.b_tel from business b, study_center sc2 where sc.b_no = b.b_no and sc2.sc_name = sc.sc_name) as phone"
					+ " from study_center sc where " +  selcol[sel] + " like '" + str + "%'";
			
			Statement ps = null;
			ps = conn.createStatement();
			ArrayList list = new ArrayList();
			ResultSet rs = ps.executeQuery(sql);
			
			while(rs.next())
			{
				ArrayList tmp =  new ArrayList();
				
				name = rs.getString("name");
				loc = rs.getString("loc");
				open = rs.getString("open");
				close = rs.getString("close");
				phone = rs.getString("phone");
				
				tmp.add(name);
				tmp.add(loc);
				tmp.add(open);
				tmp.add(close);
				tmp.add(phone);
				
				list.add(tmp);
			}
			rs.close();
			ps.close();
			
			return list;
		}
		
		// 더블클릭 시 센터명으로 스터디룸 검색
	@Override
	public ArrayList search_roomByCenter(String str) throws SQLException
	{
			String name = null , exp = null ;
			int total = 0, addf=0;
			
			String sql = "select sr_name name, sr_cap total, sr_addf addf, sr_exp exp from study_room "
						+ "where sc_no = (select sc_no from study_center where sc_name like '" + str + "')";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ArrayList list = new ArrayList();
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				ArrayList tmp =  new ArrayList();
				
				name = rs.getString("name");
				total = rs.getInt("total");
				addf = rs.getInt("addf");
				exp = rs.getString("exp");
				
				tmp.add(name);
				tmp.add(total);
				tmp.add(addf);
				tmp.add(exp);
				
				list.add(tmp);
			}
			
			rs.close();
			ps.close();
			
			return list;
		}
		
	// 모임 검색 기능
	@Override
	public ArrayList meetinginfo_Select(int key, String text) throws Exception{
		String mname=null, day = null, loc = null, sc = null, deadline =null;
		int cno = 0, cap =0,mno=0;
		String[] selcol = {"mi.mi_keyword" , " mi.mi_loc"};
		String sql = "select m.m_no as mno, m.c_no as cno, mi.mi_name as mname, mi.mi_date as day, mi.mi_loc as loc, "
					+ "(select sc.sc_name from study_center sc, study_room sr where sr.sr_no = m.sr_no and sc.sc_no= sr.sc_no ) as sc, "
					+ "mi.mi_deadline as deadline, mi.mi_cap as cap "
					+ "from meeting m, meeting_info mi "
					+ "where mi.mi_no = m.mi_no and " + selcol[key] + " like '%" + text +"%' ";

		Statement ps = null;
		ps = conn.createStatement();
		ArrayList list = new ArrayList();
		ResultSet rs = ps.executeQuery(sql);
		
		while(rs.next())
		{
			ArrayList tmp =  new ArrayList();
			
			cno = rs.getInt("cno");
			mname = rs.getString("mname");
			day =rs.getString("day");
			loc = rs.getString("loc");
			sc =rs.getString("sc");
			deadline =rs.getString("deadline");
			cap =rs.getInt("cap");
			mno = rs.getInt("mno");
			
			tmp.add(mno);
			tmp.add(cno);
			tmp.add(mname);
			tmp.add(day);
			tmp.add(loc);
			tmp.add(sc);
			tmp.add(deadline);
			tmp.add(cap);
			
			list.add(tmp);
		}
		rs.close();
		ps.close();
		   
		return list;
	}
	
	@Override
	public ArrayList myMeeting(UserClient u) throws Exception
	{	
		System.out.println(u.peo.getU_no());
		String title =null, name = null, category=null, deadline =null;
		int pay =0 , cap =0, mno=0;
		String sql = "select m.m_no Mno, mi.mi_name Title, (select us.u_name from users us where mi.u_no=us.u_no) Name, m.m_pay Pay, "
				+ "(select c_name from category where c_no = m.c_no) Category, mi.mi_cap Cap, mi.mi_deadline Deadline"
				+ " from meeting m , meeting_info mi, participation p"
				+ " where mi.mi_no = m.mi_no and p.m_no=m.m_no and (mi.u_no = " + u.peo.getU_no()+"or p.u_no="+u.peo.getU_no()+")";

		Statement ps = null;
		ps = conn.createStatement();
		ArrayList list = new ArrayList();
		ResultSet rs = ps.executeQuery(sql);
		
		
		while(rs.next())
		{
			ArrayList tmp =  new ArrayList();
			
			mno = rs.getInt("Mno");
			title = rs.getString("Title");
			name = rs.getString("Name");
			pay =rs.getInt("Pay");
			category =rs.getString("Category");
			cap =rs.getInt("Cap");
			deadline =rs.getString("Deadline");
			
			tmp.add(mno);
			tmp.add(category);
			tmp.add(title);
			tmp.add(name);
			tmp.add(pay);
			tmp.add(cap);
			tmp.add(deadline);
			
			list.add(tmp);
		}
		rs.close();
		ps.close();
		return list;
	}

	// 스터디 룸 상세정보 검색
	@Override
	public ArrayList detailRoomInfo(String center, String room) throws Exception
	{
		   String centerName = null, loc = null, tel =null, roomName =null,
		            open = null, close = null, exp =null;
		      int fph =0 , cap =0, addf =0;
		      String sql = "select sc.sc_name Centers, sc.sc_loc Loc,"
		            + " (select b.b_tel from business b, study_center sc2 where sc2.b_no = b.b_no and sc2.sc_name = sc.sc_name) Phone,"
		            + " sr.sr_name Room, sr.sr_cap Cap, sc.sc_open Open, sc.sc_close Close,"
		            + " sc.sc_fph Fph, sr.sr_addf Addf, sr.sr_exp Exp from study_center sc, study_room sr"
		            + " where sc.sc_no = (select sc_no from study_center where sc_name like '" + center +"')and"
		            + " sr.sr_no = (select sr_no from study_room sr2 where sc.sc_no = sr2.sc_no and sr_name like '"+ room +"')";

		      Statement ps = null;
		      ps = conn.createStatement();
		      ArrayList list = new ArrayList();
		      ResultSet rs = ps.executeQuery(sql);
		      

		      while(rs.next())
		      {
		         centerName = rs.getString("Centers");
		         loc = rs.getString("Loc");
		         tel = rs.getString("Phone");
		         roomName = rs.getString("Room");
		         cap = rs.getInt("Cap");
		         open = rs.getString("Open");
		         close = rs.getString("Close");
		         fph = rs.getInt("Fph");
		         addf = rs.getInt("Addf");
		         exp = rs.getString("Exp");
		         
		         list.add(centerName);
		         list.add(loc);
		         list.add(tel);
		         list.add(roomName);
		         list.add(cap);
		         list.add(open);
		         list.add(close);
		         list.add(fph);
		         list.add(exp);
		         list.add(addf);
		      }
		rs.close();
		ps.close();
		return list;
	}


	@Override
	public ArrayList search_studyListDetail(int mno) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList list =new ArrayList();
		String c_name,m_srno,m_name,m_date,m_center,m_deadline,keyword,m_email,m_loc;
		int m_pay,m_cap,m_participation;
		
		stmt = conn.createStatement();
		
		String sql = "select sr.sr_name sr_name ,c.c_name as c_name, mi.mi_name as mi_name, mi.mi_date mi_date, mi.mi_loc mi_loc, "
				+ "(select sc.sc_name from study_center sc,meeting m,study_room sr where m.sr_no=sr.sr_no and sr.sc_no=sc.sc_no and m_no="+mno+") as sc_name,"
				+ "mi.mi_deadline mi_deadline, (select count(*) from participation pi, meeting m, users u where m.m_no="+mno+" and pi.u_no=u.u_no and pi.m_no=m.m_no) as pi_count, mi.mi_cap mi_cap, "
				+ "(select sc.sc_fph from study_center sc,meeting m,study_room sr where m.sr_no=sr.sr_no and sr.sc_no=sc.sc_no and m_no="+mno+") as m_pay, u.u_email u_email ,mi.mi_keyword mi_keyword"
				+ " from category c, meeting_info mi, users u, meeting m, study_room sr "
				+ "where c.c_no=m.c_no and mi.u_no=u.u_no and sr.sr_no=m.sr_no and m.mi_no=mi.mi_no and m.m_no="+mno;
		

		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
		{

			c_name = rs.getString("c_name");
			m_name = rs.getString("mi_name");
			m_date = rs.getString("mi_date");
			m_loc = rs.getString("mi_loc");
			m_center = rs.getString("sc_name");
			m_deadline = rs.getString("mi_deadline");
			m_pay=rs.getInt("m_pay");
			m_cap=rs.getInt("mi_cap");
			m_participation = rs.getInt("pi_count");
			m_email=rs.getString("u_email");
			keyword = rs.getString("mi_keyword");
			m_srno = rs.getString("sr_name");
			
			list.add(c_name);
			list.add(m_name);
			list.add(m_date);
			list.add(m_loc);
			list.add(m_center);
			list.add(m_deadline);
			list.add(m_pay);
			list.add(m_cap);
			list.add(m_participation);
			list.add(m_email);
			list.add(keyword);
			list.add(m_srno);
			
		}
		rs.close();
		stmt.close();
		return list;		
	}


	@Override
	public ArrayList search_meetingInfo_category(String email) throws SQLException {
		// TODO Auto-generated method stub
		String mname=null, day = null, loc = null, sc = null, deadline =null;
		int cno = 0, cap =0, mno=0;
		String sql = "select m.m_no as mno, m.c_no as cno, mi.mi_name as mname, mi.mi_date as day, mi.mi_loc as loc, "
					+ "(select sc.sc_name from study_center sc, study_room sr where sr.sr_no = m.sr_no and sc.sc_no= sr.sc_no ) as sc, "
					+ "mi.mi_deadline as deadline, mi.mi_cap as cap "
					+ "from meeting m, meeting_info mi,users u "
					+ "where mi.mi_no = m.mi_no and u.c_no=m.c_no and u.u_email='"+email+"'";

		Statement ps = null;
		ps = conn.createStatement();
		ArrayList list = new ArrayList();
		ResultSet rs = ps.executeQuery(sql);
		
		while(rs.next())
		{
			ArrayList tmp =  new ArrayList();
			
			cno = rs.getInt("cno");
			mname = rs.getString("mname");
			day =rs.getString("day");
			loc = rs.getString("loc");
			sc =rs.getString("sc");
			deadline =rs.getString("deadline");
			cap =rs.getInt("cap");
			mno = rs.getInt("mno");
			
			
			tmp.add(mno);
			tmp.add(cno);
			tmp.add(mname);
			tmp.add(day);
			tmp.add(loc);
			tmp.add(sc);
			tmp.add(deadline);
			tmp.add(cap);
			
			
			list.add(tmp);
		}
		rs.close();
		ps.close();
		   
		return list;
	}


	@Override
	public void meeting_UpdatePay(int m_no,int fph,int flag) throws SQLException{
		// TODO Auto-generated method stub
		String[] key = {"+","-"};
		String sql = "update meeting set m_pay=m_pay"+key[flag]+"? where m_no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, fph);
		pstmt.setInt(2, m_no);
		
		pstmt.executeUpdate();
		pstmt.close();
	}





}
