<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" />
<link href="<%=request.getContextPath()%>/css/nolog_topbar.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="nolog-top-bar">
		<div class="nolog-top-bar-left">
			<div class="title">
				<a href="<%=request.getContextPath()%>/MainControl?cmd=index" style="color: white; text-decoration: none;"><p>ADDBRIDGE</p></a>
			</div>
		</div>
		<div class="nolog-top-bar-right">
			<div class="sign-up">
				<a href="<%=request.getContextPath()%>/MainControl?cmd=signup"
					style="color: white; text-decoration: none;"><i
					class="fas fa-home icon"></i>
					<p>회원가입</p></a>
			</div>
			<div class="login">
				<a href="<%=request.getContextPath()%>/MainControl?cmd=login"
					style="color: white; text-decoration: none;"><i
					class="fas fa-unlock-alt icon"></i>
					<p>로그인</p></a>
			</div>
		</div>
	</div>
</body>
</html>

