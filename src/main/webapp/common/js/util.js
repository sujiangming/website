//获取项目根路径
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+2);
    return(localhostPaht+projectName);
}

//获取文件根路径
function getFilePath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    return(localhostPaht+"/file/");
}


//毫秒转时间YYYY-MM-DD hh:mm:ss
function json2TimeStamp(milliseconds){
	if(milliseconds==null || milliseconds==""){
		return "";
	}
    var datetime = new Date();
    datetime.setTime(milliseconds);
    var year=datetime.getFullYear();
         //月份重0开始，所以要加1，当小于10月时，为了显示2位的月份，所以补0
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
}
//毫秒转日期YYYY-MM-DD
function json2Date(milliseconds){
	if(milliseconds==null || milliseconds==""){
		return "";
	}
    var datetime = new Date();
    datetime.setTime(milliseconds);
    var year=datetime.getFullYear();
         //月份重0开始，所以要加1，当小于10月时，为了显示2位的月份，所以补0
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    return year + "-" + month + "-" + date;
}

//毫秒转日期YYYY-MM-DD
function json2YearMonth(milliseconds){
	if(milliseconds==null || milliseconds==""){
		return "";
	}
    var datetime = new Date();
    datetime.setTime(milliseconds);
    var year=datetime.getFullYear();
         //月份重0开始，所以要加1，当小于10月时，为了显示2位的月份，所以补0
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    return year + "-" + month;
}


//获取格式化的当前时间
function getNowFormatDate(flag,vardate) {
	if(flag == false && (vardate==null || vardate==''))
		return null;
	var date;
	if(flag==true){
    	date = new Date();
	}else{
		date=new Date(vardate);
	}
    var seperator1 = "-";
    var seperator2 = ":";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hour=date.getHours();       //获取当前小时数(0-23)
    var min=date.getMinutes();     //获取当前分钟数(0-59)
    var sec=date.getSeconds();     //获取当前秒数(0-59)
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hour >= 1 && hour <= 9) {
        hour = "0" + hour;
    }
    if (min >= 0 && min <= 9) {
    	min = "0" + min;
    }if (sec >= 0 && sec <= 9) {
    	sec = "0" + sec;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate
            + " " + hour + seperator2 + min
            + seperator2 + sec;
    return currentdate;
}

Date.prototype.format = function(format) {
    var date = {
       "M+": this.getMonth() + 1,
       "d+": this.getDate(),
       "h+": this.getHours(),
       "m+": this.getMinutes(),
       "s+": this.getSeconds(),
       "q+": Math.floor((this.getMonth() + 3) / 3),
       "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
       format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
       if (new RegExp("(" + k + ")").test(format)) {
           format = format.replace(RegExp.$1, RegExp.$1.length == 1
              ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
       }
    }
    return format;
}

//删除指定字符串数组元素
function deleteArray(a,str){
	var index = $.inArray(str,a);
	if(index>=0){
		//arrayObject.splice(index,howmany,item1,.....,itemX)
		//参数    描述
		//index  必需。整数，规定添加/删除项目的位置，使用负数可从数组结尾处规定位置。
		//howmany 必需。要删除的项目数量。如果设置为 0，则不会删除项目。
		//item1, ..., itemX 可选。向数组添加的新项目。
	  a.splice(index,1);
	  return a;
	}else{
	  alert("error");
	  return false;
	}
}


//图片缩小处理
function DrawImage(ImgD, imageWidth, imageHeight) {
    var image = new Image();
    image.src = ImgD.src;
    if (image.width > 0 && image.height > 0) {
        flag = true;
        if (image.width / image.height >= imageWidth / imageHeight) {
            if (image.width > imageWidth) {
                ImgD.width = imageWidth;
                ImgD.height = (image.height * imageWidth) / image.width;
            } else {
                ImgD.width = image.width;
                ImgD.height = image.height;
            }
        }
        else {
            if (image.height > imageHeight) {
                ImgD.height = imageHeight;
                ImgD.width = (image.width * imageHeight) / image.height;
            } else {
                ImgD.width = image.width;
                ImgD.height = image.height;
            }
        }
    }
}

//构造菜单节点结构
function Node(text,nodes,href,icon,url,desc,order,parentid,forNavigation){
	this.text = text;
	this.nodes = nodes;
	this.href = href;
	this.icon = icon;
	this.order = order;
	this.desc = desc;
	this.url = url;
	this.parentid = parentid;
	this.forNavigation = forNavigation;
}

//获取生成treeview菜单数据结构
function getTreeData(menuData){
	var firstLevel = [];
	var secondLevel = [];
	var thirdLevel = [];
	var length1 = menuData.length;
	firstOrder = length1;
	for(var i=0;i<length1;i++){
		var firstMenu = menuData[i];
		var firstNode = new Node();
		firstNode.text = firstMenu.name;
		firstNode.href = firstMenu.id;
		firstNode.forNavigation = firstMenu.forNavigation;
		firstNode.icon = firstMenu.icon;
		firstNode.url = firstMenu.url;
		firstNode.desc = firstMenu.desc;
		firstNode.parentid = firstMenu.parentId;
		firstNode.order = firstMenu.order;
		var subMenu = firstMenu.children;
		if(typeof(subMenu)!="undefined"){
			secondLevel = [];
			var length2 = subMenu.length;
			for(var j=0;j<length2;j++){
				var secMenu = subMenu[j];
				var secondNode = new Node();
				secondNode.text = secMenu.name;
				secondNode.href = secMenu.id;
				secondNode.forNavigation = secMenu.forNavigation;
				secondNode.icon = secMenu.icon;
				secondNode.url = secMenu.url;
				secondNode.desc = secMenu.desc;
				secondNode.parentid = secMenu.parentId;
				secondNode.order = secMenu.order;
//				loadUrls(secMenu.id);
				var thirdMenu = secMenu.children;
				if(typeof(thirdMenu)!="undefined"){
					thirdLevel = [];
					var length3 = thirdMenu.length;
					for(var k=0;k<length3;k++){
						var trdMenu = thirdMenu[k];
						var thirdNode = new Node();
						thirdNode.text = trdMenu.name;
						thirdNode.href = trdMenu.id;
						thirdNode.forNavigation = trdMenu.forNavigation;
						thirdNode.icon = trdMenu.icon;
						thirdNode.url = trdMenu.url;
						thirdNode.desc = trdMenu.desc;
						thirdNode.parentid = trdMenu.parentId;
						thirdNode.order = trdMenu.order;
						thirdLevel.push(thirdNode);
					}
					secondNode.nodes = thirdLevel;
				}
				secondLevel.push(secondNode);
			}
			firstNode.nodes = secondLevel;
		}
		firstLevel.push(firstNode);
	}
	return firstLevel;
}

//回车事件
function keydownEvent() {
    var e = window.event || arguments.callee.caller.arguments[0];
    if (e && e.keyCode == 13 ) {
    	queryVerifyCode();
    }
}

//生成用户及设备树
function initTreeView(){
	$('#treeview').tree({
		dataUrl:function(node) {
			   if(node == null){
				   return {
			           url: getRootPath()+"treeview.app?method=initTree&type=&id="
			       };
			   }
			   if(typeof(node)!='undefined'){
				   return {
			           url: getRootPath()+"treeview.app?method=initTree&type="+node.type+"&id="+node.id
			       };
			   }else{
				   return {
			           url: getRootPath()+"treeview.app?method=initTree&type=&id="
			       };
			   }
			},
	    dataFilter: function(data){
	    	if(data != null){
	    		var treeData = new Array();
		    	for(var i=0;i<data.length;i++){
		    		var nodeData = {};
		    		nodeData.id = data[i].id;
		    		nodeData.type = data[i].type;
		    		nodeData.bayType = data[i].bayType;
		    		nodeData.name = data[i].name;
		    		nodeData.load_on_demand = true;
		    		nodeData.children = [];
		    		treeData.push(nodeData);
		    	}
		    	return treeData;
	    	}else{
	    		return;
	    	}
	    	
	    	
	    },
	    //此处判断该节点是否已被选中，若已选中则不再选中
	    onCanSelectNode: function(node) {
	    	var is_selected = $('#treeview').tree('isNodeSelected', node);
	        if (is_selected == false || is_selected==null) {
	            return true;
	        }
	        else {
	            return false;
	        }
	    }
//	    onLoading: function(is_loading, node, $el){
//	    	if(is_loading){
//	    		$el.addClass('jqtree-loading');
//	    	}
//	    },
	});
}


//生成监控设备树
function initMonitorTreeView(){
	$('#treeview').tree({
		dataUrl:function(node) {
			   if(node == null){
				   return {
			           url: getRootPath()+"treeview.app?method=initMonitorTree&type=&id="
			       };
			   }
			   if(typeof(node)!='undefined'){
				   return {
			           url: getRootPath()+"treeview.app?method=initMonitorTree&type="+node.type+"&id="+node.id
			       };
			   }else{
				   return {
			           url: getRootPath()+"treeview.app?method=initMonitorTree&type=&id="
			       };
			   }
			},
	    dataFilter: function(data){
	    	var treeData = new Array();
	    	if(data != null){
	    		for(var i=0;i<data.length;i++){
		    		var nodeData = {};
		    		nodeData.id = data[i].id;
		    		nodeData.type = data[i].type;
		    		nodeData.name = data[i].name;
		    		nodeData.load_on_demand = true;
		    		nodeData.children = [];
		    		treeData.push(nodeData);
		    	}
	    		return treeData;
	    	}else{
	    		return;
	    	}
	    	
	    },
	    //此处判断该节点是否已被选中，若已选中则不再选中
	    onCanSelectNode: function(node) {
	    	var is_selected = $('#treeview').tree('isNodeSelected', node);
	        if (is_selected == false || is_selected==null) {
	            return true;
	        }
	        else {
	            return false;
	        }
	    }
	});
}


/**
 * 遮掉层，加载中... 效果
 */
function initBlockUI(message){
	if(typeof(message)=="undefined"){
		message = "加载中";
	}
	$.blockUI({
    	message:'<img src="'+getRootPath()+'UI-lib/dist/img/loading.gif" /><h5 style="margin-left:15px">'+message+'...</h5>',
    	baseZ:20000000,
    	css:{
    		width:'200px',
    		border: 'none', 
            padding: '20px', 
            backgroundColor: '#fff', 
            '-webkit-border-radius': '10px', 
            '-moz-border-radius': '10px', 
            opacity: .7, 
            color: '#000',
            borderRadius:'10px'
    	}
    }); 
}


/** 获取上一个月 
* 
* @date 格式为yyyy-mm-dd的日期，如：2014-01-25 
*/  
function getPreMonth(date) {  
   var arr = date.split('-');  
   var year = arr[0]; //获取当前日期的年份  
   var month = arr[1]; //获取当前日期的月份  
   var day = arr[2]; //获取当前日期的日  
   var days = new Date(year, month, 0);  
   days = days.getDate(); //获取当前日期中月的天数  
   var year2 = year;  
   var month2 = parseInt(month) - 1;  
   if (month2 == 0) {  
       year2 = parseInt(year2) - 1;  
       month2 = 12;  
   }  
   var day2 = day;  
   var days2 = new Date(year2, month2, 0);  
   days2 = days2.getDate();  
   if (day2 > days2) {  
       day2 = days2;  
   }  
   if (month2 < 10) {  
       month2 = '0' + month2;  
   }  
   var t2 = year2 + '-' + month2 + '-' + day2;  
   return t2;  
}  

/** 获取上一个月 
* 
* @date 格式为yyyy-mm-dd的日期，如：2014-01-25 
*/  
function getPreMonthStr(date) {  
   var arr = date.split('-');  
   var year = arr[0]; //获取当前日期的年份  
   var month = arr[1]; //获取当前日期的月份  
   var day = arr[2]; //获取当前日期的日  
   var days = new Date(year, month, 0);  
   days = days.getDate(); //获取当前日期中月的天数  
   var year2 = year;  
   var month2 = parseInt(month) - 1;  
   if (month2 == 0) {  
       year2 = parseInt(year2) - 1;  
       month2 = 12;  
   }  
   var day2 = day;  
   var days2 = new Date(year2, month2, 0);  
   days2 = days2.getDate();  
   if (day2 > days2) {  
       day2 = days2;  
   }  
   if (month2 < 10) {  
       month2 = '0' + month2;  
   }  
   var t2 = year2 + '-' + month2;  
   return t2;  
}  
/** 获取前一天日期 
* 
* @date 格式为yyyy-mm-dd的日期，如：2014-01-25 
*/ 
function getYestoday(date){      
    var yesterday_milliseconds=date.getTime()-1000*60*60*24;       
    var yesterday = new Date();       
        yesterday.setTime(yesterday_milliseconds);       
        
    var strYear = yesterday.getFullYear();    
    var strDay = yesterday.getDate();    
    var strMonth = yesterday.getMonth()+1;  
    if(strMonth<10)    
    {    
        strMonth="0"+strMonth;    
    } 
    if(strDay<10)    
    {    
    	strDay="0"+strDay;    
    }
    datastr = strYear+"-"+strMonth+"-"+strDay;  
    return datastr;  
}

/**
 * 表格列求和函数
 * @param arg1 基数
 * @param arg2 加数
 * @returns
 */
function accAdd(arg1, arg2) {
	var r1, r2, m;
	try {
		r1 = arg1.toString().split(".")[1].length
	} catch (e) {
		r1 = 0
	}
	try {
		r2 = arg2.toString().split(".")[1].length
	} catch (e) {
		r2 = 0
	}
	c = Math.abs(r1 - r2);
	m = Math.pow(10, Math.max(r1, r2));
	// return (arg1*m+arg2*m)/m;
	if (c > 0) {
		var cm = Math.pow(10, c);
		if (r1 > r2) {
			arg1 = Number(arg1.toString().replace(".", ""));
			arg2 = Number(arg2.toString().replace(".", "")) * cm;
		} else {
			arg1 = Number(arg1.toString().replace(".", "")) * cm;
			arg2 = Number(arg2.toString().replace(".", ""));
		}
	} else {
		arg1 = Number(arg1.toString().replace(".", ""));
		arg2 = Number(arg2.toString().replace(".", ""));
	}
	return (arg1 + arg2) / m;
}
