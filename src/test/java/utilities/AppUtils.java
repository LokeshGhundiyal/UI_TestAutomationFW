package utilities;

import org.openqa.selenium.By;
import org.testng.Reporter;

import base.BaseClass;

public class AppUtils extends BaseClass {

	public void press(By applicationObject) {

		driver.findElement(applicationObject).click();

		Reporter.log("object clicked");
		logger.info("object clicked");
		//ExtentReport_WithTestNGListeners.test.info("object clicked");
	}

	public void waitingStrategy() {
		
		/*
		 * Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		 * wait.until(d -> revealed.isDisplayed());
		 */
		
		
		/*
		 * Wait<WebDriver> wait = new
		 * FluentWait<>(driver).withTimeout(Duration.ofSeconds(2))
		 * .pollingEvery(Duration.ofMillis(300)).ignoring(
		 * ElementNotInteractableException.class);
		 * 
		 * wait.until(d -> { revealed.sendKeys("Displayed"); return true; });
		 */
	}
}
