<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${CONTEXT_PATH}/resource/resView">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this)" action="${CONTEXT_PATH}/resource/search" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户搜索：<input type="text" name="words" value="${words }"/>
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
   <shiro:hasRole name="root">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${CONTEXT_PATH}/resource/openResForm" target="dialog" rel="user_add"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${CONTEXT_PATH}/resource/delete" class="delete"><span>批量删除</span></a></li>			
			<li><a class="edit" href="${CONTEXT_PATH}/resource/openResForm?id={sid_user}" target="dialog" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<!--  
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			-->
		</ul>
	</div>
	</shiro:hasRole>
	
	<table class="table" width="1200" 
		<shiro:hasRole name="cicsouser">layoutH="85"</shiro:hasRole> 
		<shiro:hasRole name="root">layoutH="110"</shiro:hasRole> >
	
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="2%"  >id</th>
				<th width="10%" >资源名</th>
				<th width="10%" >类型</th>
				<th width="30%" >URL</th>
				<th width="10%" >上一级ID</th>	
				<th width="10%" >所有上级ID</th>
				<th width="10%" >权限标示</th>
				<th width="10%" >是否激活</th>
				<th width="10%" >操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${reslist}" var="res">
			<tr target="sid_user" rel="${res.id }">
				<td><input name="ids" value="${res.id }" type="checkbox"></td>
				<td>${res.id }</td>
				<td>${res.name }</td>
				<td>${res.type }</td>
				<td>${res.url }</td>
				<td>${res.parent_id }</td>
				<td>${res.parent_ids }</td>
				<td>${res.permission }</td>
				<td>${res.available }</td>
				<td>
					
					<a title="删除" target="ajaxTodo" href="${CONTEXT_PATH}/resource/delete?id=${res.id }" class="btnDel" >删除</a>
					<a title="编辑" target="dialog" rel="user_edit" href="${CONTEXT_PATH}/resource/openResForm?id=${res.id }" class="btnEdit">编辑</a>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value});">
				<option value="10" <c:if test="${numPerPage == '10'}">selected</c:if> >10</option>
				<option value="15" <c:if test="${numPerPage == '15'}">selected</c:if> >15</option>
				<option value="20" <c:if test="${numPerPage == '20'}">selected</c:if> >20</option>
				<option value="25" <c:if test="${numPerPage == '25'}">selected</c:if> >25</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${totalCount }" numPerPage="${numPerPage }" pageNumShown="10" currentPage="${currentPage }"></div>

	</div>
</div>
