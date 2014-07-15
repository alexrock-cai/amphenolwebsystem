<!-- 用户管理模块 -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- 用户列表 -->
<button type="button" data-dojo-type="dijit/form/Button" class="commandButton">
  <span>User List</span>
	<script type="dojo/on" data-dojo-event="click">
		demo.addTab("mainTabContainer", "${CONTEXT_PATH}/pages/apps/user/userlist.jsp", "用户列表", true);
	</script>
</button>
<!-- 用户权限列表 -->
<button type="button" data-dojo-type="dijit/form/Button" class="commandButton">
  <span>Role List</span>
	<script type="dojo/on" data-dojo-event="click">
		demo.addTab("mainTabContainer", "${CONTEXT_PATH}/pages/apps/user/rolelist.jsp", "用户权限列表", true);
	</script>
</button>
<!-- 用户权限资源列表 -->
<button type="button" data-dojo-type="dijit/form/Button" class="commandButton">
  <span>Resource List</span>
	<script type="dojo/on" data-dojo-event="click">
		demo.addTab("mainTabContainer", "${CONTEXT_PATH}/pages/apps/user/resourcelist.jsp", "用户权限资源列表", true);
	</script>
</button>