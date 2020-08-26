package Exercise;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TC_03_Texbox_TextArea_html_dropdown {
	WebDriver driver;
	Select select;
	String email;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		email = "domia" + randomNumber() + "@yopmail.com";
	} 
	
	
  @Test 
  public void TC_01_Register() {
	  driver.get("https://demo.nopcommerce.com/");
	  driver.findElement(By.xpath("//a[text()='Register']")).click();
	  
	  //Your Personal Details
	  driver.findElement(By.id("gender-female")).click();
	  driver.findElement(By.id("FirstName")).sendKeys("Mia");
	  driver.findElement(By.id("LastName")).sendKeys("Do");
	  
	  select = new Select(driver.findElement(By.name("DateOfBirthDay")));
	  select.selectByValue("1");
	  List<WebElement> dayOption= select.getOptions();
	  
	  //for-each
	  for (WebElement option : dayOption) {
		  System.out.println("For each:" + option.getText());
	  }
	  
	  select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
	  select.selectByVisibleText("July");
	  List<WebElement> monthOption= select.getOptions();
	  
	  //for-each
	  for (WebElement option : monthOption) {
		  System.out.println("For each:" + option.getText());
	  }
	  
	  select = new Select(driver.findElement(By.name("DateOfBirthYear")));
	  select.selectByValue("1991");
	  List<WebElement> yearOption= select.getOptions();
	  
	  //for-each
	  for (WebElement option : yearOption) {
		  System.out.println("For each:" + option.getText());
	  }
	  
	  driver.findElement(By.id("Email")).sendKeys(email);
	  
	  //Company Details
	  driver.findElement(By.id("Company")).sendKeys("Goquo");
	  
	  //Your Password
	  driver.findElement(By.id("Password")).sendKeys("123456");
	  driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
	  
	  //Click on register button
	  driver.findElement(By.id("register-button")).click();
	  
	  //verify data
	  Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
	  Assert.assertEquals(driver.findElement(By.className("ico-account")).getText(), "My account");
	  Assert.assertEquals(driver.findElement(By.className("ico-logout")).getText(), "Log out");
	  
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
