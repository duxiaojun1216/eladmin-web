<template>
	<div class="search">
		<Card>
			<Row v-show="openSearch" @keydown.enter.native="handleSearch">
				<Form ref="searchForm" :model="searchForm" inline :label-width="70">
					<Form-item label="规则名称">
						<Input type="text" v-model="searchForm.zclx" placeholder="请输入" clearable style="width: 200px" />
					</Form-item>
					<Form-item style="margin-left:-35px;" class="br">
						<Button @click="handleSearch" type="primary" icon="ios-search">搜索</Button>
						<Button @click="handleReset">重置</Button>
					</Form-item>
				</Form>
			</Row>
			<Row class="operation">
				<Button @click="addData" type="primary" icon="md-add">新增</Button>
				<Button @click="delAll" icon="md-trash">批量删除</Button>
				<Button @click="getDataList" icon="md-refresh">刷新</Button>
				<Button type="dashed" @click="openSearch=!openSearch">{{openSearch ? '关闭搜索' : '开启搜索'}}</Button>
				<Button type="dashed" @click="openTip=!openTip">{{openTip ? '关闭提示' : '开启提示'}}</Button>
			</Row>
			<Row v-show="openTip">
				<Alert show-icon>
					已选择
					<span class="select-count">{{selectCount}}</span> 项
					<a class="select-clear" @click="clearSelectAll">清空</a>
				</Alert>
			</Row>
			<Row>
				<Table :loading="loading" border :columns="columns" sortable="custom" :data="data" @on-sort-change="changeSort" @on-selection-change="showSelect" ref="table"></Table>
			</Row>
			<Row type="flex" justify="end" class="page">
				<Page :current="searchForm.pageNumber" :total="total" :page-size="searchForm.pageSize" @on-change="changePage" @on-page-size-change="changePageSize" :page-size-opts="[10,20,50]" size="small" show-total show-elevator show-sizer></Page>
			</Row>
		</Card>
		<!-- 操作 -->
		<Modal :title="modalTaskTitle" v-model="modalTaskVisible" :mask-closable="false" :width="500">
			<Form ref="form" :model="form" :rules="rules" size="small" :label-width="80">
				<FormItem label="资产类型">
					<Select v-model="form.zclx" placeholder="请选择">
						<Option v-for="item in dictPriority" :key="item.id" :label="item.label" :value="item.value" />
					</Select>
				</FormItem>
				<FormItem label="起始时间">
					<DatePicker v-model="form.qssj" type="date" format="yyyy-MM-dd" :key="form.qssj" placeholder="选择起始时间" style="width: 100%"></DatePicker>
				</FormItem>
				<FormItem label="终止时间">
					<DatePicker v-model="form.zzsj" type="date" format="yyyy-MM-dd" :key="form.zzsj" placeholder="选择终止时间" style="width: 100%"></DatePicker>
				</FormItem>
				<FormItem label="所属区域">
					<Select v-model="form.ssqy" filterable placeholder="请选择">
						<Option v-for="item1 in depts" :key="item1.id" :label="item1.label" :value="''+item1.id"></Option>
					</Select>
				</FormItem>
				<FormItem label="标准">
					<Input v-model="form.bz" style="width: 100%;" />
				</FormItem>
			</Form>
			<div slot="footer">
				<Button type="text" @click="modalTaskVisible=false">取消</Button>
				<Button type="primary" :loading="submitLoading" @click="handelSubmit">提交</Button>
			</div>
		</Modal>
	</div>
</template>
<script>
	import { getDictDataByType } from "@/api/index";
	import { get, add, edit, del, getDeptsByPid, getTBzwhsById } from "@/api/tBzwh";
	export default {
		data() {
			return {
				name: 'TBzwh',
				openSearch: true,
				openTip: true,
				loading: true, // 表单加载状态
				modalTaskVisible: false,
				userLoading: false,
				backLoading: false,
				userList: [],
				selectCount: 0, // 多选计数
				selectList: [], // 多选数据
				submitLoading: false, // 添加或编辑提交状态
				modalTaskTitle: "", //弹框标题
				dictPriority: [], //数据字典
				depts: [], //区域集合
				formF: {
					id: null,
					zclx: "",
					qssj: "",
					zzsj: "",
					bak4: "",
					bak5: "",
					createId: "",
					createTime: "",
					updateId: "",
					updateTime: "",
					ssqy: "",
					bz: ""
				},
				form: {
					id: null,
					zclx: "",
					qssj: "",
					zzsj: "",
					bak4: "",
					bak5: "",
					createId: "",
					createTime: "",
					updateId: "",
					updateTime: "",
					ssqy: "",
					bz: ""
				},
				rules: {},
				searchForm: {
					// 搜索框对应data对象
					zclx: '',
					pageNumber: 1, // 当前页数
					pageSize: 10 // 页面大小
				},
				columns: [
					// 表头
					{
						type: 'selection',
						width: 60,
						align: 'center'
					},
					{
						title: '资产类型',
						width: 160,
						key: 'zclx',
						align: 'center',
						render: (h, params) => {
							let re = "";
							if(params.row.zclx == null) {
								return re;
							}
							if(params.row.zclx == "1") {
								re = [
									h(
										'div', {},
										"标准化厂房"
									)
								];
							} else if(params.row.zclx == "2") {
								re = [
									h(
										'div', {},
										"新建营业用房"
									)
								];
							} else if(params.row.zclx == "3") {
								re = [
									h(
										'div', {},
										"新建自住住房"
									)
								];
							} else if(params.row.zclx == "4") {
								re = [
									h(
										'div', {},
										"一手车位（车库）"
									)
								];
							} else if(params.row.zclx == "5") {
								re = [
									h(
										'div', {},
										"二手营业用房"
									)
								];
							} else if(params.row.zclx == "6") {
								re = [
									h(
										'div', {},
										"二手自住住房"
									)
								];
							}
							return re;
						}
					},
					{
						title: '标准(元/个 )/比例',
						key: 'bz',
						minWidth: 160,
						align: 'center',
					},
					{
						title: '所属区域',
						key: 'ssqy',
						width: 150,
						align: 'center',
						render: (h, params) => {
							if(this.depts == null || this.depts.length <= 0 || params.row.ssqy == null) {
								return h('div', [
									h(
										'div', {},
										params.row.ssqy
									)
								]);
							}
							let newArr = this.depts.filter((item, index, arr) => {
								return item.id == parseInt(params.row.ssqy)
							});
							if(newArr == undefined || newArr == null || newArr.length <= 0) {
								return h('div', [
									h(
										'div', {},
										params.row.ssqy
									)
								]);
							}
							return h('div', [
								h(
									'div', {},
									newArr[0].name
								)
							]);
						}

					},
					{
						title: '起始时间',
						key: 'qssj',
						width: 180,
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h(
									'div', {},
									this.formartDate(params.row.qssj, "yyyy-MM-dd")
								)
							]);
						}
					},

					{
						title: '终止时间',
						key: 'zzsj',
						width: 180,
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h(
									'div', {},
									this.formartDate(params.row.zzsj, "yyyy-MM-dd")
								)
							]);
						}
					},
					{
						title: '创建时间',
						key: 'createTime',
						width: 180,
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h(
									'div', {},
									this.formartDate(params.row.createTime)
								)
							]);
						}
					},
					{
						title: '修改时间',
						key: 'updateTime',
						width: 180,
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h(
									'div', {},
									this.formartDate(params.row.updateTime)
								)
							]);
						}
					},
					{
						title: '操作',
						key: 'action',
						align: 'center',
						width: 180,
						fixed: 'right',
						render: (h, params) => {
							return h('div', [

								h(
									'Button', {
										props: {
											size: 'small',
											type: 'info'
										},
										style: {
											marginRight: '5px'
										},
										on: {
											click: () => {
												this.updateData(params.row);
											}
										}
									},
									'修改'
								),
								h(
									'Button', {
										props: {
											size: 'small'
										},
										style: {
											marginRight: '5px'
										},
										on: {
											click: () => {
												this.deleteData(params.row);
											}
										}
									},
									'删除'
								)
							]);
						}
					}
				],
				data: [], // 表单数据
				total: 0, // 表单数据总数
			}
		},
		methods: {
			// 获取数据前设置好接口地址
			init() {
				this.form = this.formF;
				this.getDepts();
				this.getDictDataType();
				this.getDataList();
			},
			//分页查询
			getDataList() {
				this.loading = true;
				this.searchForm.id = null;
				get(this.searchForm).then(res => {
					if(res.success) {
						this.loading = false;
						this.data = res.result.content;
						this.total = res.result.totalElements;
					}
				});
			},
			//获取字典
			getDictDataType() {
				let _this = this;
				getDictDataByType('house_type').then(res => {
					if(res.success) {
						_this.dictPriority = res.result;
					}
				});
			},
			//获取区域
			getDepts() {
				let _this = this;
				getDeptsByPid({
					pid: 1
				}).then(res => {
					_this.depts = res.content;
				});
			},
			//查询
			searchUser(v) {
				if(!v) {
					return;
				}
				this.userLoading = true;
				searchUserByName(v).then(res => {
					this.userLoading = false;
					if(res.success) {
						this.userList = res.result;
					}
				});
			},

			changeSort(e) {
				this.searchForm.sort = e.key;
				this.searchForm.order = e.order;
				if(e.order == 'normal') {
					this.searchForm.order = '';
				}
				this.getDataList();
			},
			//新增
			addData(v) {
				this.form = this.formF;
				this.modalTaskTitle = '添加标准';
				this.form.id = null;
				this.modalTaskVisible = true;

			},
			//编辑
			updateData(v) {
				this.form = this.formF;
				this.modalTaskTitle = '编辑标准';
				this.form.id = v.id;
				this.modalTaskVisible = true;
				this.searchById(v);
			},
			//通过id查询
			searchById(v) {
				if(!v) {
					return;
				}
				getTBzwhsById({
					id: v.id
				}).then(res => {
					if(res.success) {
						this.loading = false;
						this.form = res.result;
						this.form.zzsj = this.formartDate(this.form.zzsj, "yyyy-MM-dd");
						this.form.qssj = this.formartDate(this.form.qssj, "yyyy-MM-dd");

					}
				});
			},
			//保存表单
			handelSubmit(form) {
				this.userLoading = true;
				if(this.form.id == null) {
					add(this.form).then(res => {
						this.userLoading = false;
						if(res.success) {
							this.modalTaskVisible = false;
							this.getDataList();
							this.$Message.success('保存成功');
						} else {
							this.$Message.error('保存失败');
						}
					});
				} else {
					edit(this.form).then(res => {
						this.userLoading = false;
						if(res.success) {
							this.modalTaskVisible = false;
							this.getDataList();
							this.$Message.success('保存成功');
						} else {
							this.$Message.error('保存失败');
						}
					});

				}

			},
			//单一删除
			deleteData(form) {
				this.$Modal.confirm({
					title: "确认删除",
					content: "您确认要删除所点击选的数据?",
					loading: true,
					onOk: () => {
						this.userLoading = true;
						var ids = [];
						ids.push(form.id);
						this.patchdeleteData(ids);
					}
				});
				
			},
			//批量删除
			delAll() {
				if(this.selectCount <= 0) {
					this.$Message.warning("您还未选择要删除的数据");
					return;
				}
				this.$Modal.confirm({
					title: "确认删除",
					content: "您确认要删除所选的 " + this.selectCount + " 条数据?",
					loading: true,
					onOk: () => {
						let ids =[];
						this.selectList.forEach(function(e) {
							ids.push(e.id );
						});
						this.patchdeleteData(ids);
					}
				});
			},
			//删除（后台）
			patchdeleteData(ids) {
				if(ids == undefined || ids == null || ids.length == 0) {
					this.$Message.error('没有选择的数据');
					return;
				}
				del(JSON.stringify(ids)).then(res => {
					this.userLoading = false;
					this.$Modal.remove();
					if(res.success) {
						this.modalTaskVisible = false;
						this.$Message.success('删除成功');
						this.getDataList();
					} else {
						this.$Message.error('删除失败');
					}
				});
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

			handleSearch() {
				this.searchForm.pageNumber = 1;
				this.searchForm.pageSize = 10;
				this.getDataList();
			},
			handleReset() {
				this.$refs.searchForm.resetFields();
				this.searchForm.pageNumber = 1;
				this.searchForm.pageSize = 10;
				// 重新加载数据
				//this.getDataList();
			},
			showSelect(e) {
				this.selectList = e;
				this.selectCount = e.length;
			},
			clearSelectAll() {
				this.$refs.table.selectAll(false);
			},

			formartDate(date, fmt) {
				if(date == undefined || date == null) {
					return "";
				}
				date = date == undefined ? new Date() : date;
				date = typeof date == 'number' ? new Date(date) : date;
				fmt = fmt || 'yyyy-MM-dd HH:mm:ss';
				var obj = {
					'y': date.getFullYear(), // 年份，注意必须用getFullYear
					'M': date.getMonth() + 1, // 月份，注意是从0-11
					'd': date.getDate(), // 日期
					'q': Math.floor((date.getMonth() + 3) / 3), // 季度
					'w': date.getDay(), // 星期，注意是0-6
					'H': date.getHours(), // 24小时制
					'h': date.getHours() % 12 == 0 ? 12 : date.getHours() % 12, // 12小时制
					'm': date.getMinutes(), // 分钟
					's': date.getSeconds(), // 秒
					'S': date.getMilliseconds() // 毫秒
				};
				var week = ['天', '一', '二', '三', '四', '五', '六'];
				for(var i in obj) {
					fmt = fmt.replace(new RegExp(i + '+', 'g'), function(m) {
						var val = obj[i] + '';
						if(i == 'w') return(m.length > 2 ? '星期' : '周') + week[val];
						for(var j = 0, len = val.length; j < m.length - len; j++) val = '0' + val;
						return m.length == 1 ? val : val.substring(val.length - m.length);
					});
				}
				return fmt;
			}

		},
		mounted() {
			this.init();

		},
		watch: {

		}
	}
</script>