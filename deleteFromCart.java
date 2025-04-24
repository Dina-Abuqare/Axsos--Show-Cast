import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;
public class deleteFromCart {           //Tc17
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }
    @Test
    public void testDeleteProductFromCart() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[text()='Delete']")).click();
        Thread.sleep(2000);
    }
    @AfterTest
    public void closeIt() {
        driver.close();
    }
}
