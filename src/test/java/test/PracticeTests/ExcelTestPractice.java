package test.PracticeTests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTestPractice {

public static void main(String[] args) throws InterruptedException, IOException {
		
	
		
		String fruit ="orange";
		String fileName = "C:\\Users\\Sourabh Sahil\\Downloads\\download.xlsx";
		String columnName = "price";
		

		
		//Edit excel-> Get column number of "price"-> Get row number of "Apple"-> Update Excel with row and column
		
		int column = getColumnNumber(fileName,columnName);
		System.out.println("Column number of "+columnName+" : "+column);
		//System.out.println(getrowNumber(fileName,fruit));
		int row = getrowNumber(fileName,fruit);
		System.out.println("Row number of "+fruit+" : "+row);
		
		
	}

	//Edit excel-> Get column number of "price"-> Get row number of "Apple"-> Update Excel with row and column
	
	private static int getrowNumber(String fileName, String fruitName) throws IOException {
		
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		ArrayList<String> al = new ArrayList<>();
		Iterator<Row> rows = sheet.rowIterator();
		Row firstRow = rows.next();
		//Row firstRow = sheet.getRow(0);
		
		int rowNumber =0, r=1;
		int k=1, columnIndex=0;

		Iterator<Cell> cells = firstRow.cellIterator();
		while(cells.hasNext()) {
			
			if(cells.next().getStringCellValue().equalsIgnoreCase("fruit_name")) {
				
				columnIndex = k;
			} k++;
		}
		
		while(rows.hasNext()) {
			
			Row row = rows.next();
			if(row.getCell(columnIndex).getStringCellValue().equalsIgnoreCase(fruitName)) {
				
				rowNumber = r;
					
				} r++;
			}
		return rowNumber;
			
		}
		

	private static int getColumnNumber(String fileName, String column) throws IOException {
		
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		Iterator<Row> rows = sheet.rowIterator();
		Row firstRow = rows.next();
		//Row firstRow = sheet.getRow(0);
		
		int k=1, columnIndex=0;
		Iterator<Cell> cells = firstRow.cellIterator();
		while(cells.hasNext()) {
			
			if(cells.next().getStringCellValue().equalsIgnoreCase(column)) {
				
				columnIndex = k;
			} k++;
		}
		
		return (columnIndex);
	}

}
