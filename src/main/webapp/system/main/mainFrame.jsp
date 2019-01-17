<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.gnit.pojo.User"%>
<%
	//获取上下文路径
	String context = request.getContextPath();
	request.setAttribute("ContextPath", context);

	//获取用户信息,从session 里面
	HttpSession httpSession = request.getSession();
	User user = (User) httpSession.getAttribute("user");
	request.setAttribute("loginUser", user);
	Boolean is = user.getIsManager();
	if (null != is && is.booleanValue() == true) {
		request.setAttribute("manager", 1);
	} else {
		request.setAttribute("manager", 0);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>GNIIT后台管理系统</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="shortcut icon"
	href="${ContextPath}/UI-lib/dist/img/favicon.ico">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet"
	href="${ContextPath}/UI-lib/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link
	href="${ContextPath}/UI-lib/bootstrap/css/font-awesome.min.css?v=4.4.0"
	rel="stylesheet" />
<!-- animate -->
<link href="${ContextPath}/UI-lib/dist/css/animate.min.css"
	rel="stylesheet" />
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${ContextPath}/UI-lib/dist/css/AdminLTE.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="${ContextPath}/UI-lib/dist/css/skins/_all-skins.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="${ContextPath}/UI-lib/plugins/iCheck/flat/blue.css">
<!-- Morris chart -->
<link rel="stylesheet"
	href="${ContextPath}/UI-lib/plugins/morris/morris.css">
<!-- jvectormap -->
<link rel="stylesheet"
	href="${ContextPath}/UI-lib/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<!-- Date Picker -->
<link rel="stylesheet"
	href="${ContextPath}/UI-lib/plugins/datepicker/datepicker3.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="${ContextPath}/UI-lib/plugins/daterangepicker/daterangepicker.css">
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet"
	href="${ContextPath}/UI-lib/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<link rel="stylesheet"
	href="${ContextPath}/UI-lib/plugins/validate/css/validationEngine.jquery.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">

	<div class="wrapper">
		<header class="main-header">
			<!-- Logo -->
			<a href="#" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>G</b>NIIT</span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg"
				style="font-family: Microsoft YaHei; font-size: 20px; vertical-align: middle; text-align: left;">
					<img src="${ContextPath}/UI-lib/dist/img/logo.png"
					style="position: relative; top: -1px; left: 2px; height: 50px;" />
					<font style="font-size: 16px; font-weight: bold;">GNIIT</font>
			</span>
			</a>
			<!-- Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" style="font-size: 14px"
					data-toggle="offcanvas" role="button"> <span class="sr-only">Toggle
						navigation</span>
				</a>

				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<!-- Tasks: style can be found in dropdown.less -->
						<li class="dropdown user user-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <img
								src="${ContextPath}/UI-lib/dist/img/user2-160x160.jpg"
								class="user-image" alt="User Image"> <span
								class="hidden-xs">${loginUser.userName}</span>
						</a>
							<ul class="dropdown-menu">
								<!-- User image -->
								<li class="user-header"><img
									src="${ContextPath}/UI-lib/dist/img/user2-160x160.jpg"
									class="img-circle" alt="User Image">
									<p>${loginUser.userName}</p></li>
								<!-- Menu Body -->

								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<a onclick="changePwd()" class="btn btn-default btn-flat">修改密码</a>
									</div>
									<div class="pull-right">
										<a href="#" id="exit" class="btn btn-default btn-flat">退出</a>
									</div>
								</li>
							</ul></li>
						<!-- Control Sidebar Toggle Button -->
						<li><a href="#" data-toggle="control-sidebar"> <i
								class="fa fa-gears"></i>
						</a></li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar" style="overflow: auto;">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar" id="sidebar">
				<!-- Sidebar user panel -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="${ContextPath}/UI-lib/dist/img/user2-160x160.jpg"
							class="img-circle" alt="User Image">
					</div>
					<div class="pull-left info">
						<p style="width: 150px; text-overflow: ellipsis; overflow: hidden">${loginUser.userName}</p>
						<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
					</div>
				</div>
				<!-- sidebar menu: : style can be found in sidebar.less -->
				<ul class="sidebar-menu" id="sidebar-menu">
					<li class="treeview" id="news"><a url="#" name="新闻管理"
						nav="新闻管理" class="menu-link"> <i class="fa fa-check"></i> <span>新闻管理</span>
							<span class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a url="pages/news/schnews/news.jsp" name="学校新闻"
								nav="学校新闻,学校" class="menu-link"> <i class="fa fa-circle-o"></i>
									<span>学校新闻</span>
							</a></li>
							<li><a url="pages/news/industry/indnews.jsp" name="行业新闻"
								nav="行业新闻,行业" class="menu-link"> <i class="fa fa-circle-o"></i>
									<span>行业新闻</span>
							</a></li>
						</ul></li>

					<li class="treeview" id="school"><a url="#" name="学校管理"
						nav="学校管理" class="menu-link"> <i class="fa fa-user"></i> <span>学校管理</span>
							<span class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a url="pages/school/school.jsp" name="校园" nav="校园环境，校园"
								class="menu-link"> <i class="fa fa-circle-o"></i> <span>校园环境</span>
							</a></li>
						</ul></li>

					<li class="treeview" id="teacher"><a url="#" name="教师管理"
						nav="教师管理" class="menu-link"> <i class="fa fa-th-large"></i> <span>教师管理</span>
							<span class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a url="pages/teachers/teachers.jsp" name="教师"
								nav="教师管理,教师" class="menu-link"> <i class="fa fa-circle-o"></i>
									<span>教师队伍</span>
							</a></li>
						</ul></li>

					<li class="treeview" id="training"><a url="#" name="实训管理"
						nav="实训管理" class="menu-link"> <i class="fa fa-envelope"></i> <span>实训管理</span>
							<span class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a url="pages/training/training.jsp" name="实训"
								nav="实训管理,实训" class="menu-link"> <i class="fa fa-circle-o"></i>
									<span>实训项目</span>
							</a></li>
						</ul></li>

					<li class="treeview" id="student"><a url="#" name="学生管理"
						nav="学生管理" class="menu-link"> <i class="fa fa-map-marker"></i>
							<span>学生管理</span> <span class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a url="pages/students/students.jsp" name="学生"
								nav="学生管理，学生" class="menu-link"> <i class="fa fa-circle-o"></i>
									<span>学生信息</span>
							</a></li>
							<li><a url="pages/students/stujob.jsp" name="就业"
								nav="就业明星，就业" class="menu-link"> <i class="fa fa-circle-o"></i>
									<span>就业明星</span>
							</a></li>
						</ul></li>

					<li class="treeview" id="zixun"><a url="#" name="咨询管理"
						nav="咨询管理" class="menu-link"> <i class="fa fa-list-alt"></i> <span>咨询管理</span>
							<span class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a url="pages/consult/phonecb/consult.jsp" name="电话回拨"
								nav="电话回拨,回拨" class="menu-link"> <i class="fa fa-circle-o"></i>
									<span>电话回拨</span>
							</a></li>
							<li><a url="pages/consult/ordersign/consult.jsp" name="预约报名"
								nav="预约报名,报名" class="menu-link"> <i class="fa fa-circle-o"></i>
									<span>预约报名</span>
							</a></li>
							<li><a url="pages/consult/freelearn/consult.jsp" name="免费试听"
								nav="免费试听,试听" class="menu-link"> <i class="fa fa-circle-o"></i>
									<span>免费试听</span>
							</a></li>
							<li><a url="pages/consult/preschool/consult.jsp" name="学前测试"
								nav="学前测试,测试" class="menu-link"> <i class="fa fa-circle-o"></i>
									<span>学前测试</span>
							</a></li>
						</ul></li>

					<li class="treeview" id="renyuan"><a url="#" name="人员管理"
						nav="人员管理" class="menu-link"> <i class="fa fa-home"></i> <span>人员管理</span>
							<span class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a url="pages/inneruser/users.jsp" name="人员"
								nav="人员管理,人员" class="menu-link"> <i class="fa fa-circle-o"></i>
									<span>人员列表</span>
							</a></li>
						</ul></li>

					<li class="treeview" id="xitong" style="display: block;"><a
						url="#" name="系统管理" nav="系统管理" class="menu-link"> <i
							class="fa fa-cogs"></i> <span>系统管理</span> <span
							class="pull-right-container"><i
								class="fa fa-angle-left pull-right"></i></span>
					</a></li>

				</ul>
			</section>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper" style="font-family: Microsoft YaHei;">
			<section class="content-header" style="background: #fff;">
				<h1 id="pagetitle"
					style="font-family: Microsoft YaHei; font-size: 15px; font-weight: bold">Welcome</h1>
				<ol class="breadcrumb" id="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">welcome</li>
				</ol>
			</section>
			<iframe width="100%" height="100%" id="content_iframe"
				src="../welcome/Welcome.jsp" frameborder="0" data-id=""
				style="min-height: 100%;"> </iframe>
		</div>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Create the tabs -->
			<ul class="nav nav-tabs nav-justified control-sidebar-tabs"></ul>
			<!-- Tab panes -->
			<div class="tab-content" id="tab-content" style="overflow-y: scroll">
				<!-- Home tab content -->
				<div class="tab-pane" id="control-sidebar-home-tab"></div>
				<!-- /.tab-pane -->
				<!-- Stats tab content -->
			</div>
		</aside>
		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
		<!--修改密码-->
		<!-- 弹出窗口区域，触发事件后弹出  -->
		<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" style="width: 50%; margin-top: 200px;">
				<div class="modal-content">
					<div class="modal-header" style="background: #18a689">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalTitle" style="color: white">修改密码</h4>
					</div>
					<div class="modal-body">
						<form id="myForm1" class="form-horizontal" role="form">
							<fieldset id="customerInfo">
								<legend style="font-size: 15px; border: 0;"></legend>

								<div class="form-group form-group-sm ">
									<label class="col-sm-2 control-label"><span
										style="color: red; position: relative; top: 2px;">*</span> 新密码</label>
									<div class="col-sm-6">
										<input class="form-control validate[required]"
											id="newPassword" type="password"
											flag="${loginUser.userMobile}" />
									</div>
								</div>
								<div class="form-group form-group-sm ">
									<label class="col-sm-2 control-label"><span
										style="color: red; position: relative; top: 2px;">*</span>
										确认新密码</label>
									<div class="col-sm-6">
										<input class="form-control validate[required]"
											id="renewPassword" type="password" />
									</div>
								</div>
								<br>
							</fieldset>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button id="btEmpAdd" onclick="openSavePwdButton()"
							class="btn btn-primary" type="button">保存</button>
					</div>
					<input class="form-control" id="id" type="hidden" />
				</div>
			</div>
		</div>
		<!-- 弹出窗口区域，触发事件后弹出   结束 -->
		<!--修改密码结束-->

	</div>
	<p id="ismanager" style="display: none;">${manager}</p>
	<!-- ./wrapper -->
	<!-- jQuery 2.2.3 -->
	<script src="${ContextPath}/UI-lib/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="${ContextPath}/UI-lib/bootstrap/js/bootstrap.min.js"></script>
	<!-- 后台推送 -->
	<script src="${ContextPath}/UI-lib/plugins/toastr/content.min.js"></script>
	<script src="${ContextPath}/UI-lib/plugins/toastr/toastr.min.js"></script>
	<script src="${ContextPath}/UI-lib/plugins/layer/layer.js"></script>
	<script
		src="${ContextPath}/UI-lib/plugins/validate/js/jquery.validationEngine-zh_CN.js"></script>
	<script
		src="${ContextPath}/UI-lib/plugins/validate/js/jquery.validationEngine.js"></script>
	<script
		src="${ContextPath}/UI-lib/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<script src="${ContextPath}/system/main/mainFrame.js"></script>
	<!-- AdminLTE App -->
	<script src="${ContextPath}/UI-lib/dist/js/app.min.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<!--<script src="${ContextPath}/UI-lib/dist/js/pages/dashboard.js"></script>-->
	<!-- AdminLTE for demo purposes -->
	<script src="${ContextPath}/UI-lib/dist/js/demo.js"></script>
	<script src="${ContextPath}/UI-lib/plugins/layer/layer.js"></script>
	<script src="${ContextPath}/common/js/jquery.blockUI.js"></script>
</body>
</html>