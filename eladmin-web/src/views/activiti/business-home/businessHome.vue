<style lang="less">
@import "../../../styles/table-common.less";
@import "./businessHome.less";
</style>
<template>
  <div class="search">
 
    <!-- Drawer抽屉式选择流程draggable -->
    <Drawer title="业务类型选择" closable v-model="processModalVisible" width="818" >
      <div class="apply-operation">
        <!-- <div>
          <Form ref="searchProcessForm" :model="searchProcessForm" inline :label-width="70">
            <Form-item label="流程名称" prop="name">
              <Input
                type="text"
                v-model="searchProcessForm.name"
                clearable
                placeholder="请输入流程名"
                style="width: 140px"
              />
            </Form-item>
            <Form-item label="所属分类" prop="category">
              <Cascader
                v-model="selectCat"
                :data="category"
                :load-data="loadData"
                @on-change="handleChangeCat"
                change-on-select
                filterable
                clearable
                placeholder="请选择分类"
                style="width: 140px"
              ></Cascader>
            </Form-item>
            <Form-item style="margin-left:-70px;" class="br">
              <Button @click="getProcessList" type="primary" icon="ios-search">搜索</Button>
              <Button @click="handleResetProcess">重置</Button>
              <i-switch
                size="large"
                v-model="searchProcessForm.showLatest"
                @on-change="getProcessList"
                style="margin:0 5px"
              >
                <span slot="open">最新</span>
                <span slot="close">全部</span>
              </i-switch>
            </Form-item>
          </Form>
        </div>-->
        <div style="float:right">
          <RadioGroup v-model="showType" type="button">
            <Radio title="缩略图" label="thumb">
              <Icon type="ios-apps"></Icon>
            </Radio>
            <Radio title="列表" label="list">
              <Icon type="md-list"></Icon>
            </Radio>
          </RadioGroup>
        </div>
      </div>
      <div class="process-wrapper" v-if="showType=='thumb'">
        <Card v-for="(item, i) in processData" :key="i" class="process-card">
          <div class="content" @click="chooseProcess(item)">
            <div class="other">
              <div class="name">{{i+1}}. {{item.name}}</div>
              <div class="key">{{item.description}}</div>
              <div class="info">版本：v.{{item.version}} 所属分类：{{item.categoryTitle}}</div>
            </div>
          </div>
        </Card>
        <Spin fix v-if="processLoading" />
      </div>
      <Table
        :loading="processLoading"
        border
        :columns="processColumns"
        :data="processData"
        ref="processTable"
        v-if="showType=='list'"
      ></Table>
    </Drawer>
  </div>
</template>

<script>
import {
  getProcessDataList,
  getFirstNode,
  getBusinessDataList,
  applyBusiness,
  deleteBusiness,
  cancelApply,
  initActCategory,
  loadActCategory
} from "@/api/activiti";
export default {
  name: "apply-manage",
  data() {
    return {
      openSearch: true,
      openTip: true,
      loading: true, // 表单加载状态
      processModalVisible: true,
      selectCat: [],
      category: [],
      error: "",
      showAssign: false,
      isGateway: false,
      searchProcessForm: {
        showLatest: true,
        name: "",
        status: "1", // 激活状态
        pageNumber: 1, // 当前页数
        pageSize: 1000, // 页面大小
        sort: "createTime", // 默认排序字段
        order: "desc" // 默认排序方式
      },
      cancelForm: {
        reason: ""
      },
      modalCancelVisible: false,
      processLoading: false,
      processColumns: [
        {
          type: "index",
          width: 60,
          align: "center"
        },
        {
          title: "名称",
          key: "name",
          width: 150,
          sortable: true
        },
        {
          title: "备注描述",
          key: "description",
          width: 150,
          sortable: true
        },
        {
          title: "所属分类",
          key: "categoryTitle",
          width: 150,
          sortable: true
        },
        {
          title: "版本",
          key: "version",
          align: "center",
          sortable: true,
          render: (h, params) => {
            let re = "";
            if (params.row.version) {
              re = "v." + params.row.version;
            }
            return h("div", re);
          }
        },
        {
          title: "操作",
          key: "action",
          width: 135,
          align: "center",
          fixed: "right",
          render: (h, params) => {
            return h("div", [
              h(
                "Button",
                {
                  props: {
                    type: "info",
                    size: "small"
                  },
                  on: {
                    click: () => {
                      this.chooseProcess(params.row);
                    }
                  }
                },
                "选择该流程"
              )
            ]);
          }
        }
      ],
      processData: [],
      showType: "thumb",
      selectCount: 0, // 多选计数
      selectList: [], // 多选数据
      drop: false, // 搜索展开标识
      dropDownContent: "展开", // 搜索展开标识文字
      dropDownIcon: "ios-arrow-down", //搜索展开图标
      searchForm: {
        // 搜索框对应data对象
        title: "",
        status: "",
        result: "",
        pageNumber: 1, // 当前页数
        pageSize: 10, // 页面大小
        startDate: "",
        endDate: "",
        sort: "createTime", // 默认排序字段
        order: "desc" // 默认排序方式
      },
      userLoading: false,
      modalVisible: false, // 添加或编辑显示
      selectDate: null,
      form: {
        sendMessage: true,
        sendSms: true,
        sendEmail: true,
        procDefId: "",
        assignees: [],
        priority: "0"
      },
      formValidate: {
        // 表单验证规则
        procDefId: [{ required: true, message: "不能为空", trigger: "blur" }],
        priority: [{ required: true, message: "不能为空", trigger: "blur" }]
      },
      submitLoading: false, // 添加或编辑提交状态
      columns: [
        // 表头
        {
          type: "selection",
          width: 60,
          align: "center"
        },
        {
          type: "index",
          width: 60,
          align: "center"
        },
        {
          title: "标题",
          key: "title",
          minWidth: 150,
          sortable: true
        },
        {
          title: "所属流程",
          key: "processName",
          width: 150,
          tooltip: true
        },
        {
          title: "当前审批环节",
          key: "currTaskName",
          width: 150,
          tooltip: true
        },
        {
          title: "状态",
          key: "status",
          align: "center",
          width: 120,
          sortable: true,
          render: (h, params) => {
            let text = "未知",
              color = "";
            if (params.row.status == 0) {
              text = "草稿";
              color = "default";
            } else if (params.row.status == 1) {
              text = "处理中";
              color = "orange";
            } else if (params.row.status == 2) {
              text = "已结束";
              color = "blue";
            } else if (params.row.status == 3) {
              text = "已撤回";
              color = "magenta";
            }
            return h("div", [
              h(
                "Tag",
                {
                  props: {
                    color: color
                  }
                },
                text
              )
            ]);
          }
        },
        {
          title: "结果",
          key: "result",
          align: "center",
          width: 120,
          sortable: true,
          render: (h, params) => {
            let text = "未知",
              color = "";
            if (params.row.result == 0) {
              text = "未提交";
              color = "default";
            } else if (params.row.result == 1) {
              text = "处理中";
              color = "orange";
            } else if (params.row.result == 2) {
              text = "已通过";
              color = "green";
            } else if (params.row.result == 3) {
              text = "已驳回";
              color = "red";
            }
            return h("div", [
              h(
                "Tag",
                {
                  props: {
                    color: color
                  }
                },
                text
              )
            ]);
          }
        },
        {
          title: "创建时间",
          key: "createTime",
          width: 180,
          sortType: "desc",
          sortable: true
        },
        {
          title: "提交申请时间",
          key: "applyTime",
          width: 180,
          sortable: true
        },
        {
          title: "操作",
          key: "action",
          align: "center",
          width: 265,
          fixed: "right",
          render: (h, params) => {
            let re = "";
            if (params.row.status == 0) {
              re = [
                h(
                  "Button",
                  {
                    props: {
                      type: "primary",
                      size: "small"
                    },
                    style: {
                      marginRight: "5px"
                    },
                    on: {
                      click: () => {
                        this.apply(params.row);
                      }
                    }
                  },
                  "提交申请"
                ),
                h(
                  "Button",
                  {
                    props: {
                      size: "small"
                    },
                    style: {
                      marginRight: "5px"
                    },
                    on: {
                      click: () => {
                        this.edit(params.row);
                      }
                    }
                  },
                  "编辑"
                ),
                h(
                  "Button",
                  {
                    props: {
                      type: "error",
                      size: "small"
                    },
                    on: {
                      click: () => {
                        this.remove(params.row);
                      }
                    }
                  },
                  "删除"
                )
              ];
            } else if (params.row.status == 1) {
              re = [
                h(
                  "Button",
                  {
                    props: {
                      size: "small",
                      type: "warning",
                      icon: "ios-redo"
                    },
                    style: {
                      marginRight: "5px"
                    },
                    on: {
                      click: () => {
                        this.cancel(params.row);
                      }
                    }
                  },
                  "撤回"
                ),
                h(
                  "Button",
                  {
                    props: {
                      size: "small",
                      type: "info"
                    },
                    style: {
                      marginRight: "5px"
                    },
                    on: {
                      click: () => {
                        this.history(params.row);
                      }
                    }
                  },
                  "查看进度"
                ),
                h(
                  "Button",
                  {
                    props: {
                      size: "small"
                    },
                    style: {
                      marginRight: "5px"
                    },
                    on: {
                      click: () => {
                        this.detail(params.row);
                      }
                    }
                  },
                  "表单数据"
                )
              ];
            } else if (
              (params.row.status == 2 && params.row.result == 3) ||
              params.row.status == 3
            ) {
              re = [
                h(
                  "Button",
                  {
                    props: {
                      type: "primary",
                      size: "small"
                    },
                    style: {
                      marginRight: "5px"
                    },
                    on: {
                      click: () => {
                        this.apply(params.row);
                      }
                    }
                  },
                  "重新申请"
                ),
                h(
                  "Button",
                  {
                    props: {
                      size: "small"
                    },
                    style: {
                      marginRight: "5px"
                    },
                    on: {
                      click: () => {
                        this.edit(params.row);
                      }
                    }
                  },
                  "编辑"
                ),
                h(
                  "Button",
                  {
                    props: {
                      size: "small",
                      type: "info"
                    },
                    style: {
                      marginRight: "5px"
                    },
                    on: {
                      click: () => {
                        this.history(params.row);
                      }
                    }
                  },
                  "审批历史"
                )
              ];
            } else {
              re = [
                h(
                  "Button",
                  {
                    props: {
                      size: "small"
                    },
                    style: {
                      marginRight: "5px"
                    },
                    on: {
                      click: () => {
                        this.detail(params.row);
                      }
                    }
                  },
                  "表单数据"
                ),
                h(
                  "Button",
                  {
                    props: {
                      size: "small",
                      type: "info"
                    },
                    style: {
                      marginRight: "5px"
                    },
                    on: {
                      click: () => {
                        this.history(params.row);
                      }
                    }
                  },
                  "审批历史"
                )
              ];
            }

            return h("div", re);
          }
        }
      ],
      data: [], // 表单数据
      total: 0, // 表单数据总数
      assigneeList: [],
      dictPriority: [{title:'测试',value:'0'}]
    };
  },
  methods: {
    init() {
      this.getDataList();
      this.getProcessList();
      this.initCategoryData();
    },
    getProcessList() {
      this.processLoading = true;
      getProcessDataList(this.searchProcessForm).then(res => {
        this.processLoading = false;
        if (res.success) {
          this.processData = res.result.content;
        }
      });
    },
    initCategoryData() {
      initActCategory().then(res => {
        if (res.success) {
          res.result.forEach(function(e) {
            if (e.isParent) {
              e.value = e.id;
              e.label = e.title;
              e.loading = false;
              e.children = [];
            } else {
              e.value = e.id;
              e.label = e.title;
            }
            if (e.status == -1) {
              e.label = "[已禁用] " + e.label;
              e.disabled = true;
            }
          });
          this.category = res.result;
        }
      });
    },
    loadData(item, callback) {
      item.loading = true;
      loadActCategory(item.value).then(res => {
        item.loading = false;
        if (res.success) {
          res.result.forEach(function(e) {
            if (e.isParent) {
              e.value = e.id;
              e.label = e.title;
              e.loading = false;
              e.children = [];
            } else {
              e.value = e.id;
              e.label = e.title;
            }
            if (e.status == -1) {
              e.label = "[已禁用] " + e.label;
              e.disabled = true;
            }
          });
          item.children = res.result;
          callback();
        }
      });
    },
    handleChangeCat(value, selectedData) {
      let categoryId = "";
      // 获取最后一个值
      if (value && value.length > 0) {
        categoryId = value[value.length - 1];
      }
      this.searchProcessForm.categoryId = categoryId;
    },
    dropDown() {
      if (this.drop) {
        this.dropDownContent = "展开";
        this.dropDownIcon = "ios-arrow-down";
      } else {
        this.dropDownContent = "收起";
        this.dropDownIcon = "ios-arrow-up";
      }
      this.drop = !this.drop;
    },
    handleResetProcess() {
      this.$refs.searchProcessForm.resetFields();
      // 重新加载数据
      this.getProcessList();
    },
    changeSort(e) {
      this.searchForm.sort = e.key;
      this.searchForm.order = e.order;
      if (e.order == "normal") {
        this.searchForm.order = "";
      }
      this.getDataList();
    },
    changePage(v) {
      this.searchForm.pageNumber = v;
      this.getDataList();
      this.clearSelectAll();
    },
    changePageSize(v) {
      this.searchForm.pageSize = v;
      this.getDataList();
    },
    selectDateRange(v) {
      if (v) {
        this.searchForm.startDate = v[0];
        this.searchForm.endDate = v[1];
      }
    },
    getDataList() {
      this.loading = true;
      // 避免后端默认值
      if (!this.searchForm.status) {
        this.searchForm.status = " ";
      }
      if (!this.searchForm.result) {
        this.searchForm.result = " ";
      }
      getBusinessDataList(this.searchForm).then(res => {
        this.loading = false;
        if (res.success) {
          this.data = res.result.content;
          this.total = res.result.totalElements;
        }
      });
    },
    handleSearch() {
      this.searchForm.pageNumber = 1;
      this.searchForm.pageSize = 10;
      this.getDataList();
    },
    handleReset() {
      this.$refs.searchForm.resetFields();
      this.searchForm.pageNumber = 1;
      this.searchForm.pageSize = 10;
      this.selectDate = null;
      this.searchForm.startDate = "";
      this.searchForm.endDate = "";
      // 重新加载数据
      this.getDataList();
    },
    handelCancel() {
      this.modalVisible = false;
    },
    handelSubmit() {
      this.$refs.form.validate(valid => {
        if (valid) {
          if (this.showAssign && this.form.assignees.length < 1) {
            this.error = "请至少选择一个审批人";
            return;
          } else {
            this.error = "";
          }
          this.submitLoading = true;
          applyBusiness(this.form).then(res => {
            this.submitLoading = false;
            if (res.success) {
              this.$Message.success("操作成功");
              this.getDataList();
              this.modalVisible = false;
            }
          });
        }
      });
    },
    add() {
      this.processModalVisible = true;
    },
    chooseProcess(v) {
      if (!v.routeName) {
        this.$Message.warning(
          "该流程信息未完善，请于流程管理中编辑流程完善信息"
        );
        return;
      }
      this.processModalVisible = false;
      let query = { type: 0, backRoute:this.$route.name, procDefId: v.id };
      this.$router.push({
        name: v.routeName,
        query: query
      });
    },
    edit(v) {
      if (!v.routeName) {
        this.$Message.error("表单路由名为空");
        return;
      }
      let query = { type: 1, id: v.tableId, backRoute: this.$route.name };
      this.$router.push({
        name: v.routeName,
        query: query
      });
    },
    detail(v) {
      if (!v.routeName) {
        this.$Message.error("表单路由名为空");
        return;
      }
      let query = { type: 2, id: v.tableId, backRoute: this.$route.name };
      this.$router.push({
        name: v.routeName,
        query: query
      });
    },
    apply(v) {
      if (!v.procDefId || v.procDefId == "null") {
        this.$Message.error("流程定义为空");
        return;
      }
      this.form.id = v.id;
      this.form.procDefId = v.procDefId;
      this.form.title = v.title;
      // 加载审批人
      this.userLoading = true;
      getFirstNode(v.procDefId).then(res => {
        this.userLoading = false;
        if (res.success) {
          if (res.result.type == 3 || res.result.type == 4) {
            this.isGateway = true;
            this.form.firstGateway = true;
            this.showAssign = false;
            this.error = "";
            return;
          }
          this.form.firstGateway = false;
          this.isGateway = false;
          if (res.result.users && res.result.users.length > 0) {
            this.error = "";
            this.assigneeList = res.result.users;
            // 默认勾选
            let ids = [];
            res.result.users.forEach(e => {
              ids.push(e.id);
            });
            this.form.assignees = ids;
            this.showAssign = true;
          } else {
            this.form.assignees = [];
            this.showAssign = true;
            this.error = '请进入"流程管理"为审批节点分配候选审批人员';
          }
        }
      });
      this.modalVisible = true;
    },
    cancel(v) {
      this.cancelForm.id = v.id;
      this.cancelForm.procInstId = v.procInstId;
      this.modalCancelVisible = true;
    },
    handelSubmitCancel() {
      this.submitLoading = true;
      cancelApply(this.cancelForm).then(res => {
        this.submitLoading = false;
        if (res.success) {
          this.$Message.success("操作成功");
          this.getDataList();
          this.modalCancelVisible = false;
        }
      });
    },
    history(v) {
      if (!v.procInstId) {
        this.$Message.error("流程实例ID不存在");
        return;
      }
      let query = { id: v.procInstId, backRoute: this.$route.name };
      this.$router.push({
        name: "historic_detail",
        query: query
      });
    },
    remove(v) {
      this.$Modal.confirm({
        title: "确认删除",
        // 记得确认修改此处
        content: "您确认要删除 " + v.title + " ?",
        loading: true,
        onOk: () => {
          // 删除
          deleteBusiness(v.id).then(res => {
            this.$Modal.remove();
            if (res.success) {
              this.$Message.success("操作成功");
              this.getDataList();
            }
          });
        }
      });
    },
    showSelect(e) {
      this.selectList = e;
      this.selectCount = e.length;
    },
    clearSelectAll() {
      this.$refs.table.selectAll(false);
    },
    delAll() {
      if (this.selectCount <= 0) {
        this.$Message.warning("您还未选择要删除的数据");
        return;
      }
      this.$Modal.confirm({
        title: "确认删除",
        content: "您确认要删除所选的 " + this.selectCount + " 条数据?",
        loading: true,
        onOk: () => {
          let ids = "";
          this.selectList.forEach(function(e) {
            ids += e.id + ",";
          });
          ids = ids.substring(0, ids.length - 1);
          // 批量删除
          deleteBusiness(ids).then(res => {
            this.$Modal.remove();
            if (res.success) {
              this.$Message.success("操作成功");
              this.clearSelectAll();
              this.getDataList();
            }
          });
        }
      });
    }
  },
  mounted() {
    this.init();
  },
  watch: {
    // 监听路由变化
    $route(to, from) {
      if (to.name == "apply-manage") {
        this.getDataList();
        this.getProcessList();
      }
    }
  }
};
</script>