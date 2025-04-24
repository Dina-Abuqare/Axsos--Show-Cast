package QABootcamp_Maven.AxsosAcademyy;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Automationproject {

	

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
    @Test(priority = 10)
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
    @Test(priority = 6)
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
    
    @Test(priority = 15)//TC11 by Bashir
    public void testContactFormWithValidData() throws InterruptedException {

        driver.findElement(By.xpath("//a[text()='Contact']")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("recipient-email")).sendKeys("bashirrimawi@gmail.com");
        driver.findElement(By.id("recipient-name")).sendKeys("Bashir Rimawi");
        driver.findElement(By.id("message-text")).sendKeys("This is a test message.");

        driver.findElement(By.xpath("//button[text()='Send message']")).click();

        Thread.sleep(2000);
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert Message: " + alertText);

        driver.switchTo().alert().accept();


        assert !alertText.isEmpty();
    }

    @Test(priority = 11)//Tc12 by Bashir
    public void testInvalidEmailInContactForm() throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='Contact']")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("recipient-email")).sendKeys("invalidemail"); // No '@'
        driver.findElement(By.id("recipient-name")).sendKeys("Tester");
        driver.findElement(By.id("message-text")).sendKeys("Message with invalid email");

        driver.findElement(By.xpath("//button[text()='Send message']")).click();

        Thread.sleep(2000);
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert Message: " + alertText);

        driver.switchTo().alert().accept();


        assert !alertText.isEmpty();
    }

    @Test(priority = 12)//Tc13
    public void testInvalidNameInContactForm() throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='Contact']")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("recipient-email")).sendKeys("bashirrimawi@gmail.com");
        driver.findElement(By.id("recipient-name")).sendKeys("12345");
        driver.findElement(By.id("message-text")).sendKeys("Trying to send with invalid name");

        driver.findElement(By.xpath("//button[text()='Send message']")).click();

        Thread.sleep(2000);
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert Message: " + alertText);

        driver.switchTo().alert().accept();


        assert !alertText.isEmpty();
    }

    @Test(priority = 13)//Tc14
    public void testEmptyMessageField() throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='Contact']")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("recipient-email")).sendKeys("bashirrimawi@gmail.com");
        driver.findElement(By.id("recipient-name")).sendKeys("Bashir Rimawi");

        driver.findElement(By.id("message-text")).clear();

        driver.findElement(By.xpath("//button[text()='Send message']")).click();

        Thread.sleep(2000);
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert Message: " + alertText);

        driver.switchTo().alert().accept();

        assert !alertText.isEmpty();
    }

    @Test(priority = 14)//Tc15
    public void testAllFieldsEmptyInContactForm() throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='Contact']")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("recipient-email")).clear();
        driver.findElement(By.id("recipient-name")).clear();
        driver.findElement(By.id("message-text")).clear();

        driver.findElement(By.xpath("//button[text()='Send message']")).click();

        Thread.sleep(2000);
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert Message: " + alertText);

        driver.switchTo().alert().accept();

        assert !alertText.isEmpty();
    }
    
    @Test(priority = 22)
    public void testPurchaseWithValidCredentials() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("name")).sendKeys("Mark");
        driver.findElement(By.id("country")).sendKeys("Palestine");
        driver.findElement(By.id("city")).sendKeys("Bethlehem");
        driver.findElement(By.id("card")).sendKeys("1234123412341234");
        driver.findElement(By.id("month")).sendKeys("04");
        driver.findElement(By.id("year")).sendKeys("2025");

        driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sweet-alert .sa-confirm-button-container .confirm")));

        Actions actions = new Actions(driver);
        actions.moveToElement(okButton).click().perform();
        Thread.sleep(2000);
    }

        @Test(priority = 16)
        public void testPurchaseWithInValidName ()  throws InterruptedException {

            driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
            Thread.sleep(2000);

            driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("country")).sendKeys("Palestine");
            driver.findElement(By.id("city")).sendKeys("Bethlehem");
            driver.findElement(By.id("card")).sendKeys("1234123412341234");
            driver.findElement(By.id("month")).sendKeys("04");
            driver.findElement(By.id("year")).sendKeys("2025");

            driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
            Thread.sleep(2000);

            try {
            	WebDriverWait   wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert")));

                Assert.fail("Purchase was successful with missing name — this should NOT happen.");
            } catch (Exception e) {
                System.out.println("✅ Purchase failed as expected due to missing name.");
            }

            try {
                WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]"));
                closeBtn.click();
            } catch (Exception ignored) {
            }
        }

        @Test(priority = 17)
        public void testPurchaseWithinValidcountry () throws InterruptedException {
            driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
            Thread.sleep(2000);

            driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("name")).sendKeys("Mark");
            driver.findElement(By.id("city")).sendKeys("Bethlehem");
            driver.findElement(By.id("card")).sendKeys("1234123412341234");
            driver.findElement(By.id("month")).sendKeys("04");
            driver.findElement(By.id("year")).sendKeys("2025");

            driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
            Thread.sleep(2000);

            try {
            	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert")));

                Assert.fail("Purchase was successful with missing name — this should NOT happen.");
            } catch (Exception e) {
                System.out.println("✅ Purchase failed as expected due to missing name.");
            }

            try {
                WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]"));
                closeBtn.click();
            } catch (Exception ignored) {
            }
        }
        @Test(priority = 18)
        public void testPurchaseWithinValidcity () throws InterruptedException {
            driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
            Thread.sleep(2000);

            driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("name")).sendKeys("Mark");
            driver.findElement(By.id("country")).sendKeys("Palestine");
            driver.findElement(By.id("card")).sendKeys("1234123412341234");
            driver.findElement(By.id("month")).sendKeys("04");
            driver.findElement(By.id("year")).sendKeys("2025");

            driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
            Thread.sleep(2000);

            try {
               WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert")));

                Assert.fail("Purchase was successful with missing name — this should NOT happen.");
            } catch (Exception e) {
                System.out.println("✅ Purchase failed as expected due to missing name.");
            }

            try {
                WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]"));
                closeBtn.click();
            } catch (Exception ignored) {
            }
        }


        @Test(priority = 19)
        public void testPurchaseinValidCredentialcreditcard () throws InterruptedException {
            driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
            Thread.sleep(2000);

            driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("name")).sendKeys("Mark");
            driver.findElement(By.id("country")).sendKeys("Palestine");
            driver.findElement(By.id("city")).sendKeys("Bethlehem");
            driver.findElement(By.id("month")).sendKeys("04");
            driver.findElement(By.id("year")).sendKeys("2025");

            driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
            Thread.sleep(2000);

            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert")));

                Assert.fail("Purchase was successful with missing name — this should NOT happen.");
            } catch (Exception e) {
                System.out.println("✅ Purchase failed as expected due to missing name.");
            }

            try {
                WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]"));
                closeBtn.click();
            } catch (Exception ignored) {
            }
        }


        @Test(priority = 20)
        public void testPurchaseinValidCredentialmonth () throws InterruptedException {
            driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
            Thread.sleep(2000);

            driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("name")).sendKeys("Mark");
            driver.findElement(By.id("country")).sendKeys("Palestine");
            driver.findElement(By.id("city")).sendKeys("Bethlehem");
            driver.findElement(By.id("card")).sendKeys("1234123412341234");
            driver.findElement(By.id("year")).sendKeys("2025");

            driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
            Thread.sleep(2000);

            try {
               WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert")));

                Assert.fail("Purchase was successful with missing name — this should NOT happen.");
            } catch (Exception e) {
                System.out.println("✅ Purchase failed as expected due to missing name.");
            }

            try {
                WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]"));
                closeBtn.click();
            } catch (Exception ignored) {
            }
        }


        @Test(priority = 21)
        public void testPurchaseinValidCredentialyear () throws InterruptedException {
            driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
            Thread.sleep(2000);


            driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("name")).sendKeys("Mark");
            driver.findElement(By.id("country")).sendKeys("Palestine");
            driver.findElement(By.id("city")).sendKeys("Bethlehem");
            driver.findElement(By.id("card")).sendKeys("1234123412341234");
            driver.findElement(By.id("month")).sendKeys("04");

            driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
            Thread.sleep(2000);

            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert")));

                Assert.fail("Purchase was successful with missing name — this should NOT happen.");
            } catch (Exception e) {
                System.out.println("✅ Purchase failed as expected due to missing name.");
            }

            try {
                WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]"));
                closeBtn.click();
            } catch (Exception ignored) {
            }
        }
        
        @Test(priority = 23)//Tc27
        public void testPhoneProductDetails() throws InterruptedException {
            driver.findElement(By.linkText("Phones")).click();
            Thread.sleep(2000); // Wait for phones to load

            List<WebElement> phones = driver.findElements(By.cssSelector(".card-title a"));
            Assert.assertTrue(phones.size() > 0, "No phones found in the Phones categore");

            WebElement selectedPhone = phones.get(0);
            String phoneName = selectedPhone.getText();
            selectedPhone.click();

            Thread.sleep(2000);

            String displayedName = driver.findElement(By.cssSelector(".name")).getText();
            Assert.assertEquals(displayedName, phoneName, "Product name not the sam");

            WebElement priceElement = driver.findElement(By.cssSelector(".price-container"));
            Assert.assertTrue(priceElement.isDisplayed(), "Price is not displayed.");

            WebElement description = driver.findElement(By.cssSelector("#more-information > p"));
            Assert.assertTrue(description.isDisplayed(), "Description is not visible.");

            WebElement addToCartBtn = driver.findElement(By.linkText("Add to cart"));
            Assert.assertTrue(addToCartBtn.isDisplayed() && addToCartBtn.isEnabled(), "Add to Cart button is not visible");
            driver.navigate().back();
            Thread.sleep(2000);
        }

        @Test(priority = 24)//Tc28
        public void testMonitorProductDetails() throws InterruptedException {
            driver.findElement(By.linkText("Monitors")).click();
            Thread.sleep(2000);

            List<WebElement> monitors = driver.findElements(By.cssSelector(".card-title a"));
            Assert.assertTrue(monitors.size() > 0, "No monitors found in the Monitors category.");

            WebElement selectedMonitor = monitors.get(0);
            String monitorName = selectedMonitor.getText();
            selectedMonitor.click();

            Thread.sleep(2000);

            String displayedName = driver.findElement(By.cssSelector(".name")).getText();
            Assert.assertEquals(displayedName, monitorName, "Monitor name not same");

            WebElement price = driver.findElement(By.cssSelector(".price-container"));
            Assert.assertTrue(price.isDisplayed(), "Price is not displayed.");

            WebElement description = driver.findElement(By.cssSelector("#more-information > p"));
            Assert.assertTrue(description.isDisplayed(), "Descrption is not visible.");

            WebElement addToCart = driver.findElement(By.linkText("Add to cart"));
            Assert.assertTrue(addToCart.isDisplayed() && addToCart.isEnabled(), "Add to Cart button is not visib");
            driver.navigate().back();
            Thread.sleep(2000);
        }
        @Test(priority = 25)//Tc29
        public void testLaptopProductDetails() throws InterruptedException {
            driver.findElement(By.linkText("Laptops")).click();
            Thread.sleep(2000); // Wait for lap tops to load

            List<WebElement> laptops = driver.findElements(By.cssSelector(".card-title a"));
            Assert.assertTrue(laptops.size() > 0, "No laptops found in the Laptops category");

            WebElement selectedLaptop = laptops.get(0);
            String laptopName = selectedLaptop.getText();
            selectedLaptop.click();

            Thread.sleep(2000);

            String displayedName = driver.findElement(By.cssSelector(".name")).getText();
            Assert.assertEquals(displayedName, laptopName, "Laptop name not same");

            WebElement priceElement = driver.findElement(By.cssSelector(".price-container"));
            Assert.assertTrue(priceElement.isDisplayed(), "Price is not displayed.");

            WebElement description = driver.findElement(By.cssSelector("#more-information > p"));
            Assert.assertTrue(description.isDisplayed(), "Description is not visibl");

            WebElement addToCartBtn = driver.findElement(By.linkText("Add to cart"));
            Assert.assertTrue(addToCartBtn.isDisplayed() && addToCartBtn.isEnabled(), "Add to Cart button is not visible");
            driver.get("https://www.demoblaze.com/");
            Thread.sleep(2000);
        }
     // TC-011: About Us functionality
        @Test(priority = 26)
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
        
        @Test(priority = 29)
        public void testLogout() throws InterruptedException {

        	driver.navigate().refresh();
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        	 
            driver.findElement(By.id("login2")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("loginusername")).sendKeys("hi12345");
            driver.findElement(By.id("loginpassword")).sendKeys("12345");
            driver.findElement(By.xpath("//button[text()='Log in']")).click();
            Thread.sleep(2000);
            driver.switchTo().alert().accept();
            // Navigate to the logout button
            WebElement logoutButton = driver.findElement(By.id("logout2"));

            logoutButton.click();
            Thread.sleep(2000);

            WebElement loginButton = driver.findElement(By.id("login2"));
            Assert.assertTrue(loginButton.isDisplayed(), "User is not logged out successfully!");
        }

        @Test(priority = 27)
        public void testAddProductToCart() throws InterruptedException {
            Thread.sleep(2000);


            WebElement product = driver.findElement(By.xpath("//a[text()='Samsung galaxy s6']"));
            product.click();

            Thread.sleep(2000);

            driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();
            Thread.sleep(2000);

            driver.switchTo().alert().accept();

            driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
            Thread.sleep(2000);

            WebElement productInCart = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/tr/td[2]"));
            Assert.assertTrue(productInCart.isDisplayed(), "Product not added to cart");
        }
        @Test(priority=28)
        public void testDeleteProductFromCart() throws InterruptedException {
            driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
            Thread.sleep(2000);

            driver.findElement(By.xpath("//a[text()='Delete']")).click();
            Thread.sleep(2000);
        }
        
      
        
        @Test(priority = 30)//Tc30
        public void testCannotAddMonitorToCartWithoutLogin() throws InterruptedException {
            driver.findElement(By.linkText("Monitors")).click();
            Thread.sleep(2000);

            List<WebElement> monitors = driver.findElements(By.cssSelector(".card-title a"));
            Assert.assertTrue(monitors.size()>0, "No monitors found in the Monitors category");

            WebElement selectedMonitor = monitors.get(0);
            selectedMonitor.click();
            Thread.sleep(2000);
            WebElement addToCart = driver.findElement(By.linkText("Add to cart"));
            addToCart.click();
            Thread.sleep(2000);
            String alertText = driver.switchTo().alert().getText();
            System.out.println("Alert Message: " + alertText);

            driver.switchTo().alert().accept();
            driver.get("https://www.demoblaze.com/");
            Thread.sleep(2000);
        }

        @Test(priority = 31)//Tc31
        public void testCannotAddPhoneToCartWithoutLogin() throws InterruptedException {
            driver.findElement(By.linkText("Phones")).click();
            Thread.sleep(2000);
            List<WebElement> phones = driver.findElements(By.cssSelector(".card-title a"));
            Assert.assertTrue(phones.size() > 0, "No phones found in the Phones category");

            WebElement selectedPhone = phones.get(0);
            selectedPhone.click();
            Thread.sleep(2000);

            WebElement addToCartBtn = driver.findElement(By.linkText("Add to cart"));
            addToCartBtn.click();

            Thread.sleep(2000);
            String alertText = driver.switchTo().alert().getText();
            System.out.println("Alert Message: " + alertText);
            driver.switchTo().alert().accept();
            driver.get("https://www.demoblaze.com/");
            Thread.sleep(2000);
        }
        
        


    
    @AfterClass
    public void closeDriver() {
        driver.close();
    }
    

}
