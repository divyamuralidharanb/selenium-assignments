package week4.day2.assignments;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Amazon {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		//1. Launch URL: https://www.amazon.in/
		driver.get("https://www.amazon.in/");
		
		//2. Type "one plus 7 pro mobiles" in Search Box and Enter
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("One plus 7 pro mobiles", Keys.ENTER);
		//driver.findElement(By.id("nav-search-submit-button")).click();  --to click on search icon rather than Keys.ENTER
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@cel_widget_id='MAIN-SEARCH_RESULTS-1']/descendant::img"))));
		
		//3. Print the price of the first resulting mobile
		String firstItemPrice = driver.findElement(By.xpath("//span[@cel_widget_id='MAIN-SEARCH_RESULTS-1']/descendant::span[@class='a-price-whole']")).getText();
		System.out.println("The price of first mobile in search result: "+firstItemPrice);
		
		//4. Click on the Mobile (First resulting) image
		driver.findElement(By.xpath("//span[@cel_widget_id='MAIN-SEARCH_RESULTS-1']/descendant::img")).click();
		
		//5. Switch to the new window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowList.get(1));
		
		//6. Print the number of customer ratings
		String ratings = driver.findElement(By.id("acrCustomerReviewText")).getText();
		System.out.println("Total number of ratings: "+ratings);
		
		//7. Click 'Add to Cart'
		driver.findElement(By.id("submit.add-to-cart")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='attachDisplayAddBaseAlert']/div/h4"))));
		
		//8. Confirm "Added to Cart" text message appeared
		String text = driver.findElement(By.xpath("//div[@id='attachDisplayAddBaseAlert']/div/h4")).getText();
		if(text.equalsIgnoreCase("Added to Cart")) {
			System.out.println("Confirmation Message: "+text);
		}
		else {
			System.out.println("Item not added to Cart");
			System.out.println(text);
		}
		
		
		//9. Click on Proceed to checkout
		driver.findElement(By.xpath("//span[@id='attach-sidesheet-checkout-button']")).click();
		
		
		//10. Confirm the title is "Amazon Sign In"
		System.out.println("Title of the page: "+driver.getTitle());
		
		//11. Click Continue (without entering mobile number/email)
		driver.findElement(By.id("continue")).click();     //span[@id='continue'] 
		
		//12. Verify the error message: Enter your email or mobile phone number
		String missingAlert = driver.findElement(By.xpath("//div[@id='auth-email-missing-alert']/div/div")).getText();
		System.out.println("Validation error message for email/phn number field: "+missingAlert);
		
		//13. Close both browsers
		driver.quit();
	}
}

