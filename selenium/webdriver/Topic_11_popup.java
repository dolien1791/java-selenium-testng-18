package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_11_popup {
	WebDriver driver;
	WebDriverWait explicitWait;
	boolean status;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chorome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_Popup_Fix() {
		driver.get("https://www.zingpoll.com/");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("Loginform")));
		sleepInSecond(3);
		driver.findElement(By.id("Loginform")).click();
		sleepInSecond(3);

		// login popup hien thi
		status = driver.findElement(By.id("Login")).isDisplayed();
		System.out.println("Lging popup = " + status);
		Assert.assertTrue(status);

		// click close popup
		driver.findElement(By.cssSelector("#Login .close")).click();
		sleepInSecond(3);

		status = driver.findElement(By.id("Login")).isDisplayed();
		System.out.println("Lging popup close = " + status);
		Assert.assertFalse(status);
		sleepInSecond(2);

		// open popup again
		driver.findElement(By.id("Loginform")).click();
		sleepInSecond(2);

		driver.findElement(By.id("loginEmail")).sendKeys("automationfc.vn@gmail.com");
		sleepInSecond(1);
		driver.findElement(By.id("loginPassword")).sendKeys("automationfc");

		driver.findElement(By.id("button-login")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='username' and contains(text(),'Automation Testing')]")).isDisplayed());
	}

	@Test
	public void TC_02_Popup_Random() {
		driver.get("https://blog.testproject.io/");
		sleepInSecond(15);
		
		//step 01
		if (driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")).isDisplayed()) {
			
			//check sign up now button display
			Assert.assertTrue(driver.findElement(By.cssSelector(".right-arr.lazyloaded")).isDisplayed());
			
			//close popup
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
			sleepInSecond(2);
		}
		
		//Step 02
		driver.findElement(By.cssSelector("#search-2 .search-field")).sendKeys("selenium");
		
		//Steps 03 click on search icon
		driver.findElement(By.cssSelector("#search-2 .glass")).click();
		
		//step 04 verify 'selenium' in all article title on first page
		List<WebElement> allArticleTitle = driver.findElements(By.cssSelector(".post-title"));
		
		//verify all article
		for(WebElement article: allArticleTitle) {
			String articleText = article.getText().trim();
			Assert.assertTrue(articleText.contains("slenium"));
		}
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
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
