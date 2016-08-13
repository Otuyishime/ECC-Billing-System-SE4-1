package utility;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SimpleDate
{
	private static DateFormat df_day;
	private static DateFormat df_time;
	private static Date dateobj;
	
	private static void init(){
		//getting current date and time using Date class
		df_day = new SimpleDateFormat("dd/MM/yy");
		df_time = new SimpleDateFormat("HH:mm:ss");
		dateobj = new Date();
	}

	public static String getTodayDate(){
		init();
		return df_day.format(dateobj);
	}
	
	public static String getCurrentTime(){
		init();
		return df_time.format(dateobj);
	}
}
