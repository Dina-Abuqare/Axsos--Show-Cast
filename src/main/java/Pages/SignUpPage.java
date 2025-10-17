package Pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
	WebDriver driver;
	WebDriverWait wait;
	
	
    public SignUpPage(WebDriver driver) {
		this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 
	}
    
    public void navigateToSignUpPage() throws InterruptedException {

    	WebElement signup = wait.until(ExpectedConditions.elementToBeClickable(By.id("signin2")));
    	signup.click();
    }
    
    public void RefreshPage() {
    	driver.navigate().refresh();
    }
    
    public void SignUp(String username, String password) {
    	

    	 WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(
   			  (By.id("sign-username"))));
    	 
    	 usernameField.clear();
         usernameField.sendKeys(username);

         WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(
        		 By.id("sign-password")));
         passwordField.clear();
         passwordField.sendKeys(password);
         
         WebElement SignUpButton = wait.until(ExpectedConditions.elementToBeClickable(
        		 By.xpath("//button[@class='btn btn-primary' and contains(text(),'Sign up')]")));
         SignUpButton.click();
         
    	
    }
    public boolean isAlertWithMessage(String expectedMessage) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
      
            wait.until(ExpectedConditions.alertIsPresent());

            // Switch to the alert
            Alert alert = driver.switchTo().alert();
         
            // Get the text of the alert
            String actualText = alert.getText();
            System.out.println("Alert Text: " + actualText);
            
            // Compare the actual text with expected
            boolean matches = actualText.equals(expectedMessage);
            alert.accept();
            
            return matches;
        } catch (Exception e) {
            return false;
        }
    }



}
