 		var prefix = ctx + "shipperModule/impBasicDataDraft";
        var tackingNumber1 = $.common.trim($("#tackingNumber1").val());
		var draftFlag = '1';
		var dataMap = {};
		dataMap.tackingNumber1 = tackingNumber1;
		dataMap.draftFlag = draftFlag;
		var str = JSON.stringify(dataMap);
        $("#form-data-draftEdit").validate({
        	onkeyup: false,
        	rules:{
        		tackingNumber1:{
        		    required: true,
        			minlength: 2,
        			maxlength: 20,
        			remote: {
                        url: "/shipperModule/impBasicData/checkTackNumUnique",
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
        
        $("#form-data-draftEdit").validate({
            focusCleanup: true
        });
		//转为标准数据
        function submitHandlerToSta() {
            if ($.validate.form()) {
                $.operate.save(prefix + "/editToStandard", $('#form-data-draftEdit').serialize());
            }
        }
		//依旧为草稿
        function submitHandlerToDra() {
            if ($.validate.form()) {
                $.operate.save(prefix + "/editToDraft", $('#form-data-draftEdit').serialize());
            }
        }