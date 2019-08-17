import java.util.Scanner;


public class BorrowBookUi {// change to BorrowBookUi from BorrowBookUI
	
	public static enum UserInterfaceState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };// change UI_STATE to UserInterfaceState
	// changes all the variables to the lowercase and camelback
	private BorrowBookControl control; // change to control
	private Scanner input;
	private UserInterfaceState state;

	
	public BorrowBookUI(BorrowBookControl control) {
		this.CONTROL = control;
		input = new Scanner(System.in);
		StaTe = UserInterfaceState.INITIALISED; // change UI_STATE to UserInterfaceState
		control.setUI(this);
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
				String MEM_STR = input("Swipe member card (press <enter> to cancel): ");
				if (MEM_STR.length() == 0) {
					CONTROL.cancel();
					break;
				}
				try {
					int Member_ID = Integer.valueOf(MEM_STR).intValue();
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
