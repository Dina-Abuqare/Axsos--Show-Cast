import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
public class LaptopProductDetailsTest {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }

    @Test
    public void testLaptopProductDetails() throws InterruptedException {
        driver.findElement(By.linkText("Laptops")).click();
        Thread.sleep(2000); // Wait for laptops to load

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
    }

    @AfterTest
    public void closeIt() {
            driver.close();
        }
    }
