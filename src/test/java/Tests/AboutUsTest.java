package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.AboutUsPage;

public class AboutUsTest extends BaseTest{

	@Test(priority = 1)
	//Dina 
	    public void testAboutUsModalText() { 
		 AboutUsPage aboutusPage=new AboutUsPage(driver);
	        aboutusPage.clickAboutUs();

	        String actualText = aboutusPage.getAboutUsModalText();
	        String expectedText = "The media could not be loaded, either because the server or network failed or because the format is not supported.";

	        System.out.println("About Us Text: " + actualText);
	        Assert.assertEquals(actualText, expectedText, "The About Us modal text does not match the expected text.");
	    }
}
