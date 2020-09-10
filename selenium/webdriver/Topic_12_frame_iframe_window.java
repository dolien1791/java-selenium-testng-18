package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_12_frame_iframe_window {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
 
  public void TC_01_Iframe() {
	  driver.get("https://kyna.vn/");
	  
	  //switch to fb iframe
	  driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")));
	  
	  String facebookLike = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
	  Assert.assertEquals(facebookLike, "169K sukaan");
	  
	  //switch to default content
	  driver.switchTo().defaultContent();
	  
	  //check search textbox displayed
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")).isDisplayed());
	  
	  //switch to webchat iframe
	  driver.switchTo().frame("cs_chat_iframe");
	  Assert.assertTrue(driver.findElement(By.cssSelector(".chat-area")).isDisplayed());
	  
	  //input text to text area
	  driver.findElement(By.tagName("textarea")).sendKeys("selenium");
	  driver.findElement(By.tagName("textarea")).sendKeys(Keys.ENTER);
	  
	  Assert.assertTrue(driver.findElement(By.id("sign-in-menu")).isDisplayed());
	  sleepInSecond(5);
	  
	  //switch to default content
	  driver.switchTo().defaultContent();
	  driver.findElement(By.id("live-search-bar")).sendKeys("Java");
	  
	  driver.findElement(By.cssSelector(".search-button")).click();
	  
	  Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "'Java'");
  }
  
  @Test 
  public void TC_02_Window() {
	  driver.get("https://kyna.vn/");
	  
	  //click to "Vietnamwork" link at pre-footer
	  clickToElementByJS("//img[@alt='VietnamWorks']");
	  driver.findElement(By.xpath("//img[@alt='VietnamWorks']"));
	  
	  Assert.assertEquals(driver.getCurrentUrl(), "https://www.vietnamworks.com/?utm_source=from_kyna");
  }
  
  public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
  
  public void clickToElementByJS(String locator) {
	  jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath(locator)));
  }
  
  public void switchToWindowByID(String parentID) {
	  //lay ra tat ca ID cua window/ tab dang co
	  Set<String> allWindows = driver.getWindowHandles();
	  
	  //dung vong lap for each de duyet qua tung ID
	  for (String runWindow : allWindows) {
		  
		  //kiem tra xem ID nao khac voi parent
		  if(!runWindow.equals(parentID)) {
			  
			  //switch vao ID (cua so/tab) do
			  driver.switchTo().window(runWindow);
			  
			  //thoa khoi vong lap
			  break;
		  }
	  }
  }
  
  @AfterClass 
	public void afterClass() {
		driver.quit();
	}
  

}
