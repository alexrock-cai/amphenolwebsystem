<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	<form method="post" action="${CONTEXT_PATH}/user/stationUpdate" class="pageForm required-validate"  onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>站别简称：</label>
				<input name="station" class="required" type="text"  value="${s.station }" alt="请输入站别名"/>
				<input name="id" type="hidden" value="${s.id }"/>
			</p>
			
			<p>
				<label>站别描述：</label>
				<input name="discription"  type="text"  value="${s.discription}" alt="请输入描述"/>
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

