<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	<form method="post" action="${CONTEXT_PATH}/dcc/update" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>客户：</label>
				
				<select name="customer" style="width:150px">
						<option value="${customer }" >${customer}</option>
					<c:forEach items="${dcc }" var="l">
						<option value="${l.customer }">${l.customer }</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>站别：</label>
			
				<select name="station" style="width:150px">
						
					<c:forEach items="${stationList }" var="s">
						<option value="${s.station }" ${s.station eq station?"selected":""} >${s.discription }</option>
					</c:forEach>
				</select>
			</p>
			
			<p>
				<label>文件类型：</label>
			
				<select name="type" style="width:150px">
						<option value="none" >请选择文件类型</option>
						<option value="WI" ${type eq "WI"?"selected":""}>作业指导书</option>
				</select>
			</p>
			<p>
				<label>产品代码：</label>
				<input name="pn" class="required" type="text"  value="${pn }" alt="请输入产品代码"/>
			</p>
			<p>
				<label>版本号：</label>
				<input name="rev" class="required" type="text"  value="${rev }" alt="请输入版本号"/>
				<input name="id" type="hidden" value="${did }"/>
			</p>
			
			
		
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

