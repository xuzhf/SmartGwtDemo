package com.demo.gwt.client.service;

import java.util.List;

import com.demo.gwt.entity.Function;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FunctionServiceAsync {

	void getOwnerFunction(AsyncCallback<List<Function>> callback);

}
