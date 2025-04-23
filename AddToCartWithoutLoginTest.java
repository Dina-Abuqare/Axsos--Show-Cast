import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class AddToCartWithoutLoginTest {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }

    @Test
    public void testCannotAddLaptopToCartWithoutLogin() throws InterruptedException {
        driver.findElement(By.linkText("Laptops")).click();
        Thread.sleep(2000);

        List<WebElement> laptops = driver.findElements(By.cssSelector(".card-title a"));
        Assert.assertTrue(laptops.size() > 0, "No laptops found.");

        WebElement selectedLaptop = laptops.get(0);
        selectedLaptop.click();
        Thread.sleep(2000);
        WebElement addToCart = driver.findElement(By.linkText("Add to cart"));
        addToCart.click();

        Thread.sleep(2000);

        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert: " + alertText);

        Assert.assertTrue(alertText.contains("log in"), "Expected login-required alert not");
        driver.switchTo().alert().accept();
    }

    @AfterTest
    public void closeIt() {
            driver.close();
    }
}
