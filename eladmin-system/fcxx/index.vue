<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="建筑类型">
            <el-input v-model="form.jzlx" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="产权年限">
            <el-input v-model="form.cqnx" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="开发商">
            <el-input v-model="form.kfs" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="面积">
            <el-input v-model="form.fcmj" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="建成时间">
            <el-date-picker v-model="form.jcsj" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="所处位置">
            <el-input v-model="form.scwz" style="width: 370px;" />
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
        <el-table-column v-if="columns.visible('jzlx')" prop="jzlx" label="建筑类型" />
        <el-table-column v-if="columns.visible('cqnx')" prop="cqnx" label="产权年限" />
        <el-table-column v-if="columns.visible('kfs')" prop="kfs" label="开发商" />
        <el-table-column v-if="columns.visible('fcmj')" prop="fcmj" label="面积" />
        <el-table-column v-if="columns.visible('jcsj')" prop="jcsj" label="建成时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.jcsj) }}</span>
          </template>
        </el-table-column>
        <el-table-column v-if="columns.visible('scwz')" prop="scwz" label="所处位置" />
        <el-table-column v-permission="['admin','tHouse:edit','tHouse:del']" label="操作" width="150px" align="center">
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
import crudTHouse from '@/api/tHouse'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

// crud交由presenter持有
const defaultCrud = CRUD({ title: '房产信息', url: 'api/tHouse', sort: 'id,desc', crudMethod: { ...crudTHouse }})
const defaultForm = { id: null, bak1: null, bak2: null, bak3: null, bak4: null, bak5: null, jzlx: null, cqnx: null, kfs: null, fcmj: null, jcsj: null, scwz: null, createId: null, createTime: null, updateId: null, updateTime: null }
export default {
  name: 'THouse',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(defaultCrud), header(), form(defaultForm), crud()],
  data() {
    return {
      permission: {
        add: ['admin', 'tHouse:add'],
        edit: ['admin', 'tHouse:edit'],
        del: ['admin', 'tHouse:del']
      },
      rules: {
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
