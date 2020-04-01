<template>
	<div class="search">
		<Card>
      <Row v-show="openSearch" @keydown.enter.native="handleSearch">
        <Form ref="searchForm" :model="searchForm" inline :label-width="70">
          <Form-item label="规则名称" >
            <Input
              type="text"
              v-model="searchForm.zclx"
              placeholder="请输入"
              clearable
              style="width: 200px"
            />
          </Form-item>
          <Form-item style="margin-left:-35px;" class="br">
            <Button @click="handleSearch" type="primary" icon="ios-search">搜索</Button>
            <Button @click="handleReset">重置</Button>
          </Form-item>
        </Form>
      </Row>
      <Row class="operation">        
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
        <Table
          :loading="loading"
          border
          :columns="columns"
          sortable="custom"
          :data="data"
          @on-sort-change="changeSort"
          @on-selection-change="showSelect"
          ref="table"
        ></Table>
      </Row>
      <Row type="flex" justify="end" class="page">
        <Page
          :current="searchForm.pageNumber"
          :total="total"
          :page-size="searchForm.pageSize"
          @on-change="changePage"
          @on-page-size-change="changePageSize"
          :page-size-opts="[10,20,50]"
          size="small"
          show-total
          show-elevator
          show-sizer
        ></Page>
      </Row>
    </Card>
	</div>
</template>
<script>

	import { get,add, edit, del }from "@/api/tBzwh";
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
			      key:'zclx',
            align: 'center',
            render: (h, params) => {
            	let re = "";
            	if(params.row.zclx=="1"){           		
	        			re=[
	            		h(
		                  'div',
		                  {
		                  },
		                  "标准化厂房"
		                )
	         		 	];
            	}
            	else if(params.row.zclx=="2"){
	        			re=[
	            		h(
		                  'div',
		                  {
		                  },
		                  "新建营业用房"
		                )
	         		 	];
            	}
            	else if(params.row.zclx=="3"){
	        			re=[
	            		h(
		                  'div',
		                  {
		                  },
		                  "新建自住住房"
		                )
	         		 	];
            	}
            	else if(params.row.zclx=="4"){
	        			re=[
	            		h(
		                  'div',
		                  {
		                  },
		                  "一手车位（车库）"
		                )
	         		 	];
            	}
            	else if(params.row.zclx=="5"){
	        			re=[
	            		h(
		                  'div',
		                  {
		                  },
		                  "二手营业用房"
		                )
	         		 	];
            	}
            	else if(params.row.zclx=="6"){
	        			re=[
	            		h(
		                  'div',
		                  {
		                  },
		                  "二手自住住房"
		                )
	         		 	];
            	}
            	console.log(re);
            	return re;
            }
          },
          {
            title: '标准',
            key: 'bz',
            minWidth: 160,
            align: 'center',
          },
          {
            title: '所属区域',
            key: 'ssqy',
            width: 150,
            align: 'center',
          },
          /*{
            title: '委托代办人',
            key: 'owner',
            width: 130
          },*/
          {
            title: '起始时间',
            key: 'qssj',
            width: 180,
            align: 'center',
            render: (h, params) => {            
			  			return h('div', [
                h(
                  'div',
                  {
                  },
                  this.formartDate(params.row.qssj)
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
                  'div',
                  {
                  },
                  this.formartDate(params.row.zzsj)
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
                  'div',
                  {
                  },
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
                  'div',
                  {
                  },
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
                  'Button',
                  {
                    props: {
                      size: 'small',
                      type: 'info'
                    },
                    style: {
                      marginRight: '5px'
                    },
                    on: {
                      click: () => {
                        this.history(params.row);
                      }
                    }
                  },
                  '修改'
                ),
                h(
                  'Button',
                  {
                    props: {
                      size: 'small'
                    },
                    style: {
                      marginRight: '5px'
                    },
                    on: {
                      click: () => {
                        this.delegateTask(params.row);
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
        //this.getDictDataType();
        this.getDataList();

      },
     
      getDictDataType() {

        getDictDataByType('priority').then(res => {
          if (res.success) {
            this.dictPriority = res.result;
          }
        });
      },

      searchUser(v) {
        if (!v) {
          return;
        }
        this.userLoading = true;
        searchUserByName(v).then(res => {
          this.userLoading = false;
          if (res.success) {
            this.userList = res.result;
          }
        });
      },
      changeSort(e) {
        this.searchForm.sort = e.key;
        this.searchForm.order = e.order;
        if (e.order == 'normal') {
          this.searchForm.order = '';
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
      getDataList() {
        this.loading = true;
        get(this.searchForm).then(res => {
            this.loading = false;
            this.data = res.content;
            this.total = res.totalElements;
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

			formartDate(date, fmt)
			{
				date = date == undefined ? new Date() : date;
				date = typeof date == 'number' ? new Date(date) : date;
				fmt = fmt || 'yyyy-MM-dd HH:mm:ss';
				var obj =
				{
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
				for(var i in obj)
				{
					fmt = fmt.replace(new RegExp(i+'+', 'g'), function(m)
					{
						var val = obj[i] + '';
						if(i == 'w') return (m.length > 2 ? '星期' : '周') + week[val];
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