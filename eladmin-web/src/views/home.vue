<style lang='less'>
	@import './home.less';
	/*	@import '../../styles/common.less';*/
</style>

<template>
  <div>
    <div v-show="true" class="home">
      <Row :gutter="10">
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
          <info-card
            id-name="user_created_count"
            :end-val="count.createUser"
            :icon-type="sbdx"
            color="#2d8cf0"
            intro-text="申报对象总量"
          />
          </Col>
          <Col :sm="24" :md="12" :lg="6" :style="{marginBottom: '10px'}" />
          <info-card
            id-name="visit_count"
            :end-val="count.visit"
            :icon-type="fc"
            color="#64d572"
            :icon-size="50"
            intro-text="申报资产总量"
          />
          </Col>
          <Col :sm="24" :md="12" :lg="6" :style="{marginBottom: '10px'}" />
          <info-card
            id-name="collection_count"
            :end-val="count.collection"
            :icon-type="sbzj"
            color="#ffd572"
            intro-text="申请资金总量(元)"
          />
          </Col>
          <Col :sm="24" :md="12" :lg="6" :style="{marginBottom: '10px'}" />
          <info-card
            id-name="transfer_count"
            :end-val="count.transfer"
            :icon-type="ffzj"
            color="#f25e43"
            intro-text="补贴发放总量(元)"
          />
          </Col>
        </Row>

        </Col>
      </Row>
      <Row :gutter="10">
        <Col :lg="24" :xl="16" :style="{marginBottom: '10px'}" />
        <visit-volume />
        </Col>
        <Col :lg="24" :xl="8" :style="{marginBottom: '10px'}" />
        <visit-separation />
        </Col>
      </Row>
      <Row :gutter="10">
        <Col :lg="16" :xl="8" :style="{marginBottom: '10px'}" />
        <radar-chart />
        </Col>
        <Col :lg="16" :xl="8" :style="{marginBottom: '10px'}" />
        <pie-chart />
        </Col>
        <Col :lg="16" :xl="8" :style="{marginBottom: '10px'}" />
        <bar-chart />
        </Col>
      </Row>
    </div>

  </div>
</template>

<script>
	import visitVolume from './components/visitVolume.vue';
	import visitSeparation from './components/visitSeparation.vue';
	import InfoCard from './components/infoCard.vue';
	import RadarChart from '@/components/Echarts/RadarChart'
  import PieChart from '@/components/Echarts/PieChart'
  import BarChart from '@/components/Echarts/BarChart'
	import show from './show.vue';
	import Cookies from 'js-cookie';
	import sbdx from './img/0111.png';
	import ffzj from './img/ffzj.png';
  import sbzj from './img/sbzj.png';
  import fc from './img/fc.png';
	export default {
		name: 'Home',
		components: {
			visitVolume,
			visitSeparation,
			show,
			InfoCard,
			RadarChart,
      PieChart,
      BarChart
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
				username: '',
				sbdx: sbdx,
				ffzj: ffzj,
				sbzj: sbzj,
				fc: fc
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
