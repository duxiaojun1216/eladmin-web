<style lang="less">
  .form_item {
    margin-bottom: 24px;
    vertical-align: top;
    zoom: 1;
    width: 350px;
    float: left;
    margin-right: 50px;
  }

  .ivu-form .ivu-form-item-label {
    text-align: left;
    vertical-align: middle;
    float: left;
    font-size: 14px;
    color: #515a6e;
    line-height: 1;
    padding: 10px 12px 10px 0;
    box-sizing: border-box;
	width: 110px;
  }

  .header_p {
    display: inline-block;
    width: 100%;
    height: 40px !important;
    line-height: 40px !important;
    font-weight: bold !important;
    font-size: 25px !important;
    color: #17233d;
    font-weight: bold;
    font-weight: 500;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    text-align: center;
	margin: 20px auto;
  }

  .ivu-divider-horizontal.ivu-divider-with-text-left:before {
    top: 50%;
    width: 0.01%;
  }

  .ivu-divider-inner-text {
    display: table;
    white-space: nowrap;
    text-align: left;
    background: 0 0;
    font-weight: 500;
    color: #2d8cf0;;
    /* font-size: 18px; */
    font-weight: bold;
    /* margin: 16px 0; */
    padding: 0px;
  }

  .ivu-divider-horizontal {
    display: block;
    height: 1px;
    width: 760px;
    /* min-width: 100%; */
    margin: 24px 0;
    clear: both;
  }

  .ivu-picker-panel-body {
    width: 250px
  }

  .ivu-card-head {
    border-bottom: 1px solid #e8eaec;
    padding: 15px 0 8px 8px!important;
    line-height: 1;
    padding-bottom: 2px;
    width: 760px;
    margin: auto;
  }
  .m_table .ivu-form-item-content{
    margin-left: 0px!important;
  }
</style>
<template>
  <div class="search">
    <Card style="width: 970px;margin: auto;">
      <p slot="title" class="header_p">
        <span v-if="type==0">泸州市农民工进城置业补贴申请表</span>
        <span v-else-if="type==1">填报置业补贴申请表</span>
        <span v-else>查看置业补贴申请</span>
      </p>
      <p slot="title" style="text-align:left ;font-size:14px;width: 250px;">
        <span>编号：JYTY032020001</span>
      </p>
      <p slot="title" style="text-align: right;font-size:14px;width: 250px;float:right;">
        <span>日期：2020年4月3日</span>
      </p>
      <Row style="width: 800px;margin: auto;">
        <Form
          ref="form"
          :model="form"
          :label-width="110"
          :rules="formValidate"
          style="position:relative"
        >

          <FormItem class="form_item1" :label="label.name1"  width="100%" >
            <RadioGroup v-model="form.sfwt"   size="medium" style="width: 250px;"  @on-change="changeFromWT">
              <Radio
                v-for="item in isWT"
                :label="item.value">{{item.label}}</Radio>
            </RadioGroup>
          </FormItem>
          <Divider orientation="left" v-if="form.sfwt=='1'">①代办人信息</Divider>
          <FormItem class="form_item" label="代办企业" prop="cardId" v-if="form.sfwt=='1'">
            <Input v-model="form.cardId" style="width:250px"></Input>
          </FormItem>
          <FormItem class="form_item" label="代办人姓名" prop="tname" v-if="form.sfwt=='1'">
            <Input v-model="form.tname" style="width:250px"></Input>
          </FormItem>

          <FormItem class="form_item" label="代办人电话" prop="cardId" v-if="form.sfwt=='1'">
            <Input v-model="form.cardId" style="width:250px"></Input>
          </FormItem>

          <FormItem class="form_item" label="代办人身份证号" prop="phone"v-if="form.sfwt=='1'">
            <Input v-model="form.phone" style="width:250px"></Input>
          </FormItem>

         <!--<FormItem label="申请人" prop="name" v-if="type!=0&&type!=1">{{form.createBy}}</FormItem>-->
          <Divider orientation="left">②补助对象信息</Divider>
          <FormItem class="form_item" label="补助人姓名" prop="tname">
            <Input v-model="form.tname" style="width:250px"></Input>
          </FormItem>

          <FormItem class="form_item" label="身份证号" prop="cardId">
            <Input v-model="form.cardId" style="width:250px"></Input>
          </FormItem>
          <FormItem class="form_item1" label="对象类型" prop="sqrlx" width="100%">
            <RadioGroup v-model="form.sqrlx" filterable placeholder="请选择"   size="medium"  @on-change="changeFromRY">
              <Radio
                v-for="item in dict.peoson_type"
                :key="item.id"
                :label="item.value">{{item.label}}</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem class="form_item" label="联系电话" prop="phone">
            <Input v-model="form.phone" style="width:250px"></Input>
          </FormItem>
          <FormItem class="form_item" label="身份证地址" prop="hjdz" style="width:100%">
            <Input
              v-model="form.hjdz"
              placeholder="请输入详细情况"
              style="width: 650px"
            ></Input>
          </FormItem>
          <FormItem class="form_item" :label="label.name"  style="width:100%">
            <Input
              type="textarea"
              v-model="form.mark"
              :rows="3"
              placeholder="请输入详细情况"
              style="width: 650px"
            ></Input>
          </FormItem>
          <Divider orientation="left">③购置房产信息</Divider>
          <FormItem class="form_item1" label="补助类别" prop="fclx"  width="100%" label-width="130px">
            <RadioGroup v-model="form.fclx" filterable placeholder="请选择"   size="medium"   @on-change="changeFromFC" id="fclxRedio">
              <Radio
                v-for="item in dict.house_type"
                :key="item.id"
                :label="item.value">{{item.label}}</Radio>

            </RadioGroup>
          </FormItem>
          <!--新增-->
          <FormItem class="form_item1" label="房屋类型" prop="fclx" width="100%" label-width="100px">
            <RadioGroup v-model="form.hxlx" filterable placeholder="请选择"   size="medium"   @on-change="changeFromHX" id="hxlxRedio">
              <Radio
                v-for="item in dict.apartment_type"
                :key="item.id"
                :label="item.value">{{item.label}}</Radio>

            </RadioGroup>
          </FormItem>
          <FormItem class="form_item" label="合同签订时间"  prop="gfrq" v-if="form.fclx!=5 && form.fclx!=6">
            <DatePicker
              v-model="form.gfrq"
              type="date"
              format="yyyy-MM-dd"
              clearable
              placeholder="选择合同签订时间"
              style="width: 250px"
            ></DatePicker>
          </FormItem>
          <FormItem class="form_item" label="网签备案时间"  prop="wqrq" v-if="form.fclx!=5 && form.fclx!=6">
            <DatePicker
              v-model="form.wqrq"
              type="date"
              format="yyyy-MM-dd"
              clearable
              placeholder="选择网签备案时间"
              style="width: 250px"
            ></DatePicker>
          </FormItem>
          <!--新增-->
          <FormItem class="form_item" label="发票时间"  prop="wqrq" v-if="form.fclx==5 || form.fclx==6">
            <DatePicker
              v-model="form.wqrq"
              type="date"
              format="yyyy-MM-dd"
              clearable
              placeholder="选择发票时间"
              style="width: 250px"
            ></DatePicker>
          </FormItem>


          <FormItem class="form_item" label="建筑面积" prop="fcmj">
            <Input v-model="form.fcmj" style="width:250px"/>
          </FormItem>
          <FormItem class="form_item" label="购房款总额" prop="fcje">
            <Input v-model="form.fcje" style="width:250px"/>
          </FormItem>
          <FormItem class="form_item" label="房屋坐落地址" prop="scwz" style="width:950px">
            <Input
              v-model="form.scwz"
              placeholder="示例：xx区xx路xx号xx栋xx单元xx号"
              style="width: 650px"
            />
          </FormItem>
          <FormItem class="form_item" :label="label.name"  style="width:950px">
            <Input
              type="textarea"
              v-model="form.zcbz"
              :rows="3"
              placeholder="请输入详细情况"
              style="width: 650px"
            />
          </FormItem>
          <Divider orientation="left">④个人材料</Divider>
          <FormItem class="m_table">
            <Table border :columns="columns1" :data="tableData" style="width: 760px">
              <template slot-scope="{ row, index }" slot="action">
                <!--<Button type="primary" size="small" style="margin-right: 5px" @click="show(index)">上传</Button>-->
                <Upload
                  class="upload-demo"
                  action="/api/yewushouli/uploadFj"
                  multiple
                  :limit="1"
                  :on-exceed="handleExceed"
                  :on-success="uploadSuccess"
                  :file-list="fileList">
                  <el-button
                    size="mini"
                    type="primary"
                    plain> 点击上传 </el-button>
                  <el-button
                    size="mini"
                    type="primary"
                    plain> 预览 </el-button>
                  <!--<div v-if="uploadImg.url" class="upload-img">
                    <img :src="uploadImg.url">
                    <div class="upload-img-cover">
                      <Icon type="ios-eye-outline" @click.native="showBigImg = true"></Icon>
                      <Icon type="ios-trash-outline" @click.native="uploadImg = {}"></Icon>
                    </div>
                  </div>-->
                </Upload>
              </template>
              <template slot-scope="{ row, index }" slot="zt">
                <Icon type="ios-checkmark-circle-outline" color="#29d21f" size="20" style="margin-left: 30%;" v-if="form.sfwt=='1'" />
              </template>
            </Table>
          </FormItem>
<!--          <Divider orientation="left">③补贴核算</Divider>-->
<!--          <FormItem class="form_item" label="购房款总额" >-->
<!--            <Input v-model="form.fcje" style="width:250px"  readonly="true"/>-->
<!--          </FormItem>-->
<!--          <FormItem class="form_item" label="补贴比例"  >-->
<!--            <Input v-model="form.btbl" style="width:250px"  readonly="true"/>-->
<!--          </FormItem>-->
<!--          <FormItem  class="form_item" label="补贴余额"  >-->
<!--            <Input v-model="form.btye" style="width:250px"  readonly="true"/>-->
<!--          </FormItem>-->
<!--          <FormItem class="form_item" label="补贴金额" >-->
<!--            <Input v-model="form.btje" style="width:250px"  readonly="true" />-->
<!--          </FormItem>-->

          <!-- <Divider orientation="left">部门材料</Divider>-->
          <Dialog
            title="提示"
            :visible.sync="centerDialogVisible"
            width="30%"
            center>
            <span>请选择申请人类型</span>
            <span slot="footer" class="dialog-footer">
            <Button @click="centerDialogVisible = false">取 消</Button>
            <Button type="primary" @click="centerDialogVisible = false">确 定</Button>
          </span>
          </Dialog>



          <Form-item class="br" style="margin-right:140px;float:right">
            <Button
              type="primary"
              :loading="submitLoading"
              @click="submitForm"
              v-if="type==0||type==1"
            >保存并关闭
            </Button>
            <Button @click="handelPrint" v-if="type!=0" type="info">打印</Button>
            <Button @click="handelCancel">关闭</Button>
          </Form-item>
          <Spin size="large" fix v-if="loading"></Spin>
        </Form>
      </Row>
    </Card>
  </div>
</template>

<script>
  import {
    getYeWuShouLiData,
    addLeave,
    updateLeave,
    applyLeave
  } from "@/api/activiti";
  import printJS from "print-js";
  import {
    getQueryObject
  } from "@/utils/index"
  import request from '@/utils/request'

  export default {
    name: "leave-notcache",
    dicts: ['house_type', 'peoson_type','apartment_type'],
    data() {
      return {
        label: {
          name: '\xa0\xa0\xa0\xa0' + "备&nbsp注",
          name1:'\xa0\xa0' + "是否为代办",
        },
        type: 0,
        loading: false, // 表单加载状态
        modalVisible: false,
        selectDate: null,
        dictType: [{title: '测试', value: '0'}],
        //this.$store.state.dict.leaveType,
        //二手房
        columns1: [
          {
            type: "index",
            title: '序号',
            width: 90,
            align: "center"
          },
          {
            title: '资料名称',
            key: 'name'
          },{
            title: '状态',
            key: 'address',
            slot: 'zt',
            width: 100,
          },
          {
            title: '操作',
            key: 'address',
            slot: 'action',
            width: 200,
          },

        ],
        tableData:[],
        tableData1:{
          name: '申请人身份证明（验原件，收复印件）'
        },
        tableData2:{
          name: '增值税普通（或专用）发票（验原件，收复印件）'
        },
        tableData3:{
          name: '不动产属相关资料（验原件，收复印件）'
        },
        tableData4:{
          name: '农民工务工材料（农民工提供，原件）'
        },
        tableData5:{
          name: '学历证明（高校毕业生提供，验原件，收复印件）'
        },
        tableData6:{
          name: '《商品房买卖合同》（验原件，收复印件）'
        },
        tableData7:{
          name: '营业执照、公司章程、出资材料（领办企业提供、验原件、收复印件）'
        },
        tableData8:{
          name: '其他材料'
        },
        tableData9:{
          name: '不动产权证（验原件，收复印件）'
        },
        tableData10:{
          name: '委托书'
        },
        tableData11:{
          name: '委托人身份证'
        },
        tableData12:{
          name: '承诺书'
        },
        tableData13:{
          name: '营业执照'
        },

        //是否委托开发商代办
        isWT:[
          {value:'0',label:'否'},
          {value:'1',label:'是'},
          ],


        form: {
          // 添加或编辑表单对象初始化数据
          sfwt:"0",
          sqrlx: "1",
          title: "",
          description: "",
          startDate: "",
          endDate: "",
          duration: 0,
          jylx:"1",
          type:'',
          procDefId:'',
          fjids:[],

        },
        formValidate: {
          // 表单验证规则
          tname: [{required: true, message: "申请人姓名不能为空", trigger: "blur"}],

          //name: [{required: true,type: 'string', message: "申请人姓名不能为空", trigger: "blur"}],
          cardId: [{required: true, message: "身份证号不能为空", trigger: "blur"}],
          sqrlx: [{required: true, message: "申请人类型不能为空", trigger: "blur"}],
          phone: [{required: true, message: "电话不能为空", trigger: "blur"}],
          hjdz: [{required: true, message: "户籍地址不能为空", trigger: "blur"}],
          //mark: [{required: false, message: "对象备注不能为空", trigger: "blur"}],
          fclx: [{required: true, message: "资产类型不能为空", trigger: "blur"}],
          jylx: [{required: true, message: "交易类型不能为空", trigger: "blur"}],
          gfrq: [{required: true, type: 'date',message: "购置时间不能为空", trigger: "change"}],
          wqrq: [{required: true, type: 'date', message: "备案时间不能为空", trigger: "change"}],
          fcmj: [{required: true, message: "购房面积不能为空", trigger: "blur"}],
          fcje: [{required: true, message: "发票总金额不能为空", trigger: "blur"}],
          scwz: [{required: true, message: "资产地址不能为空", trigger: "blur"}],
         // zcbz: [{required: true,type: 'string', message: "资产备注不能为空", trigger: "blur"}],
        },
        errorMsg: "",
        submitLoading: false, // 添加或编辑提交状态
        processList: [],
        backRoute: "",
        procDefId: "",
        options: {
          shortcuts: [
            {
              text: "1 天",
              value() {
                const end = new Date();
                const start = new Date();
                end.setTime(start.getTime());
                return [start, end];
              }
            },
            {
              text: "2 天",
              value() {
                const end = new Date();
                const start = new Date();
                end.setTime(start.getTime() + 3600 * 1000 * 24 * 1);
                return [start, end];
              }
            },
            {
              text: "3 天",
              value() {
                const end = new Date();
                const start = new Date();
                end.setTime(start.getTime() + 3600 * 1000 * 24 * 2);
                return [start, end];
              }
            },
            {
              text: "1 周",
              value() {
                const end = new Date();
                const start = new Date();
                end.setTime(start.getTime() + 3600 * 1000 * 24 * 6);
                return [start, end];
              }
            },
            {
              text: "1 个月",
              value() {
                const end = new Date();
                const start = new Date();
                end.setTime(start.getTime() + 3600 * 1000 * 24 * 29);
                return [start, end];
              }
            }
          ]
        }
      };
    },
    methods: {
      //附件上传成功 回调
      uploadSuccess(res){
        alert('上传成功'),
        console.debug(res),
        console.debug(res[0].id)
        for (var i=0;i<res.length;i++  ){
          this.form.fjids.push(res[i].id)
        }
        console.debug(this.form.fjids)

      },

      /**
       * 提交表单
       */
      submitForm(form) {
        var that= this;
        var data= that.form;
        var url= getQueryObject();
        //alert(url.procDefId)
        //console.debug(url);
        data.procDefId=url.procDefId;
        data.fjids= that.form.fjids;
        console.debug(that.form);

        var data1={
          nowTime:'',
          fcType:'',
          money:''
        } ;
        data1.fcType=that.form.fclx;
        data1.money=that.form.fcje;
        data1.nowTime=that.form;
        console.debug(data1)


       this.$refs['form'].validate((valid) => {
          if (valid) {
            return request({
              url: 'api/leave/add',
              method: 'post',
              data
            }).then(function (res) {
              alert("保存成功")
              request({
                url: '/api/message/getSubsidyMoney',
                method: 'get',
                data1
              }).then(function (res) {
                  that.form.btje=res.resultMoney,
                  that.form.btbl=res.resultType
              })
              // ({
              //   url: 'api/leave/add',
              //   method: 'get',
              //   data
              // }).then(function (res) {
              //   thus
              //
              // })

            })
          } else {
            console.log('error submit!!');

            return false;
          }
        })
      },

      show (index) {
        this.$Modal.info({
          title: 'User Info',
          content: `Name：${this.tableData[index].name}<br>Address：${this.tableData[index].address}`
        })
      },

      //改变房产类型触发
      changeFromFC(){
        //alert(this.form.type)

        var fclx= this.form.fclx;
        var sqrlx= this.form.sqrlx;
        this.tableData=[];
        //alert(fclx+sqrlx);
        //  alert(this.form.hxlx);
        if(this.form.sqrlx){
          if(this.form.hxlx=='1'){
            //委托材料
            this.tableData.push(this.tableData10);
            this.tableData.push(this.tableData11);
            this.tableData.push(this.tableData12);
            this.tableData.push(this.tableData13);
          }
          //标准厂房提供材料
          if(this.form.fclx=='1'){
            //通用材料
            this.tableData.push(this.tableData1);
            this.tableData.push(this.tableData2);
            this.tableData.push(this.tableData3);
            //农民工提供材料
            if(this.form.sqrlx=='1'){
              this.tableData.push(this.tableData4);
              this.tableData.push(this.tableData8);
              return;
            }
            //毕业生提供材料
            if(this.form.sqrlx=='3'){
              this.tableData.push(this.tableData5);
              this.tableData.push(this.tableData8);
              return;
            }
            //领办企业提供材料
            if(this.form.sqrlx=='5'){
              this.tableData.push(this.tableData7);
              this.tableData.push(this.tableData8);
              return;
            }
          }
          //新建营业用房、新建自住用房、一手车位 提供材料
          if(this.form.fclx=='2'||this.form.fclx=='3'||this.form.fclx=='4'){
            //通用材料
            this.tableData.push(this.tableData1);
            this.tableData.push(this.tableData2);
            this.tableData.push(this.tableData6);
            //农民工提供材料
            if(this.form.sqrlx=='1'){
              this.tableData.push(this.tableData4);
              this.tableData.push(this.tableData8);
              return;
            }
            //毕业生提供材料
            if(this.form.sqrlx=='3'){
              this.tableData.push(this.tableData5);
              this.tableData.push(this.tableData8);
              return;
            }
          }
          //二手营业用房、二手住房
          if(this.form.fclx=='5'||this.form.fclx=='6'){
            //通用材料
            this.tableData.push(this.tableData1);
            this.tableData.push(this.tableData2);
            this.tableData.push(this.tableData8);
            //农民工提供材料
            if(this.form.sqrlx=='1'){
              this.tableData.push(this.tableData4);
              this.tableData.push(this.tableData9);
              return;
            }
            //毕业生提供材料
            if(this.form.sqrlx=='3'){
              this.tableData.push(this.tableData5);
              this.tableData.push(this.tableData9);
              return;
            }
            //新建商品房且转为城镇户口 还没判断

          }
        }


      },
      //改变申请人类型触发
      changeFromRY(){
          this.tableData=[];
          if(this.form.fclx){
            if(this.form.hxlx=='1'){
              //委托材料
              this.tableData.push(this.tableData10);
              this.tableData.push(this.tableData11);
              this.tableData.push(this.tableData12);
              this.tableData.push(this.tableData13);
            }
            //标准厂房提供材料
            if(this.form.fclx=='1'){
              //通用材料
              this.tableData.push(this.tableData1);
              this.tableData.push(this.tableData2);
              this.tableData.push(this.tableData3);
              //农民工提供材料
              if(this.form.sqrlx=='1'){
                this.tableData.push(this.tableData4);
                this.tableData.push(this.tableData8);
                return;
              }
              //毕业生提供材料
              if(this.form.sqrlx=='3'){
                this.tableData.push(this.tableData5);
                this.tableData.push(this.tableData8);
                return;
              }
              //领办企业提供材料
              if(this.form.sqrlx=='5'){
                this.tableData.push(this.tableData7);
                this.tableData.push(this.tableData8);
                return;
              }
            }
            //新建营业用房、新建自住用房、一手车位 提供材料
            if(this.form.fclx=='2'||this.form.fclx=='3'||this.form.fclx=='4'){
              //通用材料
              this.tableData.push(this.tableData1);
              this.tableData.push(this.tableData2);
              this.tableData.push(this.tableData6);
              //农民工提供材料
              if(this.form.sqrlx=='1'){
                this.tableData.push(this.tableData4);
                this.tableData.push(this.tableData8);
                return;
              }
              //毕业生提供材料
              if(this.form.sqrlx=='3'){
                this.tableData.push(this.tableData5);
                this.tableData.push(this.tableData8);
                return;
              }
            }
            //二手营业用房、二手住房
            if(this.form.fclx=='5'||this.form.fclx=='6'){
              //通用材料
              this.tableData.push(this.tableData1);
              this.tableData.push(this.tableData2);
              this.tableData.push(this.tableData8);
              //农民工提供材料
              if(this.form.sqrlx=='1'){
                this.tableData.push(this.tableData4);
                this.tableData.push(this.tableData8);
                return;
              }
              //毕业生提供材料
              if(this.form.sqrlx=='3'){
                this.tableData.push(this.tableData5);
                this.tableData.push(this.tableData8);
                return;
              }
              //新建商品房且转为城镇户口 还没判断
            }
          }
      },
      //改变 是否改变委托 触发
      changeFromWT(){
          this.tableData=[];
          if(this.form.fclx || this.form.sqrlx){
            if(this.form.sfwt=='1'){
              //委托材料
              this.tableData.push(this.tableData10);
              this.tableData.push(this.tableData11);
              this.tableData.push(this.tableData12);
              this.tableData.push(this.tableData13);
            }
            if(this.form.sfwt=='0'){
              //委托材料
              this.tableData.splice(this.tableData.indexOf(this.tableData10),1);
              this.tableData.splice(this.tableData.indexOf(this.tableData11),1);
              this.tableData.splice(this.tableData.indexOf(this.tableData12),1);
              this.tableData.splice(this.tableData.indexOf(this.tableData13),1);
            }
            //标准厂房提供材料
            if(this.form.fclx=='1'){
              //通用材料
              this.tableData.push(this.tableData1);
              this.tableData.push(this.tableData2);
              this.tableData.push(this.tableData3);
              //农民工提供材料
              if(this.form.sqrlx=='1'){
                this.tableData.push(this.tableData4);
                this.tableData.push(this.tableData8);
                return;
              }
              //毕业生提供材料
              if(this.form.sqrlx=='3'){
                this.tableData.push(this.tableData5);
                this.tableData.push(this.tableData8);
                return;
              }
              //领办企业提供材料
              if(this.form.sqrlx=='5'){
                this.tableData.push(this.tableData7);
                this.tableData.push(this.tableData8);
                return;
              }
            }
            //新建营业用房、新建自住用房、一手车位 提供材料
            if(this.form.fclx=='2'||this.form.fclx=='3'||this.form.fclx=='4'){
              //通用材料
              this.tableData.push(this.tableData1);
              this.tableData.push(this.tableData2);
              this.tableData.push(this.tableData6);
              //农民工提供材料
              if(this.form.sqrlx=='1'){
                this.tableData.push(this.tableData4);
                this.tableData.push(this.tableData8);
                return;
              }
              //毕业生提供材料
              if(this.form.sqrlx=='3'){
                this.tableData.push(this.tableData5);
                this.tableData.push(this.tableData8);
                return;
              }
            }
            //二手营业用房、二手住房
            if(this.form.fclx=='5'||this.form.fclx=='6'){
              //通用材料
              this.tableData.push(this.tableData1);
              this.tableData.push(this.tableData2);
              this.tableData.push(this.tableData8);
              //农民工提供材料
              if(this.form.sqrlx=='1'){
                this.tableData.push(this.tableData4);
                this.tableData.push(this.tableData8);
                return;
              }
              //毕业生提供材料
              if(this.form.sqrlx=='3'){
                this.tableData.push(this.tableData5);
                this.tableData.push(this.tableData8);
                return;
              }
              //新建商品房且转为城镇户口 还没判断
            }

          }
      },
      init() {
        this.type = this.$route.query.type;
        this.backRoute = this.$route.query.backRoute;
        this.procDefId = this.$route.query.procDefId;
        if (this.type != 0) {
          this.form.id = this.$route.query.id;
          this.getData();
        } else {
          this.$refs.form.resetFields();
          this.selectDate = [];
        }

      },
      selectDateRange(v) {
        if (v[0]) {
          this.form.startDate = v[0];
          this.form.endDate = v[1];
          let date1 = this.stringToDate(this.form.startDate);
          let date2 = this.stringToDate(this.form.endDate);
          let date = date2.getTime() - date1.getTime();
          let hours = Math.round(date / (60 * 60 * 1000));
          this.form.duration = hours;
        } else {
          this.form.startDate = "";
          this.form.endDate = "";
          this.form.duration = 0;
        }
      },
      stringToDate(str) {
        let tempStrs = str.split(" ");
        let dateStrs = tempStrs[0].split("-");
        let year = parseInt(dateStrs[0], 10);
        let month = parseInt(dateStrs[1], 10) - 1;
        let day = parseInt(dateStrs[2], 10);
        let timeStrs = tempStrs[1].split(":");
        let hour = parseInt(timeStrs[0], 10);
        let minute = parseInt(timeStrs[1], 10);
        let date = new Date(year, month, day, hour, minute, 0);
        return date;
      },
      getData() {
        this.loading = true;
        getYeWuShouLiData(this.form.id).then(res => {
          this.loading = false;
          if (res.success) {
            // 转换null为""
            let v = res.result;
            for (let attr in v) {
              if (v[attr] == null) {
                v[attr] = "";
              }
            }
            let str = JSON.stringify(v);
            let data = JSON.parse(str);
            this.form = data;
            this.selectDate = [];
            this.selectDate[0] = data.startDate;
            this.selectDate[1] = data.endDate;
            //alert(data.fclx)
            if(data.sqrlx && data.fclx){
              if(data.fclx=='1'){

                this.tableData=this.tableData3;
                return;
              }
              if(data.fclx=='2'){

                this.tableData=this.tableData2;
                return;
              }
              if(data.fclx=='3'){

                this.tableData=this.tableData1;

                return;
              }
              if(data.fclx=='4'){

                this.tableData=this.tableData4;
                return;
              }
            }
          }
        });

      },
      handelSubmit() {
        this.$refs.form.validate(valid => {
          if (valid) {
            if (!this.form.startDate) {
              this.errorMsg = "请选择时间范围";
              return;
            } else {
              this.errorMsg = "";
            }
            this.submitLoading = true;
            if (this.type == 0) {
              // 添加 避免编辑后传入id等数据 记得删除
              delete this.form.id;
              this.form.procDefId = this.procDefId;
              addLeave(this.form).then(res => {
                this.submitLoading = false;
                if (res.success) {
                  this.$Message.success("操作成功");
                  this.closeCurrentPage();
                }
              });
            } else if (this.type == 1) {
              // 编辑
              updateLeave(this.form).then(res => {
                this.submitLoading = false;
                if (res.success) {
                  this.$Message.success("操作成功");
                  this.closeCurrentPage();
                }
              });
            }
          }
        });
      },
      handelPrint() {
        let data = [];
        let leave = {
          申请人: this.form.createBy,
          请假类型: this.form.type,
          标题: this.form.title,
          原因: this.form.description,
          请假开始时间: this.form.startDate,
          请假结束时间: this.form.endDate,
          持续时长: this.form.duration + " 小时"
        };
        data.push(leave);
        printJS({
          printable: data,
          type: "json",
          header: "请假申请表",
          gridStyle: "border: 1px solid lightgray;text-align:center",
          properties: [
            "申请人",
            "请假类型",
            "标题",
            "原因",
            "请假开始时间",
            "请假结束时间",
            "持续时长"
          ]
        });
      },
      handelCancel() {
        this.closeCurrentPage();
      },
      // 关闭当前页面
      closeCurrentPage() {
        this.$store.commit("removeTag", "leave");
        localStorage.pageOpenedList = JSON.stringify(
          this.$store.state.app.pageOpenedList
        );
        this.$router.push({
          name: this.backRoute
        });
      }
    },
    mounted() {
      this.init();
    },
    watch: {
      // 监听路由变化
      $route(to, from) {
        if (to.name == "leave") {
          this.getDictData();
          this.type = this.$route.query.type;
          this.backRoute = this.$route.query.backRoute;
          this.procDefId = this.$route.query.procDefId;
          if (this.type != 0) {
            this.form.id = this.$route.query.id;
            this.getData();
          } else {
            this.$refs.form.resetFields();
            this.selectDate = [];
          }
        }
      }
    }
  };
</script>
