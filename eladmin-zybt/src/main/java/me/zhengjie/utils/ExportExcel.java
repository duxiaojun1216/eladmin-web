package me.zhengjie.utils;

import me.zhengjie.service.dto.BusinessTotal;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExportExcel {

    //显示的导出表的标题
    private String title;
    //导出表的列名
    private String[] rowName;

    private List<BusinessTotal> dataList = new ArrayList<>();

    //构造方法，传入要导出的数据
    public ExportExcel(String title, List<BusinessTotal> dataList) {
        this.dataList = dataList;
        this.title = title;
    }

    public void exportExcelData(HttpServletResponse response) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();                        // 创建工作簿对象
            XSSFSheet sheet = workbook.createSheet(title);                     // 创建工作表

            //生成单元格
            for (int i = 0; i < this.dataList.size() + 4; i++) {
                XSSFRow row = sheet.createRow(i);
                for (int n = 0; n < 21; n++) {
                    XSSFCell cell = row.createCell(n);
                    cell.setCellStyle(setCellStyle(workbook));
                }
            }

            //设置表头
            XSSFRow row = sheet.getRow(0);
            CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 20);
            sheet.addMergedRegion(cellRangeAddress);
            XSSFCell cell = row.getCell(0);
            cell.setCellValue("泸州市置业补助统计表");
            row.setHeight((short) 500);
            cell.setCellStyle(setHeadCellStyle(workbook));


            row = sheet.getRow(1);
            cellRangeAddress = new CellRangeAddress(1, 2, 0, 0);
            sheet.addMergedRegion(cellRangeAddress);
            cell = row.getCell(0);
            cell.setCellValue("序号");
            cell.setCellStyle(setHeadCellStyle(workbook));

            cellRangeAddress = new CellRangeAddress(1, 2, 1, 1);
            sheet.addMergedRegion(cellRangeAddress);
            cell = row.getCell(1);
            cell.setCellValue("区县");
            sheet.setColumnWidth(1, 10 * 256);
            cell.setCellStyle(setHeadCellStyle(workbook));

            cellRangeAddress = new CellRangeAddress(1, 2, 2, 2);
            sheet.addMergedRegion(cellRangeAddress);
            cell = row.getCell(2);
            cell.setCellValue("申报对象类型");
            sheet.setColumnWidth(2, 15 * 256);
            cell.setCellStyle(setHeadCellStyle(workbook));


            cellRangeAddress = new CellRangeAddress(1, 1, 3, 5);
            sheet.addMergedRegion(cellRangeAddress);
            cell = row.getCell(3);
            cell.setCellValue("标准化厂房");
            cell.setCellStyle(setHeadCellStyle(workbook));

            cellRangeAddress = new CellRangeAddress(1, 1, 6, 8);
            sheet.addMergedRegion(cellRangeAddress);
            cell = row.getCell(6);
            cell.setCellValue("新建营业用房");
            cell.setCellStyle(setHeadCellStyle(workbook));

            cellRangeAddress = new CellRangeAddress(1, 1, 9, 11);
            sheet.addMergedRegion(cellRangeAddress);
            cell = row.getCell(9);
            cell.setCellValue("新建自住住房");
            cell.setCellStyle(setHeadCellStyle(workbook));

            cellRangeAddress = new CellRangeAddress(1, 1, 12, 14);
            sheet.addMergedRegion(cellRangeAddress);
            cell = row.getCell(12);
            cell.setCellValue("二手营业用房");
            cell.setCellStyle(setHeadCellStyle(workbook));

            cellRangeAddress = new CellRangeAddress(1, 1, 15, 17);
            sheet.addMergedRegion(cellRangeAddress);
            cell = row.getCell(15);
            cell.setCellValue("二手自住住房");
            cell.setCellStyle(setHeadCellStyle(workbook));

            cellRangeAddress = new CellRangeAddress(1, 1, 18, 20);
            sheet.addMergedRegion(cellRangeAddress);
            cell = row.getCell(18);
            cell.setCellValue("车位");
            cell.setCellStyle(setHeadCellStyle(workbook));

            row = sheet.getRow(2);
            cell = row.getCell(3);
            cell.setCellValue("套数");
            cell.setCellStyle(setHeadCellStyle(workbook));

            cell = row.getCell(4);
            cell.setCellValue("面积");
            cell.setCellStyle(setHeadCellStyle(workbook));
            cell = row.getCell(5);
            cell.setCellValue("金额");
            cell.setCellStyle(setHeadCellStyle(workbook));
            cell = row.getCell(6);
            cell.setCellValue("套数");
            cell.setCellStyle(setHeadCellStyle(workbook));
            cell = row.getCell(7);
            cell.setCellValue("面积");
            cell.setCellStyle(setHeadCellStyle(workbook));
            cell = row.getCell(8);
            cell.setCellValue("金额");
            cell.setCellStyle(setHeadCellStyle(workbook));
            cell = row.getCell(9);
            cell.setCellValue("套数");
            cell.setCellStyle(setHeadCellStyle(workbook));
            cell = row.getCell(10);
            cell.setCellValue("面积");
            cell.setCellStyle(setHeadCellStyle(workbook));
            cell = row.getCell(11);
            cell.setCellValue("金额");
            cell.setCellStyle(setHeadCellStyle(workbook));
            cell = row.getCell(12);
            cell.setCellValue("套数");
            cell.setCellStyle(setHeadCellStyle(workbook));
            cell = row.getCell(13);
            cell.setCellValue("面积");
            cell.setCellStyle(setHeadCellStyle(workbook));
            cell = row.getCell(14);
            cell.setCellValue("金额");
            cell.setCellStyle(setHeadCellStyle(workbook));
            cell = row.getCell(15);
            cell.setCellValue("套数");
            cell.setCellStyle(setHeadCellStyle(workbook));
            cell = row.getCell(16);
            cell.setCellValue("面积");
            cell.setCellStyle(setHeadCellStyle(workbook));
            cell = row.getCell(17);
            cell.setCellValue("金额");
            cell.setCellStyle(setHeadCellStyle(workbook));
            cell = row.getCell(18);
            cell.setCellValue("套数");
            cell.setCellStyle(setHeadCellStyle(workbook));
            cell = row.getCell(19);
            cell.setCellValue("面积");
            cell.setCellStyle(setHeadCellStyle(workbook));
            cell = row.getCell(20);
            cell.setCellValue("金额");
            cell.setCellStyle(setHeadCellStyle(workbook));

            int type_1_taoTotal = 0;
            double type_1_areaTotal = 0;
            double type_1_moneyTotal = 0;

            int type_2_taoTotal = 0;
            double type_2_areaTotal = 0;
            double type_2_moneyTotal = 0;

            int type_3_taoTotal = 0;
            double type_3_areaTotal = 0;
            double type_3_moneyTotal = 0;

            int type_4_taoTotal = 0;
            double type_4_areaTotal = 0;
            double type_4_moneyTotal = 0;

            int type_5_taoTotal = 0;
            double type_5_areaTotal = 0;
            double type_5_moneyTotal = 0;

            int type_6_taoTotal = 0;
            double type_6_areaTotal = 0;
            double type_6_moneyTotal = 0;

            //填充数据
            for (int i = 0; i < this.dataList.size(); i++) {
                BusinessTotal businessTotal = this.dataList.get(i);
                XSSFRow row1 = sheet.getRow(i + 3);
                row1.getCell(0).setCellValue((i + 1));
                row1.getCell(1).setCellValue(businessTotal.getArea());
                row1.getCell(2).setCellValue(businessTotal.getLabel());
                row1.getCell(3).setCellValue(businessTotal.getHouse_type1_tao() == null ? 0 : businessTotal.getHouse_type1_tao());
                row1.getCell(4).setCellValue(businessTotal.getHouse_type1_area() == null ? 0 : businessTotal.getHouse_type1_area());
                row1.getCell(5).setCellValue(businessTotal.getHouse_type1_money() == null ? 0 : businessTotal.getHouse_type1_money());
                row1.getCell(6).setCellValue(businessTotal.getHouse_type2_tao() == null ? 0 : businessTotal.getHouse_type2_tao());
                row1.getCell(7).setCellValue(businessTotal.getHouse_type2_area() == null ? 0 : businessTotal.getHouse_type2_area());
                row1.getCell(8).setCellValue(businessTotal.getHouse_type2_money() == null ? 0 : businessTotal.getHouse_type2_money());
                row1.getCell(9).setCellValue(businessTotal.getHouse_type3_tao() == null ? 0 : businessTotal.getHouse_type3_tao());
                row1.getCell(10).setCellValue(businessTotal.getHouse_type3_area() == null ? 0 : businessTotal.getHouse_type3_area());
                row1.getCell(11).setCellValue(businessTotal.getHouse_type3_money() == null ? 0 : businessTotal.getHouse_type3_money());
                row1.getCell(12).setCellValue(businessTotal.getHouse_type4_tao() == null ? 0 : businessTotal.getHouse_type4_tao());
                row1.getCell(13).setCellValue(businessTotal.getHouse_type4_area() == null ? 0 : businessTotal.getHouse_type4_area());
                row1.getCell(14).setCellValue(businessTotal.getHouse_type4_money() == null ? 0 : businessTotal.getHouse_type4_money());
                row1.getCell(15).setCellValue(businessTotal.getHouse_type5_tao() == null ? 0 : businessTotal.getHouse_type5_tao());
                row1.getCell(16).setCellValue(businessTotal.getHouse_type5_area() == null ? 0 : businessTotal.getHouse_type5_area());
                row1.getCell(17).setCellValue(businessTotal.getHouse_type5_money() == null ? 0 : businessTotal.getHouse_type5_money());
                row1.getCell(18).setCellValue(businessTotal.getHouse_type6_tao() == null ? 0 : businessTotal.getHouse_type6_tao());
                row1.getCell(19).setCellValue(businessTotal.getHouse_type6_area() == null ? 0 : businessTotal.getHouse_type6_area());
                row1.getCell(20).setCellValue(businessTotal.getHouse_type6_money() == null ? 0 : businessTotal.getHouse_type6_money());

                type_1_taoTotal += businessTotal.getHouse_type1_tao();
                type_1_areaTotal += (businessTotal.getHouse_type1_area() == null ? 0 : businessTotal.getHouse_type1_area());
                type_1_moneyTotal += (businessTotal.getHouse_type1_money() == null ? 0 : businessTotal.getHouse_type1_money());

                type_2_taoTotal += businessTotal.getHouse_type2_tao();
                type_2_areaTotal += (businessTotal.getHouse_type2_area() == null ? 0 :businessTotal.getHouse_type2_area());
                type_2_moneyTotal += (businessTotal.getHouse_type2_money() == null ? 0 :businessTotal.getHouse_type2_money());

                type_3_taoTotal += businessTotal.getHouse_type3_tao();
                type_3_areaTotal += (businessTotal.getHouse_type3_area() == null ? 0 :businessTotal.getHouse_type3_area());
                type_3_moneyTotal += (businessTotal.getHouse_type3_money() == null ? 0 :businessTotal.getHouse_type3_money());

                type_4_taoTotal += businessTotal.getHouse_type4_tao();
                type_4_areaTotal += (businessTotal.getHouse_type4_area() == null ? 0 :businessTotal.getHouse_type4_area());
                type_4_moneyTotal += (businessTotal.getHouse_type4_money() == null ? 0 :businessTotal.getHouse_type4_money());

                type_5_taoTotal += businessTotal.getHouse_type5_tao();
                type_5_areaTotal += (businessTotal.getHouse_type5_area() == null ? 0 :businessTotal.getHouse_type5_area());
                type_5_moneyTotal += (businessTotal.getHouse_type5_money() == null ? 0 :businessTotal.getHouse_type5_money());

                type_6_taoTotal += businessTotal.getHouse_type6_tao();
                type_6_areaTotal += (businessTotal.getHouse_type6_area() == null ? 0 :businessTotal.getHouse_type6_area());
                type_6_moneyTotal += (businessTotal.getHouse_type6_money() == null ? 0 :businessTotal.getHouse_type6_money());

                if (i % 5 == 0) {
                    cellRangeAddress = new CellRangeAddress((i + 3), (i + 3 + 4), 1, 1);
                    sheet.addMergedRegion(cellRangeAddress);
                }
            }

            //添加合计行
            row = sheet.getRow(this.dataList.size() + 3);
            XSSFCell cell1 = row.getCell(0);
            cell1.setCellValue("合计");

            row.getCell(1).setCellValue("--");
            row.getCell(2).setCellValue("--");
            row.getCell(3).setCellValue(type_1_taoTotal);
            row.getCell(4).setCellValue(type_1_areaTotal);
            row.getCell(5).setCellValue(type_1_moneyTotal);
            row.getCell(6).setCellValue(type_2_taoTotal);
            row.getCell(7).setCellValue(type_2_areaTotal);
            row.getCell(8).setCellValue(type_2_moneyTotal);
            row.getCell(9).setCellValue(type_3_taoTotal);
            row.getCell(10).setCellValue(type_3_areaTotal);
            row.getCell(11).setCellValue(type_3_moneyTotal);
            row.getCell(12).setCellValue(type_4_taoTotal);
            row.getCell(13).setCellValue(type_4_areaTotal);
            row.getCell(14).setCellValue(type_4_moneyTotal);
            row.getCell(15).setCellValue(type_5_taoTotal);
            row.getCell(16).setCellValue(type_5_areaTotal);
            row.getCell(17).setCellValue(type_5_moneyTotal);
            row.getCell(18).setCellValue(type_6_taoTotal);
            row.getCell(19).setCellValue(type_6_areaTotal);
            row.getCell(20).setCellValue(type_6_moneyTotal);


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(new Date());
            String fileName = "泸州市置业补助统计（" + format + "）.xlsx";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            //response.setContentType("APPLICATION/OCTET-STREAM;charset=utf-8");
            response.setHeader("content-Type", "application/ms-excel");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" + "filename*=utf-8''" + fileName);
            OutputStream out = response.getOutputStream();
            workbook.write(out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    /*
     * 列头单元格样式
     */
    public XSSFCellStyle getColumnTopStyle(XSSFWorkbook workbook) {

        // 设置字体
        XSSFFont font = workbook.createFont();
        //设置字体大小
        ((XSSFFont) font).setFontHeightInPoints((short) 11);
        //字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        XSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }

    /*
     * 列数据信息单元格样式
     */
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        //font.setFontHeightInPoints((short)10);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    /**
     * 设置单元格样式
     *
     * @param workbook
     * @return
     */
    private XSSFCellStyle setCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象
        columnTopStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        columnTopStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        columnTopStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        columnTopStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        XSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 12);//设置字体大小
        columnTopStyle.setFont(font);
        return columnTopStyle;
    }

    /**
     * 设置表头样式
     *
     * @param workbook
     * @return
     */
    private XSSFCellStyle setHeadCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象
        columnTopStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        columnTopStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        columnTopStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        columnTopStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        columnTopStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        columnTopStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font.setFontHeightInPoints((short) 12);
        columnTopStyle.setFont(font);
        return columnTopStyle;
    }
}
