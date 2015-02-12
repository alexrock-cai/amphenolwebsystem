<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>			
<div class="pageContent" layoutH="0">
				<div class="accordion" style="height:500px;width:210px;float:right;margin:5px;">
					<div class="accordionHeader">
						<h2><span>Folder</span>认证作业指导书</h2>
					</div>
					<div class="accordionContent">
						<!-- 用户信息面板 -->
						
							<table class="list" width="95%" >
								<tr>
									<td colspan="2" align="center">
										<span>WI基本信息</span>
									</td>
								</tr>
								<tr align="center">
									<td>产品：</td>
									<td>${currentWiPn }</td>
								</tr>
								<tr align="center">
									<td>站别：</td>
									<td>${currentWiStation}</td>
								</tr>
								<tr align="center">
									<td>版本：</td>
									<td>${currentWiRev }</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<span>请输入被认证的员工工号</span>
									</td>
								</tr>
								<tr align="center">
									<td>工号：</td>
									<td><input id="empNum" type="text" name="empNum" /></td>
								</tr>
								<tr >
									<td colspan="2" align="center">
										<button id="addUser" type="button">添加员工</button>
										<button id="delUser" type="button">删除员工</button>
									</td>
								</tr>
								<tr >
									<td colspan="2" align="center">
										<select id="selectUser" name="selectUsers" multiple="true" style="width:200px" size="10">
											
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<div id="timer"><font style="line-height:1"><span>认证倒计时:</span><span id="min"></span>:<span id="sec"></span></font></div>
										<button id="certUser" type="button" style="background:yellow" >开始认证</button>
										<button id="certUserEnd" type="button" style="background:green" >认证结束</button>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<span>已经通过认证的员工名单</span>
									</td>
								</tr>
								<tr >
									<td colspan="2" align="center">
										<select id="hasCertUser" name="hasSelectUsers" multiple="true" style="width:200px" size="10">
											<c:forEach items="${hascertedUsers }" var="uinfo">
												<option value="${uinfo }">${uinfo }</option>
											</c:forEach>
										</select>
									</td>
								</tr>
							</table>
					</div>	
						
				</div>
			
		

					<div class="page unitBox" style="width:900px;float:left;margin:5px;">	
						<div id="testPDF"></div>					
					</div>

	
</div>
	

	
	<script >
		//pdf显示模块
		var success = new PDFObject({
									url:"${CONTEXT_PATH}${filepath}",
									height:"500px",
									pdfOpenParams:{
													navpanes:1,
													statusbar:0,
													view:"FitH",
													pagemode:"thumbs"
													}
									}).embed("testPDF");
		$("#timer").hide(); //隐藏倒计时
		$("#certUserEnd").hide();
		var timerc =0;//倒计时初始化
		//倒计时方法
		function certTimer(){ //加时函数
        	if(timerc < 10){ //如果不到5分钟
            	++timerc; //时间变量自增1
            	$("#min").html(parseInt(timerc/60)); //写入分钟数
            	$("#sec").html(Number(parseInt(timerc%60/10)).toString()+(timerc%10)); //写入秒数（两位）
            	setTimeout("certTimer()", 1000); //设置1000毫秒以后执行一次本函数
        	}else{
        		$("#timer").hide();//隐藏计时器
        		$("#certUserEnd").show();//显示提交按钮
        		timerc = 0;//计时器复位
        		
        	}
    	};
		
		//用户认证
		function certUser(){
			
			var count = $("#selectUser option").length;
			for(var i=0;i<count;i++){
				$("#selectUser").get(0).options[i].selected = true;//选中所有添加的员工
			}
			
			$.ajax({
    	   		type:"get",
    	   		url:"${CONTEXT_PATH}/userDccCert/newDccCertificate",
    	   		async:false,
    	   		data:{users:$("#selectUser").val(),currentWiId:${currentWiId}},
    	   		success:function(json){
    	   			$("#selectUser").empty();
    	   			$("#certUserEnd").hide();
    	   			$("#certUser").show();
    	   			 alertMsg.info(json.message);
    	   			 $.each(json.certedUsers,function(i,item){
    	              	 $("#hasCertUser").append("<option value='"+item+"' >"+item+"</option>");
    	               });
    	   		}    	   
       		});
		};
		
		//添加员工按钮绑定事件							
		$("#addUser").click(function(){
			$.ajax({
    	   		type:"get",
    	   		url:"${CONTEXT_PATH}/userDccCert/confirmUser",
    	   		async:false,
    	   		data:{empNum:$("#empNum").attr("value"),currentWiId:${currentWiId}},
    	   		success:function(json){
    	   			if(json.result){
    	   				/* var oSelectUser = document.getElementById("selectUser");
						var oOption = document.createElement("option");
						oOption.setAttribute("value",$("#empNum").attr("value"));
						var oText = document.createTextNode($("#empNum").attr("value"));
						oOption.appendChild(oText);
						oSelectUser.appendChild(oOption); */
						$("#selectUser").append("<option value='"+$('#empNum').attr('value')+"' >"+$('#empNum').attr('value')+"</option>");
						$("#empNum").attr("value","");
    	   				
    	   			}else{
    	   				alertMsg.warn(json.message);
    	   			}
    	   		}    	   
       		});
			
		});
		
		//删除用户绑定事件
		$("#delUser").click(function(){
			var oSelCode = document.getElementById("selectUser");
			var oOption = oSelCode.options[oSelCode.selectedIndex];
			oSelCode.removeChild(oOption);
		});
		
		//开始认证用户绑定事件
		$("#certUser").click(function(){
			if($("#selectUser option").length==0){
				alertMsg.warn("没有添加需要认证的员工工号！");
			}else{
				$("#certUser").hide();
				$("#timer").show();
				certTimer();
			}
			
		});
		
		$("#certUserEnd").click(certUser);
	</script>	
