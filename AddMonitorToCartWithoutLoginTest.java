import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class AddMonitorToCartWithoutLoginTest {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }

    @Test
    public void testCannotAddMonitorToCartWithoutLogin() throws InterruptedException {
        driver.findElement(By.linkText("Monitors")).click();
        Thread.sleep(2000);

        List<WebElement> monitors = driver.findElements(By.cssSelector(".card-title a"));
        Assert.assertTrue(monitors.isEmpty(), "No monitors found in the Monitors category");

        WebElement selectedMonitor = monitors.get(0);
        selectedMonitor.click();
        Thread.sleep(2000);
        WebElement addToCart = driver.findElement(By.linkText("Add to cart"));
        addToCart.click();
        Thread.sleep(2000);
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert Message: " + alertText);

        Assert.assertTrue(alertText.toLowerCase().contains("log in"), "Expected alert login not shown");
        driver.switchTo().alert().accept();
    }

    @AfterTest
    public void closIt() {
            driver.close();

    }
}
