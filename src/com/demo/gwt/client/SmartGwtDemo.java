package com.demo.gwt.client;

import java.util.Date;
import java.util.LinkedHashMap;

import com.demo.gwt.client.view.HomeView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.Version; 
import com.smartgwt.client.core.KeyIdentifier;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.TabBarControls;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemIfFunction;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.LeafClickEvent;
import com.smartgwt.client.widgets.tree.events.LeafClickHandler;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
@SuppressWarnings("deprecation")
public class SmartGwtDemo implements EntryPoint, HistoryListener {
	private TabSet mainTabSet;
    private SideNavTree sideNav;
    private Menu contextMenu;
    private HomeView homeView;
    private Window introWindow;
    private HLayout homePanel;
    private static final ProjectMessages M = ProjectMessages.INSTANCE;
    public void onModuleLoad() {
        final String initToken = History.getToken();

        //setup overall layout / viewport
        VLayout main = new VLayout() {
            @Override
            protected void onInit() {
                super.onInit();
                if (initToken.length() != 0) {
                    onHistoryChanged(initToken);
                }
            }
        };

        ToolStrip topBar = new ToolStrip();
        topBar.setHeight(33);
        topBar.setWidth100();

        topBar.addSpacer(6);
        ImgButton sgwtHomeButton = new ImgButton();
        sgwtHomeButton.setSrc("pieces/24/Home.png");
        sgwtHomeButton.setWidth(48);
        sgwtHomeButton.setHeight(48);
        sgwtHomeButton.setPrompt("系统");
        sgwtHomeButton.setHoverStyle("interactImageHover");
        sgwtHomeButton.setShowRollOver(false);
        sgwtHomeButton.setShowDownIcon(false);
        sgwtHomeButton.setShowDown(false);
        sgwtHomeButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            public void onClick(ClickEvent event) {
//                com.google.gwt.user.client.Window.open("http://code.google.com/p/smartgwt/", "sgwt", null);
            }
        });
        topBar.addMember(sgwtHomeButton);
        topBar.addSpacer(6);

        Label title = new Label("运营管理系统");
		title.setStyleName("sgwtTitle");
		title.setWidth(300);
		topBar.addMember(title);

		topBar.addFill();

		Label welcome = new Label();
		welcome.setWidth(135);
		welcome.setContents("尊敬的  <span style='font-size:12px;color:blue;'>"
				+ DOM.getElementById("loginName").getInnerHTML() + "</span>&nbsp&nbsp欢迎您");
		topBar.addMember(welcome);
		topBar.addSeparator();

		Label time = new Label();
		time.setStyleName("sgwtTitle");
		time.setWidth(130);
		time.setContents("<span style='font-size:12px;color:blue;'>"
				+ DateTimeFormat.getFormat("yyyy年MM月dd日 EEE")
						.format(new Date()) + "</span>");
		topBar.addMember(time);
		topBar.addSeparator();

		ToolStripButton modification = new ToolStripButton();
		modification.setTitle("修改密码");
		modification.setIcon("silk/vcard_edit.png");
		modification
				.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
					public void onClick(ClickEvent event) {
						PasswordWindow window = new PasswordWindow();
						window.show();
					}
				});

		topBar.addButton(modification);
		topBar.addSeparator();

		ToolStripButton exitConsoleButton = new ToolStripButton();
		exitConsoleButton.setTitle("退    出");
		exitConsoleButton.setIcon("silk/exit.png");
		exitConsoleButton
				.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
					public void onClick(ClickEvent event) {
						BooleanCallback callback = new BooleanCallback() {
							@Override
							public void execute(Boolean value) {
								if (value) {
									exist();
								}
							}

						};;;
						SC.ask("退    出", "您确定要退出系统吗？", callback);
					}
				});

		topBar.addButton(exitConsoleButton);
		topBar.addSpacer(6);

        main.addMember(topBar);

        main.setWidth100();
        main.setHeight100();
        main.setStyleName("tabSetContainer");

        HLayout hLayout = new HLayout();
        hLayout.setLayoutMargin(5);
        hLayout.setWidth100();
        hLayout.setHeight100();

        VLayout sideNavLayout = new VLayout();
        sideNavLayout.setHeight100();
        sideNavLayout.setWidth(185);
        sideNavLayout.setShowResizeBar(true);

        sideNav = new SideNavTree();
//        sideNav.openFolder(node);
        sideNav.addLeafClickHandler(new LeafClickHandler() {
            public void onLeafClick(LeafClickEvent event) {
                TreeNode node = event.getLeaf();
                showSample(node);
            }
        });

        sideNavLayout.addMember(sideNav);
        
        ToolStrip toolStripVersion = new ToolStrip();
        toolStripVersion.setWidth100();
        toolStripVersion.setHeight(44);
        final Label version = new Label(M.versionLabelContents(Version.getVersion(), "" + Version.getBuildDate()).asString());
        version.setWidth100();
        version.setHeight100();
        version.setPadding(5);
        version.setValign(VerticalAlignment.CENTER);
        toolStripVersion.addMember(version);
        sideNavLayout.addMember(toolStripVersion);
        
        homePanel = new HLayout();
        homePanel.setHeight100();
        homePanel.setWidth100();
        homePanel.setOverflow(Overflow.HIDDEN);

        homeView = new HomeView(sideNav.getShowcaseData());
        homeView.setWidth100();
        homePanel.addMember(homeView);
        
        hLayout.addMember(sideNavLayout);

        mainTabSet = new TabSet();

        Layout paneContainerProperties = new Layout();
        paneContainerProperties.setLayoutMargin(0);
        paneContainerProperties.setLayoutTopMargin(1);
        mainTabSet.setPaneContainerProperties(paneContainerProperties);

        mainTabSet.setWidth100();
        mainTabSet.setHeight100();
        mainTabSet.addTabSelectedHandler(new TabSelectedHandler() {
            public void onTabSelected(TabSelectedEvent event) {
                Tab selectedTab = event.getTab();
                String historyToken = selectedTab.getAttribute("historyToken");
                if (historyToken != null) {
                    History.newItem(historyToken, false);
                } else {
                    History.newItem("main", false);
                }
            }
        });

        LayoutSpacer layoutSpacer = new LayoutSpacer();
        layoutSpacer.setWidth(5);

        SelectItem selectItem = new SelectItem();
        selectItem.setHeight(21);
        selectItem.setWidth(130);
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
        valueMap.put("EnterpriseBlue", "Enterprise Blue");
        valueMap.put("Enterprise", "Enterprise Gray");
        valueMap.put("Graphite", "Graphite");
        valueMap.put("Simplicity", "Simplicity");


        selectItem.setValueMap(valueMap);

        final String skinCookieName = "skin_name_2_4";
        String currentSkin = Cookies.getCookie(skinCookieName);
        if (currentSkin == null) {
            currentSkin = "Enterprise";
        }
        selectItem.setDefaultValue(currentSkin);
        selectItem.setShowTitle(false);
        selectItem.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
                Cookies.setCookie(skinCookieName, (String) event.getValue());
                com.google.gwt.user.client.Window.Location.reload();
            }
        });

        DynamicForm form = new DynamicForm();
        form.setPadding(0);
        form.setMargin(0);
        form.setCellPadding(1);
        form.setNumCols(1);
        form.setFields(selectItem);

        mainTabSet.setTabBarControls(TabBarControls.TAB_SCROLLER, TabBarControls.TAB_PICKER, layoutSpacer, form);

        contextMenu = createContextMenu();

        Tab tab = new Tab();
        tab.setTitle("首页&nbsp;&nbsp;");
        tab.setIcon("pieces/16/0050.png", 16);
        tab.setWidth(80);

        tab.setPane(homePanel);

        mainTabSet.addTab(tab);

        Canvas canvas = new Canvas();
        canvas.setBackgroundImage("[SKIN]/shared/background.gif");
        canvas.setWidth100();
        canvas.setHeight100();
        canvas.addChild(mainTabSet);

        hLayout.addMember(canvas);
        main.addMember(hLayout);
        main.draw();

        // Add history listener
        History.addHistoryListener(this);
//        showOverview("测试");
        RootPanel p = RootPanel.get("loadingWrapper");
        if (p != null) RootPanel.getBodyElement().removeChild(p.getElement());
        
    }

    private Menu createContextMenu() {
        Menu menu = new Menu();
        menu.setWidth(140);

        MenuItemIfFunction enableCondition = new MenuItemIfFunction() {
            public boolean execute(Canvas target, Menu menu, MenuItem item) {
                int selectedTab = mainTabSet.getSelectedTabNumber();
                return selectedTab != 0;
            }
        };

        MenuItem closeItem = new MenuItem("关闭");
        closeItem.setEnableIfCondition(enableCondition);
        closeItem.setKeyTitle("Alt+C");
        KeyIdentifier closeKey = new KeyIdentifier();
        closeKey.setAltKey(true);
        closeKey.setKeyName("C");
        closeItem.setKeys(closeKey);
        closeItem.addClickHandler(new ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                int selectedTab = mainTabSet.getSelectedTabNumber();
                mainTabSet.removeTab(selectedTab);
                mainTabSet.selectTab(selectedTab - 1);
            }
        });

        MenuItem closeAllButCurrent = new MenuItem("关闭非当前");
        closeAllButCurrent.setEnableIfCondition(enableCondition);
        closeAllButCurrent.addClickHandler(new ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                int selected = mainTabSet.getSelectedTabNumber();
                Tab[] tabs = mainTabSet.getTabs();
                int[] tabsToRemove = new int[tabs.length - 2];
                int cnt = 0;
                for (int i = 1; i < tabs.length; i++) {
                    if (i != selected) {
                        tabsToRemove[cnt] = i;
                        cnt++;
                    }
                }
                mainTabSet.removeTabs(tabsToRemove);
            }
        });

        MenuItem closeAll = new MenuItem("关闭所有");
        closeAll.setEnableIfCondition(enableCondition);
        closeAll.addClickHandler(new ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                Tab[] tabs = mainTabSet.getTabs();
                int[] tabsToRemove = new int[tabs.length - 1];

                for (int i = 1; i < tabs.length; i++) {
                    tabsToRemove[i - 1] = i;
                }
                mainTabSet.removeTabs(tabsToRemove);
                mainTabSet.selectTab(0);
            }
        });

        menu.setItems(closeItem, closeAllButCurrent, closeAll);
        return menu;
    }

	public static native void exist() /*-{
		return $wnd.close();
		//return $wnd.open("/login.jsp");
	}-*/;
    protected void showSample(TreeNode node) {
        boolean isExplorerTreeNode = node instanceof ExplorerTreeNode;
        if (isExplorerTreeNode) {
            ExplorerTreeNode explorerTreeNode = (ExplorerTreeNode) node;
            PanelFactory factory = explorerTreeNode.getFactory();
            if (factory != null) {
                String panelID = factory.getCanvasID();
                Tab tab = null;
                if (panelID != null) {
                    String tabID = panelID + "_tab";
                    tab = mainTabSet.getTab(tabID);
                }
                if (tab == null) {
                    Canvas panel = factory.createCanvas();
                    tab = new Tab();
                    tab.setID(factory.getCanvasID() + "_tab");
                    //store history token on tab so that when an already open is selected, one can retrieve the
                    //history token and update the URL
                    tab.setAttribute("historyToken", explorerTreeNode.getNodeID());
                    tab.setContextMenu(contextMenu);

                    String sampleName = explorerTreeNode.getName();

                    String icon = explorerTreeNode.getIcon();
                    if (icon == null) {
                        icon = "silk/plugin.png";
                    }
                    String imgHTML = Canvas.imgHTML(icon, 16, 16);
                    tab.setTitle("<span>" + imgHTML + "&nbsp;" + sampleName + "</span>");
                    tab.setPane(panel);
                    tab.setCanClose(true);
                    mainTabSet.addTab(tab);
                    mainTabSet.selectTab(tab);
                } else {
                    mainTabSet.selectTab(tab);
                }
            }
        }
    }

    public void onHistoryChanged(String historyToken) {
        if (historyToken == null || historyToken.equals("")) {
            mainTabSet.selectTab(0);
        } else {
            ExplorerTreeNode[] showcaseData = sideNav.getShowcaseData();
            if(showcaseData != null){
	            for (ExplorerTreeNode explorerTreeNode : showcaseData) {
	                if (explorerTreeNode.getNodeID().equals(historyToken)) {
	                    showSample(explorerTreeNode);
	                    sideNav.selectRecord(explorerTreeNode);
	                    Tree tree = sideNav.getData();
	                    TreeNode categoryNode = tree.getParent(explorerTreeNode);
	                    while (categoryNode != null && !"/".equals(tree.getName(categoryNode))) {
	                        tree.openFolder(categoryNode);
	                        categoryNode = tree.getParent(categoryNode);
	                    }
	                }
	            }
            }
        }
    }
    protected void showOverview(String message) {
        if (introWindow == null) {
            introWindow = new Window();
            introWindow.setShouldPrint(false);
            introWindow.setTitle("Overview");
            introWindow.setHeaderIcon("pieces/16/cube_green.png", 16, 16);
            introWindow.setKeepInParentRect(true);
            introWindow.setLayoutAlign(Alignment.RIGHT);
            String introContents = "<p class='intro-para'>" + message + "</p>";
            Canvas contents = new Canvas();
            contents.setCanSelectText(true);
            contents.setPadding(10);
            contents.setContents(introContents);
            contents.setDefaultWidth(200);

           introWindow.setAutoSize(true);
           introWindow.setAutoHeight();

            introWindow.setCanDragReposition(false);
            introWindow.setCanDragResize(false);
            introWindow.addItem(contents);
        }

 
//        final Layout layout = ((Layout)getMember(0));
//            if (topIntro) {
//                layout.addMember(introWindow, 0);
//            } else {
//                layout.addMember(introWindow);
//            }
//        } else {
            introWindow.show();
//        }
    }

}
