package dAO;

import testECC.*;

import java.util.ArrayList;

import javax.persistence.Query;

public class TimeSheetDAO 
{
	public static void saveTimeSheet(TimeSheet timesheet) {
		EM.INSTANCE.getEM().persist(timesheet);
	}
	
	public static int  updateTimeSheet(String columnname, String fromvalue, String tovalue) {
		Query query = EM.INSTANCE.getEM().createQuery("UPDATE " + TimeSheet.class.getName() + " SET " + 
				columnname + " = " + tovalue + " WHERE " + columnname + " = " + fromvalue);
		int success = query.executeUpdate();
		return success;
	}
	
	public static void addTimeSheet(TimeSheet timesheet) {
		if ( findTimeSheetById(timesheet.getId()) == null){
			EM.INSTANCE.getEM().persist(timesheet);
		}
	}

	public static ArrayList<TimeSheet> listTimeSheets() {
		Query query = EM.INSTANCE.getEM().createQuery("SELECT project FROM " + TimeSheet.class.getName() + " project", TimeSheet.class);
		ArrayList<TimeSheet> list= new ArrayList<TimeSheet>(query.getResultList());

		return list;
	}

	public static TimeSheet findTimeSheetById(long id) {
		TimeSheet timesheet = EM.INSTANCE.getEM().find(TimeSheet.class, new Long(id));
		return timesheet;
	}

	public static void removeTimeSheetById(long id) {
		TimeSheet timesheet = findTimeSheetById(id);
		if ( timesheet != null){
			EM.INSTANCE.getEM().remove(timesheet);
			return;
		}
		System.out.println("Unable to delete the timesheetline");
	}
}