import java.util.Scanner;


public class BorrowBookUi {// change to BorrowBookUi from BorrowBookUI
	
	public static enum UserInterfaceState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };// change UI_STATE to UserInterfaceState
	// changes all the variables to the lowercase and camelback
	private BorrowBookControl control; // change to control
	private Scanner input;
	private UserInterfaceState state;

	
	public borrowBookUi(BorrowBookControl control) {// BorrowbookUI to borrowBookUi
		this.control = control; // CONTROL to control
		input = new Scanner(System.in);
		state = UserInterfaceState.INITIALISED; // change UI_STATE to UserInterfaceState
		control.setUserInterface(this); // change to setUserInterface to ustUI
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void setState(UserInterfaceState state) { //change set_State to setState and UI_STATE to UserInterfaceState and STATE to state
		this.state = state;// change StaTe and STATE to state
	}

	
	public void run() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (state) { // change in staTe to state				
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:
				String memberCardNo = input("Swipe member card (press <enter> to cancel): "); // change made from MEM_STR to memberCardNo
				if (memberCardNo.length() == 0) {
					CONTROL.cancel();
					break;
				}
				try {
					int Member_ID = Integer.valueOf(memberCardNo).intValue();
					CONTROL.Swiped(Member_ID);
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				CONTROL.cancel();
				break;
			
				
			case SCANNING:
				String Book_Str = input("Scan Book (<enter> completes): ");
				if (Book_Str.length() == 0) {
					CONTROL.Complete();
					break;
				}
				try {
					int BiD = Integer.valueOf(Book_Str).intValue();
					CONTROL.Scanned(BiD);
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String Ans = input("Commit loans? (Y/N): ");
				if (Ans.toUpperCase().equals("N")) {
					CONTROL.cancel();
					
				} else {
					CONTROL.Commit_LOans();
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


	public void Display(Object object) {
		output(object);		
	}


}
