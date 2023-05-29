
package us.piit.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static us.piit.utility.Utility.currentDir;

public class ExcelReader {
    private final Logger LOG = LogManager.getLogger(ExcelReader.class.getName());

    static XSSFWorkbook excelWBook;
    static XSSFSheet excelWSheet;
    static XSSFCell cell;
    String path;

    public ExcelReader(String path){
        this.path = path;
    }

    public String getDataFromCell(String sheet, int rowNum, int colNum){
        try {
            FileInputStream excelFile = new FileInputStream(path);
            excelWBook = new XSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheet(sheet);
            cell = excelWSheet.getRow(rowNum).getCell(colNum);
            String cellValue = cell.getStringCellValue();
            excelFile.close();
            return cellValue;
        }catch (Exception e){
            LOG.info("no file found");
            return "";
        }
    }

    public List<String> getEntireColumnData(String sheet, int rowStart, int colNum){
        List<String> columnData = new ArrayList<>();
        try {
            File file = new File(path);
            FileInputStream excelFile = new FileInputStream(file);
            excelWBook = new XSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheet(sheet);
            for (int i = rowStart; i <= excelWSheet.getLastRowNum(); i++){
                columnData.add(excelWSheet.getRow(i).getCell(colNum).getStringCellValue());
            }
//            int i = rowStart;
//            while (excelWSheet.getRow(i).getCell(colNum).getStringCellValue() != null){
//                columnData.add(excelWSheet.getRow(i).getCell(colNum).getStringCellValue());
//                i++;
//            }
            excelFile.close();
        }catch (Exception e){
            e.printStackTrace();
            LOG.info("no data found");
        }
        return columnData;
    }

    public List<String> getEntireColumnForGivenHeader(String sheet, String headerName){
        int i = 0;
        while (getDataFromCell(sheet, 0, i) != null){
            if(getDataFromCell(sheet, 0, i).equalsIgnoreCase(headerName)){
                getEntireColumnData(sheet, 1, i);
                break;
            }
            i++;
        }
        return getEntireColumnData(sheet, 1, i);
    }

    public String getValueForGivenHeaderAndKey(String sheet, String headerName, String key){
        String value = null;
        int i = 0;
        while (getDataFromCell(sheet, 0, i) != null){
            if(getDataFromCell(sheet, 0, i).equalsIgnoreCase(headerName)){
                for (int j = 0; j < getEntireColumnData(sheet, 1, i).size(); j++){
                    if(getEntireColumnData(sheet, 1, i).get(j).equalsIgnoreCase(key)){
                        value = getEntireColumnData(sheet, 1, i+1).get(j);
                    }
                }
                break;
            }
            i++;
        }
        return value;
    }
    //Read from Excel using Data Provider
    public static Object[][] getEntireColumnData(String path, String sheet, int rowStart, int colNum) {

        ExcelReader excelReader = new ExcelReader(path);
        List<String> columnData = excelReader.getEntireColumnData(sheet, rowStart, colNum);

        Object[][] data = new Object[columnData.size()][1];
        for (int i = 0; i < columnData.size(); i++) {
            data[i][0] = columnData.get(i);
        }
        return data;
    }

    public static String getDataFromCell(String path, String sheet, int rowNum, int colNum) {
        ExcelReader excelReader = new ExcelReader(path);
        String cellValue = excelReader.getDataFromCell(sheet, rowNum, colNum);
        return  cellValue;
    }


    public static void main(String[] args)  {
        ExcelReader excelReader = new ExcelReader(currentDir+"\\manualTestCases\\OrangeHRMTest.xlsx");

        System.out.println(excelReader.getDataFromCell("DataProvider",1,0));
//        System.out.println(currentDir);
//        System.out.println(ExcelReader.getDataFromCell(currentDir+"\\manualTestCases\\OrangeHRMTest.xlsx","DataProvider",1,0));

    }
}
