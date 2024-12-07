package test.PracticeExcelDataDriving;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {

	DataFormatter formatter = new DataFormatter();
	
	@Test (dataProvider="getData")
	public void testcaseData(String greetings, String communication, String id) {
		
		System.out.println(greetings+"\t"+communication+"\t"+id);
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		//return new Object[][] {{"hello","call",1}, {"bye","text",22}};
		
		FileInputStream fis =  new FileInputStream("S:\\Automation\\DataProviderFile.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		int rows = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int columns = row.getLastCellNum();
		
		Object[][] data = new Object[rows-1][columns];
		
		for(int i=0;i<rows-1;i++) {
			
			row = sheet.getRow(i+1);
			
			for(int j=0;j<columns;j++) {
				
				data[i][j] = formatter.formatCellValue(row.getCell(j));
				
			}
		}
		return data;
	}
}
