package com.cn.readexcel;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

/**
 * Created by chenqi on 2017/5/28 0028 : 上午 9:47.
 *
 * @version : 1.0
 * @description :
 */
public class ReadExcel {

    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;

    //获取总行数
    public int getTotalRows() {
        return totalRows;
    }

    //获取总列数
    public int getTotalCells() {
        return totalCells;
    }

    //获取错误信息
    public String getErrorInfo() {
        return errorMsg;
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ReadExcel readExcel = new ReadExcel();
        File file = new File("E:\\download\\qqdownload\\892854990\\FileRecv\\代理商信息表-给产品5.27(1).xlsx");
        String path = file.getAbsolutePath();
        System.out.println(path);
        readExcel.readExcelValueForAgent(path);
    }

    public String readExcelValueForAgent(String fileName) throws FileNotFoundException {
        List<Map<String, String>> agentList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        InputStream excelInput = null;
        try {
            excelInput = new FileInputStream(new File(fileName));
            Workbook wb = null;
            boolean isExcel2003 = true;
            if (WDWUtil.isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            //当excel是2003时
            if (isExcel2003) {
                wb = new HSSFWorkbook(excelInput);
            } else {//当excel是2007时
                wb = new XSSFWorkbook(excelInput);
            }
            agentList = readExceltoMap(wb);
            System.out.println(agentList.size());
            jsonArray.addAll(agentList);
            excelInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (excelInput != null) {
                try {
                    excelInput.close();
                } catch (IOException e) {
                    excelInput = null;
                    e.printStackTrace();
                }
            }
        }
        System.out.println(jsonArray.toString());
        return jsonArray.toJSONString();

    }

    public List<Map<String, String>> readExceltoMap(Workbook wb) {
        List<Map<String, String>> agentList = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(0);
        this.totalRows = sheet.getPhysicalNumberOfRows();
        if (totalRows >= 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        for (int rows = 1; rows < totalRows; rows++) {
            Row row = sheet.getRow(rows);
            if (row == null) {
                continue;
            }

            Map<String, String> map = new HashMap<>();
            for (int cell = 1; cell < this.totalCells; cell++) {
                Cell cellc = row.getCell(cell);
                if (cellc != null) {
                   /*if (cell==1&&cellc.getCellType()==Cell.CELL_TYPE_NUMERIC&& StringUtils.isNotBlank(cellc.getStringCellValue())){
                       map.put("agentName",cellc.getStringCellValue());
                   }else */
                    if (cell == 1 && cellc.getCellType() == Cell.CELL_TYPE_STRING && StringUtils.isNotBlank(cellc.getStringCellValue())) {
                        map.put("agentName", cellc.getStringCellValue());
                    } else if (cell == 2 && cellc.getCellType() == Cell.CELL_TYPE_NUMERIC && StringUtils.isNotBlank(String.valueOf((cellc.getNumericCellValue())))) {
//                       map.put("mobileNum", String.valueOf(cellc.getNumericCellValue()));
                        map.put("mobileNum", Long.toString((long) (cellc.getNumericCellValue())));
                    } else if (cell == 3 && cellc.getCellType() == Cell.CELL_TYPE_STRING && StringUtils.isNotBlank(cellc.getStringCellValue())) {
                        map.put("area", cellc.getStringCellValue());
                    } else if (cell == 4 && cellc.getCellType() == Cell.CELL_TYPE_STRING && StringUtils.isNotBlank(cellc.getStringCellValue())) {
                        map.put("agentType", cellc.getStringCellValue());
                    } else if (cell == 5 && (cellc.getCellType() == Cell.CELL_TYPE_STRING || cellc.getCellType() == Cell.CELL_TYPE_NUMERIC)) {
                        if (cellc.getCellType() == Cell.CELL_TYPE_STRING) {
                            map.put("createTime", cellc.getStringCellValue());
                        }else {
                            map.put("createTime",formatDate(HSSFDateUtil.getJavaDate(cellc.getNumericCellValue()).getTime()) );
                        }
                    } else if (cell == 6 && (cellc.getCellType() == Cell.CELL_TYPE_STRING || cellc.getCellType() == Cell.CELL_TYPE_NUMERIC)) {
//                        map.put("endTime", cellc.getStringCellValue());
                        if (cellc.getCellType() == Cell.CELL_TYPE_STRING) {
                            map.put("endTime", cellc.getStringCellValue());
//                            map.put("endTime","---------------------什么鬼------------");
                        }else {
                            map.put("endTime",formatDate(HSSFDateUtil.getJavaDate(cellc.getNumericCellValue()).getTime()) );
                        }
                    } else if (cell == 7 && cellc.getCellType() == Cell.CELL_TYPE_NUMERIC && StringUtils.isNotBlank(String.valueOf((cellc.getNumericCellValue())))) {
                        map.put("schoolSum", Integer.toString((int) (cellc.getNumericCellValue())));
                    } else if (cell == 8 && cellc.getCellType() == Cell.CELL_TYPE_NUMERIC && StringUtils.isNotBlank(String.valueOf((cellc.getNumericCellValue())))) {
                        map.put("patriarchSum", Integer.toString((int) (cellc.getNumericCellValue())));
                    }
                }
            }
            agentList.add(map);

        }
        return agentList;
    }


    public  String formatDate(Long time){
        String date = "";
        Date date1 = new Date(time);
        SimpleDateFormat  formatter = new SimpleDateFormat("yyyy/MM/dd");
        date = formatter.format(date1);

        return date;
    }
}

