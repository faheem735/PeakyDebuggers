public class ReturnBookControl {

	private ReturnBookUI Ui;
	private enum CONTROL_STATE { INITIALISED, READY, INSPECTING };
	private CONTROL_STATE state;
	
	private library library;
	private loan currentLoan;
	

	public ReturnBookControl() {
		this.library = library.INSTANCE();
		state = CONTROL_STATE.INITIALISED;
	}
	
	
	public void Set_UI(ReturnBookUI ui) {
		if (!state.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}			this.Ui = ui;
		ui.Set_State(ReturnBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}


	public void bookScanned(int Book_ID) {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book currentBook = library.Book(bookID);
		
		if (currentBook == null) {
			Ui.display("Invalid Book Id");
			return;
		}
		if (!currentBook.onloan()) {
			Ui.display("Book has not been borrowed");
			return;
		}		
		currentLoan = library.LOAN_BY_BOOK_ID(bookID);	
		double overDueFine = 0.0;
		if (currentLoan.overDue()) {
			Over_Due_Fine = lIbRaRy.CalculateOverDueFine(CurrENT_loan);
		}
		Ui.display("Inspecting");
		Ui.display(currentBook.toString());
		Ui.display(currentLoan.toString());
		
		if (currentLoan.OVer_Due()) {
			Ui.display(String.format("\nOverdue fine : $%.2f", overDueFine));
		}
		Ui.Set_State(ReturnBookUI.UI_STATE.INSPECTING);
		state = CONTROL_STATE.INSPECTING;		
	}


	public void Scanning_Complete() {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		Ui.Set_State(ReturnBookUI.UI_STATE.COMPLETED);		
	}
 

	public void dischargeLoan(boolean isDamaged) {
		if (!state.equals(CONTROL_STATE.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		library.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		Ui.Set_State(ReturnBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;				
	}


}
