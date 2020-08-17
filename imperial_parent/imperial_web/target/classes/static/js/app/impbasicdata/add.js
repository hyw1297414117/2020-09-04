		var prefix = ctx + "shipperModule/impBasicData"
        var tackingNumber1 = $.common.trim($("#tackingNumber1").val());
		var draftFlag = '0';
		var dataMap = {};
		dataMap.tackingNumber1 = tackingNumber1;
		dataMap.draftFlag = draftFlag;
		var str = JSON.stringify(dataMap);
        $("#form-data-add").validate({
        	onkeyup: false,
        	rules:{
        		tackingNumber1:{
        		    required: true,
        			minlength: 2,
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
                    remote: "订单号已经存在"
                }
            },
            focusCleanup: true
        });
        //订单正式数据
        function submitHandler() {
            if ($.validate.form()) {
                $.operate.save(prefix + "/add", $('#form-data-add').serialize());
            }
        }
        //直接存入草稿
        function submitDraftHandler() {
            if ($.validate.form()) {
                $.operate.save(prefix + "/addDraft", $('#form-data-add').serialize());
            }
        }