package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * UI Elements to access from Login screen
 * @author Praveen Anna Haridas
 *
 */

public class LoginPage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id="loginusername")
	WebElement userNameElement;
	
	@FindBy(id="loginpassword")
	WebElement passwordElement;
	
	@FindBy(xpath="//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")
	WebElement clickLoginElement;
	
	
	public LoginPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}	

	public void enterUserName(String userName) {
		wait.until(ExpectedConditions.elementToBeClickable(userNameElement)).sendKeys(userName);
	}
	
	public void enterPassword(String password) {
		wait.until(ExpectedConditions.elementToBeClickable(passwordElement)).sendKeys(password);
	}
	
	public void clickLogin() {
		clickLoginElement.click();
	}
}
