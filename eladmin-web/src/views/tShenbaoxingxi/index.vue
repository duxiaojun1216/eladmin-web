<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="申报人id">
            <el-input v-model="form.sbrid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="房产id">
            <el-input v-model="form.fcid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="合同时间" prop="htsj">
            <el-date-picker v-model="form.htsj" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="网签时间" prop="wqsj">
            <el-date-picker v-model="form.wqsj" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="房产金额">
            <el-input v-model="form.fcje" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="房产类型">
            <el-select v-model="form.fclx" filterable placeholder="请选择">
              <el-option
                v-for="item in dict.house_type"
                :key="item.id"
                :label="item.label"
                :value="item.value" />
            </el-select>
          </el-form-item>
          <el-form-item label="补贴金额">
            <el-input v-model="form.btje" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="补贴余额">
            <el-input v-model="form.btye" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="申请人类型">
            <el-select v-model="form.sqrlx" filterable placeholder="请选择">
              <el-option
                v-for="item in dict.peoson_type"
                :key="item.id"
                :label="item.label"
                :value="item.value" />
            </el-select>
          </el-form-item>
          <el-form-item label="创建人">
            <el-input v-model="form.createId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="创建时间">
            <el-input v-model="form.createTime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="修改人">
            <el-input v-model="form.updateId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="修改时间">
            <el-input v-model="form.updateTime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="住建复核状态">
            <el-input v-model="form.zjfh" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="bak1">
            <el-input v-model="form.bak1" style="width: 370px;" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
        <el-table-column type="selection" width="55" />
        <el-table-column v-if="columns.visible('sbrid')" prop="sbrid" label="申报人id" />
        <el-table-column v-if="columns.visible('fcid')" prop="fcid" label="房产id" />
        <el-table-column v-if="columns.visible('htsj')" prop="htsj" label="合同时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.htsj) }}</span>
          </template>
        </el-table-column>
        <el-table-column v-if="columns.visible('wqsj')" prop="wqsj" label="网签时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.wqsj) }}</span>
          </template>
        </el-table-column>
        <el-table-column v-if="columns.visible('fcje')" prop="fcje" label="房产金额" />
        <el-table-column v-if="columns.visible('fclx')" prop="fclx" label="房产类型">
          <template slot-scope="scope">
            {{ dict.label.house_type[scope.row.fclx] }}
          </template>
        </el-table-column>
        <el-table-column v-if="columns.visible('btje')" prop="btje" label="补贴金额" />
        <el-table-column v-if="columns.visible('btye')" prop="btye" label="补贴余额" />
        <el-table-column v-if="columns.visible('sqrlx')" prop="sqrlx" label="申请人类型">
          <template slot-scope="scope">
            {{ dict.label.peoson_type[scope.row.sqrlx] }}
          </template>
        </el-table-column>
        <el-table-column v-if="columns.visible('createId')" prop="createId" label="创建人" />
        <el-table-column v-if="columns.visible('createTime')" prop="createTime" label="创建时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column v-if="columns.visible('updateId')" prop="updateId" label="修改人" />
        <el-table-column v-if="columns.visible('updateTime')" prop="updateTime" label="修改时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.updateTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column v-if="columns.visible('zjfh')" prop="zjfh" label="住建复核状态" />
        <el-table-column v-if="columns.visible('bak1')" prop="bak1" label="bak1" />
        <el-table-column v-permission="['admin','tShenbaoxingxi:edit','tShenbaoxingxi:del']" label="操作" width="150px" align="center">
          <template slot-scope="scope">
            <udOperation
              :data="scope.row"
              :permission="permission"
            />
          </template>
        </el-table-column>
      </el-table>
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
          { required: true, message: '网签时间不能为空', trigger: 'blur' }
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
