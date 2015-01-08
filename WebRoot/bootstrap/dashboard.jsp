<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" deferredSyntaxAllowedAsLiteral="true"%>
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
    <!-- jquery ui bootstrap css -->
	<link href="/js/jquery-ui-bootstrap/css/custom-theme/jquery-ui-1.10.3.custom.css" rel="stylesheet">
	<link rel="stylesheet" href="/js/jquery-ui-bootstrap/assets/css/font-awesome.min.css">
	<!--[if IE 7]>
    <link rel="stylesheet" href="/js/jquery-ui-bootstrap/assets/css/font-awesome-ie7.min.css">
    <![endif]-->
    <!--[if lt IE 9]>
    <link rel="stylesheet" href="/js/jquery-ui-bootstrap/css/custom-theme/jquery.ui.1.10.3.ie.css">
    <![endif]-->
    <link rel="stylesheet" href="/js/jquery-ui-bootstrap/assets/css/docs.css">
    <link rel="stylesheet" href="/js/jquery-ui-bootstrap/assets/js/google-code-prettify/prettify.css">
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
            <li><a href="#">Dashboard</a></li>
            <li><a href="#">Settings</a></li>
            <li><a href="#">Profile</a></li>
            <li><a href="#">Help</a></li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
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
        <div id="dialog" title="Tab data">
  <form>
    <fieldset class="ui-helper-reset">
      <label for="tab_title">标题</label>
      <input type="text" name="tab_title" id="tab_title" value="" class="ui-widget-content ui-corner-all">
      <label for="tab_content">内容</label>
      <textarea name="tab_content" id="tab_content" class="ui-widget-content ui-corner-all"></textarea>
    </fieldset>
  </form>
</div>
 
<button id="add_tab" class="btn">添加标签页</button>
       
       
        	<div id="tabs">
        		<ul>
        			 <li><a href="#tabs-1">Dashboard</a></li>
        		</ul>
        		<div id="tabs-1">
         <!--   <h1 class="page-header">Dashboard</h1>      --> 
          			<div class="table-responsive">
          	
            			<table id="grid-data" class="table table-condensed table-hover table-striped">
            				<thead>
            					<tr>
            						<th data-column-id="id" data-type="numeric" data-identifier="true">ID</th>
            						<th data-column-id="organization_id">organization_id</th>
            						<th data-column-id="employee_id" data-order="desc">employee_id</th>
            						<th data-column-id="station">station</th>
            						<th data-column-id="username">username</th>
            						<th data-column-id="password">password</th>
            						<th data-column-id="role_ids">role_ids</th>
            						<th data-column-id="name">name</th>
            						<th data-column-id="commands" data-formatter="commands" data-sortable="false">Commands</th>
            					</tr>
            				</thead>
            			</table>
          			</div>
          		</div>
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
	<script src="/js/jquery-ui-bootstrap/assets/js/vendor/jquery-migrate-1.2.1.min.js" ></script>
	<script src="/js/jquery-ui-bootstrap/assets/js/vendor/holder.js" ></script>
	<script src="/js/jquery-ui-bootstrap/assets/js/vendor/jquery-ui-1.10.3.custom.min.js" ></script>
	<script src="/js/jquery-ui-bootstrap/assets/js/google-code-prettify/prettify.js" ></script>
    
    <script>
    $(function(){
    //表格模块
    	$("#grid-data").bootgrid({
    		ajax:true,
    		selection:true,
    		post:function(){
    			return {
    				
    			};
    		},
    		url:"${CONTEXT_PATH}/main/bootgrid",
    		formatters:{
    			"commands":function(column,row){
    				 return "<button type=\"button\" class=\"btn btn-xs btn-default command-edit\" data-row-id=\"" + row.id + "\"><span class=\"glyphicon glyphicon-pencil\"></span></button> " +
							"<button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\"" + row.id + "\"><span class=\"glyphicon glyphicon-trash\"></span></button>";
    			}
    		}
    	}).on("loaded.rs.jquery.bootgrid",function(){
    		
    		$("#grid-data").find(".command-edit").on("click",function(e){
    			alert("You pressed edit on row:"+$(this).data("row-id"));
    		}).end().find(".command-delete").on("click",function(e){
    			alert("You pressed delete on row:"+$(this).data("row-id"));
    		});
    	
    	});
    	//在表格功能栏中添加增删改按钮
    		$("#grid-data-header").find(".actionBar").prepend("<button id=\"append\" type=\"button\" class=\"btn btn-default\">Creat</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    		$("#grid-data-header").find(".actionBar").prepend("<button id=\"append\" type=\"button\" class=\"btn btn-default\">Edit</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    		$("#grid-data-header").find(".actionBar").prepend("<button id=\"append\" type=\"button\" class=\"btn btn-default\">Delete</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    	
    	//bootstrap tab
    	/*
    		var log = function(s){
                    window.console && console.log(s);
                };
                $('.nav-tabs a:first').tab('show');
                
                $('a[data-toggle="tab"]').on('show', function (e) {
                    //log(e);
                    alert("hhhhh");
                });
                $('a[data-toggle="tab"]').on('shown', function (e) {
                    log(e.target); // activated tab
                    log(e.relatedTarget); // previous tab
                    alert("bbbb");
                });
               */ 
          //jquery UI boostrap 
              // Simple tabs adding and removing
    $('#tabs').tabs();
     
    // Dynamic tabs
var tabTitle = $( "#tab_title" ),
      tabContent = $( "#tab_content" ),
      tabTemplate = "<li><a href='#{href}'>#{label}</a> <span class='ui-icon ui-icon-close' role='presentation'> </span></li>",
      tabCounter = 2;
 
    var tabs = $( "#tabs" ).tabs();
 
    // 模态对话框的初始化：自定义按钮和一个重置内部表单的 "close" 回调
    var dialog = $( "#dialog" ).dialog({
      autoOpen: false,
      modal: true,
      buttons: {
        Add: function() {
          addTab();
          $( this ).dialog( "close" );
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      },
      close: function() {
        form[ 0 ].reset();
      }
    });
 
    // addTab 表单：当提交时调用 addTab 函数，并关闭对话框
    var form = dialog.find( "form" ).submit(function( event ) {
      addTab();
      dialog.dialog( "close" );
      event.preventDefault();
    });
 
    // 实际的 addTab 函数：使用上面表单的输入添加新的标签页
    function addTab() {
      var label = tabTitle.val() || "Tab " + tabCounter,
        id = "tabs-" + tabCounter,
        li = $( tabTemplate.replace( /#\{href\}/g, "#" + id ).replace( /#\{label\}/g, label ) ),
        tabContentHtml = tabContent.val() || "Tab " + tabCounter + " content.";
 
      tabs.find( ".ui-tabs-nav" ).append( li );
      tabs.append( "<div id='" + id + "'><p>" + tabContentHtml + "</p></div>" );
      tabs.tabs( "refresh" );
      tabCounter++;
    }
 
    // addTab 按钮：值打开对话框
    $( "#add_tab" )
      .button()
      .click(function() {
        dialog.dialog( "open" );
      });
 
    // 关闭图标：当点击时移除标签页
    tabs.delegate( "span.ui-icon-close", "click", function() {
      var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
      $( "#" + panelId ).remove();
      tabs.tabs( "refresh" );
    });
 
    tabs.bind( "keyup", function( event ) {
      if ( event.altKey && event.keyCode === $.ui.keyCode.BACKSPACE ) {
        var panelId = tabs.find( ".ui-tabs-active" ).remove().attr( "aria-controls" );
        $( "#" + panelId ).remove();
        tabs.tabs( "refresh" );
      }
    });

           
    	});
    </script>
  </body>
</html>
