var prefix = ctx + "dict/dictitemrule";
$(function() {
    var options = {
        url: prefix + "/list",
        createUrl: prefix + "/add",
        updateUrl: prefix + "/edit/{id}",
        removeUrl: prefix + "/remove",
        exportUrl: prefix + "/export",
        modalName: "字典条目规则操作",
        columns: [{
            checkbox: true
        },
        {
            field: 'id',
            title: 'null',
            visible: false
        },
        {
            field: 'itemCode',
            title: '字典条目code'
        },
        {
            field: 'touchField',
            title: '条目触发依托字段'
        },
        {
            field: 'keyWords',
            title: '条目触发关键词'
        },
        {
            field: 'status',
            title: '状态 0无效1有效'
        }]
    };
    $.table.init(options);
});