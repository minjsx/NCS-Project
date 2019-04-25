<%@page import="ad.model.Promotion"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<Promotion> sList = (List<Promotion>) request.getAttribute("statusCheck");

	DecimalFormat priceConverter = new DecimalFormat("###,###");
	//priceConverter.format("data");
		
	String ing = "진행중";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/ad_promotion_management.css">
</head>
<body>
	<div class="promotion-wrapper">
		
		<h1 class="promotion-title">프로모션 관리</h1>
		<p class="promotion-title-desc">프로모션을 이곳에서 확인할 수 있습니다.</p><pm/><pm/><pm/>
		<table class="promotion-table">
			<tr>
				<td>프로모션 등록일</td>
				<td>카테고리</td>
				<td>프로모션 명</td>
				<td>프로모션 단가</td>
				<td>자격요건</td>
				<td>희망제작기간</td>
				<td>프로모션 상태</td>
			</tr>
			<% if( sList.isEmpty() ) { %>
		<tr><td colspan="5"> 등록된 프로모션 지원현황이 없습니다. </td></tr>
		<% } else { %>
			<%
				for (int i = 0; i < sList.size(); i++) {
			%>
			<tr>
				<td><%=sList.get(i).getP_register()%></td>
				<td><%=sList.get(i).getPc_name()%></td>
				<% if(ing.equals(sList.get(i).getSname())) { %>
				<td><a href="<%=request.getContextPath()%>/MainControl?cmd=proceeding-page&p_no=<%=sList.get(i).getP_no()%>" style="text-decoration: none; color:rgb(26, 188, 156); "><%=sList.get(i).getP_name()%>
				</a></td>
				<% }else{ %>
				<td><a><%=sList.get(i).getP_name()%></a></td>
				<% } %>
				<td><%=sList.get(i).getP_price()%>원</td>
				<td><%=sList.get(i).getP_qualification()%></td>
				<td><%=sList.get(i).getP_period()%>일</td>
				<td><%=sList.get(i).getSname()%></td>		
			</tr>		
			 	<%}
	}%>
		</table>
	</div>
</body>
</html>