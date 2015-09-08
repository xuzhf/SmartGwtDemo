package com.demo.gwt.client.view;

import java.util.List;

import com.demo.gwt.client.ContentPanel;
import com.demo.gwt.client.PanelFactory;
import com.demo.gwt.client.pub.widget.GridPaginationBar;
import com.demo.gwt.client.pub.widget.ListGridDataControl;
import com.demo.gwt.client.reflection.ClassForNameAble;
import com.demo.gwt.client.service.MemberService;
import com.demo.gwt.client.service.MemberServiceAsync;
import com.demo.gwt.entity.Member;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

@ClassForNameAble
public class MemberView extends ContentPanel implements PanelFactory {
	
	private String canvasId;
	
	private GridPaginationBar pageBar;
	private UserGrid userGrid;
	private MemberServiceAsync service = GWT.create(MemberService.class);
	
	@Override
	public Canvas createCanvas() {
		MemberView user = new MemberView();
		user.addMember(user.getViewPanel());
		canvasId = user.getID();
		return user;
	}

	public String getCanvasID() {
		return canvasId;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Canvas getViewPanel() {
		VLayout layout = new VLayout();
		layout.setAlign(Alignment.CENTER);
		layout.setWidth100();
		
		userGrid = new UserGrid();
		
		//分页
		pageBar = new GridPaginationBar(userGrid,5,userGrid.getParentElement());
		
		pageBar.setDataControl(new ListGridDataControl(){
			@Override
			public void fetchData(int startNum,int pageSize){
				service.getInfoByPage(startNum, pageSize,
					new AsyncCallback<List<Member>>() {
					@Override
					public void onFailure(Throwable caught) {
						SC.say(caught.getMessage());
					}
					
					@Override
					public void onSuccess(List<Member> result) {
						List<Member> list = result;
						int i = 0;
						ListGridRecord[] datas = new ListGridRecord[result.size()];
						for(Member entity : list){
							ListGridRecord record = new ListGridRecord();
							populateRecord(record,entity);
							datas[i] = record;
							i++;
						}
						pageBar.afterFetchData(datas);
					}
				});
			}
			public void getTotal() {	
				if(service == null)
					service = GWT.create(MemberService.class);
				
				AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {
					
					@Override
					public void onSuccess(Integer result) {
						pageBar.afterGetTotal(result);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						
					}
				};;;
				service.totalCount(callback );
			}
			@Override
			public void loadData(ListGridRecord[] records) {
				userGrid.setData(records);
		}});
		
		layout.addMember(userGrid);
		layout.addMember(pageBar);
		pageBar.active();
		
		return layout;
	}
	
	private void populateRecord(ListGridRecord record, Member object) {
		record.setAttribute("id", object.getId());
		record.setAttribute("loginName", object.getLoginName());
		record.setAttribute("loginPassword", object.getLoginPassword());
		record.setAttribute("lifeCycleStatus", object.getLifeCycleStatus());
		record.setAttribute("memberClass", object.getMemberClass());
		record.setAttribute("email", object.getEmail());
		record.setAttribute("mobileNum", object.getMobileNum());
	}

}
