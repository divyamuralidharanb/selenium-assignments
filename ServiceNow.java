package week4.day1.assignments;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions; 

import io.github.bonigarcia.wdm.WebDriverManager;


public class ServiceNow {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(7));
		driver.get(" https://dev68594.service-now.com/");
		
		//Login
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		driver.findElement(By.id("sysverb_login")).click();
		
		//Search Incident in filter
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident",Keys.ENTER);
		//Choose All
		driver.findElement(By.xpath("//ul[@aria-label='Modules for Application: Incident']/li[6]/div/div/a")).click();
		
		//Create New incident
		driver.findElement(By.id("sysverb_new")).click();
		driver.findElement(By.id("sys_display.incident.caller_id")).sendKeys("Babu");
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		
		Set<String> winHandles = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(winHandles);
		driver.switchTo().window(windowList.get(1));
		driver.findElement(By.xpath("//table[@id='sys_user_table']/tbody/tr[last()]/td[3]/a")).click();
		
		
		
	}
}

