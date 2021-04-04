package week4.day1.assignments;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IRCTC {

	public static void main(String[] args) {
		//Initial setup
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");		
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(7));
		driver.get("https://www.irctc.co.in");
		
		//Handle dialog
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		
		//scroll to flights icon and click
		driver.findElement(By.xpath("//img[@alt='IRCTC Logo']/following::i")).click();
		driver.findElement(By.xpath("//label[text()='FLIGHTS']")).click();
				
		//switch to child window 
		String parentwindow = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String window : windowHandles) {
			if(!window.equals(parentwindow)) {
				driver.switchTo().window(window);
			}
		}
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href,'mailto')]/i")));
		//get customer care email from child window
		String customercare = driver.findElement(By.xpath("//a[contains(@href,'mailto')]")).getText();
		System.out.println("Customer care email: "+customercare);

		//close parent window
		driver.switchTo().window(parentwindow);
		driver.close();
		
	}
}
