package data;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadDataForJsonResponse {
    static FileInputStream fis = null;

    public FileInputStream getFileInputStream(){
        String srcFile = System.getProperty("user.dir")+"/src/test/java/data/jsonResponse.xlsx";
        File file = new File(srcFile);
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fis;
    }

    public String[][] getExcelData(String sheetName) throws IOException {
        fis = getFileInputStream();

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet(sheetName);

        int totalNumberOfRows = (sheet.getLastRowNum());
        int totalNumberOfColumns = sheet.getRow(0).getLastCellNum();

        String[][] arrayData = new String[totalNumberOfRows][totalNumberOfColumns];
        for (int i = 1; i <= totalNumberOfRows; i++) {

            XSSFRow row = sheet.getRow(i);

            for (int j = 0; j <= totalNumberOfColumns ; j++) {
                XSSFCell cell = row.getCell(j);

                try {
                    DataFormatter dataFormatter = new DataFormatter();
                    String value = dataFormatter.formatCellValue(cell);
                    arrayData[i - 1][j] = value;
                }
                catch (Exception e){

                }
            }
        }
        wb.close();
        return arrayData;
    }

}
