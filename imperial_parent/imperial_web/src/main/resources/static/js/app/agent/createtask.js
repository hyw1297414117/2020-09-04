
$(function () {
    validateRule()
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
    $('.industry').hover(function () {
        var i = $(this).index(); //当前索引
        $(this).addClass('active').siblings().removeClass('active');
        $('.case-panel').eq(i).addClass('active').siblings().removeClass('active');
    })

    //任务分类选择后在右侧任务类别详情中相应地添加删除
    $(document).on("click", "input[name=transportRadios]", function () {
        var tabOrder = $(this).val();
        if ($(this).prop("checked")) {
            //单选
            $(':checkbox[name=transportRadios]').each(function () {
                $(this).prop("checked", false);
            });
            $(this).prop("checked", true);
            //切换右侧标签页
            $(".trantabhead").removeClass('active').hide();
            $(".trantab").hide();
            $("#tabhead" + tabOrder).addClass('active').show();
            $(".tab-" + tabOrder).show().addClass('active').siblings().removeClass('active');
        } else {
            $(':checkbox[name=transportRadios]').each(function () {
                $(this).prop("checked", false);
            });
            $(".trantabhead").removeClass('active').hide();
            $(".trantab").hide();
        }
    });


    $(document).on("click", "input[name=taskCheckbox]", function () {
        var tabOrder = $(this).val();
        $(".trantabhead").removeClass('active');
        if ($(this).prop("checked")) {
            $("#tabhead" + tabOrder).show();
            $(".tab-" + tabOrder).show().addClass('active').siblings().removeClass('active');
        } else {
            $("#tabhead" + tabOrder).hide();
            $(".tab-" + tabOrder).hide();
        }
    })

    //保存
    $(document).on("click", "#batchImport", function () {
        if(!$("#taskForm").valid()){
            layer.msg("请完善任务基本信息！");
            return false;
        }
        var requestParams = {};

        //任务基本信息表单转对象
        var basicData = $("#taskForm").serializeObject(); //任务基本信息表单转对象
        //组装参数“任务分类”
        basicData.taskType = basicData.transportRadios;

        requestParams.basicData = basicData;
        requestParams.mainOrderNo = $("#mainOrderNo").val();

        if(basicData.transportRadios!== undefined){
            if(!$("#transportForm" + basicData.transportRadios).valid()){
                layer.msg("请完善运单信息！");
                return false;
            }
            //运单信息表单转对象
            requestParams.transportData = $("#transportForm" + basicData.transportRadios).serializeObject();
        }

        if (basicData.taskCheckbox !== undefined) {
            if (basicData.taskCheckbox.includes("D")) {
                if(!$("#consultForm").valid()){
                    layer.msg("请完善运单信息！");
                    return false;
                }
                basicData.taskType += "|D";
                //咨询信息表单转对象
                requestParams.consultData = $("#consultForm").serializeObject();
            }
            if (basicData.taskCheckbox.includes("E")) {
                basicData.taskType += "|E";
                requestParams.otherData = $("#otherForm").serializeObject();
            }

        }
        $.ajax({
            type: "POST",
            contentType: "application/json;charset=utf-8",
            url: "/agentModule/agentData/createTask",
            data: JSON.stringify(requestParams),
            datatype: "json",
            success: function (data) {
                layer.msg('创建任务成功', {
                    icon: 1,
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                }, function(){
                    location.reload();
                });
            },
            error: function (request) {
                layer.msg("创建任务异常", {icon: 1, time: 1000});
            }
        });
    })

    //通用方法：表单转对象
    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
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

    $("#impbasicDataTable").bootstrapTable({
        url: "/shipperModule/impBasicData/getMainOrderNoList",
        method: 'post',
        contentType: "application/x-www-form-urlencoded",   // 编码类型
        sidePagination: 'server',
        striped: false,  //隔行变色
        uniqueId: "id",
        pagination: true,//是否分页
        queryParamsType: 'limit',
        pageSize: 10,
        pageNumber: 1, //初始化加载第一页
        pageList: [10, 20],
        isAsc: true,
        singleSelect: true,//单行选择单行,设置为true将禁止多选
        columns: [
            {
                checkbox: true
            },
            {
                field: 'id', title: 'null',visible: false
            },
            {
                field: 'mainOrderNo', title: MainOrderNo
            }
        ],
        queryParams: function (params) {
            var dataMap = {};
            dataMap.mainOrderNo=$("#search_mainOrderNo").val();
            dataMap.tackingNumber1=$("#search_tackingNumber1").val();
            dataMap.bagNumber=$("#search_bagNumber").val();
            dataMap.shipperName=$("#search_shipperName").val();
            // dataMap.customer=$("#search_customer").val();
            dataMap.consigneeName=$("#search_consigneeName").val();
            dataMap.incoterms=$("#search_typeSelect").val();
            var pageParams = initPageParams(params);//分页参数的初始化
            var reqParams = {
                pageSize : params.limit,//分页的话，pageSize和pageNum两个参数必不可少！
                pageNum: params.offset / params.limit + 1,
                offset: params.offset,
                isAsc:true,
                impParams:queryParams(dataMap)
            };
            return reqParams;
        }
    })
});

/**
 * 搜索方法
 * */
function searchMainOrder(){
    $("#impbasicDataTable").bootstrapTable('refresh');
}
/**
 * 关联主单
 * */
function bindMainOrder(){
    var rows= $("#impbasicDataTable").bootstrapTable('getSelections');
    $("#mainOrderNo").val(rows[0].mainOrderNo);
    $("#mainOrderModal").modal("hide");
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#taskForm").validate({
        debug:true,
        rules: {
            taskOrigin: {
                required: true
            }
            ,taskDestination: {
                required: true
            }
            ,mainOrderNo: {
                required: true
            }
            ,tariff: {
                required: true
            }
        },
        messages: {
            taskOrigin: {
                required: icon + "起始地为空"
            }
            ,taskDestination: {
                required: icon + "目的地为空"
            }
            ,mainOrderNo: {
                required: icon + "主单为空"
            }
            ,tariff: {
                required: icon + "目的地为空"
            }
        }
    })

    $("#transportFormA").validate({
        debug:true,
        rules: {
            airMainOrderNo: {
                required: true
            }
            ,flightNumber: {
                required: true
            }
            ,chargedWeight: {
                required: true
            }
            ,actualWeight: {
                required: true
            }
            ,loadUnit: {
                required: true
            }
            ,loadAmount: {
                required: true
            }
        },
        messages: {
            airMainOrderNo: {
                required: icon + "起始地为空"
            }
            ,flightNumber: {
                required: icon + "目的地为空"
            }
            ,chargedWeight: {
                required: icon + "目的地为空"
            }
            ,actualWeight: {
                required: icon + "目的地为空"
            }
            ,loadUnit: {
                required: icon + "目的地为空"
            }
            ,loadAmount: {
                required: icon + "目的地为空"
            }
        }
    });

    $("#transportFormB").validate({
        debug:true,
        rules: {
            seaMainOrderNo: {
                required: true
            }
            ,containerNumber: {
                required: true
            }
            ,chargedWeight: {
                required: true
            }
            ,actualWeight: {
                required: true
            }
            ,loadUnit: {
                required: true
            }
            ,loadAmount: {
                required: true
            }
            ,volume: {
                required: true
            }
        },
        messages: {
            seaMainOrderNo: {
                required: icon + "起始地为空"
            }
            ,containerNumber: {
                required: icon + "目的地为空"
            }
            ,chargedWeight: {
                required: icon + "目的地为空"
            }
            ,actualWeight: {
                required: icon + "目的地为空"
            }
            ,loadUnit: {
                required: icon + "目的地为空"
            }
            ,loadAmount: {
                required: icon + "目的地为空"
            }
            ,volume: {
                required: icon + "目的地为空"
            }
        }
    });

    $("#transportFormC").validate({
        debug:true,
        rules: {
            roadMainOrderNo: {
                required: true
            }
            ,carrier: {
                required: true
            }
            ,chargedWeight: {
                required: true
            }
            ,actualWeight: {
                required: true
            }
            ,loadUnit: {
                required: true
            }
            ,loadAmount: {
                required: true
            }
            ,payType: {
                required: true
            }
        },
        messages: {
            roadMainOrderNo: {
                required: icon + "起始地为空"
            }
            ,carrier: {
                required: icon + "目的地为空"
            }
            ,chargedWeight: {
                required: icon + "目的地为空"
            }
            ,actualWeight: {
                required: icon + "目的地为空"
            }
            ,loadUnit: {
                required: icon + "目的地为空"
            }
            ,loadAmount: {
                required: icon + "目的地为空"
            }
            ,payType: {
                required: icon + "目的地为空"
            }
        }
    });
}