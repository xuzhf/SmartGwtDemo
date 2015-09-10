package com.demo.gwt.server.Impl;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.demo.gwt.client.service.SessionManagementService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


@Service("SessionManagementService")
public class SessionManagementServiceImpl extends RemoteServiceServlet implements SessionManagementService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void setUserName(String userName)
	{
//	     HttpSession httpSession = getThreadLocalRequest().getSession(true);
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession httpSession = attr.getRequest().getSession();
	     httpSession.setAttribute("userName", userName);
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		{
		    HttpSession session = getThreadLocalRequest().getSession(true);
		    if (session.getAttribute("userName") != null)
		    {
		        return (String) session.getAttribute("userName");
		    }
		    else 
		    {
		        return "";
		    }
		}
	}
}
