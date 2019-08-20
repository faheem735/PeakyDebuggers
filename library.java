
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Library implements Serializable {//Changed Class name from library to Library-Zeeshan 13/8
	
	private static final String libraryFile = "library.obj";
	private static final int loanLimit = 2;
	private static final int loanPeriod = 2;
	private static final double finePerDay = 1.0;
	private static final double maxFinesOwed = 1.0;
	private static final double damageFee = 2.0;
	
	private static library self;//Changed self from Self-Zeeshan 13/8
	private int bookId;//Changed BOOK-ID to bookId-Zeeshan 13/8
	private int memberId;//Changed MEMBER_ID to memberId-Zeeshan 13/8
	private int loanId;//Changed LOAN_ID to loanId-Zeeshan 13/8
	private Date loanDate;//Changed LOAN_DATE  to loanDate-Zeeshan 13/8
	
	private Map<Integer, book> Catalog;//Changed CATALOG to Catlog-Zeeshan 13/8
	private Map<Integer, member> Members;//Changed MEMBERS to Member-Zeeshan 13/8
	private Map<Integer, loan> Loans;//Changed LOANS to Loans -Zeeshan 13/8
	private Map<Integer, loan> Currentloans;//Changed CURRENT_LOANS TO Currentloans-Zeeshan 13/8
	private Map<Integer, book> Damagedbooks;//Changed DAMAGED_BOOKS TO Damagedbooks-Zeeshan 13/8
	

	private Library() {//Changed class name from library to Library-Zeeshan 13/8
		Catlog = new HashMap<>();//Changed CATALOG to Catlog-Zeeshan 13/8
		Members = new HashMap<>();//Changed MEMBERS to Member-Zeeshan 13/8
		Loans = new HashMap<>();//Changed LOANS to Loans -Zeeshan 13/8
		Currentloans = new HashMap<>();//Changed CURRENT_LOANS TO Currentloans-Zeeshan 13/8
		Damagedbooks = new HashMap<>();//Changed DAMAGED_BOOKS TO Damagedbooks-Zeeshan 13/8
		bookId = 1;//Changed BOOK-ID to bookId-Zeeshan 13/8
		memberId = 1;//Changed MEMBER_ID to memberId-Zeeshan 13/8		
		loanId = 1;//Changed LOAN_ID to loanId-Zeeshan 13/8		
	}

	
	public static synchronized library  getInstance() {//Changed Function name from INSTANCE to getInstance 		
		if (self == null) { //Changed self from Self-Zeeshan 13/8
			Path getPath = Paths.get(libraryFile);//Changed PATH to getPath-Zeeshan 13/8			
			if (Files.exists(getPath)) {	//Changed PATH to getPath-Zeeshan 13/8	
				try (ObjectInputStream LiF = new ObjectInputStream(new FileInputStream(libraryFile));) {
			    
					self = (library) LiF.readObject();//Changed self from Self-Zeeshan 13/8
					Calendar.INSTANCE().Set_dATE(SeLf.LOAN_DATE);//Changed Set_date to setDate-Zeeshan 13/8
					LiF.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new Library();//Changed self from Self-Zeeshan 13/8
		}
		return self;//Changed self from Self-Zeeshan 13/8
	}

	
	public static synchronized void toSave() {//Changed function name from save to toSave-Zeeshan 13/8
		if (self != null) {//Changed self from Self-Zeeshan 13/8
			self.LOAN_DATE = Calendar.INSTANCE().Date();//Changed self from Self-Zeeshan 13/8
			try (ObjectOutputStream LoF = new ObjectOutputStream(new FileOutputStream(libraryFile));) {
				LoF.writeObject(SeLf);
				LoF.flush();
				LoF.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int getBookId() {//Changed function name from BookID to getBookId-Zeeshan 13/8
		return bookId;////Changed   BookID to bookID-Zeeshan 13/8

	}
	
	
	public int getMemberId() {//Changed Function name from memberID to getMemberId-Zeeshan 13/8
		return memberId;////Changed memberID to memberId-Zeeshan 13/8
	}
	
	
	private int getNextBookID() {//Changed function name to getNextBookID-Zeeshan 13/8
		return bookID++;//////Changed   BookID to bookID-Zeeshan 13/8
	}

	
	private int getNextMemberId() {//Changed function name to getNextMemberId-Zeeshan 13/8
		return memberId++;//Changed MEMBER_ID to memberID-Zeeshan 13/8
	}

	
	private int getNextLoanId() {//Changed function name to getNextLoanId-Zeeshan 13/8
		return loanId++;//Changed LOAN_ID to loanId
	}

	
	public List<member> Members() {//Changed interface name from MEMBERS to Members-Zeeshan 13/8		
		return new ArrayList<member>(Members.values());//Changed interface name from MEMBERS to Members-Zeeshan 13/8	 
	}


	public List<book> Books() {//Changed interface name from BOOKS to Books-Zeeshan 13/8			
		return new ArrayList<book>(CATALOG.values()); 
	}


	public List<loan> CurrentLoans() {
		return new ArrayList<loan>(CURRENT_LOANS.values());
	}


	public member addMember(String lastName, String firstName, String email, int phoneNo) {//Changed function name to addMember-Zeeshan 13/8		
		member member = new member(lastName, firstName, email, phoneNo, getNextMemberId());////Changed function name to getNextMemberId-Zeeshan 13/8-Zeeshan 13/8
		Members.put(member.getID(), member);//Chaned interface name to Members and GET_ID to getId-Zeeshan 13/8		
		return member;
	}

	
	public book addBook(String a, String t, String c) {//Changed function name to addBook-Zeeshan 13/8		
		book b = new book(a, t, c, getNextBookID());//Changed function name to getNextBookID-Zeeshan 13/8
		Catalog.put(b.ID(), b);//Changed Interface name to Catlog		-Zeeshan 13/8
		return b;
	}

	
	public member members(int memberId) {//Changed function name to members-Zeeshan 13/8
		if (Members.containsKey(memberId)){//Changed interface nameto Members-Zeeshan 13/8 
		return MEMBERS.get(memberId)};//Changed interface nameto Members-Zeeshan 13/8 
		return null;
	}

	
	public book Book(int bookId) {
		if (CATALOG.containsKey(bookId)) 
			return CATALOG.get(bookId);		
		return null;
	}

	
	public int getLoanLimit() {//Changed function name to getLoanLimit-Zeeshan 13/8
		return loanLimit;
	}

	
	public boolean toBorrow(member member) {//Changed function name to toBorrow from MEMBER_CAN_BORROW-Zeeshan 13/8		
		if (member.NumberOfCurrentLoans() == loanLimit ) 
			return false;
				
		if (member.Fines_OwEd() >= maxFinesOwed) 
			return false;
				
		for (loan loan : member.getLoans())//CHanged function name from Get_loans to getLoans-Zeeshan 13/8 
			if (loan.overDue())//Changed function name fro Over_DUE to overDue-Zeeshan 13/8 
				return false;
			
		return true;
	}

	
	public int remainingLoans(member member) {//Changed function name from Loans_Remaining_For_Member to remainingLoans-Zeeshan 13/8		
		return loanLimit - member.NumberOfCurrentLoans();
	}

	
	public loan issueLoan(book book, member member) {//Changed function name from ISSUE_LOAN to issueLoan-Zeeshan 13/8
		Date dueDate = Calendar.INSTANCE().dueDate(loanPeriod);//Changed DUE_DATE to dueDate-Zeeshan 13/8
		loan loan = new loan(getNextLoanId(), book, member, dueDate);//Changed function name to getNextLoanId-Zeeshan 13/8
		member.takeOutLoan(loan);//Changed functio name fro Take_out_loan to takeOUtLOan-Zeeshan 13/8
		book.toBorrow();//Changed function name to toBorrow-Zeeshan 13/8
		Loans.put(loan.ID(), loan);
		CurrentLoan.put(book.ID(), loan);//Changed Curent_loans to CurrentLoan-Zeeshan 13/8
		return loan;
	}
	
	
	public loan loanByBookId(int bookId) {//Change function name to loanByBookId-Zeeshan 13/8
		if (CurrentLoans.containsKey(bookId)) {//CAhnged CurrentLoans-Zeeshan 13/8
			return CurrentLoans.get(bookId);//CAhnged CurrentLoans-Zeeshan 13/8
		}
		return null;
	}

	
	public double CalculateOverDueFine(loan loan) {
		if (loan.OVer_Due()) {
			long daysOverDue = Calendar.INSTANCE().getDaysDifference(loan.getDueDate());//Chnage function name to getDueDate() and getDaysDifference-Zeeshan 13/8
			double fine = daysOverDue * finePerDay;
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(loan currentLoan, boolean isDamaged) {//Changed function name to dischargeLoan-Zeeshan 13/8
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = CalculateOverDueFine(currentLoan);
		member.addFine(overDueFine);//Changed AddFine to addFine-Zeeshan 13/8	
		
		member.dischargeLoan(currentLoan);//Changed function name to dischargeLoan-Zeeshan 13/8
		book.Return(isDamaged);
		if (isDamaged) {
			member.addFine(damageFee);//Changed AddFine to addFine-Zeeshan 13/8	
		damagedBooks.put(book.ID(), book);
		}
		currentLoan.discharge();//Changed function name to discharge-Zeeshan 13/8
		curretLoan.remove(book.ID());//Changed CurrentLoans to currentLoan-Zeeshan 13/8
	}


	public void checkCurrentLoans() {
		for (loan loan : CurrentLoan.values()) {//Changed CurrentLoans to currentLoan-Zeeshan 13/8
			loan.checkOverDue();
		}		
	}


	public void repairBook(book currentBook) {//Changed repairBook -Zeeshan 13/8
		if (damagedBooks.containsKey(currentBook.ID())) {//Changed Damaged_boks -Zeeshan 13/8
			currentBook.Repair();
			damagedBooks.remove(currentBook.ID());//Changed Damaged_boks -Zeeshan 13/8
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
