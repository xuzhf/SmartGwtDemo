package com.demo.gwt.client.view;

import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class UserGrid extends ListGrid {

	public UserGrid() {
        setCanEdit(false);
        setCanDragRecordsOut(true);
        setWidth100();
        setHoverWidth(200);
        setHoverHeight(20);
        setSelectionType(SelectionStyle.SINGLE);
        setPreventDuplicates(true);
        
        ListGridField pkField = new ListGridField("id");
        pkField.setHidden(true);

        ListGridField nameField = new ListGridField("loginName", "登录名");
        ListGridField passwordField = new ListGridField("loginPassword", "登录密码");
        passwordField.setHidden(true);
        ListGridField statusField = new ListGridField("lifeCycleStatus", "账号状态");
        ListGridField memberClassField = new ListGridField("memberClass", "会员类型");
        memberClassField.setHidden(true);
        ListGridField emailField = new ListGridField("email", "电子邮箱"); 
        ListGridField mobileField = new ListGridField("mobileNum", "手机号码");
        
        setFields(pkField,nameField,passwordField,statusField,memberClassField,emailField,mobileField);
	}
	
}
