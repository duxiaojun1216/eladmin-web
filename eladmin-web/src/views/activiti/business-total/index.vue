<style lang="less">
  @import "../../../styles/table-common.less";
  @import "./businessTotal.less";
</style>
<template>
  <div class="search">
    <Card>
      <Row v-show="openSearch" @keydown.enter.native="handleSearch">
        <Form ref="searchForm" :model="searchForm" inline :label-width="60">
          <Form-item label="类型" prop="type">
            <Select v-model="searchForm.type" style="width:200px">
              <Option v-for="item in options" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
          </Form-item>
          <Form-item label="日期段" prop="timeRange">
            <DatePicker v-model="searchForm.timeRange" type="daterange" placement="bottom-end" placeholder="请选择日期时间段"
                        style="width: 200px"></DatePicker>
          </Form-item>
          <Form-item style="" class="br">
            <Button @click="handleSearch" type="primary" icon="ios-search">搜索</Button>
            <Button @click="handleReset">重置</Button>
          </Form-item>
        </Form>
      </Row>
      <Button type="primary" size="large" @click="exportData()">
        <Icon type="ios-download-outline"></Icon>
        导出
      </Button>
      <br>
      <br>
      <Table border :columns="columns1" :data="tableData" style="width: 100%" :span-method="handleSpan" show-summary
             :summary-method="handleSummary" :current="searchForm.pageNumber" :total="total" ref="table"
             :page-size="searchForm.pageSize">
      </Table>
    </Card>
  </div>
</template>
<script>
    import { getBusinessTotalData } from '../../../api/activiti';

    export default {
        name: 'business-total',
        data() {
            return {
                columns1: [
                    {
                        title: '泸州市置业补助统计表',
                        align: 'center',
                        children: [
                            {
                                title: '序号',
                                type: 'index',
                                align: 'center',
                                width: 65
                            },
                            {
                                align: 'center',
                                title: '区县',
                                key: 'area',
                                minWidth: 100
                            },
                            {
                                align: 'center',
                                title: '申报对象类型',
                                key: 'label',
                                minWidth: 120
                            },
                            {
                                align: 'center',
                                title: '标准化厂房',
                                key: 'house_type1',
                                children: [
                                    {
                                        align: 'center',
                                        title: '套数',
                                        key: 'house_type1_tao',
                                        minWidth: 65
                                    },
                                    {
                                        align: 'center',
                                        title: '面积',
                                        key: 'house_type1_area',
                                        minWidth: 65,
                                        render: (h, params) => {
                                            return h('div', {}, params.row.house_type1_area == null ? 0 : params.row.house_type1_area)
                                        }
                                    },
                                    {
                                        align: 'center',
                                        title: '金额',
                                        key: 'house_type1_money',
                                        minWidth: 65,
                                        render: (h, params) => {
                                            return h('div', {}, params.row.house_type1_money == null ? 0 : params.row.house_type1_money)
                                        }
                                    }
                                ]
                            },
                            {
                                align: 'center',
                                title: '新建营业用房',
                                key: 'house_type2',
                                children: [
                                    {
                                        align: 'center',
                                        title: '套数',
                                        key: 'house_type2_tao',
                                        minWidth: 65
                                    },
                                    {
                                        align: 'center',
                                        title: '面积',
                                        key: 'house_type2_area',
                                        minWidth: 65,
                                        render: (h, params) => {
                                            return h('div', {}, params.row.house_type2_area == null ? 0 : params.row.house_type2_area)
                                        }
                                    },
                                    {
                                        align: 'center',
                                        title: '金额',
                                        key: 'house_type2_money',
                                        minWidth: 65,
                                        render: (h, params) => {
                                            return h('div', {}, params.row.house_type2_money == null ? 0 : params.row.house_type2_money)
                                        }
                                    }
                                ]
                            },
                            {
                                align: 'center',
                                title: '新建自住住房',
                                key: 'house_type3',
                                children: [
                                    {
                                        align: 'center',
                                        title: '套数',
                                        key: 'house_type3_tao',
                                        minWidth: 65
                                    },
                                    {
                                        align: 'center',
                                        title: '面积',
                                        key: 'house_type3_area',
                                        minWidth: 65,
                                        render: (h, params) => {
                                            return h('div', {}, params.row.house_type3_area == null ? 0 : params.row.house_type3_area)
                                        }
                                    },
                                    {
                                        align: 'center',
                                        title: '金额',
                                        key: 'house_type3_money',
                                        minWidth: 65,
                                        render: (h, params) => {
                                            return h('div', {}, params.row.house_type3_money == null ? 0 : params.row.house_type3_money)
                                        }
                                    }
                                ]
                            },
                            {
                                align: 'center',
                                title: '二手营业用房',
                                key: 'house_type4',
                                children: [
                                    {
                                        align: 'center',
                                        title: '套数',
                                        key: 'house_type4_tao',
                                        minWidth: 65
                                    },
                                    {
                                        align: 'center',
                                        title: '面积',
                                        key: 'house_type4_area',
                                        minWidth: 65,
                                        render: (h, params) => {
                                            return h('div', {}, params.row.house_type4_area == null ? 0 : params.row.house_type4_area)
                                        }
                                    },
                                    {
                                        align: 'center',
                                        title: '金额',
                                        key: 'house_type4_money',
                                        minWidth: 65,
                                        render: (h, params) => {
                                            return h('div', {}, params.row.house_type4_money == null ? 0 : params.row.house_type4_money)
                                        }
                                    }
                                ]
                            },
                            {
                                align: 'center',
                                title: '二手自住住房',
                                key: 'house_type5',
                                children: [
                                    {
                                        align: 'center',
                                        title: '套数',
                                        key: 'house_type5_tao',
                                        minWidth: 65
                                    },
                                    {
                                        align: 'center',
                                        title: '面积',
                                        key: 'house_type5_area',
                                        minWidth: 65,
                                        render: (h, params) => {
                                            return h('div', {}, params.row.house_type5_area == null ? 0 : params.row.house_type5_area)
                                        }
                                    },
                                    {
                                        align: 'center',
                                        title: '金额',
                                        key: 'house_type5_money',
                                        minWidth: 65,
                                        render: (h, params) => {
                                            return h('div', {}, params.row.house_type5_money == null ? 0 : params.row.house_type5_money)
                                        }
                                    }
                                ]
                            },
                            {
                                align: 'center',
                                title: '车位',
                                key: 'house_type6',
                                children: [
                                    {
                                        align: 'center',
                                        title: '个数',
                                        key: 'house_type6_tao',
                                        minWidth: 65
                                    },
                                    {
                                        align: 'center',
                                        title: '面积',
                                        key: 'house_type6_area',
                                        minWidth: 65,
                                        render: (h, params) => {
                                            return h('div', {}, params.row.house_type6_area == null ? 0 : params.row.house_type6_area)
                                        }
                                    },
                                    {
                                        align: 'center',
                                        title: '金额',
                                        key: 'house_type6_money',
                                        minWidth: 65,
                                        render: (h, params) => {
                                            return h('div', {}, params.row.house_type6_money == null ? 0 : params.row.house_type6_money)
                                        }
                                    }
                                ]
                            }
                            // {
                            //     align: 'center',
                            //     title: '合计',
                            //     key: 'total',
                            //     minWidth:70
                            // },
                            // {
                            //     align: 'center',
                            //     title: '区合计',
                            //     key: 'rowTotal',
                            //     minWidth:70
                            // }
                        ]
                    }
                ],
                options: [{
                    value: '1',
                    label: '已发放'
                }, {
                    value: '2',
                    label: '已核算'
                }],
                openSearch: true,
                total: 0, // 表格数据总数
                tableData: [],//表格数据
                searchForm: {
                    // 搜索框对应data对象
                    timeRange: [],
                    startTime: '',
                    endTime: '',
                    type: '',
                    pageNumber: 1, // 当前页数
                    pageSize: 10 // 页面大小
                }
            };
        },
        methods: {
            init() {
                this.getDataList();
            },
            //合计
            handleSummary({ columns, data }) {
                const sums = {};
                columns.forEach((column, index) => {
                    const key = column.key;
                    if (index === 0) {
                        sums[key] = {
                            key,
                            value: '总价'
                        };
                        return;
                    }
                    const values = data.map(item => Number(item[key]));
                    if (!values.every(value => isNaN(value))) {
                        const v = values.reduce((prev, curr) => {
                            const value = Number(curr);
                            if (!isNaN(value)) {
                                return prev + curr;
                            } else {
                                return prev;
                            }
                        }, 0);

                        if (key === 'rowTotal') {
                            var value = v / 5;
                            sums[key] = {
                                key,
                                value: value + ' 万'
                            };
                        } else if (key == 'total') {
                            sums[key] = {
                                key,
                                value: v + ' 万'
                            };
                        } else {
                            sums[key] = {
                                key,
                                value: v
                            };
                        }
                    } else {
                        sums[key] = {
                            key,
                            value: '--'
                        };
                    }
                });
                return sums;
            },
            //合并行
            handleSpan({ row, column, rowIndex, columnIndex }) {
                if (columnIndex === 1) {
                    if (rowIndex % 5 === 0) {
                        return {
                            rowspan: 5,
                            colspan: 1
                        };
                    } else {
                        return {
                            rowspan: 0,
                            colspan: 0
                        };
                    }
                }
                // if (columnIndex === 10) {
                //     if (rowIndex % 5 === 0) {
                //         return {
                //             rowspan: 5,
                //             colspan: 1
                //         };
                //     } else {
                //         return {
                //             rowspan: 0,
                //             colspan: 0
                //         };
                //     }
                // }
            },
            //下载Excel
            downLoadExcel() {
                /* generate workbook object from table */
                var xlsxParam = { raw: true };//转换成excel时，使用原始的格式
                var wb = XLSX.utils.table_to_book(document.querySelector('#table'), xlsxParam)
                /* get binary string as output */
                var wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array', cellStyles: true })
                try {
                    let date = new Date();
                    FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), '统计报表（' + (date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()) + '）.xlsx')
                } catch (e) {
                    if (typeof console !== 'undefined') console.log(e, wbout)
                }
                return wbout
            },
            //搜索
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
                this.searchForm.startTime = this.searchForm.timeRange[0].toString();
                this.searchForm.endTime = this.searchForm.timeRange[1].toString();
                getBusinessTotalData(this.searchForm).then(res => {
                    this.loading = false;
                    if (res.success) {
                        this.tableData = res.result;
                        this.total = res.result.length
                    }
                });
            },
            exportData() {
                // this.$refs.table.exportCsv({
                //     filename: '泸州市置业补助统计表'
                // });
                //导出调用后台接口


            }
        },
        mounted() {
            this.init();
        },
        watch: {
            // 监听路由变化
            $route(to, from) {
                if (to.name == 'business-total') {
                    this.getDataList();
                }
            }
        }
    };
</script>
