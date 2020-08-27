$(function() {
    var tackingNumber1 = $("#tackingNumber1").val();
    var dataMap = {};
    dataMap.tackingNumber1 = tackingNumber1;
    var options = {
        	id:"refuseMsgTable",
            url: "/shipperModule/impBasicDataSubmit/orderCheckRefuseMsgList",
            modalName: Detail,
            impParams: queryParams(dataMap),
            columns: [
            {field: 'id',title: 'null',visible: false},
            {field: 'orderNumber',title: orderNumber},
            {field: 'checker',title: Reviewer},
            {field: 'opinionType',title: Rejectiontype},
            {field: 'checkOpinion',title: Reasonsforrejection},
            {field: 'insertTime',title: Timeofrejection}]
        };
        $.table.init(options);
});

