<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int result = request.getAttribute("favor") == null ? 0 : (int) request.getAttribute("favor");
	int result2 = request.getAttribute("proCancel") == null ? 0 : (int) request.getAttribute("proCancel");
	if (result == -1) {
		out.print("<script> alert('이미 신청한 프로모션입니다.'); document.location.href = '"
				+ request.getContextPath() + "/MainControl?cmd=index' </script>");	
	}else if(result2 == 98) {
		out.print("<script> alert('해당 크리에이터의 지원을 취소하셨습니다.'); document.location.href = '"
				+ request.getContextPath() + "/MainControl?cmd=index' </script>");
	} 
	
	else if(result == 99) {
		out.print("<script> alert('해당 프로모션 지원에 성공하셨습니다.'); document.location.href = '"
				+ request.getContextPath() + "/MainControl?cmd=index' </script>");
	}
	else {
		out.print("<script> alert('프로모션 신청이 완료되었습니다.'); document.location.href = '"
				+ request.getContextPath() + "/MainControl?cmd=index' </script>");
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