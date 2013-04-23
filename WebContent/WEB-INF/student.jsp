<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/commons/common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/list.js"></script>
<title>分享管理系统</title>
</head>
<body>
	<div class="head" align="center">
		<table>
			<tr>
				<td><font>分享管理系统</font></td>
			</tr>
		</table>
	</div>
	<table align="center">
		<tr>
			<td>
				<div id="success" class="dashed" align="center">
					<font color="red">恭喜你选择正确</font>
				</div>
				<div id="fail" class="dashed" align="center">
					<font color="red">伤不起呀！伤不起！你错了！</font>
				</div>
				<div id="end" class="dashed" align="center">
				
				</div>
				<div id="message" class="dashed" align="center">
					<table class="heid" align="center">
						<tr>
							<td><font>我的信息:</font></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td><select id="groupo">
									<option value="">请选择...</option>
							</select> <font>组 </font></td>
							<td><font>姓名:</font> <select id="nameo">
									<option value="">请选择...</option>
							</select></td>
							<td><input id="onebtn" type="button" value="确定" /></td>
						</tr>
					</table>
					<div id="mybrother">
						<table class="heid" align="center">
							<tr>
								<td><font>我的兄弟:</font></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td><select id="groupt">
										<option value="">请选择...</option>
								</select> <font>组 </font></td>
								<td><font>姓名: </font> <select id="namet">
										<option value="">请选择...</option>
								</select></td>
								<td><input id="twobtn" type="button" value="确定" /></td>
							</tr>
						</table>
					</div>
				</div>
			</td>
		</tr>
	</table>

	<div class="line"></div>
	<div id="box" class="box" align="center">
		<table>
			<tr>
				<td>
					<div class="dashed" id="selectborther">
						<font>我选中的兄弟:</font>
						<table align="center">
							<tr>
								<td>
									<div id="selectname"></div>
								</td>
								<td>
									<input id="updatebtn" type="button" value="确定" />
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<input id="raselect" type="button" value="一切随缘" />
				</td>
			</tr>
		</table>
		<div>
			<table id="showstudent" class="box" border="1" cellspacing="0">
				<tr>
					<td class="box"><font>姓名</font></td>
					<td class="box"><font>组</font></td>
					<td class="box"><font>地址</font></td>
					<td class="box"><font>操作</font></td>
				</tr>
			</table>
		</div>
		<table align="right">
			<tr>
				<td>
					<a href="${pageContext.request.contextPath }/ShareRecorderServlet?method=getAll">查看信息</a>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>