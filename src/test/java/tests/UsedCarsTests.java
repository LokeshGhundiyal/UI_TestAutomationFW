package tests;

import org.testng.annotations.Test;

import base.BaseClass;
import pages.ExploreUsedCarsPage;
import pages.HomePage;

public class UsedCarsTests extends BaseClass {

	@Test(invocationCount = 3, singleThreaded = false)
	public void exploreUsedCars() {
		HomePage homePage = beforeMethod();
		ExploreUsedCarsPage exploreUsedCarsPage = homePage.navigateToExploreUsedCars();
		softAssert.assertEquals(exploreUsedCarsPage.getPageTitle(), "USED CARS",
				"Titles for Used Cars page did not match");
		exploreUsedCarsPage.navigateToMumbaiCity();
		softAssert.assertAll("Please check Assertion outcomes");
	}
}
