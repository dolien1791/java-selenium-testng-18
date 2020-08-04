package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertArrayEquals;
import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_baitap6 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
  @Test 
  public void TC_01_Register() {
	  driver.get("http://live.demoguru99.com/index.php/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  
	  driver.findElement(By.id("firstname")).sendKeys("Mia");
	  driver.findElement(By.id("lastname")).sendKeys("Do");
	  String email = "miado" + randomNumber() +"@gmail.com";
	  driver.findElement(By.id("email_address")).sendKeys(email);
	  driver.findElement(By.id("password")).sendKeys("123456");
	  driver.findElement(By.id("confirmation")).sendKeys("123456");
	  
	  driver.findElement(By.xpath("//button[@title='Register']")).click();
	  
	  String successMessage = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
	  Assert.assertEquals(successMessage, "Thank you for registering with Main Website Store.");
	  
	  //Name
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box']//p[contains(text(),'Mia Do')]")).isDisplayed());
	  
	  //Email
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box']//p[contains(.,'"+ email +"')]")).isDisplayed());
	  
	  //click account
	  driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
	  
	  //click logout
	  driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	  
	  //cho element nao do o tren home page hien thi
	  Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
  }
  
  
  @AfterClass 
	public void afterClass() {
		driver.quit();
	}
  
  public int randomNumber() {
	  Random rand = new Random();
	  return rand.nextInt(9999);
  }
  

}
