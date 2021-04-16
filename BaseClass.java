package testcase;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class BaseClass {
  
	public ChromeDriver driver;
	public String fileName;
	
	
	  @Parameters({"username", "password", "url"})
	  @BeforeMethod
	  public void preCondition(String uname, String pwd, String url) {
		  WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.findElement(By.id("username")).sendKeys(uname);
			driver.findElement(By.id("password")).sendKeys(pwd);
			driver.findElement(By.className("decorativeSubmit")).click();
			driver.findElement(By.linkText("CRM/SFA")).click();
	  }
	  
	  @AfterMethod
	  public void postCondition() {
		  driver.close();
	  }

	  @DataProvider(name = "GetInputData")
	  public String[][] getLeadData() throws IOException {
		  return ReadExcelData.readData(fileName);
	  }
	  
}
