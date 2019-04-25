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
<link rel="stylesheet" href="<%=ctx %>/youtube/css/font.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

</head>
<body>
<br/>
   <br/><br/><br/>
   <div><h1 style="font-weight:bold; font-size:36px;" >관심 프로모션</h1></div><br/><br/>
   <p>관심 프로모션을 이곳에서 확인할 수 있습니다.</p><br/><br/>
   <table  border="1" style="width:1200px; height:300px" >
	<tr   bgcolor=lightgray align=center>
		<td style="width:200px; height:30px; vertical-align:middle;"> 시작일자 </td>
      <td style="width:200px; height:30px; vertical-align:middle;"> 종료일자 </td>
      <td style="width:150px; height:30px; vertical-align:middle;"> 파트너명 </td>
      <td style="width:150px; height:30px; vertical-align:middle;"> 카테고리 </td>
      <td style="width:400px; height:30px; vertical-align:middle;"> 프로모션 명 </td>
      <td style="width:150px; height:30px; vertical-align:middle;"> 단가 </td>
	</tr>
	
	<tr align=center>
		<td> 1 </td> 
		<td> 2 </td>
		<td> 3 </td>
		<td> 4 </td>
		<td> 5 </td>
		<td> 6 </td>  
	</tr>
	</table>
</body>
</html>