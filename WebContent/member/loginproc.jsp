<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="shop.member.MemberMgr"/>
<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	boolean b = memberMgr.loginChk(id,passwd);
	if( b == true ){
		session.setAttribute("idKey", id);
		response.sendRedirect("../guest/guest_index.jsp");
	}else{
		response.sendRedirect("loginfail.html");
	}
%>