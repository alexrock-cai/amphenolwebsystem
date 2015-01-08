<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html class="bg-black">
    <head>
        <meta charset="UTF-8">
        <title>Amphenol GIS PM Monitor System | Log in</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link rel="stylesheet" href="/bs-admin-bcore/template/assets/plugins/bootstrap/css/bootstrap.css" />
        <!-- font Awesome -->
        <link rel="stylesheet" href="/bs-admin-bcore/template/assets/plugins/Font-Awesome/css/font-awesome.css" />
        <!-- Theme style -->
       <link rel="stylesheet" href="/bs-admin-bcore/template/assets/css/AdminLTE.css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="/bs-admin-bcore/template/assets/js/html5shiv.min.js"></script>
      	  <script src="/bs-admin-bcore/template/assets/js/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="bg-black">

        <div class="form-box" id="login-box">
        	<div class="margin text-center">
                <span id="msg" >${msg }</span>
            </div>
            <div class="header">PM 监控系统登录</div>
            <form id="loginform" action="${CONTEXT_PATH}/pmLogin" method="post">
                <div class="body bg-gray">
                    <div class="form-group">
                        <input id="name" type="text" name="username" class="form-control" placeholder="User ID"/>
                    </div>
                    <div class="form-group">
                        <input id="pwd" type="password" name="password" class="form-control" placeholder="Password"/>
                    </div>          
                    <div class="form-group">
                        <input type="checkbox" name="remember_me"/> Remember me
                    </div>
                </div>
                <div class="footer">                                                               
                    <button id="btn_submit" type="button" class="btn bg-olive btn-block">登 录</button>  
                    
                    <p><a href="#">忘记密码</a></p>
                    
                    
                </div>
            </form>

            
        </div>


        <!-- jQuery 2.0.2 -->
        <script src="/bs-admin-bcore/template/assets/plugins/jquery-2.0.3.min.js"></script>
    
        <!-- Bootstrap -->
        <script src="/bs-admin-bcore/template/assets/plugins/bootstrap/js/bootstrap.min.js"></script>     
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
          
          $("#name,#pwd").keydown(function(e){
          			
          			if(e.which==13){
          				$("#loginform").submit();
          			}
          }); 
           $("#btn_submit").click(function(){
                   $("#loginform").submit();
          }); 

});
</script>
    </body>
</html>