/**
 * 清关公司审批通过此订单
 * */
function approved(params){
	var params = JSON.parse(params);
	var orderId = $("#id").val();
	var tackingNumber1 = $("#tackingNumber1").val();
	var ids=[];
	ids.push(orderId);
	ids = ids.join();
	var dataMap = {};
	var isresubmitFlag = params.isresubmitFlag; //获取是否二次提交状态
	if(isresubmitFlag==1){
		dataMap.isresubmitCheckFlag = 1;
	}
	dataMap.ids = ids;
	dataMap.tackingNumber1 = tackingNumber1;
	dataMap.checkFlag = 1; //审核结果（通过）
	$.ajax({
        type: "post",
        url: "/agentModule/agentData/agentCheck",
        data: dataMap, 
        dataType:"json",
        success:function(result) {
        	layer.msg("审批成功", {icon: 1,time: 2000});
       },
        error:function(){
        	layer.msg("审批失败", {icon: 2,time: 1000});
       }
   });
}
/**
 * 清关公司驳回订单
 * */
function refuse(params){
	var paramsResult = JSON.parse(params);
	var orderId = $("#id").val();
	var tackingNumber1 = $("#tackingNumber1").val();
	var ids=[];
	ids.push(orderId);
	ids = ids.join();
	var dataMap = {};
	var isresubmitFlag = paramsResult.isresubmitFlag; //获取是否二次提交状态
	if(isresubmitFlag==1){
		dataMap.isresubmitCheckFlag = 1;
	}
	dataMap.ids = ids;
	dataMap.opinionType = paramsResult.opinionType;
	dataMap.opinion = paramsResult.opinion;
	dataMap.tackingNumber1 = tackingNumber1;
	dataMap.checkFlag = 2; //审核结果（驳回）
	$.ajax({
        type: "post",
        url: "/agentModule/agentData/agentCheck",
        data: dataMap, 
        dataType:"json",
        success:function(result) {
        	layer.msg("驳回成功", {icon: 1,time: 1000});
        },
        error:function(){
        	layer.msg("驳回失败", {icon: 2,time: 1000});
       }
   });
		
}