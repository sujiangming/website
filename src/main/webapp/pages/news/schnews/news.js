$(function() {
	init();
	// 定位表格查询框
	$(".search").css({
		'position' : 'fixed',
		'right' : '15px',
		'top' : '0',
		'z-index' : '1000',
		'width' : '240px'
	});
	$(".search input").attr("placeholder", "搜索");
	$(".search input").css({
		'padding-right' : '23px'
	});
	
	initBtnEvent();

	// 实例化编辑器
	// 建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor');
});

function initBtnEvent() {
	// 新增
	$("#btn_add").bind('click', function() {
		clearForm();
		setContent("");
		$('#myModal1').modal({
			backdrop : 'static',
			keyboard : false
		});
	});
	// 删除
	$("#btn_del").bind('click', function() {
		var selections = getIdSelections();
		layer.confirm("您确定要删除所选信息吗?", {
			skin : 'layui-layer-molv',
			btn : [ '确定', '取消' ]
		}, function() {
			var url = getRootPath() + "news/delete";
			$.post(url, {
				id : selections + ""
			}, function(data) {
				if (data.status == "1") {
					layer.msg(data.message, {
						time : 2000
					}, function() {
						$('#newsInfo').bootstrapTable('refresh');
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
		$('#newsInfo').bootstrapTable({
			url : getRootPath() + "news/list?type=1", // 请求后台的URL（*）
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
				field : 'title',
				title : '新闻标题'
			}, {
				field : 'author',
				title : '创建人',
			}, {
				field : 'createTime',
				title : '创建时间',
			}, {
				field : 'pubishStatus',
				title : '发布状态',
			}, {
				field : 'publishTime',
				title : '发布时间',
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
				+ '<a id="a_edit">修改 <span style="color:#CCC">|</span> </a>'
				+ '<a id="a_publish">发布</a>';
		return html;
	}
	
	// 操作列的事件
	window.operateEvents = {
		'click #a_check' : function(e, value, row, index) {
			editOrCheck(row, 1);
		},
		'click #a_edit' : function(e, value, row, index) {
			editOrCheck(row, 2);
		},
		'click #a_publish' : function(e, value, row, index) {
			publish(row, 3);
		}
	};

	oTableInit.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			offset : params.offset, // 页码
			search : params.search // 表格搜索框的值
		};
		return temp;
	};
	dateFormate = function(value) {
		if (value == null || value == "") {
			return "";
		} else {
			return json2Date(value);
		}

	};
	
	return oTableInit;

}

/**
 * 发布信息接口
 * @param obj
 * @param type
 */
function publish(obj, type){
	var isPublish = obj.isPubish;
	if(isPublish){
		layer.msg("已发布，不能再发布了", {
			time : 2000
		});
		return;
	}
	var para = {
		id:obj.id
	};
	layer.confirm("您确定要发布该条新闻吗?", {
		skin : 'layui-layer-molv',
		btn : [ '确定', '取消' ]
	}, function() {
		var url = getRootPath() + "news/publish";
		$.post(url, para , function(data) {
			if (data.status == "1") {
				layer.msg(data.message, {
					time : 2000
				}, function() {
					$('#newsInfo').bootstrapTable('refresh');
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
}

// 表格选择事件
function tableCheckEvents() {
	var r = $('#newsInfo').bootstrapTable('getSelections');
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
	return $.map($('#newsInfo').bootstrapTable('getSelections'),
			function(row) {
				return row.id;
			});
}

/**
 * 保存操作
 */
function openSaveButton() {
	var title = $("#newsTitle").val();

	if (null == title || "" == title) {
		layer.msg("请输入新闻标题", {
			time : 2000
		});
		return;
	}

	var content = getContent();

	if (null == content || "" == content) {
		layer.msg("请输入新闻内容", {
			time : 2000
		});
		return;
	}

	var addJson = {
		id : $("#newsId").val(),
		title : title,
		content : content,
		author : $("#newsId").attr("name"),
		type : 1
	};

	$.ajax({
		type : "post",
		url : getRootPath() + "news/saveOrUpdate",
		data : addJson,
		dataType : "json",
		async : true,
		success : function(data) {
			if (data.status == "1") {
				layer.msg(data.message, {
					time : 2000
				}, function() {
					$('#newsInfo').bootstrapTable('refresh');
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

// 获取ueditor内容
function getContent() {
	var arr = [];
	arr.push(UE.getEditor('editor').getContent());
	console.log(arr.join("\n"));
	return arr.join("\n");
}

function setContent(content) {
	UE.getEditor('editor').setContent(content, false);
}

// 清空表单
function clearForm() {
	$("#newsTitle").val("");
}

// 查看和编辑
function editOrCheck(obj, type) {
	if (type == 1) {
		$('#btEmpAdd').hide();
		$("#myModalTitle").html("查看");
		$("#newsTitle").attr("disabled", true);
	} else {
		$('#btEmpAdd').show();
		$("#myModalTitle").html("编辑");
		$("#newsTitle").attr("disabled", false);
	}

	$("#newsId").val(obj.id);
	$("#newsTitle").val(obj.title);
	setContent(obj.content);

	$('#myModal1').modal({
		backdrop : 'static',
		keyboard : false
	});
}