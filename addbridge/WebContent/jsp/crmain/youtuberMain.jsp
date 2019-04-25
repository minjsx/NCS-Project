<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>애드브릿지 ADDBRIDGE - 광고주와 크리에이터의 연결다리</title>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/icon/mini-icon.png">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link href="<%=request.getContextPath()%>/css/reset.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/css/flex.css" rel="stylesheet" type="text/css"/>

</head>
<body>
	<div class="container">
		<div class="header">
		<jsp:include page="youtuberMainTopbar.jsp"/>
		</div>
		<div class="content">
			<div class="left-content"></div>
			<div class="center-content">
				 <div style="margin-top : 60px;"></div>
				 <jsp:include page="youtuberMainSlider.jsp"/>
				 <jsp:include page="cmain.jsp"/>
			</div>
			<div class="right-content"></div>
		</div>		
		<div class="footer">
		 <jsp:include page="footer.jsp"/>
		</div>
	</div>
</body>
</html>