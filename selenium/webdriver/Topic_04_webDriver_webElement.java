package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_webDriver_webElement {
	//khai bao 1 bien dai dien cho WebDriver = instance
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		//khoi tao browser len
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
  @Test 
  public void TC_01_Web_browser() {
	  driver.get("https://www.facebook.com/");
	  
	  //lay Url cua tab hien tai (tab dang dung)
	  String loginPageUrl = driver.getCurrentUrl();
	  
	  //kiem tra dieu kien dung
	  Assert.assertTrue(loginPageUrl.equals("https://www.facebook.com/"));
	  
	  //kiem tra dieu kien sai
	  Assert.assertFalse(loginPageUrl.equals("https://www.facebook.com/login"));
	  
	  //kiem tra dieu kien bang nhau
	  Assert.assertEquals(loginPageUrl, "https://www.facebook.com/");
	  
	  System.out.println(loginPageUrl);
	  
	  //HTML / CSS/ JS/ Jquery
	  driver.getPageSource();
	  
	  //Window / tab
	  String pageWindowID = driver.getWindowHandle();
	  
	  //cho element duoc tim thay sau khoang thoi gian duoc set (10s)
	  //Webdrive wait
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  //cho page duoc loading xong
	  driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	  
	  //cho scrip duoc loading xong (JS)
	  //Javascript executor (nhung doan code JS vao browser/element)
	  driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
	  
	  driver.manage().window().fullscreen();
	  
	  driver.manage().window().maximize();
	  
	  //test GUI: Graphic user interface
	  //font/size/ color/ location/ position/ responsive(resolution)
	  //set/get position
	  //set/get size
	  
	  //back ve trang truoc
	  driver.navigate().back();
	  
	  //forward ve trang truoc
	  driver.navigate().forward();
	  
	  //tai lai trang
	  driver.navigate().refresh();
	  
	  //mo ra 1 URL moi (Tracking history)
	  driver.navigate().to("https://www.facebook.com/");
	  
	  //3 loai:
	  //window/tab
	  //alert
	  //frame/ iframe
	  
	  driver.switchTo().alert();
	  
	  driver.switchTo().window("");
	  
	  driver.switchTo().frame("");
	 
	  
  }
  
  @Test 
  public void TC_02_Web_element() {
	  
  }
  
  
  @AfterClass 
	public void afterClass() {
		driver.quit();
	}
  

}
