<%@page import="shop.member.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="shop.member.MemberMgr"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
** 관리자 - 전체회원관리 **
<%@include file="admin_top.jsp"%>
<table style="width: 90%">
	<tr style="background-color: cyan;">
		<th>아이디</th>
		<th>회원명</th>
		<th>비밀번호</th>
		<th>이메일</th>
		<th>전화</th>
		<th>수정</th>
	</tr>
	<%	
		ArrayList<MemberDto> list = memberMgr.getMemberAll();
		for(MemberDto dto : list){
	%>
	<tr>
		<td><%=dto.getId()%></td> 
		<td><%=dto.getPasswd()%></td> 
		<td><%=dto.getName()%></td> 
		<td><%=dto.getEmail()%></td> 
		<td><%=dto.getPhone()%></td> 	
		<td><a href="javascript:memUpdate('<%=dto.getId()%>')">수정하기</a></td>
	</tr>	
	<%} %>
</table>
<%@include file="admin_bottom.jsp"%>

<form action="memberUpdate2.jsp" name="updateForm" method="post">
<input type="hidden" name="id">
</form>
</body>
</html>