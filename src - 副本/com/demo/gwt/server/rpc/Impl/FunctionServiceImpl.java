package com.demo.gwt.server.rpc.Impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.demo.gwt.client.service.FunctionService;
import com.demo.gwt.dao.IFunctionDao;
import com.demo.gwt.entity.Function;
import com.demo.gwt.server.spring4gwt.ThreadLocalAware;

@Service("functionService")
public class FunctionServiceImpl implements FunctionService {
	
	@Inject
	private IFunctionDao<Function> functionDao;
	
	@Override
	public List<Function> getOwnerFunction() throws DataAccessException {
		System.out.println("1-------------");
		ThreadLocalAware threadLocal = ThreadLocalAware.getInstance();
		String memberId = threadLocal.getThreadLocalRequest().getSession().getAttribute("memberId").toString();
		return functionDao.getOwnerFunction(memberId);
	}

}
