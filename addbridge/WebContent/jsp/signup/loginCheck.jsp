<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="ad.model.Member"%>
<%@page import="ad.model.Creator"%>
<%@page import="ad.model.Advertiser"%>
<%
	String email = "";
	String password = "";
	Member mem = (Member) request.getAttribute("checkResult");
	if (mem == null) {
		out.println("<script> alert('로그인에 실패하였습니다.'); document.location.href = '" + request.getContextPath() + "/MainControl?cmd=login'; </script>");
	} else if (mem instanceof Advertiser) {
		out.println("<script> alert('로그인에 성공하였습니다.'); document.location.href = '" + request.getContextPath() + "/MainControl?cmd=admain'; </script>");
		email = request.getParameter("email");
		password = request.getParameter("password");
		
		// 세션 생성 및 '20분' 시간 부여
		session.setAttribute("user", mem);
		session.setMaxInactiveInterval(60 * 20);
	} else if (mem instanceof Creator) {
		out.println("<script> alert('로그인에 성공하였습니다.'); document.location.href = '" + request.getContextPath() + "/MainControl?cmd=crmain'; </script>");
		email = request.getParameter("email");
		password = request.getParameter("password");
		
		// 세션 생성 및 '20분' 시간 부여
		session.setAttribute("user", mem);
		session.setMaxInactiveInterval(60 * 20);
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>