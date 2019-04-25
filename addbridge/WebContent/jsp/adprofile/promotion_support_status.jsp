<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="ad.model.ProStatus"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<ProStatus> sList = (List<ProStatus>) request.getAttribute("statusCheck");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/reset.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/font.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/ad_promotion_status.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/message.js"></script>
<script type="text/javascript">
 	function supportCancel(index,cno,pno) {
 		var default_form = document.forms[index];	
		default_form.action = "<%=request.getContextPath()%>/MainControl?cmd=pro-cancel&c_no=" + cno + "&p_no=" + pno;
		default_form.method = "post";
		default_form.submit();
	} 
	
</script>
</head>
<body>
	<form method="post" action="<%=request.getContextPath()%>/MainControl?cmd=pro-accept">
		<div class="promotion-wrapper">
			<h1 class="promotion-title">프로모션 지원현황</h1>
			<p class="promotion-title-desc">프로모션 지원현황을 확인할 수 있습니다.</p>
			<%
				for (int i = 0; i < sList.size(); i++) {
			%>
				<input type="hidden" name="c_no" value="<%=sList.get(i).getC_no()%>" />
				<input type="hidden" name="p_no" value="<%=sList.get(i).getP_no()%>" />
			<%
				}
			%>
			<table class="promotion-table">
				<tr>
					<td>지원자명</td>
					<td>카테고리</td>
					<td>지원프로모션</td>
					<td>구독자 수</td>
					<td>자격요건</td>
					<td>수락여부</td>
				</tr>
				<%
					for (int i = 0; i < sList.size(); i++) {
				%>
				<tr>
					<td><%=sList.get(i).getC_name()%></td>
					<td><%=sList.get(i).getCg_name()%></td>
					<td><a class="promotion-name" href="#"> <%=sList.get(i).getP_name()%></a></td>
					<td><%=sList.get(i).getC_count()%></td>
					<td><%=sList.get(i).getP_qualification()%></td>
					<td><input type="submit" class="agree_btn promotion-sup-button" value="수락"/> 
					<input type="button" class="disagree_btn promotion-sup-button" value="취소" onclick="supportCancel(0,<%=sList.get(i).getC_no()%>,<%=sList.get(i).getP_no()%>)" /></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</form>
</body>

</html>