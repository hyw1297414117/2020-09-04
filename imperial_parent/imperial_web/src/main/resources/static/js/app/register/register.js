
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
            	layer.alert("<font color='red'>恭喜你，您的账号 " + username + " 注册成功！请前往您的邮箱查收激活邮件，进行<strong>账号激活！</strong></font>", {
        	        icon: 1,
        	        title: "系统提示"
        	    },
        	    function(index) {
        	        //关闭弹窗
        	        layer.close(index);
        	        location.href = ctx + 'login';
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
    }, phonetyep);
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
                required: icon + usernameisnull,
                minlength: icon + usernamelength,
            },
            loginName: {
                required: icon +loginname ,
                minlength: icon +loginnamelength
            },
            email: {
                required: icon +useremil ,
                minlength: icon +useremillength
            },
            phonenumber: {
                required: icon + userphone,
                minlength: icon +userphonelength
            },
            password: {
                required: icon +userpassword,
                minlength: icon + userpasswordlength,
            },
            confirmPassword: {
                required: icon + userrepassword,
                equalTo: icon +userpasswordeq
            }
        }
    })
}

