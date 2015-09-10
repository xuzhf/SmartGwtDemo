package com.demo.gwt.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SessionManagementServiceAsync {

	void getUserName(AsyncCallback<String> callback);

	void setUserName(String userName, AsyncCallback<Void> callback);

}
