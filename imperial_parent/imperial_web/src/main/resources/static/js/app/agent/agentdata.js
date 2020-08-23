var prefix = ctx + "agentModule/agentData";
var agentdataTableDivWid = $("#agentdataTableDiv").width()*0.97+"px";
$(function() {
    $("#agentdataTable").bootstrapTable({
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
    identifyRowColor("agentdataTable");//标记父子表中被选中的父表tr
});

/**
 * 请求参数+分页参数
 * @param params
 * @returns
 */
function queryParamsPaging(params) {
	var dataMap = {};
	dataMap.submitFlag = 1; //只查询已经提交的订单
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
	var switchState = $('.smsenable').bootstrapSwitch('state'); 
	if(switchState){   //如果开关状态为打开状态时，获取检索条件值
		dataMap.tackingNumber1 = $("#tackingNumber1").val();
	}
	dataMap.mainOrderNo = mainOrderNo;
	dataMap.bagNumber = row.bagNumber;
	dataMap.submitFlag = 1;
	$thisDetail.append('<div id="smsenable'+tableidIndex+'" style="width:'+agentdataTableDivWid+'">'+$("#operatBtTpl").html()+'</div>');
	var cur_table = $thisDetail.append('<div id="detailDiv'+tableidIndex+'" style="width:'+agentdataTableDivWid+'"><table id="agentdataSunTable'+tableidIndex+'"></table></div>').find('table');
	var tableid = 'agentdataSunTable';
	$(cur_table).bootstrapTable({
    	id: "agentdataSunTable"+tableidIndex,
        url: "/shipperModule/impBasicData/list",
        method: 'post',
        contentType: "application/x-www-form-urlencoded",   // 编码类型
        sidePagination : 'server',
        queryParams : function(params){
        	return{
        		pageSize : params.limit,//分页时候pageSize和pageNum两个参数必不可少！
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
        	//1、审核通过的 2、状态为驳回且未二次提交的 3、状态为驳回且二次审核了的 
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
        		if(row.isresubmitFlag==1&&row.isresubmitCheckFlag==1){   //二次申请且被驳回 深红色
            		return {css:{"background-color":'#db5a6b'}};
            	}
        		return {css:{"background-color":'#f9906f'}};
        	}
            return '';
        },formatter: function(value, row, index) {
        	return value = '';
        }
        /*,formatter: function(value, row, index) {
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
	$('#agentdataSunTable'+tableidIndex).on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table load-success.bs.table", function () {
		var rows = $('#agentdataSunTable'+tableidIndex).bootstrapTable('getSelections');
		//后期做优化，为什么会执行多次？
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
	var checkFlag = row.checkFlag;
	var isresubmitFlag = row.isresubmitFlag;
	if(checkFlag==1){    //申请 批准成功的
		return {css:{background:'#e9e7ef'}};
	} 
	if(checkFlag==2&&isresubmitFlag==0){  //驳回的
		return {css:{background:'#c2ccd0'}};
	}
	if(isresubmitFlag==1){   //二次申请的
		return {css:{background:'#bce672'}};
	}
	return {};
}

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
	dataMap.submitFlag = 1;
	var params = {impParams:queryParams(dataMap)};
	$.operate.impAjax("/shipperModule/impBasicData/getMainOrderNoList",params,function(json){
		$("#agentdataTable").bootstrapTable('load',json);//主要是要这种写法
	},function(){
		layer.msg("查询失败！", {icon: 2,time: 1000});
	});
}

/**
 * 点击审核按钮展示订单详情页面
 * */
function showCheckPage(){
	var dataMap = {};
	var opinionDivShowflag = 0;  //0未展开过，1展开过 存储数据用
	var operationDivId = $(event.target).parent().parent().next()[0].id; //获取点击审核按钮所在的div的id
	var tableId = "agentdataSunTable"+operationDivId.substr(9); //获取点击按钮所属的tablle索引
	var rows = $("#agentdataSunTable"+operationDivId.substr(9)).bootstrapTable('getSelections');
	var height = $(window).height()-50+"px";
	var isresubmitFlag = rows[0].isresubmitFlag;  //查询该订单二次提交状态
	dataMap.isresubmitFlag = isresubmitFlag;
	var layerIndex = layer.open({
		type: 2,
		fix: false,
		maxmin: true,
		shade: 0.3,
		title: "审核订单数据",
		area: ["800px", height], //宽高
		content: "/agentModule/agentData/toEditPage/"+rows[0].id,
		btn:  ['通过','关闭','驳回'],
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
		/*end: function () {   //只要弹出层被销毁，一定回执行此方法
			$("#"+tableId).bootstrapTable('refresh');
        }*/
	});
	$(".layui-layer-content").css('position:relative'); 
	$(".layui-layer-btn .layui-layer-btn2").hover( //为li绑定了鼠标进入和鼠标移开的两个参数
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
