<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link href="<%=request.getContextPath()%>/css/crmain_slider.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/slider.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/mainslider.js"></script>
</head>
<body>

<div class="slide-master">		
    <div class="callbacks_container">
      <ul class="rslides" id="slider">
        <li>
          <div class="slider-wrap">
	          <div class="slider-img"><img src="<%=request.getContextPath()%>/images/slide_1.jpg" alt=""></div>          	  
          </div>
        </li>
        <li>
          <div class="slider-img"><img src="<%=request.getContextPath()%>/images/slide_2.jpg" alt=""></div> 
        </li>
        <li>
         <div class="slider-img"><img src="<%=request.getContextPath()%>/images/slide_3.jpg" alt=""></div>     
        </li>
      </ul>
	</div>
</div>	
</body>
</html>