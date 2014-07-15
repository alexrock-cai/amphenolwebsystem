<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
#moneyFormTable td {
	padding-right: 10px;
}
</style>
  
<div id="epromtab" class="tabPane">
	<form id="myForm"  data-dojo-type="dijit/form/Form" >
		<h1>Eprom File Path Setting</h1>
		<div id="tt" class="formInfo">本页设置eprom 工作岗位数据表的路径，并启动监测程序</div>
		<table id="epromFormTable">
			<tr>
				<td><label for="RootPath">Root Path</label></td>
				<td><input type="text" name="RootPath"
					data-dojo-type="dijit/form/TextBox" /></td>
			</tr>

			<tr>
	        	<td><label for="LogFileType">Log File Type:</label></td>
	        	<td>
	        		<select id="fs2" name="LogFileType" data-dojo-type="dijit/form/FilteringSelect">
	            		<option value="A">TypeA(KV118)</option>
	            		<option value="B">TypeB(KV113,115,116)</option>
	            		<option value="C">TypeC(KV034)</option>
	            		<option value="D">TypeD(excel)</option>
	          		</select>
	        	</td>
	      	</tr>
			<tr>
	        	<td><label for="WorkStationName">WorkStationName:</label></td>
	        	<td>
	        		<select id="fs1" name="WorkStationName" data-dojo-type="dijit/form/FilteringSelect">
	            		<option value="DTS">DTS</option>
	            		<option value="Shipment">Shipment</option>
	            		<option value="Program">Program</option>
	            		<option value="Verify">Verify</option>
	          		</select>
	        	</td>
	      	</tr>

			<tr>
				
				<td>
					<button type="button" data-dojo-type="dijit/form/Button" class="commandButton"> <span>Submit</span>
						<script type="dojo/on" data-dojo-event="click">
							dojo.xhrGet({
								url:"/eprom/setPath",
								handle:"json",
								form:"myForm",
								timeoutSeconds:3000,
								load:function(response,ioArgs){alert(JSON.parse(response).setPathStatus);},
								error:function(type,error){alert(error.message);},
								timeout:function(type){alert("time out");}
							});
						</script>
					</button>
					</td>
					<td>
					<button type="reset" data-dojo-type="dijit/form/Button" class="commandButton"> Reset </button>
					</td>
				<tr>
					<td>
					<button type="button" data-dojo-type="dijit/form/Button" class="commandButton"> <span>StartSaveExcel</span>
						<script type="dojo/on" data-dojo-event="click">
							dojo.xhrGet({
								url:"/eprom/saveExcel2",
								handle:"json",
								timeoutSeconds:3000,
								load:function(response,ioArgs){alert(JSON.parse(response).message);},
								error:function(type,error){alert(error.message);},
								timeout:function(type){alert("time out");}
							});
						</script>
					</button>
				</td>
				<td>
					<button type="button" data-dojo-type="dijit/form/Button" class="commandButton"> <span>StartProgramLog</span>
						<script type="dojo/on" data-dojo-event="click">
							dojo.xhrGet({
								url:"/eprom/epromProgCheck",
								handle:"json",
								timeoutSeconds:3000,
								load:function(response,ioArgs){alert(JSON.parse(response).message);},
								error:function(type,error){alert(error.message);},
								timeout:function(type){alert("time out");}
							});
						</script>
					</button>
				</td>
			</tr>
			<tr>
				<td>
					<button type="button" data-dojo-type="dijit/form/Button" class="commandButton"> <span>StartVerifyLog</span>
						<script type="dojo/on" data-dojo-event="click">
							dojo.xhrGet({
								url:"/eprom/epromVerifyCheck",
								handle:"json",
								timeoutSeconds:3000,
								load:function(response,ioArgs){alert(JSON.parse(response).message);},
								error:function(type,error){alert(error.message);},
								timeout:function(type){alert("time out");}
							});
						</script>
					</button>
				</td>
				<td>
					<button type="button" data-dojo-type="dijit/form/Button" class="commandButton"> <span>StartShipDataSave</span>
						<script type="dojo/on" data-dojo-event="click">
							dojo.xhrGet({
								url:"/eprom/saveShipmentData",
								handle:"json",
								timeoutSeconds:3000,
								load:function(response,ioArgs){alert(JSON.parse(response).message);},
								error:function(type,error){alert(error.message);},
								timeout:function(type){alert("time out");}
							});
						</script>
					</button>
				</td>
			</tr>
			<tr>
				<td>
					<button type="button" data-dojo-type="dijit/form/Button" class="commandButton"> <span>StartCheck</span>
						<script type="dojo/on" data-dojo-event="click">
							dojo.xhrGet({
								url:"/eprom/startCheck",
								handle:"json",
								timeoutSeconds:3000,
								load:function(response,ioArgs){alert(JSON.parse(response).message);},
								error:function(type,error){alert(error.message);},
								timeout:function(type){alert("time out");}
							});
						</script>
					</button>
				</td>
			</tr>
		</table>
		
	</form>

</div>
	<div data-dojo-type="dijit/Dialog" id="pathsetdialog" data-dojo-props="title: 'PathSet...', href: '/pages/apps/dialogEpromSetPath.html'"></div>
	<button data-dojo-type="dijit/form/Button" class="commandButton">Path Set Form
		<script type="dojo/on" data-dojo-event="click">
		demo.registry.byId("pathsetdialog").show();
		</script>
	</button>
<div data-dojo-type="dijit/layout/BorderContainer" id="epromGrid" 
	data-dojo-props="liveSplitters: true, design: 'sidebar'">
		<div data-dojo-type="dojo/data/ItemFileReadStore" id="epromGridStore"
			data-dojo-id="demo.epromGridStore" style="display: none"
			data-dojo-props="url: '/eprom/getPathList'">
		</div>
		<div data-dojo-type="dijit/layout/ContentPane" id="epromGridGridPane"
			data-dojo-props="region: 'center'">
			<table data-dojo-type="dojox/grid/DataGrid" id="epromPathListGrid"
				data-dojo-props="store: demo.epromGridStore, noDataMessage: 'No Files...'">
				<thead>
					<tr>
						<th field="icon" width="30px" formatter="demo.formatterIcon">&nbsp;</th>
						<th field="rootPath" width="200px">RootPath</th>
						<th field="workStationName" width="110px">WorkStationName</th>
						<th field="logFileType" width="75px" styles="text-align: right;">LogFileType</th>
						<th field="totalFiles" width="auto">TotalFiles</th>
					</tr>
				</thead>
			</table>
		</div>

</div>