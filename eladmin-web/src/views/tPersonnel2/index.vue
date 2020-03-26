<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <!--表单组件-->
      <el-form ref="form" :model="form" :rules="rules" size="small" inline="true" label-width="200">
        <el-divider content-position="left">申报人信息</el-divider>
        <el-form-item label="申报人" prop="tname" style="width: 48%" >
          <el-input v-model="form.tname" style="width: 300px; margin-left: 20px" />
        </el-form-item>
        <el-form-item label="身份证号" prop="cardId" style="width: 48%" >
          <el-input v-model="form.cardId" style="width: 300px; margin-left: 20px;" />
        </el-form-item>

        <el-form-item label="申请人类型" style="width: 48% " prop="sqrlx" >
          <!--<el-radio-group v-model="form.sqrlx" size="mini" style="width: 600px" @change="changeFromRY()">
            <el-radio-button
              v-for="item in dict.peoson_type"
              :key="item.id"
              :label="item.label"
              :value="item.value"
            />
          </el-radio-group>-->
          <el-select v-model="form.sqrlx" filterable placeholder="请选择" style="width: 300px; margin-left: 10px;"  @change="changeFromRY()">
            <el-option
              v-for="item in dict.peoson_type"
              :key="item.id"
              :label="item.label"
              :value="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone"  style="width: 48%">
          <el-input v-model="form.phone" style="width: 300px; margin-left: 20px;" />
        </el-form-item>

        <el-divider  content-position="left">申报房产信息</el-divider>
        <el-form-item label="房产类型" prop="fclx" style="width:  90%">
          <!--<el-select v-model="form.fclx" filterable placeholder="请选择" @change="changeFromFC()">
              <el-option
                v-for="item in dict.house_type"
                :key="item.id"
                :label="item.label"
                :value="item.value" />
            </el-select>-->
          <el-radio-group v-model="form.fclx" size="medium" style="width: 1000px" @change="changeFromFC()">
            <el-radio
              v-for="item in dict.house_type"
              :key="item.id"
              :label="item.label"
              :value="item.value"
            />
          </el-radio-group>
        </el-form-item>

        <el-form-item label="购房日期" prop="gfrq" style="width:30%">
          <el-date-picker v-model="form.htsj" type="datetime" style="width: 280px; margin-left: 20px;" />
        </el-form-item>
        <el-form-item label="网签备案日期" prop="wqsj" style="width:30%">
          <el-date-picker v-model="form.wqsj" type="datetime" style="width: 280px; margin-left: 20px;" />
        </el-form-item>
        <el-form-item label="完税日期" prop="wsrq" style="width:30%">
          <el-date-picker v-model="form.htsj" type="datetime" style="width: 280px; margin-left: 20px;" />
        </el-form-item>

        <el-form-item  label="房屋地址" prop="scwz" style="width:90%">
          <el-input v-model="form.fcje" style="width: 500px; margin-left: 20px;" />
        </el-form-item>
        <el-form-item  label="增值税发票总金额" prop="fpje" style="width:30%" >
          <el-input v-model="form.btje" style="width: 300px; margin-left: 20px;" />
        </el-form-item>
        <el-form-item label="不动产权证号（二手房）" prop="fcid" v-if="form.fclx == '标准厂房'" style="width:30%">
          <el-input v-model="form.btye" style="width: 300px; margin-left: 20px;" />
        </el-form-item>
        <el-form-item label="购房面积（平方米）" prop="fcmj"  style="width:30%">
        <el-input v-model="form.fcmj" style="width: 300px; margin-left: 20px;" />
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
      </el-form>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
  import crudTShenbaoxingxi from '@/api/tShenbaoxingxi'
  import CRUD, { presenter, header, form, crud } from '@crud/crud'
  import rrOperation from '@crud/RR.operation'
  import crudOperation from '@crud/CRUD.operation'
  import udOperation from '@crud/UD.operation'
  import pagination from '@crud/Pagination'

  // crud交由presenter持有
  const defaultCrud = CRUD({ title: '申报信息', url: 'api/tShenbaoxingxi', sort: 'id,desc', crudMethod: { ...crudTShenbaoxingxi }})
  const defaultForm = { id: null, sbrid: null, fcid: null, htsj: null, wqsj: null, fcje: null, fclx: null, btje: null, btye: null, sqrlx: null, bak2: null, bak3: null, bak4: null, bak5: null, createId: null, createTime: null, updateId: null, updateTime: null, zjfh: null, bak1: null }
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
          fclx: [
            { required: true, message: '房产类型不能为空', trigger: 'blur' }
          ],
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
          fcid: [
            { required: true, message: '不动产权证号（二手房）', trigger: 'blur' }
          ],
          fcmj: [
            { required: true, message: '房产面积', trigger: 'blur' }
          ]
        }    }
    },
    methods: {
      // 获取数据前设置好接口地址
      [CRUD.HOOK.beforeRefresh]() {
        return true
      }
    }
  }
</script>

<style scoped>

</style>
