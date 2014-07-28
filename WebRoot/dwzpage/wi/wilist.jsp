<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${CONTEXT_PATH}/dcc/search" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					病人编号：<input type="text" name="words" />
				</td>
				<td>
					<select class="combox" name="key">
						<option value="pn">产品代码</option>
						<option value="customer">客户</option>
						<option value="station">站别</option>
					</select>
				</td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${CONTEXT_PATH}/dcc/create" target="navTab"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${CONTEXT_PATH}/dcc/delete" class="delete"><span>批量删除默认方式</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="${CONTEXT_PATH}/dcc/delete" class="delete"><span>批量删除逗号分隔</span></a></li>
			<li><a class="edit" href="${CONTEXT_PATH}/dcc/view?uid={sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1200" layoutH="110">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="22" orderField="id" >id</th>
				<th width="120" orderField="customer" class="asc">客户</th>
				<th width="40"   orderField="station">站别</th>
				<th width="80" orderField="pn">产品代码</th>
				<th width="130" orderField="type">文件类别</th>
				<th width="60" align="center" orderField="rev">版本号</th>
				<th width="70" orderField="filepath">文件路径</th>
				<th width="70" orderField="filename">文件名</th>
				<th width="70" orderField="lastmodify">更新时间</th>
				<th width="70" orderField="operate">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${wilist}" var="wi">
			<tr target="sid_user" rel="${wi.id }">
				<td><input name="ids" value="${wi.id }" type="checkbox"></td>
				<td>${wi.id }</td>
				<td>${wi.customer }</td>
				<td>${wi.station }</td>
				<td>${wi.pn }</td>
				<td>${wi.type }</td>
				<td>${wi.rev }</td>
				<td>${wi.filepath }</td>
				<td>${wi.filename }</td>
				<td>${wi.lastmodify }</td>
				<td>
					
					<a title="删除" target="ajaxTodo" href="${CONTEXT_PATH}/dcc/delete?id=${wi.id }" class="btnDel" >删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=${wi.id }" class="btnEdit">编辑</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value},'wilist_pagination')">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" rel="wilist_pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>

	</div>
</div>
