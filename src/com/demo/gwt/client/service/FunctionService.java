package com.demo.gwt.client.service;

import java.util.List;

import com.demo.gwt.model.Function;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("web/functionService")
public interface FunctionService extends RemoteService{
	List<Function> getOwnerFunction() throws RuntimeException;
}
