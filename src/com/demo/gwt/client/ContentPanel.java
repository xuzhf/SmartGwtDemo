package com.demo.gwt.client;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.smartgwt.client.widgets.toolbar.ToolStripSpacer;

public abstract class ContentPanel extends VLayout {

	private Canvas viewPanel;
	protected ToolStrip topBar;
	protected ToolStripButton printButton;
	protected HLayout wrapper;
	public ContentPanel() {

		setWidth100();
		setHeight100();

		topBar = new ToolStrip();
		topBar.setWidth100();
		topBar.addFill();

		printButton = new ToolStripButton();
		printButton.setTitle("打  印");
		printButton.setIcon("silk/printer.png");
		printButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Canvas.showPrintPreview(viewPanel);
			}
		});
		topBar.addMember(printButton);

		topBar.addSpacer(new ToolStripSpacer(6));
		addMember(topBar);

	}
	
	protected void addPrintListener(ClickHandler handler) {
		printButton.addClickHandler(handler);
	}

	public abstract Canvas getViewPanel();

}
