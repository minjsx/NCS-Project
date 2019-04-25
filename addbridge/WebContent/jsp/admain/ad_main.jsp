<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="ad.model.Creator"%>
<%@page import="ad.model.Advertiser"%>
<%
	Object user = session.getAttribute("user");
	if (user instanceof Advertiser) {
		Advertiser advertiser = (Advertiser) user;
	} else if (user instanceof Creator) {

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
<title>애드브릿지 ADDBRIDGE - 광고주와 크리에이터의 연결다리</title>
<link href="<%=request.getContextPath()%>/css/reset.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/admain_frame.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" />
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/images/icon/mini-icon.png">
<!-- slider event start -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/slider.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/mainslider.js"></script>
<!-- slider event end -->
</head>
<body>
	<div class="container">
		<div class="header">
			<jsp:include page="ad_main_topbar.jsp" />
		</div>
		<div class="content">
			<div class="left-content"></div>
			<div class="center-content">
				<jsp:include page="ad_main_slider.jsp" />
				<jsp:include page="ad_main_content.jsp" />
			</div>
			<div class="right-content"></div>
		</div>
		<div class="footer">
			<jsp:include page="main_footer.jsp" />
		</div>
	</div>
</body>
</html>