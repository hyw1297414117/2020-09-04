/**
 * 初始化 BootStrap Table 的封装
 *
 * 约定：toolbar的id为 (bstableId + "Toolbar")
 *
 * @author cyf
 */
(function () {
    var BSTable = function (bstableId, url, columns) {
        this.btInstance = null;					//jquery和BootStrapTable绑定的对象
        this.bstableId = bstableId;
        this.url = url;
        this.method = "post";
        this.paginationType = "server";			//默认分页方式是服务器分页,可选项"client"
        this.toolbarId = bstableId + "Toolbar";
        this.columns = columns;
        this.data = {};
        this.pagination = true;
        this.queryParams = {}; // 向后台传递的自定义参数
        this.showColumns = true; 
        this.showRefresh = true; 
        this.showToggle = true;
        this.sortName = undefined; 
        this.sortOrder = "desc"; 
        this.search = false;      		//是否显示表格搜索，此搜索是客户端搜索，不会进服务端
        this.detailFormatter = "";//折叠展开显示内容function
        this.detailView = false;//是否显示折叠
        this.pageSize = 14;//每页记录行数
        this.pageList=[5,14,50,100];//可供选择的每页的行数（*）
        this.fixedColumns = false;//是否固定列
        this.fixedNumber = 1;//固定前几列fixedColumns为true时有效
    	this.height = 0;						//默认表格高度665
        this.subHeight = 0;
        this.pagination=true;
        this.rowStyle = function(row,index){	
        		return {css:{}}
        }
    };

    BSTable.prototype = {
        /**
         * 初始化bootstrap table
         */
        init: function () {
        	if(this.height==0){
            	this.autoHeight();
        	}
            var tableId = this.bstableId;
            var _queryParams = this.queryParams;
            this.btInstance =
                $('#' + tableId).impBootstrapTable({
                    contentType: "application/x-www-form-urlencoded",
                    autoSizeColumn:true,
                    url: this.url,				//请求地址
                    method: this.method,		//ajax方式,post还是get
                    ajaxOptions: {				//ajax请求的附带参数
                        data: this.data
                    },
                    detailFormatter: this.detailFormatter,//折叠展开显示内容function
                    detailView: this.detailView,//是否显示折叠
                    toolbar: "#" + this.toolbarId,//顶部工具条
                    striped: true,     			//是否显示行间隔色
                    cache: false,      			//是否使用缓存,默认为true
                    pagination: this.pagination,     		//是否显示分页（*）
                    sortName: this.sortName,      		//排序字段
                    sortable: true,      		//是否启用排序
                    sortOrder: this.sortOrder,   	//排序方式
                    pageNumber: 1,      			//初始化加载第一页，默认第一页
                    pageSize: this.pageSize,      			//每页的记录行数（*）
                    pageList: this.pageList,  	//可供选择的每页的行数（*）
                    paginationHAlign: 'right', //right, left
                    paginationVAlign: 'bottom', //bottom, top, both
                    paginationDetailHAlign: 'left', //right, left
                    paginationPreText: '上一页',
                    paginationNextText: '下一页',
                    queryParamsType: 'limit', 	//默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
                    queryParams: function (param) {
                    	return $.extend(_queryParams, param);
                    }, // 向后台传递的自定义参数
                    sidePagination: this.paginationType,   //分页方式：client客户端分页，server服务端分页（*）
                    search: this.search,      		//是否显示表格搜索，此搜索是客户端搜索，不会进服务端
                    strictSearch: true,			//设置为 true启用 全匹配搜索，否则为模糊搜索
                    showColumns: this.showColumns,     		//是否显示所有的列
                    showRefresh: this.showRefresh,     		//是否显示刷新按钮
                    showToggle: this.showToggle,                   //是否显示详细视图和列表视图的切换按钮
                    minimumCountColumns: 2,    	//最少允许的列数
                    clickToSelect: true,    	//是否启用点击选中行
                    searchOnEnterKey: true,		//设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
                    columns: this.columns,		//列数组
                    pagination: this.pagination,	//是否显示分页条
                    height: this.height,
                    rowStyle:this.rowStyle,
                    fixedColumns: this.fixedColumns,//是否固定列
                    fixedNumber: this.fixedNumber,//固定前几列
                    /*icons: {
                        refresh: 'glyphicon-repeat',
                        toggle: 'glyphicon-list-alt',
                        columns: 'glyphicon-list'
                    },
                    iconSize: 'outline',*/
                   onLoadSuccess: function(){  //加载成功时执行
                       
                   	},
                   	onPostBody: function(){  //当<tbody></tbody>中的内容被加载完后或者在你所用的DOM中有定义则被触发
                    	$("#" + tableId+"> tbody td").each(function(index){
                    		$(this).attr("title",$(this).text());
                    	});
                   	}
                });
            return this;
        },
        autoHeight:function(){
        	var t_top = $('#'+this.bstableId).offset().top;
        	var b_height = document.documentElement.clientHeight;
        	this.height = b_height-t_top+18+this.subHeight;
        },
        setSubHeight:function(height){
        	this.subHeight = height;
        },
        /**
         * 是否显示表格搜索，此搜索是客户端搜索，不会进服务端
         * @param param
         */
        setSearch: function (search) {
        	this.search = search;
        },
        /**
         * 是否显示刷新按钮
         * @param param
         */
        setShowRefresh: function (showRefresh) {
        	this.showRefresh = showRefresh;
        },
        /**
         * 是否显示所有的列
         * @param param
         */
        setShowColumns: function (showColumns) {
        	this.showColumns = showColumns;
        },
        /**
         * 是否显示详细视图和列表视图的切换按钮
         * @param param
         */
        setShowToggle: function (showToggle) {
        	this.showToggle = showToggle;
        },
        /**
        /**
         * 向后台传递的自定义参数
         * @param param
         */
        setQueryParams: function (param) {
        	this.queryParams = param;
        },
        /**
         * 设置分页方式：server 或者 client
         */
        setPaginationType: function (type) {
            this.paginationType = type;
        },
        
        /**
         * 设置分页方式
         */
        setPagination: function (pagination) {
            this.pagination = pagination;
        },
        /**
         * 设置每页显示的条数
         */
        setPageSize: function (pageSize) {
            this.pageSize = pageSize;
        },
        /**
         * 设置可供选择的每页的行数
         */
        setPageList: function (pageList) {
            this.pageList = pageList;
        },
        /**
         * 设置默认排序字段
         */
        setSortName: function (sortName) {
            this.sortName = sortName;
        },
		setRowStyle: function (rowStyle) {
            this.rowStyle = rowStyle;
        },
        /**
         * 是否显示分页
         */
        setPagination: function (pagination) {
        	this.pagination = pagination;
        },
        /**
         * 设置ajax post请求时候附带的参数
         */
        set: function (key, value) {
            if (typeof key == "object") {
                for (var i in key) {
                    if (typeof i == "function")
                        continue;
                    this.data[i] = key[i];
                }
            } else {
                this.data[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
            }
            return this;
        },

        /**
         * 设置ajax post请求时候附带的参数
         */
        setData: function (data) {
            this.data = data;
            return this;
        },

        /**
         * 清空ajax post请求参数
         */
        clear: function () {
            this.data = {};
            return this;
        },

        /**
         * 刷新 bootstrap 表格
         * Refresh the remote server data,
         * you can set {silent: true} to refresh the data silently,
         * and set {url: newUrl} to change the url.
         * To supply query params specific to this request, set {query: {foo: 'bar'}}
         */
        refresh: function (parms) {
            if (typeof parms != "undefined") {
                this.btInstance.impBootstrapTable('refresh', parms);
            } else {
                this.btInstance.impBootstrapTable('refresh');
            }
        },
        /**
         * 设置是否展开折叠
         */
        setDetailView:function(flag){
        	this.detailView = flag;
        	return this;
        },
        setDetailFormatter : function(formatter){
        	this.detailFormatter = formatter;
        	return this;
        },
        
        /**
         * 设置表格是否固定列
         */
        setFixedColumns : function(fixedColumns){
        	this.fixedColumns = fixedColumns;
        	return this;
        },
        
        /**
         * 设置表格固定前几列
         */
        setFixedNumber : function(fixedNumber){
        	this.fixedNumber = fixedNumber;
        	return this;
        },
        
        setHeight:function(height){
        	this.height = height;
        	return this;
        },
        setSort: function (sortName,sortOrder) {
            this.sortName = sortName;
            this.sortOrder = sortOrder?sortOrder:"desc";
        }
    };
    window.BSTable = BSTable;

}());

