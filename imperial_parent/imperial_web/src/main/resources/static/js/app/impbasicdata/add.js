		var prefix = ctx + "shipperModule/impBasicData"
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
                        	"tackingNumber1":function(){return $("#tackingNumber1").val().trim()}
                            ,"draftFlag":0
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