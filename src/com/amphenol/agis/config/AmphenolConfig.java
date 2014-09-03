package com.amphenol.agis.config;

import com.amphenol.UrlConfig;
import com.amphenol.agis.controller.AuthenticationController;
import com.amphenol.agis.controller.CommonController;
import com.amphenol.agis.controller.DCCFileController;
import com.amphenol.agis.controller.EEPROMController;
import com.amphenol.agis.controller.EpromVerifyController;
import com.amphenol.agis.controller.ResourceModelController;
import com.amphenol.agis.controller.RoleModelController;
import com.amphenol.agis.controller.UserModelController;
import com.amphenol.agis.controller.WIViewController;
import com.amphenol.agis.model.CustomerModel;
import com.amphenol.agis.model.DCCListModel;
import com.amphenol.agis.model.EcheckModel;
import com.amphenol.agis.model.EepConfigModel;
import com.amphenol.agis.model.EepLogModel;
import com.amphenol.agis.model.EmployeeModel;
import com.amphenol.agis.model.OrganizationModel;
import com.amphenol.agis.model.PkgModel;
import com.amphenol.agis.model.ProductModel;
import com.amphenol.agis.model.ProgramModel;
import com.amphenol.agis.model.ResourceModel;
import com.amphenol.agis.model.RoleModel;
import com.amphenol.agis.model.ShipdataModel;
import com.amphenol.agis.model.StationModel;
import com.amphenol.agis.model.UserModel;
import com.amphenol.agis.model.VerifyModel;
import com.amphenol.agis.model.WOModel;
import com.amphenol.agis.shiro.SessionHandler;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.plugin.shiro.ShiroInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroPlugin;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

public class AmphenolConfig extends JFinalConfig
{
	//用于ShiroPlugin
	private Routes routes;

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		loadPropertyFile("mysql_connect.txt");
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
		me.setErrorView(401,UrlConfig.LOGIN);
		me.setErrorView(403, UrlConfig.LOGIN);
	}

	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		this.routes=me;
		me.add("/",AuthenticationController.class);
		me.add("/main",CommonController.class);
		me.add("/eprom",EpromVerifyController.class);
		me.add("/user",UserModelController.class);
		me.add("/role",RoleModelController.class);
		me.add("/resource",ResourceModelController.class);
		me.add("/dcc",DCCFileController.class);
		me.add("/wi",WIViewController.class);
		me.add("/eeprom",EEPROMController.class);
		
	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		C3p0Plugin c3p0Plugin=new C3p0Plugin(getProperty("jdbcUrl"),getProperty("user"),getProperty("password").trim());
		me.add(c3p0Plugin);
		ActiveRecordPlugin arp=new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		me.add(new ShiroPlugin(this.routes));
		arp.addMapping("sys_user", UserModel.class);
		arp.addMapping("sys_role",RoleModel.class);
		arp.addMapping("sys_resource", ResourceModel.class);
		arp.addMapping("sys_organization", OrganizationModel.class);
		arp.addMapping("sys_dcc",DCCListModel.class);
		arp.addMapping("sys_eep_config",EepConfigModel.class);
		arp.addMapping("sys_product",ProductModel.class);
		arp.addMapping("sys_eep_program", ProgramModel.class);
		arp.addMapping("sys_eep_verify", VerifyModel.class);
		arp.addMapping("sys_eep_log", EepLogModel.class);
		arp.addMapping("sys_wo",WOModel.class);
		arp.addMapping("sys_employee", EmployeeModel.class);
		arp.addMapping("sys_customer", CustomerModel.class);
		arp.addMapping("sys_shipdata", ShipdataModel.class);
		arp.addMapping("sys_station", StationModel.class);
		arp.addMapping("sys_pkg",PkgModel.class);
		arp.addMapping("sys_echeck", EcheckModel.class);
		}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		me.add(new ShiroInterceptor());
		
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		me.add(new SessionHandler());
		me.add(new ContextPathHandler("CONTEXT_PATH"));
	}
	
	public static void main(String[] args)
	{
		JFinal.start("WebRoot", 8001, "/", 5);
	}

}
