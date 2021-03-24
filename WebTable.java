package week2.day2;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTable {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.navigate().to("http://leafground.com/pages/table.html");
		driver.manage().window().maximize();
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snaps/snap1.jpg");
		FileUtils.copyFile(source, dest);
		
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='table_id']//tr"));
		System.out.println(rows.size());
		List<WebElement> mentorsList = driver.findElements(By.xpath("//table[@id='table_id']/tbody/tr/td[5]"));
		List<WebElement> progressList = driver.findElements(By.xpath("//table[@id='table_id']/tbody/tr/td[2]"));
		for (WebElement mentor : mentorsList) {			
			System.out.println(mentor.getText());
		}
		for (WebElement progress : progressList) {			
			System.out.println(progress.getText());
		}
		driver.close();
	}

}
