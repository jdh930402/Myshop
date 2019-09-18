package shop.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberMgr { // DAO
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;

	public MemberMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.print("MemberMgr err : " + e);
		}
	}

	// 우편번호 검색
	public ArrayList<ZipcodeBean> zipcodeRead(String area3) {
		ArrayList<ZipcodeBean> list = new ArrayList<ZipcodeBean>();
		try {
			conn = ds.getConnection();
			String sql = "select * from ziptab where area3 like ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, area3 + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ZipcodeBean bean = new ZipcodeBean();
				bean.setZipcode(rs.getString("zipcode"));
				bean.setArea1(rs.getString("area1"));
				bean.setArea2(rs.getString("area2"));
				bean.setArea3(rs.getString("area3"));
				bean.setArea4(rs.getString("area4"));
				list.add(bean);
			}
		} catch (Exception e) {
			System.out.print("zipcodeRead err : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public boolean chkId(String id) {
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "select id from member where id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			b = rs.next();
		} catch (Exception e) {
			System.out.println("chkId err : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	public boolean memberInsert(MemberBean bean) {
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "insert into member values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPasswd());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getEmail());
			pstmt.setString(5, bean.getPhone());
			pstmt.setString(6, bean.getZipcode());
			pstmt.setString(7, bean.getAddress());
			pstmt.setString(8, bean.getJob());
			if (pstmt.executeUpdate() > 0) {
				b = true;
			}
		} catch (Exception e) {
			System.out.println("memberInsert err : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	public boolean loginChk(String id, String passwd) {
		boolean b = false;
		try {
			int index = 0;
			conn = ds.getConnection();
			String sql = "select id, passwd from member where id=? and passwd=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++index, id);
			pstmt.setString(++index, passwd);
			rs = pstmt.executeQuery();
			b = rs.next();
		} catch (Exception e) {
			System.out.println("loginChk err : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	public MemberDto getMember(String id) {
		MemberDto dto = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from member where id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int index = 0;
				dto = new MemberDto();
				dto.setId(rs.getString(++index));
				dto.setPasswd(rs.getString(++index));
				dto.setName(rs.getString(++index));
				dto.setEmail(rs.getString(++index));
				dto.setPhone(rs.getString(++index));
				dto.setZipcode(rs.getString(++index));
				dto.setAddress(rs.getString(++index));
				dto.setJob(rs.getString(++index));
			}
		} catch (Exception e) {
			System.out.println("getMember err : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	public boolean memberUpdate(String id, MemberBean bean) {
		boolean b = false;
		int index = 0;
		try {
			conn = ds.getConnection();
			String sql = "update member set passwd=?, name=?, email=?, phone=?, zipcode=?, address=?, job=? where id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++index, bean.getPasswd());
			pstmt.setString(++index, bean.getName());
			pstmt.setString(++index, bean.getEmail());
			pstmt.setString(++index, bean.getPhone());
			pstmt.setString(++index, bean.getZipcode());
			pstmt.setString(++index, bean.getAddress());
			pstmt.setString(++index, bean.getJob());
			pstmt.setString(++index, id);
			if(pstmt.executeUpdate() > 0) {
				b=true;
			}
		} catch (Exception e) {
			System.out.println("memberUpdate err : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return b;
	}
}
