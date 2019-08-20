import java.util.Scanner;


public class FixBookUI {

	public static enum UserInterfaceState { INITIALISED, READY, FIXING, COMPLETED };//Changed UI_STATE to UserInterfaceState-Zeeshan 13/8

	private FixBookControl control;//Changed CoNtRoL to control-Zeeshan 13/8 
	private Scanner input;
	private UserInterfaceState state;////Changed UI_STATE to UserInterfaceState and sTate to state-Zeeshan 13/8

	
	public FixBookUI(FixBookControl control) {
		this.control = control;////Changed CoNtRoL to control-Zeeshan 13/8
		input = new Scanner(System.in);
		state = UserInterfaceState.INITIALISED;////Changed UI_STATE to UserInterfaceState and sTate to state-Zeeshan 13/8
		control.setUserInterface(this);//Set_UI to setUserInterface-Zeeshan 13/8 
	}


	public void setState(UserInterfaceState state) {//Changed UI_STATE to UserInterfaceState and Set_State to setState-Zeeshan 13/8
		this.state = state;//Changed StAte state-Zeeshan 13/8
	}

	
	public void isRunning() {//Changed Function name run to isRunning()
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {//Changed sTate to state-Zeeshan 13/8
			
			case READY:
				String bookString = input("Scan Book (<enter> completes): ");//Changed BOOK_STR to bookString-Zeeshan 13/8
				if (bookString.length() == 0) {//Changed BOOK_STR to bookString-Zeeshan 13/8
					control.completeScan();//Changed CoNtRoL to control and function completeScan-Zeeshan 13/8
				}
				else {
					try {
						int bookId = Integer.valueOf(bookString).intValue();//Changed Book_ID to bookId and BOOK_STR to bookString-Zeeshan 13/8  
						control.bookScanned(bookId);//Changed Book_ID to bookId and CoNtRoL to control and BookScanned to bookScanned -Zeeshan 13/8
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String answer = input("Fix Book? (Y/N) : ");//Changed AnS to answer -Zeeshan 13/8
				boolean fix = false;//Changed fix to FIX -Zeeshan 13/8  
				if (answer.toUpperCase().equals("Y")) {////Changed AnS to answer -Zeeshan 13/8
					fix = true;//Changed fix to FIX -Zeeshan 13/8  
				}
				control fixBook(fix);//Changed CoNtRoL to control and fixBook to fiX  to fix-Zeeshan 13/8
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);//Changed StAte to state-Zeeshan 13/8  		
			
			}		
		}
		
	}

	
	private String getInput(String prompt) {//Changed function name from input to getInput-Zeeshan 13/8 
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void displayOutput(Object object) {//Changed function name from output to displayOutput-Zeeshan 13/8
		System.out.println(object);
	}
	

	public void displayResult(Object object) {
		output(object);
	}
	
	
}
