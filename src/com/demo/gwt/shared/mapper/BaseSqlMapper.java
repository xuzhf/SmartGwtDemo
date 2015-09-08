package com.demo.gwt.shared.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

/**
 * <b>function:</b>BaseSqlMapper继承SqlMapper，对Mapper进行接口封装，定义常用的增删改查方法；
 * 也可以对该接口进行扩展和封装
 * @author xiaozhao
 * @createDate 2011-5-10 下午03:26:08
 * @file BaseSqlMapper.java
 * @package com.bidxi.mapper
 * @project batisbidxi
 * @email 281785964@163.com
 * @version 1.0
 */

public interface BaseSqlMapper<T> extends SqlMapper {
	
	 public int add(T entity) throws DataAccessException;
	    
	 public int edit(T entity) throws DataAccessException;
	    
	 public T get(T entity) throws DataAccessException;
	  
	 public T getEntityById(String id) throws DataAccessException;
	    
	 public List<T> getList(T entity) throws DataAccessException;
	 
	 public List<T> queryAll() throws DataAccessException;
	 
	 public List<T> getListById(String id) throws DataAccessException;
	    
	 public void remove(T entity) throws DataAccessException;
	 
	 public int getCount(String name)throws DataAccessException;
	 
	 public List<T> getInfoByPage(Map map)throws DataAccessException;

}