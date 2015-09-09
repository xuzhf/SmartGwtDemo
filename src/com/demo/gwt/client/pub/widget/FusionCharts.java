package com.demo.gwt.client.pub.widget;

import com.smartgwt.client.widgets.Canvas;

public class FusionCharts extends Canvas {
	 
    public FusionCharts() {
        setRedrawOnResize(false);
        setWidth(400);
        setHeight(200);
    }
 
    @Override
    public String getInnerHTML() {
        return "<div id='"+getID()+"_chartContainer'>FusionCharts will load here!</div>";
    }
 
    public void resizeContainerAndActiveChart(String chartId, int width, int height) {
    setWidth(width);
        setHeight(height);
        resizeActiveChart(chartId, width, height);
    }
 
    public native void resizeActiveChart(String chartId, int width, int height) /*-{
    try {
        var chart = $wnd.FusionCharts(chartId);
        // resizeTo() does not exist in FusionCharts free version
        if (chart.resizeTo != null) chart.resizeTo(width, height);
    } catch (e) {
        // ignore
    }
    }-*/;
 
    /**
     * Remove a chart instance from page and memory
     * @param chartId
     */
    public native void removeChart(String chartId) /*-{
    try {
        var chart = $wnd.FusionCharts(chartId);
            // dispose() does not exist in FusionCharts free version
            if (chart.dispose != null) chart.dispose();
    } catch (e) {
        // ignore
    }
    }-*/;
 
    public void getFusionChart(String data, String chartType, String chartId) {
    getFusionChart(data, chartType, getInnerWidth(), getInnerHeight(), chartId, getID()+"_chartContainer");
    }
 
    public native void getFusionChart(String data, String chartType, int width, int height, String chartId, String chartContainerID) /*-{
        var chart = new $wnd.FusionCharts(chartType, chartId, width, height);
    if (chart.setXMLData != null ) chart.setXMLData(data);
    else chart.setDataXML(data);
    chart.render(chartContainerID);
    }-*/;
 
    public void getFusionMap(String data, String chartType, String chartId) {
    getFusionMap(data, chartType, getInnerWidth(), getInnerHeight(), chartId, getID()+"_chartContainer");
    }
 
    public native void getFusionMap(String data, String chartType, int width, int height, String chartId, String chartContainerID) /*-{
        if ($wnd.FusionCharts.setCurrentRenderer != null) {
            $wnd.FusionCharts.setCurrentRenderer('javascript');
        var chart = new $wnd.FusionCharts(chartType, chartId, width, height);
        chart.setXMLData(data);
        chart.render(chartContainerID);
    }
    }-*/;
}
