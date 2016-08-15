package dAO;

import testECC.*;

import java.util.ArrayList;

import javax.persistence.Query;

public class TimeSheetLineDAO 
{
	public static void saveTimeSheetLine(TimeSheetLine timesheetline) {
		EM.INSTANCE.getEM().persist(timesheetline);
	}
	
	public static int  updateTimeSheetLine(String columnname, String fromvalue, String tovalue) {
		Query query = EM.INSTANCE.getEM().createQuery("UPDATE " + TimeSheetLine.class.getName() + " SET " + 
				columnname + " = " + tovalue + " WHERE " + columnname + " = " + fromvalue);
		int success = query.executeUpdate();
		return success;
	}
	
	public static void addTimeSheetLine(TimeSheetLine timesheetline) {
		if ( findTimeSheetLineById(timesheetline.getId()) == null){
			EM.INSTANCE.getEM().persist(timesheetline);
		}
	}

	public static ArrayList<TimeSheetLine> listTimeSheetLines() {
		Query query = EM.INSTANCE.getEM().createQuery("SELECT project FROM " + TimeSheetLine.class.getName() + " project", TimeSheetLine.class);
		ArrayList<TimeSheetLine> list= new ArrayList<TimeSheetLine>(query.getResultList());

		return list;
	}

	public static TimeSheetLine findTimeSheetLineById(long id) {
		TimeSheetLine timesheetline = EM.INSTANCE.getEM().find(TimeSheetLine.class, new Long(id));
		return timesheetline;
	}

	public static void removeTimeSheetLineById(long id) {
		TimeSheetLine timesheetline = findTimeSheetLineById(id);
		if ( timesheetline != null){
			EM.INSTANCE.getEM().remove(timesheetline);
			return;
		}
		System.out.println("Unable to delete the timesheetline");
	}
}