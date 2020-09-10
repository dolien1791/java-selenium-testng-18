package TestNG;

import java.util.Random;

import org.testng.annotations.Test;

public class Topic_07_Loop {
	@Test(invocationCount = 10)
	public void TC_01_login_to_system() {
		System.out.println("TC_01_login_to_system" + randomNumber());
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
}