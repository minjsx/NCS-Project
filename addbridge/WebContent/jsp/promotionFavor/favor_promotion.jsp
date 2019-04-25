<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="ad.model.*" %>
<%@ page import="java.util.List" %>    
<% 
	List<FPromotion> fList = (List <FPromotion>)request.getAttribute("favorList");	
%>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>관심 프로모션</title>
	<link href="<%= request.getContextPath() %>/css/favor_promotion.css" rel="stylesheet" type="text/css"/>
	<script src="http://code.jquery.com/jquery-1.12.4.js"></script>  <!-- 버전 꼭 이걸로 해줘야함 -->
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/favor_promotion.js"></script>
	
</head>
<script type="text/javascript">
//p_no 얻어야됨
function imgClick(index, pno) {
	var default_form = document.forms[index];

	default_form.action = "<%=request.getContextPath()%>/MainControl?cmd=list-page2&p_no=" + pno;
	default_form.method = "post";
	default_form.submit();
}
</script>
<body>
	<form>
	<h3>관심 프로모션</h3>
	관심 프로모션을 이곳에서 확인할 수 있습니다.	
	<hr/><br/>
		<div class="div1">
			<% for(FPromotion fp : fList) { %>
				<a id="img1" class="preview" title="프로모션명 : <%=fp.getP_name() %><br/>기본단가 : <%= fp.getP_price() %><br/>마감일자 : <%= fp.getP_deadline() %>">
					<img id="img1" name="img1" width="250px" height="200px" src="<%= fp.getPi_pimg_path() %>" onclick="imgClick(0,<%=fp.getP_no()%>)" />
				</a>
			<% } %>	
		</div>		
		<br/><hr/>		
		<br/>		
	</form>	
</body>
</html>