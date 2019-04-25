package ad.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PromotionDao {
	// Single Pattern
	private static PromotionDao instance;

	// DB 연결시 관한 변수
	private static final String dbDriver = "oracle.jdbc.driver.OracleDriver";
	private static final String dbUrl = "jdbc:oracle:thin:@127.0.0.1:1521:iot3";
	private static final String dbUser = "ab";
	private static final String dbPass = "ab";

	private Connection con;

	// ##### 객체 생성하는 메소드
	public static PromotionDao getInstance() throws PromotionException {
		if (instance == null) {
			instance = new PromotionDao();
		}
		return instance;
	}

	private PromotionDao() throws PromotionException {
		try {

			/********************************************
			 * 1. 오라클 드라이버를 로딩 ( DBCP 연결하면 삭제할 부분 )
			 */
			Class.forName(dbDriver);
		} catch (Exception ex) {
			throw new PromotionException("DB 연결시 오류  : " + ex.toString());
		}

	}

	// 제작기간, 프로모션 단가, 지원자격, 마감일자, 프로모션제목, 프로모션소개, 제작컨텐츠를 뽑아오는 함수
	public List<Promotion> selectList3(int p_no) throws PromotionException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Promotion> mList = new ArrayList<Promotion>();
		boolean isEmpty = true;

		try {

			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "SELECT p.p_period, p.p_price, p.p_qualification, p.p_deadline, p.p_info, p.p_content, p.p_name, pi.pimg_path, (SELECT aimg_path FROM advertiser_image ai WHERE ai.a_no = a.a_no) as aimg_path " + 
						 "FROM promotion p, promotion_image pi, advertiser a " + 
						 "WHERE p.p_no = pi.p_no " + 
						 "AND p.a_no = a.a_no " + 
						 "AND p.p_no = ? ";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, p_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				isEmpty = false;

				String p_period = rs.getString("p_period");
				int p_price = rs.getInt("p_price");
				String p_qualification = rs.getString("p_qualification");
				String p_deadline = rs.getString("p_deadline");
				String p_content = rs.getString("p_content");
				String p_name = rs.getString("p_name");
				String p_info = rs.getString("p_info");
				String pimg_path = rs.getString("pimg_path");
				String aimg_path = rs.getString("aimg_path");

				Promotion m = new Promotion(p_period, p_price, p_qualification, p_deadline, p_content, p_name, p_info);
				m.setPimg_path(pimg_path);
				m.setAimg_path(aimg_path);
				mList.add(m);
			}

			if (isEmpty)
				return Collections.emptyList();

			return mList;

		} catch (Exception ex) {
			throw new PromotionException("DB에 목록 검색시 오류  : " + ex.toString());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	public List<Promotion> promotion_insert() throws PromotionException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Promotion> pList = new ArrayList<Promotion>();
		boolean isEmpty = true;

		try {

			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "insert into promotion(p_no, p_name, p_price, p_content, p_qualification, p_register, p_deadline, p_info, p_period ) values()";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				isEmpty = false;

				String p_period = rs.getString("p_period");
				int p_price = rs.getInt("p_price");
				String p_qualification = rs.getString("p_qualification");
				String p_deadline = rs.getString("p_deadline");
				String p_content = rs.getString("p_content");
				String p_name = rs.getString("p_name");
				String p_info = rs.getString("p_info");

				Promotion m = new Promotion(p_period, p_price, p_qualification, p_deadline, p_content, p_name, p_info);
				pList.add(m);
			}

			if (isEmpty)
				return Collections.emptyList();

			return pList;

		} catch (Exception ex) {
			throw new PromotionException("DB에 목록 검색시 오류  : " + ex.toString());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	public List<Promotion> promotion_select(int p_no) throws PromotionException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Promotion> mList = new ArrayList<Promotion>();
		boolean isEmpty = true;

		try {

			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "SELECT p_period, p_price, p_register, p_deadline, p_content, p_name from promotion where p_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, p_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				isEmpty = false;

				String p_period = rs.getString("p_period");
				int p_price = rs.getInt("p_price");
				String p_register = rs.getString("p_register");
				String p_deadline = rs.getString("p_deadline");
				String p_content = rs.getString("p_content");
				String p_name = rs.getString("p_name");

				Promotion m = new Promotion(p_period, p_price, p_register, p_deadline, p_content, p_name);
				mList.add(m);
			}

			if (isEmpty)
				return Collections.emptyList();

			return mList;

		} catch (Exception ex) {
			throw new PromotionException("DB에 목록 검색시 오류  : " + ex.toString());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
	}


	public int supportPromotion(int p_no, int c_no) throws PromotionException {
		int result = 0;
		PreparedStatement ps = null;

		try {

			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "insert into support_status values(ss_no_seq.nextval, ?, ?, sysdate)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, p_no);
			ps.setInt(2, c_no);

			ps.executeUpdate();
			result = 99;
			return result;
		} catch (Exception ex) {
			throw new PromotionException("Support Insert 오류  : " + ex.toString());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	public int selectPno(int c_no) throws PromotionException {
		int pno = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "select s.p_no from proceeding_promo s, creator c where s.c_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, c_no);

			rs = ps.executeQuery();
			if (rs.next()) {
				pno = rs.getInt("p_no");
				System.out.println("pno" + pno);
			}

			return pno;
		} catch (Exception ex) {
			throw new PromotionException("Support Insert 오류  : " + ex.toString());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	public List<Promotion> promotionSList(int c_no, int p_no) throws PromotionException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Promotion> sList = new ArrayList<Promotion>();
		boolean isEmpty = true;

		System.out.println("나오냐?" + p_no);
		System.out.println("존재하긴 하니..?" + c_no);
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "select distinct p.p_register rg , p.p_deadline dl, p.p_content ct, p.p_name pn, p.p_price pr, p.p_period pid, s.s_name sn "
					+ "from proceeding_promo ss, creator c, promotion p, status s where ss.c_no = ? and ss.p_no = ? and p.s_no = s.s_no";
			ps = con.prepareStatement(sql);
			ps.setInt(1, c_no);
			ps.setInt(2, p_no);

			rs = ps.executeQuery();
			while (rs.next()) {
				isEmpty = false;

				String p_register = rs.getString("rg");
				String p_deadline = rs.getString("dl");
				String p_content = rs.getString("ct");
				String p_name = rs.getString("pn");
				int p_price = rs.getInt("pr");
				String p_period = rs.getString("pid");
				String sname = rs.getString("sn");
				
				Promotion p = new Promotion(p_register, p_deadline, p_content, p_name, p_price, p_period, sname);
				sList.add(p);
				System.out.println("나와라 도꺠비 방망이 cm : " + c_no + "주인 이름: " + p.getP_name());
			}

			if (isEmpty)
				return Collections.emptyList();

			return sList;

		} catch (Exception ex) {
			throw new PromotionException("DB에 목록 검색시 오류  : " + ex.toString());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	// 광고주 프로모션 관리
	public List<Promotion> promotionSCheckList(int a_no) throws PromotionException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<Promotion> sList = new ArrayList<Promotion>();
		boolean isEmpty = true;
		int pno = 0;
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql1 = "select p_no from promotion where a_no = ?";
			ps = con.prepareStatement(sql1);
			ps.setInt(1, a_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				isEmpty = false;

				String sql = "select p.p_register, pc.pc_name, p.p_name, p.p_price, p.p_qualification, p.p_period, s.s_name "
						+ "from promotion p, promotion_category pc, status s "
						+ "where p.p_no = ? and p.s_no = s.s_no and p.pc_no = pc.pc_no";

				ps = con.prepareStatement(sql);
				ps.setInt(1, rs.getInt("p_no"));
				pno = rs.getInt("p_no");
				System.out.println("pNNo" + pno);
				rs2 = ps.executeQuery();
				if (rs2.next()) {
					String p_register = rs2.getString("p_register");
					String pc_name = rs2.getString("pc_name");
					String p_name = rs2.getString("p_name");
					int p_price = rs2.getInt("p_price");
					String p_qualification = rs2.getString("p_qualification");
					String p_period = rs2.getString("p_period");
					String s_name = rs2.getString("s_name");
					int p_no = pno;
					Promotion p = new Promotion(p_register, pc_name, p_name, p_price, p_qualification, p_period, s_name,
							p_no);
					sList.add(p);
				}
			}

			if (isEmpty)
				return Collections.emptyList();

			return sList;

		} catch (Exception ex) {
			throw new PromotionException("promotionSCheckList : " + ex.toString());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	// 광고주 프로모션 지원현황
	public List<ProStatus> promotionSupportStatus(int a_no) throws PromotionException {
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<ProStatus> sList = new ArrayList<ProStatus>();
		boolean isEmpty = true;

		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql1 = "select p_no from promotion where a_no = ?";
			ps = con.prepareStatement(sql1);
			ps.setInt(1, a_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				isEmpty = false;
				int pno = rs.getInt("p_no");

				String sql2 = "SELECT c.c_name, ct.cg_name, p.p_name, c.c_count, p.p_qualification, c.c_no, p.p_no " + 
							  "FROM creator c, promotion p, support_status ss, category ct, status s " + 
							  "WHERE p.p_no = ss.p_no(+) " + 
							  "AND ss.c_no = c.c_no(+) " + 
							  "AND p.s_no = s.s_no(+) " + 
							  "AND p.cg_no = ct.cg_no(+) " + 
							  "AND s.s_no = 1 " + 
							  "AND ss.p_no = ? ";
				ps2 = con.prepareStatement(sql2);
				ps2.setInt(1, pno);
				rs2 = ps2.executeQuery();

				while (rs2.next()) {
					String c_name = rs2.getString("c_name");
					String cg_name = rs2.getString("cg_name");
					String p_name = rs2.getString("p_name");
					int c_count = rs2.getInt("c_count");
					String p_qualification = rs2.getString("p_qualification");
					int c_no = rs2.getInt("c_no");
					int p_no = rs2.getInt("p_no");
					ProStatus p = new ProStatus(c_name, cg_name, p_name, c_count, p_qualification, c_no, p_no);
					sList.add(p);
				}
			}

			if (isEmpty)
				return Collections.emptyList();

			return sList;

		} catch (Exception ex) {
			throw new PromotionException("promotionSupportStatus 오류  : " + ex.toString());
		} finally {
			if (rs != null && rs2 != null) {
				try {
					rs.close();
					rs2.close();
				} catch (SQLException ex) {
				}
			}
			if (ps != null && ps2 != null) {
				try {
					ps.close();
					ps2.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	public void promotionAccept(int cno, int pno) throws PromotionException {
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;

		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			String sqlUpdate = "update promotion set s_no = 2 where p_no = ?";
			ps1 = con.prepareStatement(sqlUpdate);
			ps1.setInt(1, pno);
			ps1.executeUpdate();

			String sqlInsert = "insert into proceeding_promo values(prd_promo_seq.nextval, ?, ?, sysdate, sysdate + 14)";
			ps2 = con.prepareStatement(sqlInsert);
			ps2.setInt(1, pno);
			ps2.setInt(2, cno);
			ps2.executeUpdate();

			String sqlDel = "delete support_status where c_no = ? and p_no = ?";
			ps3 = con.prepareStatement(sqlDel);
			ps3.setInt(1, cno);
			ps3.setInt(2, pno);
			ps3.executeUpdate();

		} catch (Exception ex) {
			throw new PromotionException("DB에 목록 검색시 오류  : " + ex.toString());
		} finally {
			if (ps1 != null && ps2 != null && ps3 != null) {
				try {
					ps1.close();
					ps2.close();
					ps3.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	public List<Proceeding> promotionProceeding(int pno) throws PromotionException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proceeding pc = null;
		List<Proceeding> pList = new ArrayList<Proceeding>(); 
		boolean isEmpty = true;
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			String sql = "select c.c_no, pp.proceeding_no, p.p_name, pp.start_date, p.p_price, p.p_period, m.mem_tel, m.mem_email, ci.ci_region, c.c_name, p.p_info, p.p_content "
					   + "from member m, creator c, creator_info ci, proceeding_promo pp, promotion p where m.mem_no = c.mem_no and c.c_no = ci.c_no "
					   + "and c.c_no = pp.c_no and pp.p_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pno);
			rs = ps.executeQuery();
			if (rs.next()) {
				isEmpty = false;
				pc = new Proceeding(rs.getString("p_name"), rs.getString("start_date"), rs.getString("mem_tel"),
						rs.getString("mem_email"), rs.getString("ci_region"), rs.getString("c_name"),
						rs.getString("p_info"), rs.getString("p_content"), rs.getInt("p_price"),
						rs.getString("p_period"), rs.getInt("Proceeding_no"), rs.getInt("c_no"));
				pList.add(pc);
			}
			if (isEmpty)
				return Collections.emptyList();

			return pList;
		} catch (Exception ex) {
			throw new PromotionException("promotionProceeding 오류  : " + ex.toString());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
	}
	public void proComplete(int pno) throws PromotionException {
		PreparedStatement ps = null;
		
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			String sql = "update promotion set s_no = 3 where p_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pno);
			ps.executeUpdate();
		
				
		} catch (Exception ex) {
			throw new PromotionException("promotionProceeding 오류  : " + ex.toString());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
	}	
	
	public int promotionCancel(int cno, int pno) throws PromotionException {
		PreparedStatement ps = null;
		int result =0;
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			String sql = "delete support_status where c_no = ? and p_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, cno);
			ps.setInt(2, pno);
			ps.executeUpdate();
			result=98;
		
			return result;
			
		} catch (Exception ex) {
			throw new PromotionException("promotionCancel 오류  : " + ex.toString());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
	}
}
