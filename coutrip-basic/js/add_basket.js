/* card에 하트버튼을 누르면 아이콘 변경 이벤트*/
$(function(){
    $('.basket-heart-icon').click(function(){
      if($(this).hasClass('fa-heart-o')){
        $(this).removeClass('fa-heart-o');
        $(this).addClass('fa-heart');
      }
      else{
        $(this).removeClass('fa-heart');
        $(this).addClass('fa-heart-o');
      }
    });
});
