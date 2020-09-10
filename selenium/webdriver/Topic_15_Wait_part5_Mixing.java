package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_15_Wait_part5_Mixing {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		//explicitWait = new WebDriverWait(driver, 30);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
 
  public void TC_01_Element_Found() {
	  driver.get("https://www.facebook.com/");
	  
	  //explicit wait
	  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("mia@yopmail.com");;
	  
	  //implicit wait
	  driver.findElement(By.name("login")).click();
  }
  
  @Test 
  public void TC_02_Element_not_found_only_implicit() {
	  driver.get("https://www.facebook.com/");
	  
	  //lay timeout cua implicit
	  driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	  //System.out.println("TC_02_Element_not_found_only_implicit: " + getDateTimeSecondNow() + "------");
	  try {
		  WebElement emailTextbox = driver.findElement(By.xpath("//input[@name='tao_khong_co_o_day']"));
		  Assert.assertTrue(emailTextbox.isDisplayed());
		  System.out.println("Switch to try");
	  } catch(Exception ex) {
		  System.out.println("TC_02_Element_not_found_only_implicit: " + ex.getMessage());
		  System.out.println("---------Exception cua implicit---------");
	  }
	  //System.out.println("TC_02_Element_not_found_only_implicit: " + getDateTimeSecondNow() + "------");
  }
  
  
  @AfterClass 
	public void afterClass() {
		driver.quit();
	}
  

}
