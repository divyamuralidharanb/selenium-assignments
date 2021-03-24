package week2.day2;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContact {

	public static void main(String[] args) throws IOException{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		
		
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		
		driver.findElement(By.linkText("CRM/SFA")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		
		driver.findElement(By.linkText("Create Contact")).click();
		
		driver.findElement(By.id("firstNameField")).sendKeys("Divya");
		driver.findElement(By.id("lastNameField")).sendKeys("Muralidharan");
		driver.findElement(By.id("createContactForm_firstNameLocal")).sendKeys("Diya");
		driver.findElement(By.id("createContactForm_lastNameLocal")).sendKeys("M");
		driver.findElement(By.name("departmentName")).sendKeys("IVS");
		driver.findElement(By.tagName("textarea")).sendKeys("Independent Validation Solutions");
		driver.findElement(By.id("createContactForm_primaryEmail")).sendKeys("divya_m07@infosys.com");
		
		Select state = new Select(driver.findElement(By.id("createContactForm_generalStateProvinceGeoId")));
		state.selectByVisibleText("New York");
		driver.findElement(By.name("submitButton")).click();
		
		driver.findElement(By.linkText("Edit")).click();
		driver.findElement(By.tagName("textarea")).clear();
		driver.findElement(By.id("updateContactForm_importantNote")).sendKeys("Sample account created for parctise");
		driver.findElement(By.xpath("//input[@value='Update']")).click();
		System.out.println("Final Page Title: "+driver.getTitle());
		
		FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE), new File("./snap/createcontact.jpg"));
	}
}
