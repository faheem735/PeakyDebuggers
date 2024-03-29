import java.util.Scanner;


public class BorrowBookUi {// change to BorrowBookUi from BorrowBookUI
	
	public static enum UserInterfaceState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };// change UI_STATE to UserInterfaceState by sudeep on 20/8
	// changes all the variables to the lowercase and camelback by sudeep on 20/8
	private BorrowBookControl control; // change to control by sudeep on 20/8
	private Scanner input;
	private UserInterfaceState state;

	
	public borrowBookUi(BorrowBookControl control) {//  method name change from BorrowbookUI to borrowBookUiby sudeep on 20/8
		this.control = control; // CONTROL to control by sudeep on 20/8
		input = new Scanner(System.in);
		state = UserInterfaceState.INITIALISED; // change UI_STATE to UserInterfaceState by sudeep on 20/8
		control.setUserInterface(this); // change to setUserInterface to ustUI by sudeep on 20/8
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void setState(UserInterfaceState state) { //change set_State to setState and UI_STATE to UserInterfaceState and STATE to state by sudeep on 20/8
		this.state = state;// change StaTe and STATE to state by sudeep on 20/8
	}

	
	public void run() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (state) { // change in staTe to state				
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				// changes all CONTROL to control
			case READY:
				String memberCardNo = input("Swipe member card (press <enter> to cancel): "); // change made from MEM_STR to memberCardNo
				if (memberCardNo.length() == 0) {
					control.cancel();
					break;
				}
				try {
					int memberId = Integer.valueOf(memberCardNo).intValue(); // change the Member_ID to memberId by sudeep
					control.Swiped(memberId); // change the Member_ID to memberId by sudeep
					control.Swiped(memberId);
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				control.cancel();
				break;
			
				
			case SCANNING:
				String bookScan = input("Scan Book (<enter> completes): "); //changes Book_Str to bookscan and changes made on further as well.
				if (bookScan.length() == 0) {
					control.Complete();
					break;
				}
				try {
					int bookId = Integer.valueOf(bookScan).intValue(); // changes from BID to bookId
					control.Scanned(bookId);
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String answer = input("Commit loans? (Y/N): "); // Ans to answer
				if (answer.toUpperCase().equals("N")) {
					control.cancel();
					
				} else {
					control.commitLoan(); //changes made on Commit_Loan to commitLoan
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + state);		// change in staTe to state	
			}
		}		
	}


	public void display(Object object) { // change on Display to display.
		output(object);		
	}


}
