<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div data-dojo-type="dijit/layout/BorderContainer"
	id="ResListGridx"
	data-dojo-props="liveSplitters: false, design: 'headline'">
		<div id="restoolbar1" data-dojo-type="dijit.Toolbar" data-dojo-props="region:'top'">
		
		 <div data-dojo-type="dijit.form.Button" id="restoolbar1.new"
         	  data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconNewPage', 
         				  	   showLabel:false,
         				       onClick:function(){
         				       dijit.byId('ResCreateForm').reset();
         				       dijit.byId('ResCreateDialog').show();}">New</div>
         <!-- The following adds a line between toolbar sections-->
         <span data-dojo-type="dijit.ToolbarSeparator"></span>
         
         <div data-dojo-type="dijit.form.Button" id="restoolbar1.edit"
         	  data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconWikiword', 
         	  					showLabel:false,
         	  					onClick:function(){
         	  						 gridlist=demo.registry.byId('ResListGridxGrid');
         	  						 id=gridlist.select.row.getSelected();
         	  						 row=gridlist.model.byId(id).rawData;
         	  						dijit.byId('resid').set('value',row.id);
         	  						dijit.byId('resname').set('value',row.name);
         	  						dijit.byId('restype').set('value',row.type);
         	  						dijit.byId('resurl').set('value',row.url);
         	  						dijit.byId('parent_id').set('value',row.parent_id);
         	  						dijit.byId('parent_ids').set('value',row.parent_ids);
         	  						dijit.byId('permission').set('value',row.permission);
         	  						dijit.byId('ResEditDialog').show();
         	  					}">Edit</div>
         	  					
         <div data-dojo-type="dijit.form.Button" id="restoolbar1.delete"
         data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconDelete', 
         					showLabel:false,
         					onClick:function(){
         						gridlist=dijit.byId('ResListGridxGrid');
         						id=gridlist.select.row.getSelected();
         						dojo.xhrPost({
         							url:'${CONTEXT_PATH}/resource/delete',
         							handle:'json',
         							content:{id:id},
         							load:function(response,ioArgs){
         									alert(JSON.parse(response).status);
         									gridlist=dijit.byId('ResListGridxGrid');
         									var store=new dojo.data.ItemFileReadStore({url:'/resource/view'});
											gridlist.model.clearCache();
											gridlist.model.setStore(store);
											gridlist.body.refresh();
         							}
         						});
         					}">Delete</div>
         <!-- The following adds a line between toolbar sections-->
         <span data-dojo-type="dijit.ToolbarSeparator"></span>

		</div>
	<div data-dojo-type="dojo/data/ItemFileWriteStore" id="ResListGridxStore"
		data-dojo-id="demo.ResListGridxStore" style="display: none"
		data-dojo-props="url: '${CONTEXT_PATH}/resource/view'"></div>
		
		<!-- 新建用户权限对话框 -->
		
		<div id="ResCreateDialog" data-dojo-type="dijit/Dialog" data-dojo-props="title:'Create New'" style="display:none;">
			<form id="ResCreateForm" data-dojo-type="dijit/form/Form">
				<div class="dijitDialogPaneContentArea">
					
					<table>
						<tr>
							<td><label for="resid">资源ID: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="resid" ></td>
						</tr>
						<tr>
							<td><label for="resname">资源名称: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="resname" ></td>
						</tr>
						<tr>
							<td><label for="restype">资源类型: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="restype" ></td>
						</tr>
						<tr>
							<td><label for="resurl">资源路径: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" 
									type="text" name="resurl" ></td>
						</tr>
						<tr>
							<td><label for="parent_id">资源上级父ID: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="parent_id" ></td>
						</tr>
						<tr>
							<td><label for="parent_ids">资源上级所有父ID: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="parent_ids" ></td>
						</tr>
						<tr>
							<td><label for="permission">资源权限标示: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="permission" ></td>
						</tr>
					</table>
					
				</div>
	
				<div class="dijitDialogPaneActionBar">
					<button data-dojo-type="dijit/form/Button" type="button" id="ResCreateFormOkButton"
							data-dojo-props="onClick:function(){
								dojo.xhrPost({
									url:'${CONTEXT_PATH}/resource/create',
									handle:'json',
									form:'ResCreateForm',
									timeoutSeconds:3000,
									load:function(response,ioArgs){
											alert(JSON.parse(response).status);
											dijit.byId('ResCreateDialog').hide();
											var gridlist=dijit.byId('ResListGridxGrid');
											var store=new dojo.data.ItemFileReadStore({url:'/resource/view'});
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
											dijit.byId('ResCreateForm').reset();	
											dijit.byId('ResCreateDialog').onCancel(); }"
							id="ResCreateFormCancelButton">Cancel
					</button>
				</div>
			</form>
		</div>

		<!-- 编辑用户对话框 -->
		<div id="ResEditDialog" data-dojo-type="dijit/Dialog" data-dojo-props="title:'Edit Res'" style="display:none;">
			<form id="ResEditForm" data-dojo-type="dijit/form/Form">
				<div class="dijitDialogPaneContentArea">
					
					<table>
						<tr>
							<td><input data-dojo-type="dijit/form/TextBox" type="hidden" id="resid" name="resid" ></td>
						</tr>

						<tr>
							<td><label for="resname">资源名称: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="resname" id="resname"></td>
						</tr>
						<tr>
							<td><label for="restype">资源类型: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="restype" id="restype"></td>
						</tr>
						<tr>
							<td><label for="resurl">资源路径: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" 
									type="text" name="resurl" id="resurl"></td>
						</tr>
						<tr>
							<td><label for="parent_id">资源上级父ID: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="parent_id" id="parent_id" ></td>
						</tr>
						<tr>
							<td><label for="parent_ids">资源上级所有父ID: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="parent_ids" id="parent_ids"></td>
						</tr>
						<tr>
							<td><label for="permission">资源权限标示: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="permission" id="permission"></td>
						</tr>
					</table>
					

				</div>
	
				<div class="dijitDialogPaneActionBar">
					<button data-dojo-type="dijit/form/Button" type="button" id="ResEditFormOkButton"
							data-dojo-props="onClick:function(){
								dojo.xhrPost({
									url:'${CONTEXT_PATH}/resource/update',
									handle:'json',
									form:'ResEditForm',
									timeoutSeconds:3000,
									load:function(response,ioArgs){
											alert(JSON.parse(response).status);
											dijit.byId('ResEditDialog').hide();
											var gridlist=dijit.byId('ResListGridxGrid');
											var store=new dojo.data.ItemFileReadStore({url:'/resource/view'});
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
							dijit.byId('ResEditForm').reset();
							dijit.byId('ResEditDialog').onCancel(); }"
							id="ResEditFormCancelButton">Cancel
					</button>
				</div>
			</form>
		</div>
	
	<div data-dojo-type="dijit/layout/ContentPane" id="ResListGridxGridPane"
		data-dojo-props="region: 'center'">
	<div data-dojo-type="gridx/Grid" id="ResListGridxGrid" data-dojo-id="demo.ResListGridxGrid"
	data-dojo-props="
		store: demo.ResListGridxStore,
		cacheClass: 'gridx/core/model/cache/Async',
		structure: [
			{ id: 'column_1', field:'id', name: 'id', width: 'auto' ,editable: 'true'},
			{ id: 'column_2', field: 'name', name: '资源名称', width: 'auto' ,editable: 'true'},
			{ id: 'column_3', field: 'type', name: '资源类型', width: 'auto' ,editable: 'true'},
			{ id: 'column_4', field: 'url', name: '资源访问路径', width: 'auto' ,editable: 'true'},
			{ id: 'column_5', field: 'parent_id', name: '资源上级父ID', width: 'auto' ,editable: 'true'},
			{ id: 'column_6', field: 'parent_ids', name: '资源所有上级父ID', width: 'auto' ,editable: 'true'},
			{ id: 'column_7', field: 'permission', name: '权限标示', width: 'auto' ,editable: 'true'},
			{ id: 'column_8', field: 'available', name: '是否激活', width: 'auto' ,editable: 'true'}

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