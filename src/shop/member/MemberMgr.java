package shop.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	public MemberMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.print("MemberMgr err : " + e);
		}
	}
	// 우편번호 검색
	public ArrayList<ZipcodeBean> zipcodeRead(String area3){
		ArrayList<ZipcodeBean> list = new ArrayList<ZipcodeBean>();
		try {
			conn = ds.getConnection();
			String sql = "select * from ziptab where area3 like ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, area3+"%");
			rs = pstmt.executeQuery();
			while(rs.next()){
				ZipcodeBean bean = new ZipcodeBean();
				bean.setZipcode(rs.getString("zipcode"));
				bean.setArea1(rs.getString("area1"));
				bean.setArea2(rs.getString("area2"));
				bean.setArea3(rs.getString("area3"));
				bean.setArea4(rs.getString("area4"));
				list.add(bean);
			}
		}catch(Exception e) {
			System.out.print("zipcodeRead err : " + e);
		}finally {
				try {
					if(rs != null)
					rs.close();
					if(pstmt != null)
						pstmt.close();
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return list;
	}	
}
