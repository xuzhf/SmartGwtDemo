package com.demo.gwt.client;

import com.smartgwt.client.widgets.Canvas;

public interface PanelFactory {

    Canvas createCanvas();

    String getCanvasID();

}
