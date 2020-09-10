package javaTester;

public class Topic_07_path {
	static String source_folder = System.getProperty("user.dir");
	
	static String absolute_path = "D:\\Selenium web driver\\02-Selenium API\\UploadFIles\\01.jpg";
	static String relative_path_01 = ".\\UploadFIles\\01.jpg";
	static String relative_path_02 = source_folder + "\\UploadFIles\\01.jpg";
	public static void main(String[] args) {
		System.out.println(source_folder);

	}

}
