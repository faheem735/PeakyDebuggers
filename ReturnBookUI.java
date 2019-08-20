import java.util.Scanner;


public class ReturnBookUI {

	public static enum uiState { INITIALISED, READY, INSPECTING, COMPLETED }; // made changes to variable name

	private ReturnBookControl control; // made changes to variable name
	private Scanner input;
	private uiState state; // made changes to variable name

	
	public ReturnBookUI(ReturnBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = uiState.INITIALISED;// made changes to variable name
		control.setUi(this); // made changes to variable name
	}


	public void run() { // changed function name		
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) { // corrected variable name
			
			case INITIALISED:
				break;
				
			case READY:
				String bookStr = input("Scan Book (<enter> completes): ");
				if (bookStr.length() == 0) {
					control.scanningCompelete(); // adjustment made to names
				}
				else {
					try {
						int Book_Id = Integer.valueOf(bookStr).intValue();
						control.bookScanned(bookId);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");
				boolean isDamaged = false;// made changes to variable name
				if (ans.toUpperCase().equals("Y")) {					
					isDamaged = true; // made changes to variable name
				}
				control.dischargeLoan(isDamaged);
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);			
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
	
	public void setState(uiState state) { // made changes to function name
		this.state = state;
	}

	
}
