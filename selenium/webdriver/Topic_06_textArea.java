package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_textArea {
	WebDriver driver;
	String userIDValue, password, loginPageUrl, customerID;
	String name, dateOfBirth, address, city, state, pin, phone, email, gender;
	String editAddress, editCity, editState, editPin, editPhone, editEmail;

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
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// user (Input data) - New customer
		name = "MiaDo";
		gender = "male";
		dateOfBirth = "1991-07-01";
		address = "Amaya department\nMaluri";
		city = "Kuala Lumpur";
		state = "Malaysia";
		pin = "111000";
		phone = "0178857415";
		email = "domia" + randomNumber() + "@yopmail.com";

		// user (Input data) - Edit customer
		editAddress = "PV12\nMaluri";
		editCity = "Malacka";
		editState = "Singapor";
		editPin = "111555";
		editPhone = "0178857888";
		editEmail = "domia" + randomNumber() + "@gmail.com";

		driver.get("http://demo.guru99.com/v4/");
		loginPageUrl = driver.getCurrentUrl();
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		userIDValue = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	}

	@Test
	public void TC_02_Login() throws InterruptedException {
		driver.get(loginPageUrl);

		driver.findElement(By.name("uid")).sendKeys(userIDValue);
		Thread.sleep(2000);

		driver.findElement(By.name("password")).sendKeys(password);
		Thread.sleep(2000);

		driver.findElement(By.name("btnLogin")).click();

		// verify navigate to manage page success
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");
	}

	@Test
	public void TC_03_New_Customer() {
		
		//click new customer
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
	public void TC_04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

		// input to customer ID textbox
		driver.findElement(By.name("cusid")).sendKeys(customerID);

		driver.findElement(By.name("AccSubmit")).click();

		// verify edit customer form info matching with new customer info
		Assert.assertEquals(driver.findElement(customerNameTextbox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(genderTextbox).getAttribute("value"), gender);
		Assert.assertEquals(driver.findElement(dateOfBirthTextbox).getAttribute("value"), dateOfBirth);
		Assert.assertEquals(driver.findElement(addressTextarea).getText(), address);
		Assert.assertEquals(driver.findElement(cityTextbox).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextbox).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextbox).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(mobileNumberTextbox).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"), email);

		// verify Name/Gender/ Date of Birth are disable fields (read only)
		Assert.assertFalse(driver.findElement(customerNameTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(genderTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(dateOfBirthTextbox).isEnabled());

		// edit data at edit customer form
		driver.findElement(addressTextarea).clear();
		driver.findElement(addressTextarea).sendKeys(editAddress);
		driver.findElement(cityTextbox).clear();
		driver.findElement(cityTextbox).sendKeys(editCity);
		driver.findElement(stateTextbox).clear();
		driver.findElement(stateTextbox).sendKeys(editState);
		driver.findElement(pinTextbox).clear();
		driver.findElement(pinTextbox).sendKeys(editPin);
		driver.findElement(mobileNumberTextbox).clear();
		driver.findElement(mobileNumberTextbox).sendKeys(editPhone);
		driver.findElement(emailTextbox).clear();
		driver.findElement(emailTextbox).sendKeys(editEmail);

		driver.findElement(By.name("sub")).click();

		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Customer details updated Successfully!!!");

		// verify input data (user) matching with output data (server response)
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress.replace("\n", " "));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
