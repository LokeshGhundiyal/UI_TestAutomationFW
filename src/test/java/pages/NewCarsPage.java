package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewCarsPage extends CommonPageObjects {
	private WebDriver driver;
	
	public NewCarsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	By allNewCarBrands = By.cssSelector("ul.o-XylGE>li>a");
	By resultsCarSearch = By.cssSelector("div.lwnY3h>div>ul li h3");

	public void selectNewCarBrand(String brandName){
		List<WebElement> allNewCarsBrands = driver.findElements(allNewCarBrands);
		for (WebElement elem : allNewCarsBrands) {
			if (elem.getText().toString().contains(brandName)) {
				elem.click();
				break;
			}
		}
	}
	
	public boolean validateCarSearchResults(String brandName) {
		boolean allCarsMatch = true;
		List<WebElement> allCarsNames = driver.findElements(resultsCarSearch);
		for(WebElement element: allCarsNames) {
			if(!element.getText().toString().contains(brandName)) {
				allCarsMatch = false;
			}
		}
		return allCarsMatch;
	}
}
