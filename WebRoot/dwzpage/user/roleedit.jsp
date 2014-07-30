<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	<form method="post" action="${CONTEXT_PATH}/role/update" class="pageForm required-validate"  onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input name="id" type="hidden" value="${r.id }"/>
		
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>角色名：</label>
				<input name="role" class="required" type="text" value="${r.role }"  alt="请输入角色名"/>
			</p>
			<p>
				<label>角色描述：</label>
				<input name="description" class="required" type="text"  value="${r.description }" alt="请输入角色描述"/>
			</p>
			
			<p>
				<label>角色权限列表：</label>
				<input type="hidden" name="resLookup.id" value=""/>
				<input name="resLookup.resName" type="text" class="required" size="25" value="${r.resourcenames }"/>
				<a class="btnLook" href="${CONTEXT_PATH}/resource/resLookup?pageNum=1&numPerPage=20" lookupGroup="resLookup">查找带回</a>		
			</p>
			<p>
				<label>状态：</label>
				<select class="combox" name="available" >
					<option value="true" selected="selected">true</option>
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

