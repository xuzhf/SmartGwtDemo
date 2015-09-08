package com.demo.gwt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface IFunctionDao<T> {
	
	List<T> getOwnerFunction(String memberId) throws DataAccessException;
}
