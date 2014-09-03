<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	<form method="post" action="${CONTEXT_PATH}/dcc/create" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>客户：</label>
				
				<select name="customer" style="width:150px">
						<option value="none" >请选择客户</option>
					<c:forEach items="${dcc }" var="l">
						<option value="${l.customer }">${l.customer }</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>站别：</label>
			
				<select name="station" style="width:150px">
						<option value="none" >请选择站别</option>
					<c:forEach items="${station }" var="s">
						<option value="${s.station }">${s.discription }</option>
					</c:forEach>
				</select>
			</p>
			
			<p>
				<label>文件类型：</label>
			
				<select name="type" style="width:150px">
						<option value="none" >请选择文件类型</option>
						<option value="WI">作业指导书</option>
						<option value="OBA Check list">OBA Check list</option>
				</select>
			</p>
			<p>
				<label>产品代码：</label>
				<input name="pn" class="required" type="text"   alt="请输入产品代码"/>
			</p>
			<p>
				<label>版本号：</label>
				<input name="rev" class="required" type="text"   alt="请输入版本号"/>
			</p>
			<p>
				<label>上传文件：</label>
				<input name="wi" type="file" />
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

