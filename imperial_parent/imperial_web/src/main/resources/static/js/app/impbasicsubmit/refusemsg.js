$(function() {
    var tackingNumber1 = $("#tackingNumber1").val();
    var dataMap = {};
    dataMap.tackingNumber1 = tackingNumber1;
    var options = {
        	id:"refuseMsgTable",
            url: "/shipperModule/impBasicDataSubmit/orderCheckRefuseMsgList",
            modalName: "驳回理由详情",
            impParams: queryParams(dataMap),
            columns: [
            {field: 'id',title: 'null',visible: false},
            {field: 'orderNumber',title: '订单编号'},
            {field: 'checker',title: '审核人'},
            {field: 'opinionType',title: '驳回类型'},
            {field: 'checkOpinion',title: '驳回理由'},
            {field: 'insertTime',title: '驳回时间'}]
        };
        $.table.init(options);
});

