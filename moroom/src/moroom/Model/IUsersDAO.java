package moroom.Model;
import java.sql.SQLException;
import java.util.ArrayList;

import moroom.VO.*;

public interface IUsersDAO {

	//import MoRoom.VO.*; ÇÊ¿ä.
	public abstract void users_Insert(People people) throws SQLException;
	public abstract void users_Update(People people) throws Exception;
	public abstract void users_Delete(People people) throws Exception;

	public abstract void cashInfo_Insert(CashInfo cashinfo)throws Exception;
	
	public abstract void participation_Insert(Participation participation) throws SQLException;
	public abstract void participation_Delete(Participation participation) throws Exception; 
	
	public abstract  ArrayList search_Center(String str) throws SQLException;
	
	public abstract int categoryNameToNum(String category) throws SQLException;
	public abstract boolean emailPwCompare(String email, String pw) throws SQLException;
	public abstract boolean email_Overlap(String email) throws Exception;
	
	//Where email
	public abstract People emailSearchToUsers(String email) throws Exception;
	void cashAdd(Integer cash, String email) throws SQLException;
	public abstract void participation_CashSub(int mno,int fph) throws SQLException;
	public abstract boolean partcipation_Overlap(Participation part) throws SQLException;
	
}
