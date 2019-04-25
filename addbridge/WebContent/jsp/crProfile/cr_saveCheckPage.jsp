<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="ad.model.Member"%>
<%@page import="ad.model.Creator"%>
<%@page import="ad.model.Advertiser"%>
<%
	String ctx = request.getContextPath(); //콘텍스트명 얻어오기.
%>

<%
	Object user = session.getAttribute("user");
	if (user instanceof Advertiser) {
		
	} else if (user instanceof Creator) {
		Creator creator = (Creator) user;
	} else if (user == null) {
		//ContextPath 조심히 사용하자...
		out.print("<script> alert('세션이 만료되었습니다. 다시 로그인해주세요.'); document.location.href = '" + request.getContextPath() + "/MainControl?cmd=main' </script>");

	} else {
		//TODO : (잘못된 경로로 접속하셨습니다.) 알림창을 띄우고, NOLOGIN 메인페이지로 
		out.println("<script> alert('잘못된 경로로 접속하셨습니다.'); document.location.href = '" + request.getContextPath() + "/MainControl?cmd=main' </script>");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=ctx%>/css/reset.css" rel="stylesheet" type="text/css" />
<link href="<%=ctx%>/css/cr_flex_profile.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div class="container">
		<div class="header">
			<jsp:include page="topbar_profile.jsp" />
			
		</div>
		<div class="content">
			<div class="l
         eft-content"></div>
			<div class="center-content">
				<jsp:include page="cr_saveCheck.jsp" />
			</div>
			<div class="right-content"></div>
		</div>
		<div class="footer">
			<jsp:include page="footer.jsp" />
		</div>
	</div>
</body>
</html>
