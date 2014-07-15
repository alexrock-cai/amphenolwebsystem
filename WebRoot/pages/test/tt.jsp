<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head >
<title>
	安费诺（常州）连接系统有限公司
</title><link rel="shortcut icon" href="favicon.ico" />
<meta name="title" content="安费诺（常州）连接系统有限公司" />
<meta name="copyright" content="安费诺（常州）连接系统有限公司" />
<meta name="keywords" content="安费诺,Amphenol-GIS" />
<meta name="description" content="赛拉弗光伏,seraphim" />
<link href="images/login.css" rel="stylesheet" type="text/css" />
<link href="CSS/LoginCss/LoginCSS.css" rel="Stylesheet" media="all" type="text/css" />
	<script type="text/javascript" src="Js/jquery-1.4.2.min.js"></script>


   

    <script language="javascript" type="text/javascript">
        function AddToFavorite() {
            if (document.all) {

                window.external.addFavorite(document.URL, document.title);
            } else if (window.sidebar) {
                window.sidebar.addPanel(document.title, document.URL, "cosmo");
            }
        }

        function setHomepage() {
            if (document.all) {
                document.body.style.behavior = 'url(#default#homepage)';
                document.body.setHomePage(document.URL);
            } else if (window.sidebar) {
                if (window.netscape) {
                    try {
                        netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
                    } catch (e) {
                        alert("该操作被浏览器拒绝，如果想启用该功能，请在地址栏内输入 about:config, 然后将项 signed.applets.codebase_principal_support 值该为true");
                    }
                }
                var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
                prefs.setCharPref('browser.startup.homepage', document.URL);
            }
        }
        $(document).ready(function () {
            $("#txt_user_name").focus();
            $("#btn_reset").click(function () {
                $("#txt_user_name").val("");
                $("#txt_pass_word").val("");
                $("#txt_user_name").focus();
                $("#imgtxt_user_name").remove();
                $("#imgtxt_pass_word").remove();
                return false
            });

            $("#btn_submit").click(function () {
                var notnull;
                notnull = true;
                var p = "txt_user_name;txt_pass_word";
                var option = p.split(";");
                var divBody = $("div.master");
                for (var i = 0; i < option.length; i++) {
                    var val;
                    if (i == 0) {
                        val = "用户名称不能为空";
                    }
                    else {
                        val = "用户密码不能为空";
                    }
                    //SIMPLIFIED CHINESE_CHINA.ZHS16GBK
                    var vid = option[i];
                    var controlObj = $("#" + vid);
                    var controlValue = $.trim(controlObj.val());
                    if (controlValue == null || controlValue == "") {
                        tp = controlObj.offset().top + 3;
                        le = controlObj.offset().left + controlObj.width() + 20;
                        errorHTML = "<img id=img" + vid + "  src='Images/Main/Default/warning.gif' style='z-index:1000000' title='" + val + "' />";
                        var css = { position: "absolute", top: tp + "px", left: le + "px", visibility: "visible" };
                        $("#img" + vid).remove();
                        divBody.append(errorHTML);
                        $("#img" + vid).css(css);
                        notnull = false;
                    }
                    else {
                        $("#img" + vid).remove();
                    }
                }

                if (!notnull) {
                    return false;
                }

            })
        })
    </script>

    <style type="text/css">
        a {
            color: Black;
            text-decoration: none;
        }

            a:hover {
                color: Red;
            }

            a:visited {
                color: Black;
            }
    </style>
</head>
<body>
    <form method="post" action="" id="form1">
<div class="aspNetHidden">
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="/wEPDwUKLTExMzE1ODAzNGRkocpRSIBBbNV+Bi0Di/3dtFWuxxlk/06V6IIA0xsTL44=" />
</div>

<div class="aspNetHidden">

	<input type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION" value="/wEWBAKt85rJBwLzg+7wAgKssp/yDALwhZ6iD7EGjmlogRLjEATpIZ0+wEU3B33spvIokbg0exhXnul4" />
</div>
        <div class="master">
            <div class='header'>
                <span style="float: right"><a href='javascript:void()' onclick='javascript:setHomepage();'>设为首页</a>|<a onclick='javascript:AddToFavorite();' href='javascript:void()'>加入收藏</a>|<a  href='logo.aspx'>外协工厂登录</a>|<a  href='LOGINS.aspx'>供应商登录</a></span>
                <div>
                    <h1></h1>
                </div>
            </div>
            <div id="login" style='*height: auto;'>
                <div id="top">
                    <div id="top_left">
                        <img src="images/login_03.gif" />
                    </div>
                    <div id="top_center">
                    </div>
                </div>
                <div id="center">
                    <div id="center_left">
                    </div>
                    <div id="center_middle" style='height: 213px;'>
                        <div id="user">
                            用 户
                        <input name="txt_user_name" type="text" id="txt_user_name" class="tx" style="height:20px;width:120px;" />
                        </div>
                        <div id="password">
                            密 码
                        <input name="txt_pass_word" type="password" id="txt_pass_word" class="tx" style="height:20px;width:120px;" />
                        </div>
                        <div id="btn" style='text-align: left; _OVERFLOW: HIDDEN;'>
                            <input type="submit" name="btn_submit" value="登录" id="btn_submit" class="logbtn" style="_float: left; _display: block; _zoom: 1; _MARGIN-LEFT: 0PX;" /><input type="button" id="btn_reset" value="重置" class='logbtn' style="margin-left: 10px; _float: right; _display: block; _zoom: 1;" />
                        </div>
                        <div style='margin-left: 45px;'>
                            <span id="lab_mess" style="color:Red;font-family:Arial;"></span>
                        </div>
                    </div>
                    <div id="center_right">
                    </div>
                </div>
                <div id="down">
                    <div id="down_left">
                        <div id="inf">
                            <span class="inf_text">版本信息</span> <span class="copyright">管理系统 v1.0</span> <span
                                class="copyright">&nbsp;&nbsp;[<a href="GetPassword.aspx">忘记密码</a>|<a href="ModifyInfo.aspx">修改密码</a>]</span>
                        </div>
                    </div>
                    <div id="down_center">
                    </div>
                </div>
            </div>
        </div>
    </form>
</body>
</html>
