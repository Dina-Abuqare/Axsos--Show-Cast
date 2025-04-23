import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
public class TestCases {
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
    @Test
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
    @Test
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
    @Test
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
    @Test
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
    @Test
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

    @AfterTest
    public void closeIt() {
        driver.close();
    }
}
}
