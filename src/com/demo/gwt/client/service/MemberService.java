package com.demo.gwt.client.service;

import java.util.List;

import com.demo.gwt.model.Member;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("web/memberService")
public interface MemberService extends RemoteService {
	
	List<Member> getAllMember() throws RuntimeException;
	
	Boolean existMember(Member entity) throws RuntimeException;

	Member getMemberByLogin(String userName, String password) throws RuntimeException;
	
	List<Member> getInfoByPage(int beginIndex,int pageSize) throws RuntimeException;
	
	int totalCount()throws RuntimeException;
}
