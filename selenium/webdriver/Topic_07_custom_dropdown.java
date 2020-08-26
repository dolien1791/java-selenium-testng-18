package webdriver;

import org.testng.annotations.Test;

import jdk.nashorn.internal.runtime.regexp.joni.constants.Arguments;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.jdt.internal.compiler.ast.Argument;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_custom_dropdown {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	//explicit wait
	WebDriverWait explicitWate;
	
	//Fluent Wait
	FluentWait fluentWay;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWate = new WebDriverWait(driver, 30);
		jsExcutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
   
  public void TC_01_JQuery() {
	  driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
	  //action select item
	  selectItemInDropdown("//span[@id=\"number-button\"]", "//li[@class='ui-menu-item']/div", "5");
	  //verify: verify item selected
	  Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());
	  
	  selectItemInDropdown("//span[@id=\"number-button\"]", "//li[@class='ui-menu-item']/div", "10");
	  Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='10']")).isDisplayed());
	  
	  selectItemInDropdown("//span[@id=\"number-button\"]", "//li[@class='ui-menu-item']/div", "15");
	  Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='15']")).isDisplayed());
	  
	  selectItemInDropdown("//span[@id=\"number-button\"]", "//li[@class='ui-menu-item']/div", "19");
	  Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
  }
  
   
  public void TC_02_Angular() {
	  driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
	  
	  selectItemInDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//ul[@id='games_options']/li", "Basketball");
	  Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"), "Basketball");
	  
	  selectItemInDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//ul[@id='games_options']/li", "Golf");
	  Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"), "Golf");
	  
	  selectItemInDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//ul[@id='games_options']/li", "American Football");
	  Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"), "American Football");
  }
  
   
  public void TC_03_react() {
	  driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
	  
	  selectItemInDropdown("//i[@class='dropdown icon']", "//span[@class='text']", "Justen Kitsune");
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']/div[@class='divider text' and text()='Justen Kitsune']")).isDisplayed());
	  
	  selectItemInDropdown("//i[@class='dropdown icon']", "//span[@class='text']", "Stevie Feliciano");
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']/div[@class='divider text' and text()='Stevie Feliciano']")).isDisplayed());
	  
	  selectItemInDropdown("//i[@class='dropdown icon']", "//span[@class='text']", "Jenny Hess");
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']/div[@class='divider text' and text()='Jenny Hess']")).isDisplayed());
  }
  
  
  public void TC_04_Editable() {
	  driver.get("http://indrimuska.github.io/jquery-editable-select/");
	  
	  sendkeyToEditDropdown("//div[@id='default-place']/input", "Ford");
	  Assert.assertEquals(getHiddenText("#default-place li.es-visible"), "Ford");
	  
	  sendkeyToEditDropdown("//div[@id='default-place']/input", "Audi");
	  Assert.assertEquals(getHiddenText("#default-place li.es-visible"), "Audi");
	  
	  sendkeyToEditDropdown("//div[@id='default-place']/input", "Land Rover");
	  Assert.assertEquals(getHiddenText("#default-place li.es-visible"), "Land Rover");
  }
  
  
  public void TC_05_Advanced() {
	  driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");
	  
	  driver.switchTo().frame(driver.findElement(By.tagName("iFrame")));
  }
  
  
  @AfterClass 
	public void afterClass() {
		driver.quit();
	}
  
  	public void sendkeyToEditDropdown(String locator, String value) {
  		driver.findElement(By.xpath(locator)).sendKeys(value);
  		driver.findElement(By.xpath(locator)).clear();
  		sleepInSecond(1);
  		driver.findElement(By.xpath(locator)).sendKeys(Keys.TAB);
  		sleepInSecond(1);
  	}
  	
  //ham nay duoc dung di dung lai nhieu lan chi can truyen gia tri vao
  	public void selectItemInDropdown(String parentLacator, String itemLocator, String expectedItem) {
  		//1. click vao mot the at ki so tat ca cac item trong drop ra
  		driver.findElement(By.xpath(parentLacator)).click();
  		sleepInSecond(1);
  		
  		//2. cho cho tat ca item duoc xuat hien / co trong html DOM
  		explicitWate.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocator)));
  		
  		//3. lay het ta ca cac item nay dua vao 1 list  Element
  		List <WebElement> allItems = driver.findElements(By.xpath(itemLocator));
  		
  		//4. duyet qua tung item
  		for(WebElement item: allItems) {
  			//5. moi lan duyet kiem tra xem text cua item do co bang voi item minh can click hay k
  			String actualItem = item.getText();
  			
  			//neu nhu bang thi click vao thoat khoi k duyet nua
  			//neu nhu khong bang thi lai duyet tiep cho den het tat ca item
  			if(actualItem.equals(expectedItem)) {
  				//truoc khi click thi nen scroll den element
  				jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
  				sleepInSecond(1);
  				
  				item.click();
  				sleepInSecond(2);
  				break;
  			}
  		}
  		 
  	}
  	public String getHiddenText(String cssLocator) {
  		return (String) jsExcutor.executeScript("return document.querySelector(\"" + cssLocator + "\").textContent");
  	}
  	
  	public void selectMultiItemInDropdown(String parentXpath, String allItemXpath, String[] expectedValueitem) throws Exception{
  		//1. click vao drop downlist choo show tat ca cac gia tri
  		driver.findElement(By.xpath(parentXpath)).click();
  		
  		//2. cho cho tat ca cac gia tri trong dropdown duoc load ra thanh cong
  		explicitWate.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
  		
  		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
  		System.out.println("Tat ca phan tu trong dropdown = " + allItems.size());
  		
  		//duyet qua het tat ca cac phan tu cho den khi thoa man dieu kien
  		for(WebElement childElement : allItems) {
  			//January , April, July
  			for(String item : expectedValueitem) {
  				if (childElement.getText().equals(item)) {
  					//3. scroll den item can chon (neu nhu iem can chon co the nhin thay thi k can scroll
  					jsExcutor.executeScript("arguments[0].scrollIntoView(True);", childElement);
  					sleepInSecond(2);
  					
  					//4. click vao item can chon
  					jsExcutor.executeScript("arguments[0].click();", childElement);
  					sleepInSecond(2);
  					
  					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
  					System.out.println("Item selected = " + itemSelected.size());
  					if(expectedValueitem.length == itemSelected.size()) {
  						break;
  					}
  				}
  			}
  		}
  	}
  	
  	public void sleepInSecond(long time) {
  		try {
  			// static dead / hard wait
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}

}
