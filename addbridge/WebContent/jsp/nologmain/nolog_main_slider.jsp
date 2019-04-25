<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/nolog_main_slider.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="slide-master">
		<div class="callbacks_container">
			<ul class="rslides" id="noslider">
				<li>
					<div class="slider-wrap">
						<div class="slider-img">
							<img
								src="<%=request.getContextPath()%>/images/nolog_main_img/slideimg/main_1.jpg"
								alt="">
						</div>
						<div class="slider-top-text">
							브랜드의 스토리<span style="font-weight: normal;">와<br /> <br /></span>
							크리에이터<span style="font-weight: normal;">를 연결합니다.</span><br />
						</div>
						<div class="slider-btn">
							<a href="<%=request.getContextPath()%>/MainControl?cmd=login">마케팅 시작하기</a>
						</div>
						<div class="slider-bot-text">
							지금 <span style="color: rgb(46, 204, 113);">애드브릿지</span>를 사용해보세요.
						</div>
					</div>
				</li>
				<li>
					<div class="slider-img">
						<img
							src="<%=request.getContextPath()%>/images/nolog_main_img/slideimg/main_2.jpg"
							alt="">
					</div>
					<div class="slider-top-text">
						브랜드의 스토리<span style="font-weight: normal;">와<br /> <br /></span>
						크리에이터<span style="font-weight: normal;">를 연결합니다.</span><br />
					</div>
					<div class="slider-btn"><a href="<%=request.getContextPath()%>/MainControl?cmd=login">마케팅 시작하기</a></div>
					<div class="slider-bot-text">
						지금 <span style="color: rgb(46, 204, 113);">애드브릿지</span>를 사용해보세요.
					</div>
				</li>
				<li>
					<div class="slider-img">
						<img
							src="<%=request.getContextPath()%>/images/nolog_main_img/slideimg/main_3.jpg"
							alt="">
					</div>
					<div class="slider-top-text">
						브랜드의 스토리<span style="font-weight: normal;">와<br /> <br /></span>
						크리에이터<span style="font-weight: normal;">를 연결합니다.</span><br />
					</div>
					<div class="slider-btn"><a href="<%=request.getContextPath()%>/MainControl?cmd=login">마케팅 시작하기</a></div>
					<div class="slider-bot-text">
						지금 <span style="color: rgb(46, 204, 113);">애드브릿지</span>를 사용해보세요.
					</div>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>