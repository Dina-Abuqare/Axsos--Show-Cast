package Pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	
    public LoginPage(WebDriver driver) {
    	
		this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
	}

    public void navigateToLoginPage() throws InterruptedException {
    	
   
    	WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.id("login2")));
    	login.click();
    }
    
    
    public void RefreshPage() {
    	driver.navigate().refresh();
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
        		  By.xpath("//button[@class='btn btn-primary'and contains(text(),'Log in')]")));
          loginButton.click();
    
    }
    
    public boolean isLoginSuccessful(String username) {
    	
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement welcomeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));

            String actualText = welcomeElement.getText();
            String expectedText = "Welcome " + username;

            return actualText.equals(expectedText);
        } catch (Exception e) {
            return false;
        }
    }

  
    public boolean isAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    
    public boolean isLoginRejectedWithMessage(String expectedMessage) {
        if (isAlertPresent()) {
            Alert alert = driver.switchTo().alert();
            String actualMessage = alert.getText();
            System.out.println("Alert: " + actualMessage);
            boolean result = actualMessage.equals(expectedMessage);
            alert.accept();
            return result;
        }
        return false;
    }
}
