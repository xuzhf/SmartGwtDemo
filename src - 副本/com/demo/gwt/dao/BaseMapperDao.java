package com.demo.gwt.dao;

import java.util.List;
import java.util.Map;

import com.demo.gwt.mapper.BaseSqlMapper;

/**
 * <b>function:</b>
 * @author xiaozhao
 * @createDate 2011-6-14 上午10:33:07
 * @file BaseMapperDao.java
 * @package com.mobile.dao
 * @project zty_mobile_server
 * @email 281785964@163.com
 * @version 1.0
 */
public interface BaseMapperDao<T> {
	
	public void setMapperClass(Class<? extends BaseSqlMapper> mapperClass);
	
	public boolean add(T entity) throws Exception;
	
	public boolean edit(T entity) throws Exception;
	
	public T get(T entity) throws Exception;
	
	public T getEntityById(String id) throws Exception;
	
	public BaseSqlMapper<T> getMapper() throws Exception;
	
	public List<T> getList(T entity) throws Exception;
	
	public List<T> getListById(String id) throws Exception;
	
	public int getCount(String name) throws Exception;
	
	public List<T> getInfoByPage(Map map)throws Exception;

	List<T> queryAll() throws Exception;
}
