var prefix = ctx + "shipperModule/impBasicData";

$(function() {
	
})	

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