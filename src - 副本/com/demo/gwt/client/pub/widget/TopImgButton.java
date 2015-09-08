package com.demo.gwt.client.pub.widget;

import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author xiaozhao
 * 有激活和置灰两种状态的图片按钮
 */
public class TopImgButton extends HLayout{
	Img enable = new Img();
	Img disable = new Img();
	private boolean _enable = true;
	public TopImgButton(int buttonWidth,int buttonHeight,String enableImgSrc,String disableImgSrc){
		setWidth(buttonWidth);
		setHeight(buttonHeight);
		enable.setSrc(enableImgSrc);
		enable.setWidth(buttonWidth);
		enable.setHeight(buttonHeight);
		enable.setCursor(Cursor.POINTER);
		disable.setSrc(disableImgSrc);
		disable.setWidth(buttonWidth);
		disable.setHeight(buttonHeight);
		setMembersMargin(1);
		addMember(enable);
	}
	
	public void enable(){
		if(!_enable){
			removeMember(disable);
			addMember(enable);
			_enable = !_enable;
		}
	}
	
	public void disable(){
		if(_enable){
			removeMember(enable);
			addMember(disable);
			_enable = !_enable;
		}
	}
	
	public void setButtonClickHandler(ClickHandler handler){
		enable.addClickHandler(handler);
	}
	
	public void setButtonTip(String tipMessage){
		enable.setTooltip(tipMessage);
	}
}
