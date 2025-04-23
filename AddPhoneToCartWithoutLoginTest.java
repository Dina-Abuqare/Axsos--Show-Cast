import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class AddPhoneToCartWithoutLoginTest {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }

    @Test
    public void testCannotAddPhoneToCartWithoutLogin() throws InterruptedException {
        // Click on "Phones" category
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
        Assert.assertTrue(alertText.toLowerCase().contains("log in"), "Expected alert about login not shown");
        driver.switchTo().alert().accept();
    }

    @AfterTest
    public void closeIt() {
            driver.close();
        }
    }

