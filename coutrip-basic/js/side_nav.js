$(function(){
  $(".size").on('click', function(){
  $(this).toggleClass('focus').siblings().removeClass('focus');
  });

  /*scroll event*/
  $(window).scroll(function(){
    if ($(window).scrollTop() >= $('.card-container').offset().top){
      $('.category-nav').css('position', 'fixed');
    }else if($(window).scrollTop() <= $('.card-container').offset().top){
      $('.category-nav').css('position', 'absolute');
    }
  });
});

function openChildCategory(catid){
  $(".category-bold").removeClass("category-bold");
  $(".category-children").hide();
  $("#catparenta_"+catid).addClass("category-bold");
  $("#catchild_"+catid).show();
}
function closeChildCategory(catid){
  $(".category-children").hide();
  $(".category-bold").removeClass("category-bold");
}
