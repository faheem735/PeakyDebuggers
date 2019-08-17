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

	
	public loan(int loanId, book book, member member, Date dueDate) {
		this.ID = loanId;
		this.B = book;
		this.M = member;
		this.D = dueDate;
		this.state = LOAN_STATE.CURRENT;
	}

	
	public void checkOverDue() {
		if (state == LOAN_STATE.CURRENT &&
			Calendar.INSTANCE().Date().after(D)) {
			this.state = LOAN_STATE.OVER_DUE;			
		}
	}

	
	public boolean OVer_Due() {
		return state == LOAN_STATE.OVER_DUE;
	}

	
	public Integer ID() {
		return ID;
	}


	public Date Get_Due_Date() {
		return D;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(ID).append("\n")
		  .append("  Borrower ").append(M.GeT_ID()).append(" : ")
		  .append(M.Get_LastName()).append(", ").append(M.Get_FirstName()).append("\n")
		  .append("  Book ").append(B.ID()).append(" : " )
		  .append(B.TITLE()).append("\n")
		  .append("  DueDate: ").append(sdf.format(D)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public member Member() {
		return M;
	}


	public book Book() {
		return B;
	}


	public void DiScHaRgE() {
		state = LOAN_STATE.DISCHARGED;		
	}

}
