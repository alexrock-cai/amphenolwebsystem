<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${CONTEXT_PATH}/user/userView">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this)" action="${CONTEXT_PATH}/user/search" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户搜索：<input type="text" name="words" value="${words }"/>
				</td>
				<td>
					<select class="combox" name="key">
						<option value="username">登录名</option>
						<option value="name">姓名</option>
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
			<li><a class="add" href="${CONTEXT_PATH}/user/openUserForm" target="dialog" rel="user_add"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${CONTEXT_PATH}/user/delete" class="delete"><span>批量删除</span></a></li>			
			<li><a class="edit" href="${CONTEXT_PATH}/user/openUserForm?id={sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="add" href="${CONTEXT_PATH}/main/initUser" target="ajaxTodo"  title="批量创建用户"><span>批量创建用户</span></a></li>
		</ul>
	</div>
	</shiro:hasRole>
	
	<table class="table" width="1200" 
		<shiro:hasRole name="cicsouser">layoutH="85"</shiro:hasRole> 
		<shiro:hasRole name="root">layoutH="110"</shiro:hasRole> >
	
		<thead>
			<tr>
				<th width="2%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="3%"  >id</th>
				<th width="10%" >登录名（工号）</th>
				<th width="10%"   >姓名</th>
				<th width="10%"   >站别</th>
				<th width="10%" >拥有角色</th>
				<th width="10%" >用户状态</th>
				<th width="10%" >部门</th>
				<th width="10%" >操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${userlist}" var="u">
			<tr target="sid_user" rel="${u.id }">
				<td><input name="ids" value="${u.id }" type="checkbox"></td>
				<td>${u.id }</td>
				<td>${u.username }</td>
				<td>${u.name }</td>
				<td>${u.station }</td>
				<td>${u.rolenames }</td>
				<td>${u.locked }</td>
				<td>${u.organizationid}</td>
				
				
				<td>
					
					<a title="删除" target="ajaxTodo" href="${CONTEXT_PATH}/user/delete?id=${u.id }" class="btnDel" >删除</a>
					<a title="编辑" target="dialog" rel="user_edit" href="${CONTEXT_PATH}/user/openUserForm?id=${u.id }" class="btnEdit">编辑</a>
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
