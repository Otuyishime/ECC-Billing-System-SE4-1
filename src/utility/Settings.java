package utility;

import java.io.FileWriter;
import java.io.IOException;

public class Settings {

	// create a function to write the sessions on
	public static void writeStartSession(String username)
	{
		try {
			String dateAndtime = SimpleDate.getTodayDate() + " " + SimpleDate.getCurrentTime();
			
			// creates a FileWriter Object
			FileWriter writer = new FileWriter("src/session.txt", true); 
			// Writes the content to the file
			writer.write(username + " : " + dateAndtime + " - "); 
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeEndSession()
	{
		try {
			// creates a FileWriter Object
			FileWriter writer = new FileWriter("src/session.txt", true); 
			// Writes the content to the file
			writer.write(SimpleDate.getCurrentTime() + "\n"); 
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
