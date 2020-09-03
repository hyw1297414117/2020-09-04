$(function () {
    $("#table_task").bootstrapTable(option);
});

var option = {
    url: '/agentModule/agentData/queryTask',         //请求后台的URL（*）
    method: 'post',                      //请求方式（*）
    toolbar: '#toolbar',                //工具按钮用哪个容器
    striped: true,                      //是否显示行间隔色
    cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true,                   //是否显示分页（*）
    sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
    pageNumber: 1,                       //初始化加载第一页，默认第一页
    pageSize: 10,                       //每页的记录行数（*）
    pageList: [10, 25, 50],        //可供选择的每页的行数（*）
    strictSearch: true,
    showColumns: true,                  //是否显示所有的列
    showRefresh: true,                  //是否显示刷新按钮
    minimumCountColumns: 2,             //最少允许的列数
    clickToSelect: true,                //是否启用点击选中行
    uniqueId: "id",                     //每一行的唯一标识，一般为主键列
    showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
    queryParams: assembleParams,
    columns: [{
        checkbox: true
    }
    , {
        field: 'impTaskBasic.id',
        visible: false
    }
    , {
        field: 'impTaskBasic.taskNumber',
        title: '任务号'
    }
    , {
        field: 'impTaskBasic.businessType',
        title: '业务类型',
        formatter: function(value, row, index){
            var businessType;
            switch (value){
                case "0":
                    businessType="进口";
                    break;
                case "1":
                    businessType="出口";
                    break;
                case "2":
                    businessType="国内";
                    break;
                case "3":
                    businessType="其他";
                    break;
            }
            return businessType;
        }
    }
    , {
        field: 'impTaskBasic.taskType',
        title: '任务分类',
        formatter: function(value, row, index){
            var typeArr = [];
            var typeMarkArr = value.split('|');
            $.each(typeMarkArr,function(index,value){
                switch (value){
                    case "A":
                        typeArr.push("空运");
                        break;
                    case "B":
                        typeArr.push("海运");
                        break;
                    case "C":
                        typeArr.push("陆运");
                        break;
                    case "D":
                        typeArr.push("咨询");
                        break;
                    case "E":
                        typeArr.push("其他");
                        break;
                }
            });
            return typeArr.join("|");
        }
    }
    , {
        field: 'impMainnoTaskno.mainOrderNo',
        title: '主单号'
    }
    , {
        field: 'impTaskBasic.priority',
        title: '优先级',
        formatter: function(value, row, index){
            var priority;
            switch (value){
                case "0":
                    priority="紧急";
                    break;
                case "1":
                    priority="优质";
                    break;
                case "2":
                    priority="标准";
                    break;
                case "3":
                    priority="经济";
                    break;
            }
            return priority;
        }
    }
    , {
        field: 'impTaskBasic.taskOrigin',
        title: '任务起始地'
    }
    , {
        field: 'impTaskBasic.taskDestination',
        title: '任务目的地'
    }
    , {
        field: 'impTaskBasic.tariff',
        title: '收费标准'
    }
    , {
        field: 'impTaskBasic.inserttime',
        title: '插入时间'
    }
    , {
            title: '操作',
            align: 'center',
            formatter: function (value, row, index) {
                var actions = [];
                actions.push('<a class="btn btn-success btn-xs '  + '" href="javascript:void(0)" onclick="export(\'' + row.id + '\')"><i class="fa fa-edit"></i>导出</a> ');
                actions.push('<a class="btn btn-danger btn-xs '  + '" href="javascript:void(0)" onclick="revokeTask(\'' + row.impTaskBasic.id + '\')"><i class="fa fa-remove"></i>撤销</a> ');
                return actions.join('');
            }
    }
    ]

};
function assembleParams(params) {
    var dataMap = {};
    dataMap.taskNumber=$("#search_taskNumber").val();
    dataMap.businessType=$("#search_businessType").val();
    dataMap.taskType=($("#search_taskType").val()==null)?null:$("#search_taskType").val().join("|");
    dataMap.priority=$("#search_priority").val();
    dataMap.mainOrderNo=$("#search_mainOrderNo").val();
    dataMap.taskOrigin=$("#search_taskOrigin").val();
    dataMap.taskDestination=$("#search_taskDestination").val();
    dataMap.inserttimeStart=$("#search_inserttime_start").val();
    dataMap.inserttimeEnd=$("#search_inserttime_end").val();
    var reqParams = {
        pageSize : params.limit,//分页的话，pageSize和pageNum两个参数必不可少！
        pageNum: params.offset / params.limit + 1,
        offset: params.offset,
        isAsc:true,
        impParams: dataMap
    };
    return reqParams;
}
/**
 * 根据当前条件刷新列表
 * */
function searchTask() {
    $("#table_task").bootstrapTable("refresh");
}
/**
 * 撤销任务
 * */
function revokeTask(taskId) {
    var revokeTaskIds = new Array();
    if(taskId===null||taskId===undefined){
        var rows= $("#table_task").bootstrapTable('getSelections');
        $.each(rows,function(index,value){
            revokeTaskIds.push(value.impTaskBasic.id);
        });
    }else {
        revokeTaskIds.push(taskId);
    }

    $.ajax({
        type: "POST",
        url: "/agentModule/agentData/revokeTask",
        data: {
            "revokeTaskIds":revokeTaskIds
        },
        datatype: "json",
        success: function (data) {
            if(data.code==0){
                layer.msg('任务已撤销！', {
                    icon: 1,
                    time: 1000 //2秒关闭（如果不配置，默认是3秒）
                }, function(){
                    $("#table_task").bootstrapTable("refresh");
                });
            }else {
                layer.msg('撤销任务失败0！', {icon: 1, time: 1000});
            }

        },
        error: function (request) {
            layer.msg('撤销任务失败1！', {icon: 1, time: 1000});
        }
    });
}
