<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.removeAttribute("idKey");// session.invalidate() => 모든 attribute를 초기화, session.removeAttribute(name) => name의 attribute를 초기화 
%>
<script>
alert('로그아웃 성공');
location.href = "../guest/guest_index.jsp"; 
</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


</body>
</html>