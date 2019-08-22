import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUi userInterface; // changes from BorrowBookUI to BorrowBookUi  by sudeep on 21/8
	
	private Library liberary; // change on the name by sudeep on 21/8
	private Member member; // Change m to member by sudeep on 21/8
	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };// changes the enum name from CONTROL_STATE to ControlState.
	private ControlState state;
	
	private List<book> pending; //changes to pending from PENDING  by sudeep on 21/8
	private List<loan> completed; // changes to completed from COMPLETED by sudeep on 21/8
	private Book book; //changes Book book from book BOOK by sudeep on 21/8
	
	
	public BorrowBookControl() {
		this.liberary = liberary.INSTANCE(); // changes the LIBERARY to liberary by sudeep on 21/8
		state = ControlState.INITIALISED; //CONTROL_STATE to COntrolState changes made by sudeep on 21/8
	}
	

	public void setUserInterface(BorrowBookUi userInterface) { // change made on setuI to setUi and BorrowBookUI to BorrowBookUi and UI to userInterface by sudeep on 21/8
	
	// changes made on CONTROL_STATE to ControlState by sudeep on 21/8
		if (!state.equals(ControlState.INITIALISED)) { // added { afer the if condition by sudeep on 21/8
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
		// UI changes to userInterface on all	
		this.userInterface = userInterface;
		userInterface.setState(BorrowBookUI.userInterfaceState.READY); // changes from UI_STATE to userInterfaceState
		state = ControState.READY;	
		}		
	}

		
	public void swiped(int memberId) {// change MEMMER_ID to memberId
		if (!state.equals(ControState.READY)) 
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
		// change LIBRARY to Library	
			member = Library.member(memberId); // change MEMMER_ID to memberId
		if (member == null) { // change m to member
			userInterface.display("Invalid memberId");
			return;
		}
		if (Library.memberCanBorrow(member)) { //MEMBER_CAN_BORROW to memberCanBorrow by sudeep
			PENDING = new ArrayList<>();
			userInterface.setState(BorrowBookUI.userInterfaceState.SCANNING);
			state = ControState.SCANNING; }
		else 
		{
			userInterface.display("Member cannot borrow at this time");
			userInterface.setState(BorrowBookUI.userInterfaceState.RESTRICTED); }}
	
	
	public void scanned(int bookId) {
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
		if (Library.loanRemainingForMember(member) - pending.size() == 0) {// Loans_Remaining_For_Member to loanRemainingForMember by sudeep
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
		userInterface.SetState(BorrowBookUI.userInterfaceState.CANCELLED); // changes made from Set_State to setState by sudeep
		state = ControState.CANCELLED;
	}
	
	
}
