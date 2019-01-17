$(function() {
	init();
	// 定位表格查询框
	$(".search").css({
		'position' : 'fixed',
		'right' : '10px',
		'top' : '0',
		'z-index' : '1000',
		'width' : '240px'
	});
	$(".search input").attr("placeholder", "搜索");
	$(".search input").css({
		'padding-right' : '23px'
	});
	$('#myForm1').validationEngine();
	initBtnEvent();
});

function initBtnEvent() {
	// 删除
	$("#btn_del").bind('click', function() {
		var selections = getIdSelections();
		layer.confirm("您确定要删除所选信息吗?", {
			skin : 'layui-layer-molv',
			btn : [ '确定', '取消' ]
		}, function() {
			var url = getRootPath() + "phoneCallback/delete";
			$.post(url, {
				id : selections + ""
			}, function(data) {
				if (data.status == "1") {
					layer.msg(data.message, {
						time : 2000
					}, function() {
						$('#roleInfo').bootstrapTable('refresh');
					});
				} else {
					layer.msg(data.message, {
						time : 2000
					});
				}
			}, "json");
		}, function() {
			return;
		});
	});
}

function init() {
	// 初始化Table
	oTable = new TableInit();
	oTable.Init();
};

var TableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$('#roleInfo').bootstrapTable({
			url : getRootPath() + "phoneCallback/list", // 请求后台的URL（*）
			toolbar : '#toolbar', // 工具按钮用哪个容器
			striped : true,
			search : true,
			searchOnEnterKey : true,
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			sortable : false,
			sortOrder : "asc",
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			cache : false,
			clickToSelect : false,
			queryParams : oTableInit.queryParams,
			showExport : "true",
			minimumCountColumns : 2, // 最少允许的列数
			buttonsAlign : "left",
			buttonsClass : 'white',
			showPaginationSwitch : false,
			pagination : true,
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : '[10, 25, 50, 100,ALL]', // 可供选择的每页的行数（*）
			columns : [ {
				field : 'state',
				checkbox : true,
			}, {
				field : 'id',
				visible : false,
				title : 'id'
			}, {
				field : 'phoneNumber',
				title : '电话'
			}, {
				field : 'netAddress',
				title : '浏览网页'
			}, {
				field : 'ip',
				title : '来自IP'
			},{
				align:'center',
				field : 'createTime',
				title : '提交时间'
			}, {
				align:'center',
				field : 'handleStatus',
				title : '是否处理',
				formatter : function(value) {
					var html = "";
					if ("是" == value) {
						html = "<span class='badge bg-green'  style='padding:5px 10px;align:center;width:50px;'>"
								+ value + "</span>";
					} else {
						html = "<span class='badge bg-orange'  style='padding:5px 10px;align:center;width:50px;'>"
								+ value + "</span>";
					}
					return html;
				}
			}, {
				field : 'handlerName',
				title : '处理人'
			}, {
				field : 'handleTime',
				title : '处理时间'
			}, {
				align:'center',
				field : 'effectiveStatus',
				title : '是否有效',
				formatter : function(value) {
					var html = "";
					if ("是" == value) {
						html = "<span class='badge bg-green'  style='padding:5px 10px;align:center;width:50px;'>"
								+ value + "</span>";
					} else {
						html = "<span class='badge bg-orange'  style='padding:5px 10px;align:center;width:50px;'>"
								+ value + "</span>";
					}
					return html;
				}
			}, {
				field : 'remarks',
				title : '备注'
			}, {
				field : 'id',
				title : '操作',
				align : 'center',
				events : operateEvents,
				formatter : operateFormatter
			} ],
			onCheck : function(row, e) {
				tableCheckEvents();
			},
			onUncheck : function(row, e) {
				tableCheckEvents();
			},
			onCheckAll : function(rows) {
				$("#btn_del").attr("disabled", false);
			},
			onUncheckAll : function(rows) {
				$("#btn_del").attr("disabled", true);
			},
			onLoadSuccess : function(rows) {
				$("#btn_del").attr("disabled", true);
			}
		});
	};

	function operateFormatter(value, row, index) {
		var html = '<a id="a_check">查看 <span style="color:#CCC">|</span> </a>'
				+ '<a id="a_edit">处理</a>'
		return html;
	}

	// 操作列的事件
	window.operateEvents = {
		'click #a_check' : function(e, value, row, index) {
			editOrCheck(row, 1);
		},
		'click #a_edit' : function(e, value, row, index) {
			editOrCheck(row, 2);
		}
	};

	oTableInit.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			offset : params.offset, // 页码
			search : params.search
		// 表格搜索框的值
		};
		return temp;
	};

	return oTableInit;

};

//查看和编辑
function editOrCheck(obj, type) {
	
	if (type == 1) {
		$('#btEmpAdd').hide();
		$("#myModalTitle").html("查看");
		setSelectOp("handleStatus", obj.handleStatus);
		$("#handleStatus").attr("disabled", true);
		setSelectOp("effectiveStatus", obj.effectiveStatus);
		$("#effectiveStatus").attr("disabled", true);
		$("#remarks").attr("disabled", true);
		$("#remarks").val(obj.remarks);

	} else {
		$('#btEmpAdd').show();
		$("#myModalTitle").html("处理");
		setSelectOp("handleStatus", obj.handleStatus);
		$("#handleStatus").attr("disabled", false);
		setSelectOp("effectiveStatus", obj.effectiveStatus);
		$("#effectiveStatus").attr("disabled", false);
		$("#remarks").attr("disabled", false);
		$("#remarks").val(obj.remarks);
	}

	$("#phoneNumber").attr("disabled", true);
	$("#netAddress").attr("disabled", true);
	$("#ip").attr("disabled", true);
	$("#handlerName").attr("disabled", true);
	$("#handlerName").val(obj.handlerName);
	$("#handleTime").attr("disabled", true);
	$("#handleTime").val(obj.handleTime);
	
	$("#id").val(obj.id);
	$("#phoneNumber").val(obj.phoneNumber);
	$("#netAddress").val(obj.netAddress);
	$("#ip").val(obj.ip);

	$('#myModal1').modal({
		backdrop : 'static',
		keyboard : false
	});
}
// 表格选择事件
function tableCheckEvents() {
	var r = $('#roleInfo').bootstrapTable('getSelections');
	if (r.length == 0) {
		$("#btn_del").attr("disabled", true);
	}
	if (r.length == 1) {
		$("#btn_del").attr("disabled", false);
	}
}

/**
 * 选择框
 * 
 * @returns
 */
function getIdSelections() {
	return $.map($('#roleInfo').bootstrapTable('getSelections'), function(row) {
		return row.id;
	});
}

/**
 * 保存操作
 */
function openSaveButton() {
	var phoneNumber = $("#phoneNumber").val();
	var netAddress = $("#netAddress").val();
	var ip = $("#ip").val();
	var handlerName = $("#handlerName").val();
	var handleTime = $("#handleTime").val();
	var effectiveStatus = $("#effectiveStatus option:selected").val();
	var handleStatus = $("#handleStatus option:selected").val();
	var remarks = $("#remarks").val();
	
	if (null == effectiveStatus || "" == effectiveStatus) {
		layer.msg("请选择是否有效", {
			time : 2000
		});
		return;
	}

	if (null == handleStatus || "" == handleStatus) {
		layer.msg("请选择处理状态", {
			time : 2000
		});
		return;
	}
	
	var id = $("#id").val();
	
	if(null == id || "" == id){
		layer.msg("主键不能空", {
			time : 2000
		});
		return;
	}

	var addJson = {
		id :id ,
		phoneNumber : phoneNumber,
		netAddress : netAddress,
		ip : ip,
		handlerName : handlerName,
		handleTime : handleTime,
		effectiveStatus : effectiveStatus,
		handleStatus : handleStatus,
		remarks : remarks
	};
	
	$.ajax({
		type : "post",
		url : getRootPath() + "phoneCallback/saveOrUpdate",
		data : addJson,
		dataType : "json",
		async : true,
		success : function(data) {
			if (data.status == "1") {
				layer.msg(data.message, {
					time : 2000
				}, function() {
					$('#roleInfo').bootstrapTable('refresh');
					$('#myModal1').modal('hide');
				});
			} else {
				layer.msg(data.message, {
					time : 2000
				});
			}
		}
	});
}

function setSelectOp(id, str) {
	var obj = document.getElementById(id);
	for (i = 0; i < obj.length; i++) {
		if (obj[i].value == str) {
			obj[i].selected = true;
		} else {
			obj[i].selected = false;
		}
	}
}