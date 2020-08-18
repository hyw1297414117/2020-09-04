$(function() {
	/**
	 * 打开对应页签
	 */
   $(".checkpending").on('click',function(){
	   $.modal.openTab('订单信息审核',"/agentModule/agentData");
   })
   
   $(".div2").on('click',function(){
	   layer.msg("该模块正在开发中...");
   })
});
