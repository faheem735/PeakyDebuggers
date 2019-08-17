import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar Self; //changes from seLf to Self
	private static java.util.Calendar Calendar; // change calenDer to Calendar
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance();
	}
	// change seLf to self
	public static Calendar INSTANCE() {
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	// change CaLeNdAr to calendar
	public void incrementDate(int days) {
		calendar.add(java.util.Calendar.DATE, days);		
	}
	
	public synchronized void Set_dATE(Date date) {
		try {
			calendar.setTime(date);
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        calendar.set(java.util.Calendar.MINUTE, 0);  
	        calendar.set(java.util.Calendar.SECOND, 0);  
	        calendar.set(java.util.Calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date Date() {
		try {
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        calendar.set(java.util.Calendar.MINUTE, 0);  
	        calendar.set(java.util.Calendar.SECOND, 0);  
	        calendar.set(java.util.Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date dueDate(int loanPeriod) { // change made Due_Date to dueDate
		Date now = Date(); // Now to now
		calendar.add(java.util.Calendar.DATE, loanPeriod);
		Date dueDate = calendar.getTime(); // change to dueDate from DuEdAte
		calendar.setTime(NoW);
		return dueDate;
	}
	
	public synchronized long Get_Days_Difference(Date targetDate) {
		
		long Diff_Millis = Date().getTime() - targetDate.getTime();
	    long Diff_Days = TimeUnit.DAYS.convert(Diff_Millis, TimeUnit.MILLISECONDS);
	    return Diff_Days;
	}

}
