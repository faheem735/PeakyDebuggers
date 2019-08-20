public class ReturnBookControl {

	private ReturnBookUI ui; // Changed variable name
	private enum controlState { INITIALISED, READY, INSPECTING }; // Changed variable name
	private controlState state; // Changed variable name
	
	private library library; // Changed variable name
	private loan currentLoan; // Changed variable name
	

	public ReturnBookControl() {
		this.library = library.INSTANCE(); // fixed variable names
		state = controlState.INITIALISED; // made correction to the variables names
	}
	
	
	public void setUi(ReturnBookUI ui) { // changed function name
		if (!state.equals(controlState.INITIALISED)) { // fixed variable names
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");	
				}			this.Ui = ui;
		ui.Set_State(ReturnBookUI.UI_STATE.READY);
		sTaTe = CONTROL_STATE.READY;		
	}


	public void Book_scanned(int Book_ID) {
		if (!sTaTe.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book currentBook = library.Book(bookID); // Changed variable names
		
		if (currentBook == null) { // Changed variable name
			ui.display("Invalid Book Id");
			return;
		}
		if (!currentBook.onLoan()) { // Changed variable name
			ui.display("Book has not been borrowed");
			return;
		}		
		currentLoan = library.loanByBookId(bookId);	// Changed variable name
		double overDueFine = 0.0; // Changed variable name
		if (currentLoan.overDue()) { // Changed variable name
			overDueFine = library.calculateOverDueFine(currentLoan); // Changed variable name
		}
		ui.display("Inspecting"); // Changed variable name
		ui.display(currentBook.toString()); // Changed variable name
		ui.display(currentLoan.toString()); // Changed variable name
		
		if (currentLoan.overDue()) { // Changed variable name
			ui.display(String.format("\nOverdue fine : $%.2f", overDueFine)); // Changed variable name
		}
		ui.Set_State(ReturnBookUI.UI_STATE.INSPECTING); // Changed variable name
		state = controlState.INSPECTING;		 // Changed variable name
	}


	public void scanningCompelete() { // Changed function name
		if (!state.equals(controlState.READY)) { // Changed variable name
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		ui.Set_State(ReturnBookUI.UI_STATE.COMPLETED); // Changed variable name
	}


	public void dischargeLoan(boolean isDamaged) {// Changed variable name
		if (!state.equals(controlState.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		library.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		ui.Set_State(ReturnBookUI.UI_STATE.READY); // Changed variable name
		state = controlState.READY;			// Changed variable name	
	}


}
