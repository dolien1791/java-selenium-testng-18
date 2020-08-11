package javaTester;

public class Topic_01 {

	public static void main(String[] args) {
		System.out.println(showName());
		System.out.println(showName("Automation API"));
		System.out.println(showName("Manual", "Testing"));
	}
	
	public static String showName() {
		return "Automation Unit";	
	}
	
	public static String showName(String fullname) {
		return fullname;
	}
	
	public static String showName(String firstname, String lastname) {
		return firstname + " " + lastname;
	}
	
}
