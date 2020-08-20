var prefix = ctx + "shipperModule/impBasicData";
var ddplistTableDivWid = $("#ddplistTableDiv").width()*0.97+"px";
$(function() {
    $("#ddplistTable").bootstrapTable({
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
    /**
     * 标记父子表中被选中的父表tr
     * */
    $("#ddplistTable tbody").on('click',function(){
    	var plusiconObject = $("#ddplistTable").find("tr").find("td").find(".glyphicon-plus");
    	var minusiconObject = $("#ddplistTable").find("tr").find("td").find(".glyphicon-minus");
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
/**
 * 父表的搜索参数封装 分页用
 * */
function queryParamsPaging(params) {
	var dataMap = {};
	dataMap.submitFlag = 1; //已提交
	dataMap.incoterms = "DDP";
	return {
		pageSize : params.limit,//分页的话，pageSize和pageNum两个参数必不可少！
        offset: params.offset,
        pageNum: params.offset / params.limit + 1,
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
	dataMap.submitFlag = 1;
	dataMap.incoterms = 'DDP';
	var cur_table = $fatDetail.append('<div id="bagDiv'+mainNoIndex+'" style="width:'+ddplistTableDivWid+'"><table id="bagTable'+mainNoIndex+'"></table></div>').find('table');
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
	dataMap.submitFlag = 1;
	dataMap.incoterms = 'DDP';
	$thisDetail.append('<div id="smsenable'+tableidIndex+'" style="width:'+ddplistTableDivWid+'">'+$("#operatBtTpl").html()+'</div>');
	var cur_table = $thisDetail.append('<div id="detailDiv'+tableidIndex+'" style="width:'+ddplistTableDivWid+'"><table id="ddplistSunTable'+tableidIndex+'"></table></div>').find('table');
	var tableid = 'ddplistSunTable';
	$(cur_table).bootstrapTable({
    	id: "ddplistSunTable"+tableidIndex,
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
        {field: 'state',checkbox: true,formatter: function(value, row, index) {
        	//1、审核通过的 2、状态为驳回且未二次提交的 3、状态为驳回且二次提交审核了的 
            if(row.checkFlag==1||(row.checkFlag == 2&&row.isresubmitFlag==0)||(row.checkFlag == 2&&row.isresubmitCheckFlag==1)){
                return { disabled : true}    //不可操作
            }else{
                return { disabled : false}  //可操作
            }}},
        {field: '',title: '状态标识',align: 'center',cellStyle: function (value, row, index) {
            if(row.checkFlag==1){    //提交 批准成功的  蓝色
        		return {css:{"background-color":'#44cef6'}};
        	} 
        	if(row.checkFlag==2){  //提交 驳回的  粉红 
        		if(row.isresubmitFlag==1&&row.isresubmitCheckFlag==0){   //二次申请且未审核的  松花色
            		return {css:{"background-color":'#bce672'}};
            	}
        		return {css:{"background-color":'#f9906f'}};
        	}
            return '';
        },formatter: function(value, row, index) {
        	return value = '';
        }/*,formatter: function(value, row, index) {
        	if (row.checkFlag==1) {  //已提交 批准成功的
                return value = '批准通过'; 
            }
        	if((row.checkFlag==2)){
        		if(row.isresubmitFlag==1&&row.isresubmitCheckFlag==0){
            		return value = '二次提交'; 
            	}
        		return value = '已驳回'; 
        	}
        	return value = '';  //赋值为空
        }*/},
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
	$('#ddplistSunTable'+tableidIndex).on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table load-success.bs.table", function () {
		var rows = $('#ddplistSunTable'+tableidIndex).bootstrapTable('getSelections');
		// 非多个禁用
		$('#detailDiv'+tableidIndex).prev().find(".multiple").toggleClass('disabled', !rows.length);
		// 非单个禁用
		$('#detailDiv'+tableidIndex).prev().find(".single").toggleClass('disabled', rows.length!=1);
	});
};

/**
 * 检索参数的封装
 * */
function queryParams(params) {
    var impParams = {  
    	mainOrderNo:params.mainOrderNo,
        bagNumber:params.bagNumber,
    	tackingNumber1:params.tackingNumber1,	
    	draftFlag:params.draftFlag,
    	submitFlag:params.submitFlag,
    	isresubmitFlag:params.isresubmitFlag,
    	endFlag:params.endFlag,
    	incoterms:params.incoterms
    };
    return JSON.stringify(impParams);;
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
	dataMap.incoterms = 'DDP';
	var params = {impParams:queryParams(dataMap)};
	$.operate.impAjax(prefix + "/getMainOrderNoList",params,function(json){
		$("#ddplistTable").bootstrapTable('load',json);//主要是要这种写法
	},function(){
		layer.msg("查询失败！", {icon: 2,time: 1000});
	});
}
/**
 * 表格行样式的修改
 * */
function rowStyle(row, index) {  //# e0eee8
	var checkFlag = row.checkFlag;
	var isresubmitFlag = row.isresubmitFlag;
	if(checkFlag==1){    //同意的
		return {css:{background:'#bce672'}};
	}else if(checkFlag==2&&isresubmitFlag==0){  //驳回的
		return {css:{background:'#c2ccd0'}};
	}
	if(isresubmitFlag==1){
		return {css:{background:'#bce672'}};
	}
	return {};
}

/**
 * 点击审核按钮展示订单详情页面
 * */
function showCheckPage(){
	var dataMap = {};
	var opinionDivShowflag = 0;  //0未展开过，1展开过 存储数据用
	var operationDivId = $(event.target).parent().parent().next()[0].id; //获取点击审核按钮所在的div的id
	var tableId = "ddplistSunTable"+operationDivId.substr(9); //获取点击按钮所属的tablle索引
	var rows = $("#ddplistSunTable"+operationDivId.substr(9)).bootstrapTable('getSelections');
	var height = $(window).height()-50+"px";
	var layerIndex = layer.open({
		type: 2,
		fix: false,
		maxmin: true,
		shade: 0.3,
		title: "审核订单数据",
		area: ["800px", height], //宽高
		content: "/agentModule/agentData/toEditPage/"+rows[0].id,
		btn:  ['通过', '关闭','驳回'],
		btn1:function(index,layero){
		    var iframeWin = layero.find('iframe')[0];
            iframeWin.contentWindow.approved(JSON.stringify(dataMap));
            setTimeout(function () {   //延时1秒关闭
            	layer.close(index);
            	$("#"+tableId).bootstrapTable('refresh');
            }, 1000);
		},
		btn3:function(index,layero){
			var	opinionType = $("#opinionType").find("option:selected").val();
			var opinion = $("#opinion").val();
			dataMap.opinionType=opinionType;
			dataMap.opinion=opinion;
		    var iframeWin = layero.find('iframe')[0];
            iframeWin.contentWindow.refuse(JSON.stringify(dataMap));
            setTimeout(
            		function(){
            			layer.close(index)   
            			$("#"+tableId).bootstrapTable('refresh');
            		}, 1000);
			return false;   //一定要返回false，不然弹出层会立即关闭
		}
	});
	/**
	 * 鼠标悬浮到驳回按钮时候的动作
	 * */
	$(".layui-layer-content").css('position:relative'); 
	$(".layui-layer-btn .layui-layer-btn2").hover(//为li绑定了鼠标进入和鼠标移开的两个参数
	function() {
				if(opinionDivShowflag==0){
					$(".layui-layer").append($("#opinionTpl").html());
				}else{
					 $("#opinionDiv").show();
				}
				opinionDivShowflag = 1;  //赋值1
			  },function() {
					$("#opinionDiv").mouseleave(function(){
						$("#opinionDiv").hide();
					});
				  }
		);
}
