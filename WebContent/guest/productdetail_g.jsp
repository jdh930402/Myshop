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
** 고객용 상품 상세보기 ** <p/>
<%@include file="guest_top.jsp"%>
<form action="cartproc.jsp">
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
				
				<tr>
					<td>주문 수:</td>
					<td>
						<input type="text" name="quantity" value="1" size="5" style="text-align: center;">
					</td>
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
			<br>
			<input type="hidden" name="product_no" value="<%=bean.getNo()%>">
			<input type="submit" value="장바구니에 담기">
			<input type="button" value="이전으로 이동" onclick="history.back()">
		</td>
	</tr>
</table>
</form>
<%@include file="guest_bottom.jsp"%>
</body>
</html>