package ad.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdvertiserDao {

	// Single Pattern 
		private static AdvertiserDao instance;
			
		// DB 연결시  관한 변수 
		private static final String 	dbDriver	=	"oracle.jdbc.driver.OracleDriver";
		private static final String		dbUrl		=	"jdbc:oracle:thin:@127.0.0.1:1521:iot3";
		private static final String		dbUser		=	"ab";
		private static final String		dbPass		=	"ab";
		
		
		private Connection	 		con;	
		
		//--------------------------------------------
		//#####	 객체 생성하는 메소드 
		public static AdvertiserDao	getInstance() throws AdvertiserException
		{
			if( instance == null )
			{
				instance = new AdvertiserDao();
			}
			return instance;
		}
		
		private AdvertiserDao() throws AdvertiserException
		{
		
			try{
				/********************************************
					1. 오라클 드라이버를 로딩
						( DBCP 연결하면 삭제할 부분 )
				*/
				Class.forName( dbDriver );	
			}catch( Exception ex ){
				throw new AdvertiserException("에러 ) DB 연결시 오류  : " + ex.toString() );	
			}
			
		}
		
		//----------------------------------------------------------------------------------
	
		//크리에이터 리스트 (페이징)
		public List <Creator> selectCreatorList(HashMap<String,String> map, int firstRow, int endRow) throws AdvertiserException
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<Creator> crelist = new ArrayList<Creator>();
			
			String[] list = {"procategory", "category", "price", "channel"};
			
			try{
				con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
				
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT c.c_no, cg.cg_name, c.c_name, c.c_intro, ci.ci_price, cm.cimg_path, c.c_count " + 
							"FROM creator c, creator_info ci, category cg, creator_image cm " + 
							"WHERE c.c_no = ci.c_no " + 
							"AND c.cg_no = cg.cg_no " + 
							"AND c.c_no = cm.c_no " + 
							"AND c.c_no IN (SELECT c_no " + 
							"FROM (SELECT rownum as rnum, c_no FROM (SELECT c_no FROM creator order by 1 desc)) " + 
							"WHERE rnum >= ? and rnum <= ?)");
				
				String procatelist = map.get(list[0]) == null ? null : map.get(list[0]);
				String catelist = map.get(list[1]) == null ? null : map.get(list[1]);
				String pricelist = map.get(list[2]) == null ? null : map.get(list[2]);
				String chlist = map.get(list[3]) == null ? null : map.get(list[3]);

				
				if(procatelist != null && procatelist.length() > 0){
					String[] split = procatelist.split("-");
						for(int i = 0 ; i < split.length ; i++) {
							if(i == 0) {
								sql.append(" AND ci.ci_ad = '" + split[i] + "'");
							}else {
								sql.append(" OR ci.ci_ad = '" + split[i] + "'");
							}
						}
					
				}
				
				if(catelist != null && catelist.length() > 0) {
					String[] split = catelist.split("-");
						for(int i = 0 ; i < split.length ; i++) {
							if(i == 0) {
								sql.append(" AND c.cg_no = " + split[i] + "");
							}else {
								sql.append(" OR c.cg_no = " + split[i] + "");
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
									sql.append(" AND ci.ci_price > " + loprice + " AND ci.ci_price < " + hiprice);
								}else {
									sql.append(" OR ci.ci_price > " + loprice + " AND ci.ci_price < " + hiprice);
								}
							}
							
						}
					
				}
				
				
				if(chlist != null && chlist.length() > 1) {
					String[] split = chlist.split("-");
						for(int i = 0 ; i < split.length ; i++) {
								if(i == 0) {
									sql.append(" AND c.c_name like '%" + split[i] + "%'");
								}else {
									sql.append(" OR c.c_name like '%" + split[i] + "%'");
								}
							}
				}
		
				
				System.out.println(sql);
				ps = con.prepareStatement(sql.toString());
				ps.setInt(1, firstRow);
				ps.setInt(2, endRow);
				
				rs = ps.executeQuery();
				
				while(rs.next()){
					//c.c_no, cg.cg_name, c.c_name, c.c_intro, ci.ci_price, cm.cimg_path, c.c_count 
					
					Creator cr = new Creator();
					
					int c_no = rs.getInt(1);
					String cg_name = rs.getString(2);
					String c_name = rs.getString(3);
					String c_intro = rs.getString(4);
					int ci_price = rs.getInt(5);
					String img_src = rs.getString(6);
					int c_count = rs.getInt(7);
					
					cr.setC_no(c_no);
					cr.setCg_name(cg_name);
					cr.setC_name(c_name);
					cr.setC_intro(c_intro);
					cr.setCi_price(ci_price);
					cr.setImg_src(img_src);
					cr.setC_count(c_count);
					
					crelist.add(cr);
				}
			
				
			}catch( Exception ex ){
				throw new AdvertiserException("에러> selectCreatorList > " + ex.toString() );	
			} finally{
				if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
				if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
				if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
			}
			
			return crelist;		
		}
		
		public List <Creator> selectCreatorList(HashMap<String,String> map) throws AdvertiserException
		{
			Statement st = null;
			ResultSet rs = null;
			List<Creator> crelist = new ArrayList<Creator>();
			
			String[] list = {"procategory", "category", "price", "channel"};
			
			try{
				con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
				
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT c.c_no, cg.cg_name, c.c_name, c.c_intro, ci.ci_price, img.img_name, c.c_count " + 
						   "FROM creator c, creator_info ci, category cg, creator_image cm, image img " + 
						   "WHERE c.c_no = ci.c_no(+) " + 
						   "AND c.cg_no = cg.cg_no(+) " + 
						   "AND c.c_no = cm.c_no(+) " + 
						   "AND cm.img_no = img.img_no(+)");
				
				String procatelist = map.get(list[0]) == null ? null : map.get(list[0]);
				String catelist = map.get(list[1]) == null ? null : map.get(list[1]);
				String pricelist = map.get(list[2]) == null ? null : map.get(list[2]);
				String chlist = map.get(list[3]) == null ? null : map.get(list[3]);

				
				if(procatelist != null && procatelist.length() > 0){
					String[] split = procatelist.split("-");
						for(int i = 0 ; i < split.length ; i++) {
							if(i == 0) {
								sql.append(" AND ci.ci_ad = '" + split[i] + "'");
							}else {
								sql.append(" OR ci.ci_ad = '" + split[i] + "'");
							}
						}
					
				}
				
				if(catelist != null && catelist.length() > 0) {
					String[] split = catelist.split("-");
						for(int i = 0 ; i < split.length ; i++) {
							if(i == 0) {
								sql.append(" AND c.cg_no = " + split[i] + "");
							}else {
								sql.append(" OR c.cg_no = " + split[i] + "");
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
									sql.append(" AND ci.ci_price > " + loprice + " AND ci.ci_price < " + hiprice);
								}else {
									sql.append(" OR ci.ci_price > " + loprice + " AND ci.ci_price < " + hiprice);
								}
							}
							
						}
					
				}
				
				
				if(chlist != null && chlist.length() > 1) {
					String[] split = chlist.split("-");
						for(int i = 0 ; i < split.length ; i++) {
								if(i == 0) {
									sql.append(" AND c.c_name like '%" + split[i] + "%'");
								}else {
									sql.append(" OR c.c_name like '%" + split[i] + "%'");
								}
							}
				}
		
				
				System.out.println(sql);
				st = con.createStatement();				
				rs = st.executeQuery(sql.toString());
				
				while(rs.next()){
					//SELECT c.c_no, cg.cg_name, c.c_name, c.c_intro, ci.ci_price, img.img_name, c.c_count
					
					Creator cr = new Creator();
					
					int c_no = rs.getInt(1);
					String cg_name = rs.getString(2);
					String c_chanel = rs.getString(3);
					String c_intro = rs.getString(4);
					int ci_price = rs.getInt(5);
					String img_src = rs.getString(6);
					int c_count = rs.getInt(7);
					
					cr.setC_no(c_no);
					cr.setCg_name(cg_name);
					cr.setC_chanel(c_chanel);
					cr.setC_intro(c_intro);
					cr.setCi_price(ci_price);
					cr.setImg_src(img_src);
					cr.setC_count(c_count);
					
					crelist.add(cr);
				}
			
				
			}catch( Exception ex ){
				throw new AdvertiserException("에러> selectCreatorList > " + ex.toString() );	
			} finally{
				if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
				if( st   != null ) { try{ st.close();  } catch(SQLException ex){} }
				if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
			}
			
			return crelist;		
		}
		
		
		//카테고리
		public List <Category> selectCategory() throws AdvertiserException
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
				throw new AdvertiserException("에러> selectCategory > " + ex.toString() );	
			} finally{
				if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
				if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
				if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
			}		
		}
		
		
		public int getTotalCount() throws AdvertiserException{
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			int rowNum = 0;
			
			String sql =  "SELECT count(*) " + 
						  "FROM creator c, creator_info ci, category cg, creator_image cm " + 
						  "WHERE c.c_no = ci.c_no " + 
						  "AND c.cg_no = cg.cg_no " + 
						  "AND c.c_no = cm.c_no ";
			
			try {
				con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				if(rs.next()) {	
					rowNum = rs.getInt(1);
				}
			}catch (Exception e) {
				throw new AdvertiserException("에러> TOTALCOUNT > " + e.toString());	
			}

			return rowNum;
		}
		
	
}
