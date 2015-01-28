<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	<form method="post" action="${CONTEXT_PATH}/user/findAuthPartNumber" class="pageForm required-validate"  onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			
			<p>
				<label>认证用户信息：</label>
			</p>
			<p>
				<label>工号：${u.username }</label>
				<label>姓名：${u.name }</label>
			<input name="id" value="${u.id }" type="hidden" />
			</p>
			<p>
				<label>认证产品：</label>
				<input type="text" name="authPartNumber" class="required" />
			</p>
			
			
		
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

