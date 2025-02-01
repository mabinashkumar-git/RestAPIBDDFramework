package resources;

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

public class DataDriven {
	
	//Identify Test cases coloumn by scanning the entire 1st row
		//once coloumn is identified then scan entire test case column to identify purchase testcase row
		//after you grab purchase testcase row = pull all the data of that row and feed into test
		
		public ArrayList<String> getData(String testcaseName,String sheetName) throws IOException
		{
			//fileInputStream argument
					ArrayList<String> a = new ArrayList<String>();
					
					FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\TestData\\Demosheet.xlsx");
//					FileInputStream fis=new FileInputStream("F://Selenium//UdemyWorkspace//RESTAssuredTest//src//main//java//resources//TestData.xlsx");
					XSSFWorkbook workbook = new XSSFWorkbook(fis);
					
					int sheets = workbook.getNumberOfSheets();
					
					for(int i=0; i<sheets; i++)
					{
						if(workbook.getSheetName(i).equalsIgnoreCase(sheetName)){
						XSSFSheet sheet = workbook.getSheetAt(i);
						//Identify Test cases column by scanning the entire 1st row
						
						Iterator<Row>  rows = sheet.iterator();// sheet is collection of rows
						Row firstrow = rows.next();
						
						Iterator<Cell> cell = firstrow.cellIterator();//row is collection of cells
						
						int k=0;
						int coloumn = 0;
						
					while(cell.hasNext())
					{
						Cell value = cell.next();
						
						if(value.getStringCellValue().equalsIgnoreCase("TestCases"))
						{
							coloumn = k;
							
						}
						
						k++;
					}
					System.out.println("Column :" + coloumn);
					
					//once column is identified then scan entire test case coloum to identify purcjhase testcase row
					while(rows.hasNext())
					{
						
						Row r = rows.next();
						System.out.println("TC name :" + r.getCell(coloumn).getStringCellValue());
						System.out.println("TC name from script :" + testcaseName);
						
						if(r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcaseName))
						{
							
							//after you grab purchase test case row = pull all the data of that row and feed into test
							Iterator<Cell>  cv = r.cellIterator();
							while(cv.hasNext())
							{
								Cell c = cv.next();
								if(c.getCellType() == CellType.STRING)
									{	
										a.add(c.getStringCellValue());
										System.out.println("String value :" + c.getStringCellValue());
									}
								else
									{
										a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							
									}
							}
						}
						break;
					}
				}
		}
		return a;
					
	}
}