package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutUsPage {
	WebDriver driver;
	WebDriverWait wait;
	

    public AboutUsPage(WebDriver driver) {
    	
		this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
	}
    
    public void clickAboutUs() {
    	
        WebElement aboutUsLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@class='nav-link' and contains(text(),'About us')]")));
        aboutUsLink.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".vjs-modal-dialog-content")));
     
    }

    public String getAboutUsModalText() {
    	
        WebElement textElement = driver.findElement(By.cssSelector(".vjs-modal-dialog-content"));
        return textElement.getText();
    }
}
    

