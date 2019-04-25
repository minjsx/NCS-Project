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

public class YoutuberMainDao {
	
	// Single Pattern 
	private static YoutuberMainDao instance;
		
	// DB 연결시  관한 변수 
	private static final String 	dbDriver	=	"oracle.jdbc.driver.OracleDriver";
	private static final String		dbUrl		=	"jdbc:oracle:thin:@127.0.0.1:1521:iot3";
	private static final String		dbUser		=	"ab";
	private static final String		dbPass		=	"ab";
	
	
	private Connection	 		con;	
	
	//--------------------------------------------
	//#####	 객체 생성하는 메소드 
	public static YoutuberMainDao	getInstance() throws YoutuberMainException
	{
		if( instance == null )
		{
			instance = new YoutuberMainDao();
		}
		return instance;
	}
	
	private YoutuberMainDao() throws YoutuberMainException
	{
	
		try{
			
			/********************************************
				1. 오라클 드라이버를 로딩
					( DBCP 연결하면 삭제할 부분 )
			*/
			Class.forName( dbDriver );	
		}catch( Exception ex ){
			throw new YoutuberMainException("에러 ) DB 연결시 오류  : " + ex.toString() );	
		}
		
	}
	
	//----------------------------------------------------------------------------------
	
	//프로모션 리스트
	public List <YoutuberMain> selectProlist() throws YoutuberMainException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<YoutuberMain> proList = new ArrayList<YoutuberMain>();
		
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT p.p_no, s.s_name, c.cg_name, pc.pc_name, ad.a_ename, p.p_name, p.p_content, p.p_price, p.p_register, p.p_deadline, pmg.pimg_path, " + 
					   "(SELECT ai.aimg_path FROM advertiser a, advertiser_image ai WHERE a.a_no = ai.a_no AND a.a_no = p.a_no) as ad_image " + 
					   "FROM promotion p, promotion_category pc, status s, category c, advertiser ad, promotion_image pmg " + 
					   "WHERE p.a_no = ad.a_no " + 
					   "And p.pc_no = pc.pc_no " + 
					   "And p.cg_no = c.cg_no " + 
					   "And p.s_no = s.s_no " + 
					   "And p.p_no = pmg.p_no");
			
			//TODO:: page 및 조건 추가..
		
			ps = con.prepareStatement(sql.toString());
			
			rs = ps.executeQuery();
			
			while( rs.next()) {	
				YoutuberMain ymain = new YoutuberMain(rs.getInt("P_NO"),
													  rs.getString("S_NAME"), 
													  rs.getString("CG_NAME"), 
													  rs.getString("PC_NAME"), 
													  rs.getString("A_ENAME"), 
													  rs.getString("P_NAME"),
													  rs.getString("P_CONTENT"), 
													  rs.getString("P_PRICE"),
													  rs.getString("P_REGISTER"),
													  rs.getString("P_DEADLINE"),
													  rs.getString("pimg_path"),
													  rs.getString("ad_image"));
				proList.add(ymain);
			}				
			return proList;
			
		}catch( Exception ex ){
			throw new YoutuberMainException("에러> selectProlist > " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	public List <YoutuberMain> selectProlist(HashMap<String,String> map, int firstRow, int endRow) throws YoutuberMainException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<YoutuberMain> proList = new ArrayList<YoutuberMain>();
		String[] list = {"procategory", "category", "price", "channel"};
		
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT p.p_no, s.s_name, c.cg_name, pc.pc_name, ad.a_ename, p.p_name, p.p_content, p.p_price, p.p_register, p.p_deadline, pmg.pimg_path, " + 
					   "(SELECT ai.aimg_path FROM advertiser a, advertiser_image ai WHERE a.a_no = ai.a_no AND a.a_no = p.a_no) as ad_image " + 
					   "FROM promotion p, promotion_category pc, status s, category c, advertiser ad, promotion_image pmg " + 
					   "WHERE p.a_no = ad.a_no " + 
					   "And p.pc_no = pc.pc_no " + 
					   "And p.cg_no = c.cg_no " + 
					   "And p.s_no = s.s_no " + 
					   "And p.p_no = pmg.p_no " +
					   "And s.s_no = 1" +
					   "And p.p_no IN (SELECT p_no " +
					   "FROM (SELECT rownum as rnum, p_no FROM (SELECT p_no FROM promotion order by 1 desc)) " +
					   "WHERE rnum >= ? and rnum <= ?) ");
			
			//TODO:: page 및 조건 추가..
			
			String procatelist = map.get(list[0]) == null ? null : map.get(list[0]);
			String catelist = map.get(list[1]) == null ? null : map.get(list[1]);
			String pricelist = map.get(list[2]) == null ? null : map.get(list[2]);
			String chlist = map.get(list[3]) == null ? null : map.get(list[3]);
			
			if(procatelist != null && procatelist.length() > 0){
				String[] split = procatelist.split("-");
					for(int i = 0 ; i < split.length ; i++) {
						if(i == 0) {
							sql.append(" AND p.pc_no = '" + split[i] + "'");
						}else {
							sql.append(" OR p.pc_no = '" + split[i] + "'");
						}
					}
				
			}
			
			if(catelist != null && catelist.length() > 0) {
				String[] split = catelist.split("-");
					for(int i = 0 ; i < split.length ; i++) {
						if(i == 0) {
							sql.append(" AND p.cg_no = " + split[i] + "");
						}else {
							sql.append(" OR p.cg_no = " + split[i] + "");
						}	
					}
			}

			
			if(pricelist != null && pricelist.length() > 0) {
				String[] split = pricelist.split("-");
				String loprice = "";
				String hiprice = "";
					for(int i = 0 ; i < split.length ; i++) {
						if(split.length > 0) {
							String[] priceSplit = split[i].split("~");
							loprice = priceSplit[0];
							hiprice = priceSplit[1];
							if(i == 0) {
								sql.append(" AND p.p_price > " + loprice + " AND p.p_price < " + hiprice);
							}else {
								sql.append(" OR p.p_price > " + loprice + " AND p.p_price < " + hiprice);
							}
						}
						
					}
				
			}
			
			
			if(chlist != null && chlist.length() > 1) {
				String[] split = chlist.split("-");
					for(int i = 0 ; i < split.length ; i++) {
							if(i == 0) {
								sql.append(" AND p.p_name like '%" + split[i] + "%'");
							}else {
								sql.append(" OR p.p_name like '%" + split[i] + "%'");
							}
						}
			}
	
			
			ps = con.prepareStatement(sql.toString());
			ps.setInt(1, firstRow);
			ps.setInt(2, endRow);
			
			
			rs = ps.executeQuery();
			
			while( rs.next()) {	
				YoutuberMain ymain = new YoutuberMain(rs.getInt("P_NO"),
													  rs.getString("S_NAME"), 
													  rs.getString("CG_NAME"), 
													  rs.getString("PC_NAME"), 
													  rs.getString("A_ENAME"), 
													  rs.getString("P_NAME"),
													  rs.getString("P_CONTENT"), 
													  rs.getString("P_PRICE"),
													  rs.getString("P_REGISTER"),
													  rs.getString("P_DEADLINE"),
													  rs.getString("pimg_path"),
													  rs.getString("ad_image"));
				proList.add(ymain);
			}
				
			return proList;
			
		}catch( Exception ex ){
			throw new YoutuberMainException("에러> selectProlist > " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	//카테고리
	public List <Category> selectCategory() throws YoutuberMainException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Category> cList = new ArrayList<Category>();
		
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			String sql = "select * from category";  
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while( rs.next()) {	
				Category c = new Category(rs.getInt("cg_no"), rs.getString("cg_name"));
				cList.add(c);
			}
				
			return cList;
			
		}catch( Exception ex ){
			throw new YoutuberMainException("에러> selectCategory > " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	//프로모션 카테고리
	public List <PromotionCategory> selectProCategory() throws YoutuberMainException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PromotionCategory> pList = new ArrayList<PromotionCategory>();
		
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			String sql = "select * from promotion_category";  
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while( rs.next()) {	
				PromotionCategory pc = new PromotionCategory(rs.getInt("pc_no"), rs.getString("pc_name"));
				pList.add(pc);
			}
				
			return pList;
			
		}catch( Exception ex ){
			throw new YoutuberMainException("에러> selectProCategory > " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	//TODO image src 같이 넣기!!
	public void InsertPromotion(String title, String promotion, String contentinfo, String period, String s1, String s2, String s3, String price, int adno, String pimg_name, String pimg_src) throws YoutuberMainException
	{
		PreparedStatement ps = null;
		
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			System.out.println("adno > " + adno);
			String sql = "insert into promotion (p_no, s_no, cg_no, pc_no, a_no, p_name, p_price, p_content, p_qualification, p_register, p_deadline, p_info, p_period) " 
					   + "values(p_no_seq.nextval, 1, ?, ?, ?, ?, ?, ?, ?, sysdate, sysdate + 14, ?, ?) ";
			
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, s3); ps.setString(2, s2); ps.setInt(3, adno); ps.setString(4, title); ps.setString(5, price);
			ps.setString(6, promotion); ps.setString(7, s1); ps.setString(8, contentinfo); ps.setString(9, period);
			ps.executeUpdate();
			
			String sql2 = "Insert into promotion_image(p_no, pimg_no, pimg_name, pimg_path) VALUES(p_no_seq.currval, pimg_no_seq.nextval, ?, ?)";
			ps = con.prepareStatement(sql2);
			ps.setString(1, pimg_name); ps.setString(2, pimg_src);
			ps.executeUpdate();
			
		}catch( Exception ex ){
			throw new YoutuberMainException("에러> InsertPromotion > " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}

	public int getTotalCount() throws YoutuberMainException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rowNum = 0;
		String sql =   "SELECT COUNT(*) " +
					   "FROM promotion p, promotion_category pc, status s, category c, advertiser ad, promotion_image pmg " + 
					   "WHERE p.a_no = ad.a_no " + 
					   "And p.pc_no = pc.pc_no " + 
					   "And p.cg_no = c.cg_no " + 
					   "And p.s_no = s.s_no " + 
					   "And p.p_no = pmg.p_no";
		
		try {
			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {	
				rowNum = rs.getInt(1);
			}
		}catch (Exception e) {
			throw new YoutuberMainException("에러> TOTALCOUNT > " + e.toString());	
		}

		return rowNum;
	}
	
	public void UpdateCash(int ano) throws YoutuberMainException
	{
		PreparedStatement ps = null;
		
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			String sql = "update cash set CA_BALANCE = CA_BALANCE - 10000 where a_no = ?";  
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, ano);
			
			ps.executeUpdate();
			
			System.out.println("비용이 지출되었따");
			
		}catch( Exception ex ){
			throw new YoutuberMainException("에러> selectProCategory > " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
}
