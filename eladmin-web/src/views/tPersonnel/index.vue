<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="id">
            <el-input v-model="form.id" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="电话" prop="phone">
            <el-input v-model="form.phone" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="身份类型" prop="type">
            <el-input v-model="form.type" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="form.mark" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="状态（是否启用）">
            <el-input v-model="form.state" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备用字段2">
            <el-input v-model="form.bak2" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备用字段3">
            <el-input v-model="form.bak3" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备用字段4">
            <el-input v-model="form.bak4" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备用字段5">
            <el-input v-model="form.bak5" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="姓名" prop="tname">
            <el-input v-model="form.tname" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="身份证号" prop="cardId">
            <el-input v-model="form.cardId" style="width: 370px;" />
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
        <el-table-column v-if="columns.visible('id')" prop="id" label="id" />
        <el-table-column v-if="columns.visible('phone')" prop="phone" label="电话" />
        <el-table-column v-if="columns.visible('type')" prop="type" label="身份类型" />
        <el-table-column v-if="columns.visible('mark')" prop="mark" label="备注" />
        <el-table-column v-if="columns.visible('state')" prop="state" label="状态（是否启用）" />
        <el-table-column v-if="columns.visible('bak2')" prop="bak2" label="备用字段2" />
        <el-table-column v-if="columns.visible('bak3')" prop="bak3" label="备用字段3" />
        <el-table-column v-if="columns.visible('bak4')" prop="bak4" label="备用字段4" />
        <el-table-column v-if="columns.visible('bak5')" prop="bak5" label="备用字段5" />
        <el-table-column v-if="columns.visible('tname')" prop="tname" label="姓名" />
        <el-table-column v-if="columns.visible('cardId')" prop="cardId" label="身份证号" />
        <el-table-column v-permission="['admin','tPersonnel:edit','tPersonnel:del']" label="操作" width="150px" align="center">
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
import crudTPersonnel from '@/api/tPersonnel'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

// crud交由presenter持有
const defaultCrud = CRUD({ title: '申报人员信息', url: 'api/tPersonnel', sort: 'id,desc', crudMethod: { ...crudTPersonnel }})
const defaultForm = { id: null, phone: null, type: null, createid: null, mark: null, state: null, bak2: null, bak3: null, bak4: null, bak5: null, createTime: null, tname: null, cardId: null, updateTime: null, updateId: null }
export default {
  name: 'TPersonnel',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(defaultCrud), header(), form(defaultForm), crud()],
  data() {
    return {
      permission: {
        add: ['admin', 'tPersonnel:add'],
        edit: ['admin', 'tPersonnel:edit'],
        del: ['admin', 'tPersonnel:del']
      },
      rules: {
        phone: [
          { required: true, message: '电话不能为空', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '身份类型不能为空', trigger: 'blur' }
        ],
        tname: [
          { required: true, message: '姓名不能为空', trigger: 'blur' }
        ],
        cardId: [
          { required: true, message: '身份证号不能为空', trigger: 'blur' }
        ]
      }}
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
