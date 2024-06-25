package listeners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.Reporter;

import base.BaseClass;

public class SeleniumEventListeners extends BaseClass implements WebDriverListener {
	
	public void logInReports(String reportMessage) {
		Reporter.log(reportMessage);
		logger.info(reportMessage);
		ExtentReport_WithTestNGListeners.extentTestThread.get().info(reportMessage);
	}

	public void afterClick(WebElement element) {
		logInReports("clicked on element " + element);
	}

	public void afterGet(WebDriver driver, String url) {
		logInReports("successfully navigate to - " + url);
	}

	public void afterGetTitle(WebDriver driver, String result) {
		logInReports("Title as " + result + " retrieved from system");
	}

	public void afterQuit(WebDriver driver) {
		logInReports("Closed browser");
	}

	public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
		for(int i = 0; i<keysToSend.length;i++) {
			logInReports("Data as " + keysToSend[i] + " input on element " + element);
		}
	}
}
