var userDept = "";
$(function() {
	initLayout();
	initMenu();
	initEvents();
	caidan();
});

/* 不同用户登录展示不同菜单 */
function caidan() {
	var news = $("#news");
	var school = $("#school");
	var teacher = $("#teacher");
	var training = $("#training");
	var student = $("#student");
	var zixun = $("#zixun");
	var renyuan = $("#renyuan");
	var xitong = $("#xitong");
	if ($("#ismanager").html() == "0") {
		news.attr("style", "display:none;");
		teacher.attr("style", "display:none;");
		training.attr("style", "display:none;");
		student.attr("style", "display:none;");
		renyuan.attr("style", "display:none;");
		xitong.attr("style", "display:none;");
		school.attr("style", "display:none;");
	}
}

/** ** 初始化页面布局 *** */
function initLayout() {
	$("#sidebar").css("height", $(window).height() - 50 + "px");
	$("#content_iframe").css("height", $(window).height() - 90 + "px");
	$("#tab-content").css("height", $(window).height() - 90 + "px");
}

var tempObj = null;
/** ** 初始化菜单 *** */
function initMenu() {
	$(".menu-link")
			.each(
					function(i, n) {
						if ($(this).attr("url") != "#") {
							$(this)
									.click(
											function() {
												$("#content_iframe").attr(
														"src",
														getRootPath()
																+ $(this).attr(
																		"url"));
												window.scrollTo(0, 0);
												if (tempObj != null) {
													tempObj.parent()
															.removeClass(
																	"active");
													tempObj
															.find("i")
															.removeClass(
																	"fa fa-circle")
															.addClass(
																	"fa fa-circle-o");
												}
												$(this).parent().addClass(
														"active");
												tempObj = $(this);
												$(this).find("i").removeClass(
														"fa fa-circle-o")
														.addClass(
																"fa fa-circle");
												$("#pagetitle").text(
														$(this).attr("name"));
												var navstr = $(this)
														.attr("nav");
												var navs = navstr.split(",");
												var nav = '<li><a href="mainFrame.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>';
												for (var i = 0; i < navs.length; i++) {
													if (i == navs.length - 1) {
														nav += '<li class="active">'
																+ $(this).attr(
																		"name")
																+ '</li>';
													} else {
														nav += '<li>' + navs[i]
																+ '</li>';
													}
												}
												$("#breadcrumb").html(nav);
											});
						}
						;
					});
}

/** *初始化点击事件** */
function initEvents() {
	// 退出
	$("#exit").click(function() {
		layer.confirm("您确定要退出吗?", {
			skin : 'layui-layer-molv',
			btn : [ '确定', '取消' ]
		}, function() {
			// window.location.href = getRootPath()+"logout";
			// 退出系统
			logout();
		}, function() {
			return;
		});
	});

	$(window).resize(function() {
		initLayout();
	});
}

function logout() {
	var url = getRootPath() + "login/login.jsp";
	location.replace(url);
	event.returnValue = false;
}

// 获取项目根路径
function getRootPath() {
	// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址，如： http://localhost:8083
	var localhostPaht = curWwwPath.substring(0, pos);
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 2);
	return (localhostPaht + projectName);
};

// 修改密码
function changePwd() {
	$('#myModal1').modal();
}
function clearForm() {
	$("#newPassword").val("");
	$("#renewPassword").val("");
}

/**
 * 保存密码操作
 */
function openSavePwdButton() {
	var newPassword = $("#newPassword").val();
	if (null == newPassword || "" == newPassword) {
		layer.msg('请输入新密码', {
			time : 2000
		});
		return;
	}
	var renewPassword = $("#renewPassword").val();
	if (null == renewPassword || "" == renewPassword) {
		layer.msg('请确认下新密码', {
			time : 2000
		});
		return;
	}

	if (newPassword != renewPassword) {
		layer.msg('两次密码不一致', {
			time : 2000
		});
		return;
	}
	var url = getRootPath() + "user/updatePwdByUser";
	$.post(url, {
		mobile : $("#newPassword").attr("flag"),
		newPassword : newPassword
	}, function(data) {
		if (data.status == 1) {
			layer.msg('修改成功！', {
				time : 2000
			}, function() {
				$("#changePwdModal").modal('hide');
				// window.location.href = getRootPath() + "logout"; // 退出系统
				logout();
			});
		} else {
			layer.msg('修改失败！', {
				time : 2000
			});
		}
	});
}
