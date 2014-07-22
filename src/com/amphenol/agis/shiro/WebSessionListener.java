package com.amphenol.agis.shiro;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

import com.amphenol.Config;



public class WebSessionListener extends SessionListenerAdapter {
	
	@Override
	public void onExpiration(Session session) {
		super.onExpiration(session);
		
		System.out.println("---session过期处理");
		session.removeAttribute(Config.SESSION_USER);

	
	}
}