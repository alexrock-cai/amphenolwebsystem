<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- 侧边菜单导航栏 -->
<button type="button" data-dojo-type="dijit/form/Button" class="commandButton">
  <span>EEPROM VERIFY</span>
	<script type="dojo/on" data-dojo-event="click">
		demo.addTab("mainTabContainer", "${CONTEXT_PATH}/pages/apps/eprom/epromList.html", "EEPROM VERIFY", true);
	</script>
</button>
<button type="button" data-dojo-type="dijit/form/Button" class="commandButton">
  <span>Test</span>
	<script type="dojo/on" data-dojo-event="click">
		demo.addTab("mainTabContainer", "${CONTEXT_PATH}/pages/test/testgridx.html", "Test", true);
	</script>
</button>
<button type="button" data-dojo-type="dijit/form/Button" class="commandButton">
  <span>Older Test</span>
	<script type="dojo/on" data-dojo-event="click">
		demo.addTab("mainTabContainer", "${CONTEXT_PATH}/pages/test/epromForm.jsp", "Older Test", true);
	</script>
</button>
<button type="button" data-dojo-type="dijit/form/Button" class="commandButton">
  <span>MenuBar</span>
	<script type="dojo/on" data-dojo-event="click">
		demo.addTab("mainTabContainer", "${CONTEXT_PATH}/pages/apps/menuBar.html", "Menu", true);
	</script>
</button>