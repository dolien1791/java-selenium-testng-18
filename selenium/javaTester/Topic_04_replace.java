package javaTester;

public class Topic_04_replace {

	public static void main(String[] args) {
		String inputAddress = "Amaya department\nMaluri";
		String outputAddress = "Amaya department Maluri";
		
		inputAddress = inputAddress.replace("\n", " ");
		
		System.out.println(inputAddress);
		System.out.println(outputAddress);
	}

}
