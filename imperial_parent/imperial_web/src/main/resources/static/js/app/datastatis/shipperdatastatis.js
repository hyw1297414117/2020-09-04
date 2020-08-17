
$(function() {
	 // 基于准备好的dom，初始化echarts实例
    var bingChart = echarts.init(document.getElementById('bing'));
	bingoption = {
		    title: {
		        text: '当前账户订单数据展示',
		        left: 'center'
		    },
		    tooltip: {
		        trigger: 'item',
		        formatter: '{a} <br/>{b} : {c} ({d}%)'
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: ['受理成功', '驳回订单', '草稿数量', '完成订单']
		    },
		    series: [
		        {
		            name: '访问来源',
		            type: 'pie',
		            radius: '55%',
		            center: ['50%', '60%'],
		            data: [
		                {value: 335, name: '受理成功'},
		                {value: 100, name: '驳回订单'},
		                {value: 34, name: '草稿数量'},
		                {value: 135, name: '完成订单'}
		            ],
		            emphasis: {
		                itemStyle: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
	// 使用刚指定的配置项和数据显示图表。
	bingChart.setOption(bingoption);
	
	
	var zhuChart = echarts.init(document.getElementById('zhu'));
	zhuoption = {
		    title: {
		        text: '月度趋势图'
		    },
		    tooltip: {},
		    legend: {
		        data:['数量']
		    },
		    xAxis: {
		        data: ["01","02","03","04","05","06"]
		    },
		    yAxis: {},
		    series: [{
		        name: '数量',
		        type: 'bar',
		        data: [5, 20, 36, 10, 10, 20],
		        itemStyle: {
		            normal: {
		　　　　　　　　//这里是重点
		                color: function(params) {
		                	//注意，如果颜色太少的话，后面颜色不会自动循环，最好多定义几个颜色
		                    var colorList = ['#c23531','#2f4554','#c23531','#2f4554','#c23531','#2f4554'];
		                    return colorList[params.dataIndex]
		                }
		            }
		        }
		    }]
		};
	zhuChart.setOption(zhuoption);
	
	
});
