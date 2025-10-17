package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.LoginPage;

public class LoginTest extends BaseTest{
	
	

      //TC-006  Valid Login
	  //Aseel 
      @Test(priority = 5)
      public void TestValidCredentialsLogin() throws InterruptedException {
    	 
      LoginPage loginPage = new LoginPage(driver);
      loginPage.RefreshPage();
      loginPage.navigateToLoginPage();
      
      loginPage.Login("UsErNaMe", "123");
      
      Assert.assertTrue(loginPage.isAlertPresent());
      String username = "UsErNaMe";
      Assert.assertTrue(loginPage.isLoginSuccessful(username), "Login was successful.");
    
    
   }
	
        //TC-007  Invalid Username Login
      //Aseel 
	    @Test(priority = 1)
        public void TestInvalidUsernameLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.Login("UserName", "validPass123");
     
        Assert.assertTrue(loginPage.isLoginRejectedWithMessage("Wrong password."));
        loginPage.RefreshPage();
       }
	
	    //TC-008 Invalid Password Login
	    //Aseel 
		@Test(priority = 2)
	   
	    public void testInvalidPasswordLogin() throws InterruptedException {
	    	 LoginPage loginPage = new LoginPage(driver);
	    	 loginPage.navigateToLoginPage();
	    	  loginPage.Login("usernamee", "pass");
	        
	    	
	    	 Assert.assertTrue(loginPage.isLoginRejectedWithMessage("Wrong password."));

	    	 loginPage.RefreshPage();
	    }
		//TC-009 Invalid User Login
		//Rand 
		@Test(priority = 3)
	   
	    public void testCompletelyInvalidLogin() throws InterruptedException {
	    	 LoginPage loginPage = new LoginPage(driver);
	    	 loginPage.navigateToLoginPage();
	    	  loginPage.Login("user", "user1234");
	        
	    	
	    	Assert.assertTrue(loginPage.isLoginRejectedWithMessage("User does not exist."));
	    	 loginPage.RefreshPage();
	     
	    }

		//TC-010 Empty Fields Login
		//Rand
		@Test(priority = 4)
	    public void testEmptyFieldsLogin() throws InterruptedException {
	    	 LoginPage loginPage = new LoginPage(driver);
	    	  loginPage.navigateToLoginPage();
	    	  loginPage.Login("", "");
	        
	    	
	    
	    	 Assert.assertTrue(loginPage.isLoginRejectedWithMessage("Please fill out Username and Password."));
	    	 loginPage.RefreshPage();
	     
	    }
	    
}
