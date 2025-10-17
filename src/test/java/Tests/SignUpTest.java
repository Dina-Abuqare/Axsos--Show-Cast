package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.SignUpPage;


public class SignUpTest extends BaseTest {

	// TC-001 Valid Sign Up Test
	// Ruba
	@Test(priority = 5)
	public void testValidSignIn() throws InterruptedException {
	    SignUpPage signup = new SignUpPage(driver);
	    signup.navigateToSignUpPage();

	    String baseUsername = "username";
	    String uniqueUsername = baseUsername + System.currentTimeMillis();  // unique user each time /ex.username1718471234567
	    String password = "validPass123";

	    signup.SignUp(uniqueUsername, password);
	    Assert.assertTrue(signup.isAlertWithMessage("Sign up successful."));
	    signup.RefreshPage();
		
	}


    // TC-002 Existing User Test
    //Ruba
    @Test(priority = 4)
    public void testInvalidSignIn() throws InterruptedException {
        SignUpPage signup = new SignUpPage(driver);
        signup.navigateToSignUpPage();

        String username = "usernamee";
        String password = "ValidPass123";

        signup.SignUp(username, password);
        Assert.assertTrue(signup.isAlertWithMessage("This user already exist."));
        signup.RefreshPage();
    }

    // TC-003 Invalid Password Test
    //Ruba
    @Test(priority = 3)
    public void testInvalidPasswordSignIn() throws InterruptedException {
        SignUpPage signup = new SignUpPage(driver);
        signup.navigateToSignUpPage();

        String username = "validuser1";
        String password = "123"; // too short

        signup.SignUp(username, password);
        Assert.assertTrue(signup.isAlertWithMessage("Invalid Password."));
        signup.RefreshPage();
    }

    // TC-004 Invalid Username Test
    //Dina
    @Test(priority = 2)
    public void testInvalidUsernameSignIn() throws InterruptedException {
        SignUpPage signup = new SignUpPage(driver);
        
        signup.navigateToSignUpPage();

        String username = "!@#invalidUser"; 
        String password = "ValidPass123";

        signup.SignUp(username, password);
        Assert.assertTrue(signup.isAlertWithMessage("Invalid username."));
        signup.RefreshPage();
    }

    // TC-005 Empty Fields Test
    //Dina
    @Test(priority = 1)
    public void testInvalidEmptyFieldSignIn() throws InterruptedException {
        SignUpPage signup = new SignUpPage(driver);
        signup.navigateToSignUpPage();

        String username = "";
        String password = "";

        signup.SignUp(username, password);
        Assert.assertTrue(signup.isAlertWithMessage("Please fill out Username and Password."));
        signup.RefreshPage();
    }
}
