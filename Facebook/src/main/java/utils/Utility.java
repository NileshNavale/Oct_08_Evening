package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	static String ss;
	static DateTimeFormatter dateTime;
	
	
//	static String path = "C:\\Users\\admin\\Desktop\\Screenshots" ;
	static int i;
	static int j;
	
	//public static WebDriver driver;
	
	
	public static void captureScreenShot(WebDriver driver , String testMethodname) throws IOException 
	{
//			dateTime = DateTimeFormatter.ofPattern("dd_MM_yyy_HH_mm_ss");
//			LocalDateTime now =LocalDateTime.now();
//			ss = dateTime.format(now);
//		String path = "C:\\Users\\admin\\Desktop\\Screenshots" ;
//		TakesScreenshot ts = (TakesScreenshot)driver;
//
//		File src = ts.getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		String FileName = d.toString().replace(":","_").replace(" ", "_") +".jpeg";	
	TakesScreenshot ts = (TakesScreenshot)driver;
	File src = ts.getScreenshotAs(OutputType.FILE);
		
//		SimpleDateFormat df = new SimpleDateFormat("dd_MM_yyyy HH MM SS");
//		String dateFormat = df.format(new Date());
//		String imagename =  dateFormat;
		
		File dest = new File("C:\\Users\\admin\\Desktop\\Screenshots\\amz"+ FileName);
		FileHandler.copy(src, dest);
	
		
	}
	
	public static String getDataFromExcelSheet(String sheetName , int row , int cell) throws IOException , IllegalStateException
	{
		String path = "C:\\Users\\admin\\Desktop\\nil.xlsx";
		InputStream file = new FileInputStream(path);
		//String  value = WorkbookFactory.create(file);
		Workbook book = WorkbookFactory.create(file);
		Sheet sheet =  book.getSheet("Sheet1");
		int lastRow = sheet.getLastRowNum();
		Row rowValue = sheet.getRow(row);
		int lastCellno = rowValue.getLastCellNum();
		Cell cellValue = rowValue.getCell(cell);
		String data = "";
		try {
			data = cellValue.getStringCellValue(); 
		}
		catch (IllegalStateException e) {
			double value = cellValue.getNumericCellValue();
			data = Double.toString(value);
		}
		return data;

	
		
	}
	
}
