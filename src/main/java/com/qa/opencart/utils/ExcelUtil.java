package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	//excel sheet path
	public static final String TEST_DATA_SHEET = "./src/test/resources/TestData/opencart/opencart.xlsx";
	
	private static Workbook book;
	
	public static Sheet sheet;
	
	
	public static Object[][] getTestData(String sheetname) {
		
		
		Object[][] data = null;//initially 2D array pointing to Null
		
		// creating method as static as it is common to all cases 
		// we have to pass first sheet name to the get test data method
		
		// we have to define the excel file path
		
		
		try {
			
			//fileinstream class will help us to read the data from the Excel
			
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET);
			
			
			//workbook factory class in apachi poi 
			
			//added catch clause 
			
			//workbook is also a class
			
				 book = WorkbookFactory.create(ip);
				 
				 //sheet is also a class
				 
				 sheet = book.getSheet(sheetname);
				 
				 
				  //have to maintain 2D array to store the data 
				 
				 
				data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()]; 
				
				//getlast row numbber gives no of rows in exccel
				
				//getrow(0).getlastcellnum(); means on the Oth row in excel , last cell number gives the total cell count
				
				
				
				//have tp start 2 loops ;to iterate the rows and coloumns
				
				
				for (int i=0;i<=sheet.getLastRowNum()-1;i++) {
					
					for(int j=0;j<=sheet.getRow(0).getLastCellNum()-1;j++) {
						
						
						data[i][j] = sheet.getRow(i+1).getCell(j).toString();
						
						
						
					}
					
					
				}
				
				
				
				
			
			
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
return data;
		
	}
	
	
}
