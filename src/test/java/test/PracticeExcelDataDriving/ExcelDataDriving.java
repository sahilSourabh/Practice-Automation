package test.PracticeExcelDataDriving;

import java.io.IOException;
import java.util.ArrayList;

public class ExcelDataDriving {

	public static void main(String[] args) throws IOException {
		
		ExcelTestData dataDriving = new ExcelTestData();
		ArrayList<String> data = dataDriving.getData("Purchase");
		
		System.out.println(data);
		
		for (String values: data) {
			
			System.out.println(values);
		}
		

	}

}
