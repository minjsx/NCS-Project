<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="ad.model.Creator"%>
<%@page import="ad.model.Advertiser"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%
	if (session.getAttribute("user") == null) {
		response.sendRedirect(request.getContextPath() + "/MainControl?cmd=main");
	} else if (session.getAttribute("user") instanceof Advertiser) {
		response.sendRedirect(request.getContextPath() + "/MainControl?cmd=admain");
	} else if (session.getAttribute("user") instanceof Creator) {
		response.sendRedirect(request.getContextPath() + "/MainControl?cmd=crmain");
	}
%>
</head>
<body>
</body>
</html>






