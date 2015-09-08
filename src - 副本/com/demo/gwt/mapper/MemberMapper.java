package com.demo.gwt.mapper;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.demo.gwt.entity.Member;

public interface MemberMapper<T extends Member> extends BaseSqlMapper<T> {
	
	int existMember(T entity) throws DataAccessException;
	
	T getMemberByLogin(Map<String,String> map) throws DataAccessException;
	
	int totalCount()throws DataAccessException;
}
