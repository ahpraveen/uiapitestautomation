package uitestsuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.HomePage;
import pages.LoginPage;


/**
 * Demo UI Automated Test
 * Top 3 priority functionalities are covered - SignUp, Login, Place Order
 * Page object is implemented only for loginPage; the same model as login page other pages can be implemented
 * @author Praveen Anna Haridas
 *
 */

public class UIAutomatedTest {
	
	WebDriver driver;
	WebDriverWait wait;
	HomePage homePage;
	LoginPage loginPage;
	
    @Before
	public void setUp() {
    	
    	try {
			FileInputStream input = new FileInputStream("config.properties");
			Properties pro = new Properties();
			pro.load(input);
			System.setProperty("webdriver.chrome.driver", pro.getProperty("chromedriver"));
			driver = new ChromeDriver();
			wait = new WebDriverWait(driver,Duration.ofSeconds(50));
			driver.get(pro.getProperty("url"));		
		    driver.manage().window().maximize();
		    homePage = new HomePage(driver,wait);
		    loginPage = new LoginPage(driver,wait);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testSignUp() {	
		int randomNum;
		String userName;
		Assert.assertEquals(driver.getTitle(), "STORE");	
		wait.until(ExpectedConditions.elementToBeClickable(By.id("signin2")));
		driver.findElement(By.id("signin2")).click();
		String signInForm = driver.getWindowHandle();
		driver.switchTo().window(signInForm);	
		Random ranNumObj = new Random();
		randomNum = ranNumObj.nextInt(100000);
		userName = "user" + String.format("%05d", randomNum);
		WebElement usernameEle = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-username")));
		usernameEle.sendKeys(userName);
		driver.findElement(By.id("sign-password")).sendKeys("pass123");		
		driver.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[2]")).click();	
		wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(driver.switchTo().alert().getText(), "Sign up successful.");
		driver.switchTo().alert().accept();		
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void testSignIn() {
		loginToDemoBlaze();
		WebElement nameOfUser = wait.until(ExpectedConditions.elementToBeClickable(By.id("nameofuser")));
		Assert.assertEquals("Welcome demo2021",nameOfUser.getText());	
		logoutOfDemoBlaze();
	}
	
	@Test
	public void testPlaceOrder() {
		loginToDemoBlaze();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("nameofuser")));
		driver.findElement(By.linkText("Samsung galaxy s6")).click();
		WebElement addCart = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart")));
		addCart.click();
		wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(driver.switchTo().alert().getText(), "Product added.");
		driver.switchTo().alert().accept();	
		logoutOfDemoBlaze();		
	}

	public void logoutOfDemoBlaze() {
		driver.findElement(By.id("logout2")).click();
	}

	public void loginToDemoBlaze() {
		String win = driver.getWindowHandle();
		homePage.clickLogin();
		String loginWindow = driver.getWindowHandle();
		driver.switchTo().window(loginWindow);
		loginPage.enterUserName("demo2021");
		loginPage.enterPassword("pass123");
		loginPage.clickLogin();		
		driver.switchTo().window(win);
	}	
	
}
