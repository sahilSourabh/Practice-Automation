package test.PracticeExcelDataDriving;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelTestData {

	public static void main(String[] args) {
		
		
	}
	
	public ArrayList<String> getData(String testcaseName) throws IOException {
		
				// Identify "Testcases" column by scanning the entire 1st row
				// Once column is identified then scan entire testcase column to identify "Purchase" testcase row
				// After you grab purchase testcase row = pull all the data of that row and feed into test
				
				//Sheets->sheet->Rows->row->Cells->cell->Get entire data
				
				FileInputStream fis = new FileInputStream("S://Automation//DataDriving.xlsx");
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				
				ArrayList<String> al = new ArrayList<>();
				
				int sheets = workbook.getNumberOfSheets();
				System.out.println("Number of Sheets : "+sheets);
				
				for(int i=0;i<sheets;i++) {
					
					if(workbook.getSheetName(i).equalsIgnoreCase("testData")) {
						
						XSSFSheet sheet = workbook.getSheetAt(i);
						
						Iterator<Row> rows = sheet.rowIterator();
						Row firstRow = rows.next();
						
						// Identify "Testcases" column by scanning the entire 1st row
						Iterator<Cell> cells = firstRow.cellIterator();
						int k=0, column=0;
						while(cells.hasNext()) {
							
							if(cells.next().getStringCellValue().equalsIgnoreCase("TestCases")) {
								column = k;
							} k++;
						} 
						System.out.println("Column Number : "+column);
						
						// Once column is identified then scan entire testcase column to identify "Purchase" testcase row
						while(rows.hasNext()) {
							
							Row rowValue = rows.next();
							//getCell() gives you the cell value of every row at particular index.
							if (rowValue.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
								
								// After you grab "Purchase" testcase row then pull all the data of that row and feed into test
								Iterator<Cell> cellValues = rowValue.cellIterator();
								while(cellValues.hasNext()) {
									
									Cell cell = cellValues.next();
									
									if(cell.getCellType()==CellType.STRING) {
										
										al.add(cell.getStringCellValue());
									}
									else
									{
										al.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
									}
								}
							}
						}
					}
				}
				return al;
	}

}
