package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends CommonPageObjects {
	private WebDriver driver;

	private By newCarsMenuItem = By.xpath("//div[text()='NEW CARS']");
	private By findNewCars = By.linkText("Find New Cars");

	private By usedCarsMenuItem = By.xpath("//div[text()='USED CARS']");
	private By exploreUsedCars = By.linkText("Explore Used Cars");

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public NewCarsPage navigateToFindNewCars() {

		driver.findElement(newCarsMenuItem).click();
		driver.findElement(findNewCars).click();

		return new NewCarsPage(driver);
	}

	public ExploreUsedCarsPage navigateToExploreUsedCars() {
		// Examples of Explicit wait
		// Explicit wait where we are waiting for certain condition to be satisfied

		Wait<WebDriver> explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

		explicitWait.until(d -> {
			driver.findElement(usedCarsMenuItem).click();
			return true;
		});
		driver.findElement(exploreUsedCars).click();
		/*
		 * fluentWait.until(d ->{ driver.findElement(exploreUsedCars).click(); return
		 * true; });
		 */

		return new ExploreUsedCarsPage(driver);
	}

}
