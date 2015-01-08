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
    <title>Amphenol-GIS Web System | Monthly PM Page</title>
     <meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
     <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
    <!-- GLOBAL STYLES -->
    <!-- GLOBAL STYLES -->
    <link rel="stylesheet" href="/bs-admin-bcore/template/assets/plugins/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="/bs-admin-bcore/template/assets/css/main.css" />
    <link rel="stylesheet" href="/bs-admin-bcore/template/assets/css/theme.css" />
    <link rel="stylesheet" href="/bs-admin-bcore/template/assets/css/MoneAdmin.css" />
    <link rel="stylesheet" href="/bs-admin-bcore/template/assets/plugins/Font-Awesome/css/font-awesome.css" />
    <!--END GLOBAL STYLES -->

    <!-- PAGE LEVEL STYLES -->
	 
	<link rel="stylesheet" href="/bs-admin-bcore/template/assets/plugins/jquery.bootgrid-1.1.2/jquery.bootgrid.css"/>
	
	



<link rel="stylesheet" href="/bs-admin-bcore/template/assets/plugins/chosen/chosen.min.css" />

<link rel="stylesheet" href="/bs-admin-bcore/template/assets/plugins/datepicker/css/datepicker.css" />

    <!-- END PAGE LEVEL  STYLES -->
       <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="/bs-admin-bcore/template/assets/js/html5shiv.min.js"></script>
      <script src="/bs-admin-bcore/template/assets/js/respond.min.js"></script>
    <![endif]-->
</head>
    <!-- END HEAD -->

    <!-- BEGIN BODY -->
<body class="padTop53 " >

   <!-- MAIN WRAPPER -->
    <div id="wrap">


         <!-- HEADER SECTION -->
        <div id="top">

            <nav class="navbar navbar-inverse navbar-fixed-top " style="padding-top: 10px;">
                <a data-original-title="Show/Hide Menu" data-placement="bottom" data-tooltip="tooltip" class="accordion-toggle btn btn-primary btn-sm visible-xs" data-toggle="collapse" href="#menu" id="menu-toggle">
                    <i class="icon-align-justify"></i>
                </a>
                <!-- LOGO SECTION -->
                <header class="navbar-header">

                    <a href="/pm/index" class="navbar-brand">
                    <img src="/bs-admin-bcore/template/assets/img/logo.png" alt="" /></a>
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
                            <li><a href="/pmLogout"><i class="icon-signout"></i> Logout </a>
                            </li>
                        </ul>

                    </li>
                    <!--END ADMIN SETTINGS -->
                </ul>

            </nav>

        </div>
        <!-- END HEADER SECTION -->

	
        <!-- MENU SECTION -->
        
       <div id="left">
       	  
            <div class="media user-media well-small">
                <a class="user-link" href="#">
                    <img class="media-object img-thumbnail user-img" alt="User Picture" src="${user.getStr('img') }" />
                </a>
                <br />
                <div class="media-body">
                    <h5 class="media-heading"> ${user.getStr('name') }</h5>
                    <ul class="list-unstyled user-info">
                        
                        <li>
                             <a class="btn btn-success btn-xs btn-circle" style="width: 10px;height: 12px;"></a> Online
                           
                        </li>
                       
                    </ul>
                </div>
                <br />
            </div>

           <!-- 菜单栏列表 BEGIN -->
            <ul id="menu" class="collapse">

                
                <li class="panel ">
                    <a href="/pm/index" >
                        <i class="icon-table"></i> 首页    
                    </a>                   
                </li>



                <li class="panel active">
                    <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle" data-target="#equipment-pm-nav">
                        <i class="icon-tasks"> </i> 设备PM监控      
	   
                        <span class="pull-right">
                          <i class="icon-angle-left"></i>
                        </span>
                      
                    </a>
                    <ul class="in" id="equipment-pm-nav">
                       
                        <li class=""><a href="/pm/getPmInfoPage" class="ajax-load-page"><i class="icon-angle-right"></i> 设备PM清单 </a></li>
                         <li class=""><a href="/pm/getMonthlyPM"  class="ajax-load-page"><i class="icon-angle-right"></i> 月度PM计划 </a></li>
                        <li class=""><a href="/pm/getQuarterlyPM" class="ajax-load-page"><i class="icon-angle-right"></i> 季度PM计划 </a></li>
                        <li class=""><a href="/pm/getYearlyPM" class="ajax-load-page"><i class="icon-angle-right"></i> 年度PM计划 </a></li>
                        <li class=""><a href="/pm/setEmailAlert" class="ajax-load-page"><i class="icon-angle-right"></i> 邮件报警设定 </a></li>
                         <li class=""><a href="/pm/getRecord" class="ajax-load-page"><i class="icon-angle-right"></i> 上传记录表单 </a></li>
                        
                    </ul>
                </li>
                
            </ul>
			<!-- 菜单栏列表 END -->

        </div>
        <!--END MENU SECTION -->


        <!--PAGE CONTENT -->
        <div id="content" >

            <div class="inner">
                <div class="row">
                    <div class="col-lg-12">
                        <h2> Welcome To Amphenol GIS Equipment PM Monitor System </h2>
                   
                    </div>
                </div>

                <hr />
                  <div class="row">
                    <div class="col-lg-12">

                		<div class="box">
  							<header>
      							<h5>设备月度PM计划清单</h5>
 	 						</header>
  							
	  					<div class="table-responsive">
          	
            			<table id="grid-data-monthlyPM" class="table table-condensed table-hover table-striped" data-selection="true" data-multi-select="true" data-row-select="true" data-keep-selection="true" >
            				<thead>
            					<tr>
            						<th data-column-id="id" data-type="numeric" data-identifier="true">ID</th>
            						<th data-column-id="equipmentID" data-formatter="equipmentID" >设备编号</th>
            						<th data-column-id="year" >年度</th>
            						<th data-column-id="janPMDay" data-formatter="janPMDay">一月</th>
            						<th data-column-id="febPMDay" data-formatter="febPMDay">二月</th>
            						<th data-column-id="marPMDay" data-formatter="marPMDay">三月</th>
            						<th data-column-id="aprPMDay" data-formatter="aprPMDay">四月</th>
            						<th data-column-id="mayPMDay" data-formatter="mayPMDay">五月</th>
            						<th data-column-id="junPMDay" data-formatter="junPMDay">六月</th>
            						<th data-column-id="julPMDay" data-formatter="julPMDay">七月</th>
            						<th data-column-id="augPMDay" data-formatter="augPMDay">八月</th>
            						<th data-column-id="sepPMDay" data-formatter="sepPMDay">九月</th>
            						<th data-column-id="octPMDay" data-formatter="octPMDay">十月</th>
            						<th data-column-id="novPMDay" data-formatter="novPMDay">十一月</th>
            						<th data-column-id="decPMDay" data-formatter="decPMDay">十二月</th>

            						<th data-column-id="contents">备注</th>
            						<shiro:hasPermission name="pm:update">
            						<th data-column-id="commands" data-formatter="commands" data-sortable="false">操作</th>
            						</shiro:hasPermission>
            					</tr>
            				</thead>
            			</table>
          			</div>
          			
      						


            		</div>

					</div>
					<!-- MonthlyPM 状态 表格 -->
					<div class="col-lg-12">

                		<div class="box">
  							<header>
      							<h5>设备月度PM状态</h5>
 	 						</header>
  							
	  					<div class="table-responsive">
          	
            			<table id="grid-data-isMonthlyPM" class="table table-condensed table-hover table-striped" data-selection="true" data-multi-select="true" data-row-select="true" data-keep-selection="true" >
            				<thead>
            					<tr>
            						<th data-column-id="id" data-type="numeric" data-identifier="true">ID</th>
            						<th data-column-id="equipmentID" data-formatter="equipmentID" >设备编号</th>
            						<th data-column-id="year" >年度</th>
            						<th data-column-id="isJanPM" data-formatter="doPM">一月</th>
            						<th data-column-id="isFebPM" data-formatter="doPM">二月</th>
            						<th data-column-id="isMarPM" data-formatter="doPM">三月</th>
            						<th data-column-id="isAprPM" data-formatter="doPM">四月</th>
            						<th data-column-id="isMayPM" data-formatter="doPM">五月</th>
            						<th data-column-id="isJunPM" data-formatter="doPM">六月</th>
            						<th data-column-id="isJulPM" data-formatter="doPM">七月</th>
            						<th data-column-id="isAugPM" data-formatter="doPM">八月</th>
            						<th data-column-id="isSepPM" data-formatter="doPM">九月</th>
            						<th data-column-id="isOctPM" data-formatter="doPM">十月</th>
            						<th data-column-id="isNovPM" data-formatter="doPM">十一月</th>
            						<th data-column-id="isDecPM" data-formatter="doPM">十二月</th>

            						<th data-column-id="contents">备注</th>
            						
            					</tr>
            				</thead>
            			</table>
          			</div>
          			
      						


            		</div>

					</div>
					<!-- MonthlyPM 状态结束 -->
					
					<!-- Form  Modal BEGIN  -->
						<div  class="col-lg-12">
						<form role="form" action="/pm/createMonthlyPM" class="form-horizontal" id="newMonthlyPMModal-validation" method="post">
                        <div class="modal fade " id="newMonthlyPMModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="reset" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="H3">新建月度PM计划</h4>
                                            
                                        </div>
                                        <div class="modal-body">
                                                                         
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">设备编号</label>
                                            <div class="col-lg-6">
                                                <select data-placeholder="选择一个设备" class="form-control " name="equipmentID" tabindex="2">
                                                	<c:forEach items="${equipmentIDs }" var="e">
														<option value="${e }">${e }</option>
													</c:forEach>                                               	
                                                </select>
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">年度</label>
                                            <div class="col-lg-6">
                                                <select data-placeholder="please chosen" class="form-control " name="year" tabindex="2">
                                                	
														<option value="2014">2014年</option>
														<option value="2015">2015年</option>
														<option value="2016">2016年</option>
														<option value="2017">2017年</option>
														<option value="2018">2018年</option>
														<option value="2019">2019年</option>
														<option value="2020">2020年</option>
														<option value="2021">2022年</option>
													                                              	
                                                </select>
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">一月计划</label>
                                            <div class="col-lg-6">
                                            	
                                                 	<input type="text" class="form-control pm-datepicker" value="01-01" name="janPMDay" id="janPM" />
                                                 
                                            </div>
                                        </div>
                                       
                                       <div class="form-group">
                                            <label class="control-label col-lg-4">二月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="02-01"  name="febPMDay" id="febPM" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">三月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="03-01"  name="marPMDay" id="marPM" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">四月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="04-01"  name="aprPMDay" id="aprPM" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">五月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="05-01"  name="mayPMDay" id="mayPM" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">六月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="06-01" name="junPMDay" id="junPM" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">七月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="07-01" name="julPMDay" id="julPM" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">八月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="08-01" name="augPMDay" id="augPM" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">九月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="09-01" name="sepPMDay" id="sepPM" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">十月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="10-01" name="octPMDay" id="octPM" />
                                            </div>
                                        </div>                                                                                                                
                                    	
                                    	<div class="form-group">
                                            <label class="control-label col-lg-4">十一月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="11-01" name="novPMDay" id="novPM" />
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">十二月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="12-01" name="decPMDay"  id="decPM" />
                                            </div>
                                        </div> 
                                        
                                        </div>
                                        <div class="modal-footer">
                                            <button type="reset" class="btn btn-default" >取消</button>
                                            <button type="submit" class="btn btn-primary">保存</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </form>
						</div>
					<!-- Form END -->
					<div id="ajaxEditModal" class="col-lg-12"></div>
                 </div>
                 
                 
               </div>
			<!-- INNER END -->
			

        </div>
         <!--PAGE CONTENT -->
       
    </div>

     <!--END MAIN WRAPPER -->
	<div class="row">
	
					
	</div>
   <!-- FOOTER -->
    <div id="footer">
        <p>&copy;  Amphenol-GIS  &nbsp;2014 &nbsp;</p>
    </div>
    <!--END FOOTER -->


     <!-- GLOBAL SCRIPTS -->
    <script src="/bs-admin-bcore/template/assets/plugins/jquery-2.0.3.min.js"></script>
    <script src="/bs-admin-bcore/template/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="/bs-admin-bcore/template/assets/plugins/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <!-- END GLOBAL SCRIPTS -->

     <!-- PAGE LEVEL SCRIPTS -->
    <script src="/bs-admin-bcore/template/assets/plugins/jquery.bootgrid-1.1.2/jquery.bootgrid.min.js"></script>
   
    


<script src="/bs-admin-bcore/template/assets/plugins/chosen/chosen.jquery.min.js"></script>

<script src="/bs-admin-bcore/template/assets/plugins/datepicker/js/bootstrap-datepicker.js"></script>

<shiro:hasPermission name="pm:create">
<script>
	function bttn(){
	
		$("#grid-data-monthlyPM-header").find(".actionBar").prepend("<button id=\"new-monthlyPM\" class=\"btn btn-success\" data-toggle=\"modal\" data-target=\"#newMonthlyPMModal\">New Schedule</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
	}
</script>
</shiro:hasPermission> 
    <script>
    $(function () { 
           
        //$("#top").stickUp(); 
        //启动表单验证插件
        //$(".chzn-select").chosen();
        $(".pm-datepicker").datepicker({format:'mm-dd'});
    						
            	//表格模块
    	$("#grid-data-monthlyPM").bootgrid({
    		ajax:true,
  
    		post:function(){
    			return {
    				
    			};
    		},
    		url:"${CONTEXT_PATH}/pm/monthlyPM",
    		formatters:{
    			"doPM":function(column,row){
    				if(row[column.id]){
    					return "<span style=\"border:1px; color:green\">Yes</span>";
    				}else{
    					return "<span style=\"border:1px; color:red\">No</span>";
    				}
    			},
    			"equipmentID":function(column,row){
    						return "<a href=\"#\">" + row[column.id] + "</a>";
    			},
    			
    			"commands":function(column,row){
    				 return "<button type=\"button\" class=\"btn btn-xs btn-default command-edit\" data-row-id=\"" + row.id + "\"><span class=\"glyphicon glyphicon-pencil\"></span></button> " +
							"<button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\"" + row.id + "\"><span class=\"glyphicon glyphicon-trash\"></span></button>";
    			}
    		}
    	}).on("loaded.rs.jquery.bootgrid",function(){
    		
    		$("#grid-data-monthlyPM").find(".command-edit").on("click",function(e){
    			
    		
    			$("#ajaxEditModal").load("/pm/getEditMonthlyPMPage",
    					{
    						id:$(this).data("row-id")
    					},
    					function(data,status){
    						$(".pm-datepicker").datepicker({format:'mm-dd'});
    						$('#editMonthlyPMModal').modal("show");
    					}
    			);
    			
    		}).end().find(".command-delete").on("click",function(e){
    			$.post("/pm/deleteMonthlyPM",
    					{
    						id:$(this).data("row-id")
    					},
    					function(data,status){
    							alert(data.msg);
    						}
    					);
    					$("#grid-data-monthlyPM").bootgrid('reload');
    		});
    	
    	});
    	//在表格功能栏中添加增删改按钮
    
    	
    	
    	//月度PM状态 Grid
    	$("#grid-data-isMonthlyPM").bootgrid({
    			ajax:true,
    		    url:"${CONTEXT_PATH}/pm/monthlyPM",
    		    formatters:{
    		    	"doPM":function(column,row){
    					if(row[column.id]){
    						return "<span style=\"border:1px; color:green\">Yes</span>";
    					}else{
    						return "<span style=\"border:1px; color:red\">No</span>";
    					}
    				}
    		    }
    	});
    	 /* $("#new-monthlyPM").on("click",function(){
    		$("#ajaxMonthlyPMModal").load("/pm/getNewMonthlyPMPage", 
    									
    					function(data,status){
    						
    						//$("#newMonthlyPMModal-validation").validationEngine();
    						
    						
    						$("#newMonthlyPMModal").modal("show");
    					});
    	});  */
        bttn();    	
     });
    </script>
     <!--END PAGE LEVEL SCRIPTS -->

</body>
    <!-- END BODY -->
</html>
