<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>애드브릿지 ADDBRIDGE - 광고주와 크리에이터의 연결다리</title>
<link href="<%=request.getContextPath()%>/css/reset.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/css/nolog_main_frame.css" rel="stylesheet" type="text/css"/>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/icon/mini-icon.png">
<!-- slider event -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/slider.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/mainslider.js"></script>
<!-- slider event -->
</head>
<body>
	<div class="container">
		<div class="header">
		<jsp:include page="nolog_main_topbar.jsp"/>
		</div>
		<div class="content">
			<div class="left-content"></div>
			<div class="center-content">
				 <jsp:include page="nolog_main_slider.jsp"/>
				 <jsp:include page="nolog_main_content.jsp"/>
			</div>
			<div class="right-content"></div>
		</div>		
		<div class="footer">
		 	<jsp:include page="main_footer.jsp"/>
		</div>
	</div>
</body>
</html>