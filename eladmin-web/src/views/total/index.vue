<style lang="less">
  .ivu-table-header th {
    font-size: 13px;
    color: #606266;
    font-weight: bold;
    background-color: #D9D9D9;
    height: 55px !important;
  }


  .ivu-table-border td, .ivu-table-border th {
    height: 40px !important;
  }
</style>
<template>
  <div class="app-container">
    <div class="head-container">
      <Table border :columns="columns1" :data="tableData" style="width: 100%" :span-method="handleSpan" show-summary
             :summary-method="handleSummary">
      </Table>
    </div>
  </div>
</template>
<script>
    export default {
        name: 'total',
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
                                title: '地区',
                                key: 'name',
                                minWidth: 100
                            },
                            {
                                align: 'center',
                                title: '申报对象类型',
                                key: 'peoson_type',
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
                                        minWidth: 65
                                    },
                                    {
                                        align: 'center',
                                        title: '金额',
                                        key: 'house_type1_money',
                                        minWidth: 65
                                    }
                                ]
                            },
                            {
                                align: 'center',
                                title: '营业用房',
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
                                        minWidth: 65
                                    },
                                    {
                                        align: 'center',
                                        title: '金额',
                                        key: 'house_type2_money',
                                        minWidth: 65
                                    }
                                ]
                            },
                            {
                                align: 'center',
                                title: '自助住房',
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
                                        minWidth: 65
                                    },
                                    {
                                        align: 'center',
                                        title: '金额',
                                        key: 'house_type3_money',
                                        minWidth: 65
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
                                        minWidth: 65
                                    },
                                    {
                                        align: 'center',
                                        title: '金额',
                                        key: 'house_type4_money',
                                        minWidth: 65
                                    }
                                ]
                            },
                            {
                                align: 'center',
                                title: '二手自助住房',
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
                                        minWidth: 65
                                    },
                                    {
                                        align: 'center',
                                        title: '金额',
                                        key: 'house_type5_money',
                                        minWidth: 65
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
                                        minWidth: 65
                                    },
                                    {
                                        align: 'center',
                                        title: '金额',
                                        key: 'house_type6_money',
                                        minWidth: 65
                                    }
                                ]
                            },
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
                value: '',
                label: '',
                // tableData: [
                //     {
                //         id: '1',
                //         name: '江阳区',
                //         person_type: '农民工',
                //         house_type1_tao: 10,
                //         house_type1_area: 10,
                //         house_type1_money: 10,
                //         house_type2_tao: 20,
                //         house_type2_area: 10,
                //         house_type2_money: 10,
                //         house_type3_tao: 30,
                //         house_type3_area: 30,
                //         house_type3_money: 30,
                //         house_type4_tao: 40,
                //         house_type4_area: 40,
                //         house_type4_money: 40,
                //         house_type5_tao: 50,
                //         house_type5_area: 50,
                //         house_type5_money: 50,
                //         house_type6_tao: 60,
                //         house_type6_area: 60,
                //         house_type6_money: 60,
                //         total: 300,
                //         rowTotal: 1500
                //     }, {
                //         id: '1',
                //         name: '江阳区',
                //         person_type: '退役军人',
                //         house_type1: 10,
                //         house_type2: 20,
                //         house_type3: 30,
                //         house_type4: 40,
                //         house_type5: 50,
                //         house_type6: 60,
                //         total: 300,
                //         rowTotal: 1500
                //     }, {
                //         id: '1',
                //         name: '江阳区',
                //         person_type: '法人',
                //         house_type1: 10,
                //         house_type2: 20,
                //         house_type3: 30,
                //         house_type4: 40,
                //         house_type5: 50,
                //         house_type6: 60,
                //         total: 300,
                //         rowTotal: 1500
                //     }, {
                //         id: '1',
                //         name: '江阳区',
                //         person_type: '高校毕业人',
                //         house_type1: 10,
                //         house_type2: 20,
                //         house_type3: 30,
                //         house_type4: 40,
                //         house_type5: 50,
                //         house_type6: 60,
                //         total: 300,
                //         rowTotal: 1500
                //     }, {
                //         id: '1',
                //         name: '江阳区',
                //         person_type: '外来人口',
                //         house_type1: 10,
                //         house_type2: 20,
                //         house_type3: 30,
                //         house_type4: 40,
                //         house_type5: 50,
                //         house_type6: 60,
                //         total: 300,
                //         rowTotal: 1500
                //     }, {
                //         id: '2',
                //         name: '龙马潭区',
                //         person_type: '农民工',
                //         house_type1: 10,
                //         house_type2: 20,
                //         house_type3: 30,
                //         house_type4: 40,
                //         house_type5: 50,
                //         house_type6: 60,
                //         total: 300,
                //         rowTotal: 1500
                //     }, {
                //         id: '2',
                //         name: '龙马潭区',
                //         person_type: '退役军人',
                //         house_type1: 10,
                //         house_type2: 20,
                //         house_type3: 30,
                //         house_type4: 40,
                //         house_type5: 50,
                //         house_type6: 60,
                //         total: 300,
                //         rowTotal: 1500
                //     }, {
                //         id: '2',
                //         name: '龙马潭区',
                //         person_type: '法人',
                //         house_type1: 10,
                //         house_type2: 20,
                //         house_type3: 30,
                //         house_type4: 40,
                //         house_type5: 50,
                //         house_type6: 60,
                //         total: 300,
                //         rowTotal: 1500
                //     }, {
                //         id: '2',
                //         name: '龙马潭区',
                //         person_type: '高校毕业人',
                //         house_type1: 10,
                //         house_type2: 20,
                //         house_type3: 30,
                //         house_type4: 40,
                //         house_type5: 50,
                //         house_type6: 60,
                //         total: 300,
                //         rowTotal: 1500
                //     }, {
                //         id: '2',
                //         person_type: '外来人口',
                //         house_type1: 10,
                //         house_type2: 20,
                //         house_type3: 30,
                //         house_type4: 40,
                //         house_type5: 50,
                //         house_type6: 60,
                //         total: 300,
                //         rowTotal: 1500
                //     }, {
                //         id: '3',
                //         name: '纳溪区',
                //         person_type: '农民工',
                //         house_type1: 10,
                //         house_type2: 20,
                //         house_type3: 30,
                //         house_type4: 40,
                //         house_type5: 50,
                //         house_type6: 60,
                //         total: 300,
                //         rowTotal: 1500
                //     }, {
                //         id: '3',
                //         name: '纳溪区',
                //         person_type: '退役军人',
                //         house_type1: 10,
                //         house_type2: 20,
                //         house_type3: 30,
                //         house_type4: 40,
                //         house_type5: 50,
                //         house_type6: 60,
                //         total: 300,
                //         rowTotal: 1500
                //     }, {
                //         id: '3',
                //         name: '纳溪区',
                //         peoson_type: '法人',
                //         house_type1: 10,
                //         house_type2: 20,
                //         house_type3: 30,
                //         house_type4: 40,
                //         house_type5: 50,
                //         house_type6: 60,
                //         total: 300,
                //         rowTotal: 1500
                //     }, {
                //         id: '3',
                //         peoson_type: '高校毕业人',
                //         house_type1: 10,
                //         house_type2: 20,
                //         house_type3: 30,
                //         house_type4: 40,
                //         house_type5: 50,
                //         house_type6: 60,
                //         total: 300,
                //         rowTotal: 1500
                //     }, {
                //         id: '3',
                //         name: '纳溪区',
                //         peoson_type: '外来人口',
                //         house_type1: 10,
                //         house_type2: 20,
                //         house_type3: 30,
                //         house_type4: 40,
                //         house_type5: 50,
                //         house_type6: 60,
                //         total: 300,
                //         rowTotal: 1500
                //     }]
            };
        },
        methods: {
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
                        } else if(key == 'total'){
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
            }
        }
    };
</script>
