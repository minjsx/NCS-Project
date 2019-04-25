$(document).ready(function() {
	$(".toggleMenu").css("display", "none");
	$(".top-category-nav li").hover(function() {
	 	$(this).addClass('hover');
	}, function() {
		$(this).removeClass('hover');
	});
});