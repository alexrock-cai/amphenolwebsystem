<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${CONTEXT_PATH}/user/stationView">
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
					设备搜索：<input type="text" id="words" name="words" value="${words }"/>
				</td>
				<td>
					<select class="combox" name="key">
						<option value="equipment_id">设备名</option>
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
			<li><a class="add" href="${CONTEXT_PATH}/user/openStationForm" target="dialog" rel="station_add"><span>添加新设备</span></a></li>		
			<li><a class="edit" href="${CONTEXT_PATH}/user/openStationForm?id={sid_station}" target="dialog" warn="请选择一个站别"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="add" href="${CONTEXT_PATH}/user/openStationForm" target="dialog" rel="station_add"><span>添加PM计划</span></a></li>
		</ul>
	</div>
	</shiro:hasRole>
	
	<table class="table" width="1200" 
		<shiro:hasRole name="user">layoutH="85"</shiro:hasRole> 
		<shiro:hasRole name="root">layoutH="110"</shiro:hasRole> >
	
		<thead>
			<tr >
				<th width="2%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="3%"  >id</th>
				<th width="10%" >Machine_Photo</th>
				<th width="10%"   >Machine_ID</th>
				<th width="10%"   >Machine_Model</th>
				<th width="10%"   >Machine_Type</th>
				<th width="10%"   >Register_Date</th>
				<th width="10%"   >Made_Date</th>
				<th width="10%"   >Owner</th>
				<th width="10%"   >PM</th>
				<th width="10%"   >CAC</th>
				<th width="10%" >操作</th>
			</tr>
		</thead>
		<tbody>
		
			<tr target="sid_station" rel="1" height="auto">
				<td><input name="ids" value="1" type="checkbox"></td>
				<td>1</td>
				<td>888</td>
				<td>ATB-36</td>
				<td>AEP-12T</td>
				<td>Press-Fit</td>
				<td>2006年</td>	
				<td>2000年6月</td>	
				
				<td>王永生</td>	
				<td height="auto">
					<p>Monthly PM</p>
					<p>Quanterly PM</p>
					<p>Yearly PM</p>
				</td>
				<td>Calibration</td>							
				<td>
					
					<a title="删除" target="ajaxTodo" href="${CONTEXT_PATH}/user/deleteStation?id=${station.id }" class="btnDel" >删除</a>
					<a title="编辑" target="dialog" rel="user_edit" href="${CONTEXT_PATH}/user/openStationForm?id=${station.id }" class="btnEdit">编辑</a>
				</td>
				
			</tr>
		<tr target="sid_station" rel="1" height="auto">
				<td><input name="ids" value="1" type="checkbox"></td>
				<td>1</td>
				<td>888</td>
				<td>ATB-36</td>
				<td>AEP-12T</td>
				<td>Press-Fit</td>
				<td>2006年</td>	
				<td>2000年6月</td>	
				
				<td>王永生</td>	
				<td height="auto">
					<p>Monthly PM</p>
					<p>Quanterly PM</p>
					<p>Yearly PM</p>
				</td>
				<td>Calibration</td>							
				<td>
					
					<a title="删除" target="ajaxTodo" href="${CONTEXT_PATH}/user/deleteStation?id=${station.id }" class="btnDel" >删除</a>
					<a title="编辑" target="dialog" rel="user_edit" href="${CONTEXT_PATH}/user/openStationForm?id=${station.id }" class="btnEdit">编辑</a>
				</td>
				
			</tr>
			<tr target="sid_station" rel="1" height="auto">
				<td><input name="ids" value="1" type="checkbox"></td>
				<td>1</td>
				<td>888</td>
				<td>ATB-36</td>
				<td>AEP-12T</td>
				<td>Press-Fit</td>
				<td>2006年</td>	
				<td>2000年6月</td>	
				
				<td>王永生</td>	
				<td height="auto">
					<p>Monthly PM</p>
					<p>Quanterly PM</p>
					<p>Yearly PM</p>
				</td>
				<td>Calibration</td>							
				<td>
					
					<a title="删除" target="ajaxTodo" href="${CONTEXT_PATH}/user/deleteStation?id=${station.id }" class="btnDel" >删除</a>
					<a title="编辑" target="dialog" rel="user_edit" href="${CONTEXT_PATH}/user/openStationForm?id=${station.id }" class="btnEdit">编辑</a>
				</td>
				
			</tr>
			<tr target="sid_station" rel="1" height="auto">
				<td><input name="ids" value="1" type="checkbox"></td>
				<td>1</td>
				<td>888</td>
				<td>ATB-36</td>
				<td>AEP-12T</td>
				<td>Press-Fit</td>
				<td>2006年</td>	
				<td>2000年6月</td>	
				
				<td>王永生</td>	
				<td height="auto">
					<p>Monthly PM</p>
					<p>Quanterly PM</p>
					<p>Yearly PM</p>
				</td>
				<td>Calibration</td>							
				<td>
					
					<a title="删除" target="ajaxTodo" href="${CONTEXT_PATH}/user/deleteStation?id=${station.id }" class="btnDel" >删除</a>
					<a title="编辑" target="dialog" rel="user_edit" href="${CONTEXT_PATH}/user/openStationForm?id=${station.id }" class="btnEdit">编辑</a>
				</td>
				
			</tr>
			<tr target="sid_station" rel="1" height="auto">
				<td><input name="ids" value="1" type="checkbox"></td>
				<td>1</td>
				<td>888</td>
				<td>ATB-36</td>
				<td>AEP-12T</td>
				<td>Press-Fit</td>
				<td>2006年</td>	
				<td>2000年6月</td>	
				
				<td>王永生</td>	
				<td height="auto">
					<p>Monthly PM</p>
					<p>Quanterly PM</p>
					<p>Yearly PM</p>
				</td>
				<td>Calibration</td>							
				<td>
					
					<a title="删除" target="ajaxTodo" href="${CONTEXT_PATH}/user/deleteStation?id=${station.id }" class="btnDel" >删除</a>
					<a title="编辑" target="dialog" rel="user_edit" href="${CONTEXT_PATH}/user/openStationForm?id=${station.id }" class="btnEdit">编辑</a>
				</td>
				
			</tr>
			<tr target="sid_station" rel="1" height="auto">
				<td><input name="ids" value="1" type="checkbox"></td>
				<td>1</td>
				<td>888</td>
				<td>ATB-36</td>
				<td>AEP-12T</td>
				<td>Press-Fit</td>
				<td>2006年</td>	
				<td>2000年6月</td>	
				
				<td>王永生</td>	
				<td height="auto">
					<p>Monthly PM</p>
					<p>Quanterly PM</p>
					<p>Yearly PM</p>
				</td>
				<td>Calibration</td>							
				<td>
					
					<a title="删除" target="ajaxTodo" href="${CONTEXT_PATH}/user/deleteStation?id=${station.id }" class="btnDel" >删除</a>
					<a title="编辑" target="dialog" rel="user_edit" href="${CONTEXT_PATH}/user/openStationForm?id=${station.id }" class="btnEdit">编辑</a>
				</td>
				
			</tr>
			<tr target="sid_station" rel="1" height="auto">
				<td><input name="ids" value="1" type="checkbox"></td>
				<td>1</td>
				<td>888</td>
				<td>ATB-36</td>
				<td>AEP-12T</td>
				<td>Press-Fit</td>
				<td>2006年</td>	
				<td>2000年6月</td>	
				
				<td>王永生</td>	
				<td height="auto">
					<p>Monthly PM</p>
					<p>Quanterly PM</p>
					<p>Yearly PM</p>
				</td>
				<td>Calibration</td>							
				<td>
					
					<a title="删除" target="ajaxTodo" href="${CONTEXT_PATH}/user/deleteStation?id=${station.id }" class="btnDel" >删除</a>
					<a title="编辑" target="dialog" rel="user_edit" href="${CONTEXT_PATH}/user/openStationForm?id=${station.id }" class="btnEdit">编辑</a>
				</td>
				
			</tr>
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