package moroom.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import moroom.VO.*;

public class UsersDAO implements IUsersDAO{
	
	private Connection 	conn = null;
	private Statement 	stmt = null;
	private PreparedStatement pstmt = null;
	
	public UsersDAO(Connection _conn)
	{
		conn =_conn;
	}
	
	@Override
	public void users_Insert(People people) throws SQLException {
		// TODO Auto-generated method stub
		Users u = (Users)people;
		
		String sql = "insert into users(u_no,c_no,u_name,u_email,u_pw,u_tel,u_cash)"
				+ " values (seq_u_no.nextval,?,?,?,?,?,0)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, u.getC_no());
		pstmt.setString(2, u.getName());
		pstmt.setString(3, u.getEmail());
		pstmt.setString(4, u.getPw());
		pstmt.setString(5,u.getTel());
		
		pstmt.executeUpdate();
		pstmt.close();
	}

	@Override
	public void users_Update(People people) {
		// TODO Auto-generated method stub
		Users u = (Users)people;
		try 
		{
			String sql = "update users set c_no = ? , u_tel = ? where u_email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u.getC_no());
			pstmt.setString(2, u.getTel());
			pstmt.setString(3, u.getEmail());

			System.out.println(u.getTel());
			System.out.println(u.getC_no());
			
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("수정완료");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@Override
	public void users_Delete(People people) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cashInfo_Insert(CashInfo cashinfo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into cash_info(ci_no, u_no, ci_money, ci_date)"
				+"values(seq_ci_no.nextval,?,?,sysdate)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, cashinfo.getU_no());
		pstmt.setInt(2, cashinfo.getCi_money());
		pstmt.executeUpdate();
		
		pstmt.close();
	}

	@Override
	public void participation_Delete(Participation participation) throws SQLException{
		// TODO Auto-generated method stub
		
		String sql= "delete from participation where u_no=? and m_no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, participation.getU_no());
		pstmt.setInt(2, participation.getM_no());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		
	}
	
	@Override
	public void participation_CashSub(int mno,int fph) throws SQLException {
		// TODO Auto-generated method stub
		
		// 1시간 이용요금 이상의 캐쉬를 가지고 있는지 검사
		String sql = "select u_cash from users where u_no="+mno;
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		int cash = 0;
		while(rs.next())
		{
			cash = rs.getInt("u_cash");
		}
		rs.close();
		stmt.close();
		if(cash<fph)
		{
			throw new SQLException("잔액이 부족합니다.");
		}
		
		// 실제 캐쉬 차감하기
		sql = "update users set u_cash=u_cash-"+fph+" where u_no="+mno;
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		
		stmt.close();
	}
	
	@Override
	public boolean partcipation_Overlap(Participation part) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select p_cash from participation where m_no=? and u_no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, part.getM_no());
		pstmt.setInt(2, part.getU_no());
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next())
			return true;
		else
			return false;
	}

	@Override
	public ArrayList search_Center(String str) throws SQLException
	{
		String name=null, loc = null,
				phone = null, open = null, close = null;
		int fph = 0;
		String sql = "select sc2.sc_name name, sc2.sc_loc loc, "
				+ "(select b.b_tel from business b, study_center sc where sc.b_no = b.b_no and sc.sc_name = sc2.sc_name) as phone,"
				+ " sc2.sc_open open, sc2.sc_close close, sc2.sc_fph fph"
				+ " from study_center sc2 where sc_loc like '%" + str + "%'";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ArrayList list = new ArrayList();
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			ArrayList tmp =  new ArrayList();
			
			name = rs.getString("name");
			loc = rs.getString("loc");
			phone = rs.getString("phone");
			open = rs.getString("open");
			close = rs.getString("close");
			fph = rs.getInt("fph");
			
			tmp.add(name);
			tmp.add(loc);
			tmp.add(phone);
			tmp.add(open);
			tmp.add(close);
			tmp.add(fph);
			
			list.add(tmp);
		}
		rs.close();
		ps.close();
		
		return list;
	}
	
	
	@Override
	public People emailSearchToUsers(String email) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from users where u_email='"+email+"'";
		stmt = conn.createStatement();
		Users users= null;
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next())
		{
			users = new Users(rs.getString("u_email"), rs.getString("u_pw"), rs.getString("u_name")
					, rs.getString("u_tel"), rs.getInt("u_no"),rs.getInt("u_cash"), rs.getInt("c_no"));
		}
		rs.close();
		stmt.close();
		return users;
	}

	@Override
	public int categoryNameToNum(String category) throws SQLException {
		// TODO Auto-generated method stub
		
		// select문으로 해당 카테고리명으로 카테고리번호 얻어와서 리턴
		String sql = "select c_no from category where c_name=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, category);
		
		ResultSet rs = pstmt.executeQuery();
		int num = 0;
		while(rs.next())
		{
			num = rs.getInt("c_no");
		}
		rs.close();
		pstmt.close();
		return num;
	}

	@Override
	public boolean emailPwCompare(String email, String pw) throws SQLException {
		// TODO Auto-generated method stub
		// 이메일과 비밀번호가 일치하는지 확인하여 일치하면 true 불일치 false 리턴
		String sql = "select * from users where u_email='"+email+"' and u_pw='"+pw+"'";
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next())
			return true;
		else
			return false;
	}

	@Override
	public boolean email_Overlap(String email) throws Exception {
		// TODO Auto-generated method stub
		// 해당 이메일이 이미 등록되어 있는지 확인하고 이미 있으면 true 중복 아니면 false
		String sql = "select * from users where u_email=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next())
			return true;
		else
			return false;
	}

	@Override
	public void cashAdd(Integer cash, String email) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update users set u_cash =u_cash+? where u_email=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, cash);
		pstmt.setString(2, email);
		
		pstmt.executeUpdate();
		pstmt.close();
		
	}

	public StudyCenter selecByName(String sname) throws SQLException{
		// TODO Auto-generated method stub
		String name =null;
		String loc =null;
		int sc_fph =0;
		String sql = "select sc_name name, sc_loc loc, sc_fph from study_center sc where sc_name like '" + sname + "'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			name = rs.getString("name");
			loc = rs.getString("loc");
			sc_fph = rs.getInt("sc_fph");
		}
		StudyCenter sc = new StudyCenter(name);
		sc.setSc_loc(loc);
		sc.setSc_fph(sc_fph);
		
		ps.close();
		rs.close();
		
		return sc;
	}

	public StudyRoom selecByroom(String cname, String rname) throws SQLException{
		// TODO Auto-generated method stub
		String name =null, exp =null;
		int cap=0 ,addf =0, sr_no =0;;
		String sql = "select sr_no sno, sr_name name, sr_cap cap, sr_addf addf , sr_exp exp from study_room" +
				" where sc_no = (select sc_no from study_center where sc_name like '" + cname +"')and sr_name like '"+ rname +"'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			name = rs.getString("name");
			cap = rs.getInt("cap");
			addf = rs.getInt("addf");
			exp= rs.getString("exp");
			sr_no = rs.getInt("sno");
		}
		
		StudyRoom sr = new StudyRoom(name,cap,addf,exp);
		sr.setSr_no(sr_no);
		sr.setSr_addf(addf);
		ps.close();
		rs.close();
		
		return sr;
	}

//	@Override
//	public ArrayList search_Room(String str) throws SQLException {
//		String name = null , exp = null ;
//		int total = 0, addf=0;
//		
//		String sql = "select sr_name name, sr_cap total, sr_addf addf, sr_exp exp from study_room "
//					+ "where sc_no = (select sc_no from study_center where sc_name like '" + str + "')";
//		
//		PreparedStatement ps = conn.prepareStatement(sql);
//		ArrayList list = new ArrayList();
//		ResultSet rs = ps.executeQuery();
//		
//		while(rs.next())
//		{
//			ArrayList tmp =  new ArrayList();
//			
//			name = rs.getString("name");
//			total = rs.getInt("total");
//			addf = rs.getInt("addf");
//			exp = rs.getString("exp");
//			
//			tmp.add(name);
//			tmp.add(total);
//			tmp.add(addf);
//			tmp.add(exp);
//			
//			list.add(tmp);
//		}
//		
//		rs.close();
//		ps.close();
//		
//		return list;
//	}

	@Override
	public void participation_Insert(Participation participation) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into participation(u_no,m_no,p_cash,p_date)"
					+"values(?,?,?,sysdate)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, participation.getU_no());
		pstmt.setInt(2, participation.getM_no());
		pstmt.setInt(3, participation.getP_cash());
		
		pstmt.executeQuery();
		pstmt.close();
		
	}



}
