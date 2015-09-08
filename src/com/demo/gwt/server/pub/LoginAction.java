package com.demo.gwt.server.pub;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.demo.gwt.client.service.MemberService;
import com.demo.gwt.model.Member;
import com.demo.gwt.server.spring4gwt.ThreadLocalAware;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,SessionAware{

	private static final long serialVersionUID = -658530005548318172L;
	@Inject
	@Named("memberService")
	private MemberService memberService;
	private int resultCode = -1;
	private Member member;
	private String userName;
	private String password;
	private Map<String, Object> session;
	ThreadLocalAware threadLocal = ThreadLocalAware.getInstance();
	
	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		threadLocal.setThreadLocalResponse(response);
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		threadLocal.setThreadLocalRequest(request);
	}
	
	public String login() throws Exception {
		
		 String kaptchaExpected = (String) threadLocal.getThreadLocalRequest().getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);    
		 String kaptchaReceived = threadLocal.getThreadLocalRequest().getParameter("kaptchafield"); //获取填写的验证码内容    
		 if (kaptchaReceived == null || !kaptchaReceived.equalsIgnoreCase(kaptchaExpected)) {  
			 //判断内容是否相同
			 resultCode = 0;//验证码错误
		 }else{
			 Member userInfo = memberService.getMemberByLogin(userName, password);
			 if(userInfo != null &&userInfo.getId().equals("") == false){
				 session.put("member", userInfo);
				 session.put("memberId", userInfo.getId());
				 session.put("loginName", userInfo.getLoginName());
				 resultCode = 1;//登录成功
			 }else{
				 resultCode = 2;//用户名或密码错误
			 }
		 }
		
		return SUCCESS;
	}

}
