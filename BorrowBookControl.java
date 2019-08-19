import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUi userInterface; // changes from BorrowBookUI to BorrowBookUi 
	
	private Library liberary; // change on the name
	private member M;
	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };// changes the enum name from CONTROL_STATE to ControlState.
	private ControlState state;
	
	private List<book> pending; //changes to pending from PENDING
	private List<loan> completed; // changes to completed from COMPLETED
	private Book book; //changes Book book from book BOOK
	
	
	public BorrowBookControl() {
		this.liberary = liberary.INSTANCE(); // changes the LIBERARY to liberary
		state = ControlState.INITIALISED; //CONTROL_STATE to COntrolState changes made
	}
	

	public void setUi(BorrowBookUi userInterface) { // change made on setuI to setUi and BorrowBookUI to BorrowBookUi and UI to userInterface
	
	// changes made on CONTROL_STATE to ControlState
		if (!state.equals(ControlState.INITIALISED)) { // added { afer the if condition
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
		// UI changes to userInterface on all	
		this.userInterface = userInterface;
		userInterface.setState(BorrowBookUI.userInterfaceState.READY); // changes from UI_STATE to userInterfaceState
		state = ControState.READY;	
		}		
	}

		
	public void Swiped(int memberId) {// change MEMMER_ID to memberId
		if (!state.equals(ControState.READY)) 
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
		// change LIBRARY to Library	
		M = Library.member(memberId); // change MEMMER_ID to memberId
		if (M == null) {
			userInterface.Display("Invalid memberId");
			return;
		}
		if (Library.MEMBER_CAN_BORROW(M)) {
			PENDING = new ArrayList<>();
			userInterface.setState(BorrowBookUI.userInterfaceState.SCANNING);
			state = ControState.SCANNING; }
		else 
		{
			userInterface.Display("Member cannot borrow at this time");
			userInterface.setState(BorrowBookUI.userInterfaceState.RESTRICTED); }}
	
	
	public void Scanned(int bookId) {
		BOOK = null;
		if (!state.equals(ControState.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		BOOK = Library.Book(bookId);
		if (BOOK == null) {
			userInterface.Display("Invalid bookId");
			return;
		}
		if (!BOOK.AVAILABLE()) {
			userInterface.Display("Book cannot be borrowed");
			return;
		}
		PENDING.add(BOOK);
		for (book B : PENDING) {
			userInterface.Display(B.toString());
		}
		if (Library.Loans_Remaining_For_Member(M) - PENDING.size() == 0) {
			userInterface.Display("Loan limit reached");
			Complete();
		}
	}
	
	
	public void Complete() {
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			userInterface.Display("\nFinal Borrowing List");
			for (book B : PENDING) {
				userInterface.Display(B.toString());
			}
			COMPLETED = new ArrayList<loan>();
			userInterface.setState(BorrowBookUI.userInterfaceState.FINALISING);// changes made from Set_State to setState
			state = ControState.FINALISING;
		}
	}


	public void commitLoans() { // change made on method name Commit_Loans
		if (!state.equals(ControState.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book B : PENDING) {
			loan LOAN = Library.ISSUE_LAON(B, M);
			COMPLETED.add(LOAN);			
		}
		userInterface.Display("Completed Loan Slip");
		for (loan LOAN : COMPLETED) {
			userInterface.Display(LOAN.toString());
		}
		userInterface.setState(BorrowBookUI.userInterfaceState.COMPLETED); // changes made from Set_State to setState
		state = ControState.COMPLETED;
	}

	
	public void cancel() {
		userInterface.SetState(BorrowBookUI.userInterfaceState.CANCELLED); //// changes made from Set_State to setState
		state = ControState.CANCELLED;
	}
	
	
}
