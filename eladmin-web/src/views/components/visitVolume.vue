<template>
  <div class="visit">
    <Card>
      <p slot="title" class="card-title">
        <Icon type="md-map" style="margin-right:5px" />每日申报统计
      </p>
      <div slot="extra">
        <DatePicker type="daterange" placeholder="选择日期范围" style="width: 250px" />
      </div>
      <div style="height:273px;">
        <apexchart type="bar" height="280" :options="chartOptions" :series="series" />
      </div>
    </Card>
  </div>
</template>

<script>
	//	import echarts from "echarts";
	var zh = require('../../libs/zh.json');
	export default {
		name: 'VisitVolume',
		data() {
			return {
				series: [{
					name: '',
					data: []
				}],
				chartOptions: {
					colors: ['#398af7', '#63a3f9', '#b3d1fd'],
					chart: {
						stacked: true,
						toolbar: {
							show: false
						},
						zoom: {
							enabled: false
						},
						locales: [zh],
						defaultLocale: 'zh'
					},
					plotOptions: {
						bar: {
							columnWidth: '40%',
							horizontal: false
						}
					},
					xaxis: {
						type: 'datetime',
						labels: {
							datetimeFormatter: {
								year: 'yyyy年',
								month: 'yyyy年MMM',
								day: 'MMMdd日'
							}
						},
						categories: [
							'2019-11-01',
							'2019-11-02',
							'2019-11-03',
							'2019-11-04',
							'2019-11-05',
							'2019-11-06',
							'2019-11-07',
							'2019-11-08',
							'2019-11-09',
							'2019-11-10',
							'2019-11-11',
							'2019-11-12',
							'2019-11-13',
							'2019-11-14'
						]
					},
					legend: {
						show: false
					},
					dataLabels: {
						enabled: false
					},
					tooltip: {
						enabled: true,
						x: {
							show: true,
							format: 'MMM dd日'
						}
					}
				}
			};
		},
		mounted() {
			this.init();
		},
		methods: {
			init() {
				this.series = [{
						name: '申报对象',
						data: [44, 55, 41, 67, 22, 43, 27, 45, 15, 44, 30, 44, 30, 15]
					},
					{
						name: '申请资产',
						data: [13, 23, 20, 8, 13, 27, 23, 20, 8, 13, 27, 13, 27, 50]
					},
					{
						name: '申请补贴金额',
						data: [11, 17, 15, 15, 21, 14, 17, 15, 15, 21, 14, 21, 14, 35]
					}
				];
			}
		}
	};
</script>
<style lang="less">
	.visit {
		.ivu-card-head {
			border-bottom: none !important;
		}
	}
</style>
