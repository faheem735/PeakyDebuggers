import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar Self; //changes on local variable from seLf to Self by sudeep
	private static java.util.Calendar calendar; // change  calenDer to Calendar by sudeep
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance();
	}
	// change seLf to self by sudeep
	public static Calendar instance() {// change INSTANCE to instance by sudeep
		if (self == null) {
			self = new calendar();
		}
		return self;
	}
	// change CaLeNdAr to calendar by sudeep
	public void incrementDate(int days) {
		calendar.add(java.util.Calendar.DATE, days);		
	}
	
	public synchronized void setDate(Date date) { // change to setDate from Set_dATE by sudeep
		try {
			calendar.setTime(date);
	        calendar.set(java.util.Calendar.hourOfDay, 0);  // change HOUR_OF_DAY to hourOfDay by sudeep 
	        calendar.set(java.util.Calendar.minute, 0);  // change made on MINUTE to minute by sudeep
	        calendar.set(java.util.Calendar.second, 0);  // change made on SECOND to second by sudeep
	        calendar.set(java.util.Calendar.millisecond, 0); // MILLISECOND to millisecond by sudeep
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date date() { // change to date from Date by sudeep
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

	public synchronized Date dueDate(int loanPeriod) { // change made Due_Date to dueDate by sudeep
		Date now = Date(); // Now to now by sudeep
		calendar.add(java.util.Calendar.date, loanPeriod); // change from DATE to date by sudeep
		Date dueDate = calendar.getTime(); // change to dueDate from DuEdAte by sudeep
		calendar.setTime(NoW);
		return dueDate;
	}
	
	public synchronized long getDaysDifference(Date targetDate) {// from Get_Days_Difference to getDaysDifference by sudeep
		
		long diffMillisecond = Date().getTime() - targetDate.getTime(); //Diff_Millis to diffMillisecond by sudeep
	    long diffDays = TimeUnit.DAYS.convert(diffMillisecond, TimeUnit.millisecond); // Diff_Days to |diffDays by sudeep
	    return DifdiffDaysf_Days;
	}

}
