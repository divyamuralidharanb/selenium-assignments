package testcase;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ReadExcelData {
	
	  public static String[][] readData(String excelName) throws IOException {
		  
		  //1: SetUp excel document path
		  XSSFWorkbook wb = new XSSFWorkbook("./data/"+excelName+".xlsx");
		  
		  //2: SetUp worksheet
		  XSSFSheet ws = wb.getSheet("Sheet1");
		  
		  //3. Get into the row
		  //XSSFRow row = ws.getRow(1);
		  
		  //4. Get into the cell
		  //XSSFCell cell = row.getCell(0);
		  
		  //5. Read data from cell
		  //System.out.println(cell.getStringCellValue());
		  
		  int rowCount = ws.getLastRowNum();
		  int colCount = ws.getRow(1).getLastCellNum();
		  System.out.println("Row Count "+rowCount+" Column Count: "+colCount);
		  String[][] dataValue = new String[rowCount][colCount];
		  
		  for(int i=1; i<=rowCount; i++) {
			  for(int j=0; j<colCount; j++) {
				  System.out.println(ws.getRow(i).getCell(j).getStringCellValue());
				  dataValue[i-1][j] = ws.getRow(i).getCell(j).getStringCellValue();
			  }
		  }
		 
		  //Close the workbook
		  wb.close();
		  
		  //return the dataset to Test method
		  return dataValue;
	  }
}
