<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xml:lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<title>Amphenol-GIS APPS</title>
	<link rel="shortcut icon" href="images/favicon.png" />

	<link rel="stylesheet" href="${CONTEXT_PATH}/pages/apps/demo.css" type="text/css" />
	<script type="text/javascript">
		dojoConfig = {
			//async: true,
			pareOnLoad:true,
			//isDebug:true,

		};
	</script>
	<script type="text/javascript" src="${CONTEXT_PATH}/js/dojo-release-1.10.0/dojo/dojo.js"></script>
	<script type="text/javascript" src="${CONTEXT_PATH}/js/pdfobject.js"></script>
	<script type="text/javascript" src="${CONTEXT_PATH}/js/src.js" charset="utf-8"></script>
	<script type="text/javascript" src="${CONTEXT_PATH}/js/gridxpagesrequire.js" charset="utf-8"></script>
	

	

</head>
<body class="claro">
	<div id="preloader">Loading Application...</div>
	<!-- Using Declarative Require to "hang" some modules off demo object for declarative scripting -->
	<script type="dojo/require">
	  "demo.dom": "dojo/dom",
	  "demo.registry": "dijit/registry"
	</script>
	<div data-dojo-type="dijit/layout/BorderContainer" id="mainContainer"
		data-dojo-props="gutters:true">
		<div data-dojo-type="dijit/layout/ContentPane" id="headerPane"
			data-dojo-props="splitter:false, region:'top'">
			
			<div style="float:left;">Amphenol-GIS Web System</div>
			<div style="float:right;font-size:12px">欢迎 <c:out value="${user.getStr('username')}"></c:out> <a href="${CONTEXT_PATH}/logout">退出</a></div>
			
			</div>
		<div data-dojo-type="dijit/layout/BorderContainer" id="mainSplitter"
			data-dojo-props="liveSplitters: false, design: 'sidebar', region: 'center'">
			<div data-dojo-type="dijit/layout/AccordionContainer"
				id="leftAccordion" data-dojo-props="minSize: 20, region: 'leading'">
				
				<!--功能模块  -->
				<shiro:hasPermission name="user:*">
				<div data-dojo-type="dijit/layout/ContentPane" id="ap1"
					data-dojo-props="title: 'EEPROM', href:'${CONTEXT_PATH}/pages/apps/main/eeprom_main.jsp'"
					class="paneAccordion"></div>
				</shiro:hasPermission>
				<!-- WI功能模块 -->	
				<shiro:hasAnyRoles name="root,oracleuser,cicsouser">
				<div data-dojo-type="dijit/layout/ContentPane" id="ap2"
					data-dojo-props="title: 'WI', href:'${CONTEXT_PATH}/pages/apps/main/dcc_main.jsp'"
					class="paneAccordion"></div>
				</shiro:hasAnyRoles>
				
	
				<!-- 用户管理功能模块 -->
				<shiro:hasAnyRoles name="root,adminuser">
				<div data-dojo-type="dijit/layout/ContentPane" id="ap4"
					data-dojo-props="title: '用户管理', href:'${CONTEXT_PATH}/pages/apps/main/admin_main.jsp'"
					class="paneAccordion"></div>
				</shiro:hasAnyRoles>
				<!-- 功能模块 -->
				
				<div data-dojo-type="dijit/layout/ContentPane" id="ap3"
					data-dojo-props="title: 'Other', href:'${CONTEXT_PATH}/pages/apps/main/other.jsp'"
					class="paneAccordion"></div>


			</div>
			<!-- 主界面内容框架 -->
			<div data-dojo-type="dijit/layout/TabContainer" id="mainTabContainer"
				data-dojo-props="region: 'center'">
				<div data-dojo-type="dijit/layout/ContentPane" id="tabWelcome"
					data-dojo-props="title: 'Welcome', href:'${CONTEXT_PATH}/pages/apps/main/tabWelcome.html'"></div>
			</div>
		</div>
	</div>
</body>
</html>