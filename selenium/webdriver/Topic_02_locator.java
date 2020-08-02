package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_02_locator {
	//khai bao bien driver (instance = the hien or dai dien)
	WebDriver driver;

	@BeforeClass //pre-cond
	public void beforeClass() {
		//khoi tao trinh duyen firefox len
		driver = new FirefoxDriver();
		
		//cho element duoc stable truoc khi thao tac trong vong xx seconds
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//phong to browser len
		driver.manage().window().maximize();
		
		//mo app URL
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	
	//html cua login button
	//<button id="send2" class="button" name="send" title="Login" type="submit">
	//trong selenium co 3 loai locator la 3 dng attribute cua html (id/class/name)
	//hay duoc dev team set la duy nhat (unique)
	
  @Test //test case (action/verify)
  public void TC_01_ID() throws InterruptedException {
	  driver.findElement(By.id("email")).sendKeys("id@gmail.com");
	  Thread.sleep(2000);
	  
	  //xoa du lieu trong cac element co the nhap duoc: textbox, textarea, dropdown, etc
	  driver.findElement(By.id("email")).clear();
  }
  
  @Test
  public void TC_02_Class() throws InterruptedException {
	  driver.findElement(By.className("validate-password")).sendKeys("123456");
	  Thread.sleep(2000);
	  driver.findElement(By.className("validate-password")).clear();
  }
  
  @Test
  public void TC_03_Name() throws InterruptedException {
	  driver.findElement(By.name("login[username]")).sendKeys("name@gmail.com");
	  Thread.sleep(2000);
	  driver.findElement(By.name("login[username]")).clear();
  }
  
  @Test
  public void TC_04_Tagname() throws InterruptedException {
	  int linkNumber = driver.findElements(By.tagName("a")).size();
	  System.out.println("Sum link = " + linkNumber);
	  Thread.sleep(2000);
  }
  
  @Test //no chi work voi nhung duong link text co dinh
  public void TC_05_Link_Text() throws InterruptedException {
	  //click vao SITE MAP link
	  driver.findElement(By.linkText("SITE MAP")).click();
	  Thread.sleep(2000);
  }
  
  @Test //no chi work voi nhung duong link text tuong doi
  public void TC_06_Partial_link_Text() throws InterruptedException {
	  driver.findElement(By.partialLinkText("ADVANCED")).click();
	  Thread.sleep(2000);
  }
  
  @Test
  public void TC_07_Css() throws InterruptedException {
	  //ID
	  driver.findElement(By.cssSelector("#name")).sendKeys("LCD");
	  Thread.sleep(2000);
	  
	  //class
	  driver.findElement(By.cssSelector(".advanced-search")).isDisplayed();
	  Thread.sleep(2000);
	  
	  //Name
	  driver.findElement(By.cssSelector("input[name='short_description']")).sendKeys("Samsung LCD");
	  Thread.sleep(2000);
	  
	  //Link text
	  driver.findElement(By.cssSelector("a[href='http://live.demoguru99.com/index.php/mobile.html']")).click();
	  
	  //Partial link
	  driver.findElement(By.cssSelector("a[href*='/catalogsearch/advanced/']")).click();
	  
	  //tag name
	  int linkSize = driver.findElements(By.cssSelector("a")).size();
	  System.out.println("Css Tagname = " + linkSize);
  }
  
  @Test
  public void TC_08_Xpath() throws InterruptedException {
	  driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	  Thread.sleep(2000);
	  
	  //ID
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("xpath_id@gmail.com");
	  driver.findElement(By.xpath("//input[@id='email']")).clear();
	  Thread.sleep(2000);
	  
	  //Class
	  driver.findElement(By.xpath("//input[@class='input-text required-entry validate-password']")).sendKeys("xpath_class@gmail.com");
	  Thread.sleep(2000);
	  
	  //name
	  driver.findElement(By.xpath("//input[@name='email']")).sendKeys("xpath_name@gmail.com");
	  Thread.sleep(2000);
	  
	  //link text
	  driver.findElement(By.xpath("//a[text()='About Us']")).click();
	  Thread.sleep(2000);
	  
	  //partial link text
	  driver.findElement(By.xpath("//a[contains(text(),'Customer')]")).click();
	  Thread.sleep(2000);
	  
	  //Tagname
	  System.out.println(driver.findElements(By.xpath("//a")).size());
	  Thread.sleep(2000);
	  
	  //css
	  driver.findElement(By.xpath("//input[@title='Sign up for our newsletter']")).sendKeys("xpath_css@gmail.com");
	  Thread.sleep(2000);
	  
  }
  
  @AfterClass //post-condition
	public void afterClass() {
		driver.quit();
	}
  

}
