package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class Topic_14_UploadFile {
	WebDriver driver;
	String source_folder = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	String imageName_01 = "01.jpg";
	String imageName_02 = "02.jpg";
	String imageName_03 = "03.jpg";

	String image_01_path = source_folder + "\\UploadFIles\\" + imageName_01;
	String image_02_path = source_folder + "\\UploadFIles\\" + imageName_02;
	String image_03_path = source_folder + "\\UploadFIles\\" + imageName_03;



	public void TC_01_Sendkey_Firefox() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(image_01_path);
		sleepInSecond(1);

		uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(image_02_path);
		sleepInSecond(1);

		uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(image_03_path);
		sleepInSecond(1);
	}


	public void TC_02_Sendkey_chrome() {
		System.setProperty("webdriver.chrome.driver", source_folder + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(image_01_path);
		sleepInSecond(1);

		uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(image_02_path);
		sleepInSecond(1);

		uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(image_03_path);
		sleepInSecond(1);
	}
	

	public void TC_03_Sendkey_chrome() {
		System.setProperty("webdriver.chrome.driver", source_folder + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(image_01_path + "\n" + image_02_path + "\n" + image_03_path);
		sleepInSecond(1);
		
		//before upload
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + imageName_01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + imageName_02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + imageName_03 + "']")).isDisplayed());
		
		List<WebElement> startButton = driver.findElements(By.cssSelector("td .start"));
		for(WebElement start: startButton) {
			start.click();
			sleepInSecond(1);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + imageName_01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + imageName_02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + imageName_03 + "']")).isDisplayed());
		
		//after upload
		sleepInSecond(3);
		Assert.assertTrue(isImageDisplayed("//img[contains(@src, '" + imageName_01 + "')]"));
		Assert.assertTrue(isImageDisplayed("//img[contains(@src, '" + imageName_02 + "')]"));
		Assert.assertTrue(isImageDisplayed("//img[contains(@src, '" + imageName_03 + "')]"));
	}

	@Test
	public void TC_04_Sendkey_gofilePage() {
		System.setProperty("webdriver.chrome.driver", source_folder + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://gofile.io/?t=uploadFiles");
		
		String parentID = driver.getWindowHandle();
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(image_01_path + "\n" + image_02_path + "\n" + image_03_path);
		sleepInSecond(1);
		
		driver.findElement(By.xpath("//button[@id='uploadFiles-btnUpload']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("button[aria-lable='OK']")).isDisplayed());
		driver.findElement(By.cssSelector("button[aria-lable='OK']")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("a#link")).isDisplayed());
		driver.findElement(By.cssSelector("a#link")).click();
		
		switchToWindowByID(parentID);
		
		driver.findElement(By.xpath("//button[@class='swal2-cancel swal2-styled']")).click();
		
		//verify download icon
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + imageName_01 + "']/following-sibling::td[@class]//i[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + imageName_02 + "']/following-sibling::td[@class]//i[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + imageName_03 + "']/following-sibling::td[@class]//i[contains(@class,'download')]")).isDisplayed());
		
		//verify play icon
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + imageName_01 + "']/following-sibling::td[@class]//i[contains(@class,'play')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + imageName_02 + "']/following-sibling::td[@class]//i[contains(@class,'play')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + imageName_03 + "']/following-sibling::td[@class]//i[contains(@class,'play')]")).isDisplayed());
	}
	
	@AfterMethod
	public void afterMethod() {
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
	
	public boolean isImageDisplayed(String xpathLocator) {
		jsExecutor = (JavascriptExecutor) driver; 
		
		Boolean ImagePresent = (Boolean) 
				((JavascriptExecutor)driver).executeScript
				("return arguments[0].complete && typeof arguments[0].naturalWidth" 
				+ "!= \"undefined\" && arguments[0].naturalWidth > 0",
				driver.findElement(By.xpath(xpathLocator)));
		return ImagePresent;
	}
	
	public void switchToWindowByID(String parentID) {
		  Set<String> allWindows = driver.getWindowHandles();
		  for (String runWindow : allWindows) {
			  if(!runWindow.equals(parentID)) {
				  driver.switchTo().window(runWindow);
				  break;
			  }
		  }
	  }
}
