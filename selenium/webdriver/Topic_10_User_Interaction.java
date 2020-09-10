package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_10_User_Interaction {
	WebDriver driver;
	WebElement element;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
 
  public void TC_01_Web_browser() {
	  driver.get("https://www.myntra.com/");
	  
	  element = driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"));
	  //hover to KIDS menu
	  action.moveToElement(element).perform();
	  sleepInSecond(5);
	  
	  driver.findElement(By.xpath("//ul[@class='desktop-navBlock']//a[text()='Home & Bath']")).click();
	  
	  Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());
  }
  
 
  public void TC_02_click_and_Hold() {
	  driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
	  
	  String[] selectedTextExpected = {"1", "2", "3", "4", "5", "6", "7", "8"};
	  
	  List <WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	  
	  //click chon tu 1 den 8
	  action.clickAndHold(allItems.get(0)).moveToElement(allItems.get(7)).release().perform();
	  //click vao so 1 (giu chuot): clickAndHold
	  //move chuot den so 8: moveToElement
	  //nha chuot trai ra: release
	  sleepInSecond(5);
	  
	  //verify chon tu 1-8 thanh cong
	  List <WebElement> allItemsSelected = driver.findElements(By.cssSelector(".ui-selected"));
	  
	  //verify size = 8
	  Assert.assertEquals(allItemsSelected.size(), 8);
	  
	  //tao ra mot arrayList de lu lai selected text
	  ArrayList<String> allItemSelectedText = new ArrayList<String>();
	  
	  
	  //verify text cua element la tu so 1 den so 8 khong
	  for (WebElement webElement : allItemsSelected) {
		  allItemSelectedText.add(webElement.getText());
	  }
	  
	  Object[] selectedTextActual = (Object[]) allItemSelectedText.toArray();
	  
	  Assert.assertEquals(selectedTextExpected, selectedTextActual);
  }
  

  public void TC_03_click_and_Hold_random() {
	  driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
	  
	  List <WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	  
	  //nhan phim ctrl xuong
	  action.keyDown(Keys.CONTROL).perform();
	  
	  //click vao cac so can chon 1 4 7 12
	  action.click(allItems.get(0)).click(allItems.get(3)).click(allItems.get(6)).click(allItems.get(11)).perform();
	  
	  //nha phim ctrl ra
	  action.keyUp(Keys.CONTROL).perform();
	  
	  //verify 4 so chon thanh con 1 4 7 12
	  List <WebElement> allItemsSelected = driver.findElements(By.cssSelector(".ui-selected"));
	  Assert.assertEquals(allItemsSelected.size(), 4);
  }
  

  public void TC_04_double_click() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  element = driver.findElement(By.xpath("//button[text()='Double click me']"));
	  action.doubleClick(element).perform();
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());
  }
  
  
  public void TC_05_right_click() {
	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	  
	  //right click to button
	  driver.findElement(By.xpath("//span[text()='right click me']"));
	  action.contextClick(element).perform();
	  
	  //hover to Quit
	  element = driver.findElement(By.cssSelector(".context-menu-icon-quit"));
	  action.moveToElement(element).perform();
	  
	  //verify 'Quit' has hover + visible status
	  String quitClassAttribute = element.getAttribute("class");
	  System.out.println(quitClassAttribute);
	  
	  Assert.assertTrue(quitClassAttribute.contains("context-menu-hover"));
	  Assert.assertTrue(quitClassAttribute.contains("context-menu-visible"));
	  
	  //is display
	  Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
  }
	  
  
  @Test 
  public void TC_06_Drag_drop_html4() {
	  driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
	  
	  WebElement sourceCircle = driver.findElement(By.cssSelector("#draggable"));
	  WebElement targetCircle = driver.findElement(By.cssSelector("#droptarget"));
	  
	  action.dragAndDrop(sourceCircle, targetCircle).perform();
	  sleepInSecond(2);
	  
	  Assert.assertEquals(targetCircle.getText(), "You did great!");
  }
  
  
  public void TC_07_Drag_drop_html5() {
	 driver.get("https://automationfc.github.io/drag-drop-html5/");
	 
	 WebElement source = driver.findElement(By.cssSelector("#draggable"));
	 WebElement target = driver.findElement(By.cssSelector("#droptarget"));
	  
	 action.dragAndDrop(source, target).perform();
	 sleepInSecond(2);
	  
	 Assert.assertEquals(target.getText(), "You did great!");
	 
  }
  
  @AfterClass 
	public void afterClass() {
		driver.quit();
	}
  	
  	public void sleepInSecond(long time) {
  		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}

}
