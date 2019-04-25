<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="ad.model.Creator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Creator cr = request.getAttribute("creator") instanceof Creator ? (Creator)request.getAttribute("creator") : null;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link href="<%= request.getContextPath() %>/css/slider.css" rel="stylesheet" type="text/css"/>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/slider.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/mainslider.js"></script>
</head>
<body>

<div class="slide-master">		
    <div class="callbacks_container">
      <ul class="rslides" id="slider">
        <li>
          <div class="slider-wrap">
	          <div class="slider-img"><img src="<%=cr.getImg_src()%>" alt=""></div>
          </div>
        </li>
       <%--  <li>
          <div class="slider-img"><img src="<%= request.getContextPath() %>/images/대도서관2.jpg" alt=""></div>
            
        </li>
        <li>
         <div class="slider-img"><img src="<%= request.getContextPath() %>/images/대도서관3.jpg" alt=""></div>
          
        </li>
        <li>
         <div class="slider-img"><img src="<%= request.getContextPath() %>/images/대도서관4.jpg" alt=""></div>
         
        </li> --%>
      </ul>
	</div>
</div>	
</body>
</html>