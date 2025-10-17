package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage {
	WebDriver driver;
	WebDriverWait wait;
	

    public LogoutPage(WebDriver driver) {
		this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
	}
    
    
    public void navigateToLogoutPage() throws InterruptedException {
    	

    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login2")));
    	loginButton.click();

    }
    
    public void Login(String username, String password) {
    	
  	  WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(
  			  (By.id("loginusername"))));
        usernameField.clear();
        usernameField.sendKeys(username);

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(
      		  By.id("loginpassword")));
        passwordField.clear();
        passwordField.sendKeys(password);
        
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
      		  By.xpath("//button[@class='btn btn-primary' and contains(text(),'Log in')]")));
        loginButton.click();
  

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();  // Accept the alert (e.g., "Wrong password" popup)
        } catch (Exception e) {
       
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
    }

    public void logout() {
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout2")));
        logoutButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login2")));
    }

    public boolean isLoggedOut() {
        return driver.findElement(By.id("login2")).isDisplayed();
    }

    
}
