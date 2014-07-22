package com.amphenol.agis.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import com.amphenol.Config;
import com.amphenol.agis.model.RoleModel;
import com.amphenol.agis.model.UserModel;

public class ShiroDbRealm extends AuthorizingRealm{

	
	/**
	 * 认证回调函数,登录时调用.
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		UserModel user = UserModel.dao.findByName(token.getUsername());
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getStr(Config.LOGIN_NAME),user.getStr(Config.LOGIN_PASSWORD) , getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String loginName = (String) principals.fromRealm(getName()).iterator().next();
		UserModel user = UserModel.dao.findByName(loginName);
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			info.setRoles(user.getRoleNameList());
			for (RoleModel role : user.getRoleList()) {
				info.addStringPermissions(role.getPermissionNameList());
			}
			return info;
		} else {
			return null;
		}
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}



}