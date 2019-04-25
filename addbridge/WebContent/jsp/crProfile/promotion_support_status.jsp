<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ad.model.*"%>
<%@ page import="java.util.List"%>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>

<%
	List<Promotion> sList = (List<Promotion>)request.getAttribute("status");
	int p_no = (int)request.getAttribute("pno");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" 
	href="<%=ctx%>/css/font.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/ad_promotion_management.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script type="text/javascript" src="<%=ctx %>/js/message.js"></script>
</head>
<body>
	<div class="promotion-wrapper">
   	<h1 class="promotion-title">프로모션 리포트</h1>
   	<p class="promotion-title-desc">프로모션 결과를 이곳에서 확인할 수 있습니다.</p><pm/><pm/><pm/>
	<table class="promotion-table">
	<tr>
		<td> 등록일자 </td>
		<td> 마감일자 </td>
		<td> 컨텐츠 내용 </td>
		<td> 프로모션명 </td>
		<td> 프로모션 단가 </td>
		<td> 제작기간 </td>
		<td> 상태여부 </td>
	</tr>
	
	<% if( sList.isEmpty() ) { %>
		<tr><td colspan="5"> 등록된 프로모션 지원현황이 없습니다. </td></tr>
	<% } else { %>
	<% for( Promotion pm : sList)	{ %>
		<tr  align=center>	
			<td ><%=pm.getP_register()%> </td>
			<td> <%=pm.getP_deadline()%> </td> 
			<td> <%=pm.getP_content()%> </td> 
			<td ><a href="<%=request.getContextPath()%>/MainControl?cmd=proceeding-page_cr&p_no=<%=p_no%>"><%=pm.getP_name()%></a></td>
			<td> <%=pm.getP_price()%>  </td> 
			<td> <%=pm.getP_period()%>  </td>
			<td> <%=pm.getSname()%>  </td> 
	 	<%}
	}%>
	</table>
	</div>
</body>
</html>


<%-- <% if( sList.isEmpty() ) { %>
		<tr><td colspan="5"> 등록된 프로모션 지원현황이 없습니다. </td></tr>
	<% } else { %>
	<% for( BoardRec pm : sList)	{ %>
		<tr>	
			<td> <%=pm.getArticleId() %></td>
			<td>
				<% for(int i=0; i<pm.getLevel(); i++){ %>
					&nbsp;
				<%} %>
				<% if(pm.getLevel() != 0){ %>
					<img src="imgs/board_re.gif"/>
				<%} %>
			<a href="BoardView.jsp?id=<%=pm.getArticleId()%>" title="<%=pm.getTitle()%>">
			<%if(pm.getTitle().length()>8){ %>
			<%String str=pm.getTitle().substring(0,7).concat("...");%>
			<%=str %>
			<%} else{%>
			<%=pm.getTitle()%>
			<%} %>
			</a>
			</td> 
			<td> <%=pm.getWriterName()%>  </td> 
			<td> <%=pm.getPostingDate()%>  </td>
			<td> <%=pm.getReadCount()%>  </td> --%>
	<%-- <%} 
	</table>
		<a href="BoardList.jsp?page=1">◀◀ |</a>
		<a href="BoardList.jsp?page=<%=pNum-1%>">◀</a>
		<!-- 페이지 번호 출력. -->
		<% 
			for(int i=1; i<=totalPageCount; i++)
			{ %>
				<a href="BoardList.jsp?page=<%=i%>">[<%=i%>]</a>	
		<%  } %>
		<a href="BoardList.jsp?page=<%=pNum+1%>">▶</a>
		<a href="BoardList.jsp?page=<%=totalPageCount%>">|▶▶</a>	
	<% }// end if-else %>
	--%>