package ad.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MessageDao {
	
	// Single Pattern 
	private static MessageDao instance;
		
	// DB 연결시  관한 변수 
	private static final String 	dbDriver	=	"oracle.jdbc.driver.OracleDriver";
	private static final String		dbUrl		=	"jdbc:oracle:thin:@localhost:1521:iot3";
	private static final String		dbUser		=	"ab";
	private static final String		dbPass		=	"ab";
	
	
	private Connection	 		con;	
	
	//--------------------------------------------
	//#####	 객체 생성하는 메소드 
	public static MessageDao	getInstance() throws MessageException
	{
		if( instance == null )
		{
			instance = new MessageDao();
		}
		return instance;
	}
	
	private MessageDao() throws MessageException
	{
	
		try{
			
			/********************************************
				1. 오라클 드라이버를 로딩
					( DBCP 연결하면 삭제할 부분 )
			*/
			Class.forName( dbDriver );	
		}catch( Exception ex ){
			throw new MessageException("에러 ) DB 연결시 오류  : " + ex.toString() );	
		}
		
	}
	
	//----------------------------------------------------------------------------------
	
	
	//메세지저장(메세지번호, 진행중프로모션번호, 보내는회원번호, 받는회원번호, 내용, 날짜)
	public void SendMsg(int proceeding_no, int msg_sno, int msg_rno, String msg_content) throws MessageException
	{
		PreparedStatement ps = null;
		
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			String sql = "insert into msg(msg_no, proceeding_no, msg_sno, msg_rno, msg_content, msg_date) "
					   + "values(msg_no_seq.nextval, ?, ?, ?, ?, sysdate)";  
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, proceeding_no); 
			ps.setInt(2, msg_sno);
			ps.setInt(3, msg_rno);
			ps.setString(4, msg_content);
			
			ps.executeUpdate();
			
			
		}catch( Exception ex ){
			throw new MessageException("에러> SendMsg > " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	public int getMemNo(int c_no) throws MessageException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = 0;
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			String sql = "select mem_no from creator where c_no = ?";  
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, c_no);
			rs = ps.executeQuery();
			
			if(rs.next()){
				num = rs.getInt("mem_no");
			}
			
			return num;
			
		}catch( Exception ex ){
			throw new MessageException("추출 에러> partnerInfo > " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	public int getMemNo2(int a_no) throws MessageException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = 0;
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			String sql = "select mem_no from advertiser where a_no = ?";  
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, a_no);
			rs = ps.executeQuery();
			
			if(rs.next()){
				num = rs.getInt("mem_no");
			}
			
			return num;
			
		}catch( Exception ex ){
			throw new MessageException("추출 에러> partnerInfo > " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	//파트너 정보 받기(Member에 생성자 추가)
	public List <Member> partnerInfo(int msg_rno) throws MessageException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Member> mList = new ArrayList<Member>();
		
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			String sql = "select * from member where mem_no = ?";  
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, msg_rno);
			rs = ps.executeQuery();
			
			while( rs.next()) {	
				Member member = new Member(rs.getString("mem_email"), rs.getString("mem_tel"), rs.getString("mem_name"));
				mList.add(member);
			}
				
			return mList;
			
		}catch( Exception ex ){
			throw new MessageException("에러> partnerInfo > " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	//메세지 리스트받기(Message에 생성자 추가)
	public List <Message> MessageList(int msg_sno, int msg_rno, int proceeding_no) throws MessageException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Message> msgList = new ArrayList<Message>();
		
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			String sql = "select m.msg_no, m.msg_sno, m.msg_rno, m.msg_content, m.msg_date, ping.proceeding_no, c.c_name, a.a_ename " + 
						"from msg m, proceeding_promo ping, creator c, promotion p, advertiser a " + 
						"where m.msg_sno = ? " + 
						"AND m.msg_rno= ? " + 
						"AND m.proceeding_no = ? " + 
						"AND ping.proceeding_no = m.proceeding_no " + 
						"AND ping.c_no = c.c_no " + 
						"AND p.a_no = a.a_no AND ping.p_no = p.p_no " + 
						"union "
					  + "select m.msg_no, m.msg_sno, m.msg_rno, m.msg_content, m.msg_date, ping.proceeding_no, c.c_name, a.a_ename "
					  + "from msg m, proceeding_promo ping, creator c, promotion p, advertiser a where m.msg_sno = ?"
					  + "AND m.msg_rno= ? AND m.proceeding_no = ? AND ping.proceeding_no = m.proceeding_no AND ping.c_no = c.c_no "
					  + "AND p.a_no = a.a_no AND ping.p_no = p.p_no order by msg_date";  
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, msg_sno);
			ps.setInt(2, msg_rno);
			ps.setInt(3, proceeding_no);
			
			ps.setInt(4, msg_rno);
			ps.setInt(5, msg_sno);
			ps.setInt(6, proceeding_no);
			rs = ps.executeQuery();
			
			while( rs.next()) {	
				String sender = snederName(rs.getInt("msg_sno"));

				Message member = new Message(rs.getInt("msg_no"), rs.getInt("msg_sno"), rs.getInt("msg_rno"), rs.getString("msg_content"), 
											 rs.getString("msg_date"), rs.getInt("proceeding_no"), rs.getString("c_name"), rs.getString("a_ename"),
											 sender);
				msgList.add(member);
			}
				
			return msgList;
			
		}catch( Exception ex ){
			throw new MessageException("에러> partnerInfo > " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	//보낸사람 이름받아오기
	public String snederName(int number) throws MessageException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = null;
		
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			String sql = "select c_name as name from creator where mem_no = ? "
					   + "union select a_ename as name from advertiser where mem_no = ?";  
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, number);
			ps.setInt(2, number);
			
			rs = ps.executeQuery();
			
			while( rs.next()) {	
				
				name = rs.getString("name");
			}
				
			return name;
			
		}catch( Exception ex ){
			throw new MessageException("에러> snederName > " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	
	public int returnAno(int number) throws MessageException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		int ano = 0;
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			String sql = "select p.a_no as a_no from proceeding_promo pro, promotion p " + 
					"where pro.proceeding_no = ? and pro.p_no = p.p_no"; 
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, number);
			
			
			rs = ps.executeQuery();
			
			while( rs.next()) {	
				
				ano = rs.getInt("a_no");
			}
				
			return ano;
			
		}catch( Exception ex ){
			throw new MessageException("에러cncn> snederName > " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
}
