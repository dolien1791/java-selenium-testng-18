package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_05_Bai_Tap_Browser {
	private static final boolean WebElement = false;
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
   
  public void TC_01_Verify_URL() {
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
	  driver.findElement(By.xpath("//span[contains(text(), 'Create an Account')]")).click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
  }
  
  
    public void TC_02_Verify_Title() {
  	  driver.get("http://live.demoguru99.com/");
  	driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
  	  Assert.assertEquals(driver.getTitle(), "Customer Login");
  	  driver.findElement(By.xpath("//span[contains(text(), 'Create an Account')]")).click();
  	  Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }
    
    
    public void TC_03_Navigate_Function() {
  	  driver.get("http://live.demoguru99.com/");
  	driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
  	  driver.findElement(By.xpath("//span[contains(text(), 'Create an Account')]")).click();
  	  Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
  	  
  	  driver.navigate().back();
  	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
  	  
  	  driver.navigate().forward();
  	  Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }
    
    
     
    public void TC_04_Page_Source() {
  	  driver.get("http://live.demoguru99.com/");
  	driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
  	  String loginPageSource = driver.getPageSource();
  	  Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));
  	  
  	  driver.findElement(By.xpath("//span[contains(text(), 'Create an Account')]")).click();
  	  String registerPageSource = driver.getPageSource();
  	  Assert.assertTrue(registerPageSource.contains("Create an Account"));
  	  
    }
 
   
    public void TC_05_Web_Element() {
    	driver.get("https://www.facebook.com/");
    	//thao tac 1 element
    	//click vao 1 button/ link/ radio button/ checkbox
    	//send key vao 1 textbox/ textarea
    	
    	WebElement lastnameTextbox = driver.findElement(By.xpath("//input[@name='lastname']"));
    	lastnameTextbox.clear();
    	lastnameTextbox.sendKeys("Automation test");
    	
    	driver.findElement(By.xpath("//input[@name='lastname']")).clear();
    	driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Automation testing");
    	
    	//thao tac voi nhieu element
    	//lay ra tat ca cac duong link dang co o trang hien tai
    	//dem duoc co bao nhieu element thoa man dieu kien
    	//thao tac voi nhieu element o tren table/grid
    	
    	List<WebElement> sexRadioButton = driver.findElements(By.name("sex"));
    	System.out.println("Radio button size =" + sexRadioButton.size());
    	
    }
    
   
    public void TC_06_Web_Element_display() throws InterruptedException {
    	driver.get("https://automationfc.github.io/basic-form/index.html");
    	
    	//email textbox
    	if(driver.findElement(By.id("mail")).isDisplayed()) {
    		driver.findElement(By.id("mail")).sendKeys("Automation Testing");
    		Thread.sleep(2000);
    	}
    	
    	//Education textarea
    	if(isElementDisplayed("//textarea[@id='edu']")) {
    		driver.findElement(By.id("edu")).sendKeys("Automation Testing");
    		Thread.sleep(2000);
    	}
    	
    	//age under 18 - Radio button
    	if(driver.findElement(By.id("under_18")).isDisplayed()) {
    		driver.findElement(By.id("under_18")).click();
    		Thread.sleep(2000);
    	}
    }
    
  
    public void TC_07_Web_Element_enable() throws InterruptedException {
    	driver.get("https://automationfc.github.io/basic-form/index.html");
    	
    	//email textbox
    	if(driver.findElement(By.id("mail")).isEnabled()) {
    		System.out.println("Email is enable!");
    	} else {
    		System.out.println("Email is disable!");
    	}
    	
    	//Education textarea
    	if(driver.findElement(By.id("edu")).isDisplayed()) {
    		System.out.println("Education is enable!");
    	}else {
    		System.out.println("Education is disable!");
    	}
    	
    	//slider 02
    	if(driver.findElement(By.id("slider-2")).isDisplayed()) {
    		System.out.println("Slider is enable!");
    	}else {
    		System.out.println("Slider is disable!");
    	}
    }
    
    @Test 
    public void TC_08_Web_Element_selected() throws InterruptedException {
    	driver.get("https://automationfc.github.io/basic-form/index.html");
    	
    	driver.findElement(By.id("over_18")).click();
    	
    	driver.findElement(By.id("development")).click();
    	
    	Assert.assertTrue(driver.findElement(By.id("over_18")).isSelected());
    	Assert.assertTrue(driver.findElement(By.id("development")).isSelected());
    	
    	//click lan 2 = bo chon
    	driver.findElement(By.id("over_18")).click();
    	
    	driver.findElement(By.id("development")).click();
    	
    	Assert.assertTrue(driver.findElement(By.id("over_18")).isSelected());
    	Assert.assertFalse(driver.findElement(By.id("development")).isSelected());	
    }
    
    public boolean isElementDisplayed(String xPathValue) {
    	WebElement element = driver.findElement(By.xpath(xPathValue));
    	if(element.isDisplayed()) {
    		System.out.println("Element with xpath" + xPathValue + "is displayed");
    		return true;
    	} else {
    		System.out.println("Element with xpath" + xPathValue + "is not displayed");
    		return false;
    	}
    }
    
  
  @AfterClass 
	public void afterClass() {
		driver.quit();
	}
  

}
