import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUi userInterface; // changes from BorrowBookUI to BorrowBookUi 
	
	private Library liberary; // change on the name
	private Member member; // Change m to member
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
		member = Library.member(memberId); // change MEMMER_ID to memberId
		if (member == null) { // change m to member
			userInterface.display("Invalid memberId");
			return;
		}
		if (Library.MEMBER_CAN_BORROW(M)) {
			PENDING = new ArrayList<>();
			userInterface.setState(BorrowBookUI.userInterfaceState.SCANNING);
			state = ControState.SCANNING; }
		else 
		{
			userInterface.display("Member cannot borrow at this time");
			userInterface.setState(BorrowBookUI.userInterfaceState.RESTRICTED); }}
	
	
	public void Scanned(int bookId) {
		// changes made on all BOOK to book
		book = null;
		if (!state.equals(ControState.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		book = Library.Book(bookId);
		if (book == null) {
			userInterface.display("Invalid bookId");
			return;
		}
		if (!book.AVAILABLE()) {
			userInterface.display("Book cannot be borrowed");
			return;
		}
		// changes  all PENDING to pending by sudeep
		pending.add(book);
		for (book B : pending) {
			userInterface.display(B.toString());
		}
		if (Library.Loans_Remaining_For_Member(M) - pending.size() == 0) {
			userInterface.display("Loan limit reached");
			complete(); // changes to complete from Complete by sudeep
		}
	}
	
	
	public void complete() { // changes from Complete to complete by sudeep
		if (pending.size() == 0) {
			cancel();
		}
		else {
			userInterface.display("\nFinal Borrowing List");
			for (book B : pending) {
				userInterface.display(B.toString());
			}
			COMPLETED = new ArrayList<loan>();
			userInterface.setState(BorrowBookUI.userInterfaceState.FINALISING);// changes made from Set_State to setState by sudeep
			state = ControState.FINALISING;
		}
	}


	public void commitLoans() { // change made on method name Commit_Loans by sudeep
		if (!state.equals(ControState.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		// changes from LOAN to loan
		for (book B : pending) {
			loan loan = Library.issueLoan(B, M);  // ISSUE_LAON to issueLoan by sudeep
			COMPLETED.add(loan);			
		}
		userInterface.display("Completed Loan Slip");
		for (loan loan : COMPLETED) {
			userInterface.display(loan.toString());
		}
		userInterface.setState(BorrowBookUI.userInterfaceState.COMPLETED); // changes made from Set_State to setState by sudeep
		state = ControState.COMPLETED;
	}

	
	public void cancel() {
		userInterface.SetState(BorrowBookUI.userInterfaceState.CANCELLED); //// changes made from Set_State to setState by sudeep
		state = ControState.CANCELLED;
	}
	
	
}
