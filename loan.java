import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable { //Changed Class name to Loan-Zeeshan 13/8
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED };//Changed LOAN_STATE to LoanState-Zeeshan 13/8
	
	private int loanId;//Changed id to loanId -Zeeshan 13/8
	private book book;//Changed b to book-Zeeshan 13/8
	private member member;//Changed m to member-Zeeshan 13/8
	private Date date;//Changed d to date-Zeeshan 13/8
	private loanState state;//Changed LOAN_STATE to loanState

	
	public Loan(int loanId, book book, member member, Date dueDate) {//Changed class name to Loan-Zeeshan 13/8
		this.loanId = loanId;//Changed id to loanId -Zeeshan 13/8
		this.book = book;//Changed b to book-Zeeshan 13/8
		this.member = member;//Changed m to member-Zeeshan 13/8
		this.date = dueDate;//Changed d to date-Zeeshan 13/8
		this.state = loanState.CURRENT;//Changed LOAN_STATE to loanState Zeeshan 13/8
	}

	
	public void checkOverDue() {
		if (state == loanState.CURRENT && //Changed LOAN_STATE to loanState Zeeshan 13/8
			Calendar.INSTANCE().Date().after(date)) {
			this.state = loanState.overDue;////Changed LOAN_STATE to loanState Zeeshan 13/8			
		}
	}

	
	public boolean getOverDue() {//Changed function name to getOverDue-Zeeshan 13/8
		return state == loanState.overDue;////Changed LOAN_STATE to loanState Zeeshan 13/8
	}

	
	public Integer getLoanId() {//Changed Function name to getLoanId-Zeeshan 13/8
		return loanId;//Changed loanId-Zeeshan 13/8
	}


	public Date getDueDate() {//Changed function name to getDueDate-Zeeshan 13/8
		return date;//Changed D to date-Zeeshan 13/8
	}
	
	
	public String toString() {
		simpleDateFormat sdf = new simpleDateFormat("dd/MM/yyyy");//Changed Function name to simpleDateFormat-Zeeshan 13/8 

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(ID).append("\n")
		  .append("  Borrower ").append(M.getId()).append(" : ")//Change GET_ID to getID-Zeeshan 13/8
		  .append(M.getLastName()).append(", ").append(M.getFirstName()).append("\n")//Changed function name to getLastName and getFirstName-Zeeshan 13/8
		  .append("  Book ").append(book.ID()).append(" : " )
		  .append(B.tittle()).append("\n")//Changed function name to tittle -Zeeshan 13/8
		  .append("  DueDate: ").append(sdf.format(D)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public member getMember() {//Chnaged function name to getMember-Zeeshan 13/8
		return member;//Changed member-Zeeshan 13/8
	}


	public book Book() {
		return B;
	}


	public void DiScHaRgE() {
		state = LOAN_STATE.DISCHARGED;		
	}

}
