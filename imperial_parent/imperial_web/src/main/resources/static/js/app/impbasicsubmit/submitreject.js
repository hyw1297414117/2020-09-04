var prefix = ctx + "shipperModule/impBasicDataSubmit";
var submitrejectTbDivWid = $("#submitrejectTbDiv").width()*0.97+"px";
$(function() {
    $("#submitrejectTable").bootstrapTable({
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
    identifyRowColor("submitrejectTable");//标记父子表中被选中的父表tr
});

/**
 * 请求参数+分页参数
 * @param params
 * @returns
 */
function queryParamsPaging(params) {
	var dataMap = {};
	dataMap.checkFlag = 2;
	var pageParams = initPageParams(params);//分页参数的初始化
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
	dataMap.mainOrderNo = mainOrderNo;
	dataMap.bagNumber = row.bagNumber;
	dataMap.checkFlag = 2;
	var switchState = $('.smsenable').bootstrapSwitch('state'); 
	if(switchState){   //如果开关状态为打开状态时，获取检索条件值
		dataMap.tackingNumber1 = $("#tackingNumber1").val();
		dataMap.shipperName = $("#shipperName").val();
		dataMap.consigneeName = $("#consigneeName").val();
		dataMap.incoterms = $("#typeSelect").val();
	}

	$thisDetail.append('<div id="smsenable'+tableidIndex+'" style="width:'+submitrejectTbDivWid+'">'+$("#operatBtTpl").html()+'</div>');
	var cur_table = $thisDetail.append('<div id="detailDiv'+tableidIndex+'" style="width:'+submitrejectTbDivWid+'"><table id="submitrejectSunTable'+tableidIndex+'"></table></div>').find('table');
	var tableid = 'submitrejectSunTable';
	$(cur_table).bootstrapTable({
    	id: "submitrejectSunTable"+tableidIndex,
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
        {field: 'id',title: 'null',visible: false},
        {field: 'state',checkbox: true
			// ,formatter: function(value, row, index) {
        	// //1、二次提交
            // if(row.isresubmitFlag==1&&row.isresubmitCheckFlag==0){
            //     return { disabled : true}    //不可操作
            // }else{
            //     return { disabled : false}  //可操作
            // }}
		},
        // {field: '',title: '状态标识', align: 'center'
		// 	,cellStyle: function (value, row, index) {
		// 		if(row.isresubmitFlag==1&&row.isresubmitCheckFlag==0){   //二次申请且未审核 松花色
		// 			return {css:{"background-color":'#bce672'}};
		// 		}
		// 		if(row.checkFlag==2&&row.isresubmitCheckFlag==1){   //二次申请被驳回
		// 			return {css:{"background-color":'#db5a6b'}};
		// 		}
		// 		return '';
        // 	}
        // 	,formatter: function(value, row, index) {
        // 		return value = '';
        // 	}/*,formatter: function(value, row, index) {
        // 	if (row.isresubmitFlag==1&&row.isresubmitCheckFlag==0) {  //1二次提交未审核
        //         return value = '二次审核中...';
        //     }
        // 	if(row.checkFlag==2&&row.isresubmitCheckFlag==1){   //二次申请被驳回
        // 		return value = '二次申请被驳回';
        // 	}
        // 	return value = '';  //赋值为空
        // }*/
		// },
        {title: '驳回详情', field: '', align: 'center', valign: 'middle', formatter:function(value,row,index){
			return "<a title='驳回详情' onclick='refuseMsg(&#39;"+row.tackingNumber1+"&#39;)'>详情</a>"
		}},
			{field: 'tackingNumber1', title: BagTrackNumber1},
			{field: 'tackingNumber2', title: BagTrackNumber2},
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
	$('#submitrejectSunTable'+tableidIndex).on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table load-success.bs.table", function () {
		var rows = $('#submitrejectSunTable'+tableidIndex).bootstrapTable('getSelections');
		// 非多个禁用
		$('#detailDiv'+tableidIndex).prev().find(".multiple").toggleClass('disabled', !rows.length);
		// 非单个禁用
		$('#detailDiv'+tableidIndex).prev().find(".single").toggleClass('disabled', rows.length!=1);
	});
};

/**
 * 表格行样式的修改
 * */
function rowStyle(row, index) {  //# e0eee8
	var isresubmitFlag = row.isresubmitFlag;
	if(isresubmitFlag==1){
		return {css:{background:'#bce672'}};
	}
	return {};
}

/**
 * 驳回详情的展示
 * @param tackingNumber1
 * @returns
 */
function refuseMsg(tackingNumber1){
	var index = layer.open({
		type: 2,
		title: '订单号为['+tackingNumber1+']的驳回详情',
		area: ['800px', '350px'], //宽高
		fix: false, //不固定
		content: '/shipperModule/impBasicDataSubmit/to_refuseMsg/'+tackingNumber1
	});
}
/**
 * 提交二次审核
 * */
function secondSubmit(){
	var dataMap = {};
	var operationDivId = $(event.target).parent().parent().next()[0].id; //获取点击修改按钮所在的div的id
	var tableId = "submitrejectSunTable"+operationDivId.substr(9); //获取点击按钮所属的tableid
	var rows = $("#submitrejectSunTable"+operationDivId.substr(9)).bootstrapTable('getSelections');
	var ids=[];
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].id);
	}
	ids = ids.join();
	dataMap.isresubmitFlag = 1; //二次提交标识
	dataMap.ids = ids;
	var layerIndex = layer.confirm("确定对这&nbsp;<font color=red>"+rows.length+"</font>&nbsp;条数据进行二次提交审核吗？", {
		btn: ["确定","取消"] //按钮
		}, function(){
			$.ajax({
		        type: "post",
		        url: "/shipperModule/impBasicData/submit",
		        data: dataMap, 
		        dataType:"json",
		        success:function(result) {
		        	layer.msg("提交成功", {icon: 1,time: 2000});
		           $("#"+tableId).bootstrapTable('refresh');
		       },
		        error:function(){
		        	layer.msg("提交失败", {icon: 2,time: 1000});
		       }
		   });
		}, function(){
			layer.close(layerIndex);
		});
}

/**
 * 搜索方法
 * */
function searchFun(){
	var dataMap = {};
	var mainOrderNo = $("#mainOrderNo").val();
	var tackingNumber1 = $("#tackingNumber1").val();
	var bagNumber = $("#bagNumber").val();
	var shipperName = $("#shipperName").val();
	var consigneeName = $("#consigneeName").val();
	var typeSelect = $("#typeSelect").val();
	dataMap.mainOrderNo = mainOrderNo;
	dataMap.tackingNumber1 = tackingNumber1;
	dataMap.bagNumber = bagNumber;
	dataMap.checkFlag = 2;
	dataMap.shipperName = shipperName;
	dataMap.consigneeName = consigneeName;
	dataMap.incoterms = typeSelect;
	var params = {impParams:queryParams(dataMap)};
	$.operate.impAjax("/shipperModule/impBasicData/getMainOrderNoList",params,function(json){
		$("#submitrejectTable").bootstrapTable('load',json);//主要是要这种写法
	},function(){
		layer.msg("查询失败！", {icon: 2,time: 1000});
	});
}
/**
 * 点击修改按钮展示修改页面
 * */
function edit(){
	var operationDivId = $(event.target).parent().parent().next()[0].id; //获取点击修改按钮所在的div的id
	var tableId = "submitrejectSunTable"+operationDivId.substr(9); //获取点击按钮所属的tableid
	var rows = $("#submitrejectSunTable"+operationDivId.substr(9)).bootstrapTable('getSelections');
	var height = $(window).height()-50+"px";
	var index = layer.open({
		type: 2,
		fix: false,
		maxmin: true,
		shade: 0.3,
		title: Modifycustomsclearancedata,
		area: ["800px", height], //宽高
		content: "/shipperModule/impBasicData/toEditPage/"+rows[0].id,
		btn:  ['确定', '关闭','存入草稿'],
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
