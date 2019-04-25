package moroom.Model;
import java.sql.SQLException;
import java.util.ArrayList;

import moroom.VO.*;

public interface IBusinessDAO {

	public abstract void business_Insert(Business bin)throws SQLException;
	public abstract void business_Update(Business bin);
	public abstract void business_Delete(int binNum);
	
	public abstract void studyCenter_Insert(StudyCenter sc, int b_no);
	public abstract void studyCenter_Update(StudyCenter sc, String sc_name);
	public abstract void studyCenter_Delete(int scNum, String sc_name) throws Exception;
	
	public abstract void studyRoom_Insert(StudyRoom sr, int sc_no) throws Exception;	
	public abstract void studyRoom_Update(StudyRoom sr);
	public abstract void studyRoom_Delete(String sr_name) throws Exception;
	
	public abstract boolean emailPwCompare(String email, String pw) throws SQLException;
	public abstract boolean email_Overlap(String email) throws Exception;
	
	public abstract ArrayList<StudyCenter> locToStudyCenter(String loc);

	public abstract People emailSearchToBusiness(String email)throws SQLException;
	
	public abstract boolean insertOrUpdate(int b_no) throws Exception;
	
	public abstract ArrayList<StudyCenter> getMyCenter(int b_no) throws Exception;// 센터가 있을 시 데이터 가져오기
	public abstract int getSCNO(int b_no) throws Exception; // 센터번호 가져오기
	public abstract int getSCNO(String sc_name) throws Exception; // 센터번호 가져오기 (이름)
	public abstract StudyCenter SelectMyCenter(int sc_no) throws Exception; // 센터 가져오기
	
	
	public abstract ArrayList getMyRoom(int sc_no) throws Exception;// 스터디룸이 있을 시 데이터 가져오기
	public abstract ArrayList getMyRoom(String sr_name) throws Exception; // 스터디 룸 클릭 시 데이터 가져오기
	public abstract void studyRoom_Update(StudyRoom sr, String sr_name) throws Exception; //스터디룸 변경
	
	//예약 내역 가져오기
	public abstract ArrayList callgetReslist(String sc_name, String start_date, String end_date, String  res_state) throws Exception;

	
	
}
