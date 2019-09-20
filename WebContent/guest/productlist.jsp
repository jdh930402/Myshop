<%@page import="shop.product.ProductBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="shop.product.ProductMgr"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 : 상품 목록</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<%@include file="guest_top.jsp" %>
<table style="width: 90%">
	<tr style="background-color: gold;">
		<th>상품</th>
		<th>가격</th>
		<th>재고량</th>
		<th>상세보기</th>
	</tr>

<%
	ArrayList<ProductBean> list = productMgr.getProductAll();
	for(ProductBean bean : list){ %>
	
	<tr style="text-align: center;">
		<td>
			<img src="../data/<%=bean.getImage()%>" width="100">
			<%=bean.getName()%>
		</td>
		
		<td>
			<%=bean.getPrice()%>
		</td>

		<td>
			<%=bean.getStock()%>
		</td>

		<td>
			<a href="javascript:productDetail('<%=bean.getNo()%>')">보기</a>
		</td>
	</tr>
	
	<%} %>
</table>
<form action="productdetail_g.jsp" name="detailForm" method="post">
	<input type="hidden" name="no">
</form>
<%@include file="guest_bottom.jsp" %>
</body>
</html>