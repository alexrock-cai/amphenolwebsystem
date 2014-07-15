<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

<title>用户登录</title>
<link href="${CONTEXT_PATH}/pages/commons/images/login.css" rel="stylesheet" type="text/css" />
<script  src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.3.js" type="text/javascript" ></script>
<script type="text/javascript">

$(document).ready(function(){
          $("#loginform").submit(function(e){
                if($("#name").val()=="") {
                     $("#msg").html("用户名不能为空");
                     $("#name").focus();
                     return false;
                }

                if($("#pwd").val() == "") {
                        $("#msg").html("密码不能为空");
                        $("#pwd").focus();
                        return false;
                 } 

          });
           $("#btn_submit").click(function(){
                   $("#loginform").submit();
          }); 

});
</script>
</head>
<body>

    <div id="login">
	
	     <div id="top">
		      <div id="top_left"><img src="${CONTEXT_PATH}/pages/commons/images/login_03.gif" /></div>
			  <div id="top_center"></div>
		 </div>
		 
		 <div id="center">
		      <div id="center_left"></div>
			  <div id="center_middle">
			  	
			  	<form action="/login" method="post" id="loginform">
			  	<div id="msg"> ${msg}</div>
			       <div id="user">用 户
			         <input id="name" type="text" name="username" />
			       </div>
			       <span id="msg"></span>
				   <div id="password">密   码
				     <input id="pwd" type="password" name="password" />
				   </div>
				   <div id="btn">                          
				     <input id="btn_submit" value="登录" class="logbtn" type="button" />
				     <input id="btn_reset" value="重置" class="logbtn"  type="reset" />
				 </div>
			  	</form>	 

			  </div>
			  <div id="center_right"></div>		 
		 </div>
		 <div id="down">
		      <div id="down_left">
			      <div id="inf">
                       <span class="inf_text">版本信息</span>
					   <span class="copyright">Amphenol-GIS 生产管理信息系统 2014 v1.0</span>
			      </div>
			  </div>
			  <div id="down_center"></div>		 
		 </div>

	</div>
</body>
</html>
