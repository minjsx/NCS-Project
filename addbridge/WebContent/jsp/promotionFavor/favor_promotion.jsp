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
<title>���� ���θ��</title>
	<link href="<%= request.getContextPath() %>/css/favor_promotion.css" rel="stylesheet" type="text/css"/>
	<script src="http://code.jquery.com/jquery-1.12.4.js"></script>  <!-- ���� �� �̰ɷ� ������� -->
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/favor_promotion.js"></script>
	
</head>
<script type="text/javascript">
//p_no ���ߵ�
function imgClick(index, pno) {
	var default_form = document.forms[index];

	default_form.action = "<%=request.getContextPath()%>/MainControl?cmd=list-page2&p_no=" + pno;
	default_form.method = "post";
	default_form.submit();
}
</script>
<body>
	<form>
	<h3>���� ���θ��</h3>
	���� ���θ���� �̰����� Ȯ���� �� �ֽ��ϴ�.	
	<hr/><br/>
		<div class="div1">
			<% for(FPromotion fp : fList) { %>
				<a id="img1" class="preview" title="���θ�Ǹ� : <%=fp.getP_name() %><br/>�⺻�ܰ� : <%= fp.getP_price() %><br/>�������� : <%= fp.getP_deadline() %>">
					<img id="img1" name="img1" width="250px" height="200px" src="<%= fp.getPi_pimg_path() %>" onclick="imgClick(0,<%=fp.getP_no()%>)" />
				</a>
			<% } %>	
		</div>		
		<br/><hr/>		
		<br/>		
	</form>	
</body>
</html>