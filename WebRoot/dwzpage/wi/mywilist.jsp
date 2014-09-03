<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="jbsxBox" class="unitBox">
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return divSearch(this,'jbsxBox');" action="${CONTEXT_PATH}/wi/myWi" method="post">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					WI搜索：<input type="text" name="words" value="${words }"/>
				</td>
				<td>
					<select class="combox" name="key">
						<option value="pn" ${key eq "pn"?"selected":"" } >产品代码</option>
						<option value="customer" ${key eq "customer"?"selected":"" } >客户</option>
						<option value="station" ${key eq "station"?"selected":"" } >站别</option>
						<option value="filename" ${key eq "filename"?"selected":"" } >文件名</option>
					</select>
				</td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	
	
	<table class="table" width="1200" layoutH="85" >
	
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
				<shiro:hasRole name="root">
				<th width="70" orderField="operate">操作</th>
				</shiro:hasRole>
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
				<td><a target="navTab" href="${wi.filepath }" external="true" title="${wi.filename }">查看WI</a></td>
				<td>${wi.filename }</td>
				<td>${wi.lastmodify }</td>
				<shiro:hasRole name="root">
				<td>
					
					<a title="删除" target="ajaxTodo" href="${CONTEXT_PATH}/dcc/delete?id=${wi.id }" class="btnDel" >删除</a>
					<a title="编辑" target="navTab" href="demo_page4.html?id=${wi.id }" class="btnEdit">编辑</a>
				</td>
				</shiro:hasRole>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value},'jbsxBox')">
				<option value="10" <c:if test="${numPerPage == '10'}">selected</c:if> >10</option>
				<option value="20" <c:if test="${numPerPage == '20'}">selected</c:if> >20</option>
				<option value="30" <c:if test="${numPerPage == '30'}">selected</c:if> >30</option>
				<option value="50" <c:if test="${numPerPage == '50'}">selected</c:if> >50</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" rel="jbsxBox" targetType="navTab" totalCount="${totalCount }" numPerPage="${numPerPage}" pageNumShown="10" currentPage="${currentPage }"></div>

	</div>
</div>
</div>
