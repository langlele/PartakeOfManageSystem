<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/commons/common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息查看</title>
</head>
<body>
	<div class="head" align="center">
		<table>
			<tr>
				<td><font>信息查看</font></td>
			</tr>
		</table>
	</div>
	<table>
		<tr>
			<td>姓名</td>
			<td>上一个兄弟</td>
			<td>下一个兄弟</td>
			<td>时间</td>
		</tr>
		<c:forEach items="${list }" var="sharerecorder">
			<td>${sharerecorder.name }</td>
			<td>${sharerecorder.preName }</td>
			<td>${sharerecorder.nextName }</td>
			<td>${sharerecorder.shareTime }</td>
		</c:forEach>
	</table>
</body>
</html>