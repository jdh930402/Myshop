<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cartMgr" class="shop.order.CartMgr" scope="session"/> <!-- session이 살아있는 동안 계속 살아있음 -->
<jsp:useBean id="orderBean" class="shop.order.OrderBean"/>
<jsp:setProperty property="*" name="orderBean"/> <!-- orderbean의 변수명과 jsp의 name이 일치하면 자동으로 등록 -->
<%
	String flag = request.getParameter("flag"); // 구매목록 보기/수정/삭제 판단
	String id = (String)session.getAttribute("idKey");
	
	//out.print("번호: " + orderBean.getProduct_no() + "<br>주문수 : " + orderBean.getQuantity() + "<br>아이디 : "+ id);
	if(id == null){
		response.sendRedirect("login.jsp");
	}else if(flag == null){ // 카트에 주문상품 담기
		orderBean.setId(id);
		cartMgr.addCart(orderBean);
%>
	<script>
		alert('장바구니에 담았습니다.');
		location.href = "cartlist.jsp";
	</script>
<%
	}else if(flag.equals("update")){ // 카트 수정
		orderBean.setId(id);
		cartMgr.updateCart(orderBean);
%>
	<script>
		alert('장바구니 내용 수정');
		location.href = "cartlist.jsp";
	</script>		
		
<%
	}else if(flag.equals("delete")){ // 주문 취소
		orderBean.setId(id);
		cartMgr.deleteCart(orderBean);
%>
	<script>
		alert('해당 상품 주문 삭제');
		location.href = "cartlist.jsp";
	</script>	
<%		
	}
%>