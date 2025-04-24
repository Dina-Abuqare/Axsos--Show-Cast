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

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;
public class details {       //Tc27-29
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }

    @Test(priority = 1)//Tc27
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

    @Test(priority = 2)//Tc28
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
    @Test(priority = 3)//Tc29
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
        driver.get("https://www.demoblaze.com/");
        Thread.sleep(2000);
    }
    @AfterTest
    public void closeIt() {
        driver.close();
    }
}
