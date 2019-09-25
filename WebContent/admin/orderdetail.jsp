<%@page import="shop.product.ProductMgr"%>
<%@page import="shop.product.ProductBean"%>
<%@page import="shop.order.OrderBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="orderMgr" class="shop.order.OrderMgr"/>
<jsp:useBean id="produtMgr" class="shop.product.ProductMgr"/>

<%
	OrderBean order = orderMgr.getOrderDetail(request.getParameter("no"));
	ProductBean product = produtMgr.getProduct(order.getProduct_no());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>

</head>
<body>

<%@include file = "admin_top.jsp"%>
<form action="orderproc_admin.jsp" name = "detailForm" method="post">
<table style="width: 90%">
	<tr>
		<td> 고객아이디 : <%=order.getId()%> </td>
		<td> 주 문 번 호 : <%=order.getNo()%> </td>
		<td> 상 품 번 호 : <%=product.getNo()%> </td>
		<td> 상 품 명 : <%=product.getName()%> </td>
	</tr>
	
	<tr>
		<td>상 품 가 격 : <%=product.getPrice() %></td>
		<td>주 문 수 량 : <%=order.getQuantity() %></td>
		<td>재 고 수 : <%=product.getStock() %></td>
		<td>주 문 일 : <%=order.getSdate() %></td>
	</tr>
	
	<tr>
		<td colspan="4"> 총 결제 금액 :
		<%= Integer.parseInt(order.getQuantity()) * Integer.parseInt(product.getPrice()) %>	
	</tr>
	
	<tr>
		<td colspan="4" style="text-align: center;">
			주 문 상 태 :
				<select name="state">
					<option value="1">접수</option>
					<option value="2">입급확인</option>
					<option value="3">배송준비</option>
					<option value="4">배송중</option>
					<option value="5">배송완료</option>
				</select>
				<script>
					document.detailForm.value = <%=order.getState()%>
				</script>
		</td>
	</tr>
	<tr>
		<td colspan="4" style="text-align: center;">
			<input type="button" value="수정" onclick="javascript:orderUpdate(this.form)">
			<input type="button" value="삭제" onclick="javascript:orderDelete(this.form)">
			<input type="hidden" name="no" value="<%=order.getNo()%>">
			<input type="hidden" name="flag">
		</td>		
	</tr>
</table>
</form>
<%@include file = "admin_bottom.jsp"%>
</body>
</html>