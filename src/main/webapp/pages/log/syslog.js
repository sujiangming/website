var filter = "";
$(function () {
	init();
	//定位表格查询框
	$(".search").css({'position':'fixed','right':'15px','top':'0','z-index':'1000','width':'240px'});
	$(".search input").attr("placeholder","搜索");
	$(".search input").css({'padding-right':'23px'});
	$('#myForm1').validationEngine();
	initBtnEvent();
});

function initBtnEvent(){
	$("#btn_login").bind('click',function(){
		filter = "01";
		$('#sysLogInfo').bootstrapTable('refresh');
	});
	
	$("#btn_oper").bind('click',function(){
		filter = "02";
		$('#sysLogInfo').bootstrapTable('refresh');
	});
	
	$("#btn_empty").bind('click',function(){
		layer.confirm("您确定要清空所有日志吗?",{
			skin: 'layui-layer-molv', 
			btn: ['确定','取消']
		},function(){
			$.post(getRootPath()+"log/empty",function(data){
			 	  if(data.status == "1"){
			 		  layer.msg(data.message,{time: 2000}, function(){
			 			  $('#sysLogInfo').bootstrapTable('refresh');
			 		  });
			 	  }else{
			 		 layer.msg(data.message,{time: 2000});
			 	  }
				},"json");
		},function(){
			return;
		});
	});

	$("#btn_del").bind('click',function(){
		var selections = getIdSelections();
		layer.confirm("您确定要删除所选日志吗?",{
			skin: 'layui-layer-molv', 
			btn: ['确定','取消']
		},function(){
	        var url=getRootPath()+"log/delete";
			$.post(url,
				{id:selections+""},
		        function(data){
					if(data.status == "1"){
				 		  layer.msg(data.message,{time: 2000}, function(){
				 			  $('#sysLogInfo').bootstrapTable('refresh');
				 		  });
				 	  }else{
				 		 layer.msg(data.message,{time: 2000});
				 	  }
				});
		},function(){
			return;
		});
	});
	
	$("#buttonSearch").bind('click',function(){
		$('#sysLogInfo').bootstrapTable('refresh');
	});
	
	$("#resetSearch").bind('click',function(){
		$("#customerTypeSearch").val("");
	});
	
	$('#myModal1').on('hide.bs.modal', function () {
		
	});
}

function init(){
    var oTable = new TableInit();
    oTable.Init();
};

var TableInit = function (){
	var oTableInit = new Object();
	oTableInit.Init = function (){
		$('#sysLogInfo').bootstrapTable({
			url: getRootPath()+'log/list',         //请求后台的URL（*）
			toolbar: '#toolbar',                //工具按钮用哪个容器
    		striped: false,
            sortable: false,
            sortOrder: "asc",
            sidePagination: "server",			//分页方式：client客户端分页，server服务端分页（*）
            cache: false,
            clickToSelect: false,
            queryParams: oTableInit.queryParams,
            showExport: false,
            minimumCountColumns: 2,     //最少允许的列数
            showPaginationSwitch: false,
            pagination: true,
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: '[10, 25, 50, 100,ALL]',        //可供选择的每页的行数（*）         
    		columns: [ 
    		{
                field: 'state',
                checkbox: true,
            }, {
            	field: 'id',
            	visible:false,
            	title: 'id'
            }, {
            	field: 'username',
            	visible:false,
            	title: '用户名'
            }, {
            	field: 'cname',
            	title: '操作人'
            }, {
                field: 'operTime',
                title: '操作时间',
                formatter:json2TimeStamp
            }, {
                field: 'operModule',
                title: '模块'
            }, {
                field: 'operMsg',
                title: '操作内容'
            }, {
                field: 'operIp',
                title: '操作IP'
            }, {
            	field: 'operate',
                title: '操作',
                visible: false,
                width: '10%',
                align: 'center',
                formatter: operateFormatter,
                events: operateEvents
            }
            ],
            onCheck:function(row,e){
            	tableCheckEvents();
            },
            onUncheck: function(row,e){
            	tableCheckEvents();
            },
            onCheckAll: function(rows){
        		$("#btn_del").attr("disabled",false);
            },
            onUncheckAll: function(rows){
        		$("#btn_del").attr("disabled",true);
            },
            onLoadSuccess: function(rows){
        		$("#btn_del").attr("disabled",true);
        		var r = $('#sysLogInfo').bootstrapTable('getData');
        		if(r.length==0){
        			$("#btn_empty").attr("disabled",true);
        		}
            }
		});
	};
	
	function operateFormatter(value, row, index) {
		var html = '<a id="a_check">查看 </a>';
        return html;
    }
	//操作列的事件
	window.operateEvents = {
        'click #a_check': function (e, value, row, index) {
        	editOrCheck(row);
        }
    };
	
	oTableInit.queryParams = function (params) {
	    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	        limit: params.limit,   //页面大小
	        offset: params.offset,  //页码
	        search: params.search,  //表格搜索框的值
	        filter: filter
	    };
	    return temp;
	};
	dateFormate = function(value){
		if(value==null || value==""){
		    return "";
		}else{
			return json2Date(value);
		}
		
	};
	isOrNot = function(value){
		if(value==true){
		    return "是";
		}else{
			return "否";
		}
	};
	sexF = function(value){
		if(value==true){
		    return "男";
		}else{
			return "女";
		}
	};
	return oTableInit;
	
};

//选择表格id
function getIdSelections() {
    return $.map($('#sysLogInfo').bootstrapTable('getSelections'), function (row) {
        return row.id;
    });
}

//表格选择事件
function tableCheckEvents(){
	var r = $('#sysLogInfo').bootstrapTable('getSelections');
	if(r.length==0){
		$("#btn_del").attr("disabled",true);
	}
	if(r.length==1){
		$("#btn_del").attr("disabled",false);
	}
}

/**
 * 保存操作
 */
function openSaveButton(type){
	var flag = $('#myForm1').validationEngine('validate');
	var customerId = $("#customerId").val();
	if(flag){
		initBlockUI();
		$('#myForm1').validationEngine('hide');
    	var addJson = getDataForm();
    	$.ajax({
            type: "post",
            url: getRootPath()+"electricityCustomer.app?method=save",
            data: addJson,
            dataType: "json",
    		async : false,
            success: function(data)
            {
				$.unblockUI();
            	if(data="success"){
            		layer.msg('保存成功！',{
	    				time: 2000
	    			}, function(){
						if(type=="2"){
							location.href = getRootPath()+"pages/device/deviceAccount.jsp";
						}else{
    	    				$('#electricityCustomerInfo').bootstrapTable('refresh');
						}
	    				$('#myModal1').modal('hide');
	    			});
            	}else{
            		layer.msg('保存失败！',{
	    				time: 2000
	    			});
            	}
            }
        });
	}else{
		layer.msg('表单验证未通过！',{
			time: 3000
		});
	}
}
    
/**
 * 获取表单数据
 */
function getDataForm(){
	var addJson = {
		customerId:$("#customerId").val(),
		unitName:$("#unitName").find("option:selected").text(),
		parentDeptId:$("#parentDeptId").val(),
		customerName:$("#customerName").val(),
		customerNo:$("#customerNo").val(),
		customerType:$("#customerType").val(),
		customerSubclass:$("#customerSubclass").val(),
		customerState:$("#customerState").val(),
		voltageLevel:$("#voltageLevel").val(),
		electricityAddress:$("#electricityAddress").val(),
		industryCategory:$("#industryCategory").val(),
		industryClassify:$("#industryClassify").val(),
		createAccountDate:$("#createAccountDate").val()
	};
	return addJson;
}
    
//清空表单
function clearForm(){
	$("#customerId").val("");
	$("#customerName").val("");
	$("#parentDeptId").val("");
	$("#customerNo").val("");
	$("#customerType").val("");
	$("#customerSubclass").val("");
	$("#customerState").val("");
	$("#voltageLevel").val("");
	$("#electricityAddress").val("");
}
    
//查看和编辑
function editOrCheck(obj,type){
	$('#myForm1').validationEngine('hide');
	$("#parentDeptId").attr("disabled",true);
	$("#customerName").attr("disabled",true);
	$("#linkMobile").attr("disabled",true);
	$("#customerId").val(obj.customerId);
	$("#parentDeptId").val(obj.parentDeptId);
	$("#unitName").val(obj.companyId);
	$("#customerName").val(obj.customerName);
	$("#customerNo").val(obj.customerNo);
	$('#myModal1').modal({backdrop: 'static', keyboard: false});
}
    