package moroom.Model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import moroom.VO.*;

public class BusinessDAO implements IBusinessDAO{
	private Connection 	conn = null;
	private Statement 	stmt = null;
	private PreparedStatement pstmt = null;
	private CallableStatement csmt = null;
	
	// 생성자
	public BusinessDAO(Connection _conn)
	{
		conn =_conn;
	}
	
	
	// 메소드
	@Override
	public void business_Insert(Business bin) {
		// TODO Auto-generated method stub
		//Business(String email, String pw, String name, String tel, int b_no, String b_crn, String b_ceoname)
		
		String sql = "insert into business(B_NO, B_NAME, B_TEL, B_CRN, B_CEO, B_EMAIL, B_PW) values(SEQ_B_NO.nextval, ?, ?, ?, ?, ?, ?)";
		
		try {
		pstmt = conn.prepareStatement(sql); 
		
		pstmt.setString(1, bin.getName());
		pstmt.setString(2, bin.getTel()); 		
		pstmt.setString(3, bin.getB_crn());
		pstmt.setString(4, bin.getB_ceoname());
		pstmt.setString(5, bin.getEmail());
		pstmt.setString(6, bin.getPw());

		pstmt.executeUpdate(); //명령 실행			
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null,"사업자아이디 만들기(디비부분) 실패" + e.getMessage());
		}
		finally {
			try {
				pstmt.close(); 
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null,"사업자아이디 만들기(디비부분) pstmt.close() 실패 " + e.getMessage());
			}
			
		}
			
		   
	}

	@Override
	public void business_Update(Business bin) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Business " + 
				 "SET b_name = ?, b_tel = ?, b_crn = ?, b_ceo = ?" +
				 "WHERE b_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bin.getName());
			pstmt.setString(2, bin.getTel());
			pstmt.setString(3, bin.getB_crn());
			pstmt.setString(4, bin.getB_ceoname());
			pstmt.setInt(5, bin.getB_no());
			
			int rowNum = pstmt.executeUpdate();
			if(rowNum > 0) {
				System.out.println(rowNum + "행이 갱신되었습니다.");
			}
			else {
				throw new SQLException("오류 : 0행이 갱신됨");
			}
			
		} catch (SQLException e) {			
			JOptionPane.showMessageDialog(null, e.getMessage(), "[알림]", JOptionPane.DEFAULT_OPTION);
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void business_Delete(int binNum) {
		// TODO Auto-generated method stub
		
	}

	//센터 생성
		@Override
		public void studyCenter_Insert(StudyCenter sc, int b_no) {
			// TODO Auto-generated method stub
			
			//StudyCenter(centerName, centerLoc, centerStart,  centerOpen, centerClose, centerCancel, centerFPH, centerPicture);
			String sql = "insert into STUDY_CENTER(SC_NO, B_NO, SC_NAME, SC_LOC, SC_START, SC_OPEN, SC_CLOSE, SC_CANCEL, SC_FPH, SC_PICTURE) values(seq_sc_no.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";                                                 
			
			try {
			pstmt = conn.prepareStatement(sql); 
			
			//B_NO, SC_NAME, SC_LOC, SC_START, SC_OPEN, SC_CLOSE, SC_CANCEL, SC_FPH, SC_PICTURE
			pstmt.setInt(1, b_no);
			pstmt.setString(2, sc.getSc_name()); 
			pstmt.setString(3, sc.getSc_loc());
			pstmt.setString(4, sc.getSc_start());
			pstmt.setString(5, sc.getSc_open());
			pstmt.setString(6, sc.getSc_close());
			pstmt.setString(7, sc.getSc_cancel());
			pstmt.setInt(8, sc.getSc_fph());
			pstmt.setString(9, sc.getSc_picture());
			
			pstmt.executeUpdate(); //명령 실행		
			
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null,"센터 생성(디비부분) 실패" + e.getMessage());
			}
			finally {
				try {
					pstmt.close(); 
				}catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,"센터 생성(디비부분) pstmt.close() 실패 " + e.getMessage());
				}
				
			}
		}

	
		//센터 업데이트
		@Override
		public void studyCenter_Update(StudyCenter sc, String sc_name) {
			// TODO Auto-generated method stub
			//StudyCenter(String name, String start, String open,String close, String cancel, int fph)
			
			//String sql = "update STUDY_CENTER SC_NAME = ?, SC_LOC = ?, SC_START = ?, SC_OPEN = ?, SC_CLOSE = ?, SC_CANCEL = ?, SC_FPH = ? where B_NO = ?";                                                 
			String sql = "update STUDY_CENTER set SC_NAME = ?, SC_LOC = ?, SC_START = ?, SC_OPEN = ?, SC_CLOSE = ?, SC_CANCEL = ?, SC_FPH = ?, SC_PICTURE = ? where SC_NAME = ?";
			try {
			pstmt = conn.prepareStatement(sql); 
			
			
			pstmt.setString(1, sc.getSc_name()); 
			pstmt.setString(2, sc.getSc_loc());
			pstmt.setString(3, sc.getSc_start());
			pstmt.setString(4, sc.getSc_open());
			pstmt.setString(5, sc.getSc_close());
			pstmt.setString(6, sc.getSc_cancel());
			pstmt.setInt(7, sc.getSc_fph());
			pstmt.setString(8, sc.getSc_picture());
			
			pstmt.setString(9, sc_name);	
			
			pstmt.executeUpdate(); //명령 실행		
			
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null,"센터 업데이트(디비부분) 실패" + e.getMessage());
			}
			finally {
				try {
					pstmt.close(); 
				}catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,"센터 업데이트(디비부분) pstmt.close() 실패 " + e.getMessage());
				}
				
			}
		}
	
		//센터 상태(센터가 있는지 없는지 유무가리는 메서드)
		@Override
		public boolean insertOrUpdate(int b_no) throws Exception{
			String sql = "select * from STUDY_CENTER where B_NO = "+ b_no + "";
			stmt = conn.createStatement();		
			ResultSet rs = stmt.executeQuery(sql);
			boolean bool = true;
			
			if(rs.next()) {
				bool = true;
				return bool;//있다.
			}
			else {
				bool = false;
				return bool;//없다
			}
		}

		//센터삭제
		@Override
		public void studyCenter_Delete(int b_no, String sc_name)throws Exception {
			// TODO Auto-generated method stub
			//scNum 이걸로 받아야하는데.. 
			String sql = "delete from STUDY_CENTER where B_NO = ? AND SC_NAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_no);
			pstmt.setString(2, sc_name);
			
			ResultSet rs = pstmt.executeQuery();
			rs.close();
		}
		
	
	
		//센터있으면 데이터 가져오기
		@Override
		public ArrayList<StudyCenter> getMyCenter(int b_no) throws Exception{
			
			String sql = "select * from STUDY_CENTER where b_no = ?";
			StudyCenter sc = null;
			ArrayList<StudyCenter> list = new ArrayList<StudyCenter>();
			
			pstmt = conn.prepareStatement(sql);   	
			pstmt.setInt(1, b_no);
			
			ResultSet rs = pstmt.executeQuery();
							
			while(rs.next()) {
				sc = new StudyCenter();
				sc.setSc_no(rs.getInt("SC_NO"));
				sc.setSc_name(rs.getString("SC_NAME"));
				sc.setSc_loc(rs.getString("SC_LOC"));
				sc.setSc_start(rs.getString("SC_START"));
				sc.setSc_open(rs.getString("SC_OPEN"));
				sc.setSc_close(rs.getString("SC_CLOSE"));	
				sc.setSc_cancel(rs.getString("SC_CANCEL"));
				sc.setSc_fph(rs.getInt("SC_FPH"));
				
				list.add(sc);
			}
			
			rs.close();
			stmt.close();

			
			return list;
		}
		
		@Override
		public StudyCenter SelectMyCenter(int sc_no) throws Exception{
			
			String sql = "select * from STUDY_CENTER where sc_no = ?";
			StudyCenter sc = null;
			
			pstmt = conn.prepareStatement(sql);   	
			pstmt.setInt(1, sc_no);
			
			ResultSet rs = pstmt.executeQuery();
							
			while(rs.next()) {
				sc = new StudyCenter();
				sc.setSc_no(rs.getInt("SC_NO"));
				sc.setSc_name(rs.getString("SC_NAME"));
				sc.setSc_loc(rs.getString("SC_LOC"));
				sc.setSc_start(rs.getString("SC_START"));
				sc.setSc_open(rs.getString("SC_OPEN"));
				sc.setSc_close(rs.getString("SC_CLOSE"));	
				sc.setSc_cancel(rs.getString("SC_CANCEL"));
				sc.setSc_fph(rs.getInt("SC_FPH"));
				
			}
			
			rs.close();
			stmt.close();

			return sc;
		}
		

		//스터디룸 만들기
		@Override
		public void studyRoom_Insert(StudyRoom sr, int sc_no) throws Exception {
			// TODO Auto-generated method stub
			
			//StudyRoom(String name, int cap, String type, String addf, String exp, String picture);
			String sql = "insert into STUDY_ROOM(SR_NO, SC_NO, SR_NAME, SR_CAP, SR_TYPE, SR_ADDF, SR_EXP, SR_PICTURE) values(seq_sr_no.nextval, ?, ?, ?, ?, ?, ?, ?)";                                                 
			
			try {
			pstmt = conn.prepareStatement(sql); 
			
			//SC_NO, SR_NAME, SR_CAP, SR_TYPE, SR_ADDF, SR_EXP, SR_PICTURE
			pstmt.setInt(1, sc_no);
			pstmt.setString(2, sr.getSr_name()); 
			pstmt.setInt(3, sr.getSr_cap()); 
			pstmt.setString(4, sr.getSr_type()); 
			pstmt.setInt(5, sr.getSr_addf()); 
			pstmt.setString(6, sr.getSr_exp()); 
			pstmt.setString(7, sr.getSr_picture()); 
			
			pstmt.executeUpdate(); //명령 실행		
			
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null,"스터디룸 생성(디비부분) 실패" + e.getMessage());
			}
			finally {
				try {
					pstmt.close(); 
				}catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,"스터디룸 생성(디비부분) pstmt.close() 실패 " + e.getMessage());
				}		
			}	
		}

		//스터디룸 변경
		@Override	
		public void studyRoom_Update(StudyRoom sr, String sr_name) throws Exception{
			// TODO Auto-generated method stub
			
			//StudyRoom(String name, int cap, String type, String addf, String exp, String picture);
			String sql = "update STUDY_ROOM set SR_NAME = ?, SR_CAP = ?, SR_TYPE = ?, SR_ADDF = ?, SR_EXP = ?, SR_PICTURE= ? where sr_name = ?";                                                 
			
			try {
			pstmt = conn.prepareStatement(sql); 
			
			//SC_NO, SR_NAME, SR_CAP, SR_TYPE, SR_ADDF, SR_EXP, SR_PICTURE
			pstmt.setString(1, sr.getSr_name()); 
			pstmt.setInt(2, sr.getSr_cap()); 
			pstmt.setString(3, sr.getSr_type()); 
			pstmt.setInt(4, sr.getSr_addf()); 
			pstmt.setString(5, sr.getSr_exp()); 
			pstmt.setString(6, sr.getSr_picture()); 
			pstmt.setString(7,sr_name);
			
			pstmt.executeUpdate(); //명령 실행		
			
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null,"스터디룸 업데이트(디비부분) 실패" + e.getMessage());
			}
			finally {
				try {
					pstmt.close(); 
				}catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,"스터디룸 업데이트(디비부분) pstmt.close() 실패 " + e.getMessage());
				}		
			}	
		}

		//스터디룸 삭제
		@Override
		public void studyRoom_Delete(String sr_name) throws Exception{
			// TODO Auto-generated method stub
			String sql = "delete from STUDY_ROOM where SR_NAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sr_name);
			ResultSet rs = pstmt.executeQuery();
			rs.close();	
		}

	@Override
	public ArrayList<StudyCenter> locToStudyCenter(String loc) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean emailPwCompare(String email, String pw) throws SQLException {
		// TODO Auto-generated method stub
		// 이메일과 비밀번호가 일치하는지 확인하여 일치하면 true 불일치 false 리턴
		
		String sql = "select * from business where b_email='"+email+"' and b_pw='"+pw+"'";
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
		boolean bool;
		String sql = "select * from business where b_email=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next())
			return true;
		else
			return false;
	}


	@Override
	public People emailSearchToBusiness(String email) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from business where b_email='"+email+"'";
		stmt = conn.createStatement();
		Business business= null;
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next())
		{
			People p = new People(rs.getString("b_email"), rs.getString("b_pw"), rs.getString("b_name"), rs.getString("b_tel"));
			business = new Business(rs.getInt("b_no"), rs.getString("b_crn"), rs.getString("b_ceo"), p);
			
		}
		rs.close();
		stmt.close();
		return business;
	}


	@Override
	public ArrayList callgetReslist(String sc_name, String start_date, String end_date, String res_state)
	throws Exception {
		ArrayList col = new ArrayList();
		
		//IN sc_name, IN start_date, IN end_date, IN res_state, OUT CURSOR
		String sql = " {call business_res_select_proc(?,?,?,?,?)} ";
		
		csmt = conn.prepareCall(sql);
		
		csmt.setString(1, sc_name);
		csmt.setString(2, start_date);
		csmt.setString(3, end_date);
		csmt.setString(4, res_state);
		csmt.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
		
		
		boolean complete = csmt.execute();
		ResultSet rs = (ResultSet)csmt.getObject(5);

		while(rs.next()) {
			ArrayList row = new ArrayList();
			row.add(rs.getString(1));
			row.add(rs.getString(2));
			row.add(rs.getString(3));
			row.add(rs.getString(4));
			col.add(row);
		}
		
		csmt.close();
		rs.close();
		return col;
	}
	

	//스터디룸 만들기 위해서 만든 메서드(sc_no 가져옴)
	@Override
	public int getSCNO(int b_no) throws Exception{
		String sql = "select sc_no from STUDY_CENTER where b_no = ?";
		
		pstmt = conn.prepareStatement(sql);   	
		pstmt.setInt(1, b_no);
		
		ResultSet rs = pstmt.executeQuery();	
		
		if(rs.next()) {
			int sc_no = rs.getInt("SC_NO");	
			rs.close();
			pstmt.close();
			return sc_no;
		}else {
			rs.close();
			pstmt.close();
			return -1;
		}		
	}
	
	//작성자 : 수민
	//수정일 : 18.12.05
	//센터번호 가져오기 (이름)
	@Override
	public int getSCNO(String sc_name) throws Exception{
		String sql = "SELECT sc_no FROM study_center WHERE sc_name = ?";
		
		pstmt = conn.prepareStatement(sql);   	
		pstmt.setString(1, sc_name);
		
		ResultSet rs = pstmt.executeQuery();	
		
		if(rs.next()) {
			int sc_no = rs.getInt("SC_NO");	
			rs.close();
			pstmt.close();
			return sc_no;
		}else {
			rs.close();
			pstmt.close();
			return -1;
		}		
	}

	
	// 센터번호로 스터디룸 데이터 가져오기
	@Override
	public ArrayList getMyRoom(int sc_no) throws Exception{
		String sql = "select SR_NAME, SR_CAP, SR_TYPE, SR_ADDF, SR_EXP, SR_PICTURE from STUDY_ROOM where sc_no = ?";
		StudyRoom sr = null;
		
		ArrayList list = new ArrayList();
		
		
		pstmt = conn.prepareStatement(sql);   	
		pstmt.setInt(1, sc_no);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			ArrayList temp = new ArrayList();
		
			temp.add(rs.getString("SR_NAME"));
			temp.add(rs.getInt("SR_CAP"));
			temp.add(rs.getString("SR_TYPE"));
			temp.add(rs.getInt("SR_ADDF"));
			temp.add(rs.getString("SR_EXP"));
			temp.add(rs.getString("SR_PICTURE"));
			
			list.add(temp);
		}		
		rs.close();
		stmt.close();
	
		return list;	
	}
	
	//스터디룸 이름에 맞게  스터디룸 데이터 가져오기
	public ArrayList getMyRoom(String sr_name) throws Exception{

		String sql = "select SR_NAME, SR_CAP, SR_TYPE, SR_ADDF, SR_EXP, SR_PICTURE from STUDY_ROOM where sr_name = ?";
		
		StudyRoom sr = null;
		ArrayList<StudyRoom> list = new ArrayList<StudyRoom>();
		
		pstmt = conn.prepareStatement(sql);   	
		pstmt.setString(1,sr_name);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			sr = new StudyRoom();

			sr.setSr_name(rs.getString("SR_NAME"));
			sr.setSr_cap(rs.getInt("SR_CAP"));
			sr.setSr_type(rs.getString("SR_TYPE"));
			sr.setSr_addf(rs.getInt("SR_ADDF"));
			sr.setSr_exp(rs.getString("SR_EXP"));
			sr.setSr_picture(rs.getString("SR_PICTURE"));

			
			list.add(sr);
		}		
		rs.close();
		stmt.close();
	
		return list;	
	}


	
	
	@Override
	public void studyRoom_Update(StudyRoom sr) {
		// TODO Auto-generated method stub
		
	}


	public Business business_GetMyBusiness(int b_no) throws SQLException{
		
		String sql = "SELECT * FROM business WHERE b_no = ?";
		
		pstmt = conn.prepareStatement(sql);   	
		pstmt.setInt(1, b_no);
		
		ResultSet rs = pstmt.executeQuery();	
 
		if(rs.next()) {
			 // Business(String email, String pw, String name, String tel, int b_no, String b_crn, String b_ceoname)			
			Business b = new Business(rs.getString("B_EMAIL"), rs.getString("B_PW"), rs.getString("B_NAME"), rs.getString("B_TEL"),
						 rs.getInt("B_NO"), rs.getString("B_CRN"), rs.getString("B_CEO"));
			rs.close();
			pstmt.close();
			return b;
			
		}else {
			rs.close();
			pstmt.close();
			return null;
		}		
		
	}
	
}
