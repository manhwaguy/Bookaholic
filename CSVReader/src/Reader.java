import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Reader {

	public static void main(String[] args) {
		//
		
		try {
			
			try (FileWriter csvwriter = new FileWriter("ads.csv")) {
				csvwriter.append("FIRSTNAME");
				csvwriter.append(",");
				csvwriter.append("LASTNAME");
				csvwriter.append(",");
				csvwriter.append("AGE");
				csvwriter.append(",");
				csvwriter.append("\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
