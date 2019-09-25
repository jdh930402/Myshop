package shop.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OrderMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;

	public OrderMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.print("OrderMgr err : " + e);
		}
	}

	public void insertOrder(OrderBean order) {
		int index = 0;
		try {
			conn = ds.getConnection();
			String sql = "insert into shop_order(product_no, quantity, sdate, state, id ) values(?, ?, now(), ?, ? )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++index, order.getProduct_no());
			pstmt.setString(++index, order.getQuantity());
			pstmt.setString(++index, "1");
			pstmt.setString(++index, order.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertOrder err : " + e);
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
	}

	public ArrayList<OrderBean> getOrder(String id) {
		ArrayList<OrderBean> list = new ArrayList<OrderBean>();
		try {
			conn = ds.getConnection();
			String sql = "select * from shop_order where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int columnIndex = 0;
				OrderBean bean = new OrderBean();
				bean.setNo(rs.getString(++columnIndex));
				bean.setProduct_no(rs.getString(++columnIndex));
				bean.setQuantity(rs.getString(++columnIndex));
				bean.setSdate(rs.getString(++columnIndex));
				bean.setState(rs.getString(++columnIndex));
				bean.setId(rs.getString(++columnIndex));
				list.add(bean);
			}
		} catch (Exception e) {
			System.out.println("getOrder err : " + e);
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

	public ArrayList<OrderBean> getOrderAll() {
		ArrayList<OrderBean> list = new ArrayList<OrderBean>();
		try {
			conn = ds.getConnection();
			String sql = "select * from shop_order order by no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int columnIndex = 0;
				OrderBean bean = new OrderBean();
				bean.setNo(rs.getString(++columnIndex));
				bean.setProduct_no(rs.getString(++columnIndex));
				bean.setQuantity(rs.getString(++columnIndex));
				bean.setSdate(rs.getString(++columnIndex));
				bean.setState(rs.getString(++columnIndex));
				bean.setId(rs.getString(++columnIndex));
				list.add(bean);
			}
		} catch (Exception e) {
			System.out.println("getOrder err : " + e);
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

	public OrderBean getOrderDetail(String no) {
		OrderBean bean = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from shop_order where no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int index = 0;
				bean = new OrderBean();
				bean.setNo(rs.getString(++index));
				bean.setProduct_no(rs.getString(++index));
				bean.setQuantity(rs.getString(++index));
				bean.setSdate(rs.getString(++index));
				bean.setState(rs.getString(++index));
				bean.setId(rs.getString(++index));
			}

		} catch (Exception e) {
			System.out.println("getOrderDetail err : " + e);
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
		return bean;
	}

	public boolean updateOrder(String no, String state) {
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "update shop_order set state=? where no=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, state);
			pstmt.setString(2, no);
			if (pstmt.executeUpdate() > 0) {
				b = true;
			}
		} catch (Exception e) {
			System.out.println("updateOrder err : " + e);
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
		return b;
	}

	public boolean deleteOrder(String no) {
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "delete from shop_order where no=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			if (pstmt.executeUpdate() > 0) {
				b = true;
			}
		} catch (Exception e) {
			System.out.println("deleteOrder err : " + e);
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
		return b;
	}
}