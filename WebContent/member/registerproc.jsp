<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="memberBean" class="shop.member.MemberBean"/>
<jsp:useBean id="memberMgr" class="shop.member.MemberMgr"/>
<jsp:setProperty property="*" name="memberBean"/> <!-- register과 memberBean의 변수명이 같아야한다. -->
<% 
	boolean b = memberMgr.memberInsert(memberBean);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	if(b){
		out.print("<b>회원가입 성공!!!</b><br>");
		out.println("<a href='login.jsp'>로그인</a>");
	}else{
		out.print("<b>회원가입 실패!!!</b><br>");
		out.println("<a href='register.jsp'>가입 재시도</a>");		
	}
%>
</body>
</html>