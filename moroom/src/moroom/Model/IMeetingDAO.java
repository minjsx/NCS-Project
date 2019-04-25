package moroom.Model;
import moroom.VO.*;
import moroom.View.UserClient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IMeetingDAO {
	
	public abstract ArrayList<MeetingInfo> meetinginfo_Select(int key, String text) throws Exception;
	public abstract void meetingInfo_update(MeetingInfo meeting) throws SQLException; //모임변경
	public abstract void meeting_Delete(int m_no)  throws SQLException; //모임삭제
	public abstract void meetingInfo_Delete(int mi_no); //모임정보삭제
	public abstract ArrayList myMeeting(UserClient u) throws Exception;
	public abstract boolean meetingInfo_Insert(UserClient u, String roomName, String date, String keyword, String local, int total,
			String deadline, String category, int roomNum, int total_money) throws Exception;
	public abstract ArrayList search_CenterByName(int sel,String str) throws SQLException;
	public abstract ArrayList search_roomByCenter(String str) throws SQLException;
	
	ArrayList detailRoomInfo(String center, String room) throws Exception;
	
	public abstract ArrayList search_studyListDetail(int mno) throws SQLException;
	public abstract int mnoTomino(int mno) throws SQLException;
	public abstract int mnoTosrno(int mno) throws SQLException;
	public abstract ArrayList search_meetingInfo_category(String email) throws SQLException;
	public abstract void meeting_UpdatePay(int m_no,int fphm,int flag) throws SQLException; //모임회비 update
	
}
