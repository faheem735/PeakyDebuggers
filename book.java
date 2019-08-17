import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable {
	
	private String title; // changes variable names to title from TITLE - sudeep-13/8(13:00)
	private String author; // changes variable names to author from AUTHOR
	private String phoneNo; // changes variable names to phoneNo from CALLNO
	private int bookId;     // changes variable names to id from bookId
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED }; //change STATE to State
	private STATE state; // changes from State to state - sudeep- 13/8 (13:00)
	
	// changed all the upper case  variables to lowercase variable. 
	public book(String author, String title, String phoneNO, int bookId) {
		this.author = author;
		this.title = title;
		this.phoneNo = callNo;
		this.bookId = bookId;
		this.State = State.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(bookId).append("\n")
		  .append("  Title:  ").append(TITLE).append("\n")
		  .append("  Author: ").append(AUTHOR).append("\n")
		  .append("  CallNo: ").append(CALLNO).append("\n")
		  .append("  State:  ").append(State);
		
		return sb.toString();
	}

	public Integer ID() {
		return bookId;
	}

	public String TITLE() {
		return TITLE;
	}


	
	public boolean AVAILABLE() {
		return State == STATE.AVAILABLE;
	}

	
	public boolean On_loan() {
		return State == STATE.ON_LOAN;
	}

	
	public boolean IS_Damaged() {
		return State == STATE.DAMAGED;
	}

	
	public void Borrow() {
		if (State.equals(STATE.AVAILABLE)) {
			State = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", State));
		}
		
	}


	public void Return(boolean DAMAGED) {
		if (State.equals(STATE.ON_LOAN)) {
			if (DAMAGED) {
				State = STATE.DAMAGED;
			}
			else {
				State = STATE.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", State));
		}		
	}

	
	public void Repair() {
		if (State.equals(STATE.DAMAGED)) {
			State = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", State));
		}
	}


}
