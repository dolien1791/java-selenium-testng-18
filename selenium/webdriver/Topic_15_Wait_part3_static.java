package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_15_Wait_part3_static {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	public void TC_01_10s() {
		driver.get("http://juliemr.github.io/protractor-demo/");

		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("5");
		driver.findElement(By.id("gobutton")).click();

		// chi can 3s de ket qua xuat hien. du thoi gian, lang phi mat 7s
		sleepInSecond(10);

		Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='10']")).isDisplayed());
	}

	@Test
	public void TC_02_3s() {
		driver.get("http://juliemr.github.io/protractor-demo/");

		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("5");
		driver.findElement(By.id("gobutton")).click();

		// chi can 3s de ket qua xuat hien. du thoi gian
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='10']")).isDisplayed());
	}
	
	@Test
	public void TC_03_1s() {
		driver.get("http://juliemr.github.io/protractor-demo/");

		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("5");
		driver.findElement(By.id("gobutton")).click();

		// chi can 3s de ket qua xuat hien. thieu thoi gian
		sleepInSecond(1);

		Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='10']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
