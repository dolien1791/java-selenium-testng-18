package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_00_template {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
  @Test 
  public void TC_01_Web_browser() {
	  driver.get("");
  }
  
  @Test 
  public void TC_02_Web_Element() {
	  
  }
  
  
  @AfterClass 
	public void afterClass() {
		driver.quit();
	}
  

}
