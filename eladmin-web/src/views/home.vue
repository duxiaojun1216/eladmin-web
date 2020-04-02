<style lang='less'>
  @import './home.less';
	/*	@import '../../styles/common.less';*/
</style>

<template>
  <div>
    <div v-show="true" class="home">
      <!-- <panel-group /> -->
      <Row :gutter="10">
        <Col :lg="24" :xl="12">
        <Row :gutter="20">
          <Col :lg="24" :xl="24" :style="{marginBottom: '10px'}">
          <Card>
            <Row type="flex" class="user-info cardRow">
              <Col span="8">
              <Row class-name="made-child-con-middle" type="flex" align="middle">
                <img class="avator-img" :src="user.avatar ? baseApi + '/avatar/' + user.avatar : Avatar" />
              </Row>
              </Col>
              <Col span="16" style="padding-left:6px;">
              <Row class-name="made-child-con-middle" type="flex" align="middle">
                <div>
                  <b class="card-user-info-name">{{username}}</b>
                  <p>泸州市置业补助平台欢迎您使用</p>
                  <p>2020年3月31日</p>
                </div>
              </Row>
              </Col>
            </Row>
            <div class="line-gray" />
            <Row class="margin-top-8">
              <Col span="8">
              <p class="notwrap">:</p>
              </Col>
              <Col span="16" class="padding-left-8">
              </Col>
            </Row>
          </Card>
          </Col>
        </Row>
        </Col>
        <!--<Col :lg="24" :xl="16">
        <Row :gutter="5">
          <Col :sm="24" :md="12" :lg="6" :style="{marginBottom: '10px'}">
          <info-card id-name="user_created_count" :end-val="count.createUser" :icon-type="sbdx" color="#2d8cf0"
            intro-text="申报对象总量(人次)" />
          </Col>
          <Col :sm="24" :md="12" :lg="6" :style="{marginBottom: '10px'}">
          <info-card id-name="visit_count" :end-val="count.visit" :icon-type="fc" color="#64d572" :icon-size="50"
            intro-text="申报资产总量(件/套)" />
          </Col>
          <Col :sm="24" :md="12" :lg="6" :style="{marginBottom: '10px'}">
          <info-card id-name="collection_count" :end-val="count.collection" :icon-type="sbzj" color="#ffd572"
            intro-text="申请资金总量(万元)" />
          </Col>
          <Col :sm="24" :md="12" :lg="6" :style="{marginBottom: '10px'}">
          <info-card id-name="transfer_count" :end-val="count.transfer" :icon-type="ffzj" color="#f25e43" intro-text="补贴发放总量(万元)" />
          </Col>

        </Row>

        </Col>-->

        <Col :sm="24" :md="12" :lg="6">
        <cardArea class="margin" />
        </Col>
        <Col :sm="24" :md="12" :lg="6">
        <cardAreaFill class="margin" />
        </Col>
        <!--<Col :sm="24" :md="12" :lg="6">
        <cardLine />
        </Col>-->
      </Row>

      <Row :gutter="10">
        <Col :lg="24" :xl="16" :style="{marginBottom: '10px'}">
        <visit-volume />
        </Col>
        <Col :lg="24" :xl="8" :style="{backgroundColor: '#fff'}">
        <Row type="flex" justify="center" align="middle" class="right progressItemRow">
          <div class="content">
            <progressItem value="50" title="江阳区" :data="50" color="#5089de" />
            <progressItem value="30" title="龙马潭区" :data="30" color="#23b397" />
            <progressItem value="20 " title="纳溪区" :data="20" color="#56c2d6" />
            <progressItem value="98,512" title="广州" :data="52" color="#f8cc6b" />
            <progressItem value="2,154" title="成都" :data="28" color="#f0643b" />
          </div>
        </Row>
        </Col>
      </Row>
      <Row :gutter="10">
        <Col :lg="16" :xl="8" :style="{marginBottom: '10px'}">
        <radar-chart />
        </Col>
        <Col :lg="16" :xl="8" :style="{marginBottom: '10px'}">
        <pie-chart />
        </Col>
        <Col :lg="16" :xl="8" :style="{marginBottom: '10px'}">
        <bar-chart />
        </Col>
      </Row>
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
  import sbdx from './img/0111.png';
  import ffzj from './img/ffzj.png';
  import sbzj from './img/sbzj.png';
  import fc from './img/fc.png';
  import cardArea from './components/cardArea.vue';
  import cardAreaFill from './components/cardAreaFill.vue';
  import {
    mapGetters
  } from 'vuex';
  import progressItem from "./components/progress";
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
      PanelGroup,
      cardArea,
      cardAreaFill,
      progressItem
    },
    data() {
      return {
        showVideo: false,
        count: {
          createUser: 496,
          visit: 500,
          collection: 105,
          transfer: 58
        },
        city: '泸州',
        username: '测试账号',
        sbdx: sbdx,
        ffzj: ffzj,
        sbzj: sbzj,
        fc: fc
      };
    },
    computed: {
      ...mapGetters([
        'sidebar',
        'device',
        'user',
        'baseApi'
      ]),
      show: {
        get() {
          return this.$store.state.settings.showSettings
        },
        set(val) {
          this.$store.dispatch('settings/changeSetting', {
            key: 'showSettings',
            value: val
          })
        }
      }
    },
    mounted() {
      this.init();
    },
    methods: {
      init() {
//      let userInfo = JSON.parse(Cookies.get('userInfo'));
//      this.username = userInfo.username;
//      ipInfo().then(res => {
//        if (res.success) {
//          this.city = res.result;
//        }
//      });
      }
    }
  };
</script>
