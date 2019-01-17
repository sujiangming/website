<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	request.setAttribute("ContextPath", path);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" name="renderer"
	content="text/html; charset=utf-8;webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>GNIIT后台管理系统</title>

<link rel="shortcut icon"
	href="${ContextPath}/UI-lib/dist/img/favicon.ico">
<link href="${ContextPath}/login/css/bootstrap.min.css?v=3.3.5"
	rel="stylesheet">
<link href="${ContextPath}/login/css/font-awesome.min.css?v=4.4.0"
	rel="stylesheet">
<link href="${ContextPath}/login/css/custom.css" rel="stylesheet">
<link href="${ContextPath}/login/css/animate.min.css" rel="stylesheet">
<link href="${ContextPath}/login/css/style.min.css?v=4.0.0"
	rel="stylesheet">
<!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

</head>

<body style="background: #376B6D" class="">
	<div
		style="height: 77px; line-height: 77px; background: #305A56; margin-top: 0px">
		<div
			style="text-align: center; margin-left: auto; margin-right: auto;">
			<p class="text-muted"
				style="color: #FDFBF3; font-size: 25px; font-family: Microsoft YaHei; line-height: 77px;">GNIIT后台管理系统</p>
		</div>
	</div>
	<div class="middle-box text-center loginscreen  animated fadeInDown"
		style="width: 400px; height: 320px; background: #E7E5DD; margin-top: 5%; border-radius: 8px">
		<div>
			<div style="">
				<h2
					style="color: #209E85; font-family: Microsoft YaHei; font-size: 20px; font-weight: bold;">登录</h2>
			</div>
			<div class="form-group">
				<input type="text" name="username_" id="username_"
					style="width: 299px; text-align: center; margin-right: auto; margin-left: auto; border-radius: 4px; font-family: Microsoft YaHei"
					class="form-control" placeholder="用户名/手机号">
			</div>
			<div class="form-group">
				<input type="password" name="password_" id="password_"
					style="width: 299px; text-align: center; margin-right: auto; margin-left: auto; border-radius: 4px; font-family: Microsoft YaHei"
					class="form-control" placeholder="密码">
			</div>
			<button onclick="doLogin()" id="btn_Login"
				style="width: 299px; text-align: center; margin-right: auto; margin-left: auto; border-radius: 4px; font-family: Microsoft YaHei"
				class="btn btn-primary block m-b">登录</button>
			<div style="float: right; margin-right: 50px">
				<label><input type="checkbox" name="remember_me_"
					class="i-checks"
					style="font-family: Microsoft YaHei; position: relative; top: 2px;">自动登录</label>
			</div>
			<div align="left"
				style="margin-left: 50px; margin-bottom: 5px; font-size: 12px; color: red"
				id="errorInfo"></div>

		</div>
	</div>
	<div style="margin-top: 20px">
		<p class="text-muted text-center"
			style="color: #FDFBF3; font-family: Microsoft YaHei">Copyright©2017</p>
	</div>

	<script src="${ContextPath}/login/js/jquery.min.js"
		type="text/javascript"></script>
	<script src="${ContextPath}/login/js/bootstrap.min.js?v=3.3.5"
		type="text/javascript"></script>
	<script src="${ContextPath}/login/js/icheck.min.js"
		type="text/javascript"></script>

	<script>
		window.checkForm = function() {
			var errorInfo = $("#errorInfo");
			var username = $("#username_").val();
			if (username == "" || username == null) {
				errorInfo.html("用户名不能为空！");
				$("#username_").focus();
				return false;
			}
			
			if (!(/^1(3|4|5|7|8)\d{9}$/.test(username))) {
				errorInfo.html("请输入正确的账号");
				$("#username_").focus();
				return false;
			}
			
			var password = $("#password_").val();
			if (password == "" || password == null) {
				errorInfo.html("密码不能为空！");
				$("#password_").focus();
				return false;
			}

		};
		function doLogin() {
			var data = {
				account : $("#username_").val(),
				pwd : $("#password_").val()
			};

			$.ajax({
				type : "POST",
				url : getRootPath() + "login",
				data : data,
				dataType : "json",
				async : false,
				success : function(data) {
					if (data.status == "1") {
						window.location.replace("${ContextPath}/system/main/mainFrame.jsp");
					} else {
						$("#errorInfo").html(data.message);
					}
				}
			});
		};
		function getRootPath() {
			//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
			var curWwwPath = window.document.location.href;
			//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
			var pathName = window.document.location.pathname;
			var pos = curWwwPath.indexOf(pathName);
			//获取主机地址，如： http://localhost:8083
			var localhostPaht = curWwwPath.substring(0, pos);
			//获取带"/"的项目名，如：/uimcardprj
			var projectName = pathName.substring(0, pathName.substr(1).indexOf(
					'/') + 2);
			return (localhostPaht + projectName);
		};

		//登陆按钮绑定回车键
		$(document).keydown(function(event) {
			if (event.keyCode == 13) {
				$("#btn_Login").click();
			}
		});
	</script>
</body>
</html>