<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div data-dojo-type="dijit/layout/BorderContainer"
	id="RoleListGridx"
	data-dojo-props="liveSplitters: false, design: 'headline'">
		<div id="roletoolbar1" data-dojo-type="dijit.Toolbar" data-dojo-props="region:'top'">
		
		 <div data-dojo-type="dijit.form.Button" id="roletoolbar1.new"
         	  data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconNewPage', 
         				  	   showLabel:false,
         				       onClick:function(){
         				       dijit.byId('RoleCreateForm').reset();
         				       dijit.byId('RoleCreateDialog').show();}">New</div>
         <!-- The following adds a line between toolbar sections-->
         <span data-dojo-type="dijit.ToolbarSeparator"></span>
         
         <div data-dojo-type="dijit.form.Button" id="roletoolbar1.edit"
         	  data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconWikiword', 
         	  					showLabel:false,
         	  					onClick:function(){
         	  						 gridlist=demo.registry.byId('RoleListGridxGrid');
         	  						 id=gridlist.select.row.getSelected();
         	  						 row=gridlist.model.byId(id).rawData;
         	  						dijit.byId('roleid').set('value',row.id);
         	  						dijit.byId('role').set('value',row.role);
         	  						dijit.byId('description').set('value',row.description);
         	  						dijit.byId('resource_ids').set('value',row.resource_ids);
         	  						
         	  						dijit.byId('RoleEditDialog').show();
         	  					}">Edit</div>
         	  					
         <div data-dojo-type="dijit.form.Button" id="roletoolbar1.delete"
         data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconDelete', 
         					showLabel:false,
         					onClick:function(){
         						gridlist=dijit.byId('RoleListGridxGrid');
         						id=gridlist.select.row.getSelected();
         						dojo.xhrPost({
         							url:'${CONTEXT_PATH}/role/delete',
         							handle:'json',
         							content:{id:id},
         							load:function(response,ioArgs){
         									alert(JSON.parse(response).status);
         									gridlist=dijit.byId('RoleListGridxGrid');
         									var store=new dojo.data.ItemFileReadStore({url:'/role/view'});
											gridlist.model.clearCache();
											gridlist.model.setStore(store);
											gridlist.body.refresh();
         							}
         						});
         					}">Delete</div>
         <!-- The following adds a line between toolbar sections-->
         <span data-dojo-type="dijit.ToolbarSeparator"></span>

		</div>
	<div data-dojo-type="dojo/data/ItemFileWriteStore" id="RoleListGridxStore"
		data-dojo-id="demo.RoleListGridxStore" style="display: none"
		data-dojo-props="url: '${CONTEXT_PATH}/role/view'"></div>
		
		<!-- 新建用户权限对话框 -->
		
		<div id="RoleCreateDialog" data-dojo-type="dijit/Dialog" data-dojo-props="title:'Create New'" style="display:none;">
			<form id="RoleCreateForm" data-dojo-type="dijit/form/Form">
				<div class="dijitDialogPaneContentArea">
					
					<table>
						<tr>
							<td><label for="role">Role: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="role" ></td>
						</tr>
						<tr>
							<td><label for="description">Description: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="description" ></td>
						</tr>
						<tr>
							<td><label for="resource_ids">Resource_ids: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="resource_ids" ></td>
						</tr>
					</table>
					
				</div>
	
				<div class="dijitDialogPaneActionBar">
					<button data-dojo-type="dijit/form/Button" type="button" id="RoleCreateFormOkButton"
							data-dojo-props="onClick:function(){
								dojo.xhrPost({
									url:'${CONTEXT_PATH}/role/create',
									handle:'json',
									form:'RoleCreateForm',
									timeoutSeconds:3000,
									load:function(response,ioArgs){
											alert(JSON.parse(response).status);
											dijit.byId('RoleCreateDialog').hide();
											var gridlist=dijit.byId('RoleListGridxGrid');
											var store=new dojo.data.ItemFileReadStore({url:'/role/view'});
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
							dijit.byId('RoleCreateForm').reset();
							dijit.byId('RoleCreateDialog').onCancel(); }"
							id="RoleCreateFormCancelButton">Cancel
					</button>
				</div>
			</form>
		</div>

		<!-- 编辑用户对话框 -->
		<div id="RoleEditDialog" data-dojo-type="dijit/Dialog" data-dojo-props="title:'Edit Role'" style="display:none;">
			<form id="RoleEditForm">
				<div class="dijitDialogPaneContentArea">
					
					<table>
						<tr>
							<td><input data-dojo-type="dijit/form/TextBox" type="hidden" id="roleid" name="roleid" ></td>
						</tr>

						<tr>
							<td><label for="role">Role: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="role" id="role"></td>
						</tr>
						<tr>
							<td><label for="description">Description: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="description" id="description"></td>
						</tr>
						<tr>
							<td><label for="resource_ids">Resource_ids: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="resource_ids" id="resource_ids"></td>
						</tr>
					</table>
					

				</div>
	
				<div class="dijitDialogPaneActionBar">
					<button data-dojo-type="dijit/form/Button" type="button" id="RoleEditFormOkButton"
							data-dojo-props="onClick:function(){
								dojo.xhrPost({
									url:'${CONTEXT_PATH}/role/update',
									handle:'json',
									form:'RoleEditForm',
									timeoutSeconds:3000,
									load:function(response,ioArgs){
											alert(JSON.parse(response).status);
											dijit.byId('RoleEditDialog').hide();
											var gridlist=dijit.byId('RoleListGridxGrid');
											var store=new dojo.data.ItemFileReadStore({url:'${CONTEXT_PATH}/role/view'});
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
							dijit.byId('RoleEditForm').reset();
							dijit.byId('RoleEditDialog').onCancel(); }"
							id="RoleEditFormCancelButton">Cancel
					</button>
				</div>
			</form>
		</div>
	
	<div data-dojo-type="dijit/layout/ContentPane" id="RoleListGridxGridPane"
		data-dojo-props="region: 'center'">
	<div data-dojo-type="gridx/Grid" id="RoleListGridxGrid" data-dojo-id="demo.RoleListGridxGrid"
	data-dojo-props="
		store: demo.RoleListGridxStore,
		cacheClass: 'gridx/core/model/cache/Async',
		structure: [
			{ id: 'column_1', field:'id', name: 'id', width: 'auto' ,editable: 'true'},
			{ id: 'column_2', field: 'role', name: '权限', width: 'auto' ,editable: 'true'},
			{ id: 'column_3', field: 'description', name: '权限描述', width: 'auto' ,editable: 'true'},
			{ id: 'column_4', field: 'resource_ids', name: '可访问资源', width: 'auto' ,editable: 'true'},
			{ id: 'column_5', field: 'available', name: '是否激活状态', width: 'auto' ,editable: 'true'}

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