package week2.day2;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGround_DropDown {
	
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.navigate().to("http://www.leafground.com/pages/Dropdown.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
		
		//Dropdown1 - selectByIndex
		Select dropdown1 = new Select(driver.findElement(By.id("dropdown1")));
		dropdown1.selectByIndex(1);
		//Dropdown2 - selectByVisibleText
		Select dropdown2 = new Select(driver.findElement(By.name("dropdown2")));
		dropdown2.selectByVisibleText("Appium");
		//Dropdown3 - selectByValue
		Select dropdown3 = new Select(driver.findElement(By.id("dropdown3")));
		dropdown3.selectByValue("3");
		//Dropdown4 - No of elements
		System.out.println("No of elements in the dropdown: "+driver.findElements(By.xpath("//select[@class='dropdown']/option")).size());
		//Dropdown5 - select by sendKeys
		driver.findElement(By.xpath("(//*[@class='example'])[last()-1]/select")).sendKeys("LoadRunner",Keys.TAB);
		//Dropdown6 - Multi select
		for(int i=2; i<=4; i++) {
			driver.findElement(By.xpath("(//*[@class='example'])[last()]/select/option["+i+"]")).click();
		}
		
		File dropdownscreen = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(dropdownscreen, new File("./snap/dropdownsnap.jpg"));
	}

}
