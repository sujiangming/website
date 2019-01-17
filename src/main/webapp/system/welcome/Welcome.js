$(function(){
	$(".content").css("height",$(window).height()+"px");
	$.ajax({
        type: "post",
        url: getRootPath()+"mainframe/initWelcomeTopData",
        dataType: "json",
		async : false,
        success: function(data)
        {
        	if(data.status=="1"){
        		$("#lqRate").html(data.data.lqRate);
        		$("#reportNum").html(data.data.reportNum);
        		$("#orderAmount").html(data.data.orderAmount);
        		$("#userNum").html(data.data.userNum);
        	}
        }
    });
});