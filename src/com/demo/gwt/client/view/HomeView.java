package com.demo.gwt.client.view;

import java.util.List;

import com.demo.gwt.client.pub.widget.GridPaginationBar;
import com.demo.gwt.client.pub.widget.ListGridDataControl;
import com.demo.gwt.client.service.HomeService;
import com.demo.gwt.client.service.HomeServiceAsync;
import com.demo.gwt.model.Student;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeNode;

public class HomeView extends VLayout {
	private String canvasId;

	private GridPaginationBar pageBar;
	private ListGrid homeGrid;
	private HomeServiceAsync service = GWT.create(HomeService.class);

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
	public HomeView(TreeNode[] showcaseData) {
		homeGrid = new ListGrid();
		homeGrid.setCanEdit(false);
		homeGrid.setCanDragRecordsOut(true);
		homeGrid.setWidth100();
		homeGrid.setHoverWidth(200);
		homeGrid.setHoverHeight(20);
		homeGrid.setSelectionType(SelectionStyle.SINGLE);
		homeGrid.setPreventDuplicates(true);
        
        ListGridField pkField = new ListGridField("id");
        pkField.setHidden(true);

        ListGridField name = new ListGridField("name", "登录名");
        ListGridField age = new ListGridField("age", "登录密码");
        
        homeGrid.setFields(pkField,name,age);

		// 分页
		pageBar = new GridPaginationBar(homeGrid, 5,
				homeGrid.getParentElement());

		pageBar.setDataControl(new ListGridDataControl() {
			@Override
			public void fetchData(int startNum, int pageSize) {
				service.listStudents(
						new AsyncCallback<List<Student>>() {
							@Override
							public void onFailure(Throwable caught) {
								SC.say(caught.getMessage());
							}

							@Override
							public void onSuccess(List<Student> result) {
								List<Student> list = result;
								int i = 0;
								ListGridRecord[] datas = new ListGridRecord[result
										.size()];
								for (Student entity : list) {
									ListGridRecord record = new ListGridRecord();
									populateRecord(record, entity);
									datas[i] = record;
									i++;
								}
								pageBar.afterFetchData(datas);
							}
						});
			}

			public void getTotal() {
				if (service == null)
					service = GWT.create(HomeService.class);

				AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {

					@Override
					public void onSuccess(Integer result) {
						pageBar.afterGetTotal(result);
					}

					@Override
					public void onFailure(Throwable caught) {

					}
				};
				service.totalCount(callback);
			}

			@Override
			public void loadData(ListGridRecord[] records) {
				homeGrid.setData(records);
			}
		});

		addMember(homeGrid);
		addMember(pageBar);
		pageBar.active();
	}

	private void populateRecord(ListGridRecord record, Student object) {
		record.setAttribute("id", object.getId());
		record.setAttribute("name", object.getName());
		record.setAttribute("age", object.getAge());
	}
}
