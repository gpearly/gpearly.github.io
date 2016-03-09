package defecttracker;

public class DateUtils {
	
	public static java.sql.Date getCurrentDate() {		
		java.util.Date date = new java.util.Date();
		return new java.sql.Date(date.getTime());
	}
}
