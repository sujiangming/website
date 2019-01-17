var table_btn = new Array();
var toolbar_btn = new Array();
var btnIdArray = new Array();

//工具条按钮初始化
function toolbarBtnInit(){
	var pathName = window.document.location.pathname;
	var url=getRootPath()+"mainFrame/mainFrameInfo.app?method=initPermissions";
    $.ajax({
        type: "post",
        url: url,
        data: {pathName:pathName},
        dataType: "json",
		async : false,
        success: function(data)
        {
        	if(data.length==0){
        		console.log(pathName+"：--没有查到权限数据");
        	}
        	for(var i=0;i<data.length;i++){
        		var btnId = data[i].btnId;
        		var btnName = data[i].btnName;
        		if(btnId.indexOf("btn")!=-1){
        			toolbar_btn.push('<button id="'+btnId+'" class="btn btn-primary" style="margin-right:5px;" type="button"> '+btnName+' </button>');
        			btnIdArray.push(btnId);
        		}else{
        			var obj = {
						btnId:btnId,
						btnName:btnName
        			}
        			table_btn.push(obj);
        		}
        	}
        	$("#topToolbar").html(toolbar_btn.join(''));
        }
    });
	
};

//表格链接初始化
function tableBtnInit(){
	var result = new Array();
	if(table_btn.length>3){
		for(var i=0;i<table_btn.length;i++){
			if(i>1){
				if(i==2){
					result.push('<div class="dropdown" style="display:inline"><a class="dropdown-toggle" data-toggle="dropdown" id="more" href="javascript:void(0)"> 更多<b class="caret"></b></a><ul class="dropdown-menu  m-t-xs">');
					result.push('<li><a id="'+table_btn[i].btnId+'">'+table_btn[i].btnName+'</a></li>');
				}else{
					result.push('<li><a id="'+table_btn[i].btnId+'">'+table_btn[i].btnName+'</a></li>');
				}
			}else{
				result.push('<a id="'+table_btn[i].btnId+'">'+table_btn[i].btnName+' <span style="color:#CCC">|</span> </a>');
			}
		}
		result.push('</ul></div>');
	}else{
		for(var i=0;i<table_btn.length;i++){
			if(i==table_btn.length-1){
				result.push('<a id="'+table_btn[i].btnId+'">'+table_btn[i].btnName+' </a>');
			}else{
				result.push('<a id="'+table_btn[i].btnId+'">'+table_btn[i].btnName+'</a> <span style="color:#CCC">|</span> ');
			}
		}
	}
	
	return result.join('');
};
