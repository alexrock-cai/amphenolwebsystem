<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$("#sn").focus();
</script>

<div id="pkginputBox" class="unitBox">
<div class="pageHeader">
	<form id="pagerForm" class="pageForm required-validate"  onsubmit="return divSearch(this,'pkginputBox');" action="${CONTEXT_PATH}/eeprom/pkgInput" method="post">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />

	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td colspan="2">
					产品序列号：<input type="text" id="sn" name="customer_sn" class="required" />
				</td>
				
				<td align="center"><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<shiro:hasAnyRoles name="root">
	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasRole name="root">
			<li><a class="icon" href="${CONTEXT_PATH}/eeprom/checkPkgList"  target="ajaxTodo" title="确定要入库检查吗?"><span>入库检查</span></a></li>
			</shiro:hasRole>
		</ul>
	</div>
	</shiro:hasAnyRoles>
	
	<table class="table" width="1200" layoutH="135" >
	
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="22" orderField="id" >id</th>
				<th width="130" >产品序列号</th>
				<th width="80" >包装扫描时间</th>
				<shiro:hasAnyRoles name="root">
				<th width="120" >客户</th>
				<th width="40"  >工单号</th>
				<th width="80" >部件号</th>
				<th width="60" >是否Program</th>
				<th width="70" >是否Verify</th>
				<th width="70" >是否TAG IN</th>
				
				
				<th width="70" orderField="operate">操作</th>
				</shiro:hasAnyRoles>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${pkglist}" var="pkg" >
			<tr target="pkg_id" rel="${pkg.id }">
				<td><input name="ids" value="${pkg.id }" type="checkbox"></td>
				<td>${pkg.id }</td>
				<td>${pkg.customer_sn }</td>
				<td>${pkg.timestamp }</td>
				<shiro:hasAnyRoles name="root">
				<td>${pkg.customer_name }</td>
				<td>${pkg.wo }</td>
				<td>${pkg.pn }</td>
				<td>${pkg.hasprogram eq null?"未检查":pkg.hasprogram}</td>
				<td>${pkg.hasverify eq null?"未检查":pkg.hasverify}</td>
				<td>${pkg.hastagin eq null?"未检查":pkg.hastagin}</td>
				
				<td>
					<a title="删除" target="ajaxTodo" href="${CONTEXT_PATH}/eeprom/deletePkgInput?id=${pkg.id }" class="btnDel" >删除</a>
				</td>
				</shiro:hasAnyRoles>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value},'pkginputBox')">
				<option value="10" <c:if test="${numPerPage == '10'}">selected</c:if> >10</option>
				<option value="15" <c:if test="${numPerPage == '15'}">selected</c:if> >15</option>
				<option value="20" <c:if test="${numPerPage == '20'}">selected</c:if> >20</option>
				<option value="25" <c:if test="${numPerPage == '25'}">selected</c:if> >25</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" rel="pkginputBox" targetType="navTab" totalCount="${totalCount }" numPerPage="${numPerPage}" pageNumShown="10" currentPage="${currentPage }"></div>

	</div>
</div>
</div>

			
	
</div>
