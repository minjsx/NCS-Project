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
<link rel="stylesheet" href="<%=ctx %>/css/font.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

</head>
<body>
<br/>
   <br/><br/><br/>
   <div><h1 style="font-weight:bold; font-size:36px;" >관심 유튜버</h1></div><br/><br/>
   <p>관심 유튜버을 이곳에서 확인할 수 있습니다.</p><br/><br/>
  
   <textarea style="width:100%;height:800px;text-align: center">관심 유튜버이 아직 없어요...</textarea>

</body>
</html>