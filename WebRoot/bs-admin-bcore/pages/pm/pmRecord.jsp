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
    <title>Amphenol-GIS Web System | PM Record Page</title>
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
	<link rel="stylesheet" href="/bs-admin-bcore/template/assets/css/bootstrap-fileupload.min.css" />
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
      							<h5>设备PM记录清单</h5>
 	 						</header>
  							
	  					<div class="table-responsive">
          	
            			<table id="grid-data-pm-record" class="table table-condensed table-hover table-striped" data-selection="true" data-multi-select="true" data-row-select="true" data-keep-selection="true" >
            				<thead>
            					<tr>
            						<th data-column-id="id" data-type="numeric" data-identifier="true">ID</th>
            						<th data-column-id="equipmentID" data-formatter="equipmentID" >设备编号</th>
            						<th data-column-id="PMType" >PM记录类型</th>
            						<th data-column-id="PMOperator" >PM执行人</th>
            						<th data-column-id="PMTime" >PM执行时间</th>
            						<th data-column-id="uploadBy" >记录上传人</th>
            						<th data-column-id="uploadTime" >上传时间</th>
            						<th data-column-id="file" data-formatter="file">记录</th>
            						<shiro:hasPermission name="pm:*">
            						<th data-column-id="commands" data-formatter="commands" data-sortable="false">操作</th>
            						</shiro:hasPermission>
            					</tr>
            				</thead>
            			</table>
          			</div>
          			
      						


            		</div>

					</div>
					
					
					<!-- Form  Modal BEGIN  -->
						<div  class="col-lg-12">
						
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
    <script src="/bs-admin-bcore/template/assets/plugins/jasny/js/bootstrap-fileupload.js"></script>
	<script src="/bs-admin-bcore/template/assets/plugins/datepicker/js/bootstrap-datepicker.js"></script>



      
    
    <script>
    $(function () { 

        $(".pm-datepicker").datepicker({format:'yyyy-mm-dd'});
    						
            	//表格模块
    	$("#grid-data-pm-record").bootgrid({
    		ajax:true,
  
    		post:function(){
    			return {
    				
    			};
    		},
    		url:"${CONTEXT_PATH}/pm/pmRecord",
    		formatters:{
    			"equipmentID":function(column,row){
    						return "<a href=\"#\">" + row[column.id] + "</a>";
    			},
    			"commands":function(column,row){
    				 return "<button type=\"button\" class=\"btn btn-xs btn-default command-edit\" data-row-id=\"" + row.id + "\"><span class=\"glyphicon glyphicon-pencil\"></span></button> " +
							"<button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\"" + row.id + "\"><span class=\"glyphicon glyphicon-trash\"></span></button>";
    			},
    			"file":function(column,row){
    				return "<a href=\"${CONTEXT_PATH}/PMRecord/"+row[column.id]+"\" target='blank' >"+row[column.id]+"</a>";
    			}
    		}
    	}).on("loaded.rs.jquery.bootgrid",function(){
    		
    		$("#grid-data-pm-record").find(".command-edit").on("click",function(e){
    			
    		
    			$("#ajaxEditModal").load("/pm/getEditRecordPage",
    					{
    						id:$(this).data("row-id")
    					},
    					function(data,status){
    						$(".pm-datepicker").datepicker({format:'mm-dd'});
    						$('#editQuarterlyPMModal').modal("show");
    					}
    			);
    			
    		}).end().find(".command-delete").on("click",function(e){
    			$.post("/pm/deletePMRecord",
    					{
    						id:$(this).data("row-id")
    					},
    					function(data,status){
    							alert(data.msg);
    						}
    					);
    					$("#grid-data-pm-record").bootgrid('reload');
    		});
    		if(!$("#new-YearlyRecord").length>0){
    			$("#grid-data-pm-record-header").find(".actionBar").prepend("<button id=\"new-YearlyRecord\" class=\"btn btn-success\" data-toggle=\"modal\" data-target=\"#newYearlyPMRecordModal\">Upload YearlyPM Record</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    		}
    		if(!$("#new-QuarterlyRecord").length>0){
    			$("#grid-data-pm-record-header").find(".actionBar").prepend("<button id=\"new-QuarterlyRecord\" class=\"btn btn-success\" data-toggle=\"modal\" data-target=\"#newQuarterlyPMRecordModal\">Upload QuarterlyPM Record</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
       		}
       		if(!$("#new-MonthlyRecord").length>0){
        		$("#grid-data-pm-record-header").find(".actionBar").prepend("<button id=\"new-MonthlyRecord\" class=\"btn btn-success\" data-toggle=\"modal\" >Upload MonthlyPM Record</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    		}
    	$("#new-MonthlyRecord").on("click",function(e){
        	$("#ajaxEditModal").load("/pm/getCreatePMRecord",
        							{
        								PMType:"MonthlyPM"
        							},function(data,status){
        								$(".pm-datepicker").datepicker({format:'yyyy-mm-dd'});
        								$("#newMonthlyPMRecordModal").modal("show");
        								
        								$("#monthly-year").on("change",function(e){
        										$.post("/pm/getEquipmentIDs",
        														{
        															PMType:"MonthlyPM",
        															mYear:$("#monthly-year").val()
        														},function(data,status){
        															
        															var sbody="";
        						
        																$.each(data.meIDs,function(n,value){
        																	var options="";
        																	options +="<option value='"+value+"'>"+value+"</option>";
        																	sbody +=options;
        																});
        																
        																$("#mequipmentID").empty().append(sbody);
        															});
        
        										});	
        								
        							});
        	
        });
        
        	$("#new-QuarterlyRecord").on("click",function(e){
        		$("#ajaxEditModal").load("/pm/getCreatePMRecord",
        							{
        								PMType:"QuarterlyPM"
        							},function(data,status){
        								$(".pm-datepicker").datepicker({format:'yyyy-mm-dd'});
        								$("#newQuarterlyPMRecordModal").modal("show");
        								
        								$("#quarterly-year").on("change",function(e){
        										$.post("/pm/getEquipmentIDs",
        														{
        															PMType:"QuarterlyPM",
        															mYear:$("#quarterly-year").val()
        														},function(data,status){
        															
        															var sbody="";
        						
        																$.each(data.qeIDs,function(n,value){
        																	var options="";
        																	options +="<option value='"+value+"'>"+value+"</option>";
        																	sbody +=options;
        																});
        																
        																$("#qequipmentID").empty().append(sbody);
        															});
        
        										});	
        								
        							});
        	
        		});
        		
        		
        		$("#new-YearlyRecord").on("click",function(e){
        		$("#ajaxEditModal").load("/pm/getCreatePMRecord",
        							{
        								PMType:"YearlyPM"
        							},function(data,status){
        								$(".pm-datepicker").datepicker({format:'yyyy-mm-dd'});
        								$("#newYearlyPMRecordModal").modal("show");
        								
        								$("#yearly-year").on("change",function(e){
        										$.post("/pm/getEquipmentIDs",
        														{
        															PMType:"YearlyPM",
        															mYear:$("#yearly-year").val()
        														},function(data,status){
        															
        															var sbody="";
        						
        																$.each(data.qeIDs,function(n,value){
        																	var options="";
        																	options +="<option value='"+value+"'>"+value+"</option>";
        																	sbody +=options;
        																});
        																
        																$("#yequipmentID").empty().append(sbody);
        															});
        
        										});	
        								
        							});
        	
        		});
    	
    	
    	});
    	//在表格功能栏中添加增删改按钮
    
    	//$("#grid-data-pm-record-header").find(".actionBar").prepend("<button id=\"new-pmRecord\" class=\"btn btn-success\" data-toggle=\"modal\" data-target=\"#newYearlyPMRecordModal\">Upload YearlyPM Record</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    	//$("#grid-data-pm-record-header").find(".actionBar").prepend("<button id=\"new-pmRecord\" class=\"btn btn-success\" data-toggle=\"modal\" data-target=\"#newQuarterlyPMRecordModal\">Upload QuarterlyPM Record</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        //$("#grid-data-pm-record-header").find(".actionBar").prepend("<button id=\"new-pmRecord\" class=\"btn btn-success\" data-toggle=\"modal\" data-target=\"#newMonthlyPMRecordModal\">Upload MonthlyPM Record</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        
       //$("#grid-data-pm-record-header").find(".actionBar").prepend("<button id=\"new-YearlyRecord\" class=\"btn btn-success\" data-toggle=\"modal\" data-target=\"#newYearlyPMRecordModal\">Upload YearlyPM Record</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    	//$("#grid-data-pm-record-header").find(".actionBar").prepend("<button id=\"new-QuarterlyRecord\" class=\"btn btn-success\" data-toggle=\"modal\" data-target=\"#newQuarterlyPMRecordModal\">Upload QuarterlyPM Record</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        //$("#grid-data-pm-record-header").find(".actionBar").prepend("<button id=\"new-MonthlyRecord\" class=\"btn btn-success\" data-toggle=\"modal\" >Upload MonthlyPM Record</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        
        //*******************************************Yuan PM Record 创建页面*******************
       /*  $("#equipmentID").on("change",function(e){
        	$.post("/pm/getSelectJsonData",
        			{
        				equipmentID:$("#equipmentID").val()
        				},function(data,status){
        					if(data.equipmentInfo.isMonthlyPM)
        						$("#pt option[value='MonthlyPM']").show();
        					else
        						$("#pt option[value='MonthlyPM']").hide();
        					if(data.equipmentInfo.isQuarterlyPM)
        						$("#pt option[value='QuarterlyPM']").show();
        					else
        						$("#pt option[value='QuarterlyPM']").hide();
        					if(data.equipmentInfo.isYearlyPM)
        						$("#pt option[value='YearlyPM']").show();
        					else
        						$("#pt option[value='YearlyPM']").hide();
        			});
        }); 
        
        $("#pt option[value='MonthlyPM']").on("click",function(e){
        											$("#yearlyPM").val("").hide();
        											$("#quarterlyPM").val("").hide();
   													$("#monthlyPM").show();
   													});
        $("#pt option[value='QuarterlyPM']").on("click",function(e){
        											$("#yearlyPM").val("").hide();
        											$("#monthlyPM").val("").hide();
        											$("#quarterlyPM").show();
        											}); 
         $("#pt option[value='YearlyPM']").on("click",function(e){
         											
        											$("#monthlyPM").val("").hide();
        											$("#quarterlyPM").val("").hide();
        											$("#yearlyPM").show();
        											}); */ 	
     });
    </script>
     <!--END PAGE LEVEL SCRIPTS -->

</body>
    <!-- END BODY -->
</html>
