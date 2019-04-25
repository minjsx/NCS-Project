<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="ad.model.*"%>
<%@ page import="java.util.List"%>
<%
	/* int check = 0; */
	String getInfoReq = "email";
	String getInfoMem = "바보";

	String emailId = "";
	List<Member> idList = (List<Member>) request.getAttribute("idFind");
	if (idList != null) {
		for (Member sign : idList) {
			getInfoMem = sign.getEmail();
		}
	}

	List<Member> pwList = (List<Member>) request.getAttribute("passwordFind");
	if (pwList != null) {
		for (Member sign : pwList) {
			getInfoReq = "비밀번호";
			getInfoMem = sign.getPassword();
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
<title></title>
<link rel="StyleSheet"
	href="<%=request.getContextPath()%>/css/register.css" type="text/css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"></link>
<script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
<script
	src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script type="text/javascript">
	function formClose(check) {
		window.close(this);
		/* check = 1;
		if(check == 1) {
			window.open("jsp/signup/registerflex.jsp", "",'');
		} */
	}
</script>
</head>
<body>
	<div class="find-form-wrapper">
		<div class="show-user-forms">
			<form id="idFind">
				<div class="input-field">
					<div class="show-user-title">
						<b>알림</b>
					</div>
					<div class="show-user-info">
						<%
							if (idList != null || pwList != null) {
						%>
						<b>회원님의 <%=getInfoReq%>은(는) <%=getInfoMem%>입니다.
						</b>
						<%
							} else {
						%>
						<b>이메일 또는 비밀번호를 찾을 수 없습니다. </b>
						<%
							}
						%>
						<div class="find-forms">
							<input type="button" value="확인" class="button"
								onclick="formClose()"></input>
								<%-- <%=check%> --%>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>