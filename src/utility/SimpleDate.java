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

	public static String getTodayDate(Date date){
		df_day = new SimpleDateFormat("dd/MM/yy");
		return df_day.format(date);
	}

	public static boolean compareDates(String fromdate, String todate, String date){
		String fromd_arr[] = fromdate.split("/");
		String tod_arr[] = todate.split("/");
		String d_arr[] = date.split("/");

		String frommonth = fromd_arr[0], fromday = fromd_arr[1],fromyear = fromd_arr[2],
				tomonth = tod_arr[0], today = tod_arr[1], toyear = tod_arr[2],
				month = d_arr[0], day = d_arr[1], year = d_arr[2];

		Date fromDate = new Date(), toDate = new Date(), currentDate = new Date();
		fromDate.setMonth(Integer.parseInt(frommonth));
		fromDate.setDate(Integer.parseInt(fromday));
		fromDate.setYear(Integer.parseInt(fromyear));

		toDate.setMonth(Integer.parseInt(tomonth));
		toDate.setDate(Integer.parseInt(today));
		toDate.setYear(Integer.parseInt(toyear));

		currentDate.setMonth(Integer.parseInt(month));
		currentDate.setDate(Integer.parseInt(day));
		currentDate.setYear(Integer.parseInt(year));

		if ( currentDate.getYear() >= fromDate.getYear() && currentDate.getYear() <= toDate.getYear()){
			if ( currentDate.getMonth() >= fromDate.getMonth() && currentDate.getMonth() <= toDate.getMonth()){
				if (currentDate.getMonth() == fromDate.getMonth()){
					if ( currentDate.getDate() >= fromDate.getDate()){
						return true;
					}
				}
				
				if (currentDate.getMonth() == toDate.getMonth()){
					if ( currentDate.getDate() <= toDate.getDate()){
						return true;
					}
				}
				
				if (currentDate.getMonth() < toDate.getMonth()){
					return true;
				}
			}
		}
		return false;
	}
}
