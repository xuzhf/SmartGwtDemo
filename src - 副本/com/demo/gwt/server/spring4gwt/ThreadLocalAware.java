package com.demo.gwt.server.spring4gwt;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ThreadLocalAware {

	private ThreadLocal<HttpServletRequest> perThreadRequest;

	private ThreadLocal<HttpServletResponse> perThreadResponse;
	
	private ServletContext servletContext;
	
	private static ThreadLocalAware instance = null;

    public static ThreadLocalAware getInstance() {
        if (instance == null) {
            instance = new ThreadLocalAware();
        }
        return instance;
    }
    
	private ThreadLocalAware() {
		
	}

	public HttpServletRequest getThreadLocalRequest() {
		synchronized (this) {
			validateThreadLocalData();
			return perThreadRequest.get();
		}
	}

	public void setThreadLocalRequest(HttpServletRequest threadLocalRequest) {
		synchronized (this) {
			validateThreadLocalData();
			this.perThreadRequest.set(threadLocalRequest);
		}
	}

	public HttpServletResponse getThreadLocalResponse() {
		synchronized (this) {
			validateThreadLocalData();
			return perThreadResponse.get();
		}
	}

	public void setThreadLocalResponse(HttpServletResponse threadLocalResponse) {
		synchronized (this) {
			validateThreadLocalData();
			this.perThreadResponse.set(threadLocalResponse);
		}
	}
	
	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	private void validateThreadLocalData() {
		if (perThreadRequest == null) {
			perThreadRequest = new ThreadLocal<HttpServletRequest>();
		}
		if (perThreadResponse == null) {
			perThreadResponse = new ThreadLocal<HttpServletResponse>();
		}
	}

}
