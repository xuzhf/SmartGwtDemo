package com.demo.gwt.shared.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.demo.gwt.model.Function;

public interface FunctionMapper<T extends Function> extends BaseSqlMapper<T> {
	
	List<T> getOwnerFunction(String memberId) throws DataAccessException;
	
}
