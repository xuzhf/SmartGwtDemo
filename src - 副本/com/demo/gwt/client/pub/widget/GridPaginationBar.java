package com.demo.gwt.client.pub.widget;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStrip;

/**
 * 
 * 分页工具条
 * 
 * 使用方法：
 * 1、实例化一个分页工具栏，传入每页记录条数和绑定的grid
 * 2、实现一个ListGridDataControl（实例化方法参见类中注释，注意和分页工具的相互调用），并把它设值给分页工具栏的dataControl属性，
 *    注意：分页工具条和ListGridDataControl需要紧密配合相互调用。
 * 3、调用active()方法，激活分页条并加载grid数据
 * 
 * 提示：
 * 1、本分页工具不处理grid的行号，行号理解为grid的列定义范畴
 * 2、可以多次调用active()方法，调用的结果相当于把分页栏定位到首页，并重新获取总页数
 * 3、如果查询业务数据的参数改变时，可以重新实现并设置ListGridDataControl，然后调用active()
 * 4、可以调用setMaskMessage()设置遮罩的提示信息,如果不设置，采用默认值
 * 
 * 说明：
 * 之所以需要ListGridDataControl回调分页工具条的方法，导致增加了相互依赖，主要是考虑到获取数据和获取记录总数的方法通常是异步的，
 * 为保持操作的同步性，只能通过互相调用的方式保持工具条和grid的正常工作。
 * 
 * @author xiaozhao
 * @version 1.0
 */
public class GridPaginationBar extends ToolStrip{

	/**
	 * 模态遮罩
	 */
	ModalWindow mask;
	
	private String maskMessage = " 数据加载中...... ";

	public void setMaskMessage(String maskMessage) {
		this.maskMessage = maskMessage;
	}
	/**
	 * 每页条数
	 */
	private int pageSize = -1;
	
	/**
	 * 当前页
	 */
	private int pageNum = -1;
	
	/**
	 * 总页数
	 */
	private int totalPage = -1;
	
	/**
	 * 数据记录条数
	 */
	private int totalRowNum = -1;
	
	/**
	 * 绑定的grid
	 */
	private final ListGrid grid;
	
	//首页
	TopImgButton first = new TopImgButton(16,16,"page/firstPage.gif","page/firstPageDisabled.gif");
	
	//上一页
	TopImgButton backward = new TopImgButton(16,16,"page/prevPage.gif","page/prevPageDisabled.gif");
	
	//下一页
	TopImgButton forward = new TopImgButton(16,16,"page/nextPage.gif","page/nextPageDisabled.gif");
	
	//末页
	TopImgButton last = new TopImgButton(16,16,"page/lastPage.gif","page/lastPageDisabled.gif");	
	
	//输入框form
	DynamicForm pageForm;
	
	//页码输入框
	protected TextItem pageText;
	
	//转到按钮
	IButton go = new IButton("转到");
	
	//总页数
	protected Label totalLabel;
	
	//grid数据控制器
	private ListGridDataControl dataControl;
	
	public void setDataControl(ListGridDataControl dataControl){
		this.dataControl = dataControl;
	}
	
	public GridPaginationBar(ListGrid listGrid, int pageSize) {
		this(listGrid, pageSize,null);
	}

	/**
	 * 
	 * 构造方法中，只初始化工具条元素和事件，不初始化数据，
	 * 构造完成后工具条处于禁用状态，需要首先传入grid数据控制器，
	 * 然后调用active()方法来激活工具条，并开始加载数据。
	 * 
	 * @param listGrid 绑定的grid
	 * @param pageSize 每页记录条数
	 * @param maskCanvas 加载数据过程中，需要用遮罩遮盖的控件，如果传入null,则默认遮盖的对象为绑定的grid
	 */
	public GridPaginationBar(ListGrid listGrid, int pageSize, Canvas maskCanvas) {
		this.grid = listGrid;
		mask = new ModalWindow(maskCanvas == null ? grid : maskCanvas);
		mask.setLoadingIcon("page/loadingSmall.gif");
		this.pageSize = pageSize;
		first.setButtonTip("首页");
		first.setButtonClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				goToPage(1);
			}
		});
		backward.setButtonTip("上一页");
		backward.setButtonClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				goToPage(pageNum - 1);
			}
		});
		pageText = new TextItem();
		pageText.setWidth(40);
		pageText.setHeight(20);
		pageText.setShowTitle(false);
		pageText.setTextAlign(Alignment.RIGHT);
		go.setWidth(34);
		go.setHeight(20);
		go.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				String page = pageText.getValueAsString();
				if(page == null || page.trim().equals("")){
					SC.say("请输入页码");
					return ;
				}else {
					try{
						int pageint = Integer.valueOf(page);
						if(pageint<1){
							SC.say("输入的页码不能小于1");
							return;
						}else if(pageint>totalPage){
							SC.say("输入的页码不能大于总页数 "+totalPage);
							return;
						}else {
							goToPage(pageint);
						}
					}catch(NumberFormatException e){
						SC.say("请输入整数");
						return;
					}
				}
			}});
		
		forward.setButtonTip("下一页");
		forward.setButtonClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				goToPage(pageNum + 1);
			}
		});

		last.setButtonTip("末页");
		last.setButtonClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				goToPage(totalPage);
			}
		});

		totalLabel = new Label();
		totalLabel.setWrap(false);
		totalLabel.setWidth(50);

		setHeight(20);
		setOverflow(Overflow.VISIBLE);
		//setStyleName("normal");
		//this.setMargin(5);

		pageForm = new DynamicForm();
		pageForm.setNumCols(1);
		pageForm.setWidth(45);
		pageForm.setItems(pageText);
		Label blank1 = new Label();
		blank1.setContents(" ");
		blank1.setWidth(5);
		blank1.setHeight(20);

		Label blank2 = new Label();
		blank2.setContents(" ");
		blank2.setWidth(5);
		blank2.setHeight(20);

		Label blank3 = new Label();
		blank3.setContents(" ");
		blank3.setWidth(15);
		blank3.setHeight(20);

		Label blank4 = new Label();
		blank4.setContents(" ");
		blank4.setWidth(5);
		blank4.setHeight(20);
		
		Label blank5 = new Label();
		blank5.setContents(" ");
		blank5.setWidth(5);
		blank5.setHeight(20);
		
		addMember(first);
		addMember(blank1);
		addMember(backward);
		addMember(blank2);
		addMember(pageForm);
		addMember(go);
		addMember(blank5);
		addMember(forward);
		addMember(blank4);
		addMember(last);
		addMember(blank3);
		addMember(totalLabel);
		setAlign(Alignment.CENTER);
		setWidth100();
		resetPaginationState();
	}

	/**
	 * 转到指定页，获取分页数据并装载，
	 * 获取数据过程中，加模态遮罩
	 * 
	 * @param pageNum
	 */
	public void goToPage(int pageNum) { 
		go.disable();
		if (pageNum > totalPage)
			pageNum = totalPage;
		if (pageNum < 1)
			pageNum = 1;
		if (pageNum == this.pageNum) {
			go.enable();
			return;
		}
		mask.show(maskMessage, true);
		//fetch data and reload grid
		this.pageNum = pageNum;
		int startNum = (pageNum - 1) * pageSize;
		dataControl.fetchData(startNum, pageSize);		
	}

	/**
	 * 根据记录总数计算总页数
	 */
	private void countTotalPages()
	{
		if(totalRowNum < 1){
			totalPage = -1;
			return;
		}
		int pages = (int) Math.ceil(((float) totalRowNum) / ((float) pageSize));
		// never return zero pages
		if (pages == 0)
			pages = 1;
		this.totalPage=pages;
		pageNum = 1;
	}
	
	/**
	 * 首次查询数据，获取数据总数等信息，并更新分页条状态
	 * 获取数据过程中，加模态遮罩
	 */
	public void active(){
		if(dataControl == null){
			SC.say("错误","分页栏未配置ListGridDataControl数据控制器，请检查代码");
			return;
		}		
		//首先禁用工具条
		if(pageNum != -1){
			resetPaginationState();
		}
		mask.show(maskMessage, true);
		dataControl.getTotal();		
	}
	
	/**
	 * ListGridDataControl获取总记录数后的回调方法
	 * @param all
	 */
	public void afterGetTotal(int all){
		this.totalRowNum = all;
		countTotalPages();
		if(totalPage==-1){
			//如果没有查询到数据，则清空原来的数据
			afterFetchData(new ListGridRecord[0]);
		} else {
			dataControl.fetchData(0, pageSize);
		}		
	}
	
	/**
	 * ListGridDataControl获取数据后的回调方法
	 * @param datas
	 */
	public void afterFetchData(ListGridRecord[] datas){
		if(datas != null){
			dataControl.loadData(datas);
		}
		updatePageinationState();
		mask.hide();
	}
	
	/**
	 * 清除分页工具条状态，全部置灰，不负责清空grid当前数据
	 */
	private void resetPaginationState(){
		pageNum = -1;
		totalPage = -1;
		totalRowNum = -1;
		first.disable();
		backward.disable();
		last.disable();
		forward.disable();
		pageText.disable();
		go.disable();
		totalLabel.setContents("");
	}

	/**
	 * 依据当前页和总页数控制工具条状态
	 * 规则：
	 * 		当前页为-1，则说明工具条尚未激活或者没有查询到数据，不需要处理；
	 *		当前页等于1，则首页按钮和上一页按钮置灰，否则，激活；
	 * 		当前页等于总页数，则末页按钮和上一页按钮置灰，否则，激活；
	 */
	private void updatePageinationState() {
		if(pageNum==-1||totalPage==-1){
			return;
		}
		totalLabel.setContents("第" + pageNum + "页/共" + totalPage + "页, 共"+totalRowNum+"条");
		pageText.enable();
		go.enable();
		pageText.setValue(pageNum);
		if (pageNum == 1){
			first.disable();
			backward.disable();
		} else {
			first.enable();
			backward.enable();
		}
		if (pageNum == totalPage){
			last.disable();
			forward.disable();
		} else {
			last.enable();
			forward.enable();
		}
	}
}
