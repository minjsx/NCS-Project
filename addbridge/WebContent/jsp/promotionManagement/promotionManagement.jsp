<%@page import="ad.model.Advertiser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Object user = session.getAttribute("user");
	if (user instanceof Advertiser) {
		out.println("<script> alert('프로모션이 등록되었습니다.'); document.location.href = '" + request.getContextPath()
				+ "/MainControl?cmd=admain'; </script>");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>promotionManagement</title>

</head>
<body>

</body>
</html>