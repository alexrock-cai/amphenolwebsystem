<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div data-dojo-type="dijit/layout/BorderContainer"
	id="UserListGridx"
	data-dojo-props="liveSplitters: false, design: 'headline'">
		<div id="toolbar1" data-dojo-type="dijit.Toolbar" data-dojo-props="region:'top'">
		
		 <div data-dojo-type="dijit.form.Button" id="toolbar1.new"
         	  data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconNewPage', 
         				  	   showLabel:false,
         				       onClick:function(){
         				       dijit.byId('UserCreateForm').reset();
         				       dijit.byId('UserCreateDialog').show();}">New</div>
         <!-- The following adds a line between toolbar sections-->
         <span data-dojo-type="dijit.ToolbarSeparator"></span>
         
         <div data-dojo-type="dijit.form.Button" id="toolbar1.edit"
         	  data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconWikiword', 
         	  					showLabel:false,
         	  					onClick:function(){
         	  						 gridlist=demo.registry.byId('UserListGridxGrid');
         	  						 id=gridlist.select.row.getSelected();
         	  						 row=gridlist.model.byId(id).rawData;
         	  						dijit.byId('userid').set('value',row.id);
         	  						dijit.byId('organization').set('value',row.organization_id);
         	  						dijit.byId('username').set('value',row.username);
         	  						dijit.byId('role_ids').set('value',row.role_ids);
         	  						dijit.byId('newpass').set('value',row.password);
         	  						dijit.byId('verpass').set('value',row.password);
         	  						dijit.byId('UserEditDialog').show();
         	  					}">Edit</div>
         	  					
         <div data-dojo-type="dijit.form.Button" id="toolbar1.delete"
         data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconDelete', 
         					showLabel:false,
         					onClick:function(){
         						gridlist=dijit.byId('UserListGridxGrid');
         						id=gridlist.select.row.getSelected();
         						dojo.xhrPost({
         							url:'${CONTEXT_PATH}/user/delete',
         							handle:'json',
         							content:{id:id},
         							load:function(response,ioArgs){
         									alert(JSON.parse(response).status);
         									gridlist=dijit.byId('UserListGridxGrid');
         									var store=new dojo.data.ItemFileReadStore({url:'/user/view'});
											gridlist.model.clearCache();
											gridlist.model.setStore(store);
											gridlist.body.refresh();
         							}
         						});
         					}">Delete</div>
         <!-- The following adds a line between toolbar sections-->
         <span data-dojo-type="dijit.ToolbarSeparator"></span>

		</div>
	<div data-dojo-type="dojo/data/ItemFileReadStore" id="UserListGridxStore"
		data-dojo-id="demo.UserListGridxStore" style="display: none"
		data-dojo-props="url: '${CONTEXT_PATH}/user/view'"></div>
		
		<!-- 新建用户对话框 -->
		
		<div id="UserCreateDialog" data-dojo-type="dijit/Dialog" data-dojo-props="title:'Create New'" style="display:none;">
			<form id="UserCreateForm" data-dojo-type="dijit/form/Form">
				<div class="dijitDialogPaneContentArea">
					
					<table>
						<tr>
							<td><label for="username">UserName: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="username" ></td>
						</tr>
						<tr>
							<td><label for="role_ids">Role_ids: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="role_ids" ></td>
						</tr>
					</table>
					
					<div data-dojo-type="dojox/form/PasswordValidator" name="password" > 
						<table>
							<tr>
								<td><label for="password">Password: </label></td>
								<td><input type="password" pwType="new" ></td>
							</tr>
							<tr>
								<td><label for="validate">Validate: </label></td>
								<td><input type="password" pwType="verify"></td>
							</tr>
						</table>
					</div>
				</div>
	
				<div class="dijitDialogPaneActionBar">
					<button data-dojo-type="dijit/form/Button" type="button" id="UserCreateFormOkButton"
							data-dojo-props="onClick:function(){
								
								dojo.xhrPost({
									url:'${CONTEXT_PATH}/user/create',
									handle:'json',
									form:'UserCreateForm',
									timeoutSeconds:3000,
									load:function(response,ioArgs){
											alert(JSON.parse(response).status);
											dijit.byId('UserCreateDialog').hide();
											var gridlist=dijit.byId('UserListGridxGrid');
											var store=new dojo.data.ItemFileReadStore({url:'${CONTEXT_PATH}/user/view'});
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
							dijit.byId('UserCreateForm').reset();
							dijit.byId('UserCreateDialog').onCancel(); }"
							id="UserCreateFormCancelButton">Cancel
					</button>
				</div>
			</form>
		</div>

		<!-- 编辑用户对话框 -->
		<div id="UserEditDialog" data-dojo-type="dijit/Dialog" data-dojo-props="title:'Edit User'" style="display:none;">
			<form id="UserEditForm">
				<div class="dijitDialogPaneContentArea">
					
					<table>
						<tr>
							<td><input data-dojo-type="dijit/form/TextBox" type="hidden" id="userid" name="userid" ></td>
						</tr>
						<tr>
							<td><label for="organization_id">Organization_id:</label>
							<td><input data-dojo-type="dijit/form/TextBox" type="text" name="organization_id" id="organization"></td>
						</tr>
						<tr>
							<td><label for="username">UserName: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									  type="text" name="username" id="username"></td>
						</tr>
						<tr>
							<td><label for="role_ids">Role_ids: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									  type="text" name="role_ids" id="role_ids"></td>
						</tr>
					</table>
					
					<div data-dojo-type="dojox/form/PasswordValidator" name="password" id="password"> 
						<table>
							<tr>
								<td><label for="password">New Password: </label></td>
								<td><input type="password" pwType="new" id="newpass"></td>
							</tr>
							<tr>
								<td><label for="validate">Validate Pass: </label></td>
								<td><input type="password" pwType="verify" id="verpass"></td>
							</tr>
						</table>
					</div>
				</div>
	
				<div class="dijitDialogPaneActionBar">
					<button data-dojo-type="dijit/form/Button" type="button" id="UserEditFormOkButton"
							data-dojo-props="onClick:function(){
								dojo.xhrPost({
									url:'${CONTEXT_PATH}/user/update',
									handle:'json',
									form:'UserEditForm',
									timeoutSeconds:3000,
									load:function(response,ioArgs){
											alert(JSON.parse(response).status);
											dijit.byId('UserEditDialog').hide();
											var gridlist=dijit.byId('UserListGridxGrid');
											var store=new dojo.data.ItemFileReadStore({url:'${CONTEXT_PATH}/user/view'});
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
							dijit.byId('UserEditForm').reset();
							dijit.byId('UserEditDialog').onCancel(); }"
							id="UserEditFormCancelButton">Cancel
					</button>
				</div>
			</form>
		</div>
	
	<div data-dojo-type="dijit/layout/ContentPane" id="UserListGridxGridPane"
		data-dojo-props="region: 'center'">
	<div data-dojo-type="gridx/Grid" id="UserListGridxGrid" data-dojo-id="demo.UserListGridxGrid"
	data-dojo-props="
		store: demo.UserListGridxStore,
		cacheClass: 'gridx/core/model/cache/Async',
		structure: [
			{ id: 'column_1', field:'id', name: 'id', width: 'auto' ,editable: 'true'},
			{ id: 'column_2', field: 'organization_id', name: '公司组织', width: 'auto' ,editable: 'true'},
			{ id: 'column_3', field: 'username', name: '用户名', width: 'auto' ,editable: 'true'},
			{ id: 'column_4', field: 'password', name: '密码', width: 'auto' ,editable: 'true'},
			{ id: 'column_5', field: 'salt', name: 'Salt', width: 'auto' ,editable: 'true'},
			{ id: 'column_6', field: 'role_ids', name: '角色', width: 'auto',editable: 'true' },
			{ id: 'column_7', field: 'locked', name: '已锁定', width: 'auto' ,editable: 'true'}
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