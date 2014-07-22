<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div data-dojo-type="dijit/layout/BorderContainer"
	id="eepConfigListGridx"
	data-dojo-props="liveSplitters: false, design: 'headline'">
		<div id="eepconfigtoolbar1" data-dojo-type="dijit.Toolbar" data-dojo-props="region:'top'">
		
		 <div data-dojo-type="dijit.form.Button" id="eepconfigtoolbar1.new"
         	  data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconNewPage', 
         				  	   showLabel:false,
         				       onClick:function(){
         				       dijit.byId('EepConfigCreateForm').reset();
         				       dijit.byId('EepConfigCreateDialog').show();}">New</div>
         <!-- The following adds a line between toolbar sections-->
         <span data-dojo-type="dijit.ToolbarSeparator"></span>
         
         <div data-dojo-type="dijit.form.Button" id="eepconfigtoolbar1.edit"
         	  data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconWikiword', 
         	  					showLabel:false,
         	  					onClick:function(){
         	  						 gridlist=demo.registry.byId('EepConfigListGridxGrid');
         	  						 id=gridlist.select.row.getSelected();
         	  						 row=gridlist.model.byId(id).rawData;
         	  						dijit.byId('configid').set('value',row.id);
         	  						dijit.byId('configfilepath').set('value',row.filepath);
         	  						dijit.byId('configfunction').set('value',row.function);

         	  						dijit.byId('EepConfigEditDialog').show();
         	  					}">Edit</div>
         	  					
         <div data-dojo-type="dijit.form.Button" id="eepconfigtoolbar1.delete"
         data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconDelete', 
         					showLabel:false,
         					onClick:function(){
         						gridlist=dijit.byId('EepConfigListGridxGrid');
         						id=gridlist.select.row.getSelected();
         						dojo.xhrPost({
         							url:'${CONTEXT_PATH}/eeprom/configDelete',
         							handle:'json',
         							content:{id:id},
         							load:function(response,ioArgs){
         									alert(JSON.parse(response).status);
         									gridlist=dijit.byId('EepConfigListGridxGrid');
         									var store=new dojo.data.ItemFileReadStore({url:'/eeprom/configView'});
											gridlist.model.clearCache();
											gridlist.model.setStore(store);
											gridlist.body.refresh();
         							}
         						});
         					}">Delete</div>
         <!-- The following adds a line between toolbar sections-->
         <span data-dojo-type="dijit.ToolbarSeparator"></span>

		</div>
	<div data-dojo-type="dojo/data/ItemFileReadStore" id="EepConfigListGridxStore"
		data-dojo-id="demo.EepConfigListGridxStore" style="display: none"
		data-dojo-props="url: '${CONTEXT_PATH}/eeprom/configView'"></div>
		
		<!-- 新建配置对话框 -->
		
		<div id="EepConfigCreateDialog" data-dojo-type="dijit/Dialog" data-dojo-props="title:'Create New'" style="display:none;">
			<form id="EepConfigCreateForm" data-dojo-type="dijit/form/Form">
				<div class="dijitDialogPaneContentArea">
					
					<table>
						<tr>
							<td><label for="filepath">路径: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="filepath" ></td>
						</tr>
						<tr>
							<td><label for="function">路径功能: </label></td>
							<td><select data-dojo-type="dijit/form/FilteringSelect"  name="function">
    								<option value="work">工作目录</option>
    								<option value="handled">处理后文件放置目录</option>

									</select></td>
						</tr>
					</table>
					
				</div>
	
				<div class="dijitDialogPaneActionBar">
					<button data-dojo-type="dijit/form/Button" type="button" id="EepConfigCreateFormOkButton"
							data-dojo-props="onClick:function(){
								
								dojo.xhrPost({
									url:'${CONTEXT_PATH}/eeprom/configCreate',
									handle:'json',
									form:'EepConfigCreateForm',
									timeoutSeconds:3000,
									load:function(response,ioArgs){
											alert(JSON.parse(response).status);
											dijit.byId('EepConfigCreateDialog').hide();
											var gridlist=dijit.byId('EepConfigListGridxGrid');
											var store=new dojo.data.ItemFileReadStore({url:'${CONTEXT_PATH}/eeprom/configView'});
											gridlist.model.clearCache();
											gridlist.model.setStore(store);
											gridlist.body.refresh();
										},
									error:function(type,error){alert(error.message)},
									timeout:function(type){alert('time out');}
								});
							}">OK</button>
					<button data-dojo-type="dijit/form/Button" type="button"
							data-dojo-props="onClick:function(){
							dijit.byId('EepConfigCreateForm').reset();
							dijit.byId('EepConfigCreateDialog').onCancel(); }"
							id="EepConfigCreateFormCancelButton">Cancel
					</button>
				</div>
			</form>
		</div>

		<!-- 编辑用户对话框 -->
		<div id="EepConfigEditDialog" data-dojo-type="dijit/Dialog" data-dojo-props="title:'Edit Config'" style="display:none;">
			<form id="EepConfigEditForm" data-dojo-type="dijit/form/Form">
				<div class="dijitDialogPaneContentArea">
					
					<table>
						<tr>
							<td><input data-dojo-type="dijit/form/TextBox" type="hidden" id="configid" name="configid" ></td>
						</tr>
						<tr>
							<td><label for="filepath">路径:</label>
							<td><input data-dojo-type="dijit/form/TextBox" type="text" name="filepath" id="configfilepath"></td>
						</tr>
						<tr>
							<td><label for="function">路径功能: </label></td>
							<td><select data-dojo-type="dijit/form/FilteringSelect"  name="function" id="configfunction">
    								<option value="work">工作目录</option>
    								<option value="handled">处理后文件放置目录</option>

									</select></td>
						</tr>

					</table>
					
				</div>
	
				<div class="dijitDialogPaneActionBar">
					<button data-dojo-type="dijit/form/Button" type="button" id="EepConfigEditFormOkButton"
							data-dojo-props="onClick:function(){
								dojo.xhrPost({
									url:'${CONTEXT_PATH}/eeprom/configUpdate',
									handle:'json',
									form:'EepConfigEditForm',
									timeoutSeconds:3000,
									load:function(response,ioArgs){
											alert(JSON.parse(response).status);
											dijit.byId('EepConfigEditDialog').hide();
											var gridlist=dijit.byId('EepConfigListGridxGrid');
											var store=new dojo.data.ItemFileReadStore({url:'${CONTEXT_PATH}/eeprom/configView'});
											gridlist.model.clearCache();
											gridlist.model.setStore(store);
											gridlist.body.refresh();
										},
									error:function(type,error){alert(error.message)},
									timeout:function(type){alert('time out');}
								});
							}">OK</button>
					<button data-dojo-type="dijit/form/Button" type="button"
							data-dojo-props="onClick:function(){
							dijit.byId('EepConfigEditForm').reset();
							dijit.byId('EepConfigEditDialog').onCancel(); }"
							id="EepConfigEditFormCancelButton">Cancel
					</button>
				</div>
			</form>
		</div>
	
	<div data-dojo-type="dijit/layout/ContentPane" id="EepConfigListGridxGridPane"
		data-dojo-props="region: 'center'">
	<div data-dojo-type="gridx/Grid" id="EepConfigListGridxGrid" data-dojo-id="demo.EepConfigListGridxGrid"
	data-dojo-props="
		store: demo.EepConfigListGridxStore,
		cacheClass: 'gridx/core/model/cache/Async',
		structure: [
			{ id: 'column_1', field:'id', name: 'id', width: 'auto' ,editable: 'true'},
			{ id: 'column_2', field: 'filepath', name: '工作路径', width: 'auto' ,editable: 'true'},
			{ id: 'column_3', field: 'function', name: '路径功能', width: 'auto' ,editable: 'true'}

		],
		selectRowTriggerOnCell: true,
		modules: [

        	
        	'gridx/modules/Focus',
			'gridx/modules/CellWidget',
			'gridx/modules/Edit',
			'gridx/modules/VirtualVScroller',
			'gridx/modules/ColumnResizer',
			'gridx/modules/extendedSelect/Row',
			'gridx/modules/SingleSort',
			'gridx/modules/RowHeader',
			'gridx/modules/IndirectSelect',
			'gridx/modules/Filter',
			'gridx/modules/filter/FilterBar',
			'gridx/modules/Pagination',
			'gridx/modules/pagination/PaginationBar',	
		],
		 editLazySave: true 
		"></div>
	</div>

</div>