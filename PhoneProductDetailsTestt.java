import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
public class PhoneProductDetailsTestt {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }

    @Test
    public void testPhoneProductDetails() throws InterruptedException {
        driver.findElement(By.linkText("Phones")).click();
        Thread.sleep(2000); 

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
    }

    @AfterTest
    public void closeIt() {
            driver.close();

    }
}
