<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath();
    request.setAttribute("ContextPath",path);
%>
<script>
function reload(){
	window.location.href="${ContextPath}/login/login.jsp";
}
</script>

<body onload="reload()">

</body>
