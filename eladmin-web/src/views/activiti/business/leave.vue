<style lang="less">
.ivu-card-body {
    padding: 0!important;
}
  .form_item {
    margin-bottom: 24px;
    vertical-align: top;
    zoom: 1;
    width: 370px;
    float: left;
    margin-right: 50px;
    padding-left: 40px;
  }
  .form_item1 {
    padding-left: 40px;
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
    font-weight: bold;
    padding: 0px;
  }

  .ivu-divider-horizontal {
    display: block;
    height: 1px;
	width:753px;
    min-width: 753px!important;
    margin: 16px 38px!important;
    clear: both;
  }

  .ivu-picker-panel-body {
    width: 250px
  }

  .ivu-card-head {
    border-bottom: none;
    padding: 15px 0 8px 18px!important;
    line-height: 1;
    padding-bottom: 2px;
    width: 760px;
    margin: auto;
  }
  .m_table .ivu-form-item-content{
    margin-left: 50px!important;
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
      <Row style="width: 820px;margin: auto;">
        <Form
          ref="form"
          :model="form"
          :label-width="110"
          :rules="formValidate"
          style="position:relative;border: 1px solid #e8eaec;"
          inline
		  id="applyForm"
        >

          <FormItem class="form_item1" :label="label.name1"  prop="sfwt" width="100%"  style="   margin-bottom: 0px!important;">
            <RadioGroup v-model="form.sfwt"    @on-change="changeFromWT">
              <Radio
                v-for="item in isWT"
                :label="item.value">{{item.label}}</Radio>
            </RadioGroup>
          </FormItem>
          <Divider orientation="left" v-if="form.sfwt=='1'">代办人信息</Divider>
          <FormItem class="form_item" label="代办企业" prop="enterpriseName" v-if="form.sfwt=='1'">
            <Input v-model="form.enterpriseName" style="width:250px"></Input>
          </FormItem>
          <FormItem class="form_item" label="代办人姓名" prop="personName" v-if="form.sfwt=='1'">
            <Input v-model="form.personName" style="width:250px"></Input>
          </FormItem>

          <FormItem class="form_item" label="代办人电话" prop="telephone" v-if="form.sfwt=='1'">
            <Input v-model="form.telephone" style="width:250px"></Input>
          </FormItem>

          <FormItem class="form_item" label="身份证号" prop="dbrcardID" v-if="form.sfwt=='1'">
            <Input v-model="form.dbrcardID" style="width:250px"></Input>
          </FormItem>

         <!--<FormItem label="申请人" prop="name" v-if="type!=0&&type!=1">{{form.createBy}}</FormItem>-->
          <Divider orientation="left">补助对象信息</Divider>
           <FormItem class="form_item1" :label="label.dxlx" prop="sqrlx"  width="100%">
            <RadioGroup v-model="form.sqrlx" filterable placeholder="请选择" @on-change="changeFromRY">
              <Radio
                v-for="item in dict.peoson_type"
                :key="item.id"
                :label="String(item.id)">{{item.label}}</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem class="form_item" label="补助人姓名" prop="tname">
            <Input v-model="form.tname" style="width:250px"></Input>
          </FormItem>
          <FormItem class="form_item" label="联系电话" prop="phone">
            <Input v-model="form.phone" style="width:250px"></Input>
          </FormItem>
          <FormItem class="form_item" label="身份证号" prop="cardId">
            <Input v-model="form.cardId" style="width:250px"></Input>
          </FormItem>

          <FormItem class="form_item" label="身份证地址" prop="hjdz" style="width:95%">
            <Input
              v-model="form.hjdz"
              placeholder="请输入详细情况"
              style="width: 100%"
            ></Input>
          </FormItem>
          <Divider orientation="left">购置房产信息</Divider>
          <FormItem class="form_item1" :label="label.bzlx" prop="fclx"   width="100%">
            <RadioGroup v-model="form.fclx" filterable placeholder="请选择" @on-change="changeFromFC" id="fclxRedio">
              <Radio
                v-for="item in dict.house_type"
                :key="item.id"
                :label="String(item.id)">{{item.label}}</Radio>

            </RadioGroup>
          </FormItem>
          <!--新增-->
          <FormItem class="form_item1" :label="label.fwlx"  prop="fwlx" width="100%" v-if="form.fclx=='15'  ">
            <RadioGroup v-model="form.fwlx" filterable placeholder="请选择" id="hxlxRedio">
              <Radio
                v-for="item in dict.apartment_type"
                :key="item.id"
                :label="String(item.id)">{{item.label}}</Radio>
            </RadioGroup>
          </FormItem>

          <FormItem class="form_item" label="合同签订时间"  prop="gfrq" v-if="form.fclx!=28 && form.fclx!=29">
            <DatePicker
              v-model="form.gfrq"
              type="date"
              format="yyyy-MM-dd"
              clearable
              placeholder="选择合同签订时间"
              style="width: 250px"
            ></DatePicker>
          </FormItem>

          <FormItem class="form_item" label="网签备案时间"  prop="wqrq" v-if="form.fclx!=28 && form.fclx!=29">
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
          <FormItem class="form_item" label="发票时间"  prop="fpsj" v-if="form.fclx==28 || form.fclx==29">
            <DatePicker
              v-model="form.fpsj"
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
            <Input v-model="form.fcje"  style="width:250px" />
          </FormItem>
          <FormItem class="form_item" label="房屋坐落地址" prop="scwz" style="width:95%">
            <Input
              v-model="form.scwz"
              placeholder="示例：xx区xx路xx号xx栋xx单元xx号"
              style="width:100%"
            />
          </FormItem>
          <Divider orientation="left">个人材料</Divider>
          <FormItem class="m_table">
            <Table border :columns="columns1" :data="tableData" style="width: 728px" :span-method="handleSpan" @on-row-click="bindFileData">
              <template slot-scope="{ row, index }" slot="action">
                <Upload
                  class="upload-demo"
                  action="/api/yewushouli/uploadFj"
                  multiple
                  :data="form.fileData"
                  :limit="1"
                  :on-success="uploadSuccess"
                  :before-upload="beforeUpload">
                  <el-button
                    size="mini"
                    type="primary"
                    plain> 点击上传
                  </el-button>
                  <el-button
                    size="mini"
                    type="primary"
                    plain> 预览
                  </el-button>
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
<!--          <Dialog-->
<!--            title="提示"-->
<!--            :visible.sync="centerDialogVisible"-->
<!--            width="30%"-->
<!--            center>-->
<!--            <span>请选择申请人类型</span>-->
<!--            <span slot="footer" class="dialog-footer">-->
<!--            <Button @click="centerDialogVisible = false">取 消</Button>-->
<!--            <Button type="primary" @click="centerDialogVisible = false">确 定</Button>-->
<!--          </span>-->
<!--          </Dialog>-->

          <Form-item class="br" style="margin-right:40px;float:right">
            <Button
              type="primary"
              :loading="submitLoading"
              @click="submitForm"
              v-if="type==0||type==1"
            >保存并关闭
            </Button>
            <Button @click="handelPrint" type="info">打印</Button>
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
          name: '\xa0\xa0\xa0' + "备"+'\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0'+"注",
          name1: "是否为代办",
          dxlx: "对象类型",
          bzlx: "补助类型",
          fwlx: "房屋类型",
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
            align: 'center',
            title: '材料类型',
            width: 100,
            key: 'type',
          },{
            title: '资料名称',
            key: 'name',
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
          name: '申请人身份证明',
          type:'个人材料',
          mergeCol:0,
        },
        tableData2:{
          name: '增值税普通（或专用）发票',
          type:'个人材料',
          mergeCol:0,
        },
        tableData3:{
          name: '不动产属相关资料',
          type:'个人材料',
          mergeCol:0,
        },
        tableData4:{
          name: '农民工务工材料',
          type:'个人材料',
          mergeCol:0,
        },
        tableData5:{
          name: '学历证明',
          type:'个人材料',
          mergeCol:0,
        },
        tableData6:{
          name: '《商品房买卖合同》',
          type:'个人材料',
          mergeCol:0,
        },
        tableData7:{
          name: '营业执照、公司章程、出资材料',
          type:'个人材料',
          mergeCol:0,
        },
        tableData8:{
          name: '其他材料',
          type:'其他材料',
          mergeCol:0,
        },
        tableData9:{
          name: '不动产权证',
          type:'个人材料',
          mergeCol:0,
        },
        tableData10:{
          name: '委托书',
          type:'委托材料',
          mergeCol:0,
        },
        tableData11:{
          name: '委托人身份证',
          type:'委托材料',
          mergeCol:0,
        },
        tableData12:{
          name: '承诺书',
          type:'委托材料',
          mergeCol:0,
        },
        tableData13:{
          name: '营业执照',
          type:'委托材料',
          mergeCol:0,
        },

        //是否委托开发商代办
        isWT:[
          {value:'0',label:'否'},
          {value:'1',label:'是'},
          ],


        form: {
          // 添加或编辑表单对象初始化数据
          sfwt:"0",
          sqrlx: 7,
          procDefId:'',
          fjids:[],
          enterpriseName:'',
          personName:'',
          telephone:'',
          dbrcardID:'',
          fpsj:'',
          fileData:{},
          gfrq:'',
          wqrq:'',
          tname:'',
          phone:'',
          hjdz:'',
          fcmj:'',
          fcje:'',
          scwz:'',
          fclx:'',
          fwlx:'30',
          cardId:'',
        },
        formValidate: {
          // 表单验证规则
          enterpriseName: [{required: true,  type: 'string',message: "代办企业不能为空", trigger: "blur"}],
          personName: [{required: true, type: 'string', message: "代办人姓名不能为空", trigger: "blur"}],
          telephone: [{required: true, type: 'string', message: "代办人电话不能为空", trigger: "blur"}],
          dbrcardID: [{required: true, type: 'string', message: "代办人身份证号不能为空", trigger: "blur"}],

          fpsj: [{required: true,type: 'date', message: "发票时间不能为空", trigger: "change"}],
          gfrq: [{required: true,  type: 'date',message: "购置时间不能为空", trigger: "change"}],
          wqrq: [{required: true, type: 'date', message: "备案时间不能为空", trigger: "change"}],

          tname: [{required: true, type: 'string', message: "补助人姓名不能为空", trigger: "blur"}],
          cardId: [{required: true, type: 'string', message: "身份证号不能为空", trigger: "blur"}],
          phone: [{required: true, type: 'string', message: "电话不能为空", trigger: "blur"}],
          hjdz: [{required: true, type: 'string', message: "户籍地址不能为空", trigger: "blur"}],
          fcmj: [{required: true, type: 'string', message: "购房面积不能为空", trigger: "blur"}],
          fcje: [{required: true, type: 'string', message: "发票总金额不能为空", trigger: "blur"}],
          scwz: [{required: true, type: 'string', message: "资产地址不能为空", trigger: "blur"}],
          sfwt: [{required: true, type: 'string', message: "请选择是否为代办", trigger: "change"}],

          fwlx: [{required: true,type: 'string', message: "补助类型不能为空", trigger: "change"}],
          fclx: [{required: true, type: 'string',message: "资产类型不能为空", trigger: "change"}],
          sqrlx: [{required: true,type: 'string', message: "对象类型不能为空", trigger: "change"}],
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
      //点击表格 某行 绑定数据
      bindFileData(data){
        this.form.fileData={};
        this.form.fileData.name=data.name;
        this.form.fileData.type=data.type;
        console.debug(this.form.fileData)
      },


      created() {
        let that = this;
        that.newData = that.integratedData(that.tableData);
      },

      // 被整理的数组中相同age的元素需放在一块，否则还要再整理数据（暂未处理）
      integratedData(data) {
        let that = this;
        // 获取所有的附件类型
        let arrId = [];
        data.forEach(i => {
          !arrId.includes(i.type) ? arrId.push(i.type) : arrId;
        });
        // 提前为每个type设置跨行数为0
        let arrObj = [];
        arrId.forEach(j => {
          arrObj.push({
            id: j,
            num: 0
          })
        });
        // 计算每个type的可跨行数
        data.forEach(k => {
          arrObj.forEach(l => {
            k.type === l.id ? l.num ++ : l.num;
          })
        });
        data.forEach(m => {
          arrObj.forEach((n,index) => {
            if(m.type === n.id){
              if(arrId.includes(m.type)){
                m.mergeCol = n.num;
                arrId.splice(arrId.indexOf(m.type),1)
              }else{
                m.mergeCol = 0;
              }
            }
          })
        });
        return data;
      },

      //合并表格行
      handleSpan({ row, column, rowIndex, columnIndex }) {

        if (columnIndex === 1) {
          return {
            rowspan: this.tableData.mergeCol === 0 ? 0:row.mergeCol,
            colspan: this.tableData.mergeCol === 0 ? 0:1
          };
        }
      },

      //附件上传之前
      beforeUpload(){
        console.debug(this.form.fileData)
      },

      //附件上传成功 回调
      uploadSuccess(res){
        alert('上传成功')
        for (var i=0;i<res.length;i++  ){
          this.form.fjids.push(res[i].id)
        }

      },

      /**
       * 提交表单
       */
      submitForm(form) {
        var that= this;
        var data= that.form;
        var url= getQueryObject();
        //alert(url.procDefId)
        data.procDefId=url.procDefId;
        data.fjids= that.form.fjids;
        // alert(that.form.sqrlx.key);
        // data.sqrlx=that.form.sqrlx.key;



        /*var data1={
          nowTime:'',
          fcType:'',
          money:''
        } ;
        data1.fcType=that.form.fclx.key;
        data1.money=that.form.fcje;
        data1.nowTime=that.form;
        console.debug(data1)*/

       this.$refs['form'].validate((valid) => {
          if (valid) {
            return request({
              url: 'api/leave/add',
              method: 'post',
              data
            }).then(function (res) {
              alert("保存成功")

             /* request({
                url: '/api/message/getSubsidyMoney',
                method: 'get',
                data1
              }).then(function (res) {
                  that.form.btje=res.resultMoney,
                  that.form.btbl=res.resultType
              })*/
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

          if(this.form.sfwt=='1'){
            //委托材料


            this.tableData.push(this.tableData10);
            this.tableData.push(this.tableData11);
            this.tableData.push(this.tableData12);
            this.tableData.push(this.tableData13);
          }
          //标准厂房提供材料
          if(this.form.fclx=='13'){


            //通用材料
            this.tableData.push(this.tableData1);
            this.tableData.push(this.tableData2);
            this.tableData.push(this.tableData3);
            //农民工提供材料
            if(this.form.sqrlx=='7'){
              this.tableData.push(this.tableData4);
            }
            //毕业生提供材料
            if(this.form.sqrlx=='9'){
              this.tableData.push(this.tableData5);
            }
            //领办企业提供材料
            if(this.form.sqrlx=='11'){
              this.tableData.push(this.tableData7);
            }
          }
          //新建营业用房、新建自住用房、一手车位 提供材料
          if(this.form.fclx=='14'||this.form.fclx=='15'||this.form.fclx=='16'){
            //通用材料
            this.tableData.push(this.tableData1);
            this.tableData.push(this.tableData2);
            this.tableData.push(this.tableData6);
            //农民工提供材料
            if(this.form.sqrlx=='7'){
              this.tableData.push(this.tableData4);
            }
            //毕业生提供材料
            if(this.form.sqrlx=='9'){
              this.tableData.push(this.tableData5);
            }
          }
          //二手营业用房、二手住房
          if(this.form.fclx=='28'||this.form.fclx=='29'){


            //通用材料
            this.tableData.push(this.tableData1);
            this.tableData.push(this.tableData2);
            this.tableData.push(this.tableData9);

            //农民工提供材料
            if(this.form.sqrlx=='7'){
              this.tableData.push(this.tableData4);

            }
            //毕业生提供材料
            if(this.form.sqrlx=='9'){
              this.tableData.push(this.tableData5);
              return;
            }
            //新建商品房且转为城镇户口 还没判断

          }
          this.tableData.push(this.tableData8);
          this.created()
          return;
        }


      },
      //改变申请人类型触发
      changeFromRY(){
          this.tableData=[];
          if(this.form.fclx){
            if(this.form.sfwt=='1'){
              //委托材料
              this.tableData.push(this.tableData10);
              this.tableData.push(this.tableData11);
              this.tableData.push(this.tableData12);
              this.tableData.push(this.tableData13);
            }
            //标准厂房提供材料
            if(this.form.fclx=='13'){
              //通用材料
              this.tableData.push(this.tableData1);
              this.tableData.push(this.tableData2);
              this.tableData.push(this.tableData3);
              //农民工提供材料
              if(this.form.sqrlx=='7'){
                this.tableData.push(this.tableData4);
              }
              //毕业生提供材料
              if(this.form.sqrlx=='9'){
                this.tableData.push(this.tableData5);
              }
              //领办企业提供材料
              if(this.form.sqrlx=='11'){
                this.tableData.push(this.tableData7);
              }
            }
            //新建营业用房、新建自住用房、一手车位 提供材料
            if(this.form.fclx=='14'||this.form.fclx=='15'||this.form.fclx=='16'){
              //通用材料
              this.tableData.push(this.tableData1);
              this.tableData.push(this.tableData2);
              this.tableData.push(this.tableData6);
              //农民工提供材料
              if(this.form.sqrlx=='7'){
                this.tableData.push(this.tableData4);
              }
              //毕业生提供材料
              if(this.form.sqrlx=='9'){
                this.tableData.push(this.tableData5);
              }
            }
            //二手营业用房、二手住房
            if(this.form.fclx=='28'||this.form.fclx=='29'){
              //通用材料
              this.tableData.push(this.tableData1);
              this.tableData.push(this.tableData2);
              //农民工提供材料
              if(this.form.sqrlx=='7'){
                this.tableData.push(this.tableData4);
                this.tableData.push(this.tableData9)
              }
              //毕业生提供材料
              if(this.form.sqrlx=='9'){
                this.tableData.push(this.tableData5);
                this.tableData.push(this.tableData9)
              }
              //新建商品房且转为城镇户口 还没判断
            }
            this.tableData.push(this.tableData8)
          }
          this.created()
      },

      //改变 是否改变委托 触发
      changeFromWT(){
          //清空表格
          this.tableData=[];

          if(this.form.sfwt=='0'){

            //  清空表单里的委托信息
            this.form.enterpriseName=null;
            this.form.personName=null;
            this.form.dbrcardID=null;
            this.form.telephone=null;
          }

          if(this.form.fclx || this.form.sqrlx){
            if(this.form.sfwt=='1'){

              //表单清空
              this.tableData=[];
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
            if(this.form.fclx=='13'){
              //通用材料
              this.tableData.push(this.tableData1);
              this.tableData.push(this.tableData2);
              this.tableData.push(this.tableData3);
              //农民工提供材料
              if(this.form.sqrlx=='7'){
                this.tableData.push(this.tableData4);
              }
              //毕业生提供材料
              if(this.form.sqrlx=='9'){
                this.tableData.push(this.tableData5);
              }
              //领办企业提供材料
              if(this.form.sqrlx=='11'){
                this.tableData.push(this.tableData7);
              }
            }
            //新建营业用房、新建自住用房、一手车位 提供材料
            if(this.form.fclx=='14'||this.form.fclx=='15'||this.form.fclx=='16'){
              //通用材料
              this.tableData.push(this.tableData1);
              this.tableData.push(this.tableData2);
              this.tableData.push(this.tableData6);
              //农民工提供材料
              if(this.form.sqrlx=='7'){
                this.tableData.push(this.tableData4);
              }
              //毕业生提供材料
              if(this.form.sqrlx=='9'){
                this.tableData.push(this.tableData5);
              }
            }
            //二手营业用房、二手住房
            if(this.form.fclx=='28'||this.form.fclx=='29'){
              //通用材料
              this.tableData.push(this.tableData1);
              this.tableData.push(this.tableData2);
              //农民工提供材料
              if(this.form.sqrlx=='7'){
                this.tableData.push(this.tableData4);
              }
              //毕业生提供材料
              if(this.form.sqrlx=='9'){
                this.tableData.push(this.tableData5);
              }
              this.tableData.push(this.tableData9);
              //新建商品房且转为城镇户口 还没判断
            }
            this.tableData.push(this.tableData8);
          }
        this.created()
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
        console.debug(this.dict.peoson_type)
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
            this.form.sqrlx=parseInt(this.form.sqrlx);
            this.form.fclx=parseInt(this.form.fclx);
            this.form.fwlx=parseInt(this.form.fwlx);
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

        printJS({ printable: 'applyForm', type: 'html', header: '这是标题内容' });
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
