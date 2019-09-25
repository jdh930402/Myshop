<%@page import="shop.order.OrderBean"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="cartMgr" class="shop.order.CartMgr" scope="session"/>
<jsp:useBean id="orderMgr" class="shop.order.OrderMgr"/>
<jsp:useBean id="productMgr" class="shop.product.ProductMgr"/>

<%
	Hashtable hCart = cartMgr.getCartList();
	Enumeration enu = hCart.keys();
	if(hCart.size() == 0){ %>
		<script>
			alert('주문 내역이 없습니다.');
			location.href = "orederlist.jsp"
		</script>
<%	}else{
		while(enu.hasMoreElements()){
			OrderBean order = (OrderBean)hCart.get(enu.nextElement());
			orderMgr.insertOrder(order); // 주문 정보를 DB에 저장
			productMgr.reduceProduct(order); // 주문 수량만큼 재고량에서 지우는 작업
			cartMgr.deleteCart(order); // 주문 완료시 카트에 있는 데이터를 날리는 작업;
		} %>
		
			<script>
			alert('주문 처리 완료');
			location.href = "orderlist.jsp"
			</script>
<%	}  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>