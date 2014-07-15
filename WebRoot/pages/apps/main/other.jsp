<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<style type="text/css">
#aboutBox{
	max-width: 600px;
	padding: 1em;
}
</style>
<div data-dojo-type="dijit/Dialog" id="dialogAbout" data-dojo-props="title: 'About Amphenol-GIS Web system'">
	<div id="aboutBox" class="instructionBox">
		<h2>Amphenol-GIS Web system Application</h2>
		<p>Version 1.0<br/>
		build with Jfinal dojo poi shiro <br/>
		Liscensed under BSD and AFL<br/>
		<a href="http://dojotoolkit.org/" target="_blank">http://dojotoolkit.org/</a></p>
	</div>
	<div class="dijitDialogPaneActionBar">
		<button data-dojo-type="dijit/form/Button" id="aboutDialogOk">Ok
			<script type="dojo/on" data-dojo-event="click">
				demo.registry.byId("dialogAbout").hide();
			</script>
		</button>
	</div>
</div>
<button type="button" data-dojo-type="dijit/form/Button" class="commandButton">
	<span>About...</span>
	<script type="dojo/on" data-dojo-event="click">
		demo.registry.byId("dialogAbout").show();
	</script>
</button>
