package tests;

import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;
import pages.NewCarsPage;

public class NewCarsTests extends BaseClass {

	@Test
	public void findNewCars() {
		String brandName = "Maruti";
		HomePage homePage = beforeMethod();
		NewCarsPage newCarsPage = homePage.navigateToFindNewCars();
		softAssert.assertEquals(homePage.getPageTitle(), "New Cars 122232", "Page Title did not match");
		newCarsPage.selectNewCarBrand(brandName);
		softAssert.assertTrue(newCarsPage.validateCarSearchResults(brandName),"Some of new car results don't match to brand " + brandName);
		softAssert.assertAll("Please check Assertion outcomes");
	}
	
	@Test
	public void dummyTest() {
		beforeMethod();
		System.out.println("---------------this is dummy test------------");
	}
}
