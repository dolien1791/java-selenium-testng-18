package TestNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assertion {
	@Test
	public void TC_01() {
		int a = 10;
		int b = 15;

		boolean status = a < b;
		System.out.println(status);

		// ham kiem tra 1 dieu kien mong muon tra ve DUNG
		Assert.assertTrue(status);

		// ham kiem tra 1 dieu kien mong muon tra ve SAI
		Assert.assertFalse(a > b); //sai cua sai la pass

		// ham kiem tra 2 dieu kien mong muon tra ve BANG NHAU
		Assert.assertEquals(a, 10);
	}
}
