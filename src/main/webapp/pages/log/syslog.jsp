<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <% String path = request.getContextPath();
    request.setAttribute("ContextPath",path);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>日志管理</title> 
<%@ include file="/common/taglibs.jsp"%>
<script src="${ContextPath}/pages/log/syslog.js" type="text/javascript"></script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content">
    
	    <!-- 按钮及查询区域，此处按钮为占位符，实际加载的为view中的按钮控件  -->
	    <div style="width:100%;height:55px;background-color:#f3f3f4;position:fixed;left:0;top:0;padding:0 10px;z-index:1000">
			<div class="row">
		   		<div  class="col-md-6" style="float:left;margin-top:10px;">
		   			 <button id="btn_login" class="btn btn-info" type="button"><i class="fa fa-search"></i>&nbsp;登录日志</button>
		    		 <button id="btn_oper" class="btn btn-info" type="button"><i class="fa fa-search"></i> 操作日志</button>
		    		 <button id="btn_del" class="btn btn-warning" type="button" disabled><i class="fa fa-minus"></i>&nbsp;删除日志</button>
		    		 <button id="btn_empty" class="btn btn-danger" type="button"><i class="fa fa-remove"></i>&nbsp;清空日志</button>
		   	    </div>
		   	</div>
	   	</div>
	   	<i class="fa fa-search" style="position:fixed;right:20px;top:19px;z-index:1001;color:#e7eaec;font-size:15px;"></i>
	   	<!-- 占位div -->
   		<div class="" style="width:100%;height:45px;"></div>
   		<!-- 占位div -->
   		
   		<!-- 数据表格区域  -->  
   		<div class="row">
	            <div class="col-sm-12">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>操作日志信息<small></small></h5>
	                        <div class="ibox-tools">
	                            <a class="collapse-link">
	                                <i class="fa fa-chevron-up"></i>
	                            </a>
	                            <a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#">
	                                <i class="fa fa-wrench"></i>
	                            </a>
	                            
	                            <a class="close-link">
	                                <i class="fa fa-times"></i>
	                            </a>
	                        </div>
	                    </div>
	                    <div class="ibox-content" id="tempTable">
	                    	<div id="toolbar"></div>
							<table class="table-no-bordered" id="sysLogInfo"></table>	
	                    	
	                    </div>
	                    
	                </div>
	            </div>
	        </div>
    </div>
</body>
</html>