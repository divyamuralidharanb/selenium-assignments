package week2.day2;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FindOpportunities {
	/*
	 * //Pseudo Code
	 * 
	 * 1. Launch URL "http://leaftaps.com/opentaps/control/login"	 * 
	 * 2. Enter UserName and Password Using Xpath Locator	 * 
	 * 3. Click on Login Button using Xpath Locator	 * 
	 * 4. Click on CRM/SFA Link	 * 
	 * 5. Click on Oppurtunites Button	 * 
	 * 6. Click on Find Oppurtunites using Xpath Locator	 * 
	 * 7. Get the List of Oppurtunites available in Oppurtunity id usinf Xpath Locator	 * 
	 * 8. Get the Title of the Page and verify it.
	 */
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		
		driver.findElement(By.linkText("CRM/SFA")).click();
		
		driver.findElement(By.xpath("//a[text()='Opportunities']")).click();
		
		driver.findElement(By.xpath("//a[text()='Find Opportunities']")).click();
		
		List<WebElement> opportunityIdList = driver.findElements(By.xpath("//div[contains(@class,'salesOpportunityId')]/a[contains(@href,'salesOpportunityId')]"));
		for (WebElement opportunityId : opportunityIdList) {
			System.out.println(opportunityId.getText());
		}
		
		if (driver.getTitle().contentEquals("Find Opportunities | opentaps CRM")) {
			System.out.println("Title verified: "+driver.getTitle());
		}
		
		FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE), new File("./snap/findopportunity.jpg"));
	}
	

}
