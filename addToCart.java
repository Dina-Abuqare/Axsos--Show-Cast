import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;
public class addToCart {     //Tc  16,30,32
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }

    @Test(priority = 1)
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
    @Test(priority = 2)//Tc30
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

    @Test(priority = 3)//Tc31
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
    @AfterTest
    public void closeIt() {
        driver.close();
    }
}
