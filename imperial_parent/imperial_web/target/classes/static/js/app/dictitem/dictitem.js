var prefix = ctx + "dict/dictitem";
$(function() {
    var options = {
        url: prefix + "/list",
        createUrl: prefix + "/add",
        updateUrl: prefix + "/edit/{id}",
        removeUrl: prefix + "/remove",
        exportUrl: prefix + "/export",
        modalName: "字典条目操作",
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
            title: '条目code'
        },
        {
            field: 'itemContent',
            title: '条目内容'
        },
        {
            field: 'status',
            title: '条目状态 0无效 1有效'
        }]
    };
    $.table.init(options);
});