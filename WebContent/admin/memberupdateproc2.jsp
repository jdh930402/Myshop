<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="memberBean" class="shop.member.MemberBean"/>
<jsp:setProperty property="*" name="memberBean"/>
<jsp:useBean id="memberMgr" class="shop.member.MemberMgr"/>
<%
	boolean b = memberMgr.memberUpdate(memberBean.getId(), memberBean);
	if(b){ %>
		<script>
			alert('수정 성공');
			location.href = "membermanager.jsp";
		</script>		
	<%	 } else{ %>
			<script>
			alert('수정 실패\n 관리자에게 문의 바람');
			history.back();
			</script>
	<%} %>
