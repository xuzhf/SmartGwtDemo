package com.demo.gwt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface IMemberDao<T> {
	
	List<T> getAllMember() throws DataAccessException;
	
	Boolean existMember(T entity) throws DataAccessException;

	T getMemberByLogin(String userName, String pwd) throws DataAccessException;
	
	List<T> getInfoByPage(int beginIndex,int pageSize) throws DataAccessException;
	
	int totalCount()throws DataAccessException;
}
