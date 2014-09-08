<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	<form method="post" action="${CONTEXT_PATH}/eeprom/uploadData" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>数据类型：</label>
				
				<select name="filetype" style="width:150px">
					
					<option value="tagin" ${filetype eq "tagin"?"selected":"" }>TagIn 数据</option>
					<option value="shipdata" ${filetype eq "shipdata"?"selected":""}>发货数据</option>
				
				</select>
			</p>
			
			<p>
				<label>上传文件：</label>
				<input name="datafile" type="file" />
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

