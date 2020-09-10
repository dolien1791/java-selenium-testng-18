package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_15_Wait_part4_Explicit {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	WebElement dateSelected;
	String today = "Wednesday, September 9, 2020";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		explicitWait = new WebDriverWait(driver, 15);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_alert_presence() {
		driver.get("http://demo.guru99.com/v4/index.php");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.name("btnLogin")));
		driver.findElement(By.name("btnLogin")).click();

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		System.out.println(alert.getText());

		alert.accept();
	}

	
	public void TC_02_visible() {
		driver.get("http://juliemr.github.io/protractor-demo/");

		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("5");
		driver.findElement(By.id("gobutton")).click();

		WebElement resultText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='10']")));

		Assert.assertTrue(resultText.isDisplayed());
	}

	
	public void TC_03_Invisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='start']/button"))).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")), "Hello World!");
	}
	
	@Test
	public void TC_04_Ajax_loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//cho date time picker hien thi
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
		
		//DOM truoc khi click
		dateSelected = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		
		Assert.assertEquals(dateSelected.getText(), "No Selected Dates to display.");
		
		//click vao ngay hien tai
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@title='" + today + "']"))).click();
		
		//cho cho loadin icon bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@class='raDiv']"))));
		
		//DOM sau khi click
		dateSelected = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(dateSelected.getText(), today);
	}
	
	@Test
	public void TC_05_upload_file() {
		driver.get("https://gofile.io/uploadFiles");
		
		//cho upload file button visible
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
