package webdriver;

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;

import com.google.common.base.Function;
import com.google.gson.annotations.Until;

public class Topic_15_Wait_part6_Fluent {
	WebDriver driver;
	FluentWait<WebElement> fluentElement;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	@Test 
  public void TC_01_Web_browser() {
	  driver.get("https://automatFluentWait<T>ub.io/fluent-wait/");
	  
	  
	  WebElement countdown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
	  fluentElement = new FluentWait<WebElement>(countdown);
	  
	  fluentElement.withTimeout(15, TimeUnit.SECONDS);
	  	//tan so moi 1/10 s check 1 lan
	  	//.pollingEvery(100, TimeUnit.MILLISECONDS);
	  	//neu gap exception la 1 find khong thay element se bo qua
	  	//.ignoring(NoSuchElementException.class);
	  	//kiem tra dieu kien
	  	//.until(new Function<WebElement, Boolean>(){
	  		public Boolean apply(WebElement element) {
	  			//kiem tra dieu kien countdown = 00
	  			boolean flag = element.getText().endsWith("02");
	  			System.out.println("Time = " + element.getText());
	  			//return gia tri cho function apply
	  			return flag;
	  		}
	  	})
  }

	@Test
	public void TC_02_Web_Element() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
