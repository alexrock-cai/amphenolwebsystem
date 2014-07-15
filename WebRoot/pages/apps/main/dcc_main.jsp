<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<shiro:hasRole name="root">
<button type="button" data-dojo-type="dijit/form/Button" class="commandButton">
  <span>WI 管理</span>
	<script type="dojo/on" data-dojo-event="click">
		demo.addTab("mainTabContainer", "${CONTEXT_PATH}/pages/apps/wi/wilist.jsp", "WI 管理", true);
	</script>
</button>

<button type="button" data-dojo-type="dijit/form/Button" class="commandButton">
  <span>WI init</span>
	<script type="dojo/on" data-dojo-event="click">
		demo.addTab("mainTabContainer", "${CONTEXT_PATH}/dcc/initWI", "WI init", true);
	</script>
</button>
</shiro:hasRole>
<shiro:authenticated>

<button type="button" data-dojo-type="dijit/form/Button" class="commandButton">
  <span>My WI</span>
	<script type="dojo/on" data-dojo-event="click">
		demo.addTab("mainTabContainer", "${CONTEXT_PATH}/pages/apps/wi/mywilist.jsp", "My WI", true);
	</script>
</button>
</shiro:authenticated>