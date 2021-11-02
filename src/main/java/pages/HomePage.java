package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Elements to access from HomePage
 * @author Praveen Haridas
 * 
 */
public class HomePage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id = "login2")
	WebElement loginElement;
	
	public HomePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}	

	public void clickLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(loginElement)).click();
	}
	
	
	
	public void clickSignUp() {
		
	}
	
	public void clickLogout() {
		
	}
	
	public void acceptAlert() {
		
	}
	
	public void clickProduct() {
		
	}	
	

}
