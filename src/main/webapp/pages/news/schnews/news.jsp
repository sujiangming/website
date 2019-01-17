<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.gnit.pojo.User"%>
<%
	String path = request.getContextPath();
	request.setAttribute("ContextPath", path);
	
	//获取用户信息,从session 里面
	HttpSession httpSession = request.getSession();
	User user = (User) httpSession.getAttribute("user");
	request.setAttribute("loginUser", user);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学校新闻</title>
<%@ include file="/common/taglibs.jsp"%>
<link href="${ContextPath}/UI-lib/plugins/fileinput/fileinput.min.css"
	rel="stylesheet" />
<script src="${ContextPath}/UI-lib/plugins/fileinput/fileinput.min.js"
	type="text/javascript"></script>
<!-- ueditor -->
<%-- <script type="text/javascript" charset="utf-8"
	src="${ContextPath}/ueditor/third-party/jquery-1.10.2.min.js"></script> --%>
<script type="text/javascript" charset="utf-8"
	src="${ContextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${ContextPath}/ueditor/ueditor.all.min.js">
</script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="${ContextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="${ContextPath}/pages/news/schnews/news.js"
	type="text/javascript"></script>
<style type="text/css">
.form-group-sm .form-control {
	height: 34px
}
</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">

		<!-- 按钮及查询区域，此处按钮为占位符，实际加载的为view中的按钮控件  -->
		<div
			style="width: 100%; height: 55px; background-color: #f3f3f4; position: fixed; left: 0; top: 0; padding: 0 10px; z-index: 1000">
			<div class="row">
				<div class="col-md-6" style="float: left; margin-top: 10px;">
					<button id="btn_add" class="btn btn-primary" type="button">
						<i class="fa fa-plus"></i>&nbsp;新增
					</button>
					<button id="btn_del" class="btn btn-primary" type="button">
						<i class="fa fa-minus"></i>&nbsp;删除
					</button>
				</div>
			</div>
		</div>
		<i class="fa fa-search"
			style="position: fixed; right: 20px; top: 19px; z-index: 1001; color: #e7eaec; font-size: 15px;"></i>
		<!-- 占位div -->
		<div class="" style="width: 100%; height: 45px;"></div>
		<!-- 占位div -->

		<!-- 数据表格区域  -->
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							学校新闻<small></small>
						</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="dropdown-toggle" data-toggle="dropdown"
								href="table_data_tables.html#"> <i class="fa fa-wrench"></i>
							</a> <a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content" id="tempTable">
						<div id="toolbar"></div>
						<table class="table-no-bordered" id="newsInfo">
						
						</table>
					</div>

				</div>
			</div>
		</div>

		<!-- 弹出窗口区域，触发事件后弹出  -->
		<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" style="width: 800px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalTitle">新增(编辑)新闻</h4>
					</div>
					<div class="modal-body">
						<div class="form-group form-group-sm ">
							  <label class="col-sm-2 control-label"><span style="color:red;position:relative;top:2px;">*</span>新闻标题</label>
							  <div class="col-sm-6">
									<input class="form-control validate[required]" id="newsTitle" type="text" />
							  </div>
						</div>
						<br>
						<div class="form-group form-group-sm ">
							  <label class="col-sm-2 control-label"><span style="color:red;position:relative;top:2px;">*</span>新闻内容</label>
						</div>
						<br>
						
						<div>
							<script id="editor" type="text/plain"
								style="width: 100%; height: 350px;"></script>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button id="btEmpAdd" onclick="openSaveButton()"
							class="btn btn-primary" type="button">保存</button>
					</div>
					<input class="form-control" id="newsId" name="${loginUser.userName}" type="hidden" />
				</div>
			</div>
		</div>
		<!-- 弹出窗口区域，触发事件后弹出   结束 -->
	</div>
</body>

</html>