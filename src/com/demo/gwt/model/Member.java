package com.demo.gwt.model;

/**
 * MemberPrim entity. @author MyEclipse Persistence Tools
 */

public class Member implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String loginName;
	private String realName;
	private String loginPassword;
	private String lifeCycleStatus;
	private String memberClass;
	private String email;
	private String mobileNum;
	private float cash;
	// Constructors

	/** default constructor */
	public Member() {
	}

	/** minimal constructor */
	public Member(String id, String loginName) {
		this.id = id;
		this.loginName = loginName;
	}

	/** full constructor */
	public Member(String id, String loginName, String loginPassword,
			String lifeCycleStatus, String memberClass, String email,
			String mobileNum) {
		this.id = id;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.lifeCycleStatus = lifeCycleStatus;
		this.memberClass = memberClass;
		this.email = email;
		this.mobileNum = mobileNum;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getLifeCycleStatus() {
		return this.lifeCycleStatus;
	}

	public void setLifeCycleStatus(String lifeCycleStatus) {
		this.lifeCycleStatus = lifeCycleStatus;
	}

	public String getMemberClass() {
		return this.memberClass;
	}

	public void setMemberClass(String memberClass) {
		this.memberClass = memberClass;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNum() {
		return this.mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public float getCash() {
		return cash;
	}

	public void setCash(float cash) {
		this.cash = cash;
	}
	
}