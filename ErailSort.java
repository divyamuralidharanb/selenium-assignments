package week3.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErailSort {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://erail.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(7));
		
		//Uncheck date selection
		driver.findElement(By.id("chkSelectDateOnly")).click();
		
		//Choose from station as MAS
		Thread.sleep(3);
		driver.findElement(By.id("txtStationFrom")).clear();
		driver.findElement(By.id("txtStationFrom")).sendKeys("Chennai");
		Actions action = new Actions(driver);
		List<WebElement> sources = driver.findElements(By.xpath("//div[@class='autocomplete']/div/div[2]"));
		for (WebElement source : sources) {
			if(source.getText().equals("MAS")) {
				action.moveToElement(source).click().build().perform();
			}
		}
		//Choose to station as Kollam
		driver.findElement(By.id("txtStationTo")).clear();
		driver.findElement(By.id("txtStationTo")).sendKeys("Kollam",Keys.TAB);
		
		//Click on Get Trains button
		driver.findElement(By.id("buttonFromTo")).click();
		
		//Explicit for train table to load
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(4));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@id='divTrainsList']/table/tbody/tr/td[2]/a"), 1));
		
		//Get the train names from the table
		List<WebElement> trainList = driver.findElements(By.xpath("//div[@id='divTrainsList']/table/tbody/tr/td[2]/a"));	
		List<String> trainNames = new ArrayList<String>();
		for (WebElement train : trainList) {
			trainNames.add(train.getText());
		}
		Collections.sort(trainNames);
		for (String trainName : trainNames) {
			System.out.println(trainName);
		}		
	}
}

