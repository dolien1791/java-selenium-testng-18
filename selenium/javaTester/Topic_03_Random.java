package javaTester;

import java.util.Random;

public class Topic_03_Random {

	public static void main(String[] args) {
		System.out.println("mia" + randomNumber() + "@yopmail.com");
		System.out.println("mia" + randomNumber() + "@yopmail.com");
		System.out.println("mia" + randomNumber() + "@yopmail.com");
	}
	
	public static int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
