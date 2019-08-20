import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class member implements Serializable {

	private String lastName; // Last name 
	private String firstName; // First name
	private String email; // Email
	private int phoneNo; // Phone no
	private int id; // ID
	private double fines; // fines variable
	
	private Map<Integer, loan> LNS;

	
	public member(String lastName, String firstName, String email, int phoneNo, int id) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.id = id;
		
		this.LNS = new HashMap<>();
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(id).append("\n")
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")
		  .append("  Email: ").append(email).append("\n")
		  .append("  Phone: ").append(phoneNo)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", fines))
		  .append("\n");
		
		for (loan LoAn : LNS.values()) {
			sb.append(LoAn).append("\n");
		}		  
		return sb.toString();
	}

	
	public int getId() {
		return id;
	}

	
	public List<loan> getLoans() {
		return new ArrayList<loan>(LNS.values());
	}

	
	public int numberOfCurrentLoans() {
		return LNS.size();
	}

	
	public double finesOwned() {
		return fines;
	}

	
	public void takeOutLoan(loan loan) {
		if (!LNS.containsKey(loan.ID())) {
			LNS.put(loan.ID(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String getLastName() {
		return lastName;
	}

	
	public String getFirstName() {
		return firstName;
	}


	public void addFine(double fine) {
		fines += fine;
	}
	
	public double payFine(double amount) {
		if (amount < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (amount > fines) {
			change = amount - fines;
			fines = 0;
		}
		else {
			fines -= amount;
		}
		return change;
	}


	public void dischargeLoan(loan loanAmount) {
		if (LNS.containsKey(load.id())) {
			LNS.remove(loan.id());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
