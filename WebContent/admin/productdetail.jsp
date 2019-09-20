<%@page import="shop.product.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="shop.product.ProductMgr"/>
<%
	String no = request.getParameter("no");
	ProductBean bean = productMgr.getProduct(no);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<%@include file="admin_top.jsp"%>
<table style="width: 80%">
	<tr>
		<td style="width: 20%">
			<img src="../data/<%=bean.getImage()%>" width="150">			
		</td>
		
		<td style="width: 50%">
			<table style="width: 100%">
				<tr>
					<td>번호 : </td>
					<td><%=bean.getNo()%>
				</tr>
				<tr>
					<td>상품명 : </td>
					<td><%=bean.getName()%>
				</tr>
				<tr>
					<td>가 격 : </td>
					<td><%=bean.getPrice()%>
				</tr>
				<tr>
					<td>등록일 : </td>
					<td><%=bean.getSdate()%>
				</tr>
				<tr>
					<td>재고량 : </td>
					<td><%=bean.getStock()%>
				</tr>
			
			</table>
		</td>
		
		<td style="width: 30%; vertical-align: top;">
			<b>* 상품 설명 *</b><br>
			<%=bean.getDetail()%>
		</td>
	</tr>
	
	<tr>
		<td colspan="3">
			<a href="javascript:productUpdate('<%=bean.getNo()%>')">수정하기</a>
			<a href="javascript:productDelete('<%=bean.getNo()%>')">삭제하기</a>
		</td>
	</tr>
</table>
<%@include file="admin_bottom.jsp"%>

<form action="productupdate.jsp" name="updateForm" method="post">
	<input type="hidden" name="no">
</form>

<form action="productproc.jsp?flag=delete" name="deleteForm" method="post">
	<input type="hidden" name="no">
</form>
</body>
</html>