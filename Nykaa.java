package week4.day2.assignments;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Nykaa {
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//1. Launch URL: "https://www.nykaa.com/"
		driver.get("https://www.nykaa.com/");
		
		
		//2. Enter text as Perfumes in Search Bar and press Enter
		driver.findElement(By.id("SearchInputBox")).sendKeys("Perfumes",Keys.ENTER);
		
		
		//3. Get The Names Of all the Perfumes Displayed
		List<WebElement> perfumeNameList = driver.findElements(By.xpath("//div[@class='m-content__product-list__title']/h2/span"));
		System.out.println("Perfume Name List displayed:");
		for (WebElement perfume : perfumeNameList) {
			System.out.println(perfume.getText());
		}
		
		//4. Get The Price of all the Perfumes Displayed
		List<WebElement> perfumePriceList = driver.findElements(By.xpath("//span[@class='post-card__content-price-offer']"));
		List<Integer> perfumePrices = new ArrayList<Integer>();
		System.out.println("Perfume Price List displayed:");
		for (WebElement perfume : perfumePriceList) {
			System.out.println(perfume.getText());
			perfumePrices.add(Integer.parseInt(perfume.getText().replaceAll("\\D", "")));
		}
		Integer max = Collections.max(perfumePrices);
		System.out.println("Highest Price: "+max);
		
		//5. Click on the Highest Price Perfume  
		//To get perfume name of highest price perfume
		String highPricePerfume = driver.findElement(By.xpath("//span[text()='"+max+"']/ancestor::div[contains(@class,'product-list__price')]/preceding-sibling::div[contains(@class,'product-list__title')]/h2/span")).getText();
		System.out.println("Highest price perfume name: "+highPricePerfume);
		//click on it
		driver.findElement(By.xpath("//span[text()='"+max+"']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowList.get(1));
		
		
		//6. Click on Add To Bag
		driver.findElement(By.xpath("//button[text()='ADD TO BAG']")).click();
		
		
		//7. verify Message Product Added To the bag
		//wait for element to be visible
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='add-to-bag-text']"))));
		String msg = driver.findElement(By.xpath("//div[@class='add-to-bag-text']")).getText();
		System.out.println(msg);
		
		//8. Click on bag Icon
		driver.findElement(By.xpath("//div[@class='AddBagIcon']")).click();
		
		
		//9. Get the Grand Total Value
		String grandTotal = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div")).getText();
		System.out.println("Grand Total in Cart: "+grandTotal);
		
		
		//10. Click on Proceed
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Proceed']")));
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		
		
		//11. Click on Continue as Guest
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']"))));
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		
		
		//12. Fill all the Fields in Address 
		driver.findElement(By.name("name")).sendKeys("Divya Muralidharan");
		driver.findElement(By.name("email")).sendKeys("divya@gmail.com");
		driver.findElement(By.name("phoneNumber")).sendKeys("9500230129");
		driver.findElement(By.name("pinCode")).sendKeys("691012");
		driver.findElement(By.xpath("//legend[text()='Address']/following-sibling::textarea")).sendKeys("50, curzon road, kollam");
		driver.findElement(By.xpath("//button[text()='SHIP TO THIS ADDRESS']")).click();
		
		
		//13. Click on Paynow
		driver.findElement(By.xpath("//button[contains(text(),'PAY')]")).click();
		
		
		//14. Get the Error Message displayed in Red Color
		String errorMsg = driver.findElement(By.xpath("//div[@class='form-field  error']/div/following-sibling::span")).getText();
		System.out.println("Error message from PayNow section: "+errorMsg);
		
		//15. close the current browser
		driver.close();
		
	}
}

