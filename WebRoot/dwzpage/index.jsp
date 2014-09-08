<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Amphenol-GIS Web System</title>

<link rel="shortcut icon" href="/static/image/favicon.ico" />
<link href="/js/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/js/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/js/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="/js/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="/js/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="/js/dwz/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="/js/dwz/jquery-1.7.2.js" type="text/javascript"></script>
<script src="/js/dwz/jquery.cookie.js" type="text/javascript"></script>
<script src="/js/dwz/jquery.validate.js" type="text/javascript"></script>
<script src="/js/dwz/jquery.bgiframe.js" type="text/javascript"></script>
<script src="/js/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="/js/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="/js/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="/js/chart/raphael.js"></script>
<script type="text/javascript" src="/js/chart/g.raphael.js"></script>
<script type="text/javascript" src="/js/chart/g.bar.js"></script>
<script type="text/javascript" src="/js/chart/g.line.js"></script>
<script type="text/javascript" src="/js/chart/g.pie.js"></script>
<script type="text/javascript" src="/js/chart/g.dot.js"></script>

<script src="/js/dwz/dwz.core.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.util.date.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.validate.method.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.regional.zh.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.barDrag.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.drag.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.tree.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.accordion.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.ui.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.theme.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.switchEnv.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.alertMsg.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.contextmenu.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.navTab.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.tab.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.resize.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.dialog.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.sortDrag.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.cssTable.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.stable.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.taskBar.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.ajax.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.pagination.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.database.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.datepicker.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.effects.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.panel.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.checkbox.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.history.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.combox.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.print.js" type="text/javascript"></script>
<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="/js/dwz/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("${CONTEXT_PATH}/dwzpage/dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"../../js/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="http://www.ltd.amphenol-tcs.com">标志</a>
				<ul class="nav">
					<li><a><h2>欢迎 ${user.getStr('username')} 登录本系统</h2></a></li>
					<li><a href="${CONTEXT_PATH}/dwzpage/user/changepwd.jsp" target="dialog" width="600">修改密码</a></li>
					<li><a href="/logout">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>作业指导书</h2>
					</div>
					<div class="accordionContent">
						<!-- 用户信息面板 -->
						
							<table class="list" width="100%" >
								<tr>
									<td colspan="2" align="center"><img src="${user.getStr('img') }" height="50%" width="50%"/></td> 
									
								</tr>
								<tr align="center">
									<td>姓名 </td>
									<td>${user.getStr('name')}</td>
								</tr>
								<tr align="center">
									<td>工号 </td>
									<td>${user.getStr('username')}</td>
								</tr>
								<tr>
									<td>认证技能 </td>
									<td><a href="${CONTEXT_PATH}/wi/myWi?pageNum=1&numPerPage=20" target="navTab" rel="wi_list" title="我的WI">${user.getResourceNames() }</a></td>
								</tr>
								<tr>
									<td colspan="2" align="center"><a href="${CONTEXT_PATH}/wi/myWi?pageNum=1&numPerPage=20" target="navTab" rel="wi_list">我的WI</a></td>
								</tr>
							</table>
						
						<!-- 功能菜单
						<ul class="tree treeFolder collapse">
							<li><a href="${CONTEXT_PATH}/dwzpage/main.html" target="navTab" rel="main" >主页</a></li>
							<li></li>	
						</ul>
						 -->
					</div>
					<shiro:hasAnyRoles name="root,admin_leader">
					<div class="accordionHeader">
						<h2><span>Folder</span>用户管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a href="${CONTEXT_PATH}/user/userView?pageNum=1&numPerPage=20" target="navTab" rel="user_list" >用户列表</a></li>
							<shiro:hasRole name="root">
								<li><a href="${CONTEXT_PATH}/role/roleView?pageNum=1&numPerPage=20" target="navTab" rel="role_list" >角色列表</a></li>
								<li><a href="${CONTEXT_PATH}/resource/resView?pageNum=1&numPerPage=20" target="navTab" rel="res_list" >权限列表</a></li>
							</shiro:hasRole>
							<li><a href="${CONTEXT_PATH}/user/stationView?pageNum=1&numPerPage=20" target="navTab" rel="station_list" >站别列表</a></li>
						</ul>
					</div>
					</shiro:hasAnyRoles>
					<shiro:hasAnyRoles name="root,eeprom_user,WIUser:EEPROM_PKG">
					<div class="accordionHeader">
						<h2><span>Folder</span>EEPROM System</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<shiro:hasAnyRoles name="root,eeprom_user">
							<li><a href="${CONTEXT_PATH}/eeprom/dashboard" target="navTab" rel="eeprom_list">EEPROM Dashboard</a></li>
							<li><a href="${CONTEXT_PATH}/eeprom/doEcheckView?pageNum=1&numPerPage=20" target="navTab" rel="echeck_log">系统检测日志</a></li>
							<li><a href="${CONTEXT_PATH}/eeprom/echeckView?pageNum=1&numPerPage=20" target="navTab" rel="echeck_list">系统检测异常产品清单</a></li>
							<li><a href="${CONTEXT_PATH}/eeprom/view?pageNum=1&numPerPage=20" target="navTab" rel="eeprom_list">EEPROM Log文件路径配置</a></li>
							<li><a href="${CONTEXT_PATH}/eeprom/productView?pageNum=1&numPerPage=20" target="navTab" rel="product_list">已经上传TAG IN数据</a></li>
							<li><a href="${CONTEXT_PATH}/eeprom/programView?pageNum=1&numPerPage=20" target="navTab" rel="program_list">已经上传Program Log数据</a></li>
							<li><a href="${CONTEXT_PATH}/eeprom/verifyView?pageNum=1&numPerPage=20" target="navTab" rel="verify_list">已经上传Verify Log数据</a></li>
							</shiro:hasAnyRoles>
							<li><a href="${CONTEXT_PATH}/eeprom/openPkgInputPage?pageNum=1&numPerPage=20" target="navTab" rel="pkg_input">包装录入</a></li>
						</ul>
					</div>
					</shiro:hasAnyRoles>
					<shiro:hasAnyRoles name="root">
					<div class="accordionHeader">
						<h2><span>Folder</span>Equipment PM System</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a href="${CONTEXT_PATH}/dwzpage/equipment/mgtboard.jsp" target="navTab" rel="mgtboard">Equipment Mgt Board</a></li>
						</ul>
					</div>
					</shiro:hasAnyRoles>
					
					<shiro:hasAnyRoles name="root,admin_wipublish">
					<div class="accordionHeader">
						<h2><span>Folder</span>WI Publish</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a href="${CONTEXT_PATH}/dcc/myWi?pageNum=1&numPerPage=20" target="navTab" rel="wi_publish">WI 发布</a></li>
						</ul>
					</div>
					</shiro:hasAnyRoles>
					
					<div class="accordionHeader">
						<h2><span>Folder</span>others</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a href="${CONTEXT_PATH}/dwzpage/testpage/testjs.html" target="dialog" rel="dlg_page">列表</a></li>
							<li><a href="newPage1.html" target="dialog" rel="dlg_page2">列表</a></li>
						</ul>
					</div>
						
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<p><span>Amphenol-GIS Web System</span></p>
							
						</div>
						<div class="pageCentent">
								
								<div style="position:absolute;top:40%;left:50%;margin:-100px 0 0 -100px;width:100%;height:400px">   
       								 <span style="font-size:40px">Lean Factory</span>
    							</div>
							    <div style="position:absolute;top:50%;left:50%;margin:-100px 0 0 -350px;width:400px;height:300px">   
       								 <img src="${CONTEXT_PATH}/static/image/aa.png" height="80%" width="80%" /> 
    							</div>   
								<div style="position:absolute;top:50%;left:50%;margin:-100px 0 0 80px;width:400px;height:300px">   
       								 <img src="${CONTEXT_PATH}/static/image/bb.png" height="80%" width="80%" /> 
    							</div> 

						
						</div>
						
				</div>
					
			</div>
		</div>
	</div>

	</div>

	<div id="footer">Copyright &copy; 2014 <a href="http://www.amphenol-tcs.com" target="dialog" external="true">Amphenol-GIS IT Department</a> </div>



</body>
</html>