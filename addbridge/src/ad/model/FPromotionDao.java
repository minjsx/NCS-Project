package ad.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FPromotionDao {
	// Single Pattern 
	private static FPromotionDao instance;
	
	// DB 연결시  관한 변수 
	private static final String 	dbDriver	=	"oracle.jdbc.driver.OracleDriver";
	private static final String		dbUrl		=	"jdbc:oracle:thin:@127.0.0.1:1521:iot3";
	private static final String		dbUser		=	"ab";
	private static final String		dbPass		=	"ab";
	
	private Connection	 		con;
	
	//#####	 객체 생성하는 메소드 
	public static FPromotionDao	getInstance() throws FPromotionException
	{
		if( instance == null )
		{
			instance = new FPromotionDao();
		}
		return instance;
	}
	
	
	private FPromotionDao() throws FPromotionException
	{		
		try{
			
			/********************************************
				1. 오라클 드라이버를 로딩
					( DBCP 연결하면 삭제할 부분 )
			*/
			Class.forName( dbDriver );	
		}catch( Exception ex ){
			throw new FPromotionException("DB 연결시 오류  : " + ex.toString() );	
		}
		
	}
	
	
	
	
	public int favorPromotion(int p_no, int c_no) throws FPromotionException
	{
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql1 = "select p_no from creator_favor where c_no = ?";
		try {
		con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		ps = con.prepareStatement(sql1);
		ps.setInt(1, c_no);
		
		rs = ps.executeQuery();
		// select 를 했는데, rs_next가 하나라도 나오면, -> 이미 신청함
		if (rs.next()) {
			result = -1;	
		}else {
			String sql2 = "insert into creator_favor(cf_no, p_no, c_no) values(cf_no_seq.nextval, ?, ?)";
			ps = con.prepareStatement(sql2);
			ps.setInt(1, p_no);
			ps.setInt(2, c_no);				
			result = ps.executeUpdate();	
		}
		return result;
	}	
		catch (SQLException ex) {
			// TODO Auto-generated catch block
			throw new FPromotionException("DB에 목록 검색시 오류  : " + ex.toString() );	
		}finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
		
	
	}
	
	
	
	//관심프로모션 버튼 클릭시(select)  -> 나중에 sql문에서 cf.c_no를 ?로 바꿔야 한다  (세션 정보 이용)
	public List<FPromotion> favorList(int c_no) throws FPromotionException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<FPromotion> mList = new ArrayList<FPromotion>();
		boolean isEmpty = true;
				
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );				
			String sql = "select p.p_no, p.p_name, p.p_price, p.p_deadline, pi.pimg_path " + 
						 "from creator_favor cf, promotion p , promotion_image pi " + 
						 "where cf.p_no = p.p_no " + 
						 "and p.p_no = pi.p_no " + 
						 "and cf.c_no = ? ";
			
			ps = con.prepareStatement( sql );
			ps.setInt(1, c_no);
			rs = ps.executeQuery();
			while( rs.next())
			{
				isEmpty = false;

				int pno = rs.getInt("p_no");
				String p_name = rs.getString("p_name");
				int p_price = rs.getInt("p_price");
				String p_deadline = rs.getString("p_deadline");
				String p_img = rs.getString("pimg_path");
				
				FPromotion m = new FPromotion(pno, p_name, p_price, p_deadline);
				m.setPi_pimg_path(p_img);
				mList.add(m);
			}
			
			if( isEmpty ) return Collections.emptyList();
			
			return mList;
			
		}catch( Exception ex ){
			throw new FPromotionException("DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	
	
	
	
	
	
}
