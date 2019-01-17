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
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>免费试听</title>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${ContextPath}/pages/consult/preschool/consult.js"></script>
<style type="text/css">
.form-group-sm .form-control {
	height: 34px
}
</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">

		<!-- 按钮及查询区域，此处按钮为占位符，实际加载的为view中的按钮控件  -->
		<!-- 按钮及查询区域，此处按钮为占位符，实际加载的为view中的按钮控件  -->
		<div
			style="width: 100%; height: 55px; background-color: #f3f3f4; position: fixed; left: 0; top: 0; padding: 0 10px; z-index: 1000;">
			<div class="row">
				<div class="col-md-6" style="float: left; margin-top: 10px;">
					<button id="btn_add" class="btn btn-primary" type="button"
						style="display: none;">
						<i class="fa fa-plus"></i>&nbsp;新增
					</button>
					<button id="btn_del" class="btn btn-primary" type="button">
						<i class="fa fa-minus"></i>&nbsp;删除
					</button>
				</div>
			</div>
		</div>
		<i class="fa fa-search"
			style="position: fixed; right: 15px; top: 19px; z-index: 1001; color: #e7eaec; font-size: 15px;"></i>
		<!-- 占位div -->
		<div class="" style="width: 100%; height: 45px;"></div>
		<!-- 占位div -->

		<!-- 数据表格区域  -->
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							免费试听<small></small>
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
						<table class="table-no-bordered" id="roleInfo"></table>

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
						<h4 class="modal-title" id="myModalTitle">查看信息</h4>
					</div>
					<div class="modal-body">
						<form id="myForm1" class="form-horizontal" role="form">
							<fieldset id="customerInfo">
								<legend style="font-size: 15px; border: 0;"></legend>

								<div class="form-group form-group-sm ">
									<label class="col-sm-2 control-label"><span
										style="color: red; position: relative; top: 2px;">*</span> 电话</label>
									<div class="col-sm-6">
										<input class="form-control validate[required]"
											id="phoneNumber" type="text" />
									</div>
								</div>

								<div class="form-group form-group-sm ">
									<label class="col-sm-2 control-label"><span
										style="color: red; position: relative; top: 2px;">*</span> 年龄段</label>
									<div class="col-sm-6">
										<input class="form-control validate[required]" id="ageGroup"
											type="text" />
									</div>
								</div>

								<div class="form-group form-group-sm ">
									<label class="col-sm-2 control-label"><span
										style="color: red; position: relative; top: 2px;">*</span> 想法</label>
									<div class="col-sm-6">
										<input class="form-control validate[required]" id="ideas"
											type="text" />
									</div>
								</div>

								<div class="form-group form-group-sm ">
									<label class="col-sm-2 control-label"><span
										style="color: red; position: relative; top: 2px;">*</span> 时间段</label>
									<div class="col-sm-6">
										<input class="form-control validate[required]" id="timeSlot"
											type="text" />
									</div>
								</div>

								<div class="form-group form-group-sm ">
									<label class="col-sm-2 control-label"><span
										style="color: red; position: relative; top: 2px;">*</span>
										浏览网页</label>
									<div class="col-sm-6">
										<input class="form-control validate[required]" id="netAddress"
											type="text" />
									</div>
								</div>
								<div class="form-group form-group-sm ">
									<label class="col-sm-2 control-label"><span
										style="color: red; position: relative; top: 2px;">*</span>
										来自IP</label>
									<div class="col-sm-6">
										<input class="form-control validate[required]" id="ip"
											type="text" />
									</div>
								</div>

								<div class="form-group form-group-sm ">
									<label class="col-sm-2 control-label"><span
										style="color: red; position: relative; top: 2px;">*</span> 处理人</label>
									<div class="col-sm-6">
										<input class="form-control validate[required]"
											id="handlerName" type="text" />
									</div>
								</div>
								<div class="form-group form-group-sm ">
									<label class="col-sm-2 control-label"><span
										style="color: red; position: relative; top: 2px;">*</span>
										处理时间</label>
									<div class="col-sm-6">
										<input class="form-control validate[required]" id="handleTime"
											type="text" />
									</div>
								</div>
								<div class="form-group form-group-sm ">
									<label class="col-sm-2 control-label"><span
										style="color: red; position: relative; top: 2px;">*</span>
										是否处理</label>
									<div class="col-sm-6">
										<select id="handleStatus" width=100%>
											<option selected="selected" value="">---请选择---</option>
											<option value="是">是</option>
											<option value="否">否</option>
										</select>
									</div>
								</div>
								<div class="form-group form-group-sm ">
									<label class="col-sm-2 control-label"><span
										style="color: red; position: relative; top: 2px;">*</span>
										是否有效</label>
									<div class="col-sm-6">
										<select id="effectiveStatus" width=100%>
											<option selected="selected" value="">---请选择---</option>
											<option value="是">是</option>
											<option value="否">否</option>
										</select>
									</div>
								</div>
								<div class="form-group form-group-sm ">
									<label class="col-sm-2 control-label"><span
										style="color: red; position: relative; top: 2px;"></span> 备注</label>
									<div class="col-sm-6">
										<input class="form-control validate[required]" id="remarks"
											type="text" />
									</div>
								</div>
								<br>
							</fieldset>
						</form>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button id="btEmpAdd" onclick="openSaveButton()"
							class="btn btn-primary" type="button">保存</button>
					</div>
					<input class="form-control" id="id" type="hidden" />
				</div>
			</div>
		</div>
		<!-- 弹出窗口区域，触发事件后弹出   结束 -->
	</div>
</body>

</html>