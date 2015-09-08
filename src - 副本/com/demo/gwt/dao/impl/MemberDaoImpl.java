package com.demo.gwt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.demo.gwt.dao.BaseMapperDao;
import com.demo.gwt.dao.IMemberDao;
import com.demo.gwt.entity.Member;
import com.demo.gwt.mapper.MemberMapper;

@Repository
/*用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean,该注解的作用不只是将类识别为Bean，
 * 同时它还能将所标注的类中抛出的数据访问异常封装为 Spring 的数据访问异常类型。*/
public class MemberDaoImpl<T extends Member> implements IMemberDao<T> {

	@Inject
	private BaseMapperDao<T> mapperDao;
	
	@Override
	public List<T> getAllMember() throws DataAccessException {
		mapperDao.setMapperClass(MemberMapper.class);
		List<T> list = null;
		try {
			list =  (List<T>) mapperDao.queryAll();
		} catch (DataAccessException e) {
			throw e;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return list;
	}

	@Override
	public Boolean existMember(T entity) throws DataAccessException {
		mapperDao.setMapperClass(MemberMapper.class);
		boolean flag = false;
		try {
			int result = ((MemberMapper<T>)mapperDao.getMapper()).existMember(entity);
			if(result > 0)
				flag = true;
		} catch (DataAccessException e) {
			throw e;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return flag;
	}

	@Override
	public T getMemberByLogin(String loginName, String loginPassword) {
		mapperDao.setMapperClass(MemberMapper.class);
		Map<String,String> map = new HashMap<String,String>();
		map.put("loginName", loginName);
		map.put("loginPassword", loginPassword);
		T member = null;
		try {
			member = ((MemberMapper<T>)mapperDao.getMapper()).getMemberByLogin(map);
		} catch (DataAccessException e) {
			throw e;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return member;
	}

	@Override
	public List<T> getInfoByPage(int beginIndex, int pageSize)
			throws DataAccessException {
		mapperDao.setMapperClass(MemberMapper.class);
		Map<String,Integer> map = new HashMap<String,Integer> ();
		map.put("beginIndex", beginIndex);
		map.put("pageSize", pageSize);
		List<T> list = null;
		try {
			list = mapperDao.getInfoByPage(map);
		} catch (DataAccessException ex) {
			throw ex;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public int totalCount() throws DataAccessException {
		mapperDao.setMapperClass(MemberMapper.class);
		int result;
		try {
			result = ((MemberMapper<T>)mapperDao.getMapper()).totalCount();
		} catch (DataAccessException e) {
			throw e;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return result;
	}

}
