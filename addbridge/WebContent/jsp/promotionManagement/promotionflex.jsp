<%@page import="ad.service.FileSaveHelper"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileUploadException"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="ad.model.*" %>    
<%@ page import="java.util.List" %>

<%-- Advertiser 세션정보 얻어옴 --%>
<%!
	String title; //프로모션 제목
	String promotion; //프로모션 소개 p_cont
	String contentinfo; //제작컨텐츠 안내 p_info
	String period; //제작기간 p_period
	String s1; //자격요건
	String s2; //프로모션유형
	String s3; //카테고리
	String price; //단가 
	String pimg_name;
	String pimg_src;
	Advertiser advertiser;
%>
<%
	int adno  = 0;
	Object user = session.getAttribute("user");

	if (user instanceof Advertiser) {
		advertiser = (Advertiser) user;
		adno = advertiser.getA_no();
	} else if (user instanceof Creator) {

	} else if (user == null) {
		//ContextPath 조심히 사용하자...
		out.print("<script> alert('세션이 만료되었습니다. 다시 로그인해주세요.'); document.location.href = '" + request.getContextPath() + "/MainControl?cmd=main' </script>");

	} else {
		//TODO : (잘못된 경로로 접속하셨습니다.) 알림창을 띄우고, NOLOGIN 메인페이지로 
		out.println("<script> alert('잘못된 경로로 접속하셨습니다.'); document.location.href = '" + request.getContextPath() + "/MainControl?cmd=main' </script>");
	}
	
	
	// ------------------------------------------------------------
	
	request.setCharacterEncoding("UTF-8");
/*   	promotion = request.getParameter("promotion");
  	contentinfo =  request.getParameter("contentinfo"); */
  	
		// 앞의 화면의 Form의 enctype이 multipart/form-data 인지 확인
		boolean isMutipart = ServletFileUpload.isMultipartContent( request );
		if( !isMutipart ){ throw new FileUploadException("mutipart"); }

		// FileItem의 Factory를 설정 - 파일을 넘겨받으면 FileItem으로 지정되는데 그것을 관리하는 클래스
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 업로드요청을 처리하는 ServletFileUpload 클래스 생성
		ServletFileUpload upload = new ServletFileUpload( factory );

		// 이전 화면에서 데이타를 넘겨받는 request 객체를 파싱하여 데이타를 FileItem 클래스의 List로 넘겨받음
		List <FileItem> items = upload.parseRequest( request );

		// List에 들어있는 내용들을 하나씩 얻어오고자 Iterator로 변환
		Iterator <FileItem> iters = items.iterator();

		// 객체 생성 ***
		while( iters.hasNext())
		{
		// 넘겨받은 객체들을 하나씩 얻어오기
		FileItem item = iters.next();

		  if( item.isFormField())   // 폼의 입력 항목이라면 [예] <input type='text'>의 입력값
		  {
			  String name = item.getFieldName();    
			  // 폼의 이름을 얻어와서 그 이름에서 앞의 폼에서 지정한 이름을 찾아 그 값을 얻어온다
			  	if(name.equals("title")){ title = item.getString("UTF-8"); } //프로모션 제목
			  	else if(name.equals("promotion")){ promotion = item.getString("UTF-8"); } //프로모션 소개 p_cont
			  	else if(name.equals("contentinfo")){ contentinfo = item.getString("UTF-8"); } //제작컨텐츠 안내 p_info
			  	else if(name.equals("period")){ period = item.getString("UTF-8"); } //제작기간 p_period
			  	else if(name.equals("s1")){ s1 = item.getString("UTF-8"); } //자격요건
			  	else if(name.equals("s2")){ s2 = item.getString("UTF-8"); } //프로모션유형
			  	else if(name.equals("s3")){ s3 = item.getString("UTF-8"); } //카테고리
			  	else if(name.equals("price")){ price = item.getString("UTF-8"); } //단가

		  }else{  // 폼에서 화일입력항목이라면 [예] <input type='file'>
			 String name = item.getFieldName();
		     if( name.equals("profileImage"))      // 폼의 화일태그 중에 "upFileFirst"인가
		     {
		        String realpath = FileSaveHelper.save( "C:\\tomcatwork\\Adbridge\\WebContent\\images\\input_img", item.getInputStream(), item.getName());
		        
		        pimg_name = item.getName();
		        pimg_src = "/Adbridge/images/input_img/"+item.getName();
		     }
		  }
		}
		
		YoutuberMainDao.getInstance().InsertPromotion(title, promotion, contentinfo, period, s1, s2, s3, price, adno, pimg_name, pimg_src);
		YoutuberMainDao.getInstance().UpdateCash(adno);
		
		
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/reset.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/css/pro_flex_register.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container">
      <div class="header">
      <%-- <jsp:include page="<%=request.getContextPath()%>/jsp/adprofile/ad_topbar_profile.jsp"/> --%>   
      </div>
      <div class="content">
         <div class="left-content"></div>
         <div class="center-content">
             <jsp:include page="promotionManagement.jsp"/>
         </div>
         <div class="right-content"></div>
      </div>      
      <div class="footer">
      <%-- <jsp:include page="<%=request.getContextPath()%>/jsp/adprofile/footer.jsp"/> --%>
      </div>
   </div>
</body>
</html>