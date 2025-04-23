import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
public class InvalidContactEmailTest {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
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

    @AfterTest
    public void closeIt() {
        driver.close();
        }
    }

