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
	
	public ArrayList<ProductBean> getProductAll(){
		ArrayList<ProductBean> list = new ArrayList<ProductBean>();
		try {
			conn = ds.getConnection();
			String sql = "select * from shop_product";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
				// 업로드 할 파일의 절대경로
				//String uploadDir = "C:\\dev\\eclipse-workspace\\Myshop\\WebContent\\data"; => window용
				String uploadDir = "C:/dev/eclipse-workspace/Myshop/WebContent/data"; 
				MultipartRequest multi = new MultipartRequest(request, uploadDir, 5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
				conn = ds.getConnection();
				String sql = "insert into shop_product(name, price, detail, sdate, stock, image) values(?, ?, ?, now(), ?, ?)";
				pstmt = conn.prepareStatement(sql);
				int index = 0;
				pstmt.setString(++index, multi.getParameter("name"));
				pstmt.setString(++index, multi.getParameter("price"));
				pstmt.setString(++index, multi.getParameter("detail"));
				pstmt.setString(++index, multi.getParameter("stock"));
				if(multi.getFilesystemName("image") == null) {
					pstmt.setString(++index, "ready.gif");
					System.out.println(index);
				} else {
					pstmt.setString(++index, multi.getFilesystemName("image"));
					System.out.println(index);
				}
				// 이미지는 multi데이터로 업로드가 되고  파일명은 db로 들어간다.
				if(pstmt.executeUpdate() > 0) {
					b= true;
				}
				System.out.println("1");
 			} catch(Exception e) {
				System.out.println("insertProduct err : " + e);
			}  finally {
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
