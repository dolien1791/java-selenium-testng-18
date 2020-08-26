package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_Default_Dropdown {
	WebDriver driver;
	Select select;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
   
  public void TC_01_Open_browser() {
	  driver.get("https://www.facebook.com/");
	  
	  //khoi tao 1 bien select: di tim element co id = day
	  select = new Select(driver.findElement(By.id("month")));
	  
	  //dropdown khong ho tro chon nhieu (multiple)
	  boolean status = select.isMultiple();
	  System.out.println(status);
	  Assert.assertFalse(status);
	  
	  //khong nen dung
	  select.deselectByIndex(2);
	  sleepInSecond(3);
	  
	  //kiem tra da chon 1 item thanh cong
	  Assert.assertEquals(select.getFirstSelectedOption().getText(), "Feb");
	  
	  //value string
	  select.selectByValue("5");
	  sleepInSecond(3);
	  Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");
	  
	  //visible text
	  select.deselectByVisibleText("Oct");
	  sleepInSecond(3);
	  Assert.assertEquals(select.getFirstSelectedOption().getText(), "Oct");
	  
	  //get tat ca cac the option cua dropdown (item)
	  List<WebElement> monthOption= select.getOptions();
	  
	  //in ra xem co bao nhieu gia tri
	  int monthOptionSize = monthOption.size();
	  System.out.println("Month item number" + monthOptionSize);
	  
	  //kiem tra so luong item cua dropdown bang bao nhieu
	  Assert.assertEquals(monthOptionSize, 13);
	  
	  //in ra item value cua no la gi
	  //for-iterator
	  for (int i = 0; i < monthOption.size(); i++) {
		  monthOption.get(i);
	  }
	  
	  //for-each
	  for (WebElement option : monthOption) {
		  System.out.println("For each:" + option.getText());
	  }
  }
  
  @Test 
  public void TC_02_Multiple() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
	  
	  Assert.assertTrue(select.isMultiple());
	  
	  select.selectByVisibleText("Automation");
	  select.selectByVisibleText("Mobile");
	  select.selectByVisibleText("Perfomance");
	  
	  int optionSelected = select.getAllSelectedOptions().size();
	  System.out.println("Item selected =" + optionSelected);
	  Assert.assertEquals(optionSelected, 3);
	  
	  List <WebElement> optionSelectedElement = select.getAllSelectedOptions();
	  for(WebElement option : optionSelectedElement) {
		  System.out.println(option.getText());
	  }
	  
	  //bo chon ca 3
	  select.deselectAll();
	  
	  optionSelected = select.getAllSelectedOptions().size();
	  System.out.println("Item selected =" + optionSelected);
	  Assert.assertEquals(optionSelected, 0);
  }
  
  public void sleepInSecond(long time) {
	  try {
		Thread.sleep(time * 1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
  }
  
  
  @AfterClass 
	public void afterClass() {
		driver.quit();
	}
  

}
