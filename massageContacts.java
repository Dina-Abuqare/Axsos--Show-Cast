import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;

public class massageContacts {         //Tc 11-15
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }

    @Test(priority = 1)//TC11 by Bashir
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

    @Test(priority = 2)//Tc12 by Bashir
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

    @Test(priority = 3)//Tc13
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

    @Test(priority = 4)//Tc14
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

    @Test(priority = 5)//Tc15
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
    @AfterTest
    public void closeIt() {
        driver.close();
    }

}
