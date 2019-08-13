import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class member implements Serializable {

	private String LN; // Last Name
	private String FN; // First Name
	private String EM; // Email
	private int PN; // Phone Number
	private int ID; // ID
	private double FINES; 
	
	private Map<Integer, loan> LNS;

	
	// Function to Assign passed variables to their respective objects
	public member(String lastName, String firstName, String email, int phoneNo, int id) {
		this.LN = lastName;
		this.FN = firstName;
		this.EM = email;
		this.PN = phoneNo;
		this.ID = id;
		
		this.LNS = new HashMap<>();
	}

	
	// Function to make a string of all the data about a person
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(ID).append("\n")
		  .append("  Name:  ").append(LN).append(", ").append(FN).append("\n")
		  .append("  Email: ").append(EM).append("\n")
		  .append("  Phone: ").append(PN)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", FINES))
		  .append("\n");
		
		// changing loAn to loanValues in the for loop
		for (loan loanValues : LNS.values()) { 
			sb.append(loanValues).append("\n"); // replaced loAn with loanValues
		}		  
		return sb.toString();
	}

	
	// Changing GeT_ID to getID
	public int getID() {
		return ID;
	}

	
	// Changed Get_LoAns() with getLoans()
	public List<loan> getLoans() {
		return new ArrayList<loan>(LNS.values());
	}

	
	// Changed Number_Of_Current_Loans with NumberOfCurrentLoans
	public int NumberOfCurrentLoans() {
		return LNS.size();
	}

	
	// changed Fines_OwEd with finesOwed
	public double finesOwed() {
		return FINES;
	}

	
	// Changed Take_Out_Loan to TakeOutLoan
	public void TakeOutLoan(loan loan) {
		if (!LNS.containsKey(loan.ID())) {
			LNS.put(loan.ID(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member"); // thow exception
		}		
	}

	// Changed Get_LastName to getLastName
	public String getLastName() {
		return LN;
	}

	
	// Changed Get_FirstName to getFirstName
	public String getFirstName() {
		return FN;
	}


	// Changed Add_Fine to addFine
	public void addFine(double fine) {
		FINES += fine;
	}
	
	// Changed Pay_Fine to payFine
	public double Pay_Fine(double AmOuNt) {
		if (AmOuNt < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (AmOuNt > FINES) {
			change = AmOuNt - FINES;
			FINES = 0;
		}
		else {
			FINES -= AmOuNt;
		}
		return change;
	}


	// Changed dIsChArGeLoAn to dischargeLoan
	public void dischargeLoan(loan LoAn) {
		if (LNS.containsKey(LoAn.ID())) {
			LNS.remove(LoAn.ID());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
