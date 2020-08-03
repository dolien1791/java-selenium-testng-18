package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertArrayEquals;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_Xpath_technical {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
  @Test 
  public void TC_01_LoginWithEmptyEmailAndPassword() {
	  driver.get("http://live.demoguru99.com/index.php/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("");
	  driver.findElement(By.id("pass")).sendKeys("");
	  driver.findElement(By.name("send")).click();
	  
	  Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
  }
  
  @Test 
  public void TC_02_LoginWithInvalidEmail() {
	  driver.get("http://live.demoguru99.com/index.php/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("123@123.46987");
	  driver.findElement(By.id("pass")).sendKeys("123456");
	  driver.findElement(By.name("send")).click();
	  
	  Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
  }
  
  @Test 
  public void TC_03_LoginWithInvalidPassword() {
	  driver.get("http://live.demoguru99.com/index.php/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("mia@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123");
	  driver.findElement(By.name("send")).click();
	  
	  Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
  }
  
  @Test 
  public void TC_04_LoginWithIncorrectPassword() {
	  driver.get("http://live.demoguru99.com/index.php/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("mia@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123456");
	  driver.findElement(By.name("send")).click();
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
  }
  
  
  @AfterClass 
	public void afterClass() {
		driver.quit();
	}
  

}
