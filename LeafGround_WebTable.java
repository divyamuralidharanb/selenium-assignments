package week2.day2;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGround_WebTable {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/table.html");
		driver.manage().window().maximize();
		
		WebElement table = driver.findElement(By.id("table_id"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		List<WebElement> cols = rows.get(1).findElements(By.tagName("td"));
		
		System.out.println("No of rows: "+rows.size());
		System.out.println("No of columns: "+cols.size());
		
		
		List<WebElement> learnDetails = driver.findElements(By.xpath("//table//tr/td[1]"));
	
		for (int i=0; i<learnDetails.size(); i++) {	
			if(learnDetails.get(i).getText().equals("Learn to interact with Elements")) {
				int j=i+2;	//since java list index starts with 0 whereas selenium xpath index starts with 1
				//Also since tr[1] has the headers alone, we need to add additional row for that
				String progress = driver.findElement(By.xpath("//table//tr["+j+"]/td[2]")).getText();
				System.out.println("Progress for Learn to interact with elements is: "+progress);
			}			
		}	
		
		List<WebElement> progressList = driver.findElements(By.xpath("//table//tr/td[2]"));
		int[] progressarray = new int[progressList.size()];
		
		for (int i=0; i<progressList.size(); i++) {
			String replace = progressList.get(i).getText().replace("%", "");
			progressarray[i] = Integer.parseInt(replace);
		}
		Arrays.sort(progressarray);
		System.out.println("Min progress: "+progressarray[0]+"%");
		
		for (int i=0; i<progressList.size(); i++) {			
			if(progressList.get(i).getText().contains(String.valueOf(progressarray[0]))){
				int j = i+2;
				driver.findElement(By.xpath("//table//tr["+j+"]/td[3]/input")).click();
			}		
		}
		
		FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE), new File("./snap/webtablesnap.jpg"));
		driver.close();
	}

}
