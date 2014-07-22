<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h1>总共有${total}块产品未经过EEPROM，清单如下：</h1>
</div>
<div >
	<table >
		<tbody>
			<tr>
				<th width="4%">id</th>
				<th width="5%">customer_name</th>
				<th width="5%">customer_sn</th>
				<th width="8%">pn</th>
				<th width="5%">wo</th>
				<th width="5%">hasprogram</th>
				<th width="10%">hasverify</th>

			</tr>
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
