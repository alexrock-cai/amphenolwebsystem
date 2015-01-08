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
    <title>BCORE Admin Dashboard Template | Dashboard </title>
     <meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
     <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
    <!-- GLOBAL STYLES -->
    <link rel="stylesheet" href="/bs-admin-bcore/template/assets/plugins/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="/bs-admin-bcore/template/assets/css/main.css" />
    <link rel="stylesheet" href="/bs-admin-bcore/template/assets/css/theme.css" />
    <link rel="stylesheet" href="/bs-admin-bcore/template/assets/css/MoneAdmin.css" />
    <link rel="stylesheet" href="/bs-admin-bcore/template/assets/plugins/Font-Awesome/css/font-awesome.css" />
    <!--END GLOBAL STYLES -->

    <!-- PAGE LEVEL STYLES -->
    <link href="/bs-admin-bcore/template/assets/css/layout2.css" rel="stylesheet" />
       <link href="/bs-admin-bcore/template/assets/plugins/flot/examples/examples.css" rel="stylesheet" />
       <link rel="stylesheet" href="/bs-admin-bcore/template/assets/plugins/timeline/timeline.css" />
       <link rel="stylesheet" href="/bs-admin-bcore/template/assets/plugins/jquery.bootgrid-1.1.2/jquery.bootgrid.css"/>
       
       <link rel="stylesheet" href="/bs-admin-bcore/template/assets/plugins/fullcalendar-1.6.2/fullcalendar/fullcalendar.css" />
       <link rel="stylesheet" href="/bs-admin-bcore/template/assets/css/calendar.css" />
    <!-- END PAGE LEVEL  STYLES -->
     <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

    <!-- END HEAD -->

    <!-- BEGIN BODY -->
<body class="padTop53 " >

    <!-- MAIN WRAPPER -->
    <div id="wrap" >
        

        <!-- HEADER SECTION -->
        <div id="top">

            <nav class="navbar navbar-inverse navbar-fixed-top " style="padding-top: 10px;">
                <a data-original-title="Show/Hide Menu" data-placement="bottom" data-tooltip="tooltip" class="accordion-toggle btn btn-primary btn-sm visible-xs" data-toggle="collapse" href="#menu" id="menu-toggle">
                    <i class="icon-align-justify"></i>
                </a>
                <!-- LOGO SECTION -->
                <header class="navbar-header">

                    <a href="/pm/index" class="navbar-brand">
                    <img src="/bs-admin-bcore/template/assets/img/logo.png" alt="" />
                        
                        </a>
                </header>
                <!-- END LOGO SECTION -->
                <ul class="nav navbar-top-links navbar-right">

                    <!-- MESSAGES SECTION -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="label label-success">0</span>    <i class="icon-envelope-alt"></i>&nbsp; <i class="icon-chevron-down"></i>
                        </a>

                        <ul class="dropdown-menu dropdown-messages">
                        
                            <li>
                                <a class="text-center" href="#">
                                    <strong>Read All Messages</strong>
                                    <i class="icon-angle-right"></i>
                                </a>
                            </li>
                        </ul>

                    </li>
                    <!--END MESSAGES SECTION -->

                    <!--TASK SECTION -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="label label-danger">0</span>   <i class="icon-tasks"></i>&nbsp; <i class="icon-chevron-down"></i>
                        </a>

                        <ul class="dropdown-menu dropdown-tasks">
                            
                            <li>
                                <a class="text-center" href="#">
                                    <strong>See All Tasks</strong>
                                    <i class="icon-angle-right"></i>
                                </a>
                            </li>
                        </ul>

                    </li>
                    <!--END TASK SECTION -->

                    <!--ALERTS SECTION -->
                    <li class="chat-panel dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="label label-info">0</span>   <i class="icon-comments"></i>&nbsp; <i class="icon-chevron-down"></i>
                        </a>

                        <ul class="dropdown-menu dropdown-alerts">

                            <li>
                                <a class="text-center" href="#">
                                    <strong>See All Alerts</strong>
                                    <i class="icon-angle-right"></i>
                                </a>
                            </li>
                        </ul>

                    </li>
                    <!-- END ALERTS SECTION -->

                    <!--ADMIN SETTINGS SECTIONS -->

                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="icon-user "></i>&nbsp; <i class="icon-chevron-down "></i>
                        </a>

                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="#"><i class="icon-user"></i> User Profile </a>
                            </li>
                            <li><a href="#"><i class="icon-gear"></i> Settings </a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="/pm/login"><i class="icon-signout"></i> Logout </a>
                            </li>
                        </ul>

                    </li>
                    <!--END ADMIN SETTINGS -->
                </ul>

            </nav>

        </div>
        <!-- END HEADER SECTION -->



        <!-- MENU SECTION -->
       <div id="left" >
       		<!-- 用户登录信息 BEGIN -->
            <div class="media user-media well-small">
                <a class="user-link" href="#">
                    <img class="media-object img-thumbnail user-img" alt="User Picture" src="/bs-admin-bcore/template/assets/img/user_old.gif" />
                </a>
                <br />
                <div class="media-body">
                    <h5 class="media-heading"> Root</h5>
                    <ul class="list-unstyled user-info">
                        
                        <li>
                             <a class="btn btn-success btn-xs btn-circle" style="width: 10px;height: 12px;"></a> Online
                           
                        </li>
                       
                    </ul>
                </div>
                <br />
            </div>
			<!-- 用户登录信息 END -->
			
			<!-- 菜单栏列表 BEGIN -->
            <ul id="menu" class="collapse">

                
                <li class="panel active">
                    <a href="/pm/index" >
                        <i class="icon-table"></i> 首页    
                    </a>                   
                </li>



                <li class="panel ">
                    <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle" data-target="#equipment-pm-nav">
                        <i class="icon-tasks"> </i> 设备PM监控      
	   
                        <span class="pull-right">
                          <i class="icon-angle-left"></i>
                        </span>
                      
                    </a>
                    <ul class="collapse" id="equipment-pm-nav">
                       
                        <li class=""><a href="button.html"><i class="icon-angle-right"></i> 设备PM清单 </a></li>
                         <li class=""><a href="icon.html"><i class="icon-angle-right"></i> 月度PM计划 </a></li>
                        <li class=""><a href="progress.html"><i class="icon-angle-right"></i> 季度PM计划 </a></li>
                        <li class=""><a href="tabs_panels.html"><i class="icon-angle-right"></i> 年度PM计划 </a></li>
                        <li class=""><a href="notifications.html"><i class="icon-angle-right"></i> 邮件报警设定 </a></li>
                         <li class=""><a href="more_notifications.html"><i class="icon-angle-right"></i> 上传记录表单 </a></li>
                        
                    </ul>
                </li>
                
            </ul>
			<!-- 菜单栏列表 END -->
        </div>
        <!--END MENU SECTION -->



        <!--PAGE CONTENT -->
        <div id="content">
             
            <div class="inner">
                <div class="row">
                    <div class="col-lg-12">


                        <h2> Full Calendar </h2>



                    </div>
                </div>

                <hr />
                <div class="row">
                    <div class="col-lg-12">

                		<div class="box">
  							<header>
      							<h5>Calendar</h5>
  							</header>
  						<div class="body">
      						<div class="row">
	  							<div class="col-lg-3">
	      							<div class="well well-small">
		  								<div id="add-event-form">
		      								<fieldset>
			  									<legend>Add Custom Event</legend>
			  									<span class="help-block">Event Title</span>
			  									<input id="title" name="title" type="text" placeholder="event title" class="form-control input-small">
			  									<label class="radio">
			      									<input type="radio" name="priority" value="label label-default" checked>
			      									<span class="label label-default">default</span>
			  									</label>
			  									<label class="radio">
			      									<input type="radio" name="priority" value="label label-warning">
			      									<span class="label label-warning">warning</span>
			  									</label>
			  									<label class="radio">
			      									<input type="radio" name="priority" value="label label-success">
			      									<span class="label label-success">success</span>
			  									</label>
			  									<label class="radio">
			      									<input type="radio" name="priority" value="label label-info">
			      									<span class="label label-info">info</span>
			  									</label>
			  									<label class="radio">
			      									<input type="radio" name="priority" value="label label-danger">
			      									<span class="label label-danger">danger</span>
			  									</label>
			  									<br>
			  									<button id="add-event" type="button" class="btn btn-sm btn-default">Add Event</button>
		      								</fieldset>
		  								</div>

	      							</div>
	      							<div class="well well-small">
		  								<h4>Draggable Events</h4>
		  								<ul id='external-events' class="list-inline">
		      								<li class="external-event"><span class="label label-default">My Event 1</span></li>
		      								<li class="external-event"><span class="label label-danger">My Event 2</span></li>
		      								<li class="external-event"><span class="label label-success">My Event 3</span></li>
		      								<li class="external-event"><span class="label label-warning">My Event 4</span></li>
		      								<li class="external-event"><span class="label label-info">My Event 5</span></li>
		  								</ul>

		  								<label class="checkbox inline" for='drop-remove'>
		      								<input type="checkbox" id="drop-remove">
		      									remove after drop
		  								</label>
	      							</div>
	  							</div>
	  						<div id="calendar" class="col-lg-9"></div>
      					</div>
  					</div>


            	</div>

			</div>
          </div>
        </div>



      </div>
    <!--END PAGE CONTENT -->

   </div>

    <!--END MAIN WRAPPER -->

    <!-- FOOTER -->
    <div id="footer">
        <p>&copy;  Amphenol-GIS &nbsp;2014 &nbsp;</p>
    </div>
    <!--END FOOTER -->


    <!-- GLOBAL SCRIPTS -->
    <script src="/bs-admin-bcore/template/assets/plugins/jquery-2.0.3.min.js"></script>
     <script src="/bs-admin-bcore/template/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="/bs-admin-bcore/template/assets/plugins/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <!-- END GLOBAL SCRIPTS -->

    <!-- PAGE LEVEL SCRIPTS -->
    <script src="/bs-admin-bcore/template/assets/plugins/flot/jquery.flot.js"></script>
    <script src="/bs-admin-bcore/template/assets/plugins/flot/jquery.flot.resize.js"></script>
    <script src="/bs-admin-bcore/template/assets/plugins/flot/jquery.flot.time.js"></script>
     <script  src="/bs-admin-bcore/template/assets/plugins/flot/jquery.flot.stack.js"></script>
     <script src="/bs-admin-bcore/template/assets/plugins/jquery.bootgrid-1.1.2/jquery.bootgrid.min.js"></script>
     
     <script src="/bs-admin-bcore/template/assets/js/jquery-ui.min.js"></script>

     <script src="/bs-admin-bcore/template/assets/plugins/fullcalendar-1.6.2/fullcalendar/fullcalendar.min.js"></script>  
   	 <script src="/bs-admin-bcore/template/assets/js/calendarInit.js"></script>
   
   
    
    <script>
    $(function () { CalendarInit(); });
    </script>
    <!-- END PAGE LEVEL SCRIPTS -->


</body>

    <!-- END BODY -->
</html>
