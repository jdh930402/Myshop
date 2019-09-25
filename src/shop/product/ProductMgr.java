package shop.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import shop.order.OrderBean;

public class ProductMgr {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;

	public ProductMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.print("MemberMgr err : " + e);
		}
	}

	public ArrayList<ProductBean> getProductAll() {
		ArrayList<ProductBean> list = new ArrayList<ProductBean>();
		try {
			conn = ds.getConnection();
			String sql = "select * from shop_product";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int index = 0;
				ProductBean bean = new ProductBean();
				bean.setNo(rs.getInt(++index));
				bean.setName(rs.getString(++index));
				bean.setPrice(rs.getString(++index));
				bean.setDetail(rs.getString(++index));
				bean.setSdate(rs.getString(++index));
				bean.setStock(rs.getString(++index));
				bean.setImage(rs.getString(++index));
				list.add(bean);
			}
		} catch (Exception e) {
			System.out.println("getProductAll err : " + e);
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

	// productproc.jsp의 request변수를 사용하기 위해서는 HttpServletRequest타입으로 받아야한다.
	public boolean insertProduct(HttpServletRequest request) {
		boolean b = false;
		try {
			// 업로드 할 파일의 절대경로 => 상대경로는 허용x 
			// String uploadDir = "C:\\dev\\eclipse-workspace\\Myshop\\WebContent\\data"; => window용
			String uploadDir = "C:/dev/eclipse-workspace/Myshop/WebContent/data";
			MultipartRequest multi = new MultipartRequest(request, uploadDir, 5 * 1024 * 1024, "utf-8",
					new DefaultFileRenamePolicy());
			conn = ds.getConnection();
			String sql = "insert into shop_product(name, price, detail, sdate, stock, image) values(?, ?, ?, now(), ?, ?)";
			pstmt = conn.prepareStatement(sql);
			int index = 0;
			pstmt.setString(++index, multi.getParameter("name"));
			pstmt.setString(++index, multi.getParameter("price"));
			pstmt.setString(++index, multi.getParameter("detail"));
			pstmt.setString(++index, multi.getParameter("stock"));
			System.out.println(multi.getFilesystemName("image"));
			if (multi.getFilesystemName("image") == null) {
				pstmt.setString(++index, "ready.gif");
				System.out.println(index);
			} else {
				pstmt.setString(++index, multi.getFilesystemName("image"));
			}
			// 이미지는 multi데이터로 업로드가 되고 파일명은 db로 들어간다.
			if (pstmt.executeUpdate() > 0) {
				b = true;
			}
		} catch (Exception e) {
			System.out.println("insertProduct err : " + e);
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

	public ProductBean getProduct(String no) {
		ProductBean bean = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from shop_product where no=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int index = 0;
				bean = new ProductBean();
				bean.setNo(rs.getInt(++index));
				bean.setName(rs.getString(++index));
				bean.setPrice(rs.getString(++index));
				bean.setDetail(rs.getString(++index));
				bean.setSdate(rs.getString(++index));
				bean.setStock(rs.getString(++index));
				bean.setImage(rs.getString(++index));
			}
		} catch (Exception e) {
			System.out.println("getProduct err : " + e);
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

	public boolean updateProduct(HttpServletRequest request) {
		boolean b = false;
		try {
			int index = 0;
			String uploadDir = "C:/dev/eclipse-workspace/Myshop/WebContent/data";
			MultipartRequest multi = new MultipartRequest(request, uploadDir, 5 * 1024 * 1024, "utf-8",
					new DefaultFileRenamePolicy());
			conn = ds.getConnection();
			
			if( multi.getFilesystemName("image") == null) {
				String sql = "update shop_product set name=?, price=?, detail=?, sdate=now(), stock=? where no=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(++index, multi.getParameter("name"));
				pstmt.setString(++index, multi.getParameter("price"));
				pstmt.setString(++index, multi.getParameter("detail"));
				pstmt.setString(++index, multi.getParameter("stock"));
				pstmt.setString(++index, multi.getParameter("no"));	
			}else {
				String sql = "update shop_product set name=?, price=?, detail=?, sdate=now(), stock=?, image=? where no=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(++index, multi.getParameter("name"));
				pstmt.setString(++index, multi.getParameter("price"));
				pstmt.setString(++index, multi.getParameter("detail"));
				pstmt.setString(++index, multi.getParameter("stock"));
				pstmt.setString(++index, multi.getFilesystemName("image"));	
				pstmt.setString(++index, multi.getParameter("no"));
			}
			if (pstmt.executeUpdate() > 0) {
				b = true;
			}
		} catch (Exception e) {
			System.out.println("updateProduct err : " + e);
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
	
	public boolean deleteProduct(String no) {
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "delete from shop_product where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			if(pstmt.executeUpdate()>0) {
				b = true;
			}
		} catch(Exception e) {
			System.out.println("deleteProduct err : " + e);
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
	
	public void reduceProduct(OrderBean order) {
		try {
			conn = ds.getConnection();
			String sql = "update shop_product set stock=(stock - ? ) where no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order.getQuantity());
			pstmt.setString(2, order.getProduct_no());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("reduceProduct err :" + e);
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
	
}
