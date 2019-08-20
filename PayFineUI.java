import java.util.Scanner;


public class PayFineUI {


	// Chaning UI_STATE to uiState
	public static enum uiState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };

	private PayFineControl control; // Changed CoNtRol to control
	private Scanner input; 
	private uiState state; // changed UI_STATE to uiState StAtE to uiState state

	/
	public PayFineUI(PayFineControl control) {
		this.control = control; // changed CoNtRoL to control
		input = new Scanner(System.in);
		StAtE = UI_STATE.INITIALISED;
		control.Set_UI(this);
	}
	
	
	public void setState(uiState state) { // changed some names to proper notations
		this.state = state; // changed this.StAtE to this.state
	}


	public void run() { // changed function name to run
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) { // changed StAtE to state
			
			case READY:
				String memStr = input("Swipe member card (press <enter> to cancel): "); // changed variable name
				if (memStr.length() == 0) { // Changed variable name
					control.cancel(); // Changed variable name
					break;
				}
				try {
					int memberID = Integer.valueOf(memStr).intValue();  // Changed variables name
					control.cardSwiped(memberID);  // Changed variables names
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0; // Changed member name
				String amountStr = input("Enter amount (<Enter> cancels) : ");
				if (amountStr.length() == 0) { // changed variable name
					control.cancel(); // fixed variable name according to changes made
					break;
				}
				try {
					amount = Double.valueOf(amountStr).doubleValue();
				}
				catch (NumberFormatException e) {}
				if (amount <= 0) {
					output("Amount must be positive");
					break;
				}
				control.payFine(amount); // made changes to variable names
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
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
			

	// Changed function name to display
	public void display(Object object) {
		output(object);
	}


}
