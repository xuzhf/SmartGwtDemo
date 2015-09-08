package com.demo.gwt.client.service;

import java.util.List;

import com.demo.gwt.model.Member;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MemberServiceAsync {

	void existMember(Member entity, AsyncCallback<Boolean> callback);

	void getAllMember(AsyncCallback<List<Member>> callback);

	void getMemberByLogin(String userName, String password,
			AsyncCallback<Member> callback);

	void getInfoByPage(int beginIndex, int pageSize,
			AsyncCallback<List<Member>> callback);

	void totalCount(AsyncCallback<Integer> callback);

}
