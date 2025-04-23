import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class MonitorProductDetailsTest {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }

    @Test
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
    }

    @AfterTest
    public void closeIT() {
            driver.close();
        }
    }

