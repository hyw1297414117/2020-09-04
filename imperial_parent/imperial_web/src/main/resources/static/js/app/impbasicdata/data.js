var prefix = ctx + "shipperModule/impBasicData";
var screenWidth = window.screen.width;  //获取屏幕宽度
var tableSonWidth = screenWidth*0.83+"px";
var impbasicDataDivWid = $("#impbasicDataDiv").width()*0.97+"px";
$(function() {
    $("#impbasicDataTable").bootstrapTable({
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
        {field: 'mainOrderNo',title: MainOrderNo}],
        //注册加载子表的事件。注意下这里的三个参数！
        onExpandRow: function (index, row, $detail) {
        	InitSubTable(index, row, $detail);
        }
    })
    $('#searchModule').append($("#searchTpl").html());  //添加搜索模块
    switchRender(); //渲染开关
    identifyRowColor("impbasicDataTable"); //标记父子表中被选中的父表tr
});
/**
 * 请求参数+分页参数
 * @param params
 * @returns
 */
function queryParamsPaging(params) {
	var dataMap = {};
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
	dataMap.mainOrderNo = mainOrderNo;
	dataMap.bagNumber = row.bagNumber;
	dataMap.checkFlag=0
	var switchState = $('.smsenable').bootstrapSwitch('state'); 
	if(switchState){   //如果开关状态为打开状态时，获取检索条件值
		dataMap.tackingNumber1 = $("#tackingNumber1").val();
		dataMap.checkFlag = $("#stateSelect").val();
		dataMap.shipperName = $("#shipperName").val();
		dataMap.consigneeName = $("#consigneeName").val();
		dataMap.incoterms = $("#typeSelect").val();
	}

	$thisDetail.append('<div id="smsenable'+tableidIndex+'" style="width:'+impbasicDataDivWid+'">'+$("#operatBtTpl").html()+'</div>');
	var cur_table = $thisDetail.append('<div id="detailDiv'+tableidIndex+'" style="width:'+impbasicDataDivWid+'"><table id="impbasicDataSunTable'+tableidIndex+'"></table></div>').find('table');
	var tableid = 'impbasicDataSunTable';
	$(cur_table).bootstrapTable({
    	id: "impbasicDataSunTable"+tableidIndex,
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
        pageSize:10,
        pageList: [5,10, 20],
        fixedColumns:true,
        fixedNumber:3,
        columns: [
        	{field: 'id',title: 'null',visible: false},
        	{field: 'state',checkbox: true,formatter: function(value, row, index) {
        	//1、第一次提交且未审核 2、二次提交且未审核  3、审核成功
            if((row.submitFlag == 1&&row.checkFlag==0)||(row.isresubmitFlag==1&&row.isresubmitCheckFlag==0)||row.checkFlag==1){
                return { disabled : true}    //不可操作
            }else{
                return { disabled : false}  //可操作
            }}},
        {field: '',title: stateIdentification, align: 'center',cellStyle: function (value, row, index) {
            if (row.submitFlag == 1&&row.checkFlag==0) {  //已提交 未审核 灰色
                return {css:{"background-color":"#a1afc9"}}; 
            }
            if(row.checkFlag==1){    //提交 批准成功的  蓝色
        		return {css:{"background-color":'#44cef6'}};
        	} 
        	if(row.checkFlag==2){  //提交被驳回  粉红 
        		// if(row.isresubmitFlag==1&&row.isresubmitCheckFlag==0){   //二次申请且未审核 松花色
            	// 	return {css:{"background-color":'#bce672'}};
            	// }
        		// if(row.isresubmitFlag==1&&row.isresubmitCheckFlag==1){   //二次申请且被驳回
            	// 	return {css:{"background-color":'#db5a6b'}};
            	// }
        		return {css:{"background-color":'#f9906f'}};
        	}
            return '';
        },formatter: function(value, row, index) {
        	return value = '';
        }
        /*,formatter: function(value, row, index) {
        	if (row.submitFlag == 1&&row.checkFlag==0) {  //1、已提交未审核 2、二次提交未审核
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
        }*/},
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
	$('#impbasicDataSunTable'+tableidIndex).on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table load-success.bs.table", function () {
		var rows = $('#impbasicDataSunTable'+tableidIndex).bootstrapTable('getSelections');
		// 非多个禁用
		$('#detailDiv'+tableidIndex).prev().find(".multiple").toggleClass('disabled', !rows.length);
		// 非单个禁用
		$('#detailDiv'+tableidIndex).prev().find(".single").toggleClass('disabled', rows.length!=1);
	});
};

/**
 * 表格行样式的修改
 * */
function rowStyle(row, index) {  
	return {css:{background:'#00bc12'}};  
}

/**
 * 搜索方法
 * */
function searchFun(){
	var dataMap = {};
	var mainOrderNo = $("#mainOrderNo").val();
	var tackingNumber1 = $("#tackingNumber1").val();
	var bagNumber = $("#bagNumber").val();
	var stateSelect = $("#stateSelect").val();
	var shipperName = $("#shipperName").val();
	var consigneeName = $("#consigneeName").val();
	var typeSelect = $("#typeSelect").val();
	dataMap.mainOrderNo = mainOrderNo;
	dataMap.tackingNumber1 = tackingNumber1;
	dataMap.bagNumber = bagNumber;
	dataMap.checkFlag = stateSelect;
	dataMap.shipperName = shipperName;
	dataMap.consigneeName = consigneeName;
	dataMap.incoterms = typeSelect;
	var params = {impParams:queryParams(dataMap)};
	$.operate.impAjax(prefix + "/getMainOrderNoList",params,function(json){
		$("#impbasicDataTable").bootstrapTable('load',json);//主要是要这种写法
	},function(){
		layer.msg("查询失败！", {icon: 2,time: 1000});
	});
}

/**
 * 提交审核方法
 * */
function submit(){
	var operationDivId = $(event.target).parent().parent().next()[0].id; //获取点击修改按钮所在的div的id
	var tableId = "impbasicDataSunTable"+operationDivId.substr(9); //获取点击按钮所属的tableid
	var rows = $("#impbasicDataSunTable"+operationDivId.substr(9)).bootstrapTable('getSelections');
	var ids=[];
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].id);
	}
	ids = ids.join();
	var dataMap = {};
	dataMap.ids = ids;
	dataMap.submitFlag = 1;
	var layerIndex = layer.confirm("确定对这&nbsp;<font color=red>"+rows.length+"</font>&nbsp;条数据提交审核吗？", {
		btn: ["确定","取消"] //按钮
		}, function(){
			$.operate.impAjax("/shipperModule/impBasicData/submit",dataMap,function(){
				layer.msg("提交成功", {icon: 1,time: 2000});
		        $("#impbasicDataSunTable"+operationDivId.substr(9)).bootstrapTable('refresh');//主要是要这种写法
			},function(){
				layer.msg("提交失败", {icon: 2,time: 1000});
			});
		}, function(){
			layer.close(layerIndex);
		})
}

/**
 * 删除方法（待优化，应该刷新父级表格）
 */
function remove(){
	var operationDivId = $(event.target).parent().parent().next()[0].id; //获取点击修改按钮所在的div的id
	var tableId = "impbasicDataSunTable"+operationDivId.substr(9); //获取点击按钮所属的tableid
	var rows = $("#impbasicDataSunTable"+operationDivId.substr(9)).bootstrapTable('getSelections');
	var ids=[];
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].id);
	}
	ids = ids.join();
	var dataMap = {};
	dataMap.ids = ids;
	dataMap.submitFlag = 1;
	var layerIndex = layer.confirm(Confirmthedeletion+"&nbsp;<font color=red>"+rows.length+"</font>&nbsp;"+Thedata, {
		btn: [Subm, Close] ,//按钮
		title:message
		}, function(){
			$.operate.impAjax("/shipperModule/impBasicData/remove",dataMap,function(){
				layer.msg(successfullydelete, {icon: 1,time: 2000});
		        $("#impbasicDataSunTable"+operationDivId.substr(9)).bootstrapTable('refresh');//主要是要这种写法
			},function(){
				layer.msg(failtodelete, {icon: 2,time: 1000});
			});
		}, function(){
			layer.close(layerIndex);
		})
}
/**
 * 导出Excel表格
 */
function exportExcel(formId) {
	//table.set();
	$.modal.confirm("确定导出目标数据吗？", function() {
		var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
		var operationDivId = $(event.target).parent().parent().next()[0].id; //获取点击修改按钮所在的div的id
		var tableId = "impbasicDataSunTable"+operationDivId.substr(9); //获取点击按钮所属的tableid
		var params = $("#" + tableId).bootstrapTable('getOptions');
		var dataParam = $("#" + currentId).serializeArray();
		dataParam.push({ "name": "orderByColumn", "value": params.sortName });
		dataParam.push({ "name": "isAsc", "value": params.sortOrder });
		$.modal.loading("正在导出数据，请稍后...");
		$.post(prefix+"/export", dataParam, function(result) {
			if (result.code == web_status.SUCCESS) {
		        window.location.href = ctx + "common/download?fileName=" + encodeURI(result.msg) + "&delete=" + true;
			} else if (result.code == web_status.WARNING) {
                $.modal.alertWarning(result.msg)
            } else {
				$.modal.alertError(result.msg);
			}
			$.modal.closeLoading();
		});
	});
}

/**
 * 点击修改按钮展示修改页面
 * */
function edit(){
	var operationDivId = $(event.target).parent().parent().next()[0].id; //获取点击修改按钮所在的div的id
	var tableId = "impbasicDataSunTable"+operationDivId.substr(9); //获取点击按钮所属的tableid
	var rows = $("#impbasicDataSunTable"+operationDivId.substr(9)).bootstrapTable('getSelections');
	var height = $(window).height()-50+"px";
	var index = layer.open({
		type: 2,
		fix: false,
		maxmin: true,
		shade: 0.3,
		title: Modifycustomsclearancedata,
		area: ["800px", height], //宽高
		content: "/shipperModule/impBasicData/toEditPage/"+rows[0].id,
		btn:  [Subm, Close,Savetodraft],
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
/**
 * 点击添加按钮展示添加页面
 * */
function add(){
	var url = prefix + "/add";
	var height = $(window).height()-50+"px";
	var index = layer.open({
		type: 2,
		fix: false,
		maxmin: true,
		shade: 0.3,
		title: '添加清关系统基础表数据',
		area: ["800px", height], //宽高
		content: url,
		btn: ['确定', '关闭','存入草稿'],
		btn1:function(index,layero){
		    var iframeWin = layero.find('iframe')[0];
            iframeWin.contentWindow.submitHandler(index, layero);
		},
		btn3:function(index,layero){
		    var iframeWin = layero.find('iframe')[0];
            iframeWin.contentWindow.submitDraftHandler(index, layero);
            return false;
		}
	});
}
