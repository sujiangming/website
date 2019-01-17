String.prototype.endWith = function(oString) {
	var reg = new RegExp(oString + "$");
	return reg.test(this);
};

/*******************************************************************************
 * 
 * cookies 部分
 */

var tcCore = {};


tcCore.guidGenerator = function() {

    var S4 = function() {

       return (((1+Math.random())*0x10000)|0).toString(16).substring(1);

    };

    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());

};

tcCore.corePageHead = $("head");

tcCore.cookie = function(name) {

	var cookieArray = document.cookie.split("; "); // 得到分割的cookie名值对

	for ( var i = 0; i < cookieArray.length; i++) {

		var arr = cookieArray[i].split("="); // 将名和值分开

		if (arr[0] == name)
			return unescape(arr[1]); // 如果是指定的cookie，则返回它的值

	}

	return "";

};

tcCore.getCookie = function(objName) {// 获取指定名称的cookie的值

	var arrStr = document.cookie.split("; ");

	for ( var i = 0; i < arrStr.length; i++) {

		var temp = arrStr[i].split("=");

		if (temp[0] == objName)
			return unescape(temp[1]);

	}

};

tcCore.addCookie = function(objName, objValue, objHours) { // 添加cookie

	var str = objName + "=" + escape(objValue);

	if (objHours > 0) { // 为时不设定过期时间，浏览器关闭时cookie自动消失

		var date = new Date();

		var ms = objHours * 3600 * 1000;

		date.setTime(date.getTime() + ms);

		str += "; expires=" + date.toGMTString();

	}

	document.cookie = str;

};

tcCore.setCookie = function(name, value)// 两个参数，一个是cookie的名子，一个是值

{

	var Days = 30; // 此 cookie 将被保存 30 天

	var exp = new Date(); // new Date("December 31, 9998");

	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);

	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString();

};

tcCore.delCookie = function(name)// 删除cookie

{

	var exp = new Date();

	exp.setTime(exp.getTime() - 1);

	var cval = tcCore.getCookie(name);

	if (cval != null)
		document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();

};


tcCore.addMeta = function(metaStr) {
	$(metaStr).insertAfter($("head").children(":first"));
};

tcCore.loadCss = function(cssurl, id) {
	tcCore.corePageHead.append("<link id='" + id + "'  rel='stylesheet' href='"
			+ cssurl + "' />");

};

tcCore.loadJs = function(jsurl, id) {
	tcCore.corePageHead.append("<script id='" + id + "'   src='" + jsurl
			+ "'></script>");
};

tcCore.loadShortIco = function(shourIco, id) {
	tcCore.corePageHead.append("<link id='" + id
			+ "'  rel='shortcut icon' type='image/x-icon' href='" + shourIco
			+ "' />");

	tcCore.corePageHead
			.append("<link rel='bookmark' type='image/x-icon' href='"
					+ shourIco + "' />");
};

tcCore.loadTitle = function(title) {
	tcCore.corePageHead.append("<title>"+ title +"</title>");
};

tcCore.copy = function(obj) {
	if(obj){
		var str = JSON.stringify(obj);
		return  JSON.parse(str);
	}
	return null;
	//$(metaStr).insertAfter($("head").children(":first"));
};

/*******************************************************************************
 * 
 */
tcCore.ajax = function(ajaxObj) {
	if (ajaxObj.data != null && typeof (ajaxObj.data) == "object") {
		ajaxObj.data = JSON.stringify(ajaxObj.data);
	}
	ajaxObj.contentType = 'application/json;charset=UTF-8';
	ajaxObj.dataType = ajaxObj.dataType||'json';
	ajaxObj.async = true;
	if (typeof (ajaxObj.error) != "function") {
		ajaxObj.error = tcCore.ajaxErrorFunction;
	}
	var context =  $url(">");
	if (ajaxObj.url.indexOf(context) != 0) {
		ajaxObj.url =context  + ajaxObj.url;
	}
	$.ajax(ajaxObj);
};

tcCore.post = function(ajaxObj) {
	if (typeof (ajaxObj.data) == "object") {
		ajaxObj.data = JSON.stringify(ajaxObj.data);
	}
	ajaxObj.type = 'POST';

	tcCore.ajax(ajaxObj);
};

tcCore.get = function(ajaxObj) {
	if (typeof (ajaxObj.data) == "object") {
		ajaxObj.data = JSON.stringify(ajaxObj.data);
	}
	ajaxObj.type = 'GET';
	tcCore.ajax(ajaxObj);
};

tcCore.postjsonp = function(ajaxObj) {
	if (typeof (ajaxObj.data) == "object") {
		ajaxObj.data = JSON.stringify(ajaxObj.data);
	}
	ajaxObj.type = 'POST';
	ajaxObj.dataType = "jsonp";
	tcCore.ajax(ajaxObj);
};

tcCore.getjsonp = function(ajaxObj) {
	if (typeof (ajaxObj.data) == "object") {
		ajaxObj.data = JSON.stringify(ajaxObj.data);
	}
	ajaxObj.type = 'GET';
	ajaxObj.dataType = "jsonp";
	tcCore.ajax(ajaxObj);
};

tcCore.ajaxErrorFunction = function(e) {
	alert("操作失败" + e);
};

tcCore.getContext = function() {
	if (view == null || view.get("context") == null
			|| view.get("context").get("contextPath") == null) {
		contextPath = top.view.get("context").get("contextPath");
	} else {
		contextPath = view.get("context").get("contextPath");
	}
	return contextPath;
};

tcCore.getFullContext = function() {
	if (view == null || view.get("context") == null
			|| view.get("context").get("contextPath") == null) {
		contextPath = top.view.get("context").get("contextPath");
	} else {
		contextPath = view.get("context").get("contextPath");
	}
	var href = 	window.location.href;
	if(href.indexOf(contextPath)>=0){
		return href.substring(0,(href.indexOf(contextPath)+contextPath.length));
	}
	return contextPath;
};

/*******************************************************************************
 * 时间格式化
 */
Date.prototype.format = function(format) {
	format = format || "yyyy-MM-dd hh:mm:ss";
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format.substr(0, format.length);
};

tcCore.easyUiDateTimeBox = {};
tcCore.easyUiDateTimeBox.formatDateText = function(date) {
	return date.format("yyyy-MM-dd hh:mm:ss");
};

tcCore.easyUiDateTimeBox.parseDate = function(dateStr) {
	if (dateStr == null || dateStr == "") {
		return new Date();
	}
	var regexDT = /(\d{4})-?(\d{2})?-?(\d{2})?\s?(\d{2})?:?(\d{2})?:?(\d{2})?/g;
	var matchs = regexDT.exec(dateStr);
	var date = new Array();
	for ( var i = 1; i < matchs.length; i++) {
		if (matchs[i] != undefined) {
			date[i] = matchs[i];
		} else {
			if (i <= 3) {
				date[i] = '01';
			} else {
				date[i] = '00';
			}
		}
	}
	return new Date(date[1], date[2] - 1, date[3], date[4], date[5], date[6]);
};

tcCore.dayBefore = function(d1, d2) {
	return (d1.format("yyyyMMdd")) < (d2.format("yyyyMMdd"));
};

tcCore.getParameter = function(paras) {
	var url = location.href;
	var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
	var paraObj = {};
	for ( var i = 0; j = paraString[i]; i++) {
		paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j
				.indexOf("=") + 1, j.length);
	}
	var returnValue = paraObj[paras.toLowerCase()];
	if (typeof (returnValue) == "undefined") {
		return "";
	} else {
		return returnValue;
	}
};

tcCore.isStringEmpty = function(str) {
	return str == null || str == "" || str == undefined;
};

//js获取项目根路径，如： http://localhost:8083/uimcardprj
tcCore.getRootPath = function(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

/*******************************************************************************
 * window方法绑定
 */
window.openDoradoDialog = function(cDialogObj) {
	// 开发过程中发现dialog的几个居中方法都失效，尝试自己计算屏幕中间位置 百分比的情况没有处理 后续有时间补上
	var newDialogObj = $.extend({}, window.dialogObj, cDialogObj);
	var screenWidth = screen.width;
	var screenHeight = screen.height;
	var left = (screenWidth - newDialogObj.width) / 2;
	var top = (screenHeight - newDialogObj.height) / 2 - 80;
	newDialogObj.left = left;
	newDialogObj.top = top;
	newDialogObj.onHide = function(self, arg) {
		if (cDialogObj.onHide) {
			cDialogObj.onHide.call(this, self, arg, self.get("userData"));
		}
		top.topDialogParma = null;
	};
	var dialog = new dorado.widget.Dialog(newDialogObj);
	dialog.show();
	var dialogId = dialog._id;
	var src = "";
	if (window != window.top) {
		src = window.top.getMCFUrl(newDialogObj.src);
	} else {
		src = getMCFUrl(newDialogObj.src);
	}
	if (src.indexOf("?") > 0) {
		src = src + "&dialogId=" + dialogId;
	} else {
		src = src + "?dialogId=" + dialogId;
	}
	dialog.getContentContainer().innerHTML = "<iframe src='"
			+ src
			+ "' style='width:100%;height:99%' frameborder='no' border='0' ></iframe>";
	return dialog;
};

window.openDialogOnTop = function(dialogObj, params) {
	var dialog = top.openDoradoDialog(dialogObj);
	if (!top.topDialogMap) {
		top.topDialogMap = {};
	}
	if (!top.topDialogParamsMap) {
		top.topDialogParamsMap = {};
	}
	top.topDialogMap[dialog._id] = dialog;
	top.topDialogParamsMap[dialog._id] = params;
};

window.closeDialogOnTop = function(param) {
	var dialogId = window.tcCore.getParameter("dialogId");
	var dialog = top.topDialogMap[dialogId];
	if (top && top.topDialogMap && dialog) {
		dialog.set("userData", param);
		top.topDialogMap[dialog._id] = null;
		top.topDialogParamsMap[dialog._id] = null;
		dialog.close();
	}
};

window.getDialogParams = function() {
	var dialogId = window.tcCore.getParameter("dialogId");
	var dialog = top.topDialogMap[dialogId];
	if (top && top.topDialogParamsMap && dialog) {
		return top.topDialogParamsMap[dialogId];
	}
	return null;
};
window.dialogObj = {
	caption : '基本信息',
	showAnimateType : "none",
	align : "right",
	vAlign : "bottom",
	autoAdjustPosition : false,
	width : 300,
	height : 300,
	left : 200,
	top : 50
};

function getMCFUrl(url) {
	// 如果不以http开头，增加context
	if (url.indexOf("http://") == 0 || url.indexOf("https://") == 0) {
		return url;
	} else if (url.indexOf("www.") == 0) {
		return "http://" + url;
	} else {
		if (url.indexOf("/") != 0) {
			// 如果没有以'/'开始,加一个'/'
			url = '/' + url;
		}
		return $url(">"+url);
	}

}

function setOperationsUseable(view) {
	var operationListStr = view.get("context").get("authorize_operation_list");
	var control = null;

	if (operationListStr != null && operationListStr != "") {
		var operationList = operationListStr.split(";");
		$(operationList).each(function() {
			control = view.id(this);
			if (control) {
				control.set({
					visible : true
				});
			}
		});
	}
}

window.openImageSelector = function(block, callback){
	
	var title = '图片选择器';
	var parameter = '';
	if(block){
		if(block.title){
			title = block.title;
		}
		var isFavicon = block.isFavicon;
		var pageSize = block.pageSize;
		var blockWidth = block.blockWidth;
		var blockHeight = block.blockHeight;
		var lineSize = block.lineSize;
		
		var sysTitle = block.currentSystemTitle;
		var currentLogoPath = block.currentLogoPath;
		var currentFaviconPath = block.currentFaviconPath;
		
		parameter = '?isFavicon='+ isFavicon +'&pageSize='+ pageSize +'&lineSize='+ lineSize 
					+'&blockWidth='+ blockWidth +'&blockHeight='+ blockHeight +'&currentSystemTitle='+ sysTitle 
					+'&currentLogoPath='+ currentLogoPath +'&currentFaviconPath='+ currentFaviconPath;
	}
	window.openDialogOnTop({
		caption : title,
		width : 800,
		height : 450,
		src : 'com/gs/mcf/controls/upload/imageselector/imageSelector.d' + parameter,
		onHide: function(self, arg, data){
			if(callback){
				// 把当前this传递给它，改变运行作用域
				callback.call(this, data);
			}
		}
	});
};

// var flag = window.setInterval(function() {
// if($("#viewMain").objects[0]){
// window.clearInterval(flag);
// setOperationsUseable(view);
// }
// }, 50);
