package com.demo.gwt.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.demo.gwt.dao.BaseMapperDao;
import com.demo.gwt.dao.IFunctionDao;
import com.demo.gwt.entity.Function;
import com.demo.gwt.mapper.FunctionMapper;

@Repository
public class FunctionDaoImpl<T extends Function> implements IFunctionDao<T> {

	@Inject
	private BaseMapperDao<T> mapperDao;
	
	@Override
	public List<T> getOwnerFunction(String memberId) throws DataAccessException {
		mapperDao.setMapperClass(FunctionMapper.class);
		List<T> list = null;
		try {
			list =  ((FunctionMapper<T>)mapperDao.getMapper()).getOwnerFunction(memberId);
		} catch (DataAccessException e) {
			throw e;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return list;
	}

}
