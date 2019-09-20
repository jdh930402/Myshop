<%@page import="shop.product.ProductBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="productMgr" class="shop.product.ProductMgr"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품관리</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
** 관리자 : 전체 상품 목록 **<br/>
<%@include file="admin_top.jsp"%>

<a href="productinsert.jsp">[상품등록]</a><br>
<table style="width: 90%">
	<tr style="background-color: #ccbbaa">
		<th>번호</th>
		<th>상품명</th>
		<th>가격</th>
		<th>등록일</th>
		<th>재고량</th>
		<th>상세보기</th>
	</tr>
<%
	ArrayList<ProductBean> list = productMgr.getProductAll();
	if(list.size() == 0){ %>
	<tr><td colspan="6">등록된 상품이 없습니다.</td></tr>
			
<% 	}else{
	int index = 0;	
	for(ProductBean bean : list){
%>
	<tr>
		<td><%=++index%></td>
		<td><%=bean.getName()%></td>
		<td><%=bean.getPrice()%></td>
		<td><%=bean.getSdate()%></td>
		<td><%=bean.getStock()%></td>
		<td><a href="javascript:productDetail('<%=bean.getNo()%>')">상세보기</a></td>
	</tr>
	<%	} %>
<%
	}	
%>
	
</table>
<%@include file="admin_bottom.jsp"%>

<form action="productdetail.jsp" name="detailForm" method="post">
	<input type="hidden" name="no">
</form>

</body>
</html>