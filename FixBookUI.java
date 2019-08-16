import java.util.Scanner;


public class FixBookUI {

	public static enum UserInterfaceState { INITIALISED, READY, FIXING, COMPLETED };//Changed UI_STATE to UserInterfaceState-Zeeshan 13/8

	private FixBookControl control;//Changed CoNtRoL to control-Zeeshan 13/8 
	private Scanner input;
	private UserInterfaceState state;////Changed UI_STATE to UserInterfaceState and sTate to state-Zeeshan 13/8

	
	public FixBookUI(FixBookControl control) {
		this.CoNtRoL = control;
		input = new Scanner(System.in);
		StAtE = UI_STATE.INITIALISED;
		control.Set_Ui(this);
	}


	public void Set_State(UI_STATE state) {
		this.StAtE = state;
	}

	
	public void RuN() {
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (StAtE) {
			
			case READY:
				String Book_STR = input("Scan Book (<enter> completes): ");
				if (Book_STR.length() == 0) {
					CoNtRoL.SCannING_COMplete();
				}
				else {
					try {
						int Book_ID = Integer.valueOf(Book_STR).intValue();
						CoNtRoL.Book_scanned(Book_ID);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String AnS = input("Fix Book? (Y/N) : ");
				boolean FiX = false;
				if (AnS.toUpperCase().equals("Y")) {
					FiX = true;
				}
				CoNtRoL.FIX_Book(FiX);
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + StAtE);			
			
			}		
		}
		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	

	public void display(Object object) {
		output(object);
	}
	
	
}
