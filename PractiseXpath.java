package week2.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PractiseXpath {
	/*
	**No need to write Selenium script - Just find these elements in the DOM using only XPaths	
	Try all the xpaths that you've learnt (basic, text-based, axes..)
	1. Launch the URL	
	2. Enter the username based on its label	
	3. Enter the password based on its label	
	4. Click Login	
	5. Click on CRMSFA link	
	6. Click on Leads link	
	7. Click on Merge Leads link	
	8. Click the first img icon	
	9. Select the first resulting lead	
	10. Click the second img icon	
	11. Select the second resulting lead	
	12. Click on Merge Lead (submit) button
	13. Get the company name text based on its label	
	*/
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//label[@for='username']/following-sibling::input"));
		
		driver.findElement(By.xpath("//label[@for='password']/following-sibling::input"));
		
		driver.findElement(By.xpath("//input[@value='Login' and @class='decorativeSubmit']"));
		
		driver.findElement(By.xpath("//div[@id='label']/a"));
		
		driver.findElement(By.xpath("//a[text()='Leads']"));
		
		driver.findElement(By.xpath("//ul[@class='shortcuts']/li[4]/a"));
		
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]"));
		
		
	}

}
