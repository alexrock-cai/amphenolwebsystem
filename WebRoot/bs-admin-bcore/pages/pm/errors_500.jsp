<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

 <!-- BEGIN HEAD -->
<head>
    <meta charset="UTF-8" />
    <title>Amphenol GIS Web System |Error 500</title>
     <meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
     <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
    <!-- GLOBAL STYLES -->
  <!-- GLOBAL STYLES -->
    <link rel="stylesheet" href="/bs-admin-bcore/template/assets/plugins/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="/bs-admin-bcore/template/assets/plugins/Font-Awesome/css/font-awesome.css" />
    <!--END GLOBAL STYLES -->

     <!-- PAGE LEVEL STYLES -->
    <link rel="stylesheet" href="/bs-admin-bcore/template/assets/css/error.css" />
    <link rel="stylesheet" href="/bs-admin-bcore/template/assets/lib/magic/magic.css" />    
      <!--END PAGE LEVEL STYLES -->
   <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="/bs-admin-bcore/template/assets/js/html5shiv.min.js"></script>
      <script src="/bs-admin-bcore/template/assets/js/respond.min.js"></script>
    <![endif]-->
</head>
     <!-- END HEAD -->
     <!-- BEGIN BODY -->
<body >
       <!--  PAGE CONTENT --> 
  <div class="container">
        <div class="col-lg-8 col-lg-offset-2 text-center">
	  <div class="logo">
	    <h1>Error 500 !</h1>
          </div>
          <p class="lead text-muted"> Oops, an error has occurred. ${msg}</p>
          <div class="clearfix"></div>
                <div class="col-lg-6 col-lg-offset-3">
                    <form action="/pm/index">
                    
                    <div class="input-group">
		      <input type="text" placeholder="search .." class="form-control" />
		      <span class="input-group-btn">
			<button class="btn btn-primary" type="button"><i class="icon-search"></i></button>
		      </span>
		    </div>
		    
                    </form>
                </div>
            <div class="clearfix"></div>
            <br />
                <div class="col-lg-6  col-lg-offset-3">
		  <div class="btn-group btn-group-justified">
		      <a href="/pm/index" class="btn btn-primary">返回首页</a>
                      <a href="${href}" class="btn btn-success">返回上一页</a>
		  </div>
                        
                </div>
        </div>
                
        </div>

	      
	    <!-- END  PAGE CONTENT -->      


</body>
     <!-- END BODY -->
</html>
