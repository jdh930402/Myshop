<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="mBean" class="shop.member.MemberBean"/>
<jsp:setProperty property="*" name="mBean"/>
<jsp:useBean id="memberMgr" class="shop.member.MemberMgr"/>
<%
	String id = (String)session.getAttribute("idKey");
	boolean b = memberMgr.memberUpdate(id, mBean);
	if(b){ %>
		<script>
			alert('수정 성공');
			location.href = "../guest/guest_index.jsp";
		</script>		
	<% }else{ %>
		<script>
			alert('수정 실패');
			history.back();
		</script>	
	
	<% }%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>