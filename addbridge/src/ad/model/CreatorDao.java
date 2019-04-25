package ad.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ad.model.Creator;
import ad.model.CreatorException;

public class CreatorDao {
	// Single Pattern
	private static CreatorDao instance;

	// DB 연결시 관한 변수
	private static final String dbDriver = "oracle.jdbc.driver.OracleDriver";
	private static final String dbUrl = "jdbc:oracle:thin:@127.0.0.1:1521:iot3";
	private static final String dbUser = "ab";
	private static final String dbPass = "ab";

	private Connection con;

	// ##### 객체 생성하는 메소드
	public static CreatorDao getInstance() throws CreatorException {
		if (instance == null) {
			instance = new CreatorDao();
		}
		return instance;
	}

	private CreatorDao() throws CreatorException {
		try {

			/********************************************
			 * 1. 오라클 드라이버를 로딩 ( DBCP 연결하면 삭제할 부분 )
			 */
			Class.forName(dbDriver);
		} catch (Exception ex) {
			throw new CreatorException("DB 연결시 오류  : " + ex.toString());
		}

	}

	// 채널명, 컨텐츠 소개, 컨텐츠 내용, 구독자수 뽑아오는 함수
	public List<Creator> selectList(int c_no) throws CreatorException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Creator> mList = new ArrayList<Creator>();
		boolean isEmpty = true;

		try {

			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "SELECT c_count, c_chanel, c_intro, c_content from creator where c_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, c_no);
			rs = ps.executeQuery();
			if (rs.next()) {
				isEmpty = false;

				int c_count = rs.getInt("c_count");
				String c_chanel = rs.getString("c_chanel");
				String c_intro = rs.getString("c_intro");
				String c_content = rs.getString("c_content");

				Creator m = new Creator(c_count, c_chanel, c_intro, c_content);
				mList.add(m);
			} else if (isEmpty)
				return Collections.emptyList();

			return mList;

		} catch (Exception ex) {
			throw new CreatorException("DB에 목록 검색시 오류  : " + ex.toString());
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

	// 카테고리, 선호광고, 희망단가,크리에이터명을 불러오는 함수
	public List<Creator> selectList2(int c_no) throws CreatorException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Creator> mList2 = new ArrayList<Creator>();
		boolean isEmpty = true;

		try {

			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "SELECT c_name, cg_name, ci_ad, ci_price from creator_info ci, category cg, creator c where c.cg_no = cg.cg_no and ci.ci_no = c.c_no and ci.c_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, c_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				isEmpty = false;
				String c_name = rs.getString("c_name");
				String cg_name = rs.getString("cg_name");
				String ci_ad = rs.getString("ci_ad");
				int ci_price = rs.getInt("ci_price");

				Creator m = new Creator(cg_name, ci_ad, ci_price, c_name);
				mList2.add(m);
			}

			if (isEmpty)
				return Collections.emptyList();

			return mList2;

		} catch (Exception ex) {
			throw new CreatorException("DB에 목록 검색시 오류  : " + ex.toString());
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

	public Creator getCreatorlist(int a_no, int c_no) throws CreatorException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Creator cr = new Creator();
		boolean isEmpty = true;

		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			String sql = "SELECT c.c_no, c.c_name, c.c_chanel, c.c_count, c.c_intro, c.c_content, cg.cg_name, ci.ci_ad, ci.ci_price, cimg.cimg_name, cimg.cimg_path " + 
					 	 "FROM creator c, creator_info ci, creator_image cimg, category cg " + 
					 	 "WHERE c.c_no = ci.c_no " + 
					 	 "AND c.c_no = cimg.c_no " + 
					 	 "AND c.cg_no = cg.cg_no " + 
					 	 "AND c.c_no = ? ";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, c_no);
			rs = ps.executeQuery();
			if (rs.next()) {
				isEmpty = false;
				
				cr.setC_no(rs.getInt("c_no"));
				cr.setC_name(rs.getString("c_name"));
				cr.setC_chanel(rs.getString("c_chanel"));
				cr.setC_count(rs.getInt("c_count"));
				cr.setC_intro(rs.getString("c_intro"));
				cr.setC_content(rs.getString("c_content"));
				cr.setCg_name(rs.getString("cg_name"));
				cr.setCi_ad(rs.getString("ci_ad"));
				cr.setCi_price(rs.getInt("ci_price"));
				cr.setImg_name(rs.getString("cimg_name"));
				cr.setImg_src(rs.getString("cimg_path"));
				
			} else if (isEmpty)
				return null;

			return cr;

		} catch (Exception ex) {
			throw new CreatorException("getCreatorlist 목록 검색시 오류  : " + ex.toString());
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

}
