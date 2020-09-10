package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Topic_15_Wait_part1_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 10);
		
		driver.manage().window().maximize();
	}
	
	
  @Test 
  public void TC_01_Visible() {
	  driver.get("https://www.facebook.com/");
	  
	  //Wait for email visible
	  explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@name='reg_email__']")));
	  
	  driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("mia@yopmail.com");
  }
  
  @Test 
  public void TC_02_Invisible() {
	  driver.navigate().refresh();
	  
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	  
	  driver.get("http://live.demoguru99.com/index.php/");
	  
	  //2.1 Element co trong DOM + khong hien thi tren UI
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
	  
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='footer']//a[text()='My Account']")));
	  
	  driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	  
	  //2.2 element khong co trong DOM + khong hien thi tren UI
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='email']")));
	  
	  
  }
  
  @Test 
  public void TC_03_Presence() {
	  driver.get("https://www.facebook.com/");
	  
	  //3.1 element co trong DOM + hien thi tren giao dien
	  explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email__']")));
	  
	  driver.get("http://live.demoguru99.com/index.php/");
	  
	  //3.2 Element co trong DOM nhung khong hien thi tren UI
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
  }
  
  @Test 
  public void TC_04_staleness() {
	  driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	  
	  driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();
	  
	  explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='create_account_error']//li")));
	  WebElement emailErrorMessage = driver.findElement(By.xpath("//div[@id='create_account_error']//li"));
	  
	  driver.navigate().refresh();
	  
	//Element khong co trong DOM
	  explicitWait.until(ExpectedConditions.stalenessOf(emailErrorMessage));
  }
  
  @AfterClass 
	public void afterClass() {
		driver.quit();
	}
  

}
