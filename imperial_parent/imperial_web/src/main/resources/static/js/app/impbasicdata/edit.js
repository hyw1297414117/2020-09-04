var prefix = ctx + "shipperModule/impBasicData";
var tackingNumber1 = $.common.trim($("#tackingNumber1").val());
var draftFlag = '0';
var dataMap = {};
dataMap.tackingNumber1 = tackingNumber1;
dataMap.draftFlag = draftFlag;
var str = JSON.stringify(dataMap);
$("#form-data-edit").validate({
    focusCleanup: true,
	onkeyup: false,
	rules:{
		tackingNumber1:{
		    required: true,
			minlength: 1,
			maxlength: 20,
			remote: {
				url: prefix + "/checkTackNumUnique",
                type: "post",
                dataType: "json",
                data: {
                	"rebate":str
                },
                dataFilter: function(data, type) {
                	return $.validate.unique(data);
                }
            }
		},
		bagNumber:{
			required: true
		}
		
	},
	messages: {
        "tackingNumber1": {
            remote: Theordernumberalreadyexists
        }
    },
    focusCleanup: true
});
//存为标准数据
function submitHandlerToSta() {

    if ($.validate.form()) {
        $.operate.save(prefix + "/edit", $('#form-data-edit').serialize());
    }
}
//存为草稿
function submitHandlerToDra() {

    if ($.validate.form()) {
        $.operate.save(prefix + "/editToDraft", $('#form-data-edit').serialize());
    }
}

