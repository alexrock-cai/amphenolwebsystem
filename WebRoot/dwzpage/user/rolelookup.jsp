<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" action="${CONTEXT_PATH}/role/roleLookup">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form method="post" action="${CONTEXT_PATH}/role/roleLookup" onsubmit="return dialogSearch(this);">
	<div class="searchBar">
		<ul class="searchContent">
		
			<li>
				<label>部门经理:</label>
				<input class="textInput" name="leader" value="" type="text">
			</li>
				<li>
				<label>上级部门:</label>
				<input class="textInput" name="parentOrg.orgName" value="" type="text">
			</li> 
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" multLookup="roleId" warn="请选择部门">选择带回</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="roleId" /></th>
				<th orderfield="orgName">角色名</th>
				<th orderfield="orgNum">角色描述</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${rolelist}" var="role">
			<tr>
				<td><input type="checkbox" name="roleId" value="{id:'${role.id }', roleName:'${role.role }', roleDescription :'${role.description }'}"/></td>
				<td>${role.role }</td>
				<td>${role.description }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="dialogPageBreak({numPerPage:this.value});">
				<option value="1" <c:if test="${numPerPage == '1'}">selected</c:if> >1</option>
				<option value="5" <c:if test="${numPerPage == '5'}">selected</c:if> >5</option>
				<option value="20" <c:if test="${numPerPage == '20'}">selected</c:if> >20</option>
				<option value="25" <c:if test="${numPerPage == '25'}">selected</c:if> >25</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="dialog" totalCount="${totalCount }" numPerPage="${numPerPage }" pageNumShown="10" currentPage="${currentPage }"></div>

	</div>
</div>