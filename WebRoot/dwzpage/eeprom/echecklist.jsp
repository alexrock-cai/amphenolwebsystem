<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="echeckListBox" class="unitBox">
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return divSearch(this,'echeckListBox');" action="${CONTEXT_PATH}/eeprom/echeckView" method="post">
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
						<option value="timestamp" ${key eq "timestamp"?"selected":"" } >系统判别时间</option>
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
				<th width="130" >产品序列号</th>
				<th width="120" >客户</th>
				<th width="40"  >工单号</th>
				<th width="80" >部件号</th>
				<th width="70" >系统判别时间</th>
				<th width="60" >是否Program</th>
				<th width="70" >是否Verify</th>
				<th width="70" >是否Tag In</th>
				<th width="70" >是否处理</th>
				<th width="70" >处理人</th>
				<th width="70" >处理内容</th>
				<th width="70" >处理结束时间</th>
				<th width="70">操作</th>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${echeckList}" var="echeck">
			<tr target="sid_echeck" rel="${echeck.id }">
				<td><input name="ids" value="${echeck.id }" type="checkbox"></td>
				<td>${echeck.id }</td>
				<td>${echeck.customer_sn }</td>
				<td>${echeck.customer_name }</td>
				<td>${echeck.wo }</td>
				<td>${echeck.pn }</td>
				<td>${echeck.timestamp }</td>
				<td>${echeck.hasprogram }</td>
				<td>${echeck.hasverify }</td>
				<td>${echeck.hastagin }</td>
				<td>${echeck.dealresult }</td>
				<td>${echeck.dealpeople }</td>
				<td>${echeck.dealcontent }</td>
				<td>${echeck.dealendtime}</td>
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
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value},'echeckListBox')">
				<option value="10" <c:if test="${numPerPage == '10'}">selected</c:if> >10</option>
				<option value="15" <c:if test="${numPerPage == '15'}">selected</c:if> >15</option>
				<option value="20" <c:if test="${numPerPage == '20'}">selected</c:if> >20</option>
				<option value="25" <c:if test="${numPerPage == '25'}">selected</c:if> >25</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" rel="echeckListBox" targetType="navTab" totalCount="${totalCount }" numPerPage="${numPerPage}" pageNumShown="10" currentPage="${currentPage }"></div>

	</div>
</div>
</div>
