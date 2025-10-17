package Tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.LogoutPage;

public class LogoutTest extends BaseTest {

	//Rand 
	@Test(priority = 1)
	    public void testLogoutFunctionality() throws InterruptedException {
		  
		    LogoutPage logoutPage = new LogoutPage(driver);
		    logoutPage.navigateToLogoutPage();
		    logoutPage.Login("UsErNaMe", "123");
	        logoutPage.logout();
	        Assert.assertTrue(logoutPage.isLoggedOut());
	    }
}
