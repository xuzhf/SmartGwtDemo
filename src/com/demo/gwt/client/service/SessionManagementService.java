package com.demo.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("SessionManagementService")
public interface SessionManagementService extends RemoteService {
	public void setUserName(String userName);
	public String getUserName();
}
