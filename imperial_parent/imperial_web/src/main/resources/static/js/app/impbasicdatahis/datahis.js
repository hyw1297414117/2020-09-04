var prefix = ctx + "shipperModule/impBasicDatahis";
var impbasicdatahisTbDivWid = $("#impbasicdatahisTbDiv").width()*0.97+"px";
$(function() {
    $("#impbasicdatahisTb").bootstrapTable({
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
            InitBagNumberTable(index, row, $detail);
        }
    })
    $('#searchModule').append($("#searchTpl").html());  //添加搜索模块
    switchRender(); //渲染开关
    /**
     * 标记父子表中被选中的父表tr
     * */
    $("#impbasicdatahisTb tbody").on('click',function(){
    	var plusiconObject = $("#impbasicdatahisTb").find("tr").find("td").find(".glyphicon-plus");
    	var minusiconObject = $("#impbasicdatahisTb").find("tr").find("td").find(".glyphicon-minus");
        $.each(plusiconObject,function(index,value){
        	$($(this).parents("tr")).removeClass('selectTrbgColor');  //清除样式
        })
        $.each(minusiconObject,function(index,value){
        	$($(this).parents("tr")).addClass('selectTrbgColor');
        })
    })
/**
 * 初始化订单背景颜色
 * @param params
 * @returns
 */
bgColorInit();
});

function queryParamsPaging(params) {
	var dataMap = {};
	dataMap.orderEndFlag = 1;
	return {
		pageSize : params.limit,//分页的话，pageSize和pageNum两个参数必不可少！
        pageNum: params.offset / params.limit + 1,
        offset: params.offset,
        isAsc:true,
        impParams:queryParams(dataMap)
	};
}

/**
 * 初始化包裹表格
 * */
function InitBagNumberTable (mainNoIndex, fatRow, $fatDetail) {
	var mainOrderNo = fatRow.mainOrderNo;
	var switchState = $('.smsenable').bootstrapSwitch('state'); 
	var dataMap = {};
	if(switchState){   //如果开关状态为打开状态时，获取检索条件值
		dataMap.tackingNumber1 = $("#tackingNumber1").val();
		dataMap.bagNumber = $("#bagNumber").val();
	}
	dataMap.mainOrderNo = mainOrderNo;
	dataMap.orderEndFlag = 1;
	var cur_table = $fatDetail.append('<div id="bagDiv'+mainNoIndex+'" style="width:'+impbasicdatahisTbDivWid+'"><table id="bagTable'+mainNoIndex+'"></table></div>').find('table');
	$(cur_table).bootstrapTable({
    	id: "bagTable"+mainNoIndex,
        url: "/shipperModule/impBasicData/getBagNumberList",
        method: 'post',
        contentType: "application/x-www-form-urlencoded",   // 编码类型
        sidePagination : 'server',
        queryParams: {impParams:queryParams(dataMap)},
        detailView:true,
        //striped: false,  //隔行变色
        uniqueId: "id",
        pagination : true,//是否分页
        queryParamsType:'limit',
        pageSize: 10,
        pageNumber : 1, //初始化加载第一页
        pageList: [10, 20],
        isAsc:true,
        columns: [
            {field: 'id',title: 'null',visible: false},
            {field: 'bagNumber',title: '包裹号'}],
        //注册加载子表的事件。此处onExpandRow有且仅有三个参数
        onExpandRow: function (index, row, $detail) {
            InitSubTable(mainNoIndex,index,row,$detail,mainOrderNo);
        }
    });
};

/**
 * 重构订单列表表
 * */
function InitSubTable (mainNoIndex,bagNumberIndex, row, $thisDetail,mainOrderNo) {
	var tableidIndex = mainNoIndex+""+bagNumberIndex;   //加空字符连接，不加会出问题，比如0+0=0
	var dataMap = {};
	var switchState = $('.smsenable').bootstrapSwitch('state'); 
	if(switchState){   //如果开关状态为打开状态时，获取检索条件值
		dataMap.tackingNumber1 = $("#tackingNumber1").val();
	}
	dataMap.mainOrderNo = mainOrderNo;
	dataMap.bagNumber = row.bagNumber;
	dataMap.orderEndFlag = 1;
	$thisDetail.append('<div id="smsenable'+tableidIndex+'" style="width:'+impbasicdatahisTbDivWid+'">'+$("#operatBtTpl").html()+'</div>');
	var cur_table = $thisDetail.append('<div id="detailDiv'+tableidIndex+'" style="width:'+impbasicdatahisTbDivWid+'"><table id="impbasicdatahisSunTb'+tableidIndex+'"></table></div>').find('table');
	var tableid = 'impbasicdatahisSunTb';
	$(cur_table).bootstrapTable({
    	id: "impbasicdatahisSunTb"+tableidIndex,
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
        {field: 'tackingNumber1', title: '包裹追踪号1'},
        {field: 'tackingNumber2', title: '包裹追踪号2'},
        {field: 'bagNumber',title: '包裹号'},
        {field: 'boxNumber',title: '外箱号'},
        {field: 'palletNumber',title: '托盘号'},
        {field: 'containerNumber',title: '集装箱号码'},
        {field: 'reference1',title: '参考编码1'},
        {field: 'referrnce2',title: '参考编码2'},
        {field: 'shipperName',title: '发货人'},
        {field: 'shipperReference',title: '发货方识别码'},
        {field: 'consigneeName',title: '收货人姓名'},
        {field: 'lineAddress1',title: '收货地址1',},
        {field: 'lineAddress2',title: '收货地址2'},
        {field: 'lineAddress3',title: '收货地址3'},
        {field: 'town',title: '城镇'},
        {field: 'state',title: '州郡'},
        {field: 'postCode',title: '邮编'},
        {field: 'countryCode',title: '国家代码'},
        {field: 'email',title: '电子邮箱'},
        {field: 'phone',title: '电话'},
        {field: 'pieces',title: '包裹数量'},
        {field: 'weight',title: '重量'},
        {field: 'weightUom',title: '重量单位'},
        {field: 'value',title: '价值'},
        {field: 'shippingRate',title: '运费'},
        {field: 'currency',title: '币种'},
        {field: 'incoterms',title: '贸易术语 DDU/DDP'},
        {field: 'importPurpose',title: '进口目的'},
        {field: 'eoriNumber',title: '未知'},
        {field: 'mossNumber',title: '未知'},
        {field: 'description',title: '货物描述'},
        {field: 'hsCode',title: '海关代码'},
        {field: 'itemQuantity',title: '物品件数'},
        {field: 'itemValue',title: '物品单价'},
        {field: 'skuNumber',title: '未知'},
        {field: 'returnInstruction',title: '退货方式'},
        {field: 'salesLink',title: '销售连结'},
        {field: 'lastMileProvider',title: '末端派送商'},
        {field: 'lastMileAccountName',title: '末端派送账户名'},
        {field: 'orderIsdraftFlag',visible: false},
        {field: 'orderEndFlag',visible: false},
        {field: 'submitFlag',visible: false}],
        onLoadSuccess: function () {
            registCheckbox(tableid,tableidIndex,$('#'+tableid+tableidIndex))
        },
    });
	$('#impbasicdatahisSunTb'+tableidIndex).on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table load-success.bs.table", function () {
		var rows = $('#impbasicdatahisSunTb'+tableidIndex).bootstrapTable('getSelections');
		// 非多个禁用
		$('#detailDiv'+tableidIndex).prev().find(".multiple").toggleClass('disabled', !rows.length);
		// 非单个禁用
		$('#detailDiv'+tableidIndex).prev().find(".single").toggleClass('disabled', rows.length!=1);
	});
};

/**
 * 检索方法的封装
 * */
function queryParams(params) {
    var impParams = {   					
    	tackingNumber1:params.tackingNumber1,	
    	orderEndFlag:params.orderEndFlag,
    	pageSize:10,
    	pageNum:1,
    	isAsc:true
    };
    return JSON.stringify(impParams);
};
/**
 * 搜索方法
 * */
function searchFun(){
	var dataMap = {};
	var mainOrderNo = $("#mainOrderNo").val();
	var tackingNumber1 = $("#tackingNumber1").val();
	var bagNumber = $("#bagNumber").val();
	dataMap.mainOrderNo = mainOrderNo;
	dataMap.tackingNumber1 = tackingNumber1;
	dataMap.bagNumber = bagNumber;
	dataMap.orderEndFlag = 1;
	var params = {impParams:queryParams(dataMap)};
	$.operate.impAjax("/shipperModule/impBasicData/getMainOrderNoList",params,function(json){
		$("#impbasicdatahisTb").bootstrapTable('load',json);//主要是要这种写法
	},function(){
		layer.msg("查询失败！", {icon: 2,time: 1000});
	});
}
        