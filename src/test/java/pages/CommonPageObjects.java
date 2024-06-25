package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonPageObjects {

	private WebDriver driver;

	// Initializing Fluent wait where we have more control over conditions like
	// ignoring exceptions etc.
	Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20))
			.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

	// Initializing Explicit wait where we are waiting for certain condition to be
	// satisfied
	Wait<WebDriver> explicitWait = new WebDriverWait(driver, Duration.ofSeconds(20));

	public CommonPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	By pageTitle = By.cssSelector("h1");

	public String getPageTitle() {
		return driver.findElement(pageTitle).getText();
	}
	
}
