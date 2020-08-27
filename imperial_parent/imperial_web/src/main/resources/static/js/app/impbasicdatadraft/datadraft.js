var prefix = ctx + "shipperModule/impBasicDataDraft";
var impbasicDraftDivWid = $("#impbasicDraftDiv").width()*0.97+"px";
$(function() {
    $("#datadraftTable").bootstrapTable({
    	url:"/shipperModule/impBasicData/getMainOrderNoList",
    	method: 'post',
        contentType: "application/x-www-form-urlencoded",   // 编码类型
        sidePagination : 'server',
        detailView:true,
        striped: false,  //隔行变色
        uniqueId: "id",
        pagination : true,//是否分页
        queryParamsType:'limit',
        pageSize: 10,
        pageNumber : 1, //初始化加载第一页
        pageList: [10, 20],
        isAsc:true,
        queryParams: queryParamsPaging,
        columns: [
        {field: 'id',title: 'null',visible: false},
        {field: 'mainOrderNo',title: '主单号'}],
        //注册加载子表的事件。注意下这里的三个参数！
        onExpandRow: function (index, row, $detail) {
        	InitSubTable(index, row, $detail);
        }
    })
    $('#searchModule').append($("#searchTpl").html());  //添加搜索模块
    switchRender(); //渲染开关
    identifyRowColor("datadraftTable");//标记父子表中被选中的父表tr
});

/**
 * 请求参数+分页参数
 * @param params
 * @returns
 */
function queryParamsPaging(params) {
	var dataMap = {};
	dataMap.orderIsdraftFlag = 1;
	var pageParams = initPageParams(params);////分页参数的初始化
	return {
		pageParams,
        impParams:queryParams(dataMap)
	};
}

/**
 * 初始化订单详情表格
 * @param mainNoIndex
 * @param fatRow
 * @param $fatDetail
 * @returns
 */
function InitSubTable (mainNoIndex, row, $thisDetail) {
	var mainOrderNo = row.mainOrderNo;
	var tableidIndex = mainNoIndex;   //加空字符连接，不加会出问题，比如0+0=0
	var dataMap = {};
	var switchState = $('.smsenable').bootstrapSwitch('state'); 
	if(switchState){   //如果开关状态为打开状态时，获取检索条件值
		dataMap.tackingNumber1 = $("#tackingNumber1").val();
	}
	dataMap.mainOrderNo = mainOrderNo;
	dataMap.bagNumber = row.bagNumber;
	dataMap.orderIsdraftFlag = 1;
	$thisDetail.append('<div id="smsenable'+tableidIndex+'" style="width:'+impbasicDraftDivWid+'">'+$("#operatBtTpl").html()+'</div>');
	var cur_table = $thisDetail.append('<div id="detailDiv'+tableidIndex+'" style="width:'+impbasicDraftDivWid+'"><table id="impbasicDataDraftSunTable'+tableidIndex+'"></table></div>').find('table');
	var tableid = 'impbasicDataDraftSunTable';
	$(cur_table).bootstrapTable({
    	id: "impbasicDataDraftSunTable"+tableidIndex,
        url: "/shipperModule/impBasicData/list",
        method: 'post',
        contentType: "application/x-www-form-urlencoded",   // 编码类型
        sidePagination : 'server',
        queryParams : function(params){
        	return{
        		pageSize : params.limit,//分页的话，pageSize和pageNum两个参数必不可少！
                pageNum: params.offset / params.limit + 1,
                offset: params.offset,
                isAsc:true,
                impParams:queryParams(dataMap)
        	}},
        clickToSelect: true,
        //rowStyle:rowStyle, //颜色设置
        striped: false,  //隔行变色
        uniqueId: "id",
        pagination : true,//是否分页
        queryParamsType:'limit',
        pageSize:10,
        pageList: [5,10, 20],
        fixedColumns:true,
        fixedNumber:3,
        columns: [
        {field: 'state',checkbox: true},
        {field: 'id',title: 'null',visible: false},
        {field: 'tackingNumber1', title: BagTrackNumber1},
        {field: 'tackingNumber2', title: BagTrackNumber1},
            {field: 'bagNumber',title: packetnumber},
            {field: 'boxNumber',title: Outerboxnumber},
            {field: 'palletNumber',title: Palletnumber},
            {field: 'containerNumber',title: Containernumber},
            {field: 'reference1',title: Referencecode1},
            {field: 'referrnce2',title: Referencecode2},
            {field: 'shipperName',title: ShipperName},
            {field: 'shipperReference',title: Shipperidentificationcode},
            {field: 'consigneeName',title: ConsigneeName},
            {field: 'lineAddress1',title: Shippingaddress1},
            {field: 'lineAddress2',title: Shippingaddress2},
            {field: 'lineAddress3',title: Shippingaddress3},
            {field: 'town',title: City},
            {field: 'state',title:Statesandcounties},
            {field: 'postCode',title: zipcode},
            {field: 'countryCode',title: Countrycode},
            {field: 'email',title: Email},
            {field: 'phone',title: Tel},
            {field: 'pieces',title: Numberofpackages},
            {field: 'weight',title: Weight},
            {field: 'weightUom',title: Unitofweight},
            {field: 'value',title: Val},
            {field: 'shippingRate',title: freight},
            {field: 'currency',title: Currency},
            {field: 'incoterms',title: 'DDU/DDP'},
            {field: 'importPurpose',title: Importpurpose},
            {field: 'eoriNumber',title: Unknown},
            {field: 'mossNumber',title: Unknown},
            {field: 'description',title: Descriptionofgoods},
            {field: 'hsCode',title: customscode},
            {field: 'itemQuantity',title: Thequantityofgoods},
            {field: 'itemValue',title: Thepriceofgoods},
            {field: 'skuNumber',title: Unknown},
            {field: 'returnInstruction',title: returnway},
            {field: 'salesLink',title: Saleslink},
            {field: 'lastMileProvider',title: Terminaldispatcher},
            {field: 'lastMileAccountName',title: Terminalaccountname},
        {field: 'orderIsdraftFlag',visible: false},
        {field: 'orderEndFlag',visible: false},
        {field: 'submitFlag',visible: false}],
        onLoadSuccess: function () {
            registCheckbox(tableid,tableidIndex,$('#'+tableid+tableidIndex))
        },
    });
	$('#impbasicDataDraftSunTable'+tableidIndex).on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table load-success.bs.table", function () {
		var rows = $('#impbasicDataDraftSunTable'+tableidIndex).bootstrapTable('getSelections');
		// 非多个禁用
		$('#detailDiv'+tableidIndex).prev().find(".multiple").toggleClass('disabled', !rows.length);
		// 非单个禁用
		$('#detailDiv'+tableidIndex).prev().find(".single").toggleClass('disabled', rows.length!=1);
	});
};

/**
 * 检索方法
 * */
function searchFun(){
	var dataMap = {};
	var mainOrderNo = $("#mainOrderNo").val();
	var tackingNumber1 = $("#tackingNumber1").val();
	var bagNumber = $("#bagNumber").val();
	dataMap.mainOrderNo = mainOrderNo;
	dataMap.tackingNumber1 = tackingNumber1;
	dataMap.bagNumber = bagNumber;
	dataMap.orderIsdraftFlag = 1;
	var params = {impParams:queryParams(dataMap)};
	$.operate.impAjax("/shipperModule/impBasicData/getMainOrderNoList",params,function(json){
		$("#datadraftTable").bootstrapTable('load',json);//主要是要这种写法
	},function(){
		layer.msg("查询失败！", {icon: 2,time: 1000});
	});
}

/**
 * 修改页面的展示
 * */
function edit(){
	var operationDivId = $(event.target).parent().parent().next()[0].id; //获取点击修改按钮所在的div的id
	var tableId = "impbasicDataDraftSunTable"+operationDivId.substr(9); //获取点击按钮所属的tableid
	var rows = $("#impbasicDataDraftSunTable"+operationDivId.substr(9)).bootstrapTable('getSelections');
	var height = $(window).height()-50+"px";
	var index = layer.open({
		type: 2,
		fix: false,
		maxmin: true,
		shade: 0.3,
		title: '修改清关草稿数据',
		area: ["800px", height], //宽高
		content: "/shipperModule/impBasicDataDraft/editdraft/"+rows[0].id,
		btn: ['转为标准数据', '关闭','存入草稿'],
		btn1:function(index,layero){
		    var iframeWin = layero.find('iframe')[0];
            iframeWin.contentWindow.submitHandlerToSta(index, layero);
		},
		btn3:function(index,layero){
		    var iframeWin = layero.find('iframe')[0];
            iframeWin.contentWindow.submitHandlerToDra(index, layero);
            return false;
		}
	});
}
