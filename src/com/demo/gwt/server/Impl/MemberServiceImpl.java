package com.demo.gwt.server.Impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.demo.gwt.client.service.MemberService;
import com.demo.gwt.model.Member;
import com.demo.gwt.server.DAO.IMemberDao;
import com.demo.gwt.shared.util.encryption.MD5;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Inject
	private IMemberDao<Member> memberDao;
	
	public List<Member> getAllMember() throws DataAccessException {
		return memberDao.getAllMember();
	}

	public Boolean existMember(Member entity) throws DataAccessException {
		return memberDao.existMember(entity);
	}
	
	public Member getMemberByLogin(String userName,String password) throws DataAccessException {
		String pwd = MD5.encrypt(password);
		return memberDao.getMemberByLogin(userName,pwd);
	}

	public List<Member> getInfoByPage(int beginIndex, int pageSize)
			throws DataAccessException {
		return memberDao.getInfoByPage(beginIndex, pageSize);
	}

	public int totalCount() throws DataAccessException {
		return memberDao.totalCount();
	}

}
