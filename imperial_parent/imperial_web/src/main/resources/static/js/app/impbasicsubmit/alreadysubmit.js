var prefix = ctx + "shipperModule/impBasicData";
var alreadysubmitTbDivWid = $("#alreadysubmitTbDiv").width()*0.97+"px";
$(function() {
    $("#alreadysubmitTable").bootstrapTable({
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
    $("#alreadysubmitTable tbody").on('click',function(){
    	var plusiconObject = $("#alreadysubmitTable").find("tr").find("td").find(".glyphicon-plus");
    	var minusiconObject = $("#alreadysubmitTable").find("tr").find("td").find(".glyphicon-minus");
        $.each(plusiconObject,function(index,value){
        	$($(this).parents("tr")).removeClass('selectTrbgColor');  //清除样式
        })
        $.each(minusiconObject,function(index,value){
        	$($(this).parents("tr")).addClass('selectTrbgColor');
        })
    })
});
function queryParamsPaging(params) {
	var dataMap = {};
	dataMap.submitFlag = 1;
	return {
		pageSize : params.limit,//分页的话，pageSize和pageNum两个参数必不可少！
        pageNum: params.offset / params.limit + 1,
        offset: params.offset,
        isAsc:true,
        impParams:queryParams(dataMap)
	};
}

/**
 * 检索参数的封装
 * */
function queryParams(params) {
    var impParams = {   					
    	mainOrderNo:params.mainOrderNo,
    	bagNumber:params.bagNumber,
    	tackingNumber1:params.tackingNumber1,	
    	submitFlag:params.submitFlag,
    };
    return JSON.stringify(impParams);;
};

//初始化包裹表格
function InitBagNumberTable (mainNoIndex, fatRow, $fatDetail) {
	var mainOrderNo = fatRow.mainOrderNo;
	var switchState = $('.smsenable').bootstrapSwitch('state'); 
	var dataMap = {};
	if(switchState){   //如果开关状态为打开状态时，获取检索条件值
		dataMap.tackingNumber1 = $("#tackingNumber1").val();
		dataMap.bagNumber = $("#bagNumber").val();
	}
	dataMap.mainOrderNo = mainOrderNo;
	dataMap.submitFlag = 1;
	var cur_table = $fatDetail.append('<div id="bagDiv'+mainNoIndex+'" style="width:'+alreadysubmitTbDivWid+'"><table id="bagTable'+mainNoIndex+'"></table></div>').find('table');
	$(cur_table).bootstrapTable({
    	id: "bagTable"+mainNoIndex,
        url: prefix + "/getBagNumberList",
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
	dataMap.submitFlag = 1;
	$thisDetail.append('<div id="smsenable'+tableidIndex+'" style="width:'+alreadysubmitTbDivWid+'">'+$("#operatBtTpl").html()+'</div>');
	var cur_table = $thisDetail.append('<div id="detailDiv'+tableidIndex+'" style="width:'+alreadysubmitTbDivWid+'"><table id="alreadysubmitSunTable'+tableidIndex+'"></table></div>').find('table');
	var tableid = 'alreadysubmitSunTable';
	$(cur_table).bootstrapTable({
    	id: "alreadysubmitSunTable"+tableidIndex,
        url: prefix + "/list",
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
        pageSize:5,
        pageList: [5,10, 20],
        fixedColumns:true,
        fixedNumber:3,
        columns: [
        	{field: 'id',title: 'null',visible: false},
        	{field: 'state',checkbox: true,formatter: function(value, row, index) {
        	//1、第一次提交且未审核 2、二次提交且未审核  3、审核成功
            if(row.checkFlag==0||(row.isresubmitFlag==1&&row.isresubmitCheckFlag==0)||row.checkFlag==1){
                return { disabled : true}    //不可操作
            }else{
                return { disabled : false}  //可操作
            }}},
        {field: '',title: '状态标识', align: 'center',cellStyle: function (value, row, index) {
            if (row.checkFlag==0) {  //已提交 未审核 灰色
                return {css:{"background-color":"#a1afc9"}}; 
            }
            if(row.checkFlag==1){    //提交 批准成功的  蓝色
        		return {css:{"background-color":'#44cef6'}};
        	} 
        	if(row.checkFlag==2){  //提交被驳回  粉红 
        		if(row.isresubmitFlag==1&&row.isresubmitCheckFlag==0){   //二次申请且未审核 松花色
            		return {css:{"background-color":'#bce672'}};
            	}
        		return {css:{"background-color":'#f9906f'}};
        	}
            return '';
        },formatter: function(value, row, index) {
        	if (row.checkFlag==0) {  
                return value = '审核中...'; 
            }
        	if(row.checkFlag==1){
        		return value = '审核通过'; 
        	}
        	if(row.checkFlag==2){
        		if(row.isresubmitFlag==1&&row.isresubmitCheckFlag==0){
            		return value = '二次提交审核中...'; 
            	}
        		return value = '驳回'; 
        	}
        	return value = '';  //赋值为空
        }},
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
	$('#alreadysubmitSunTable'+tableidIndex).on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table load-success.bs.table", function () {
		var rows = $('#alreadysubmitSunTable'+tableidIndex).bootstrapTable('getSelections');
		// 非多个禁用
		$('#detailDiv'+tableidIndex).prev().find(".multiple").toggleClass('disabled', !rows.length);
		// 非单个禁用
		$('#detailDiv'+tableidIndex).prev().find(".single").toggleClass('disabled', rows.length!=1);
	});
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
	dataMap.submitFlag = 1;
	var params = {impParams:queryParams(dataMap)};
	$.operate.impAjax(prefix + "/getMainOrderNoList",params,function(json){
		$("#alreadysubmitTable").bootstrapTable('load',json);//主要是要这种写法
	},function(){
		layer.msg("查询失败！", {icon: 2,time: 1000});
	});
}
