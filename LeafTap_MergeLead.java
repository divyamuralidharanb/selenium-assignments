package week4.day1.assignments;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class LeafTap_MergeLead {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(8));
		
		driver.get("http://leaftaps.com/opentaps");
		
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		
		driver.findElement(By.linkText("CRM/SFA")).click();
		
		driver.findElement(By.xpath("//a[text()='Leads']")).click();
		
		driver.findElement(By.xpath("//a[text()='Merge Leads']")).click();
		
		String parentWindow = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//input[@id='partyIdFrom']/following-sibling::a/img")).click();
		
		Set<String> fromWinHandles = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(fromWinHandles);
		
		driver.switchTo().window(windowList.get(1));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("id")));
		driver.findElement(By.name("id")).sendKeys("10100");
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='x-grid3-row-table']/tbody/tr[1]/td/div/a")));
		driver.findElement(By.xpath("//table[@class='x-grid3-row-table']/tbody/tr[1]/td/div/a")).click();
		driver.switchTo().window(parentWindow);
		
		driver.findElement(By.xpath("//input[@id='partyIdTo']/following-sibling::a/img")).click();
		Set<String> toWinHandles = driver.getWindowHandles();
		for (String window : toWinHandles) {
			if(!window.equals(parentWindow))
				driver.switchTo().window(window);
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("id")));
		driver.findElement(By.name("id")).sendKeys("10099");
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='x-grid3-row-table']/tbody/tr[1]/td/div/a")));
		driver.findElement(By.xpath("//table[@class='x-grid3-row-table']/tbody/tr[1]/td/div/a")).click();
		driver.switchTo().window(parentWindow);
		
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		
		driver.switchTo().alert().accept();
		
		driver.findElement(By.xpath("//a[text()='Find Leads']")).click();
		
		driver.findElement(By.name("id")).sendKeys("10100");
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		
		Boolean noRecords = driver.findElement(By.xpath("//div[text()='No records to display']")).isDisplayed();
		if(noRecords) {
			System.out.println("No records to display");
		}
		else {
			System.out.println("Lead id still present");
		}
		
		driver.close();
		
	}
}

