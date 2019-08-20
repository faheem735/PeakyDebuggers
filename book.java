import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable {
	
	private String title; // changes variable names to title from TITLE - sudeep-13/8(13:00)
	private String author; // changes variable names to author from AUTHOR
	private String phoneNo; // changes variable names to phoneNo from CALLNO
	private int bookId;     // changes variable names to id from bookId
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED }; //change STATE to State
	private state state; // changes from State to state - sudeep- 13/8 (13:00)
	
	// changed all the upper case  variables to lowercase variable. 
	public book(String author, String title, String phoneNO, int bookId) {
		this.author = author;
		this.title = title;
		this.phoneNo = phoneNO;
		this.bookId = bookId;
		this.State = State.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder stingBuilder = new stingbuilder();
		stingbuilder.append("Book: ").append(bookId).append("\n")// added stringbuilder on .append by sudeep
		stingbuilder.append("  Title:  ").append(title).append("\n")
		stingbuilder.append("  Author: ").append(author).append("\n")
		stingbuilder.append("  phoneNO: ").append(phoneNo).append("\n")
		stingbuilder.append("  State:  ").append(State);
		
		return stingbuilder.toString();
	}

	public Integer bookId() {// ID ro bookId
		return bookId;
	}
	// changes TITLE to title
	public String title() {
		return title;
	}


	// changes STATE to state7
	public boolean AVAILABLE() {
		return state == State.AVAILABLE; // changes state to State and State to state- by sudeep
	}

	
	public boolean onLoan() { // changes form On_loan to onLoan
		return state == State.ON_LOAN;// changes state to State and State to state- by sudeep
	}

	
	public boolean isDamaged() { // change from IS_Damaged to isDamaged
		return state == State.DAMAGED;// changes state to State and State to state- by sudeep
	}

	
	public void borrow() { // changes Borrow to borrow
		if (State.equals(state.AVAILABLE)) {
			state = State.ON_LOAN; // changes state to State and State to state- by sudeep
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", State));
		}
		
	}


	public void returndamage(boolean DAMAGED) {
		if (State.equals(state.ON_LOAN)) {
			if (DAMAGED) {
				state = State.DAMAGED; // changes state to State and State to state- by sudeep
			}
			else {
				state = State.AVAILABLE; // changes state to State and State to state- by sudeep
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", State));
		}		
	}

	
	public void Repair() {
		if (State.equals(state.DAMAGED)) {
			State = state.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", State));
		}
	}


}
