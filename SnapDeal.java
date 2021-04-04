package week4.day1.assignments;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class SnapDeal {
	public static void main(String[] args) throws WebDriverException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.get("https://www.snapdeal.com/");
		
		//Choose Sport shoes from Men's Fashion menu
		Actions builder = new Actions(driver);
		builder
			.moveToElement(driver.findElement(By.xpath("//li[text()='More Categories']/following::li/a/span")))
			.moveToElement(driver.findElement(By.xpath("//span[text()='Sports Shoes']")))
			.click()
			.build();
		
		//Fetch Sports shoe count
		String totalSportShoes = driver.findElement(By.xpath("//h1[@category='Sports Shoes']/span")).getText();
		System.out.println(totalSportShoes);
		
		//Choose Training Shoes
		driver.findElement(By.xpath("//div[text()='Sports Shoes']/../following-sibling::ul/li/a/div[text()='Training Shoes']")).click();
		
		//Sort by price low to high
		builder
			.moveToElement(driver.findElement(By.xpath("//span[text()='Sort by:']/following-sibling::div")))
			.moveToElement(driver.findElement(By.xpath("//li[@data-sorttype='plth']/span")))
			.click()
			.build();
		
		String displayPrice = driver.findElement(By.xpath("//section[@data-dpwlbl='Product Grid']/div/div[3]/div/a/div/div/span[2]")).getAttribute("display-price");
		System.out.println(displayPrice);
		
		
		driver.findElement(By.xpath("//input[@placeholder='Search by Brand']")).click();
		driver.findElement(By.xpath("//label[@for='Brand-Puma']")).click();
		driver.findElement(By.xpath("//div[text()='APPLY']")).click();
		
		//Hover over PUma Blue Training Shoes
		builder.moveToElement(driver.findElement(By.xpath("//img[@title='Puma Blue Training Shoes']"))).perform();
		
		//Quick View
		builder.moveToElement(driver.findElement(By.xpath("//img[@title='Puma Blue Training Shoes']/ancestor::a/following-sibling::div/div"))).click().build();
		
		String cost = driver.findElement(By.xpath("//div[contains(text(),'Price')]/following-sibling::div/div[2]/span")).getText().replaceAll("\\D", "");
		String discount = driver.findElement(By.xpath("//div[contains(text(),'Price')]/following-sibling::div/div[2]/span[2]")).getText().replaceAll("[a-zA-Z]", "");
		
		System.out.println("Cost of Puma Blue Training Shoes: "+cost);
		System.out.println("Discount % of Puma Blue Training Shoes: "+discount);
		
		FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE), new File("./snaps/PumaBlueTrainingShoes.jpg"));
		
		driver.findElement(By.xpath("//*[@id='sidebar_modal_right']/div/div/i")).click();
		
		driver.close();
	}
	
}

