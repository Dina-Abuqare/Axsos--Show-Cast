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

public class logout {   //Tc26
    WebDriver driver;
    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }
    @Test(priority = 10)
    public void testLogout() throws InterruptedException {
        driver.findElement(By.id("login2")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("loginusername")).sendKeys("hi12345");
        driver.findElement(By.id("loginpassword")).sendKeys("12345");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        // Navigate to the logout button
        WebElement logoutButton = driver.findElement(By.id("logout2"));

        logoutButton.click();
        Thread.sleep(2000);

        WebElement loginButton = driver.findElement(By.id("login2"));
        Assert.assertTrue(loginButton.isDisplayed(), "User is not logged out successfully!");
    }

       @AfterTest
      public void closeIt () {
        driver.close();
}
}
