package com.amphenol.agis.controller;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.amphenol.UrlConfig;
import com.amphenol.agis.model.UserModel;
import com.jfinal.core.Controller;


public class AuthenticationController extends Controller 
{
	public void index()
	{
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()&subject.isPermitted("pm:*"))
		{
			redirect(UrlConfig.PM_INDEX);
		}
		else if(subject.isAuthenticated()&subject.isPermitted("user:*")){
			redirect(UrlConfig.INDEX_ACTION);
			
		}
		else
		{
			render(UrlConfig.LOGIN);
		}
	}
	
	public void loginView()
	{
		render(UrlConfig.LOGIN);
	}
	
	public void pmLoginView(){
		render(UrlConfig.PM_LOGIN);
	}
	public void login()
	{

		try
		{
			UsernamePasswordToken token=new UsernamePasswordToken(getPara("username"), getPara("password"));
			Subject subject=SecurityUtils.getSubject();
			if(!subject.isAuthenticated())
			{
				token.setRememberMe(true);
				subject.login(token);
				subject.getSession(true).setAttribute("user", UserModel.dao.findByName(getPara("username")));
				
			}
			redirect(UrlConfig.INDEX_ACTION);
		}
		catch(UnknownAccountException e)
		{
			setAttr("msg","用户名不存在");
			e.printStackTrace();
			forwardAction("/");
		}
		catch(IncorrectCredentialsException e)
		{
			setAttr("msg","密码错误");
			e.printStackTrace();
			forwardAction("/");
		}
		catch(LockedAccountException e)
		{
			setAttr("msg","用户被锁了，请联系管理员");
			e.printStackTrace();
			forwardAction("/");
		}
		catch(ExcessiveAttemptsException e)
		{
			setAttr("msg","对不起！尝试次数太多，请稍后再试");
			e.printStackTrace();
			forwardAction("/");
		}
		catch(AuthenticationException e)
		{
			setAttr("msg","没有权限，请联系管理员");
			e.printStackTrace();
			forwardAction("/");
		}
		catch(Exception e)
		{
			setAttr("msg","请重新登录");
			e.printStackTrace();
			forwardAction("/");
		}
	}
	
	public void logout()
	{
		try
		{
			Subject subject = SecurityUtils.getSubject();
			subject.logout();

			render(UrlConfig.LOGIN);

		} catch (AuthenticationException e)
		{
			e.printStackTrace();
			renderText("异常：" + e.getMessage());
		}
	}
	
	public void pmLogin(){
		try
		{
			UsernamePasswordToken token=new UsernamePasswordToken(getPara("username"), getPara("password"));
			Subject subject=SecurityUtils.getSubject();
			if(!subject.isAuthenticated())
			{
				token.setRememberMe(true);
				subject.login(token);
				subject.getSession(true).setAttribute("user", UserModel.dao.findByName(getPara("username")));
			}
			redirect(UrlConfig.PM_INDEX);
		}
		catch(UnknownAccountException e)
		{
			setAttr("msg","用户名不存在");
			e.printStackTrace();
			forwardAction("/pmLoginView");
		}
		catch(IncorrectCredentialsException e)
		{
			setAttr("msg","密码错误");
			e.printStackTrace();
			forwardAction("/pmLoginView");
		}
		catch(LockedAccountException e)
		{
			setAttr("msg","用户被锁了，请联系管理员");
			e.printStackTrace();
			forwardAction("/pmLoginView");
		}
		catch(ExcessiveAttemptsException e)
		{
			setAttr("msg","对不起！尝试次数太多，请稍后再试");
			e.printStackTrace();
			forwardAction("/pmLoginView");
		}
		catch(AuthenticationException e)
		{
			setAttr("msg","没有权限，请联系管理员");
			e.printStackTrace();
			forwardAction("/pmLoginView");
		}
		catch(Exception e)
		{
			setAttr("msg","请重新登录");
			e.printStackTrace();
			forwardAction("/pmLoginView");
		}
	}
	
	public void pmLogout(){
		try
		{
			Subject subject = SecurityUtils.getSubject();
			subject.logout();

			render(UrlConfig.PM_LOGIN);

		} catch (AuthenticationException e)
		{
			e.printStackTrace();
			renderText("异常：" + e.getMessage());
		}
	}
}
