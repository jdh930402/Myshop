<%@page import="shop.product.ProductMgr"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="shop.product.ProductMgr"/>
<%
	String flag = request.getParameter("flag");
	boolean result = false;
	
	if(flag.equals("insert")){
		result = productMgr.insertProduct(request); // request는 service메서드를 오버라이딩 한것이기 때문에 servlet에서 컴파일하면서 이전 form에서 전송한 데이터를 무조건 컴파일하게 된다.
		
	}else if(flag.equals("update")){
		result = productMgr.updateProduct(request);
	}else if(flag.equals("delete")){
		result = productMgr.deleteProduct(request.getParameter("no"));
	}else{
		response.sendRedirect("productmanager.jsp");
	}
	
	if(result){%>
		<script>
		alert('정상 처리 되었습니다.');
		location.href = "productmanager.jsp";
		</script>
<%	}else{	%>
		<script>
		alert('작업 실패');
		location.href = "productmanager.jsp";		
		</script>
<%	}	%>