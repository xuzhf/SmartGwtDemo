package com.demo.gwt.client;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;

public class PasswordWindow extends Window {

	public PasswordWindow() {
		setTitle("修改密码");
		setWidth(400);
		setHeight(250);
		setShowMinimizeButton(false);
		setIsModal(false);
		setShowModalMask(true);
		centerInPage();
		
		final DynamicForm editorForm = new DynamicForm();
		editorForm.setMargin(25);
		editorForm.setNumCols(2);
		editorForm.setCellPadding(5);
		editorForm.setAutoFocus(false);

		final PasswordItem nameItem = new PasswordItem("loginName", "原 密 码");
		nameItem.setRequired(true);
		final PasswordItem passwordItem = new PasswordItem("loginPassword", "新密码");
		passwordItem.setRequired(true);
		PasswordItem passwordAgain = new PasswordItem("loginPasswordAgain","重复密码");
		passwordAgain.setRequired(true);
		MatchesFieldValidator matchesValidator = new MatchesFieldValidator();
		matchesValidator.setOtherField("loginPassword");
		matchesValidator.setErrorMessage("两次密码不一致");
		passwordAgain.setValidators(matchesValidator);

		ButtonItem saveButton = new ButtonItem("saveItem", "保	    存");
		saveButton.setAlign(Alignment.CENTER);
		saveButton.setWidth(100);
		saveButton.setColSpan(4);
		saveButton
				.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
					public void onClick(
							com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
						editorForm.validate();
						// 判断是否通过验证
						if (!editorForm.hasErrors()) {
							//updatePawd(loginName,OperaterId,nameItem.getValueAsString(),passwordItem.getValueAsString());
						}
					}
				});
		editorForm.setFields(nameItem, passwordItem, passwordAgain, saveButton);
		addItem(editorForm);
	}
	
}
