var prefix = ctx + "shipperModule/impBasicData";

$(function() {
})	

/**
 * 下载模板
 */
function importTemplate(){
	$.get(prefix + "/importTemplate", function(result) {
		if (result.code == web_status.SUCCESS) {
	        window.location.href = ctx + "common/download?fileName=" + encodeURI(result.msg) + "&delete=" + true;
		} else if (result.code == web_status.WARNING) {
            $.modal.alertWarning(result.msg)
        } else {
			$.modal.alertError(result.msg);
		}
	});
}
/**
 * 批量导入数据
 */
function importExcel(formId) {
	var currentId = $.common.isEmpty(formId) ? 'importTpl' : formId;
	layer.open({
		type: 1,
		area: ['400px', '230px'],
		fix: false,
		//不固定
		maxmin: true,
		shade: 0.3,
		title: '导入订单数据',
		content: $('#' + currentId).html(),
		btn: ['<i class="fa fa-check"></i> 导入', '<i class="fa fa-remove"></i> 取消'],
		// 弹层外区域关闭
		shadeClose: true,
		btn1: function(index, layero){
			var file = layero.find('#file').val();
			if (file == '' || (!$.common.endWith(file, '.xls') && !$.common.endWith(file, '.xlsx') && !$.common.endWith(file, '.csv'))){
				$.modal.msgWarning("请选择后缀为 'xls'、'xlsx'、'csv'的文件。");
				return false;
			}
			var index = layer.load(2, {shade: false});
			$.modal.disable();
			var formData = new FormData(layero.find('form')[0]);
			$.ajax({
				url:prefix +"/importData",
				data: formData,
				cache: false,
				contentType: false,
				processData: false,
				type: 'POST',
				success: function (result) {
					if (result.code == web_status.SUCCESS) {
						$.modal.closeAll();
						$.modal.alertSuccess(result.msg);
						$.table.refresh();
					} else if (result.code == web_status.WARNING) {
						layer.close(index);
						$.modal.enable();
                        $.modal.alertWarning(result.msg)
                    } else {
						layer.close(index);
						$.modal.enable();
						$.modal.alertError(result.msg);
					}
				}
			});
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
}/**
 * 点击添加按钮展示草稿箱页面
 * */
function toDrafts(){
	var url = "/shipperModule/impBasicDataDraft";
	var height = $(window).height()-50+"px";
	var index = layer.open({
		type: 2,
		fix: false,
		maxmin: true,
		shade: 0.3,
		title: '基础表数据草稿箱',
		area: ["1200px", height], //宽高
		content: url
	});
}