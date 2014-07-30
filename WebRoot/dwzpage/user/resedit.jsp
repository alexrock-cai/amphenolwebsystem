<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	<form method="post" action="${CONTEXT_PATH}/resource/edit" class="pageForm required-validate"  onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>id：</label>
				<input name="id" class="required" type="text"  value="${res.id }" />
			</p>
			<p>
				<label>权限名：</label>
				<input name="name" class="required" type="text" value="${res.name }"  alt="请输入权限名"/>
			</p>
			<p>
				<label>类型：</label>
				<select name="type"  class="combox" >
					<option value="menu">menu</option>
					<option value="button">button</option>
				</select>
			</p>
			<p>
				<label>资源路径：</label>
				<input name="url"  type="text" value="${res.url }"  alt="请输入路径"/>
			</p>
			<p>
				<label>Parent_id：</label>
				<input name="parent_id" class="required" type="text"  value="${res.parent_id }" />
			</p>
			<p>
				<label>Parent_ids：</label>
				<input name="parent_ids" class="required" type="text"  value="${res.parent_ids }" />
			</p>
			<p>
				<label>Permission：</label>
				<input name="permission" class="required" type="text"  value="${res.permission }" />
			</p>
			<p>
				<label>状态：</label>
				<select class="combox" name="available">
					<option value="true">true</option>
					<option value="fale">false</option>
				</select>
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