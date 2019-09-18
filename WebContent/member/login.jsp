<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("idKey");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="../js/script.js"></script>
<script type="text/javascript">

window.onload = function(){
	document.getElementById("btnLogin").addEventListener("click", funcLogin, false);	
	document.getElementById("btnNewMember").addEventListener("click", funcNew, false);	
}

function funcLogin(){
	if(loginForm.id.value === ""){
		alert("아이디 입력");
		loginForm.id.focus();
	}else if(loginForm.passwd.value === ""){
		alert("비밀번호 입력");
		loginForm.passwd.focus();
	}
	loginForm.action = "loginproc.jsp";
	loginForm.method="post"
	loginForm.submit();
	
}

function funcNew(){
	location.href = "register.jsp"; // redirect방식 => 서버에 전송이 안되고 클라이언트 단계에서 바로 전송된다.
}

</script>
</head>
<body>
<% if(id == null){ %>
	<form name="loginForm">
		<table border="1">
  			<tr>
  				<td colspan="2" style="text-align: center;">* 로그인 *</td>
  			</tr>
  			<tr>
  				<td>아이디</td>
  				<td><input type="text" name="id"></td>
  			</tr>
  			<tr>
  				<td>비밀번호</td>
  				<td><input type="text" name="passwd"></td>
  			</tr>
  			<tr>
  				<td colspan="2" style="text-align: center;"> 
  					<input type="button" value= "로그인" id="btnLogin">
  					<input type="button" value= "회원가입" id="btnNewMember">
  				</td>
  			</tr>
		</table>
	</form>
<% } %>
</body>
</html>