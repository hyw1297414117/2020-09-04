$(function () {
    // var bussiTypeArr = [];   //存储下拉框内容 数组形式
    // var mySelect= $("#mySelect").mySelect({
    //     mult:true,//true为多选,false为单选
    //     option:[//选项数据
    //         {label:"海运",value:0},
    //         {label:"陆运",value:1},
    //         {label:"空运",value:2},
    //         {label:"咨询",value:3},
    //         {label:"其他",value:4}
    //     ],
    //     onChange:function(res){//选择框值变化返回结果
    //         //console.log(res)
    //     }
    // });
    // //点击 选择此规则之后 将tap页签和左边业务类型下拉框内容保持一致
    // $("#selChargeType").on('click',function(){
    //     var activeObjectIndex = $('.desc-list').find(".active").index();
    //     if($.inArray(activeObjectIndex,bussiTypeArr)<0){    //只添加不重复的数据
    //         bussiTypeArr.push(activeObjectIndex);
    //     }
    //     mySelect.setResult(bussiTypeArr);   //给左边业务类型下拉框 重新赋值
    // })

    //tap状态
    $('.industry').hover(function() {
        var i = $(this).index(); //当前索引
        $(this).addClass('active').siblings().removeClass('active');
        $('.case-panel').eq(i).addClass('active').siblings().removeClass('active');
    })

    //任务分类选择后在右侧任务类别详情中相应地添加删除
    $(document).on("click","input[name=transportRadios]",function(){
        var tabOrder = $(this).val();
        $(".trantabhead").removeClass('active').hide();
        $(".trantab").hide();
        $("#tabhead"+tabOrder).addClass('active').show();
        $(".tab-"+tabOrder).show().addClass('active').siblings().removeClass('active');
        // var id = $(this).attr('id'); //当前索引
        // if (this.checked==true){
        //     $.each($("input[name=transportRadios]"),function (i,v){
        //         if($(this).attr('id')!==id){
        //             $(this).prop('disabled');
        //         }
        //     })
        // }else{
        // }
    });
    $(document).on("click","input[name=taskCheckbox]",function(){
        var tabOrder = $(this).val();
        $(".trantabhead").removeClass('active');
        if (this.checked==true){
            $("#tabhead"+tabOrder).show();
            $(".tab-"+tabOrder).show().addClass('active').siblings().removeClass('active');
        }else{
            $("#tabhead"+tabOrder).hide();
            $(".tab-"+tabOrder).hide();
        }
    })

    //保存
    $(document).on("click","#batchImport",function(){
        var requestParams = {};
        var basicData = $("#form-data-add").serializeObject(); //自动将form表单封装成json
        //货运方式
        var transportType = basicData.transportRadios;

        // console.log(JSON.stringify(basicData));

        //组装参数“任务分类”
        basicData.taskType=transportType;
        requestParams.basicData=basicData;

        var transportData = $("#transportForm"+basicData.transportRadios).serializeObject();
        // console.log(JSON.stringify(transportData));
        transportData.type=basicData.transportRadios;
        requestParams.transportData=transportData;
        if(basicData.taskCheckbox !== undefined){
            if(basicData.taskCheckbox.includes("D")){
                basicData.taskType+="|D";
                var consultData = $("#consultform").serializeObject();
                requestParams.consultData=consultData;
            }
            if(basicData.taskCheckbox.includes("E")){
                basicData.taskType+="|E";
                var otherData = $("#otherform").serializeObject();
                requestParams.otherData=otherData;
            }

        }
        alert("将提交的数据："+JSON.stringify(requestParams));
        // $.ajax({
        //     type : "POST",
        //     url : "/agentModule/agentData/createTask",
        //     data : JSON.stringify(requestParams),
        //     error : function(request) {
        //         $.modal.alertError("系统错误");
        //     },
        //     success : function(data) {
        //         $.modal.alert(data);
        //     }
        // });
    })

    //通用方法：表单转对象
    $.fn.serializeObject = function()
    {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})
