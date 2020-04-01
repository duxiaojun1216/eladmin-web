<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission"/>
      <el-button class="filter-item" size="mini" type="warning" icon="el-icon-download" @click="downLoadExcel">导出
      </el-button>
      <!--表格渲染-->
      <el-table id="table" :data="tableData" border size="small" style="width: 100%;" :summary-method="getSummaries"
                show-summary :header-cell-style="tableHeaderColor"
                :span-method="objectSpanMethod">

        <el-table-column label="泸州市置业补助统计表" align="center">
          <el-table-column label="" align="left">
            <template slot="header" slot-scope="scope">
              日期：
              <span id="nowDate" name="nowDate">{{getNowFormatDate()}}</span>
            </template>


            <el-table-column type="index" :index="indexMethod" label="序号" align="center"/>
            <el-table-column prop="name" label="地区" align="center"/>
            <!--        <el-table-column prop="peoson_type1" label="农民工" align="center"/>-->
            <!--        <el-table-column prop="peoson_type2" label="退役军人" align="center"/>-->
            <!--        <el-table-column prop="peoson_type3" label="法人" align="center"/>-->
            <!--        <el-table-column prop="peoson_type4" label="高校毕业人" align="center"/>-->
            <!--        <el-table-column prop="peoson_type5" label="外来人口" align="center"/>-->
            <el-table-column prop="peoson_type" label="申报对象类型" align="center"/>
            <el-table-column prop="house_type1" label="标准化厂房" align="center"/>
            <el-table-column prop="house_type2" label="营业用房" align="center"/>
            <el-table-column prop="house_type3" label="自助住房" align="center"/>
          </el-table-column>
          <el-table-column align="right">
            <template slot="header" slot-scope="scope">
              类型：
              <label id="type" name="type" style="display: none;">{{label}}</label>
              <el-select v-model="value" placeholder="请选择" @change="handleChange">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </template>
            <el-table-column prop="house_type4" label="二手营业用房" align="center"/>
            <el-table-column prop="house_type5" label="二手自助住房" align="center"/>
            <el-table-column prop="house_type6" label="车位" align="center"/>
            <el-table-column prop="total" label="合计" align="center"/>
            <el-table-column prop="rowTotal" label="区合计" align="center"/>
          </el-table-column>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination/>
    </div>
  </div>
</template>

<script>
    import CRUD, { presenter, header, form, crud } from '@crud/crud'
    import rrOperation from '@crud/RR.operation'
    import crudOperation from '@crud/CRUD.operation'
    import udOperation from '@crud/UD.operation'
    import pagination from '@crud/Pagination'
    import FileSaver from 'file-saver'
    import XLSX from 'xlsx'

    // crud交由presenter持有
    export default {
        name: 'total',
        components: { pagination, crudOperation, rrOperation, udOperation },
        data() {
            return {
                permission: {},
                options: [{
                    value: '1',
                    label: '已发放'
                }, {
                    value: '2',
                    label: '已核算'
                }],
                value: '',
                label: '',
                tableData: [{
                    id: '1',
                    name: '江阳区',
                    peoson_type: '农民工',
                    house_type1: 10,
                    house_type2: 20,
                    house_type3: 30,
                    house_type4: 40,
                    house_type5: 50,
                    house_type6: 60,
                    total: 300,
                    rowTotal: 1500
                }, {
                    id: '1',
                    name: '江阳区',
                    peoson_type: '退役军人',
                    house_type1: 10,
                    house_type2: 20,
                    house_type3: 30,
                    house_type4: 40,
                    house_type5: 50,
                    house_type6: 60,
                    total: 300,
                    rowTotal: 1500
                }, {
                    id: '1',
                    name: '江阳区',
                    peoson_type: '法人',
                    house_type1: 10,
                    house_type2: 20,
                    house_type3: 30,
                    house_type4: 40,
                    house_type5: 50,
                    house_type6: 60,
                    total: 300,
                    rowTotal: 1500
                }, {
                    id: '1',
                    name: '江阳区',
                    peoson_type: '高校毕业人',
                    house_type1: 10,
                    house_type2: 20,
                    house_type3: 30,
                    house_type4: 40,
                    house_type5: 50,
                    house_type6: 60,
                    total: 300,
                    rowTotal: 1500
                }, {
                    id: '1',
                    name: '江阳区',
                    peoson_type: '外来人口',
                    house_type1: 10,
                    house_type2: 20,
                    house_type3: 30,
                    house_type4: 40,
                    house_type5: 50,
                    house_type6: 60,
                    total: 300,
                    rowTotal: 1500
                }, {
                    id: '2',
                    name: '龙马潭区',
                    peoson_type: '农民工',
                    house_type1: 10,
                    house_type2: 20,
                    house_type3: 30,
                    house_type4: 40,
                    house_type5: 50,
                    house_type6: 60,
                    total: 300,
                    rowTotal: 1500
                }, {
                    id: '2',
                    name: '龙马潭区',
                    peoson_type: '退役军人',
                    house_type1: 10,
                    house_type2: 20,
                    house_type3: 30,
                    house_type4: 40,
                    house_type5: 50,
                    house_type6: 60,
                    total: 300,
                    rowTotal: 1500
                }, {
                    id: '2',
                    name: '龙马潭区',
                    peoson_type: '法人',
                    house_type1: 10,
                    house_type2: 20,
                    house_type3: 30,
                    house_type4: 40,
                    house_type5: 50,
                    house_type6: 60,
                    total: 300,
                    rowTotal: 1500
                }, {
                    id: '2',
                    name: '龙马潭区',
                    peoson_type: '高校毕业人',
                    house_type1: 10,
                    house_type2: 20,
                    house_type3: 30,
                    house_type4: 40,
                    house_type5: 50,
                    house_type6: 60,
                    total: 300,
                    rowTotal: 1500
                }, {
                    id: '2',
                    peoson_type: '外来人口',
                    house_type1: 10,
                    house_type2: 20,
                    house_type3: 30,
                    house_type4: 40,
                    house_type5: 50,
                    house_type6: 60,
                    total: 300,
                    rowTotal: 1500
                }, {
                    id: '3',
                    name: '纳溪区',
                    peoson_type: '农民工',
                    house_type1: 10,
                    house_type2: 20,
                    house_type3: 30,
                    house_type4: 40,
                    house_type5: 50,
                    house_type6: 60,
                    total: 300,
                    rowTotal: 1500
                }, {
                    id: '3',
                    name: '纳溪区',
                    peoson_type: '退役军人',
                    house_type1: 10,
                    house_type2: 20,
                    house_type3: 30,
                    house_type4: 40,
                    house_type5: 50,
                    house_type6: 60,
                    total: 300,
                    rowTotal: 1500
                }, {
                    id: '3',
                    name: '纳溪区',
                    peoson_type: '法人',
                    house_type1: 10,
                    house_type2: 20,
                    house_type3: 30,
                    house_type4: 40,
                    house_type5: 50,
                    house_type6: 60,
                    total: 300,
                    rowTotal: 1500
                }, {
                    id: '3',
                    peoson_type: '高校毕业人',
                    house_type1: 10,
                    house_type2: 20,
                    house_type3: 30,
                    house_type4: 40,
                    house_type5: 50,
                    house_type6: 60,
                    total: 300,
                    rowTotal: 1500
                }, {
                    id: '3',
                    name: '纳溪区',
                    peoson_type: '外来人口',
                    house_type1: 10,
                    house_type2: 20,
                    house_type3: 30,
                    house_type4: 40,
                    house_type5: 50,
                    house_type6: 60,
                    total: 300,
                    rowTotal: 1500
                }]
            };
        },
        methods: {
            getSummaries(param) {
                const { columns, data } = param;
                const sums = [];
                columns.forEach((column, index) => {
                    if (index1 === 0) {
                        sums[index1] = '合计';
                        return;
                    }

                    const values = data.map(item => Number(item[column.property]));
                    if (!values.every(value => isNaN(value))) {
                        sums[index1] = values.reduce((prev, curr) => {
                            const value = Number(curr);
                            if (!isNaN(value)) {
                                return prev + curr;
                            } else {
                                return prev;
                            }
                        }, 0);

                        if (index1 == 10) {
                            sums[index1] = sums[index1] / 5;
                        }
                        sums[index1] += ' 万元';
                    } else {
                        sums[index1] = '--';
                    }
                });
                return sums;
            },
            objectSpanMethod({ row, column, rowIndex, columnIndex }) {
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
                if (columnIndex === 10) {
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
            },
            indexMethod(index) {
                return index1 + 1;
            },
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
            tableHeaderColor({ row, column, rowIndex, columnIndex }) {
                if (rowIndex === 0) {
                    return 'height:50px;font-size:20px;background:#D9D9D9;color:#606266;';
                }
                if (rowIndex === 1) {
                    return 'background:#D9D9D9;color:#606266;font-weight: bold;';
                }
                if (rowIndex === 2) {
                    return 'background:#D9D9D9;color:#606266;font-weight: bold;';
                }
            },
            handleChange(val) {
                var _that = this;
                //传进来的val是select组件选中的value值，也就是一个fruitEnName
                var obj = {}
                obj = this.options.find(function(item) {
                    return item.value === val
                })
                //obj 就是被选中的那个对象，也就能拿到label值了。
                console.log(obj.label)
                _that.label = obj.label;
            },
            getNowFormatDate() {
                var date = new Date();
                var seperator1 = "-";
                var year = date.getFullYear();
                var month = date.getMonth() + 1;
                var strDate = date.getDate();
                if (month >= 1 && month <= 9) {
                    month = "0" + month;
                }
                if (strDate >= 0 && strDate <= 9) {
                    strDate = "0" + strDate;
                }
                var currentdate = year + seperator1 + month + seperator1 + strDate;
                return currentdate;
            }
        }
    };

</script>

<style scoped>

</style>
