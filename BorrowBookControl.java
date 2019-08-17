import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUi userInterface; // changes from BorrowBookUI to BorrowBookUi 
	
	private Library liberary; // change on the name
	private member M;
	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };// changes the enum name from CONTROL_STATE to ControlState.
	private ControlState State;
	
	private List<book> pending; //changes to pending from PENDING
	private List<loan> completed; // changes to completed from COMPLETED
	private Book book; //changes Book book from book BOOK
	
	
	public BorrowBookControl() {
		this.liberary = liberary.INSTANCE(); // changes the LIBERARY to liberary
		State = ControlState.INITIALISED; //CONTROL_STATE to COntrolState changes made
	}
	

	public void setUi(BorrowBookUi userInterface) { // change made on setuI to setUi and BorrowBookUI to BorrowBookUi and UI to userInterface
	
	// changes made on CONTROL_STATE to ControlState
		if (!State.equals(ControlState.INITIALISED)) 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
		// UI changes to userInterface on all	
		this.userInterface = userInterface;
		userInterface.Set_State(BorrowBookUI.UI_STATE.READY);
		State = ControState.READY;		
	}

		
	public void Swiped(int MEMMER_ID) {
		if (!State.equals(ControState.READY)) 
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		M = LIBRARY.MEMBER(MEMMER_ID);
		if (M == null) {
			userInterface.Display("Invalid memberId");
			return;
		}
		if (LIBRARY.MEMBER_CAN_BORROW(M)) {
			PENDING = new ArrayList<>();
			userInterface.Set_State(BorrowBookUI.UI_STATE.SCANNING);
			State = ControState.SCANNING; }
		else 
		{
			userInterface.Display("Member cannot borrow at this time");
			userInterface.Set_State(BorrowBookUI.UI_STATE.RESTRICTED); }}
	
	
	public void Scanned(int bookId) {
		BOOK = null;
		if (!State.equals(ControState.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		BOOK = LIBRARY.Book(bookId);
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
		if (LIBRARY.Loans_Remaining_For_Member(M) - PENDING.size() == 0) {
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
			userInterface.Set_State(BorrowBookUI.UI_STATE.FINALISING);
			State = ControState.FINALISING;
		}
	}


	public void Commit_LOans() {
		if (!State.equals(ControState.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book B : PENDING) {
			loan LOAN = LIBRARY.ISSUE_LAON(B, M);
			COMPLETED.add(LOAN);			
		}
		userInterface.Display("Completed Loan Slip");
		for (loan LOAN : COMPLETED) {
			userInterface.Display(LOAN.toString());
		}
		userInterface.Set_State(BorrowBookUI.UI_STATE.COMPLETED);
		State = ControState.COMPLETED;
	}

	
	public void cancel() {
		userInterface.Set_State(BorrowBookUI.UI_STATE.CANCELLED);
		State = ControState.CANCELLED;
	}
	
	
}
