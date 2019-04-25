package ad.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import ad.command.CommandException;

public class CashDao {

	// Single Pattern
		private static CashDao instance;

		// DB 연결시 관한 변수
		private static final String dbDriver = "oracle.jdbc.driver.OracleDriver";
		private static final String dbUrl = "jdbc:oracle:thin:@127.0.0.1:1521:iot3";
		private static final String dbUser = "ab";
		private static final String dbPass = "ab";

		private Connection con;
		PreparedStatement ps = null;
		ResultSet rs = null;
		

		// ##### 객체 생성하는 메소드
		public static CashDao getInstance() throws CommandException {
			if (instance == null) {
				instance = new CashDao();
			}
			return instance;
		}

		private CashDao() throws CommandException {
			try {

				/********************************************
				 * 1. 오라클 드라이버를 로딩 ( DBCP 연결하면 삭제할 부분 )
				 */
				Class.forName(dbDriver);
			} catch (Exception ex) {
				throw new CommandException("DB 연결시 오류  : " + ex.toString());
			}

		}
		
		//cash 테이블에 데이터 추가/수정/검색
		public void cash(int ad_no, int total, int chargePrice) throws CommandException
		{
			try{
				con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );		
				
				String sql1 = "select a_no from cash where a_no = ?";
				ps = con.prepareStatement( sql1 );
				ps.setInt(1, ad_no);
				rs = ps.executeQuery();
				if (rs.next()) {
					String sql3 = "update cash set ca_recharge = ?, ca_balance = ca_balance + ? where a_no = ?";
					ps = con.prepareStatement(sql3);
					ps.setInt(1, chargePrice);
					ps.setInt(2, chargePrice);
					ps.setInt(3, ad_no);
					ps.executeUpdate();
				}
				else {
					String sql2 = "insert into cash(ca_no, CA_RECHARGE, CA_BALANCE, A_NO) values(ca_no_seq.nextval, ?, ?, ?)";
					ps = con.prepareStatement( sql2 );
					ps.setInt(1, chargePrice);
					ps.setInt(2, total);
					ps.setInt(3, ad_no);
					ps.executeUpdate();
				}
						
				
			}catch( Exception ex ){
				throw new CommandException("DB에 목록 검색시 오류  : " + ex.toString() );	
			} finally{
				if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
				if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
			}		
		}
		
}
