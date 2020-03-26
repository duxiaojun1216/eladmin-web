<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <el-input v-model="query.value" clearable placeholder="输入搜索内容" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <el-select v-model="query.type" clearable placeholder="类型" class="filter-item" style="width: 130px">
          <el-option v-for="item in queryTypeOptions" :key="item.key" :label="item.display_name" :value="item.key" />
        </el-select>
        <rrOperation :crud="crud" />
      </div>
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="资产类型">
            <el-select v-model="form.zclx" filterable placeholder="请选择">
              <el-option
                v-for="item in dict.house_type"
                :key="item.id"
                :label="item.label"
                :value="item.value" />
            </el-select>
          </el-form-item>
          <el-form-item label="起始时间">
            <el-date-picker v-model="form.qssj" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="终止时间">
            <el-date-picker v-model="form.zzsj" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="比例">
            <el-input v-model="form.bl" style="width: 370px;" />
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
        <el-table-column v-if="columns.visible('zclx')" prop="zclx" label="资产类型">
          <template slot-scope="scope">
            {{ dict.label.house_type[scope.row.zclx] }}
          </template>
        </el-table-column>
        <el-table-column v-if="columns.visible('qssj')" prop="qssj" label="起始时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.qssj) }}</span>
          </template>
        </el-table-column>
        <el-table-column v-if="columns.visible('zzsj')" prop="zzsj" label="终止时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.zzsj) }}</span>
          </template>
        </el-table-column>
        <el-table-column v-if="columns.visible('bl')" prop="bl" label="比例" />
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
        <el-table-column v-permission="['admin','tBzwh:edit','tBzwh:del']" label="操作" width="150px" align="center">
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
import crudTBzwh from '@/api/tBzwh'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

// crud交由presenter持有
const defaultCrud = CRUD({ title: 't_bzwh', url: 'api/tBzwh', sort: 'id,desc', crudMethod: { ...crudTBzwh }})
const defaultForm = { id: null, zclx: null, qssj: null, zzsj: null, bl: null, bak3: null, bak4: null, bak5: null, createId: null, createTime: null, updateId: null, updateTime: null }
export default {
  name: 'TBzwh',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(defaultCrud), header(), form(defaultForm), crud()],
  dicts: ['house_type'],
  data() {
    return {
      permission: {
        add: ['admin', 'tBzwh:add'],
        edit: ['admin', 'tBzwh:edit'],
        del: ['admin', 'tBzwh:del'],
        add: ['common', 'tBzwh:add'],
        edit: ['common', 'tBzwh:edit'],
        del: ['common', 'tBzwh:del']
      },
      rules: {
      },
      queryTypeOptions: [
        { key: 'zclx', display_name: '资产类型' }
      ]
    }
  },
  methods: {
    // 获取数据前设置好接口地址
    [CRUD.HOOK.beforeRefresh]() {
      const query = this.query
      if (query.type && query.value) {
        this.crud.params[query.type] = query.value
      }
      return true
    }
  }
}
</script>

<style scoped>

</style>
