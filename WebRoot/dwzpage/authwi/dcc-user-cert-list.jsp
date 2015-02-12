<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="userWiBox" class="unitBox">
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return divSearch(this,'userWiBox');" action="${CONTEXT_PATH}/userDccCert/openDccUserCertList" method="post">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户搜索：<input type="text" id="words" name="words" value="${words }"/>
				</td>
				<td>
					<select class="combox" name="key">
						<option value="username" selected>工号</option>
					</select>
				</td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></td>
			</tr>
		</table>
	</div>
	</form>
</div>

<div class="pageContent">
   <shiro:hasAnyRoles name="root,WICert:supervisor,WICert:test,WICert:atb">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${CONTEXT_PATH}/userDccCert/openUserCert" target="dialog" rel="user_dcc_cert"><span>认证作业员</span></a></li>
			<shiro:hasRole name="root" >	
			<li><a class="edit" href="${CONTEXT_PATH}/userDccCert/userCertEdit?id={sid_user}" target="navTab" warn="请选择一条记录"><span>修改</span></a></li>
			</shiro:hasRole>
		</ul>
	</div>
	</shiro:hasAnyRoles>
	
	<table class="table" width="1200" layoutH="110" >
	
		<thead>
			<tr>
				<th width="2%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="10%" >工号</th>
				<th width="10%" >姓名</th>
				<th width="10%" >站别</th>
				<th width="10%" >产品</th>
				<th width="10%" >认证时间</th>
				<!-- 不用设定有效期 
					<th width="10%" >有效期</th>
					<th width="10%" >过期时间</th>
				-->
				<th width="10%" >认证人</th>
				<th width="10%" >是否过期</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${dccCerts}" var="cert">
			<tr target="sid_user" rel="${cert.id }">
				<td><input name="ids" value="${cert.id }" type="checkbox"></td>
				<td>${cert.user.username }</td>
				<td>${cert.user.name }</td>
				<td>${cert.dcc.station }</td>
				<td>${cert.dcc.pn}</td>
				<td>${cert.certTime }</td>
				<!--  
					<td>${cert.validity}个月</td>
					<td>${cert.validDate}</td>
				-->
				<td>${cert.certUser.username}</td>
				<c:if test="${cert.available }">
					<td style="color:#00FF00" >有效</td>
				</c:if>	
				<c:if test="${!cert.available}">
					<td style="color:#FF0000" >过期</td>
				</c:if>
				
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value},'userWiBox')">
				<option value="10" <c:if test="${numPerPage == '10'}">selected</c:if> >10</option>
				<option value="15" <c:if test="${numPerPage == '15'}">selected</c:if> >15</option>
				<option value="20" <c:if test="${numPerPage == '20'}">selected</c:if> >20</option>
				<option value="25" <c:if test="${numPerPage == '25'}">selected</c:if> >25</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" rel="userWiBox" targetType="navTab" totalCount="${totalCount }" numPerPage="${numPerPage }" pageNumShown="10" currentPage="${currentPage }"></div>

	</div>
</div>
</div>