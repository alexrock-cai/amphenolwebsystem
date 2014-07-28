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
		debug:true,	// 调试模式 【true|false】
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
					<li><a href="#" target="dialog" width="600">修改密码</a></li>
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
						<h2><span>Folder</span>应用组件</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${CONTEXT_PATH}/dwzpage/main.html" target="navTab" rel="main" >主页</a></li>
							<li><span>WI(作业指导书系统)</span>
								<ul>
									<li><a href="${CONTEXT_PATH}/dcc/wiview?pageNum=1&numPerPage=20" target="navTab" rel="wi_list">WI清单</a></li>
									<li><a href="http://www.baidu.com" target="navTab" rel="page1">页面一(外部页面)</a></li>
									<li><a href="demo_page2.html" target="navTab" rel="external" external="true">iframe navTab页面</a></li>
									<li><a href="demo_page1.html" target="navTab" rel="page1" fresh="false">替换页面一</a></li>
									<li><a href="demo_page2.html" target="navTab" rel="page2">页面二</a></li>
									<li><a href="demo_page4.html" target="navTab" rel="page3" title="页面三（自定义标签名）">页面三</a></li>
									<li><a href="demo_page4.html" target="navTab" rel="page4" fresh="false">测试页面（fresh="false"）</a></li>
									<li><a href="w_editor.html" target="navTab">表单提交会话超时</a></li>
									<li><a href="demo/common/ajaxTimeout.html" target="navTab">navTab会话超时</a></li>
									<li><a href="demo/common/ajaxTimeout.html" target="dialog">dialog会话超时</a></li>
									<li><a href="index_menu.html" target="_blank">横向导航条</a></li>
								</ul>
							</li>
						</ul>
						
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>登录信息</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">

						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>关于</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a href="newPage1.html" target="dialog" rel="dlg_page">列表</a></li>
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
						
							    <div style="width:50%;margin:0 300px;">   
       								 <img src="/static/image/first_workshop.jpg" height="80%" width="100%" /> 
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