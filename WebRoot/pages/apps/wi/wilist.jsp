<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div data-dojo-type="dijit/layout/BorderContainer"
	id="WiListGridx"
	data-dojo-props="liveSplitters: false, design: 'headline'">
		<div id="Witoolbar1" data-dojo-type="dijit.Toolbar" data-dojo-props="region:'top'">
		
		 <div data-dojo-type="dijit.form.Button" id="Witoolbar1.new"
         	  data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconNewPage', 
         				  	   showLabel:false,
         				       onClick:function(){dijit.byId('WiCreateDialog').show();}">New</div>
         <!-- The following adds a line between toolbar sections-->
         <span data-dojo-type="dijit.ToolbarSeparator"></span>
         
         <div data-dojo-type="dijit.form.Button" id="Witoolbar1.edit"
         	  data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconWikiword', 
         	  					showLabel:false,
         	  					onClick:function(){
         	  						 gridlist=demo.registry.byId('WiListGridxGrid');
         	  						 id=gridlist.select.row.getSelected();
         	  						 row=gridlist.model.byId(id).rawData;
         	  						dijit.byId('id').set('value',row.id);
         	  						dijit.byId('customer').set('value',row.customer);
         	  						dijit.byId('pn').set('value',row.pn);
         	  						dijit.byId('type').set('value',row.type);
         	  						dijit.byId('rev').set('value',row.rev);
         	  						dijit.byId('filepath').set('value',row.filepath);
         	  						dijit.byId('filename').set('value',row.filename);
         	  						dijit.byId('lastmodify').set('value',row.lastmodify);
         	  						dijit.byId('operate').set('value',row.operate);
         	  						dijit.byId('WiEditDialog').show();
         	  					}">Edit</div>
         	  					
         <div data-dojo-type="dijit.form.Button" id="Witoolbar1.delete"
         data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconDelete', 
         					showLabel:false,
         					onClick:function(){
         						gridlist=dijit.byId('WiListGridxGrid');
         						id=gridlist.select.row.getSelected();
         						dojo.xhrPost({
         							url:'${CONTEXT_PATH}/dcc/delete',
         							handle:'json',
         							content:{id:id},
         							load:function(response,ioArgs){
         									alert(JSON.parse(response).status);
         									gridlist=dijit.byId('WiListGridxGrid');
         									var store=new dojo.data.ItemFileReadStore({url:'${CONTEXT_PATH}/dcc/view'});
											gridlist.model.clearCache();
											gridlist.model.setStore(store);
											gridlist.body.refresh();
         							}
         						});
         					}">Delete</div>
         <!-- The following adds a line between toolbar sections-->
         <span data-dojo-type="dijit.ToolbarSeparator"></span>

		</div>
	<div data-dojo-type="dojo/data/ItemFileWriteStore" id="WiListGridxStore"
		data-dojo-id="demo.WiListGridxStore" style="display: none"
		data-dojo-props="url: '${CONTEXT_PATH}/dcc/view'"></div>
		
		<!-- 新建用户对话框 -->
		
		<div id="WiCreateDialog" data-dojo-type="dijit/Dialog" data-dojo-props="title:'Create New'" style="display:none;">
			<form id="WiCreateForm" enctype="multipart/form-data" method="post" action="${CONTEXT_PATH}/dcc/create">
				<div class="dijitDialogPaneContentArea">
					
					<table>
						<tr>
							<td><label for="customer">Customer: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="customer" ></td>
						</tr>
						<tr>
							<td><label for="pn">Pn: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="pn" ></td>
						</tr>
						<tr>
							<td><label for="type">Type: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="type" ></td>
						</tr>
						<tr>
							<td><label for="rev">Rev: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="rev" ></td>
						</tr>
						<tr>
							<td><label for="file">File: </label></td>
							<td><input type="file" name="file" ></td>
						</tr>


					</table>
					
				</div>
	
				<div class="dijitDialogPaneActionBar">
					<button data-dojo-type="dijit/form/Button" type="button" id="WiCreateFormOkButton"
							data-dojo-props="onClick:function(){
							require(['dojo/request/iframe'], function(iframe){
    								iframe('${CONTEXT_PATH}/dcc/create', {
									form:'WiCreateForm',
									handleAs:'text/html'   																			
    								}).then(function(r){
    										alert(r)
    										dijit.byId('WiCreateDialog').hide();
											var gridlist=dijit.byId('WiListGridxGrid');
											var store=new dojo.data.ItemFileReadStore({url:'${CONTEXT_PATH}/dcc/view'});
											gridlist.model.clearCache();
											gridlist.model.setStore(store);
											gridlist.body.refresh();
    								},function(error){
    									alert(error)
    								});
    								
								});
							}">OK</button>
					<button data-dojo-type="dijit/form/Button" type="button"
							data-dojo-props="onClick:function(){dijit.byId('WiCreateDialog').onCancel(); }"
							id="WiCreateFormCancelButton">Cancel
					</button>

				</div>
			</form>
		</div>

		<!-- 编辑用户对话框 -->
		<div id="WiEditDialog" data-dojo-type="dijit/Dialog" data-dojo-props="title:'Edit User'" style="display:none;">
			<form id="WiEditForm">
				<div class="dijitDialogPaneContentArea">
					
					<table>
						<tr>
							<td><label for="customer">Customer: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="customer" id="customer"></td>
						</tr>
						<tr>
							<td><label for="pn">Pn: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="pn" id="pn"></td>
						</tr>
						<tr>
							<td><label for="type">Type: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="type" id="type"></td>
						</tr>
						<tr>
							<td><label for="rev">Rev: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="rev" id="rev"></td>
						</tr>
						<tr>
							<td><label for="filepath">Filepath: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="filepath" id="filepath"></td>
						</tr>
						<tr>
							<td><label for="filename">Filename: </label></td>
							<td><input data-dojo-type="dijit/form/ValidationTextBox" required="true" 
									type="text" name="filename" id="filename"></td>
						</tr>

					</table>
					
				</div>
	
				<div class="dijitDialogPaneActionBar">
					<button data-dojo-type="dijit/form/Button" type="button" id="WiEditFormOkButton"
							data-dojo-props="onClick:function(){
								dojo.xhrPost({
									url:'${CONTEXT_PATH}/dcc/update',
									handle:'json',
									form:'WiEditForm',
									timeoutSeconds:3000,
									load:function(response,ioArgs){
											alert(JSON.parse(response).status);
											dijit.byId('WiEditDialog').hide();
											var gridlist=dijit.byId('WiListGridxGrid');
											var store=new dojo.data.ItemFileReadStore({url:'${CONTEXT_PATH}/dcc/view'});
											gridlist.model.clearCache();
											gridlist.model.setStore(store);
											gridlist.body.refresh();
										},
									error:function(type,error){alert(error.message)},
									timeout:function(type){alert('time out');}
								});
							}">OK</button>
					<button data-dojo-type="dijit/form/Button" type="button"
							data-dojo-props="onClick:function(){dijit.byId('WiEditDialog').onCancel(); }"
							id="WiEditFormCancelButton">Cancel
					</button>
				</div>
			</form>
		</div>
	
	<div data-dojo-type="dijit/layout/ContentPane" id="WiListGridxGridPane"
		data-dojo-props="region: 'center'">
	<div data-dojo-type="gridx/Grid" id="WiListGridxGrid" data-dojo-id="demo.WiListGridxGrid"
	data-dojo-props="
		store: demo.WiListGridxStore,
		cacheClass: 'gridx/core/model/cache/Async',
		structure: [
			{ id: 'column_1', field:'id', name: 'id', width: 'auto' ,editable: 'true'},
			{ id: 'column_2', field: 'customer', name: '客户', width: 'auto' ,editable: 'true'},
			{ id: 'column_3', field: 'station', name: '站别', width: 'auto' ,editable: 'true'},
			{ id: 'column_4', field: 'pn', name: '产品代码', width: 'auto' ,editable: 'true'},
			{ id: 'column_5', field: 'type', name: '文件类型', width: 'auto' ,editable: 'true'},
			{ id: 'column_6', field: 'rev', name: '文件版本号', width: 'auto' ,editable: 'true'},
			{ id: 'column_7', field: 'filepath', name: '文件路径', width: 'auto',editable: 'true' },
			{ id: 'column_8', field: 'filename', name: '文件名', width: 'auto' ,editable: 'true'},
			{ id: 'column_9', field: 'lastmodify', name: '最后更新时间', width: 'auto' ,editable: 'true'},
			{ id: 'column_10', field: 'operate', name: '操作', width: 'auto' ,editable: 'true'}
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

