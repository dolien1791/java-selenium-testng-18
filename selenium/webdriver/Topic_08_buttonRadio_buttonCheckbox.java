package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_buttonRadio_buttonCheckbox {
	WebDriver driver;
	
	//checkbox
	By firstCheckbox = By.xpath("//input[@value='Anemia']");
	By secondCheckbox = By.xpath("//input[@value='Asthma']");
	By thirdCheckbox = By.xpath("//input[@value='Arthritis']");
	By allCheckboxes = By.xpath("//input[@type='checkbox']");
	
	//radio
	By firstRadio = By.xpath("//input[@value='3-4 days']");
	By secondRadio = By.xpath("//input[@value='I have a strict diet']");

	@BeforeClass
	public void beforeClass() {
		//absolute path
		//System.setProperty("webdriver.chrome.driver", "D:\\Selenium web driver\\02-Selenium API\\BrowserDriver\\chromedriver.exe");
		
		//relative path (1)
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		
		//relative path (2)
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\BrowserDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
  
  public void TC_01_button(){
	  driver.get("https://www.fahasa.com/customer/account/create");
	  
	  driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
	  
	  WebElement loginButton = driver.findElement(By.cssSelector(".fhs-btn-login"));
	  
	  //verify login button is disable
	  boolean status = loginButton.isEnabled();
	  System.out.println("Login Status = " + status);
	  Assert.assertFalse(status);
	  
	  //input to email password
	  driver.findElement(By.cssSelector("#login_username")).sendKeys("domia@yopmail.com");
	  driver.findElement(By.cssSelector("#login_password")).sendKeys("123456");
	  sleepInSecond(2);
	  
	  //verify login button is enable
	  status = loginButton.isEnabled();
	  System.out.println("Login Status = " + status);
	  Assert.assertTrue(status);
	  
	  loginButton.click();
	  
	  String errorMessage = driver.findElement(By.cssSelector(".fhs-btn-login")).getText();
	  Assert.assertEquals(errorMessage, "Số điện thoại/Email hoặc Mật khẩu sai!");
	  sleepInSecond(5);
  }
  
  
  public void TC_02_default_radio_checkbox() throws InterruptedException {
	  driver.get("https://automationfc.github.io/multiple-fields/");
	  
	  //verify deselected
	  Assert.assertFalse(driver.findElement(firstCheckbox).isSelected());
	  Assert.assertFalse(driver.findElement(secondCheckbox).isSelected());
	  Assert.assertFalse(driver.findElement(thirdCheckbox).isSelected());
	  Assert.assertFalse(driver.findElement(firstRadio).isSelected());
	  Assert.assertFalse(driver.findElement(secondRadio).isSelected());
	  
	  //click 
	  driver.findElement(firstCheckbox).click();
	  driver.findElement(secondCheckbox).click();
	  driver.findElement(thirdCheckbox).click();
	  driver.findElement(firstRadio).click();
	  driver.findElement(secondRadio).click();
	  
	  sleepInSecond(5);
	  
	  //verify selected
	  Assert.assertTrue(driver.findElement(firstCheckbox).isSelected());
	  Assert.assertTrue(driver.findElement(secondCheckbox).isSelected());
	  Assert.assertTrue(driver.findElement(thirdCheckbox).isSelected());
	  Assert.assertTrue(driver.findElement(firstRadio).isSelected());
	  Assert.assertTrue(driver.findElement(secondRadio).isSelected());
	  
	  driver.navigate().refresh();
	  
	  //click to all checkbox
	  List<WebElement> checkboxes = driver.findElements(allCheckboxes);
	  
	  //selected
	  for (WebElement checkbox : checkboxes) {
		  checkbox.click();
		  Thread.sleep(500);
	  }
	  
	  //verify selected
	  for (WebElement checkbox : checkboxes) {
		  Assert.assertTrue(checkbox.isSelected());
	  }
	  
	  //de-select
	  for (WebElement checkbox : checkboxes) {
		  checkbox.click();
	  }
	  
	  //verify de-selected
	  for (WebElement checkbox : checkboxes) {
		  Assert.assertFalse(checkbox.isSelected());
	  }
  }
  
  @Test 
  public void TC_03_custom_radio_checkbox() {
	  driver.get("https://material.angular.io/components/checkbox/examples");
	  
	  //dung the div de click
	  driver.findElement(By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::div")).click();
	  sleepInSecond(2);
	  
	  //verify checked checkbox is selected
	  Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::div/input")).isSelected());
  }
  
  	public void sleepInSecond(long time) {
  		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  	
  
  @AfterClass 
	public void afterClass() {
		driver.quit();
	}
  

}
