<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="shop.member.MemberMgr"/>
<%
	String adminId = request.getParameter("adminid");
	String adminPasswd = request.getParameter("adminpasswd");
	
	boolean b = memberMgr.addminLoginCheck(adminId, adminPasswd);
	
	if(b){
		session.setAttribute("adminKey", adminId); // 관리자 세션 설정		
		response.sendRedirect("admin_index.jsp");
	}else{%>
	<script>
		alert('관리자 입력자료 불일치!');
		location.href = "adminlogin.jsp";
	</script>
<%  } %>