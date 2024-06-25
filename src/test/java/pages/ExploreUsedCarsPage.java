package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExploreUsedCarsPage extends CommonPageObjects {

	private WebDriver driver;
	
	public ExploreUsedCarsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	private By mumbaiCity = By.xpath("//a[@data-cityname='Mumbai']//span");
	
	public void navigateToMumbaiCity() {
		
		driver.findElement(mumbaiCity).click();
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		 * wait.until(d -> { driver.findElement(mumbaiCity).click(); return true; });
		 * wait.until(ExpectedConditions.elementToBeClickable(mumbaiCity));
		 */
		
		

	}

}
