<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="programListBox" class="unitBox">
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return divSearch(this,'programListBox');" action="${CONTEXT_PATH}/eeprom/programView" method="post">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					产品搜索：<input type="text" name="words" value="${words }"/>
				</td>
				<td>
					<select class="combox" name="key">
						<option value="customer_sn" ${key eq "pn"?"selected":"" } >产品序列号</option>
						<option value="customer" ${key eq "customer"?"selected":"" } >客户</option>
					
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
				<th width="22">id</th>
				<th width="70">客户产品序列号</th>
				<th width="100">时间戳</th>
				<th width="70">烧录状态</th>
				<th width="70">left_status</th>
				<th width="70">right_status</th>
				<th width="100">最近一次时间戳</th>
				<th width="70">烧录次数</th>
				<th width="70">操作</th>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${programList}" var="program">
			<tr target="sid_program" rel="${program.id }">
				<td><input name="ids" value="${program.id }" type="checkbox"></td>
				<td>${program.id }</td>
				<td>${program.customer_sn }</td>
				<td>${program.timestamp }</td>
				<td>${program.status }</td>
				<td>${program.left_status }</td>
				<td>${program.right_status }</td>
				<td>${program.last_timestamp }</td>
				<td>${program.program_times }</td>
				<td>
					
					<a title="删除" target="ajaxTodo" href="${CONTEXT_PATH}/dcc/delete?id=${wi.id }" class="btnDel" >删除</a>
					<a title="编辑" target="dialog" href="${CONTEXT_PATH}/dcc/openWiForm?id=${wi.id }" class="btnEdit">编辑</a>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value},'programListBox')">
				<option value="10" <c:if test="${numPerPage == '10'}">selected</c:if> >10</option>
				<option value="15" <c:if test="${numPerPage == '15'}">selected</c:if> >15</option>
				<option value="20" <c:if test="${numPerPage == '20'}">selected</c:if> >20</option>
				<option value="25" <c:if test="${numPerPage == '25'}">selected</c:if> >25</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" rel="programListBox" targetType="navTab" totalCount="${totalCount }" numPerPage="${numPerPage}" pageNumShown="10" currentPage="${currentPage }"></div>

	</div>
</div>
</div>
