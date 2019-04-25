/*package ad.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

	// Single Pattern
	private static MemberDao instance;

	// DB �뿰寃곗떆 愿��븳 蹂��닔
	private static final String dbDriver = "oracle.jdbc.driver.OracleDriver";
	private static final String dbUrl = "jdbc:oracle:thin:@127.0.0.1:1521:iot3";
	private static final String dbUser = "ab";
	private static final String dbPass = "ab";

	private Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// --------------------------------------------
	// ##### 媛앹껜 �깮�꽦�븯�뒗 硫붿냼�뱶
	public static MemberDao getInstance() throws MemberException {
		if (instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}

	public MemberDao() throws MemberException {

		try {
			Class.forName(dbDriver);
		} catch (Exception ex) {
			throw new MemberException("DB �뿰寃곗떆 �삤瑜�   : " + ex.toString());
		}
	}

	public int emailCheck(Member mem) throws MemberException {
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			String sql = "select * from member where MEM_EMAIL = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mem.getEmail());
			rs = ps.executeQuery();
			while (rs.next()) {
				return -1;
			}

			System.out.println("�씠硫붿씪泥댄겕�맖");
			return 1;
		} catch (Exception ex) {
			throw new MemberException("ID CHECK �떆 �삤瑜� : " + ex.toString());
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

	public void memberInsert(Member mem, String adrNo) throws MemberException {

		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			String sqlMember = "insert into member(MEM_NO, MEM_PW, MEM_NAME, MEM_EMAIL, TYPE_NO, MEM_TEL) values(mem_no_seq.nextval, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sqlMember);
			ps.setString(1, mem.getPassword());
			ps.setString(2, mem.getName());
			ps.setString(3, mem.getEmail());
			ps.setInt(4, mem.getType());
			ps.setString(5, mem.getPhone());
			ps.executeUpdate();

			if (mem.getType() == 2) {
				// 愿묎퀬二� �뀒�씠釉� �씤�꽌�듃
				String sqlAdvertiser = "insert into advertiser(a_no, mem_no, a_ceo, a_ename, a_eno, a_tel) values(a_no_seq.nextval, mem_no_seq.currval, ?, ?, ?, ?)";
				ps = con.prepareStatement(sqlAdvertiser);
				ps.setString(1, "2");
				ps.setString(2, mem.getName());
				ps.setString(3, adrNo);
				ps.setString(4, mem.getPhone());
				ps.executeUpdate();
			}
		} catch (Exception ex) {
			throw new MemberException("MEMBER �엯�젰 �떆 �삤瑜�  : " + ex.toString());
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

	public Member loginCheck(Member mem) throws MemberException {

		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "select * from member where mem_email = ? and mem_pw = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mem.getEmail());
			ps.setString(2, mem.getPassword());
			rs = ps.executeQuery();
			while (rs.next()) {
				int memNo = rs.getInt("MEM_NO");
				String email = rs.getString("MEM_EMAIL");
				String pw = rs.getString("MEM_PW");
				String name = rs.getString("MEM_NAME");
				String phone = rs.getString("MEM_TEL");
				int type = rs.getInt("TYPE_NO");

				if (type == 1) {
					Creator creator = new Creator();
					creator.setMemNo(memNo);
					creator.setMem_no(memNo);
					creator.setEmail(email);
					creator.setPassword(pw);
					creator.setName(name);
					creator.setPhone(phone);
					creator.setType(type);

					String sql2 = "select * from creator c, creator_info ci where mem_no = ? and c.c_no = ci.c_no";
					ps = con.prepareStatement(sql2);
					ps.setInt(1, memNo);
					rs = ps.executeQuery();
					if (rs.next()) {
						creator.setC_no(rs.getInt("c_no"));
						creator.setC_chanel(rs.getString("c_chanel"));
						creator.setC_intro(rs.getString("c_intro"));
						creator.setC_content(rs.getString("c_content"));
						creator.setC_url(rs.getString("c_url"));
						creator.setC_rurl(rs.getString("c_rurl"));
						creator.setC_name(rs.getString("c_name"));
						creator.setC_count(rs.getInt("c_count"));
						creator.setCi_no(rs.getInt("ci_no"));
						creator.setCi_addr(rs.getString("ci_addr"));
						creator.setCi_term(rs.getString("ci_term"));
						creator.setCi_price(rs.getInt("ci_price"));
						creator.setCi_ad(rs.getString("ci_ad"));
						creator.setCi_region(rs.getString("ci_region"));
						creator.setCg_no(rs.getInt("cg_no"));
					}

					return creator;
				} else if (type == 2) {
					Advertiser advertiser = new Advertiser();
					advertiser.setMemNo(memNo);
					advertiser.setEmail(email);
					advertiser.setPassword(pw);
					advertiser.setName(name);
					advertiser.setPhone(phone);
					advertiser.setType(type);

					String sql2 = "select * from advertiser where mem_no = ?";
					ps = con.prepareStatement(sql2);
					ps.setInt(1, memNo);
					rs = ps.executeQuery();
					if (rs.next()) {
						advertiser.setA_eno(rs.getString("a_eno"));
						advertiser.setA_name(rs.getString("a_ename"));
						advertiser.setA_no(rs.getInt("a_no"));
					}

					return advertiser;
				} else {
					throw new Exception("濡쒓렇�씤 泥댄겕 ���엯NO �삤瑜�");
				}
			}

		} catch (Exception ex) {
			throw new MemberException("濡쒓렇�씤 泥댄겕 �떆 �삤瑜�  : " + ex.toString());
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
		return null;
	}

	public List<Member> idFind(Member mem) throws MemberException {
		List<Member> idList = new ArrayList<Member>();
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "select * from member where mem_name = ? and mem_tel = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mem.getName());
			ps.setString(2, mem.getPhone());
			rs = ps.executeQuery();
			if (rs.next()) {
				mem = new Member(rs.getString("mem_email"));
				idList.add(mem);
			} else {
				return null;
			}
			return idList;
		} catch (Exception ex) {
			throw new MemberException("�븘�씠�뵒 李얘린 �떆 �삤瑜�  : " + ex.toString());
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

	public List<Member> passwordFind(Member mem) throws MemberException {
		List<Member> pwList = new ArrayList<Member>();
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "select * from member where mem_email = ? and mem_tel = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mem.getEmail());
			ps.setString(2, mem.getPhone());
			rs = ps.executeQuery();
			if (rs.next()) {
				mem = new Member(rs.getString("mem_email"), rs.getString("mem_pw"));
				pwList.add(mem);
			} else {
				return null;
			}
			return pwList;
		} catch (Exception ex) {
			throw new MemberException("鍮꾨�踰덊샇 李얘린 �떆 �삤瑜�  : " + ex.toString());
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

	// �겕由ъ뿉�씠�꽣 �씤�꽌�듃
	public void CrInsert(Creator ct, int mem_no, int num) throws MemberException {
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "INSERT INTO Creator(c_no,c_chanel,c_intro,c_content,mem_no,cg_no,c_url,c_rurl,c_name, c_count) VALUES(c_no_seq.nextval,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, ct.getC_chanel());
			ps.setString(2, ct.getC_intro());
			ps.setString(3, ct.getC_content());
			ps.setInt(4, mem_no);
			ps.setInt(5, num);
			ps.setString(6, ct.getC_url());
			ps.setString(7, ct.getC_rurl());
			ps.setString(8, ct.getC_name());
			ps.setInt(9, ct.getC_count());

			ps.executeUpdate();

		} catch (Exception ex) {
			throw new MemberException("�겕由ъ뿉�씠�꽣 ) DB�뿉 �엯�젰�떆 �삤瑜�  : " + ex.toString());
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

	public int getSeqCreator() throws MemberException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int c_no = 0;
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "select max(c_no) from creator";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				c_no = rs.getInt(1);
			}
			return c_no;

		} catch (

		Exception ex) {
			throw new MemberException("�겕由ъ뿉�씠�꽣 get ) DB�뿉 �엯�젰�떆 �삤瑜�  : " + ex.toString());
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

	public void CrInfoInsert(Creator ct, int cr_no) throws MemberException {
		PreparedStatement ps = null;
		try {

			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			String sql = "INSERT INTO Creator_Info VALUES(ci_no_seq.nextval,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, ct.getCi_addr());
			ps.setString(2, ct.getCi_term());
			ps.setInt(3, ct.getCi_price());
			ps.setString(4, ct.getCi_ad());
			ps.setString(5, ct.getCi_region());
			ps.setInt(6, cr_no);

			ps.executeUpdate();

		} catch (Exception ex) {
			throw new MemberException("�겕由ъ뿉�씠�꽣 �씤�룷 ) DB�뿉 �엯�젰�떆 �삤瑜�  : " + ex.toString());
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

	// �겕由ъ뿉�씠�꽣 �씤�꽌�듃
	public void CrUpdate(Creator ct, int mem_no, int num) throws MemberException {
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "Update Creator set " + "c_chanel=?,c_intro=?,c_content=?,cg_no=?,c_url=?,c_rurl=?,c_name=?, c_count=? "
					+ "where mem_no = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, ct.getC_chanel());
			ps.setString(2, ct.getC_intro());
			ps.setString(3, ct.getC_content());
			ps.setInt(4, num);
			ps.setString(5, ct.getC_url());
			ps.setString(6, ct.getC_rurl());
			ps.setString(7, ct.getC_name());
			ps.setInt(8, ct.getC_count());
			System.out.println(ct.getC_count());
			ps.setInt(9, mem_no);

			ps.executeUpdate();

		} catch (Exception ex) {
			throw new MemberException("�겕由ъ뿉�씠�꽣 ) DB�뿉 �엯�젰�떆 �삤瑜�  : " + ex.toString());
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

	public void CrInfoUpdate(Creator ct, int cr_no) throws MemberException {
		PreparedStatement ps = null;
		try {

			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			String sql = "Update Creator_Info set " + "ci_addr=?,ci_term=?,ci_price=?,ci_ad=?,ci_region=?"
					+ "where c_no = ?";

			ps = con.prepareStatement(sql);

			ps.setString(1, ct.getCi_addr());
			ps.setString(2, ct.getCi_term());
			ps.setInt(3, ct.getCi_price());
			ps.setString(4, ct.getCi_ad());
			ps.setString(5, ct.getCi_region());
			ps.setInt(6, cr_no);

			ps.executeUpdate();

		} catch (Exception ex) {
			throw new MemberException("CreatorInfo ) DB CreatorInfo  : " + ex.toString());
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
}*/
package ad.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

	// Single Pattern
	private static MemberDao instance;

	// DB �뿰寃곗떆 愿��븳 蹂��닔
	private static final String dbDriver = "oracle.jdbc.driver.OracleDriver";
	private static final String dbUrl = "jdbc:oracle:thin:@127.0.0.1:1521:iot3";
	private static final String dbUser = "ab";
	private static final String dbPass = "ab";

	private Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// --------------------------------------------
	// ##### 媛앹껜 �깮�꽦�븯�뒗 硫붿냼�뱶
	public static MemberDao getInstance() throws MemberException {
		if (instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}

	public MemberDao() throws MemberException {

		try {
			Class.forName(dbDriver);
		} catch (Exception ex) {
			throw new MemberException("DB �뿰寃곗떆 �삤瑜�   : " + ex.toString());
		}
	}

	public int emailCheck(Member mem) throws MemberException {
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			String sql = "select * from member where MEM_EMAIL = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mem.getEmail());
			rs = ps.executeQuery();
			while (rs.next()) {
				return -1;
			}

			System.out.println("�씠硫붿씪泥댄겕�맖");
			return 1;
		} catch (Exception ex) {
			throw new MemberException("ID CHECK �떆 �삤瑜� : " + ex.toString());
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

	public void memberInsert(Member mem, String adrNo) throws MemberException {

		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			String sqlMember = "insert into member(MEM_NO, MEM_PW, MEM_NAME, MEM_EMAIL, TYPE_NO, MEM_TEL) values(mem_no_seq.nextval, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sqlMember);
			ps.setString(1, mem.getPassword());
			ps.setString(2, mem.getName());
			ps.setString(3, mem.getEmail());
			ps.setInt(4, mem.getType());
			ps.setString(5, mem.getPhone());
			ps.executeUpdate();

			if (mem.getType() == 2) {
				// advertiser
				String sqlAdvertiser = "insert into advertiser(a_no, mem_no, a_ceo, a_ename, a_eno, a_tel) values(a_no_seq.nextval, mem_no_seq.currval, ?, ?, ?, ?)";
				ps = con.prepareStatement(sqlAdvertiser);
				ps.setString(1, "2");
				ps.setString(2, mem.getName());
				ps.setString(3, adrNo);
				ps.setString(4, mem.getPhone());
				ps.executeUpdate();

				//email to get adNo
				int adno = getAdNo(mem);
				con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				String sqlAdvertiserCash = "insert into cash(ca_no, ca_recharge, ca_balance, a_no) values(ca_no_seq.nextval, 0, 0, ?)";
				ps = con.prepareStatement(sqlAdvertiserCash);
				ps.setInt(1, adno);
				ps.executeUpdate();
			}
		} catch (Exception ex) {
			throw new MemberException("MEMBER �엯�젰 �떆 �삤瑜�  : " + ex.toString());
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

	public Member loginCheck(Member mem) throws MemberException {

		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "select * from member where mem_email = ? and mem_pw = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mem.getEmail());
			ps.setString(2, mem.getPassword());
			rs = ps.executeQuery();
			while (rs.next()) {
				int memNo = rs.getInt("MEM_NO");
				String email = rs.getString("MEM_EMAIL");
				String pw = rs.getString("MEM_PW");
				String name = rs.getString("MEM_NAME");
				String phone = rs.getString("MEM_TEL");
				int type = rs.getInt("TYPE_NO");

				if (type == 1) {
					Creator creator = new Creator();
					creator.setMemNo(memNo);
					creator.setMem_no(memNo);
					creator.setEmail(email);
					creator.setPassword(pw);
					creator.setName(name);
					creator.setPhone(phone);
					creator.setType(type);

					//member다오 수정 0209
					String sql2 = "select * from creator c, creator_info ci, creator_image img where mem_no = ? and c.c_no = ci.c_no and c.c_no = img.c_no";
					ps = con.prepareStatement(sql2);
					ps.setInt(1, memNo);
					rs = ps.executeQuery();
					if (rs.next()) {
						creator.setC_no(rs.getInt("c_no"));
						creator.setC_chanel(rs.getString("c_chanel"));
						creator.setC_intro(rs.getString("c_intro"));
						creator.setC_content(rs.getString("c_content"));
						creator.setC_url(rs.getString("c_url"));
						creator.setC_rurl(rs.getString("c_rurl"));
						creator.setC_name(rs.getString("c_name"));
						creator.setC_count(rs.getInt("c_count"));
						creator.setCi_no(rs.getInt("ci_no"));
						creator.setCi_addr(rs.getString("ci_addr"));
						creator.setCi_term(rs.getString("ci_term"));
						creator.setCi_price(rs.getInt("ci_price"));
						creator.setCi_ad(rs.getString("ci_ad"));
						creator.setCi_region(rs.getString("ci_region"));
						creator.setCg_no(rs.getInt("cg_no"));
						creator.setImg_name(rs.getString("cimg_name"));
						creator.setImg_src(rs.getString("cimg_path"));
					}

					return creator;
				} else if (type == 2) {
					Advertiser advertiser = new Advertiser();
					advertiser.setMemNo(memNo);
					advertiser.setEmail(email);
					advertiser.setPassword(pw);
					advertiser.setName(name);
					advertiser.setPhone(phone);
					advertiser.setType(type);
					
					String sql2 = "SELECT a.a_eno, a.a_ename, a.a_no, c.ca_balance, (SELECT aimg_name FROM advertiser_image ai WHERE a.a_no = ai.a_no) as aimg_name, (SELECT aimg_path FROM advertiser_image ai WHERE a.a_no = ai.a_no) as aimg_path " + 
								  "FROM advertiser a, cash c " + 
								  "WHERE a.a_no = c.a_no " + 
								  "AND a.mem_no = ?";
					ps = con.prepareStatement(sql2);
					ps.setInt(1, memNo);
					rs = ps.executeQuery();
					if (rs.next()) {
						advertiser.setA_eno(rs.getString("a_eno"));
						advertiser.setA_name(rs.getString("a_ename"));
						advertiser.setA_no(rs.getInt("a_no"));
						advertiser.setCash(rs.getInt("ca_balance"));
						advertiser.setImg_name(rs.getString("aimg_name"));
						advertiser.setImg_src(rs.getString("aimg_path"));
					}

					return advertiser;
				} else {
					throw new Exception("Advertiser 오류");
				}
			}

		} catch (Exception ex) {
			throw new MemberException("濡쒓렇�씤 泥댄겕 �떆 �삤瑜�  : " + ex.toString());
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
		return null;
	}

	public List<Member> idFind(Member mem) throws MemberException {
		List<Member> idList = new ArrayList<Member>();
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "select * from member where mem_name = ? and mem_tel = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mem.getName());
			ps.setString(2, mem.getPhone());
			rs = ps.executeQuery();
			if (rs.next()) {
				mem = new Member(rs.getString("mem_email"));
				idList.add(mem);
			} else {
				return null;
			}
			return idList;
		} catch (Exception ex) {
			throw new MemberException("�븘�씠�뵒 李얘린 �떆 �삤瑜�  : " + ex.toString());
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

	public List<Member> passwordFind(Member mem) throws MemberException {
		List<Member> pwList = new ArrayList<Member>();
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "select * from member where mem_email = ? and mem_tel = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mem.getEmail());
			ps.setString(2, mem.getPhone());
			rs = ps.executeQuery();
			if (rs.next()) {
				mem = new Member(rs.getString("mem_email"), rs.getString("mem_pw"));
				pwList.add(mem);
			} else {
				return null;
			}
			return pwList;
		} catch (Exception ex) {
			throw new MemberException("鍮꾨�踰덊샇 李얘린 �떆 �삤瑜�  : " + ex.toString());
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

	// �겕由ъ뿉�씠�꽣 �씤�꽌�듃
	public void CrInsert(Creator ct, int mem_no, int num) throws MemberException {
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "INSERT INTO Creator(c_no,c_chanel,c_intro,c_content,mem_no,cg_no,c_url,c_rurl,c_name, c_count) VALUES(c_no_seq.nextval,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, ct.getC_chanel());
			ps.setString(2, ct.getC_intro());
			ps.setString(3, ct.getC_content());
			ps.setInt(4, mem_no);
			ps.setInt(5, num);
			ps.setString(6, ct.getC_url());
			ps.setString(7, ct.getC_rurl());
			ps.setString(8, ct.getC_name());
			ps.setInt(9, ct.getC_count());

			ps.executeUpdate();

		} catch (Exception ex) {
			throw new MemberException("�겕由ъ뿉�씠�꽣 ) DB�뿉 �엯�젰�떆 �삤瑜�  : " + ex.toString());
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

	
	public int getAdNo(Member mem) throws MemberException{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		int adno = 0;
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "SELECT a.a_no FROM advertiser a, member m WHERE a.mem_no = m.mem_no AND mem_email = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mem.getEmail());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				adno = rs.getInt(1);
			}
					
					
		} catch (Exception ex) {
			throw new MemberException("�겕由ъ뿉�씠�꽣 get ) DB�뿉 �엯�젰�떆 �삤瑜�  : " + ex.toString());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
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
		
		return adno;
	}
	
	
	public int getSeqCreator() throws MemberException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int c_no = 0;
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "select max(c_no) from creator";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				c_no = rs.getInt(1);
			}
			return c_no;

		} catch (

		Exception ex) {
			throw new MemberException("�겕由ъ뿉�씠�꽣 get ) DB�뿉 �엯�젰�떆 �삤瑜�  : " + ex.toString());
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

	public void CrInfoInsert(Creator ct, int cr_no) throws MemberException {
		PreparedStatement ps = null;
		try {

			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			String sql = "INSERT INTO Creator_Info VALUES(ci_no_seq.nextval,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, ct.getCi_addr());
			ps.setString(2, ct.getCi_term());
			ps.setInt(3, ct.getCi_price());
			ps.setString(4, ct.getCi_ad());
			ps.setString(5, ct.getCi_region());
			ps.setInt(6, cr_no);

			ps.executeUpdate();

		} catch (Exception ex) {
			throw new MemberException("�겕由ъ뿉�씠�꽣 �씤�룷 ) DB�뿉 �엯�젰�떆 �삤瑜�  : " + ex.toString());
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

	// 0209 수정
	public void CrUpdate(Creator ct, int mem_no) throws MemberException {
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "Update Creator set " + "c_chanel=?,c_intro=?,c_content=?,cg_no=?,c_url=?,c_rurl=?,c_name=?, c_count=? "
					+ "where mem_no = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, ct.getC_chanel());
			ps.setString(2, ct.getC_intro());
			ps.setString(3, ct.getC_content());
			ps.setInt(4, ct.getCg_no());
			ps.setString(5, ct.getC_url());
			ps.setString(6, ct.getC_rurl());
			ps.setString(7, ct.getC_name());
			ps.setInt(8, ct.getC_count());
			System.out.println(ct.getC_count());
			ps.setInt(9, mem_no);

			ps.executeUpdate();

		} catch (Exception ex) {
			throw new MemberException("�겕由ъ뿉�씠�꽣 ) DB�뿉 �엯�젰�떆 �삤瑜�  : " + ex.toString());
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

	public void CrInfoUpdate(Creator ct, int cr_no) throws MemberException {
		PreparedStatement ps = null;
		try {

			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			String sql = "Update Creator_Info set " + "ci_addr=?,ci_term=?,ci_price=?,ci_ad=?,ci_region=?"
					+ "where c_no = ?";

			ps = con.prepareStatement(sql);

			ps.setString(1, ct.getCi_addr());
			ps.setString(2, ct.getCi_term());
			ps.setInt(3, ct.getCi_price());
			ps.setString(4, ct.getCi_ad());
			ps.setString(5, ct.getCi_region());
			ps.setInt(6, cr_no);

			ps.executeUpdate();

		} catch (Exception ex) {
			throw new MemberException("CreatorInfo ) DB CreatorInfo  : " + ex.toString());
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

	public void CrImgInsert(Creator ct) throws MemberException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			int c_no = 0;
			
			String selectSql = "SELECT c_no FROM creator WHERE mem_no = ?";
			ps = con.prepareStatement(selectSql);
			ps.setInt(1, ct.getMemNo());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				c_no = rs.getInt(1);
			}
			
			String sql = "INSERT INTO creator_image(cimg_no, c_no, cimg_name, cimg_path) VALUES(cimg_no_seq.nextval, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, c_no);
			ps.setString(2, ct.getImg_name());
			String img_src = "/Adbridge/images/input_img/" + ct.getImg_name();
			ps.setString(3, img_src);

			ps.executeUpdate();

		} catch (Exception ex) {
			throw new MemberException("CrImgInsert ) DB CrImgInsert  : " + ex.toString());
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
	
	public void CrImgUpdate(Creator ct) throws MemberException {
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			String sql = "UPDATE creator_image SET cimg_name = ?, cimg_path = ? WHERE c_no = ?";

			ps = con.prepareStatement(sql);

			ps.setString(1, ct.getImg_name());
			ps.setString(2, ct.getImg_src());
			ps.setInt(3, ct.getC_no());

			ps.executeUpdate();

		} catch (Exception ex) {
			throw new MemberException("CrImgUpdate ) DB CrImgUpdate  : " + ex.toString());
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

	public void AdprofileInsert(Advertiser ad)  throws MemberException {
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			String sql = "INSERT INTO advertiser_image(a_no, aimg_no, aimg_name, aimg_path) VALUES(?, aimg_no_seq.nextval, ?, ?)";

			ps = con.prepareStatement(sql);

			ps.setInt(1, ad.getA_no());
			ps.setString(2, ad.getImg_name());
			ps.setString(3, ad.getImg_src());
			ps.executeUpdate();

		} catch (Exception ex) {
			throw new MemberException("AdprofileInsert ) DB AdprofileInsert  : " + ex.toString());
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

	
	public void AdprofileUpdate(Advertiser ad)  throws MemberException {
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			String sql = "UPDATE advertiser_image SET aimg_name = ?, aimg_path = ? WHERE a_no = ? ";

			ps = con.prepareStatement(sql);

			ps.setString(1, ad.getImg_name());
			ps.setString(2, ad.getImg_src());
			ps.setInt(3, ad.getA_no());
		
			ps.executeUpdate();

		} catch (Exception ex) {
			throw new MemberException("AdprofileUpdate ) DB AdprofileUpdate  : " + ex.toString());
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