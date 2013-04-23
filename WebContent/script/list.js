/**
 * @author Administrator
 */
$(function() {
	
	$("#twobtn").hide();
	$("#onebtn").hide();
	$("#success").hide();
	$("#fail").hide();
	$("#box").hide();
	$("#selectborther").hide();
	$("#end").hide();
	$("#mybrother").hide();
	var myname;//我的名字
	var borthername;//选择下一个人
	var selprename;//选择上一个人信息保存
	var prename;//查询上一个人的信息保存
	// 我的信息 组信息
	$.post("StudentServlet.do?method=getGroupAjax", function(data, textStatus) {
		for ( var i = 0; i < data.length; i++) {
			var groupoData = data[i][0];
			var $optionEle = $("<option/>").attr("value", groupoData).text(
					groupoData);
			$("#groupo").append($optionEle);
		}

	}, "json");
	// 我的信息 更新组信息
	$("#groupo").change(
			function() {

				$("#groupt option[value!='']").remove();
				$("#nameo option[value!='']").remove();
				$("#namet option[value!='']").remove();

				var nameo = this.value;
				
				if (nameo != "") {
					$.post("StudentServlet.do?method=getNameAjax", {
						nameo : nameo
					}, function(data, textStatus) {
						for ( var i = 0; i < data.length; i++) {
							var nameoData = data[i][0];
							var $optionElet = $("<option/>")
									.attr("value", data).text(nameoData);
							$("#nameo").append($optionElet);
						}

					}, "json");
				}

			});
	//更新我的	名字信息
	$("#nameo").change(function(){
		$("#onebtn").show();
	});
	// 我的信息 确定
	$("#onebtn").click(
			function() {
				$("#onebtn").hide();
				$("#groupt option[value!='']").remove();
				$("#namet option[value!='']").remove();
				var name = $("#nameo option[selected='selected']").text();
				if (name != "请选择...") {// 判断是否选择了自己的名字
					myname = name;//传递给最后更新请求
					$.post("StudentServlet.do?method=getTwoGroupAjax",{Nextname : myname},
							function(data, textStatus) {
							prename = data[data.length-1];
							if(prename == "1"){
								alert("选择错误");
							}else{
								for ( var i = 0; i < data.length-1; i++) {
									var groupoData = data[i][0];
									var $optionEle = $("<option/>").attr(
											"value", groupoData).text(
											groupoData);
									$("#groupt").append($optionEle);
								}
								$("#mybrother").show();
								//请求插入一个条记录传递我的名字
								  $.post("StudentServlet.do?method=insterrecotdAjax",{name:myname}, function(data, textStatus) {
									
								  }, "json");
							}
							}, "json");
				} else {
					$("#groupt option[value!='']").remove();
					$("#namet option[value!='']").remove();
				}
			});
	// 更给兄弟 组信息
	$("#groupt").change(
			function() {

				$("#namet option[value!='']").remove();
				var nameo = this.value;
				if (nameo != "") {
					$.post("StudentServlet.do?method=getNameAjax", {
						nameo : nameo
					}, function(data, textStatus) {
						for ( var i = 0; i < data.length; i++) {
							var nameoData = data[i][0];
							var $optionElet = $("<option/>")
									.attr("value", data).text(nameoData);
							$("#namet").append($optionElet);
						}

					}, "json");
				}

			});
	//更新兄弟	名字信息
	$("#namet").change(function(){
		$("#twobtn").show();
	});
	
	
	var mark = false;
	var num = 0;
	// 兄弟信息 确定
	$("#twobtn").click(
			  function() {
				  selprename = $("#namet option[selected='selected']").text();
					  if(num != 1){//判断是否两次不通过
						  if(confirm("你确定吗(⊙_⊙)？")){
							  if(prename == selprename){
								  mark = true;
							  }else{
								  num++;
								  $("#twobtn").hide();
								  alert("还是好好想想吧！");
							  }
							  if(mark != true){//判断是否通过
							  }else{//通过
								  	//发送更新请求
									$.post("StudentServlet.do?method=updatarecotdPreAjax",{name:myname,peoplename:selprename}, function(data, textStatus) {
									}, "json");
								  
								  $("#message").hide();
								  $("#success").show();
								  $("#box").show();
								  getBox();
							  }
						  }
					  }else{//两次错误后
						  $("#message").hide();
							$("#fail").show();
					  }
							  
							  
//							  $.post("StudentServlet.do?method=getRecordAjax", {
//								  name : $("#nameo option[selected='selected']").text(),
//								  prename : $("#namet option[selected='selected']").text()
//							  }, function(data, textStatus) {
//								  if(data[0] == 1){
//									  mark = true;
//								  }else{
//									  num++;
//									  $("#twobtn").hide();
//									  alert("还是好好想想吧！");
//								  }
//								  if(mark != true){//判断是否通过
//								  }else{//通过
//									  $("#message").hide();
//									  $("#success").show();
//									  $("#box").show();
//									  getBox();
//								  }
//							  }, "json");
//						  }
//					  }else{//两次错误后
//						  $("#message").hide();
//							$("#fail").show();
//					  }
				  
			});
	//一切随缘
	$("#raselect").click(function(){
		$.post("StudentServlet.do?method=getRondomStudentAjax", function(data, textStatus) {
			borthername = data.name;
			$("#selectname").empty();
				$("#selectborther").show();
				$("#selectname").append("<font color='red'>"+borthername+"</font>");
				//$("tr:has(input[id='"+borthername+"'])").addClass("alt");//高亮显示不行
		}, "json");
	});
	//{"address":"山东临沂","email":"awydss@gmail.com","groupname":"佣兵天下","id":1,"name":"薛庆彬","phone":"18669998655","qq":"356864195","state":0}
	//box中的数据请求
	function getBox(){
		$.post("StudentServlet.do?method=getBoxAjax", function(data, textStatus) {
			for ( var i = 0; i < data.length; i++) {
				$("<tr/>").appendTo($("#showstudent")).append("<td class='box'>"+data[i].name+"</td>")
													  .append("<td class='box'>"+data[i].groupname+"</td>")
													  .append("<td class='box'>"+data[i].address+"</td>")
													  .append("<td class='box'><input id="+data[i].name+" name='newselect' type='button' value='这人好'/></td>");
			}
			//发送更新请求
			$("input[name='newselect']").click(function(){
				$("#selectname").empty();
				borthername = this.id;
					$("#selectborther").show();
					$("#selectname").append("<font color='red'>"+borthername+"</font>");
					location.href ="#selectborther";
					//$("tr:has(input[id='"+borthername+"'])").addClass("alt");//高亮显示不行
			});
		}, "json");
	}
	//点击选中兄弟确定
	$("#updatebtn").click(function(){
		//发送更新请求
		$.post("StudentServlet.do?method=updatarecotdAjax",{name:myname,prename:borthername}, function(data, textStatus) {
			alert("已经提交！");
			$("#success").hide();
			$("#box").hide();
			$("#end").show();
			var mesaage = myname+"分享的太榜了！大家鼓掌！";
			$("#end").append("<font color='red'>"+mesaage+"</font>");
		}, "json");
	});
	
});
