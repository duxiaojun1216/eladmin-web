<style lang='less'>
	@import './home.less';
	/*	@import '../../styles/common.less';*/
</style>

<template>
  <div>
    <div v-show="true" class="home">
      <panel-group />
      <!--<el-row :gutter="40" class="panel-group">
			    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
					<info-card id-name="user_created_count" :end-val="count.createUser" :icon-type="sbdx" color="#2d8cf0" intro-text="申报对象总量" />
			    </el-col>
			    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
					<info-card id-name="visit_count" :end-val="count.visit" :icon-type="fc" color="#64d572" :icon-size="50" intro-text="申报资产总量" />
			      </div>
			    </el-col>
			    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
					<info-card id-name="collection_count" :end-val="count.collection" :icon-type="sbzj" color="#ffd572" intro-text="申请资金总量(元)" />
			    </el-col>
			    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
					<info-card id-name="transfer_count" :end-val="count.transfer" :icon-type="ffzj" color="#f25e43" intro-text="补贴发放总量(元)" />
			    </el-col>
			</el-row>-->

      <!--<Row :gutter="10">
				<Col :lg="24" :xl="8" />
				<Row :gutter="5">
					<Col :lg="12" :xl="24" :style="{marginBottom: '10px'}" />
					<Card>
						<Row type="flex" class="user-info">
							<Col span="8" />
							<Row class-name="made-child-con-middle" type="flex" align="middle">
								<img class="avator-img" :src="avatarPath">
							</Row>
							</Col>
							<Col span="16" style="padding-left:6px;" />
							<Row class-name="made-child-con-middle" type="flex" align="middle">
								<div>
									<b class="card-user-info-name">{{ username }}</b>
									<p>置业补助平台欢迎您使用</p>
								</div>
							</Row>
							</Col>
						</Row>
						<div class="line-gray" />
					</Card>
					</Col>
				</Row>
				</Col>
				<Col :lg="24" :xl="16" />
				<Row :gutter="5">
					<Col :sm="24" :md="12" :lg="6" :style="{marginBottom: '10px'}" />

					</Col>
					<Col :sm="24" :md="12" :lg="6" :style="{marginBottom: '10px'}" />
					<info-card id-name="visit_count" :end-val="count.visit" :icon-type="fc" color="#64d572" :icon-size="50" intro-text="申报资产总量" />
					</Col>
					<Col :sm="24" :md="12" :lg="6" :style="{marginBottom: '10px'}" />
					<info-card id-name="collection_count" :end-val="count.collection" :icon-type="sbzj" color="#ffd572" intro-text="申请资金总量(元)" />
					</Col>
					<Col :sm="24" :md="12" :lg="6" :style="{marginBottom: '10px'}" />
					<info-card id-name="transfer_count" :end-val="count.transfer" :icon-type="ffzj" color="#f25e43" intro-text="补贴发放总量(元)" />
					</Col>
				</Row>

				</Col>
			</Row>-->
      <el-row :gutter="32">
        <el-col :xs="24" :sm="24" :lg="16">
          <div class="chart-wrapper">
            <visit-volume />
          </div>
        </el-col>
        <el-col :xs="24" :sm="24" :lg="8">
          <div class="chart-wrapper">
            <visit-separation />
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="32">
        <el-col :xs="24" :sm="24" :lg="8">
          <div class="chart-wrapper">
            <radar-chart />
          </div>
        </el-col>
        <el-col :xs="24" :sm="24" :lg="8">
          <div class="chart-wrapper">
            <pie-chart />
          </div>
        </el-col>
        <el-col :xs="24" :sm="24" :lg="8">
          <div class="chart-wrapper">
            <bar-chart />
          </div>
        </el-col>
      </el-row>
    </div>

  </div>
</template>

<script>
	import PanelGroup from './dashboard/PanelGroup'
	import visitVolume from './components/visitVolume.vue';
	import visitSeparation from './components/visitSeparation.vue';
	import InfoCard from './components/infoCard.vue';
	import RadarChart from '@/components/Echarts/RadarChart'
	import PieChart from '@/components/Echarts/PieChart'
	import BarChart from '@/components/Echarts/BarChart'
	import show from './show.vue';
	import Cookies from 'js-cookie';

	export default {
		name: 'Home',
		components: {
			visitVolume,
			visitSeparation,
			show,
			InfoCard,
			RadarChart,
			PieChart,
			BarChart,
			PanelGroup
		},
		data() {
			return {
				showVideo: false,
				count: {
					createUser: 496,
					visit: 500,
					collection: 2439305,
					transfer: 1903498
				},
				city: '',
				username: ''
			};
		},
		computed: {
			currNav() {
				return this.$store.state.app.currNav;
			},
			avatarPath() {
				return localStorage.avatorImgPath;
			}
		},
		mounted() {
			this.init();
		},
		methods: {
			init() {
				//				let userInfo = JSON.parse(Cookies.get('userInfo'));
				//				this.username = userInfo.username;
				//				ipInfo().then(res => {
				//					if(res.success) {
				//						this.city = res.result;
				//					}
				//				});
			}
		}
	};
</script>
