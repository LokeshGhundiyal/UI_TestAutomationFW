package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

import listeners.SeleniumEventListeners;
import pages.HomePage;

public class BaseClass {

	/*
	 * Selenium - browser launch, waits TestNG Excel Database Log4j ReportNG
	 * ExtentReports
	 */

	public WebDriver driverForListener;
	public WebDriver driver;
	public Capabilities capabilities;
	public static Properties configProperties;
	public static Logger logger; // probably this will have to be static as same log file will have to be
									// maintained by all threads
	public SoftAssert softAssert;
	public HomePage homePage;

	public HomePage beforeMethod() {

		// Soft Assert object initialization
		softAssert = new SoftAssert();

		/*
		 * To make this work we have to add couple of dependencies in pom.xml Log4j-core
		 * and Log4j-api and then, Initialize Log4j logger
		 */

		logger = LogManager.getLogger(BaseClass.class);
		logger.info("Log4j File successfully started");

		// Loading properties file which have all execution configurations defined
		configProperties = new Properties();
		try {
			FileInputStream fp = new FileInputStream("./src/test/java/properties/config.properties");
			configProperties.load(fp);
			logger.info("Config Properties file successfully loaded");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage().toString());
		} catch (IOException i) {
			// TODO Auto-generated catch block
			i.printStackTrace();
			logger.info(i.getMessage().toString());
		}

		/*
		 * if remote execution enabled then navigate remote webdriver or else based on
		 * browser name initiate specific driver session
		 */
		String browserName = configProperties.get("browser").toString();
		if (Boolean.parseBoolean(configProperties.getProperty("remoteExecution"))) {
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setPlatform(Platform.fromString("Windows 11"));
			desiredCapabilities.setBrowserName(browserName);
			
			//if error while launching remote driver then skip 
			try {
				driverForListener = new RemoteWebDriver(new URL("http://192.168.29.253:4444"), desiredCapabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new SkipException("Execution on Remote WebDriver Skipped because of error ", e);
			}
		} else {
			//common browser options to add to any browser we will launch based on value
			List<String> browserOptions = new ArrayList<String>();
			browserOptions.add("--disable-notifications");
			browserOptions.add("--start-maximized");

			switch (browserName) {
			case "chrome":
				// Adding chrome options
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments(browserOptions);

				driverForListener = new ChromeDriver(chromeOptions);
				logger.info("Chrome driver successfully launched");
				break;
			case "MicrosoftEdge":
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments(browserOptions);
				
				driverForListener = new EdgeDriver(edgeOptions);
				logger.info("Edge driver successfully launched");
				break;
			case "firefox":
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments(browserOptions);
				
				driverForListener = new FirefoxDriver(firefoxOptions);
				logger.info("Firefox driver successfully launched");
				break;
			default:
				driver = new ChromeDriver();
				logger.info("Default as in - Chrome driver successfully launched");
				break;
			}
		}
		/*
		 * Assigning Original WebDriver instance to Event Firing Decorator which creates
		 * decorated instance of webdriver. This decorated driver instance have Selenium
		 * Listener configured which listens to the events done using this instance
		 */
		WebDriverListener webDriverListener = new SeleniumEventListeners();
		driver.set(new EventFiringDecorator<WebDriver>(webDriverListener).decorate(driverForListener));

		//
		driver.get().get(configProperties.getProperty("url").toString());
		driver.get().manage().window().maximize();
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(configProperties.getProperty("implicitWait"))));

		driver.manage().timeouts()
				.pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(configProperties.getProperty("pageLoadTimeout"))));

		return new HomePage(driver);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
