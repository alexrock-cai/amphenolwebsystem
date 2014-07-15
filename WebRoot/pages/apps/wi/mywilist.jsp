<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div data-dojo-type="dijit/layout/BorderContainer"
	id="MyWiListGridx"
	data-dojo-props="liveSplitters: false, design: 'headline'">
	<shiro:hasRole name="root">
		<div id="MyWitoolbar1" data-dojo-type="dijit.Toolbar" data-dojo-props="region:'top'">
		
		 <div data-dojo-type="dijit.form.Button" id="MyWitoolbar1.new"
         	  data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconNewPage', 
         				  	   showLabel:false,
         				       onClick:function(){dijit.byId('MyWiCreateDialog').show();}">New</div>
         <!-- The following adds a line between toolbar sections-->
         <span data-dojo-type="dijit.ToolbarSeparator"></span>
         
         <div data-dojo-type="dijit.form.Button" id="MyWitoolbar1.edit"
         	  data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconWikiword', 
         	  					showLabel:false,
         	  					onClick:function(){
         	  						 gridlist=demo.registry.byId('MyWiListGridxGrid');
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
         	  						dijit.byId('MyWiEditDialog').show();
         	  					}">Edit</div>
         	  					
         <div data-dojo-type="dijit.form.Button" id="MyWitoolbar1.delete"
         data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconDelete', 
         					showLabel:false,
         					onClick:function(){
         						gridlist=dijit.byId('MyWiListGridxGrid');
         						id=gridlist.select.row.getSelected();
         						dojo.xhrPost({
         							url:'${CONTEXT_PATH}/dcc/delete',
         							handle:'json',
         							content:{id:id},
         							load:function(response,ioArgs){
         									alert(JSON.parse(response).status);
         									gridlist=dijit.byId('MyWiListGridxGrid');
         									var store=new dojo.data.ItemFileReadStore({url:'/wi/myWi'});
											gridlist.model.clearCache();
											gridlist.model.setStore(store);
											gridlist.body.refresh();
         							}
         						});
         					}">Delete</div>
         <!-- The following adds a line between toolbar sections-->
         <span data-dojo-type="dijit.ToolbarSeparator"></span>

		</div>
	</shiro:hasRole>	
	<div data-dojo-type="dojo/data/ItemFileWriteStore" id="MyWiListGridxStore"
		data-dojo-id="demo.MyWiListGridxStore" style="display: none"
		data-dojo-props="url: '${CONTEXT_PATH}/wi/myWi'"></div>
		
		<!-- 新建用户对话框 -->
		

	
	<div data-dojo-type="dijit/layout/ContentPane" id="MyWiListGridxGridPane"
		data-dojo-props="region: 'center'">
	<div data-dojo-type="gridx/Grid" id="MyWiListGridxGrid" data-dojo-id="demo.MyWiListGridxGrid"
	data-dojo-props="
		store: demo.MyWiListGridxStore,
		cacheClass: 'gridx/core/model/cache/Async',
		structure: [
			{ id: 'column_1', field:'id', name: 'id', width: 'auto' ,editable: 'true'},
			{ id: 'column_2', field: 'customer', name: '客户', width: 'auto' ,editable: 'true'},
			{ id: 'column_3', field: 'pn', name: '产品代码', width: 'auto' ,editable: 'true'},
			{ id: 'column_4', field: 'type', name: '文件类型', width: 'auto' ,editable: 'true'},
			{ id: 'column_5', field: 'rev', name: '文件版本号', width: 'auto' ,editable: 'true'},
			{ id: 'column_6', field: 'filepath', name: '文件路径', width: 'auto',editable: 'true' },
			{ id: 'column_7', field: 'filename', name: '文件名', width: 'auto' ,editable: 'true'},
			{ id: 'column_8', field: 'lastmodify', name: '最后更新时间', width: 'auto' ,editable: 'true'},
			{ id: 'column_9', field: 'operate', name: '操作', width: 'auto' ,editable: 'true'}
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

