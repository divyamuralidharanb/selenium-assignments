package week3.day2.assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		
		//Choosing Jackets and Coats under Women
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='desktop-main' and text()='Women']")));
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Women']"))).perform();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='desktop-categoryLink' and text()='Jackets & Coats']")));
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-categoryLink' and text()='Jackets & Coats']"))).click().build().perform();
		
		//Getting total count
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title-count")));
		String totalItems = driver.findElement(By.className("title-count")).getText().replaceAll("[^0-9]", "");
		System.out.println(totalItems);
		int totalCount = Integer.parseInt(totalItems);
		System.out.println("Total no of Jackets & Coats: "+totalCount);
		
		//Getting category count
		List<WebElement> categories = driver.findElements(By.className("categories-num"));
		String categorycount = "";
		int[] categoryNum = new int[2];
		int categorySum = 0;
		for (int i=0; i < categories.size(); i++) {
			categorycount = categories.get(i).getText().replaceAll("[^0-9]", "");
			categoryNum[i] = Integer.parseInt(categorycount);
			
			System.out.println(categoryNum[i]);
			categorySum += categoryNum[i];
		}
		System.out.println(categorySum);
		//Validating if total count = sum of category count
		if(totalCount == categorySum) {
			System.out.println("The total count and sum of categories match");
		}
		
		//Choosing Coats
		driver.findElement(By.xpath("//input[@value='Coats']/following-sibling::div")).click();
		
		//Choose + more options in brands
		driver.findElement(By.className("brand-more")).click();
		
		//Type Mango and select from pop up
		driver.findElement(By.className("FilterDirectory-searchInput")).sendKeys("MANGO");
		driver.findElement(By.xpath("//ul[@class='FilterDirectory-list']/li/label/div")).click();
		driver.findElement(By.xpath("//ul[@class='FilterDirectory-indices']/following-sibling::span")).click();
		
		//Confirm all the Coats are of brand MANGO
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("product-brand"), 20));
		List<WebElement> brands = driver.findElements(By.className("product-brand"));
		int counter = 0;
		for (WebElement eachbrand : brands) {
			if(eachbrand.getText().equals("MANGO")) {
				counter += 1;
				continue;
			}
			else {
				System.out.println("All brands are not MANGO");
				break;
			}
		}
		System.out.println(counter);
		
		//Sort by Better discount and get least price from results
		driver.findElement(By.className("sort-sortBy")).click();
		driver.findElement(By.xpath("//div[@class='horizontal-filters-sortContainer']/descendant::li[3]/label")).click();
		action.moveToElement(driver.findElement(By.xpath("//ul[@class='results-base']/li[1]"))).perform();
		
		//Print price of first coat after sorting by better discount
		System.out.println(driver.findElement(By.xpath("//ul[@class='results-base']/li[1]/a//div[@class='product-price']/span/span[1]")).getText());
		
		//Add the first coat to wishList
		action.moveToElement(driver.findElement(By.xpath("//ul[@class='results-base']/li[1]/div[3]"))).perform();
		action.moveToElement(driver.findElement(By.xpath("//ul[@class='results-base']/li[1]/div[3]/span"))).click().build().perform();
		System.out.println("Title of Page: "+driver.getTitle());
		
		driver.close();
	}
}
