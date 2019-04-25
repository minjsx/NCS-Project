<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>애드브릿지 ADDBRIDGE - 회원정보 찾기</title>
<link rel="StyleSheet" href="<%=request.getContextPath()%>/css/reset.css" type="text/css"/>
<link rel="StyleSheet" href="<%=request.getContextPath()%>/css/flex.css" type="text/css"/>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/icon/mini-icon.png">
</head>
<body>
	<div class="container">
		<div class="header">
			<jsp:include page="topbar.jsp" />
		</div>
		<div class="content">
			<div class="center-content">
				<jsp:include page="findForm.jsp" />
			</div>
		</div>
		<div class="footer">
			<jsp:include page="footer.jsp" />
		</div>
	</div>
</body>
</html>