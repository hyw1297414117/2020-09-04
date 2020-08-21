
$(function() {
    validateRule();
    $('.imgcode').click(function() {
		var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
		$(".imgcode").attr("src", url);
	});
});

$.validator.setDefaults({
    submitHandler: function() {
    	register();
    }
});

function register() {
	$.modal.loading($("#btnSubmit").data("loading"));
	var username = $.common.trim($("input[name='username']").val());
    var loginName = $.common.trim($("input[name='loginName']").val());
    var email = $.common.trim($("input[name='email']").val());
    var phonenumber = $.common.trim($("input[name='phonenumber']").val());
    var password = $.common.trim($("input[name='password']").val());
    var validateCode = $("input[name='validateCode']").val();
    $.ajax({
        type: "post",
        url: ctx + "register",
        data: {
            "userName": username,
            "password": password,
            "loginName": loginName,
            "email": email,
            "phonenumber": phonenumber,
            "validateCode": validateCode
        },
        success: function(r) {
            if (r.code == 0) {
            	layer.alert("<font color='red'>恭喜你，您的账号 " + username + " 注册成功！</font>", {
        	        icon: 1,
        	        title: "系统提示"
        	    },
        	    function(index) {
        	        //关闭弹窗
        	        layer.close(index);
        	        location.href = ctx + 'register';
        	    });
            } else {
            	$.modal.closeLoading();
            	$('.imgcode').click();
            	$(".code").val("");
            	$.modal.msg(r.msg);
            }
        }
    });
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
    //自定义方法，完成手机号码的验证
    //name:自定义方法的名称，method：函数体, message:错误消息
    $.validator.addMethod("phonenumber", function(value, element, param){
        //方法中又有三个参数:value:被验证的值， element:当前验证的dom对象，param:参数(多个即是数组)
        //alert(value + "," + $(element).val() + "," + param[0] + "," + param[1]);
        return new RegExp(/1(3|5|8)\d{9}$/).test(value);
    }, "手机号码不正确必须是13/15/18开头");
    $("#registerForm").validate({
        rules: {
            username: {
                required: true,
                minlength: 2
            },
            loginName: {
                required: true,
                minlength: 2
            },
            email: {
                required: true,
                minlength: 2,
                email:true
            },
            phonenumber: {
                required: true,
                minlength: 11,
                phonenumber:true
            },
            password: {
                required: true,
                minlength: 5
            },
            confirmPassword: {
                required: true,
                equalTo: "[name='password']"
            }
        },
        messages: {

            username: {
                required: icon + "请输入您的真实姓名",
                minlength: icon + "真实姓名不能小于2个字符",
            },
            loginName: {
                required: icon + "请输入您的登录名",
                minlength: icon + "登录名不能小于2个字符"
            },
            email: {
                required: icon + "请输入您的邮箱",
                minlength: icon + "邮箱不能小于2个字符"
            },
            phonenumber: {
                required: icon + "请输入您的手机号码",
                minlength: icon + "手机号码不能小于11个字符"
            },
            password: {
            	required: icon + "请输入您的密码",
                minlength: icon + "密码不能小于5个字符",
            },
            confirmPassword: {
                required: icon + "请再次输入您的密码",
                equalTo: icon + "两次密码输入不一致"
            }
        }
    })
}

