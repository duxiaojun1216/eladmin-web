<template>
  <div class="app-container" style="width: 70%;margin-left: 15%;border: 1px solid #dcdfe6;">
    <!--工具栏-->
    <div class="head-container">
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <!--表单组件-->
      <!--表单组件-->
      <h2 align="center">泸州置业补助申请表</h2>
      <el-form ref="form" :model="form" :rules="rules" size="medium" inline="true" label-width="150px" label-position="left">
        <el-divider content-position="left" ><el-tag size="medium" effect="plain">申报房产信息</el-tag></el-divider>
        <el-form-item label="申报人" prop="tname" style="width: 48%;"  >
          <el-input v-model="form.tname" style="width: 300px; "  />
        </el-form-item>
        <el-form-item label="身份证号" prop="cardId" style="width: 48% " >
          <el-input v-model="form.cardId" style="width: 300px; margin-left: 35px;" />
        </el-form-item>

        <el-form-item label="申请人类型" style="width: 48% ;" prop="sqrlx" labelPosition="right" >
          <!--<el-radio-group v-model="form.sqrlx" size="mini" style="width: 600px" @change="changeFromRY()">
            <el-radio-button
              v-for="item in dict.peoson_type"
              :key="item.id"
              :label="item.label"
              :value="item.value"
            />
          </el-radio-group>-->
          <el-select v-model="form.sqrlx" filterable placeholder="请选择" style="width: 300px;"  @change="changeFromRY()">
            <el-option
              v-for="item in dict.peoson_type"
              :key="item.id"
              :label="item.label"
              :value="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone"  style="width: 48%;;">
          <el-input v-model="form.phone" style="width: 300px;margin-left: 35px;" />
        </el-form-item>

          <el-divider  content-position="left"><el-tag size="medium" effect="plain" >申报房产信息</el-tag></el-divider>
        <el-form-item label="房产类型"  style="width:  90%" v-model="form.fclx" >
          <!--<el-select v-model="form.fclx" filterable placeholder="请选择" @change="changeFromFC()">
              <el-option
                v-for="item in dict.house_type"
                :key="item.id"
                :label="item.label"
                :value="item.value" />
            </el-select>-->
          <el-radio-group v-model="radio1"  size="medium" style="width: 100%" @change="changeFromFC()">
            <el-radio
              v-for="item in dict.house_type"
              :key="item.id"
              :label="item.value">{{item.label}}</el-radio>

          </el-radio-group>
        </el-form-item>

        <el-form-item label="购房日期" prop="gfrq" style="width:30%">
          <el-date-picker v-model="form.gfrq" type="datetime" style="width: 180px;;" />
        </el-form-item>
        <el-form-item label="网签备案日期" prop="wqrq" style="width:30%">
          <el-date-picker v-model="form.wqrq" type="datetime" style="width: 180px;" />
        </el-form-item>
        <el-form-item label="完税日期" prop="wsrq" style="width:35%" label-width="180px">
          <el-date-picker v-model="form.wsrq" type="datetime" style="width: 180px;" />
        </el-form-item>


        <el-form-item  label="增值税发票总金额" prop="fpje" style="width:30%" >
          <el-input v-model="form.fpje" style="width: 180px;" />
        </el-form-item>
        <el-form-item label="购房面积（平方米）" prop="fcmj"  style="width:30%">
        <el-input v-model="form.fcmj" style="width: 180px; " />
        </el-form-item>
        <el-form-item label="不动产权证号（二手房）" prop="bdcqzh" v-if="radio1 == '二手营业用房/自住用房'" label-width="180px" >
          <el-input v-model="form.bdcqzh" style="width: 180px; " />
        </el-form-item>

        <el-form-item  label="房屋地址" prop="scwz" style="width:100%">
          <el-input v-model="form.scwz" style="width:938px; " />
        </el-form-item>

        <!--
               <el-form-item v-if="form.fclx == '标准化厂房'" label="房屋地址">
                  <el-input v-model="form.fcje" style="width: 300px; margin-left: 20px;" />
                </el-form-item>
                <p>房产信息：</p>
                <el-form-item label="建筑类型">
                  <el-input v-model="form.jzlx" style="width: 300px; margin-left: 20px;" />
                </el-form-item>
                <el-form-item label="产权年限">
                  <el-input v-model="form.cqnx" style="width: 300px; margin-left: 20px;" />
                </el-form-item>
                <el-form-item label="开发商">
                  <el-input v-model="form.kfs" style="width: 300px; margin-left: 20px;" />
                </el-form-item>
                <el-form-item label="面积">
                  <el-input v-model="form.fcmj" style="width: 300px; margin-left: 20px;" />
                </el-form-item>
                <el-form-item label="建成时间">
                  <el-date-picker v-model="form.jcsj" type="datetime" style="width: 300px; margin-left: 20px;" />
                </el-form-item>
                <el-form-item label="所处位置">
                  <el-input v-model="form.scwz" style="width: 300px; margin-left: 20px;" />
                </el-form-item>-->
        <el-divider  content-position="left"><el-tag size="medium" effect="plain">申报材料</el-tag></el-divider>
        <el-form-item>
          <el-table
            ref="table1"
            :data="tableData"
            style="width: 100%"
            >
            <el-table-column
              type="index"
              label="序号"
              width="50">
            </el-table-column>
            <el-table-column
              label="资料名称"
              width="800px"
              prop="name">
            </el-table-column>
            <el-table-column label="操作" width="200px">
              <template slot-scope="scope">
                <!--<el-button
                  size="mini"
                  type="primary"
                  plain
                  @click="handleEdit(scope.$index, scope.row)">上传</el-button>-->
                <el-upload
                  class="upload-demo"
                  action="/api/yewushouli/uploadFj"
                  multiple
                  :data="sbxx"
                  :limit="1"
                  :on-exceed="handleExceed"
                  :file-list="fileList">
                  <el-button
                    size="mini"
                    type="primary"
                    plain> 点击上传 </el-button>
                </el-upload>

              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
        <el-dialog
          title="提示"
          :visible.sync="centerDialogVisible"
          width="30%"
          center>
          <span>请选择申请人类型</span>
          <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
          </span>
        </el-dialog>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="crud.cancelCU">取消</el-button>
        <el-button  type="primary" @click="submitForm()">确认</el-button>
<!--        <el-button :loading="crud.cu === 2" type="primary" @click="crud.submitCU">确认2</el-button>-->

      </div>

    </div>
  </div>
</template>

<script>
  import crudTYewushouli from '@/api/yewushouli'
  import request from '@/utils/request'
  import CRUD, { presenter, header, form, crud } from '@crud/crud'
  import rrOperation from '@crud/RR.operation'
  import crudOperation from '@crud/CRUD.operation'
  import udOperation from '@crud/UD.operation'
  import pagination from '@crud/Pagination'

  // crud交由presenter持有
  const defaultCrud = CRUD({ title: '申报信息', url: 'api/tShenbaoxingxi', sort: 'id,desc', crudMethod: { ...crudTYewushouli }})
  const defaultForm = { tname: null, cardId: null, sqrlx : null, phone: null, fclx: null, gfrq: null, wqsj: null, wsrq: null, fpje: null, fcmj: null, bdcqzh: null, scwz: null }
  export default {
    name: 'TShenbaoxingxi',
    components: { pagination, crudOperation, rrOperation, udOperation },
    mixins: [presenter(defaultCrud), header(), form(defaultForm), crud()],
    dicts: ['house_type', 'peoson_type'],
    data() {
      return {
        permission: {
          add: ['admin', 'tShenbaoxingxi:add'],
          edit: ['admin', 'tShenbaoxingxi:edit'],
          del: ['admin', 'tShenbaoxingxi:del']
        },
        centerDialogVisible: false,
        //二手房
        tableData1: [{
          name: '申请人身份证明（验原件，收复印件）'
        }, {
          name: '增值税普通（或专用）发票（验原件，收复印件）'
        }, {
          name: '不动产权证（验原件，收复印件）'
        }, {
          name: '农民工务工材料（农民工提供，原件）'
        }, {
          name: '学历证明（高校毕业生提供，验原件，收复印件）'
        }],

        //新建营业房、自住房、一手车位
        tableData2:[{
          name: '申请人身份证明（验原件，收复印件）'
        }, {
          name: '《商品房买卖合同》（验原件，收复印件）'
        }, {
          name: '增值税普通（或专用）发票（验原件，收复印件）'
        }, {
          name: '农民工务工材料（农民工提供，原件）'
        }, {
          name: '学历证明（高校毕业生提供，验原件，收复印件）'
        }],

        //标准化厂房
        tableData3:[{
          name: '申请人身份证明（验原件，收复印件）'
        }, {
          name: '营业执照、公司章程、出资材料（领办企业提供、验原件、收复印件）'
        }, {
          name: '不动产属相关资料（验原件，收复印件）'
        }, {
          name: '增值税普通（或专用）发票（验原件，收复印件）'
        }, {
          name: '农民工务工材料（农民工提供，原件）'
        }, {
          name: '学历证明（高校毕业生提供，验原件，收复印件）'
        }],

        //购买新建商品房且转为城镇户口
        tableData4:[{
          name: '申请人身份证明（验原件，收复印件）'
        }],
        sbxx:{
          code:'',
          sqxxId:'',
        },
        rules: {
          htsj: [
            { required: true, message: '合同时间不能为空', trigger: 'blur' }
          ],
          wqsj: [
            { required: true, message: '网签备案日期不能为空', trigger: 'blur' }
          ],
          tname: [
            { required: true, message: '申报人不能为空', trigger: 'blur' }
          ],
          cardId: [
            { required: true, message: '身份证号不能为空', trigger: 'blur' }
          ],
          sqrlx: [
            { required: true, message: '申请人类型不能为空', trigger: 'blur' }
          ],
          phone: [
            { required: true, message: '联系电话不能为空', trigger: 'blur' }
          ],
          /*fclx: [
            { required: true, message: '房产类型不能为空', trigger: 'blur' }
          ],*/
          gfrq: [
            { required: true, message: '购房日期不能为空', trigger: 'blur' }
          ],
          wsrq: [
            { required: true, message: '完税日期不能为空', trigger: 'blur' }
          ],
          scwz: [
            { required: true, message: '房屋地址不能为空', trigger: 'blur' }
          ],
          fpje: [
            { required: true, message: '增值税发票总金额不能为空', trigger: 'blur' }
          ],
          bdcqzh: [
            { required: true, message: '不动产权证号（二手房）', trigger: 'blur' }
          ],
          fcmj: [
            { required: true, message: '房产面积', trigger: 'blur' }
          ]
        },
        radio1: '1'

      }
    },
    methods: {
      // 获取数据前设置好接口地址
      [CRUD.HOOK.beforeRefresh]() {
        return true
      },
      /**
       * 提交表单
       */
     /* submitForm() {
        var that= this;
        var data= that.form;
        data.fclx=that.radio1;
        console.debug(that.form);

      },*/
      submitForm(form) {
        var that= this;
        var data= that.form;
        data.fclx=that.radio1;
        console.debug(that.form);
        this.$refs['form'].validate((valid) => {
          if (valid) {
            return request({
              url: 'api/yewushouli',
              method: 'post',
              data
            }).then(function (res) {
                if(res.success){
                  that.sbxx.code=res.code;
                  that.sbxx.sqxxid=res.sqxxid;
                  alert("信息保存成功")
                  console.debug(that.sbxx)
                }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        })
      },

      //改变房产类型触发
      changeFromFC(){
        var _this= this;
        if(!_this.form.sqrlx){
          _this.centerDialogVisible = true;
          return;
        }
        if (_this.form.sqrlx){
          if(_this.radio1=='1'){

            _this.tableData=_this.tableData3;
              return;
          }
          if(_this.radio1=='2'){

            _this.tableData=_this.tableData2;
            return;
          }
          if(_this.radio1=='3'){

            _this.tableData=_this.tableData1;
            return;
          }
          if(_this.radio1=='4'){

            _this.tableData=_this.tableData4;
            return;
          }
        }

      },
/*
      changeFromFC(){
        var _this= this;
        if(!_this.form.sqrlx){
          _this.centerDialogVisible = true;
          return;
        }
        if (_this.form.sqrlx){
          if(_this.form.fclx=='标准厂房'){
            _this.tableData=_this.tableData3;
              return;
          }
          if(_this.form.fclx=='新建营业用房/自住住房'){
            _this.tableData=_this.tableData2;
            return;
          }
          if(_this.form.fclx=='二手营业用房/自住用房'){
            _this.tableData=_this.tableData1;
            return;
          }
          if(_this.form.fclx=='一手车位（车库）'){
            _this.tableData=_this.tableData4;
            return;
          }
        }

      },
*/
      //改变申请人类型触发
      changeFromRY(){
        if(this.radio1){

          if(this.radio1=='1'){

            this.tableData=this.tableData3;
          }
          if(this.radio1=='2'){

            this.tableData=this.tableData2;
          }
          if(this.radio1=='3'){

            this.tableData=this.tableData1;
          }
          if(this.radio1=='4'){

            this.tableData=this.tableData4;
          }
        }

      }
    },

     }
</script>

<style scoped>
  .el-divider__text.is-left {
    left: 0px;

  }
  .el-divider__text {

    padding: 0 0px;


  }
  .el-input__inner {

    border: 1px solid #4D5A79;

  }
</style>
