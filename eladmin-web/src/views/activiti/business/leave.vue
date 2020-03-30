<style lang="less">
.form_item{
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
}
.header_p{
    display: inline-block;
    width: 100%;
    height: 40px!important;
    line-height: 40px!important;
	font-weight: bold!important;
    font-size: 25px!important;
    color: #17233d;
    font-weight: bold;
    font-weight: 500;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    text-align: center;
}
.ivu-divider-horizontal.ivu-divider-with-text-left:before {
    top: 50%;
    width: 0.01%;
}
.ivu-divider-inner-text{
    display: table;
    white-space: nowrap;
    text-align: left;
    background: 0 0;
    font-weight: 500;
    color: #0553f9;
    /* font-size: 18px; */
    font-weight: bold;
    /* margin: 16px 0; */
	padding: 0px;
}
.ivu-divider-horizontal {
    display: block;
    height: 1px;
    width: 750px;
    /* min-width: 100%; */
    margin: 24px 0;
    clear: both;
}
.ivu-picker-panel-body{
   width:250px
}
.ivu-card-head {
    border-bottom: 1px solid #e8eaec;
    padding: 14px 16px;
    line-height: 1;
    padding-bottom: 2px;
}
</style>
<template>
  <div class="search">
    <Card style="width: 800px;margin: auto;">
      <p slot="title" class="header_p">
        <span v-if="type==0">泸州市置业补贴申请登记</span>
        <span v-else-if="type==1">填报置业补贴申请表</span>
        <span v-else>查看置业补贴申请</span>
      </p>
	  <p slot="title" style="text-align:left ;font-size:14px;width: 250px;">
        <span v-if="type==0">编号：JYTY032020001</span>
      </p>
	  <p slot="title" style="text-align: right;font-size:14px;width: 250px;float:right;margin-right: 15px;">
        <span v-if="type==0">日期：2020年4月3日</span>
      </p>
      <Row style="width: 800px;margin: auto;">
        <Form
          ref="form"
          :model="form"
          :label-width="100"
          :rules="formValidate"
          style="position:relative"
        >
		
          <FormItem label="申请人" prop="title" v-if="type!=0&&type!=1">{{form.createBy}}</FormItem>
          <FormItem class="form_item" label="申请人姓名" prop="title">
            <Input v-model="form.title" style="width:250px" />
          </FormItem>
		  <FormItem class="form_item" label="身份证号" prop="title">
            <Input v-model="form.title" style="width:250px" />
          </FormItem>
		  <FormItem class="form_item" label="申请人类型" prop="title">
            <Input v-model="form.title" style="width:250px" />
          </FormItem>
		  <FormItem class="form_item" label="联系电话" prop="title">
            <Input v-model="form.title" style="width:250px" />
          </FormItem>
		  <FormItem class="form_item" label="户籍地址" prop="description" style="width:950px">
            <Input           
              v-model="form.description"            
              placeholder="请输入详细情况"
              style="width: 650px"
            />
		  </FormItem>
		   <FormItem class="form_item" label="对象备注" prop="description" style="width:950px">
            <Input
              type="textarea"
              v-model="form.description"
              :rows="3"
              placeholder="请输入详细情况"
              style="width: 650px"
            />
		  </FormItem>
		  <Divider orientation="left">①购置资产信息</Divider>
		  <FormItem class="form_item" label="资产类别" prop="type">
            <Select v-model="form.type" placeholder="请选择" style="width: 250px">
              <Option v-for="(item, i) in dictType" :key="i" :value="item.value">{{item.title}}</Option>
            </Select>		  
          </FormItem>
         <FormItem class="form_item" label="交易类型" prop="title" >
          <RadioGroup v-model="animal" style="width: 250px">
            <Radio label="一手房产"></Radio>
            <Radio label="二手房产"></Radio>
           </RadioGroup>
          </FormItem>
		  <FormItem class="form_item" label="购置时间" :error="errorMsg" prop="type">
            <DatePicker
              v-model="selectDate"           
              type="date"
              format="yyyy-MM-dd HH:mm"
              clearable            
              placeholder="选择购置时间"
              style="width: 250px"
            ></DatePicker>
          </FormItem>
		  <FormItem class="form_item" label="备案时间" :error="errorMsg" prop="type">
            <DatePicker
              v-model="selectDate"           
              type="date"
              format="yyyy-MM-dd HH:mm"
              clearable            
              placeholder="选择购置时间"
              style="width: 250px"
            ></DatePicker>
          </FormItem>
		  <FormItem class="form_item" label="购房面积" prop="title">
            <Input v-model="form.title" style="width:250px" />
          </FormItem>
		  <FormItem class="form_item" label="发票总金额" prop="type">
            <Input v-model="form.title" style="width:250px" />
          </FormItem>
		  <FormItem class="form_item" label="资产地址" prop="description" style="width:950px">
            <Input           
              v-model="form.description"            
              placeholder="示例：xx区xx路xx号xx栋xx单元xx号"
              style="width: 650px"
            />
			</FormItem>
          <FormItem class="form_item" label="资产备注" prop="description" style="width:950px">
            <Input
              type="textarea"
              v-model="form.description"
              :rows="3"
              placeholder="请输入详细情况"
              style="width: 650px"
            />
          </FormItem>
		  <Divider orientation="left">②补贴核算</Divider>
          <Divider orientation="left">③个人材料</Divider>
		  <Divider orientation="left">④部门材料</Divider>
		  
		  
          <Form-item class="br" style="margin-right:50px;float:right">
            <Button
              type="primary"
              :loading="submitLoading"
              @click="handelSubmit"
              v-if="type==0||type==1"
            >保存并关闭</Button>
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
  getLeaveData,
  addLeave,
  updateLeave,
  applyLeave
} from "@/api/activiti";
import printJS from "print-js";
export default {
  name: "leave-notcache",
  data() {
    return {
      type: 0,
      loading: false, // 表单加载状态
      modalVisible: false,
      selectDate: null,
      dictType:[{title:'测试',value:'0'}] ,
	  //this.$store.state.dict.leaveType,
      form: {
        // 添加或编辑表单对象初始化数据
        type: "",
        title: "",
        description: "",
        startDate: "",
        endDate: "",
        duration: 0
      },
      formValidate: {
        // 表单验证规则
        type: [{ required: true, message: "类型不能为空", trigger: "blur" }],
        title: [{ required: true, message: "标题不能为空", trigger: "blur" }],
        description: [
          { required: true, message: "原因不能为空", trigger: "blur" }
        ]
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
      getLeaveData(this.form.id).then(res => {
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