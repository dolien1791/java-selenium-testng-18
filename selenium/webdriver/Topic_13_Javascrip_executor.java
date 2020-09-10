package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_13_Javascrip_executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebElement element;
	String name, dateOfBirth, address, city, state, pin, phone, email, gender, password, customerID;

	By customerNameTextbox = By.name("name");
	By genderTextbox = By.name("gender");
	By genderMaleRadioButton = By.xpath("//input[@value='m']");
	By dateOfBirthTextbox = By.name("dob");
	By addressTextarea = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By mobileNumberTextbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passwordTextbox = By.name("password");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;

		name = "MiaDo";
		gender = "male";
		dateOfBirth = "1991-07-01";
		address = "Amaya department\nMaluri";
		city = "Kuala Lumpur";
		state = "Malaysia";
		pin = "111000";
		phone = "0178857415";
		email = "domia" + randomNumber() + "@yopmail.com";
		password = "123456789";

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_click_hidden_element() {
		driver.get("https://www.myntra.com/");
		WebElement homeAndPathLink = driver.findElement(By.xpath("//a[text()='Home & Bath']"));

		jsExecutor.executeScript("arguments[0].click();", homeAndPathLink);

		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());
	}

	public void TC_02_Live_Guru() {
		navigateToUrlByJS("http://live.demoguru99.com/");

		String liveGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");

		String liveGuruUrl = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(liveGuruUrl, "http://live.demoguru99.com/");

		clickToElementByJS("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

		String liveGuruInnerValue = getPageInnerText();
		Assert.assertTrue(liveGuruInnerValue.contains("Samsung Galaxy was added to your shopping cart."));
		Assert.assertTrue(verifyTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		clickToElementByJS("//a[text()='Customer Service']");

		String customerServiceTitle = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(customerServiceTitle, "Customer Service");

		scrollToElement("//input[@id='newsletter']");

		Assert.assertTrue(verifyTextInInnerText("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));

		navigateToUrlByJS("http://demo.guru99.com/v4/");

		String demoGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");
	}

	public void TC_03_Remove_Attribute() {
		driver.get("http://demo.guru99.com/v4/");

		driver.findElement(By.name("uid")).sendKeys("mngr277128");
		driver.findElement(By.name("password")).sendKeys("ujUnYsy");
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");

		// click new customer
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(customerNameTextbox).sendKeys(name);

		// remove attribute @type=date
		removeAttributeInDOM("//input[@name='dob']", "type");
		sleepInSecond(5);

		// Date of birth date time picker
		driver.findElement(dateOfBirthTextbox).sendKeys(dateOfBirth);

		driver.findElement(addressTextarea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(mobileNumberTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);

		driver.findElement(By.name("sub")).click();

		// verify create new customer success
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Customer Registered Successfully!!!");

		// verify input data (user) matching with output data (server response)
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address.replace("\n", " "));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}

	@Test
	public void TC_04_create_account() {
		navigateToUrlByJS("http://live.demoguru99.com/");

		clickToElementByJS("//div[@id='header-account']//a[text()='My Account']");
		sleepInSecond(2);

		clickToElementByJS("//span[text()='Create an Account']");
		sleepInSecond(1);

		// click new customer
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(customerNameTextbox).sendKeys(name);
		driver.findElement(dateOfBirthTextbox).sendKeys(dateOfBirth);
		driver.findElement(addressTextarea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(mobileNumberTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);

		driver.findElement(By.name("sub")).click();

		// verify input data (user) matching with output data (server response)
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address.replace("\n", " "));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// Browser
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getPageInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean verifyTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	// Element
	public void highlightElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void clickToElementByJS(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
