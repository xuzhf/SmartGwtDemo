package com.demo.gwt.entity;

import com.demo.gwt.client.PanelFactory;
import com.demo.gwt.client.reflection.ClassTools;

/**
 * Function entity. @author MyEclipse Persistence Tools
 */

public class Function implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String parentId;
	private String name;
	private String description;
	private String functionClass;
	private String functionCode;
	private String url;
	private Integer level;
	private String iconPath;
	private Integer sequence;

	// Constructors

	/** default constructor */
	public Function() {
	}

	/** minimal constructor */
	public Function(String id) {
		this.id = id;
	}

	/** full constructor */
	public Function(String id, String parentId, String name,
			String description, String functionClass, String functionCode,
			String url, Integer level, String iconPath, Integer sequence) {
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.description = description;
		this.functionClass = functionClass;
		this.functionCode = functionCode;
		this.url = url;
		this.level = level;
		this.iconPath = iconPath;
		this.sequence = sequence;
	}
	
	public PanelFactory getUserPanel(){
		
		PanelFactory factory = ClassTools.newInstance(functionClass);

		return factory;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFunctionClass() {
		return this.functionClass;
	}

	public void setFunctionClass(String functionClass) {
		this.functionClass = functionClass;
	}

	public String getFunctionCode() {
		return this.functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getIconPath() {
		return this.iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public Integer getSequence() {
		return this.sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

}