package com.demo.gwt.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.demo.gwt.dao.BaseMapperDao;
import com.demo.gwt.mapper.BaseSqlMapper;

/**
 * <b>function:</b>运用SqlSessionTemplate封装Dao常用增删改方法，可以进行扩展
 * @author xiaozhao
 * @createDate 2011-6-14 上午10:44:12
 * @file BaseMapperDaoImpl.java
 * @package com.mobile.dao.impl
 * @project zty_mobile_server
 * @email 281785964@163.com
 * @version 1.0
 */
@SuppressWarnings("unchecked")
@Repository
@Lazy  //用以解决循环依赖警告
public class BaseMapperDaoImpl<T> extends SqlSessionTemplate implements
		BaseMapperDao<T> {

	@Inject
	public BaseMapperDaoImpl(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}

	@SuppressWarnings("rawtypes")
	private Class<? extends BaseSqlMapper> mapperClass;

	public void setMapperClass(Class<? extends BaseSqlMapper> mapperClass) {
		this.mapperClass = mapperClass;
	}

	public BaseSqlMapper<T> getMapper() {
		return this.getMapper(mapperClass);
	}

	public T get(T entity) throws Exception {
		return this.getMapper().get(entity);
	}

	public boolean add(T entity) throws Exception {
		boolean flag = false;
		try {
			if(this.getMapper().add(entity)>0)
				flag = true;
		} catch (Exception e) {
			flag = false;
			throw e;
		}
		return flag;
	}

	public int getCount(String name) throws Exception {
		int count;
		try {
			count = this.getMapper().getCount(name);
		} catch (Exception e) {
			count = 0;
			throw e;
		}
		return count;
	}

	public List<T> getInfoByPage(Map map) throws Exception {
		List<T> list = null;
		list = this.getMapper().getInfoByPage(map);
		return list;
	}

	public T getEntityById(String id) throws Exception {
		return this.getMapper().getEntityById(id);

	}

	public List<T> getList(T entity) throws Exception {
		return this.getMapper().getList(entity);
	}
	
	public List<T> queryAll() throws Exception {
		return this.getMapper().queryAll();
	}

	public boolean edit(T entity) throws Exception {
		boolean flag = false;
		if(this.getMapper().edit(entity)>0){
			flag = true;
		}
		return flag;
	}

	public List<T> getListById(String id) throws Exception {
		return this.getMapper().getListById(id);
	}
}
