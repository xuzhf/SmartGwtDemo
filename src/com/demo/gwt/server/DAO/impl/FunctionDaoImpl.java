package com.demo.gwt.server.DAO.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.demo.gwt.model.Function;
import com.demo.gwt.server.DAO.IBaseMapperDao;
import com.demo.gwt.server.DAO.IFunctionDao;
import com.demo.gwt.shared.mapper.FunctionMapper;

@Repository
public class FunctionDaoImpl<T extends Function> implements IFunctionDao<T> {

	@Inject
	private IBaseMapperDao<T> mapperDao;
	
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
