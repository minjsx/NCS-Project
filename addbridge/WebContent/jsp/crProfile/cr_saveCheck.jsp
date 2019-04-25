<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
    System.out.println(ctx);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=ctx %>/css/reset.css" rel="stylesheet" type="text/css"/>
<link href="<%=ctx %>/css/cr_flex_profile.css" rel="stylesheet" type="text/css"/>
</head>
<body>
  <br/>
   <br/><br/><br/>
   <div><h1 style="font-weight:bold; font-size:36px; margin-left: 600px;
    margin-top: 300px; margin-bottom: 500px;" > 저장/수정이 완료 되었습니다. </h1></div><br/><br/>
  
</body>
</html>