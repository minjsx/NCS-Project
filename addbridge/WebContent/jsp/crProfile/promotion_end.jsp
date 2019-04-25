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
   <div><h1 style="font-weight:bold; font-size:36px;" >종료된 프로모션</h1></div><br/><br/>
   <p>종료된 프로모션을 이곳에서 확인할 수 있습니다.</p><br/><br/>
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

<%--
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="board.model.*, board.service.*"%>
<%@ page import="java.util.List"%>

<%
	//웹브라우저가 게시글 목록을 캐싱할 경우 새로운 글이 추가되더라도 새글이 목록에 안 보일 수 있기 때문에 설정
	response.setHeader("Pragma", "No-cache"); // HTTP 1.0 version
	response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1 version
	response.setHeader("Cache-Control", "no-store"); // 일부 파이어폭스 버스 관련
	response.setDateHeader("Expires", 1L); // 현재 시간 이전으로 만료일을 지정함으로써 응답결과가 캐쉬되지 않도록 설정
%>

<%
	// Service에 getArticleList()함수를 호출하여 전체 메세지 레코드 검색 
	List<BoardRec> mList = null;
	ListArticleService lms = ListArticleService.getInstance();
	String pNum = request.getParameter("pagemain");

	int totalPageCount = lms.getTotalCount();
	int pNum2 = 0;

	if (pNum == null) {
		pNum2 = 1;
		mList = lms.getArticleList(1);
	} else {
		pNum2 = Integer.parseInt(pNum);
		mList = lms.getArticleList(pNum2);
	}

	if (pNum2 <= 0) {
		response.sendRedirect("BoardList.jsp?pagemain=1");
	} else if (pNum2 > totalPageCount) {
		response.sendRedirect("BoardList.jsp?pagemain=" + totalPageCount);
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 목록</title>
</head>
<BODY>
	<h3>게시판 목록</h3>
	<table border="1" bordercolor="darkblue">
		<tr>
			<td>글번호</td>
			<td>제 목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		<%
			if (mList.isEmpty()) {
		%>
		<tr>
			<td colspan="5">등록된 게시물이 없습니다.</td>
		</tr>
		<%
			} else {
		%>
		<%
			for (BoardRec rec : mList) {
		%>
		<tr>
			<td><%=rec.getArticleId()%></td>
			<td>
				<%
					for (int i = 0; i < rec.getLevel(); i++) {
				%> &nbsp; <%
 	}
 %> <%
 	if (rec.getLevel() != 0) {
 %> <img src="imgs/board_re.gif" /> <%
 	}
 %> <a href="BoardView.jsp?id=<%=rec.getArticleId()%>"
				title="<%=rec.getTitle()%>"> <%
 	if (rec.getTitle().length() > 8) {
 %>
					<%=rec.getTitle().substring(0, 7) + "...."%> <%
 	} else {
 %> <%=rec.getTitle()%>
					<%
						}
					%>
			</a>
			</td>
			<td><%=rec.getWriterName()%></td>
			<td><%=rec.getPostingDate()%></td>
			<td><%=rec.getReadCount()%></td>
		</tr>

		<%
			} // end else
		%>
		<%
			}
		%>
		<tr>
			<td colspan="5"><a href="BoardInputForm.jsp">글쓰기</a></td>
		</tr>
	</table>
	<!-- 페이지 번호 출력 -->
	<a href="BoardList.jsp?pagemain=<%=1%>">◀◀</a>
	<a href="BoardList.jsp?pagemain=<%=pNum2 - 1%>">◀</a>
	<%
		for (int i = 1; i <= totalPageCount; i++) {
	%>
	<a href="BoardList.jsp?pagemain=<%=i%>">[<%=i%>]
	</a>
	<%
		}
	%>
	<a href="BoardList.jsp?pagemain=<%=pNum2 + 1%>">▶</a>
	<a href="BoardList.jsp?pagemain=<%=totalPageCount%>">▶▶</a>

</BODY>
</HTML>


--%>