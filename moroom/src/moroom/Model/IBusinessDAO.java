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
	
	public abstract ArrayList<StudyCenter> getMyCenter(int b_no) throws Exception;// ���Ͱ� ���� �� ������ ��������
	public abstract int getSCNO(int b_no) throws Exception; // ���͹�ȣ ��������
	public abstract int getSCNO(String sc_name) throws Exception; // ���͹�ȣ �������� (�̸�)
	public abstract StudyCenter SelectMyCenter(int sc_no) throws Exception; // ���� ��������
	
	
	public abstract ArrayList getMyRoom(int sc_no) throws Exception;// ���͵���� ���� �� ������ ��������
	public abstract ArrayList getMyRoom(String sr_name) throws Exception; // ���͵� �� Ŭ�� �� ������ ��������
	public abstract void studyRoom_Update(StudyRoom sr, String sr_name) throws Exception; //���͵�� ����
	
	//���� ���� ��������
	public abstract ArrayList callgetReslist(String sc_name, String start_date, String end_date, String  res_state) throws Exception;

	
	
}
