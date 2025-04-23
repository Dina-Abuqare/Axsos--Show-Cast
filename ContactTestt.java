import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ContactTestt {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
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

