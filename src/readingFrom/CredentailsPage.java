package readingFrom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CredentailsPage {

	WebDriver driver;
	
	@Before
	public void setup()
	{
		
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32 (2)\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://facebook.com/");
	}
	
	@Test
	public void test() throws IOException {
		String filePath="C:\\Users\\Avani\\OneDrive\\Desktop\\Creds.xls";
		
		FileInputStream fs= new FileInputStream(filePath);
		HSSFWorkbook wb= new HSSFWorkbook(fs);
		HSSFSheet sheet=wb.getSheet("Sheet1");
		
		int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
		for(int i=1;i<=rowCount;i++)
		{
			driver.findElement(By.xpath("//input[@type='text']")).sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			driver.findElement(By.xpath("//input[@type='password']")).sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			//String text=driver.findElement(By.xpath("//div[@class='clearfix _5466 _44mg'][position()=2]//div[2]")).getText();
			//System.out.println(text);
			
		}
		wb.close();

	}
}
