<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id= "memberMgr" class="shop.member.MemberMgr" scope = "page"/>
<!-- MemberMgr memberMgr = new MemberMgr()과 같이 객체를 생성하는 과정과 같다. scope는 default가 클래스로 생성됨. -->
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	boolean b = memberMgr.chkId(id);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 검사</title>

<link href="../css/style.css" rel="stylesheet" type="text/css">
<script src="../js/script.js"></script>
<script type="text/javascript">

</script>

</head>
<body>
<strong><%=id%></strong>
<% if(b){ %>
는(은) 이미 사용중인 id입니다.<p/>
<a href="#" onclick="opener.document.regForm.id.focus(); window.close();">닫기</a>
<%}else{%>
는(은) 사용 가능합니다<p/>
<a href="#" onclick="opener.document.regForm.passwd.focus(); window.close();">닫기</a>
<%} %>


</body>
</html>