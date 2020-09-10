package TestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_06_Parameter {
	WebDriver driver;
	String userDirectory = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");

	@Parameters("browserName")
	@BeforeClass
	public void beforeClass(String browser) {
		if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", userDirectory + ".\\BrowserDriver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", userDirectory + ".\\BrowserDriver\\credits.html");
			driver = new EdgeDriver();
		}else {
			throw new RuntimeException("Please input your browser name!");
		}
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "user_pass")
	public void TC_01_LoginToSystem(String username, String password) {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}

	@DataProvider(name = "user_pass")
		public Object [] [] UserAndPasswprdData(){
			return new Object [] [] {
			{"selenium_11_01@gmail.com", "111111"}, 
			{"selenium_11_02@gmail.com", "111111"},
			{"selenium_11_03@gmail.com", "111111"}};
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
