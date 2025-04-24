package QABootcamp_Maven.AxsosAcademyy;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCasesDina {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }


    // TC-001: Valid Sign Up
    @Test(priority = 1)
    public void testValidSignIn() throws InterruptedException {
        driver.findElement(By.id("signin2")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("sign-username")).sendKeys("UsernameNumber1");
        driver.findElement(By.id("sign-password")).sendKeys("password");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@class='btn btn-primary' and contains(text(),'Sign up')]")).click();
        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert Text: " + alertText);
        Assert.assertEquals(alertText, "Sign up successful.", "Alert text did not match the expected message.");
        alert.accept();
    }

    // TC-002: Existing Username
    @Test(priority = 2)
    public void testInvalidSignIn() throws InterruptedException {
    	driver.navigate().refresh();
    	Thread.sleep(2000);  // let the page fully load
    	driver.findElement(By.id("signin2")).click();

      

        driver.findElement(By.id("sign-username")).sendKeys("Username");
        driver.findElement(By.id("sign-password")).sendKeys("password12345");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@class='btn btn-primary' and contains(text(),'Sign up')]")).click();
        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert Text: " + alertText);
        Assert.assertEquals(alertText, "This user already exist.", "Alert text did not match the expected message.");
        alert.accept();
    }

    // TC-003: Invalid Password Format
    @Test(priority = 3)
    public void testInvalidPasswordSignIn() throws InterruptedException {
    	
    	driver.navigate().refresh();
    	Thread.sleep(2000);  // let the page fully load
    	driver.findElement(By.id("signin2")).click();
    	
        driver.findElement(By.id("sign-username")).sendKeys("Usernamee");
        driver.findElement(By.id("sign-password")).sendKeys("password12345!@+234567hj");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@class='btn btn-primary' and contains(text(),'Sign up')]")).click();
        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert Text: " + alertText);
        Assert.assertEquals(alertText, "Invalid Password.", "Unexpected, Successful sign up");
        alert.accept();
    }

    // TC-004: Invalid Username
    @Test(priority = 4)
    public void testInvalidUsernameSignIn() throws InterruptedException {
    	driver.navigate().refresh();
    	Thread.sleep(2000);  // let the page fully load
    	driver.findElement(By.id("signin2")).click();
    	
        driver.findElement(By.id("sign-username")).sendKeys("Usernamee1234!#@1223455");
        driver.findElement(By.id("sign-password")).sendKeys("password");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@class='btn btn-primary' and contains(text(),'Sign up')]")).click();
        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert Text: " + alertText);
        Assert.assertEquals(alertText, "Invalid username.", "Unexpected, Successful sign up");
        alert.accept();
    }

    // TC-005: Empty Fields Sign Up
    @Test(priority = 5)
    public void testInvalidEmptyFieldSignIn() throws InterruptedException {
    	driver.navigate().refresh();
    	Thread.sleep(2000);  // let the page fully load
    	driver.findElement(By.id("signin2")).click();
    	
        driver.findElement(By.id("sign-username")).sendKeys("");
        driver.findElement(By.id("sign-password")).sendKeys("");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@class='btn btn-primary' and contains(text(),'Sign up')]")).click();
        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert Text: " + alertText);
        Assert.assertEquals(alertText, "Please fill out Username and Password.", "Unexpected, Successful signup");
        alert.accept();
    }

    // TC-006: Successful Login
    @Test(priority = 6)
    public void testSuccessfulLoginIn() throws InterruptedException {
    	driver.navigate().refresh();
    	driver.findElement(By.id("login2")).click();
        Thread.sleep(2000);
        
        String username = "Username";

      

        driver.findElement(By.id("loginusername")).sendKeys("Username");
        driver.findElement(By.id("loginpassword")).sendKeys("password");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@class='btn btn-primary' and contains(text(),'Log in')]")).click();
        Thread.sleep(3000);
       

        WebElement nameofuser = driver.findElement(By.id("nameofuser"));
        String text = nameofuser.getText();
        String expectedText = "Welcome " + username;

        Assert.assertEquals(text, expectedText, "Login message did not match.");

        driver.findElement(By.id("logout2")).click();
        Thread.sleep(3000);
    }

 // TC-007: Invalid Username, Valid Password
    @Test(priority = 8)
    public void testInvalidUsernameLogin() throws InterruptedException {
    	
    	driver.navigate().refresh();
    	Thread.sleep(2000);  // let the page fully load
    	driver.findElement(By.id("login2")).click();

        driver.findElement(By.id("loginusername")).sendKeys("Usernamevhbjkbvfsh");
        driver.findElement(By.id("loginpassword")).sendKeys("password");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert: " + alertText);

        Assert.assertEquals(alertText, "User does not exist.");
        alert.accept();
    }

    // TC-008: Valid Username, Invalid Password
    @Test(priority = 9)
    public void testInvalidPasswordLogin() throws InterruptedException {
    	driver.navigate().refresh();
    	Thread.sleep(2000);  // let the page fully load
    	driver.findElement(By.id("login2")).click();


        driver.findElement(By.id("loginusername")).sendKeys("Username");
        driver.findElement(By.id("loginpassword")).sendKeys("password123");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert: " + alertText);

        Assert.assertEquals(alertText, "Wrong password.");
        alert.accept();
    }

    // TC-009: Invalid Username, Invalid Password
    @Test(priority = 10)
    public void testCompletelyInvalidLogin() throws InterruptedException {
    	driver.navigate().refresh();
    	Thread.sleep(2000);  // let the page fully load
    	driver.findElement(By.id("login2")).click();


        driver.findElement(By.id("loginusername")).sendKeys("Username098utrfsdxhjkl");
        driver.findElement(By.id("loginpassword")).sendKeys("wrongpass");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert: " + alertText);

        Assert.assertEquals(alertText, "User does not exist.");
        alert.accept();
    }

    // TC-010: Empty Fields Login
    @Test(priority = 7)
    public void testEmptyFieldsLogin() throws InterruptedException {
    	
    	driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login2")));
        loginBtn.click();
    	

        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert: " + alertText);

        Assert.assertEquals(alertText, "Please fill out Username and Password.");
        alert.accept();
    }

    // TC-011: About Us functionality
    @Test(priority = 11)
    public void testAboutUsFunctionality() throws InterruptedException {

    	driver.navigate().refresh();
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	 
    	 

    	// Wait for the "About Us" and click it
        WebElement aboutUs = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='nav-link' and contains(text(),'About us')]")));
        aboutUs.click();
        driver.findElement(By.xpath("//a[@class='nav-link' and contains(text(),'About us')]")).click();
        Thread.sleep(2000);

        WebElement Text = driver.findElement(By.cssSelector(".vjs-modal-dialog-content"));
        String actualText = Text.getText();

        String expectedText = "The media could not be loaded, either because the server or network failed or because the format is not supported.";
        System.out.println("About Us Text: " + actualText);

        Assert.assertEquals(actualText, expectedText);
    }

    @AfterTest
    public void closeDriver() {
        driver.close();
    }
  
    }

