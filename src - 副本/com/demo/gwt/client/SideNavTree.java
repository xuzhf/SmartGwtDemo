/*
 * Smart GWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * Smart GWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  Smart GWT is also
 * available under typical commercial license terms - see
 * smartclient.com/license.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */

package com.demo.gwt.client;

import java.util.List;

import com.demo.gwt.client.service.FunctionService;
import com.demo.gwt.client.service.FunctionServiceAsync;
import com.demo.gwt.entity.Function;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.events.DataArrivedEvent;
import com.smartgwt.client.widgets.tree.events.DataArrivedHandler;

public class SideNavTree extends TreeGrid {
	
	private String idSuffix = "";
	private FunctionServiceAsync functionService = GWT.create(FunctionService.class);
	private ExplorerTreeNode[] showcaseData ;

    public SideNavTree() {
        setWidth100();
        setHeight100();
        setCustomIconProperty("icon");
        setAnimateFolderTime(100);
        setAnimateFolders(true);
        setAnimateFolderSpeed(1000);
        setNodeIcon("silk/application_view_list.png");
        setShowSortArrow(SortArrow.CORNER);
        setShowAllRecords(true);
        setLoadDataOnDemand(false);
        setCanSort(false);
        setShowHeader(false);
        
        TreeGridField field = new TreeGridField();
        field.setCanFilter(true);
        field.setName("nodeTitle");
        field.setTitle("<b>Samples</b>");
        setFields(field);
        
        getTreeData();
    }
    
    public void getTreeData(){
    	if(functionService == null)
    		functionService = GWT.create(FunctionService.class);
    	AsyncCallback<List<Function>> callback = new AsyncCallback<List<Function>>() {
			
			@Override
			public void onSuccess(List<Function> result) {
				int i = 0;
				ExplorerTreeNode[] treeData = new ExplorerTreeNode[result.size()];
				for(Function power : result){
					treeData[i] = new ExplorerTreeNode(power.getName(), power.getId(), power.getParentId(),power.getIconPath(), power.getUserPanel(), true, idSuffix);
					i++;
				}
				Tree tree = new Tree();
		        tree.setModelType(TreeModelType.PARENT);
		        tree.setNameProperty("nodeTitle");
		        tree.setOpenProperty("isOpen");
		        tree.setIdField("nodeID");
		        tree.setParentIdField("parentNodeID");
		        tree.setRootValue("root" + idSuffix);
		        
				tree.setData(treeData);
				showcaseData = treeData;
		        setData(tree);
		        
		        addDataArrivedHandler(new DataArrivedHandler() {  
		            public void onDataArrived(DataArrivedEvent event) {  
		            	getData().openAll();  
		            }  
		        }); 
			}
			
			@Override
			public void onFailure(Throwable caught) {
				SC.say("获取用户功能列表失败！！！");
			}
		};;;
		
		functionService.getOwnerFunction(callback );
    }

    
    public ExplorerTreeNode[] getShowcaseData() {
        return showcaseData;
    }
    
}
