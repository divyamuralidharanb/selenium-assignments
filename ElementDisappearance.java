package week3.day2.assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ElementDisappearance {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/disapper.html");
		driver.manage().window().maximize();

		//Wait until element disappears
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("btn")));
		System.out.println("Element is displayed? "+driver.findElement(By.id("btn")).isDisplayed());

		if(driver.findElement(By.id("show")).getText().contains("disappeared")) {
			System.out.println(driver.findElement(By.id("show")).getText());
		}
		
		driver.close();
	}

}
