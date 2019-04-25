<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>애드브릿지 ADDBRIDGE - 광고주와 크리에이터의 연결다리</title>
<link href="<%=request.getContextPath()%>/css/reset.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/pro_flex_register.css" rel="stylesheet" type="text/css"/>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/icon/mini-icon.png">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
</head>
<body>
	<div class="container">
		<div class="header">
			<jsp:include page="ad_topbar_profile.jsp" />
		</div>
		<div class="content">
			<div class="left-content"></div>
			<div class="center-content">
				<jsp:include page="ad_profileForm.jsp" />
			</div>
			<div class="right-content"></div>
		</div>
		<div class="footer">
			<jsp:include page="footer.jsp" />
		</div>
	</div>
</body>
</html>