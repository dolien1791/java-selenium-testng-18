package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_09_Alert {
	WebDriver driver;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
  
  public void TC_01_Alert() {
	  driver.get("http://demo.guru99.com/v4/index.php");
	  
	  driver.findElement(By.name("btnLogin")).click();
	  sleepInSecond(2);
	  
	  //switch vao alert
	  alert = driver.switchTo().alert();
	  sleepInSecond(2);
	  
	  //get text cua alert
	  System.out.println("Alert text = " + alert.getText());
	  
	  //send text vao alert
	  //alert.sendKeys("");
	  
	  //Accept alert
	  alert.accept();
	  sleepInSecond(2);
	  
	  //alert cancel
	  //alert.dismiss();
  }
  
 
  public void TC_02_JS_Alert() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  
	  /*-----------alert accept--------------*/
	  driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	  
	  alert = driver.switchTo().alert();
	  //verify alert text
	  Assert.assertEquals(alert.getText(), "I am a JS Alert");
	  
	  alert.accept();
	  sleepInSecond(2);
	  //verify accept alert success
	  Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
	  
	  /*-----------alert confirm--------------*/
	  driver.navigate().refresh();
	  
	  driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	  
	  alert = driver.switchTo().alert();
	  
	  Assert.assertEquals(alert.getText(), "I am a JS Confirm");
	  
	  //cancel alert
	  alert.dismiss();
	  sleepInSecond(2);
	  
	  Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
	  
	  /*-----------alert prompt--------------*/
	  driver.navigate().refresh();
	  
	  driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	  
	  alert = driver.switchTo().alert();
	  
	  Assert.assertEquals(alert.getText(), "I am a JS prompt");
	  
	  //sendkey to alert
	  alert.sendKeys("test");
	  sleepInSecond(2);
	  
	  //accept alert
	  alert.accept();
	  
	  Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: test");
  }
  
 
  public void TC_03_Authentication_Alert() {
	  String username = "admin";
	  String password = "admin";
	  driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']")).isDisplayed());
  }
  
  @Test 
  public void TC_04_Authentication_Alert() {
	  String username = "admin";
	  String password = "admin";
	  driver.get("http://the-internet.herokuapp.com/");
	  
	  //get link herf
	  String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
	  
	  handleAuthentificationAlert(basicAuthenLink, username, password);
	  
	  //by pass Authentication alert
	  Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']")).isDisplayed());
  }
  
  public void handleAuthentificationAlert(String link, String username, String password) {
	  //link = http://the-internet.herokuapp.com/basic_auth
	  String splitLink[] = link.split("//");
	  link = splitLink[0] + "//" + username + ":" + password + "@" + splitLink[1];
	  driver.get(link);
  }
  
  public void sleepInSecond(long time) {
	  try {
		Thread.sleep(time*1000);
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
