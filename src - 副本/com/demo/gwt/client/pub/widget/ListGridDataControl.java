package com.demo.gwt.client.pub.widget;

import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * 
 * @author xiaozhao
 * listGrid数据获取及装载接口 ，和工具条有紧密的相互调用
 * grid加分页工具条时，需要实现此接口并提供给分页工具条
 * 
 * 
 * 示例：
 * 
 * GridPaginationBar pageBar = new GridPaginationBar(bizGrid,20,bizGrid.getParentElement());
 * 
 * pageBar.setDataControl(new ListGridDataControl(){
 *			public void fetchData(int start, int pageSize) {
 *				service.getBusiness(sid,start,pageSize,
 *		                new AsyncCallback<List<XXXXVO>>() {
 *		                    public void onFailure(Throwable caught) {
 *		                        Window.alert(caught.getMessage());
 *		                    }
 *		                    public void onSuccess(List<XXXXVO> result) {
 *		                        list = result;
 *		                        pageBar.afterFetchData(PendingBizData.getNewRecords(result));
 *		                    }
 *		                });
 *			}
 *			public void getTotal() {	
 *				service.getPendingAuditBusinessTotal(sid, new AsyncCallback<Integer>(){
 *					public void onFailure(Throwable arg0) {
 *						Window.alert(arg0.getMessage());
 *					}
 *					public void onSuccess(Integer arg0) {
 *						pageBar.afterGetTotal(arg0);
 *					}} );
 *			}
 *			public void loadData(ListGridRecord[] records) {
 *				 bizGrid.setData(records);
 *			}});
 */
public interface ListGridDataControl {

	/**
	 * 获取分页数据，并回调分页工具栏的afterFetchData()方法，
	 * 如果需要异步获取数据，请注意回调afterFetchData()的时机，保持数据的同步操作。
	 * 
	 * @param startNum
	 * @param pageSize
	 */
	public void fetchData(int startNum,int pageSize);
	
	/**
	 * 获取数据记录总数，并回调分页工具栏的afterGetTotal()方法
	 * 如果需要异步获取数据，请注意回调afterGetTotal()的时机，保持数据的同步操作。
	 * 
	 * @return 总数
	 */
	public void getTotal();
	
	/**
	 * 把数据装载入grid 
	 * @param records
	 */
	public void loadData(ListGridRecord[] records);
}
