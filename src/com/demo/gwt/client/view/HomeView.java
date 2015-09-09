package com.demo.gwt.client.view;

import java.util.List;

import com.demo.gwt.client.pub.widget.FusionCharts;
import com.demo.gwt.client.pub.widget.GridPaginationBar;
import com.demo.gwt.client.pub.widget.ListGridDataControl;
import com.demo.gwt.client.service.HomeService;
import com.demo.gwt.client.service.HomeServiceAsync;
import com.demo.gwt.model.Student;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeNode;

public class HomeView extends VLayout {

	private GridPaginationBar pageBar;
	private ListGrid homeGrid;
	private HomeServiceAsync service = GWT.create(HomeService.class);


	private String mapsChart = 
			"<map numberPrefix=\"$\" baseFontSize=\"9\" fillAlpha=\"70\" hoverColor=\"639ACE\" showBevel=\"0\" showShadow=\"0\">"+
			"<colorRange>"+
			"	<color minValue=\"0\" maxValue=\"10000\" displayValue=\"Very Low Sales\" color=\"B80000\" />"+
			"	<color minValue=\"10000\" maxValue=\"20000\" displayValue=\"Moderate Sales\" color=\"FFCC33\" />"+
			"	<color minValue=\"20000\" maxValue=\"50000\" displayValue=\"High Sales\" color=\"008200\" />"+
			"</colorRange>"+
			"<data>"+
			"	<entity id=\"001\" value=\"5150\" />"+
			"	<entity id=\"002\" value=\"10515\" />"+
			"	<entity id=\"003\" value=\"4310\" />"+
			"	<entity id=\"004\" value=\"12000\" />"+
			"	<entity id=\"005\" value=\"8900\" />"+
			"	<entity id=\"006\" value=\"6515\" />"+
			"	<entity id=\"007\" value=\"7000\" />"+
			"	<entity id=\"008\" value=\"12515\" />"+
			"	<entity id=\"009\" value=\"18000\" />"+
			"	<entity id=\"010\" value=\"3412\" />"+
			"	<entity id=\"011\" value=\"7867\" />"+
			"	<entity id=\"012\" value=\"9800\" />"+
			"	<entity id=\"013\" value=\"34000\" />"+
			"	<entity id=\"014\" value=\"38000\" />"+
			"	<entity id=\"015\" value=\"12390\" />"+
			"	<entity id=\"016\" value=\"11000\" />"+
			"	<entity id=\"018\" value=\"7890\" />"+
			"	<entity id=\"019\" value=\"21000\" />"+
			"	<entity id=\"020\" value=\"6878\" />"+
			"	<entity id=\"021\" value=\"5632\" />"+
			"	<entity id=\"022\" value=\"9876\" />"+
			"	<entity id=\"023\" value=\"3412\" />"+
			"	<entity id=\"024\" value=\"16753\" />"+
			"	<entity id=\"025\" value=\"13457\" />"+
			"	<entity id=\"026\" value=\"5464\" />"+
			"	<entity id=\"027\" value=\"12356\" />"+
			"	<entity id=\"028\" value=\"4322\" />"+
			"	<entity id=\"029\" value=\"9877\" />"+
			"	<entity id=\"030\" value=\"16536\" />"+
			"	<entity id=\"031\" value=\"10536\" />"+
			"	<entity id=\"032\" value=\"10978\" />"+
			"	<entity id=\"033\" value=\"8032\" />"+
			"	<entity id=\"034\" value=\"14578\" />"+
			"	<entity id=\"035\" value=\"13452\" />"+
			"	<entity id=\"036\" value=\"10090\" />"+
			"	<entity id=\"037\" value=\"9786\" />"+
			"	<entity id=\"038\" value=\"45678\" />"+
			"	<entity id=\"039\" value=\"2115\" />"+
			"	<entity id=\"040\" value=\"3411\" />"+
			"	<entity id=\"041\" value=\"2411\" />"+
			"	<entity id=\"042\" value=\"2211\" />"+
			"	<entity id=\"043\" value=\"16211\" />"+
			"	<entity id=\"044\" value=\"28511\" />"+
			"	<entity id=\"045\" value=\"12811\" />"+
			"	<entity id=\"046\" value=\"14411\" />"+
			"</data>"+
			"</map>";
	
	private String funnelChart = 
			"<chart caption=\"Sales distribution by Employee\" subCaption=\"Jan 07 - Jul 07\" numberPrefix=\"$\" isSliced=\"1\" streamlinedData=\"0\" isHollow=\"0\"> "+
			"  <set label=\"Buchanan\" value=\"20000\"/>"+
			"  <set label=\"Callahan\" value=\"49000\"/>"+
			"  <set label=\"Davolio\" value=\"63000\"/>"+
			"  <set label=\"Dodsworth\" value=\"41000\"/>"+
			"  <set label=\"Fuller\" value=\"74000\"/>"+
			"  <set label=\"King\" value=\"49000\"/>"+
			"  <set label=\"Leverling\" value=\"77000\"/>"+
			"  <set label=\"Peacock\" value=\"54000\"/>"+
			"  <set label=\"Suyama\" value=\"14000\"/>"+
			"</chart>";
	
	private String funnelChartFV =
			"<chart isSliced='1' slicingDistance='4' decimalPrecision='0'>"+
			"	<set name='Selected' value='41' color='99CC00' alpha='85'/>"+
			"	<set name='Tested' value='84' color='333333' alpha='85'/>"+
			"	<set name='Interviewed' value='126' color='99CC00' alpha='85'/>"+
			"	<set name='Candidates Applied' value='180' color='333333' alpha='85'/>"+
			"</chart>";
	
	
	private String candlestickChart = 
			"<chart numPDivLines=\"5\" caption=\"XYZ - 3 Months\" numberPrefix=\"$\" bearBorderColor=\"E33C3C\" bearFillColor=\"E33C3C\" bullBorderColor=\"1F3165\" PYAxisName=\"Price\" VYAxisName=\"Volume (In Millions)\" volumeHeightPercent=\"20\">"+
			   "<categories>"+
			   "<category label=\"2006\" x=\"1\" showLine=\"1\" />"+ 
			   "</categories>"+
			   "<dataset>"+
			      "<set open=\"24.6\" high=\"25.24\" low=\"24.58\" close=\"25.19\" x=\"1\" volume=\"17856350\" />"+ 
			      "<set open=\"24.36\" high=\"24.58\" low=\"24.18\" close=\"24.41\" x=\"2\" volume=\"3599252\" /> "+
			      "<set open=\"24.63\" high=\"24.66\" low=\"24.11\" close=\"24.95\" x=\"3\" volume=\"74685351\" /> "+
			      "<set open=\"24.53\" high=\"24.84\" low=\"24.01\" close=\"24.95\" x=\"4\" volume=\"49236987\" /> "+
			      "<set open=\"24.84\" high=\"24.94\" low=\"24.56\" close=\"24.93\" x=\"5\" volume=\"18247006\" /> "+
			      "<set open=\"24.96\" high=\"25.03\" low=\"24.58\" close=\"24.89\" x=\"6\" volume=\"67419690\" /> "+
			      "<set open=\"25.25\" high=\"25.46\" low=\"25.11\" close=\"25.13\" x=\"7\" volume=\"95517555\" /> "+
			      "<set open=\"25.27\" high=\"25.37\" low=\"25.0999\" close=\"25.18\" x=\"8\" volume=\"83656552\" /> "+
			      "<set open=\"25.33\" high=\"25.43\" low=\"25.06\" close=\"25.16\" x=\"9\" volume=\"42177624\" /> "+
			      "<set open=\"25.38\" high=\"25.51\" low=\"25.23\" close=\"25.38\" x=\"10\" volume=\"40668662\" /> "+
			      "<set open=\"25.2\" high=\"25.78\" low=\"25.07\" close=\"25.09\" x=\"11\" volume=\"78602232\" />"+
			      "<set open=\"25.66\" high=\"25.8\" low=\"25.35\" close=\"25.37\" x=\"12\" volume=\"10338104\" /> "+
			      "<set open=\"25.77\" high=\"25.97\" low=\"25.54\" close=\"25.72\" x=\"13\" volume=\"38067037\" /> "+
			      "<set open=\"26.31\" high=\"26.35\" low=\"25.81\" close=\"25.83\" x=\"14\" volume=\"52104215\" /> "+
			      "<set open=\"26.23\" high=\"26.6\" low=\"26.2\" close=\"26.35\" x=\"15\" volume=\"46274157\" />"+
			      "</dataset>"+
			   "<trendset name=\"Simple Moving Average\" color=\"0099FF\" thickness=\"0\" alpha=\"100\" includeInLegend=\"1\">"+
			      "<set x=\"1\" value=\"24.6\" /> "+
			      "<set x=\"2\" value=\"24.69\" /> "+
			      "<set x=\"3\" value=\"24.89\" /> "+
			      "<set x=\"4\" value=\"24.92\" /> "+
			      "<set x=\"5\" value=\"25.2\" /> "+
			      "<set x=\"6\" value=\"25.1\" />"+
			      "<set x=\"7\" value=\"25.17\" />"+
			      "<set x=\"8\" value=\"25.2\" />"+
			      "<set x=\"9\" value=\"25.2\" />"+
			      "<set x=\"10\" value=\"25.31\" />"+
			      "<set x=\"11\" value=\"25.28\" /> "+
			      "<set x=\"12\" value=\"25.52\" /> "+
			      "<set x=\"13\" value=\"25.7\" /> "+
			      "<set x=\"14\" value=\"25.9\" /> "+
			      "<set x=\"15\" value=\"26\" />"+
			   "</trendset>"+
			   "<trendLines>"+
			      "<line startValue=\"24.2\" color=\"0372AB\" displayvalue=\"S1\" thickness=\"0\" dashed=\"1\" dashLen=\"2\" dashGap=\"2\" />"+
			      "<line startValue=\"23.35\" color=\"0372AB\" displayvalue=\"S2\" thickness=\"0\" dashed=\"1\" dashLen=\"2\" dashGap=\"2\" />"+
			      "<line startValue=\"28.2\" color=\"0372AB\" displayvalue=\"R2\" thickness=\"0\" dashed=\"1\" dashLen=\"2\" dashGap=\"2\" />"+
			      "<line startValue=\"27.65\" color=\"0372AB\" displayvalue=\"R1\" thickness=\"0\" dashed=\"1\" dashLen=\"2\" dashGap=\"2\" />"+
			   "</trendLines>"+
			   "<vtrendLines>"+
			      "<line startValue=\"10\" endValue=\"13\" color=\"FF5904\" displayvalue=\"Results Impact\" isTrendZone=\"1\" alpha=\"10\" />"+
			   "</vtrendLines>"+
			"</chart>";
	
	private String candlestickChartFV = 
			"<graph  caption='XYZ - 3 Months' "+ 
					"	                numberPrefix='$'"+ 
					"	                bearBorderColor='E33C3C'"+ 
					"	                bearFillColor='E33C3C' "+
					"	                bullBorderColor='1F3165' "+
					"	                showAlternateHGridColor='1'"+ 
					"	                alternateHGridColor='D7EBED' "+
					"	                divlinecolor='CCEBFF' "+
					"	                decimalPrecision='2' "+
					"	                divLineDecimalPrecision='0'"+ 
					"	                limitsDecimalPrecision='0' "+
					"	                yAxisMinValue='23' "+
					"	                numdivlines='4'> "+
					"	<categories> "+
					"	        <category name='2006' xIndex='1' showLine='1'/>"+
					"	        <category name='Feb' xIndex='30' showLine='1'/>"+
					"	</categories>"+
					"	<data>"+
					"	        <set open='24.6' high='25.24' low='24.58' close='25.19' xIndex='1'/>"+
					"	        <set open='24.36' high='24.58' low='24.18' close='24.41' xIndex='2'/>"+
					"	        <set open='24.63' high='24.66' low='24.11' close='24.95' xIndex='3'/>"+
					"	        <set open='24.53' high='24.84' low='24.01' close='24.95' xIndex='4'/>"+
					"	        <set open='24.84' high='24.94' low='24.56' close='24.93' xIndex='5'/>"+
					"	        <set open='24.96' high='25.03' low='24.58' close='24.89' xIndex='6'/>"+
					"	        <set open='25.25' high='25.46' low='25.11' close='25.13' xIndex='7'/>"+
					"	        <set open='25.27' high='25.37' low='25.0999' close='25.18' xIndex='8'/>"+
					"	        <set open='25.33' high='25.43' low='25.06' close='25.16' xIndex='9'/>"+
					"	        <set open='25.38' high='25.51' low='25.23' close='25.38' xIndex='10'/>"+
					"	        <set open='25.2' high='25.78' low='25.07' close='25.09' xIndex='11'/>"+
					"	        <set open='25.66' high='25.8' low='25.35' close='25.37' xIndex='12'/>"+
					"	        <set open='25.77' high='25.97' low='25.54' close='25.72' xIndex='13'/>"+
					"	        <set open='26.31' high='26.35' low='25.81' close='25.83' xIndex='14'/>"+
					"	        <set open='26.23' high='26.6' low='26.2' close='26.35' xIndex='15'/>"+
					"	        <set open='26.37' high='26.42' low='26.21' close='26.37' xIndex='16'/>"+
					"	        <set open='26.35' high='26.55' low='26.22' close='26.37' xIndex='17'/>"+
					"	        <set open='26.63' high='26.69' low='26.35' close='26.39' xIndex='18'/>"+
					"	        <set open='26.65' high='26.72' low='26.5' close='26.7' xIndex='19'/>"+
					"	        <set open='26.48' high='26.62' low='26.35' close='26.53' xIndex='20'/>"+
					"	        <set open='26.63' high='26.65' low='26.41' close='26.5' xIndex='21'/>"+
					"	        <set open='26.89' high='26.99' low='26.61' close='26.7' xIndex='22'/>"+
					"	        <set open='26.6' high='26.95' low='26.55' close='26.88' xIndex='23'/>"+
					"	        <set open='26.75' high='26.76' low='26.4799' close='26.61' xIndex='24'/>"+
					"	        <set open='26.65' high='26.795' low='26.5' close='26.57' xIndex='25'/>"+
					"	        <set open='26.9' high='26.98' low='26.43' close='26.46' xIndex='26'/>"+
					"	        <set open='26.92' high='27.11' low='26.74' close='26.77' xIndex='27'/>"+
					"	        <set open='26.7' high='27.1' low='26.59' close='26.99' xIndex='28'/>"+
					"	        <set open='26.98' high='27.06' low='26.5' close='26.59' xIndex='29'/>"+
					"	        <set open='27.09' high='27.15' low='26.93' close='26.95' xIndex='30'/>"+
					"	</data>"+
					"	        <trendLines>"+
					"	                <line startValue='24.2' color='0372AB' displayvalue='S1' thickness='1' />"+
					"	        <line startValue='23.35' color='0372AB' displayvalue='S2' thickness='1' />"+
					"	                <line startValue='28.2' color='0372AB' displayvalue='R2' thickness='1' />"+
					"	        <line startValue='27.65' endValue='27.65' color='0372AB' displayvalue='R1' thickness='1' />"+
					"	        </trendLines>"+
					"	</graph>";
	
	private String combinationChartDualY = 
			"<chart caption=\"Visits from search engines\" showValues=\"0\" showColumnShadow=\"0\" sNumberSuffix=\"%\">"+
			"<categories>"+
				"<category label=\"Jan\" />"+
				"<category label=\"Feb\" />"+
				"<category label=\"Mar\" />"+
				"<category label=\"Apr\" />"+
				"<category label=\"May\" />"+
				"<category label=\"Jun\" />"+
				"<category label=\"Jul\" />"+
				"<category label=\"Aug\" />"+
				"<category label=\"Sep\" />"+
        		"<category label=\"Oct\" />"+
        		"<category label=\"Nov\" />"+
        		"<category label=\"Dec\" />"+
       		"</categories>"+
       		"<dataset seriesName=\"Google\">"+
       			"<set value=\"3040\" />"+
       			"<set value=\"2794\" />"+
       			"<set value=\"3026\" />"+
       			"<set value=\"3341\" />"+
       			"<set value=\"2800\" />"+
       			"<set value=\"2507\" />"+
       			"<set value=\"3701\" />"+
       			"<set value=\"2671\" />"+
       			"<set value=\"2980\" />"+
       			"<set value=\"2041\" />"+
       			"<set value=\"1813\" />"+
       			"<set value=\"1691\" />"+
        		"</dataset>"+
        	"<dataset seriesName=\"Yahoo\">"+
        		"<set value=\"1200\" />"+
        		"<set value=\"1124\" />"+
        		"<set value=\"1006\" />"+
        		"<set value=\"921\" />"+
        		"<set value=\"1500\" />"+
        		"<set value=\"1007\" />"+
        		"<set value=\"921\" />"+
        		"<set value=\"971\" />"+
        		"<set value=\"1080\" />"+
        		"<set value=\"1041\" />"+
        		"<set value=\"1113\" />"+
        		"<set value=\"1091\" />"+
        		"</dataset>"+
        	"<dataset seriesName=\"MSN\">"+
        		"<set value=\"600\" />"+
        		"<set value=\"724\" />"+
        		"<set value=\"806\" />"+
        		"<set value=\"621\" />"+
        		"<set value=\"700\" />"+
        		"<set value=\"907\" />"+
        		"<set value=\"821\" />"+
        		"<set value=\"671\" />"+
        		"<set value=\"880\" />"+
        		"<set value=\"641\" />"+
        		"<set value=\"913\" />"+
        		"<set value=\"691\" />"+
       		"</dataset>"+
       		"<dataset seriesName=\"Percent of total hits\" parentYAxis=\"S\" lineThickness=\"4\" color=\"993300\" renderAs = \"Line\">"+
	       		"<set value=\"96.5\" />"+
	       		"<set value=\"77.1\" />"+
	            "<set value=\"73.2\" />"+
	            "<set value=\"61.1\" />"+
	            "<set value=\"70.0\" />"+
	            "<set value=\"60.7\" />"+
	            "<set value=\"62.1\" />"+
	            "<set value=\"75.1\" />"+
	            "<set value=\"80.0\" />"+
	            "<set value=\"54.1\" />"+
	            "<set value=\"51.3\" />"+
	            "<set value=\"59.1\" />"+
	        "</dataset>"+
	        "</chart>";

	private String combinationChartDualYFV = 
			"<graph caption='Sales' PYAxisName='Revenue' SYAxisName='Quantity' numberPrefix='$' showvalues='0' numDivLines='4' "+ 
					"formatNumberScale='0' decimalPrecision='0' anchorSides='10' anchorRadius='3' anchorBorderColor='009900'>"+
					"	<categories>"+
					"		<category name='March'/>"+
					"		<category name='April'/>"+
					"		<category name='May'/>"+
					"		<category name='June'/>"+
					"		<category name='July'/>"+
					"	</categories>"+
					"	<dataset seriesName='Product A' color='AFD8F8' showValues='0'>"+
					"		<set value='25601.34'/>"+
					"		<set value='20148.82'/>"+
					"		<set value='17372.76'/>"+
					"		<set value='35407.15'/>"+
					"		<set value='38105.68'/>"+
					"	</dataset>"+
					"	<dataset seriesName='Product B' color='F6BD0F' showValues='0'>"+
					"		<set value='57401.85'/>"+
					"		<set value='41941.19'/>"+
					"		<set value='45263.37'/>"+
					"		<set value='117320.16'/>"+
					"		<set value='114845.27'/>"+
					"	</dataset>"+
					"	<dataset seriesName='Total Quantity' color='8BBA00' showValues='0' parentYAxis='S'>"+
					"		<set value='45000'/>"+
					"		<set value='44835'/>"+
					"		<set value='42835'/>"+
					"		<set value='77557'/>"+
					"		<set value='92633'/>"+
					"	</dataset>"+
					"</graph>'";
	
	private int chartId = 0;
	private String activeChart = "-1";
	
	private String getChartId() {
		chartId += 1;
		return "chartId_"+chartId;
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
//		homeGrid.setHeight(300);
        
        ListGridField pkField = new ListGridField("id");
        pkField.setHidden(true);

        ListGridField name = new ListGridField("name", "姓名");
        ListGridField age = new ListGridField("age", "年龄");
        
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
		addMember(getViewPanel2());
		pageBar.active();
	}

	public VLayout getViewPanel2() {
		HLayout hLayout = new HLayout();

		final Label labelTitle = new Label();
		labelTitle.setHeight(10);
		labelTitle.setWidth100();

		final DynamicForm form = new DynamicForm();
		form.setIsGroup(true);
		form.setNumCols(4);
		form.setGroupTitle("Chart's Dimensions");

		final FusionCharts fusionChartLayout = new FusionCharts();
		fusionChartLayout.setID("chart");
		fusionChartLayout.setCanDragResize(true);
		fusionChartLayout.setShowEdges(true);
		fusionChartLayout.setCanDrag(true);
		fusionChartLayout.setCanDrop(true);
		fusionChartLayout.setCanDragReposition(true);
		fusionChartLayout.setOverflow(Overflow.HIDDEN);
		fusionChartLayout.addResizedHandler(new ResizedHandler() {
			@Override
			public void onResized(ResizedEvent event) {
				fusionChartLayout.resizeActiveChart(activeChart, fusionChartLayout.getInnerWidth(), 
						fusionChartLayout.getInnerHeight());
				form.setValue("width", fusionChartLayout.getInnerWidth());
				form.setValue("height", fusionChartLayout.getInnerHeight());
			}
		});
		final HLayout chartLayout1 = new HLayout();
        chartLayout1.setID("chartLayout1");
        chartLayout1.setWidth(400);
        chartLayout1.setHeight(200);
        chartLayout1.setShowEdges(true);
        chartLayout1.setCanAcceptDrop(true);

        final HLayout chartLayout2 = new HLayout();
        chartLayout2.setID("chartLayout2");
        chartLayout2.setWidth(400);
        chartLayout2.setHeight(200);
        chartLayout2.setShowEdges(true);
        chartLayout2.setCanAcceptDrop(true);
        
		IntegerItem widthItem = new IntegerItem();
		widthItem.setTitle("Width");
		widthItem.setName("width");
		widthItem.setDefaultValue(400);
		widthItem.setWidth(100);
		widthItem.setLength(3);		
		IntegerItem heightItem = new IntegerItem();
		heightItem.setTitle("Height");
		heightItem.setName("height");
		heightItem.setDefaultValue(200);
		heightItem.setWidth(100);
		heightItem.setLength(3);
		ButtonItem buttonItem = new ButtonItem();
		buttonItem.setWidth(100);
		buttonItem.setTitle("Resize Chart");
		buttonItem.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			@Override
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				fusionChartLayout.resizeContainerAndActiveChart(activeChart, Integer.parseInt(form.getValueAsString("width")), 
						Integer.parseInt(form.getValueAsString("height")));
			}
		});
		ButtonItem buttonMoveItem = new ButtonItem();
		buttonMoveItem.setWidth(100);
		buttonMoveItem.setTitle("Move Chart");
		buttonMoveItem.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			@Override
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				if (chartLayout1.contains(fusionChartLayout)) {
					chartLayout2.addMember(fusionChartLayout);
				} else {
					chartLayout1.addMember(fusionChartLayout);
				}
			}
		});
		form.setFields(widthItem, heightItem, buttonItem, buttonMoveItem);
		
		IButton buttonFusionCharts = new IButton();
		buttonFusionCharts.setTitle("Fusion Chart");
		buttonFusionCharts.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				labelTitle.setContents("Fusion Chart - Chart:MSColumn3DLineDY");
				fusionChartLayout.removeChart(activeChart);
				activeChart = getChartId();
				fusionChartLayout.getFusionChart(combinationChartDualY, "MSColumn3DLineDY", activeChart);// comment out this line if you want to use the free version
				//fusionChartLayout.getFusionChart(combinationChartDualYFV, "resources/chart/FusionChartsFree/FCF_MSColumn3DLineDY.swf", activeChart);// comment out this line if you want to use the paid version
			}
        });
		IButton buttonPowerCharts = new IButton();
		buttonPowerCharts.setTitle("Power Chart");
		buttonPowerCharts.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				labelTitle.setContents("Power Chart - Chart:CandleStick");
				fusionChartLayout.removeChart(activeChart);
				activeChart = getChartId();
				fusionChartLayout.getFusionChart(candlestickChart, "CandleStick", activeChart);// comment out this line if you want to use the free version
				//fusionChartLayout.getFusionChart(candlestickChartFV, "resources/chart/FusionChartsFree/FCF_Candlestick.swf", activeChart);// comment out this line if you want to use the paid version
			}
        });
		IButton buttonFusionWidgets = new IButton();
		buttonFusionWidgets.setTitle("Fusion Widgets");
		buttonFusionWidgets.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				labelTitle.setContents("Fusion Widgets - Chart:Funnel");
				fusionChartLayout.removeChart(activeChart);
				activeChart = getChartId();
				fusionChartLayout.getFusionChart(funnelChart, "Funnel", activeChart);// comment out this line if you want to use the free version
				//fusionChartLayout.getFusionChart(funnelChartFV, "resources/chart/FusionChartsFree/FCF_Funnel.swf", activeChart);// comment out this line if you want to use the paid version
			}
        });
		IButton buttonFusionMaps = new IButton();
		buttonFusionMaps.setTitle("Fusion Maps");
		buttonFusionMaps.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				labelTitle.setContents("Fusion Maps - Chart:FCMap_europe");
				fusionChartLayout.removeChart(activeChart);
				activeChart = getChartId();
				fusionChartLayout.getFusionMap(mapsChart, "FCMap_europe", activeChart);
			}
        });
        hLayout.addMember(buttonFusionCharts);
        hLayout.addMember(buttonPowerCharts);
        hLayout.addMember(buttonFusionWidgets);
        hLayout.addMember(buttonFusionMaps);
        
        chartLayout1.addMember(fusionChartLayout);
        
        HLayout parentHLayout = new HLayout();
        parentHLayout.addMember(chartLayout1);
        parentHLayout.addMember(chartLayout2);
        
        VLayout vLayout = new VLayout();
        vLayout.setWidth(400);
        vLayout.addMember(hLayout);
        vLayout.addMember(form);
        vLayout.addMember(labelTitle);
        vLayout.addMember(parentHLayout);
        
//        vLayout.draw();
		
		return vLayout;
	}
	
	private void populateRecord(ListGridRecord record, Student object) {
		record.setAttribute("id", object.getId());
		record.setAttribute("name", object.getName());
		record.setAttribute("age", object.getAge());
	}
}
