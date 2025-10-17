package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        try {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get("https://www.demoblaze.com/");
            System.out.println("Browser launched and navigated successfully.");
        } catch (Exception e) {
            System.err.println("Error during WebDriver setup: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterTest
    public void close() {
        try {
        	
            if (driver != null) {
                driver.close();
                System.out.println("Browser closed successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error during WebDriver teardown: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
