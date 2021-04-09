package week4.day2.assignments;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class PepperFry {
	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		
		//1) Go to https://www.pepperfry.com/
		driver.get("https://www.pepperfry.com/");
		
		Actions builder = new Actions(driver);
		
		//2) Mouseover on Furniture and click Office Chairs under Chairs
		builder.moveToElement(driver.findElement(By.xpath("//a[@rel='meta-furniture']"))).perform();
		driver.findElement(By.xpath("//a[text()='Office Chairs']")).click();
		Thread.sleep(7000);
		
		//close the ad pop up in the right bottom corner
		driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
		driver.findElement(By.id("webklipper-publisher-widget-container-notification-close-div")).click();
		driver.switchTo().defaultContent();
		//close the registration pop up
		driver.findElement(By.xpath("//div[@id='regPopUp']/a")).click();
		
		//3) click Executive Chairs
		Thread.sleep(2000);
		//elementClickInterceptible exception, then changed to cliking on parent div rather than the img 
		driver.findElement(By.xpath("//img[@alt='Executive Chairs']/parent::div")).click();
		
		
		//4) Change the minimum Height to 50 in under Dimensions
		//clear first and then enter, else it is taking value as 5030 instead of 50
		driver.findElement(By.xpath("//div[text()='Height']/following-sibling::div/input[1]")).clear();
		driver.findElement(By.xpath("//div[text()='Height']/following-sibling::div/input[1]")).sendKeys("50",Keys.ENTER);
		Thread.sleep(5000);
		
		//5) Add "Galician High Back Executive Chair In Black Colour" chair to Wishlist
		builder
			.moveToElement(driver.findElement(By.xpath("//img[@alt='Galician High Back Executive Chair in Black Colour']")))
			.perform();
		driver.findElement(By.xpath("//a[@data-productname='Galician High Back Executive Chair in Black Colour']")).click();
				
		
		//6) Mouseover on Bedroom and Click Study tables
		builder.moveToElement(driver.findElement(By.xpath("//a[@rel='meta-bedroom']"))).perform();
		driver.findElement(By.xpath("//a[contains(@href,'hover-bedroom') and text()='Study Tables']")).click();
		Thread.sleep(3000);
		
		
		//7) Select Spacewood as Brand
		driver.findElement(By.xpath("//label[text()='Spacewood']")).click();
		Thread.sleep(5000);
		
		
		//8) Select Price as 7000 to 8000
		//driver.findElement(By.xpath("//label[@for='price7000-8000']")).click(); 
		builder.moveToElement(driver.findElement(By.xpath("//label[@for='price7000-8000']"))).click().perform();
		Thread.sleep(3000);
		
		
		//9) Add "SOS Carter Study Table In Lorraine Walnut & Silver Grey Finish" to Wishlist
		driver.findElement(By.xpath("//a[@data-productname='SOS Carter Study Table in Lorraine walnut & silver grey Finish']")).click();
		
		
		//10) Verify the number of items in the Wishlist
		String noOfWishlistItems = driver.findElement(By.xpath("//span[text()='Wishlist']/preceding-sibling::span/span")).getText(); 
		System.out.println("Nunmber of items in wishlist: "+noOfWishlistItems);
		
		
		//11) Navigate to Wishlist
		driver.findElement(By.xpath("//a[@class='wishlist_bar']")).click();
		
		
		//12) Move Table only to Cart from Wishlist
		driver.findElement(By.xpath("//a[contains(text(),'Study Table')]/parent::p/following-sibling::div/div/a")).click();
		Thread.sleep(3000);
		
		
		//13) Click Proceed to Pay Securely
		driver.findElement(By.xpath("//a[text()='Proceed to pay securely ']")).click();
		//pageload
		
		
		//14) Enter Pincode as 600028 in Delivery & Assembly Details and click Go
		driver.findElement(By.id("pin_code")).sendKeys("600028");
		driver.findElement(By.id("pin_check")).click();
		
		
		//15) Click Place Order
		driver.findElement(By.xpath("//a[text()='PLACE ORDER']")).click();
		//pageLoad
		
		
		//16) Capture a screenshot by Clicking on Order Summary
		driver.findElement(By.xpath("//span[text()='ORDER SUMMARY']")).click();
		FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE), new File("./snaps/PepperFry/OrderSummary.png"));
		
		
		//17) Close the browser
		driver.close();
	}
}

