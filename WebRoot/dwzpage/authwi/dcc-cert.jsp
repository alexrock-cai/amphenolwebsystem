<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	<form method="post" action="${CONTEXT_PATH}/userDccCert/dccCertificate" class="pageForm required-validate"  onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			
			<p>
				<label>工号：</label>
				<input type="text" name="username" class="required" />
			</p>
			<p>
				<label>认证站别：</label>
			
				<select name="station" style="width:150px">
						<option value="none" >请选择站别</option>
					<c:forEach items="${station }" var="s">
						<option value="${s.station }">${s.discription }</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>认证产品：</label>
				<input type="text" name="pn" class="required" />
			</p>
			<p>
				
				<input type="hidden" name="validity" class="required digits" value="0" alt="有效期只能是整数"/>
				
			</p>
			
		
		</div>
		
		<div class="formBar">
			<ul>
				<li id="timer"><div><font style="line-height:2"><span id="min"></span>:<span id="sec"></span></font></div></li>
				<!--  <li><a class="buttonActive" href="javascript:submitDccCert();"><span>保存</span></a></li> -->
				<li><div id="certSubmitButton" class="buttonActive"><div class="buttonContent"><button type="submit" >提交</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#certSubmitButton").hide();//隐藏提交按钮
	 var timerc=0; //全局时间变量（秒数）
    function add(){ //加时函数
        if(timerc < 10){ //如果不到5分钟
            ++timerc; //时间变量自增1
            $("#min").html(parseInt(timerc/60)); //写入分钟数
            $("#sec").html(Number(parseInt(timerc%60/10)).toString()+(timerc%10)); //写入秒数（两位）
            setTimeout("add()", 1000); //设置1000毫秒以后执行一次本函数
        }else{
        	$("#timer").hide();//隐藏计时器
        	$("#certSubmitButton").show();//显示提交按钮
        	
        }
    };

		add();
	
</script>
