<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
            						<th data-column-id="isQuarterlyPM" data-formatter="isQuarterlyPM">季度PM计划</th>
            						<th data-column-id="isYearlyPM" data-formatter="isYearlyPM">年度PM计划</th>
            						<th data-column-id="commands" data-formatter="commands" data-sortable="false">Commands</th>
            					</tr>
            				</thead>
            			</table>
          			</div>
<script>
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
    			"isQuarterlyPM":function(column,row){
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

         
                
               				
			//显示时间
		
                
    	});
</script>
