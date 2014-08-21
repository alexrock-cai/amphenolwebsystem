<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	<form method="post" action="${CONTEXT_PATH}/user/create" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户名：</label>
				<input name="username" class="required" type="text"   alt="请输入用户名"/>
			</p>
			<p>
				<label>姓名：</label>
				<input name="name" class="required" type="text"  alt="请输入姓名"/>
			</p>
			<p>
				<label>密码：</label>
				<input id="user_edit_pwd" type="password" name="password"  class="required alphanumeric" minlength="6" maxlength="20" alt="字母、数字、下划线 6-20位"/>
			</p>
			<p>
				<label>确认密码：</label>
				<input type="password" name="repassword" class="required" equalto="#user_edit_pwd" />
			</p>
			<p>
				<label>用户角色：</label>
				<input type="hidden" name="roleLookup.id" value=""/>
				<input name="roleLookup.roleName" type="text" class="required" size="25" />
				<a class="btnLook" href="${CONTEXT_PATH}/role/roleLookup?pageNum=1&numPerPage=100" lookupGroup="roleLookup">查找带回</a>		
			</p>
			<p>
				<label>上传照片：</label>
				<input name="img" type="file" />
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

