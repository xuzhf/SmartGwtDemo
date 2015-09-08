package com.demo.gwt.model;

/**
 * MemberPrim entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2422904769944312216L;
	/**
	 * 
	 */
	private Integer age;
	   private String name;
	   private Integer id;

	   public void setAge(Integer age) {
	      this.age = age;
	   }
	   public Integer getAge() {
	      return age;
	   }

	   public void setName(String name) {
	      this.name = name;
	   }
	   public String getName() {
	      return name;
	   }

	   public void setId(Integer id) {
	      this.id = id;
	   }
	   public Integer getId() {
	      return id;
	   }
	
}