import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
public class purchase {   //Tc18-25
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }
    @Test(priority = 1)
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


        @Test(priority = 2)
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
                wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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

        @Test(priority = 3)
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
                wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
        @Test(priority = 4)
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
                wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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


        @Test(priority = 5)
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


        @Test(priority = 6)
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
                 wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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


        @Test(priority = 7)
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
                 wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
        @AfterTest
        public void closeIt () {
            driver.close();
        }
    }
}
