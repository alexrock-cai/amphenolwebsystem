<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/static/image/favicon.ico">

    <title>Dashboard Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="/js/bootstrap-3.2.0-dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/js/bootstrap-3.2.0-dist/css/dashboard.css" rel="stylesheet">
    <!-- jquery bootgrid css -->
    <link href="/js/jquery.bootgrid-1.1.2/jquery.bootgrid.css" rel="stylesheet">
   
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="/js/bootstrap-3.2.0-dist/js/html5shiv.min.js"></script>
      <script src="/js/bootstrap-3.2.0-dist/js/respond.min.js"></script>
    <![endif]-->
    
  </head>

  <body>
    <!-- 顶部导航条 -->
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Equipment PM Monitor System</a>
        </div>
        <div class="navbar-collapse collapse">
         
          <ul class="nav navbar-nav navbar-right">
            <li><a><span id="localtime"></span></a></li>
            
          </ul>
          
        </div>
      </div>
    </div>
	<!--  ******* 顶部导航条结束 ***********  -->
	
	<!-- 总部内容 -->
    <div class="container-fluid">
      <div class="row">
      <!-- 左侧边栏 -->
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">DashBoard</a></li>
            <li><a href="#">Reports</a></li>
            <li><a href="#">Analytics</a></li>
            <li><a href="#">Export</a></li>
          </ul>
          <!-- demo 列表
          <ul class="nav nav-sidebar">
            <li><a href="">Nav item</a></li>
            <li><a href="">Nav item again</a></li>
            <li><a href="">One more nav</a></li>
            <li><a href="">Another nav item</a></li>
            <li><a href="">More navigation</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="">Nav item again</a></li>
            <li><a href="">One more nav</a></li>
            <li><a href="">Another nav item</a></li>
          </ul>
          -->
        </div>
        <!-- **************左侧边栏 结束 ***************-->
        <!-- 主内容页面 -->          
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        	<ul id="mytab" class="nav nav-tabs">
            	<li class="active" ><a href="#pmInfo" data-toggle="tab">设备基本信息</a></li>
            	<li><a href="#monthlyPM" data-toggle="tab">月度PM计划</a></li>
            	<li><a href="#querterlyPM" data-toggle="tab">季度PM计划</a></li>
            	<li><a href="#yearlyPM" data-toggle="tab">年度PM计划</a></li>
        	</ul>
        	<div class="tab-content">
            	<div class="tab-pane active" id="pmInfo">
         <!--   <h1 class="page-header">Dashboard</h1>      --> 
          			<div class="table-responsive">
          	
            			<table id="grid-data-pmInfo" class="table table-condensed table-hover table-striped">
            				<thead>
            					<tr>
            						<th data-column-id="id" data-type="numeric" data-identifier="true">ID</th>
            						<th data-column-id="equipmentID" data-formatter="equipmentID" >设备编号</th>
            						<th data-column-id="model" >类别</th>
            						<th data-column-id="equipmentName">设备名称</th>
            						<th data-column-id="owner">责任人</th>
            						<th data-column-id="ownerEmail">责任人邮箱地址</th>
            						<th data-column-id="supervisor">责任人上司</th>
            						<th data-column-id="supervisorEmail">责任人上司邮箱地址</th>
            						<th data-column-id="isMonthlyPM" data-formatter="isMonthlyPM">月度PM计划</th>
            						<th data-column-id="isQuerterlyPM" data-formatter="isQuerterlyPM">季度PM计划</th>
            						<th data-column-id="isYearlyPM" data-formatter="isYearlyPM">年度PM计划</th>
            						<th data-column-id="commands" data-formatter="commands" data-sortable="false">Commands</th>
            					</tr>
            				</thead>
            			</table>
          			</div>
          		</div>
            	<div class="tab-pane" id="monthlyPM">2222</div>
            	<div class="tab-pane" id="querterlyPM">333</div>
            	<div class="tab-pane" id="yearlyPM">444.</div>
        	</div>
        </div>
        <!-- **********主页内容结束********************* -->
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="/js/jquery/jquery-1.8.3.js"></script>
    <script src="/js/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>
    <script src="/js/bootstrap-3.2.0-dist/assets/js/docs.min.js"></script>
    <script src="/js/jquery.bootgrid-1.1.2/jquery.bootgrid.min.js"></script>
    <script>
                   //显示时间
          function showLocale(objD){
var str,colorhead,colorfoot;
var yy = objD.getYear();
if(yy<1900) yy = yy+1900;
var MM = objD.getMonth()+1;
if(MM<10) MM = '0' + MM;
var dd = objD.getDate();
if(dd<10) dd = '0' + dd;
var hh = objD.getHours();
if(hh<10) hh = '0' + hh;
var mm = objD.getMinutes();
if(mm<10) mm = '0' + mm;
var ss = objD.getSeconds();
if(ss<10) ss = '0' + ss;
var ww = objD.getDay();
if ( ww==0 ) colorhead="<font color=\"#000000\">";
if ( ww > 0 && ww < 6 ) colorhead="<font color=\"#000000\">";
if ( ww==6 ) colorhead="<font color=\"#000000\">";
if (ww==0) ww="星期日";
if (ww==1) ww="星期一";
if (ww==2) ww="星期二";
if (ww==3) ww="星期三";
if (ww==4) ww="星期四";
if (ww==5) ww="星期五";
if (ww==6) ww="星期六";
colorfoot="</font>";
str = colorhead + yy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss + " " + ww + colorfoot;
return(str);
}

function tick(){
var today;
today = new Date();
document.getElementById("localtime").innerHTML = showLocale(today);
window.setTimeout("tick()", 1000);
}
    $(function(){
    //表格模块
    	$("#grid-data-pmInfo").bootgrid({
    		ajax:true,
    		selection:true,
    		post:function(){
    			return {
    				
    			};
    		},
    		url:"${CONTEXT_PATH}/pm/pmInfo",
    		formatters:{
    			"equipmentID":function(column,row){
    						return "<a href=\"#\">" + row[column.id] + "</a>";
    			},
    			"isMonthlyPM":function(column,row){
    				if(row[column.id]){
    					return "<span style=\"border:1px; color:green\">Yes</span>";
    				}else{
    					return "<span style=\"border:1px; color:red\">No</span>";
    				}
    			},
    			"isQuerterlyPM":function(column,row){
    				if(row[column.id]){
    					return "<span style=\"border:1px; color:green\">Yes</span>";
    				}else{
    					return "<span style=\"border:1px; color:red\">No</span>";
    				}
    			},
    			"isYearlyPM":function(column,row){
    				if(row[column.id]){
    					return "<span style=\"border:1px; color:green\">Yes</span>";
    				}else{
    					return "<span style=\"border:1px; color:red\">No</span>";
    				}
    			},
    			"commands":function(column,row){
    				 return "<button type=\"button\" class=\"btn btn-xs btn-default command-edit\" data-row-id=\"" + row.id + "\"><span class=\"glyphicon glyphicon-pencil\"></span></button> " +
							"<button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\"" + row.id + "\"><span class=\"glyphicon glyphicon-trash\"></span></button>";
    			}
    		}
    	}).on("loaded.rs.jquery.bootgrid",function(){
    		
    		$("#grid-data-pmInfo").find(".command-edit").on("click",function(e){
    			alert("You pressed edit on row:"+$(this).data("row-id"));
    		}).end().find(".command-delete").on("click",function(e){
    			alert("You pressed delete on row:"+$(this).data("row-id"));
    		});
    	
    	});
    	//在表格功能栏中添加增删改按钮
    		$("#grid-data-pmInfo-header").find(".actionBar").prepend("<button id=\"append\" type=\"button\" class=\"btn btn-default\">Delete</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    		$("#grid-data-pmInfo-header").find(".actionBar").prepend("<button id=\"append\" type=\"button\" class=\"btn btn-default\">Edit</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    		$("#grid-data-pmInfo-header").find(".actionBar").prepend("<button id=\"append\" type=\"button\" class=\"btn btn-default\">New</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    	
    	//bootstrap tab

            $('.nav-tabs a:first').tab('show');
                
               				
			//显示时间
			tick(); 
                
    	});
    </script>
  </body>
</html>
