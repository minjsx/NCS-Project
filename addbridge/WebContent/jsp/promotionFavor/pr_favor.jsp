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
<link href="<%=ctx%>/css/reset.css" rel="stylesheet" type="text/css"/>
<link href="<%=ctx%>/css/favor_flex.css" rel="stylesheet" type="text/css"/>
</head>
<body>
   <div class="container">
      <div class="header">
      <jsp:include page="topbar_profile.jsp"/>      
      </div>
      <div class="content">
         <div class="left-content"></div>
         <div class="center-content">
             <jsp:include page="favor_promotion.jsp"/>
         </div>
         <div class="right-content"></div>
      </div>      
      <div class="footer">
         <jsp:include page="footer.jsp"/>
      </div>
   </div>
</body>
</html>