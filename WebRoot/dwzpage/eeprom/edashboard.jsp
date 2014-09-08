<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h2 class="contentTitle">EEPROM Dashboard</h2>
<div class="pageContent sortDrag" selector="h1" layoutH="50">
	<div class="panel" defH="50">
		<h1>操作信息</h1>
		<div>			
			<ul class="rightTools">
				<li><a class="button" target="dialog" href="${CONTEXT_PATH}/eeprom/openUploadForm?filetype=tagin" mask="true"><span>导入TAG IN 数据</span></a></li>
				<li><a class="button" target="dialog" href="${CONTEXT_PATH}/eeprom/openUploadForm?filetype=shipdata" mask="true"><span>导入发货数据</span></a></li>
				<li><a class="button" target="dialog" href="${CONTEXT_PATH}/eeprom/checkPkgInput" mask="true"><span>入库检查</span></a></li>
				<li><a class="button" target="ajaxTodo" href="${CONTEXT_PATH}/eeprom/checkShipdata" mask="true"><span>出货检查</span></a></li>
			</ul>
		</div>
	</div>

	<div class="panel close collapse" defH="150">
		<h1>问题板子清单</h1>
		<div>
			<div class="pageContent">
	<table class="table" width="1200" layoutH="85" >
		<thead>
			<tr>
				<th width="4%">id</th>
				<th width="5%">customer_name</th>
				<th width="5%">customer_sn</th>
				<th width="8%">pn</th>
				<th width="5%">wo</th>
				<th width="5%">hasprogram</th>
				<th width="10%">hasverify</th>

			</tr>
		</thead>	
		<tbody>
			<c:forEach items="${noEepromList}" var="eeprom">
			<tr>
				<td style="text-align:left;"><c:out value="${eeprom.id}" default=""/></td>
				<td style="text-align:left;">${eeprom.customer_name}</td>
				<td style="text-align:left;">${eeprom.customer_sn}</td>
				<td style="text-align:left;">${eeprom.pn}</td>
				<td style="text-align:left;">${eeprom.wo}</td>
				<td style="text-align:left;">${eeprom.hasprogram}</td>
				<td style="text-align:left;">${eeprom.hasverify}</td>

			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
			
		</div>


	
</div>

