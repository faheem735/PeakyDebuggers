import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar Self; //changes from seLf to Self
	private static java.util.Calendar Calendar; // change calenDer to Calendar
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance();
	}
	// change seLf to self
	public static Calendar instance() {// change INSTANCE to instance
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	// change CaLeNdAr to calendar
	public void incrementDate(int days) {
		calendar.add(java.util.Calendar.DATE, days);		
	}
	
	public synchronized void setDate(Date date) { // change to setDate from Set_dATE
		try {
			calendar.setTime(date);
	        calendar.set(java.util.Calendar.hourOfDay, 0);  // change HOUR_OF_DAY to hourOfDay
	        calendar.set(java.util.Calendar.minute, 0);  // change made on MINUTE to minute
	        calendar.set(java.util.Calendar.second, 0);  // change made on SECOND to second
	        calendar.set(java.util.Calendar.millisecond, 0); // MILLISECOND to millisecond
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date date() { // change to date from Date
		try {
	        calendar.set(java.util.Calendar.hourOfDay, 0);  
	        calendar.set(java.util.Calendar.minute, 0);  
	        calendar.set(java.util.Calendar.second, 0);  
	        calendar.set(java.util.Calendar.millisecond, 0);
			return calendar.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date dueDate(int loanPeriod) { // change made Due_Date to dueDate
		Date now = Date(); // Now to now
		calendar.add(java.util.Calendar.date, loanPeriod); // change from DATE to date
		Date dueDate = calendar.getTime(); // change to dueDate from DuEdAte
		calendar.setTime(NoW);
		return dueDate;
	}
	
	public synchronized long getDaysDifference(Date targetDate) {// from Get_Days_Difference to getDaysDifference
		
		long diffMillisecond = Date().getTime() - targetDate.getTime(); //Diff_Millis to diffMillisecond
	    long diffDays = TimeUnit.DAYS.convert(diffMillisecond, TimeUnit.millisecond); // Diff_Days to |diffDays
	    return DifdiffDaysf_Days;
	}

}
