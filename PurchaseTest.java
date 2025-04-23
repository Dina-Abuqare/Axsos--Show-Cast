package demoblaze.DemoBlazeAutomation.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;

public class PurchaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setup() {
    	WebDriverManager.chromedriver().setup(); 
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void testAddProductToCart() throws InterruptedException {
    	Thread.sleep(2000);
    	// Click on a product

    	
    	WebElement product = driver.findElement(By.xpath("//a[text()='Samsung galaxy s6']"));
    	product.click();

        Thread.sleep(2000);

        // Click Add to cart
        driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();
        Thread.sleep(2000);

        // Handle alert
        driver.switchTo().alert().accept();

        // Go to cart
        driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
        Thread.sleep(2000);

        // Assert product added
        WebElement productInCart = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/tr/td[2]"));
        Assert.assertTrue(productInCart.isDisplayed(), "Product not added to cart");
    }



    @Test(priority = 2)
    public void testDeleteProductFromCart() throws InterruptedException {
    	driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
        Thread.sleep(2000);

        // Click delete
        driver.findElement(By.xpath("//a[text()='Delete']")).click();
        Thread.sleep(2000);
        }

    @Test(priority = 3)
public void testPurchaseWithValidCredentials() throws InterruptedException {
    // Go to Cart
    	driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
        Thread.sleep(2000);

    // Click Place Order
    driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
    Thread.sleep(1000);

    // Fill form
    driver.findElement(By.id("name")).sendKeys("Mark");
    driver.findElement(By.id("country")).sendKeys("Palestine");
    driver.findElement(By.id("city")).sendKeys("Bethlehem");
    driver.findElement(By.id("card")).sendKeys("1234123412341234");
    driver.findElement(By.id("month")).sendKeys("04");
    driver.findElement(By.id("year")).sendKeys("2025");

    // Click Purchase
    driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
    Thread.sleep(2000);

 // Wait for the sweet alert to appear
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sweet-alert .sa-confirm-button-container .confirm")));

    // Use Actions class to hover over the button and click it
    Actions actions = new Actions(driver);
    actions.moveToElement(okButton).click().perform(); // Hover and click the OK button
    Thread.sleep(2000); // Optional wait to ensure modal closes before proceeding
}
  

@Test(priority = 4)
public void testPurchaseWithInValidName() throws InterruptedException {
	
    // Go to Cart
    	driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
        Thread.sleep(2000);

    // Click Place Order
    driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
    Thread.sleep(1000);

    // Fill form
    driver.findElement(By.id("country")).sendKeys("Palestine");
    driver.findElement(By.id("city")).sendKeys("Bethlehem");
    driver.findElement(By.id("card")).sendKeys("1234123412341234");
    driver.findElement(By.id("month")).sendKeys("04");
    driver.findElement(By.id("year")).sendKeys("2025");

    // Click Purchase
    driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
    Thread.sleep(2000);
   
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert")));

        // ❌ If confirmation appears, test should fail
        Assert.fail("Purchase was successful with missing name — this should NOT happen.");
    } catch (Exception e) {
        // ✅ Expected: No confirmation dialog
        System.out.println("✅ Purchase failed as expected due to missing name.");
    }

    // Close the modal if it's still open
    try {
        WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]"));
        closeBtn.click();
    } catch (Exception ignored) {}
}

@Test(priority = 5)
public void testPurchaseWithinValidcountry() throws InterruptedException {
    // Go to Cart
    	driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
        Thread.sleep(2000);

    // Click Place Order
    driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
    Thread.sleep(1000);

    // Fill form
    driver.findElement(By.id("name")).sendKeys("Mark");
    driver.findElement(By.id("city")).sendKeys("Bethlehem");
    driver.findElement(By.id("card")).sendKeys("1234123412341234");
    driver.findElement(By.id("month")).sendKeys("04");
    driver.findElement(By.id("year")).sendKeys("2025");

    // Click Purchase
    driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
    Thread.sleep(2000);

    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert")));

        // ❌ If confirmation appears, test should fail
        Assert.fail("Purchase was successful with missing name — this should NOT happen.");
    } catch (Exception e) {
        // ✅ Expected: No confirmation dialog
        System.out.println("✅ Purchase failed as expected due to missing name.");
    }

    // Close the modal if it's still open
    try {
        WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]"));
        closeBtn.click();
    } catch (Exception ignored) {}
}
@Test(priority = 6)
public void testPurchaseWithinValidcity() throws InterruptedException {
    // Go to Cart
    	driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
        Thread.sleep(2000);

    // Click Place Order
    driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
    Thread.sleep(1000);

    // Fill form
    driver.findElement(By.id("name")).sendKeys("Mark");
    driver.findElement(By.id("country")).sendKeys("Palestine");
    driver.findElement(By.id("card")).sendKeys("1234123412341234");
    driver.findElement(By.id("month")).sendKeys("04");
    driver.findElement(By.id("year")).sendKeys("2025");

    // Click Purchase
    driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
    Thread.sleep(2000);

    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert")));

        // ❌ If confirmation appears, test should fail
        Assert.fail("Purchase was successful with missing name — this should NOT happen.");
    } catch (Exception e) {
        // ✅ Expected: No confirmation dialog
        System.out.println("✅ Purchase failed as expected due to missing name.");
    }

    // Close the modal if it's still open
    try {
        WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]"));
        closeBtn.click();
    } catch (Exception ignored) {}
}
    

@Test(priority = 7)
public void testPurchaseinValidCredentialcreditcard() throws InterruptedException {
    // Go to Cart
    	driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
        Thread.sleep(2000);

    // Click Place Order
    driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
    Thread.sleep(1000);

    // Fill form
    driver.findElement(By.id("name")).sendKeys("Mark");
    driver.findElement(By.id("country")).sendKeys("Palestine");
    driver.findElement(By.id("city")).sendKeys("Bethlehem");
    driver.findElement(By.id("month")).sendKeys("04");
    driver.findElement(By.id("year")).sendKeys("2025");

    // Click Purchase
    driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
    Thread.sleep(2000);

    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert")));

        // ❌ If confirmation appears, test should fail
        Assert.fail("Purchase was successful with missing name — this should NOT happen.");
    } catch (Exception e) {
        // ✅ Expected: No confirmation dialog
        System.out.println("✅ Purchase failed as expected due to missing name.");
    }

    // Close the modal if it's still open
    try {
        WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]"));
        closeBtn.click();
    } catch (Exception ignored) {}
}
    

    
    
@Test(priority = 8)
public void testPurchaseinValidCredentialmonth() throws InterruptedException {
    // Go to Cart
    	driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
        Thread.sleep(2000);

    // Click Place Order
    driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
    Thread.sleep(1000);

    // Fill form
    driver.findElement(By.id("name")).sendKeys("Mark");
    driver.findElement(By.id("country")).sendKeys("Palestine");
    driver.findElement(By.id("city")).sendKeys("Bethlehem");
    driver.findElement(By.id("card")).sendKeys("1234123412341234");
    driver.findElement(By.id("year")).sendKeys("2025");

    // Click Purchase
    driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
    Thread.sleep(2000);

    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert")));

        // ❌ If confirmation appears, test should fail
        Assert.fail("Purchase was successful with missing name — this should NOT happen.");
    } catch (Exception e) {
        // ✅ Expected: No confirmation dialog
        System.out.println("✅ Purchase failed as expected due to missing name.");
    }

    // Close the modal if it's still open
    try {
        WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]"));
        closeBtn.click();
    } catch (Exception ignored) {}
}


@Test(priority = 9)
public void testPurchaseinValidCredentialyear() throws InterruptedException {
    // Go to Cart
    	driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
        Thread.sleep(2000);
        

    // Click Place Order
    driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
    Thread.sleep(1000);

    // Fill form
    driver.findElement(By.id("name")).sendKeys("Mark");
    driver.findElement(By.id("country")).sendKeys("Palestine");
    driver.findElement(By.id("city")).sendKeys("Bethlehem");
    driver.findElement(By.id("card")).sendKeys("1234123412341234");
    driver.findElement(By.id("month")).sendKeys("04");
   

    // Click Purchase
    driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
    Thread.sleep(2000);

    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert")));

        // ❌ If confirmation appears, test should fail
        Assert.fail("Purchase was successful with missing name — this should NOT happen.");
    } catch (Exception e) {
        // ✅ Expected: No confirmation dialog
        System.out.println("✅ Purchase failed as expected due to missing name.");
    }

    // Close the modal if it's still open
    try {
        WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]"));
        closeBtn.click();
    } catch (Exception ignored) {}
}

@Test(priority = 10)
public void testLogout() throws InterruptedException {
    // Login as a user
    driver.findElement(By.id("login2")).click();
    Thread.sleep(1000);
    
    // Enter valid username and password
    driver.findElement(By.id("loginusername")).sendKeys("hi12345");  
    driver.findElement(By.id("loginpassword")).sendKeys("12345"); 
    driver.findElement(By.xpath("//button[text()='Log in']")).click();
    Thread.sleep(2000);
    driver.switchTo().alert().accept();
    // Navigate to the logout button
    WebElement logoutButton = driver.findElement(By.id("logout2"));
    
    // Click on Log out button
    logoutButton.click();
    Thread.sleep(2000);
    
    // Verify user is logged out by checking login button visibility
    WebElement loginButton = driver.findElement(By.id("login2"));
    Assert.assertTrue(loginButton.isDisplayed(), "User is not logged out successfully!");
}
}

