package Goquo_NTA;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_01_Full_Function {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
  @Test 
  public void TC_01_Web_browser() throws InterruptedException {
	  driver.get("https://nta.pre-production.goquo.io");
	  driver.findElement(By.id("Username")).sendKeys("ntatest");
	  Thread.sleep(2000);
	  driver.findElement(By.id("Password")).sendKeys("Admin@123");
	  Thread.sleep(2000);
	  driver.findElement(By.name("Save")).click();
	  Assert.assertEquals(driver.getCurrentUrl(), "https://nta.pre-production.goquo.io/agent-portal");
  }
  
  @Test 
  public void TC_02_Web_Element() {
	  
  }
  
  
  @AfterClass 
	public void afterClass() {
		driver.quit();
	}
  

}
